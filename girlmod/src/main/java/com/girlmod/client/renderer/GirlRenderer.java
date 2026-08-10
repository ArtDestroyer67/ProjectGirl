package com.girlmod.client.renderer;

import com.girlmod.client.model.GirlModel;
import com.girlmod.entity.GirlEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

@OnlyIn(Dist.CLIENT)
public class GirlRenderer extends GeoEntityRenderer<GirlEntity> {

    // Single shared model instance: this is what GeckoLib actually uses
    // internally for rendering (passed to super() below) AND what we use
    // for our own texture-location / render-pass-flag logic. These must
    // be the SAME object — passing a second, separate `new GirlModel()`
    // to super() here previously meant setRenderingPartnerPass() below was
    // flipping a flag on an instance GeckoLib never reads from, so the
    // partner rig kept resolving to girl.png no matter what.
    private final GirlModel girlModel;

    /**
     * All bone names in girl_dressed.geo.json that are part of the armor.
     * When hasAnyArmorEquipped() is false these bones are scaled to 0 so they vanish
     * without needing a separate geo file.
     */
    private static final Set<String> ARMOR_BONES = new HashSet<>(Arrays.asList(
        "armorHelmet",
        "armorShoulderL", "armorShoulderR",
        "armorBoobs",     "armorChest",
        "armorBootyL",    "armorBootyR",
        "armorHip",
        "armorPantsUpL",  "armorPantsUpR",
        "armorPantsLowL", "armorPantsLowR",
        "armorShoesL",    "armorShoesR"
    ));

    /**
     * Top-level bone name of the embedded male "steve" rig baked into the
     * same geo file — used for synced penetration animations during sex
     * positions. Hidden by default; only shown when the current state's
     * showPartnerRig flag is true (see states.json / StateDefinition).
     * Scaling this one root bone to 0 hides its entire subtree (torso,
     * head, arms, legs, everything).
     */
    private static final String PARTNER_RIG_BONE = "steve";

    /**
     * Bone name in girl.geo.json/girl_dressed.geo.json that acts as the
     * mainhand attachment point — see mobInteract's weapon-equip shift-
     * click and renderRecursively() below, which draws whatever's in
     * EquipmentSlotType.MAINHAND at this bone's transform each frame.
     */
    private static final String WEAPON_BONE = "weapon";

    // Stashed in renderEarly so renderRecursively can hand it back to
    // GeckoLib after rendering the held item — required per GeckoLib's own
    // documented pattern for this (the render-type buffer GeckoLib expects
    // to keep writing into gets swapped out while item rendering runs).
    private IRenderTypeBuffer rtb;
    // renderRecursively() doesn't receive the entity as a parameter in
    // this GeckoLib version, so we stash it ourselves at the top of our
    // own render() override (which does receive it) for use there.
    private GirlEntity currentEntity;

    // ── Physics-like bone sway (bell, ponytails, boobs, cowtail) ────────────
    // GeckoLib has no built-in physics simulation for bones — this fakes
    // convincing sway with a simple damped-spring integration per bone,
    // chasing a target angle driven by her horizontal speed (rhythmic sway
    // while moving) and yaw delta (a lag/wobble kick when turning). Applied
    // in renderEarly() each frame, same technique already used for armor
    // bone visibility above.
    //
    // The renderer instance is shared across every GirlEntity in the
    // world (Minecraft registers one renderer per entity TYPE, not per
    // instance), so per-entity physics state (current angle/velocity per
    // bone) can't live in plain fields here — it's tracked per-entity in
    // this WeakHashMap instead, which also means entries are automatically
    // freed once a girl entity is unloaded/removed.
    private final Map<GirlEntity, SwayState> swayStates = new WeakHashMap<>();

    private static final class SwayState {
        float prevYaw = Float.NaN;
        boolean physicsAppliedThisFrame = false;
        final float[] angle = new float[SWAY_CONFIGS.length];
        final float[] angVel = new float[SWAY_CONFIGS.length];
    }

    /** Tune these per bone if the sway looks too stiff/loose/fast — not visually verified, expect to adjust by eye. */
    private static final class SwayConfig {
        final String bone;
        final float stiffness;   // higher = snaps back to rest faster
        final float damping;     // higher = settles faster, less overshoot/wobble
        final float moveScale;   // how much horizontal speed contributes to the swing amplitude
        final float turnScale;   // how much a sudden yaw change "kicks" the bone
        final float frequency;   // speed of the rhythmic walking-sway oscillation
        final float phase;       // offset into the oscillation, so paired bones (boobL/R) don't move in perfect lockstep
        final Axis  axis;        // which local rotation axis the sway is applied to

        SwayConfig(String bone, float stiffness, float damping, float moveScale, float turnScale, float frequency, float phase, Axis axis) {
            this.bone = bone; this.stiffness = stiffness; this.damping = damping;
            this.moveScale = moveScale; this.turnScale = turnScale;
            this.frequency = frequency; this.phase = phase; this.axis = axis;
        }
    }

    private enum Axis { X, Y, Z }

    // Axis choice here is a best guess (front-back swing on X, turn-lag
    // lean on Z) — if a bone sways in the wrong direction in-game, that's
    // the field to change first.
    private static final SwayConfig[] SWAY_CONFIGS = {
        new SwayConfig("bell",      0.35f, 0.35f,  6f, 0.40f, 1.1f, 0.0f, Axis.X),
        new SwayConfig("ponyTailL", 0.20f, 0.28f, 10f, 0.60f, 0.9f, 0.0f, Axis.Z),
        new SwayConfig("ponyTailR", 0.20f, 0.28f, 10f, 0.60f, 0.9f, 0.2f, Axis.Z),
        new SwayConfig("boobL",     0.28f, 0.30f,  5f, 0.30f, 1.6f, 0.0f, Axis.X),
        new SwayConfig("boobR",     0.28f, 0.30f,  5f, 0.30f, 1.6f, 0.3f, Axis.X),
        new SwayConfig("cowtail",   0.18f, 0.25f,  8f, 0.50f, 0.8f, 0.0f, Axis.X),
    };

    // ponyTailL2/R2 share the exact same pivot as ponyTailL/R in the geo
    // file (a second segment of the same tail), so they just mirror
    // whatever angle was computed for the primary bone rather than running
    // their own independent simulation.
    private static final String[] PONYTAIL_L_FOLLOWERS = { "ponyTailL2" };
    private static final String[] PONYTAIL_R_FOLLOWERS = { "ponyTailR2" };

    private void applyPhysicsSway(GirlEntity entity) {
        SwayState state = swayStates.computeIfAbsent(entity, e -> new SwayState());
        if (state.physicsAppliedThisFrame) return; // already stepped this frame — see render()'s reset
        state.physicsAppliedThisFrame = true;

        double dx = entity.getX() - entity.xo;
        double dz = entity.getZ() - entity.zo;
        float speed = (float) Math.sqrt(dx * dx + dz * dz); // horizontal distance moved since last tick

        float yaw = entity.yBodyRot;
        float yawDelta = 0f;
        if (!Float.isNaN(state.prevYaw)) {
            yawDelta = MathHelper.wrapDegrees(yaw - state.prevYaw);
        }
        state.prevYaw = yaw;

        @SuppressWarnings("unchecked")
        AnimatedGeoModel<GirlEntity> animatedModel = (AnimatedGeoModel<GirlEntity>) this.getGeoModelProvider();

        for (int i = 0; i < SWAY_CONFIGS.length; i++) {
            SwayConfig cfg = SWAY_CONFIGS[i];

            float oscillation = (float) Math.sin(entity.tickCount * cfg.frequency + cfg.phase) * speed * cfg.moveScale;
            float turnKick = -yawDelta * cfg.turnScale;
            float target = oscillation + turnKick;

            float accel = (target - state.angle[i]) * cfg.stiffness - state.angVel[i] * cfg.damping;
            state.angVel[i] += accel;
            state.angle[i]  += state.angVel[i];

            IBone bone = animatedModel.getAnimationProcessor().getBone(cfg.bone);
            setSwayAxis(bone, cfg.axis, state.angle[i]);

            if (cfg.bone.equals("ponyTailL")) {
                for (String follower : PONYTAIL_L_FOLLOWERS) {
                    setSwayAxis(animatedModel.getAnimationProcessor().getBone(follower), cfg.axis, state.angle[i]);
                }
            } else if (cfg.bone.equals("ponyTailR")) {
                for (String follower : PONYTAIL_R_FOLLOWERS) {
                    setSwayAxis(animatedModel.getAnimationProcessor().getBone(follower), cfg.axis, state.angle[i]);
                }
            }
        }
    }

    private static void setSwayAxis(IBone bone, Axis axis, float degrees) {
        if (bone == null) return;
        switch (axis) {
            case X: bone.setRotationX(degrees); break;
            case Y: bone.setRotationY(degrees); break;
            case Z: bone.setRotationZ(degrees); break;
        }
    }

    public GirlRenderer(EntityRendererManager manager) {
        this(manager, new GirlModel());
    }

    // Private chaining constructor so the single GirlModel instance can be
    // created once, passed to super(), and stashed in the field — a plain
    // field initializer can't be used here because instance field
    // initializers run *after* super() returns, so `girlModel` would still
    // be null at the point super(manager, girlModel) needed it.
    private GirlRenderer(EntityRendererManager manager, GirlModel model) {
        super(manager, model);
        this.girlModel = model;
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) {
        return girlModel.getTextureLocation(entity);
    }

    /**
     * Top-level bones that hold the entire girl mesh in both geo files
     * ("items" only exists in girl_dressed.geo.json — harmless no-op lookup
     * on the nude model). Kept separate from PARTNER_RIG_BONE ("steve"),
     * which is its own top-level bone.
     */
    private static final String[] GIRL_ROOT_BONES = { "body", "items" };

    /**
     * Draws the entity in up to two passes so the girl body and the
     * embedded "steve" partner rig can each use their own texture
     * (GeckoLib binds a single texture per render() call, so a mid-draw
     * texture swap isn't possible — two full passes, each hiding the
     * bones that don't belong to it, is the standard workaround).
     *
     * Pass 1 — girl body visible, steve hidden, girl.png bound.
     * Pass 2 — only steve visible, girl body hidden, player/steve.png
     *          bound. Only runs when the current pose calls for the
     *          partner rig to be shown at all.
     */
    @Override
    public void render(GirlEntity entity, float entityYaw, float partialTicks,
                        MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
                        int packedLightIn) {

        this.currentEntity = entity; // see field comment — needed by renderRecursively()
        swayStates.computeIfAbsent(entity, e -> new SwayState()).physicsAppliedThisFrame = false;

        @SuppressWarnings("unchecked")
        AnimatedGeoModel<GirlEntity> animatedModel =
            (AnimatedGeoModel<GirlEntity>) this.getGeoModelProvider();

        IBone steveBone = animatedModel.getAnimationProcessor().getBone(PARTNER_RIG_BONE);
        boolean showPartner = (entity.getStateDef().showPartnerRig || entity.isPartnerForced())
            && steveBone != null;

        // --- Pass 1: girl body, steve hidden, girl texture ---
        girlModel.setRenderingPartnerPass(false);
        setBoneScale(steveBone, 0f);
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        // --- Pass 2: steve only, player texture ---
        if (showPartner) {
            for (String name : GIRL_ROOT_BONES) {
                setBoneScale(animatedModel.getAnimationProcessor().getBone(name), 0f);
            }
            setBoneScale(steveBone, 1f);
            girlModel.setRenderingPartnerPass(true);

            super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

            // Restore so the next frame (and anything else that reads bone
            // scale, e.g. hitboxes) sees the normal girl-visible state.
            girlModel.setRenderingPartnerPass(false);
            for (String name : GIRL_ROOT_BONES) {
                setBoneScale(animatedModel.getAnimationProcessor().getBone(name), 1f);
            }
        }
    }

    private static void setBoneScale(IBone bone, float scale) {
        if (bone == null) return;
        // Use the library's real hide flag (matches the approach used by
        // the reference GeckoLib mods this project's geo files came from)
        // rather than relying purely on 0-scale, which only fakes
        // invisibility via degenerate geometry instead of skipping the
        // bone during the render walk.
        bone.setHidden(scale == 0f);
        bone.setScaleX(scale);
        bone.setScaleY(scale);
        bone.setScaleZ(scale);
    }

    /**
     * Called by GeckoLib just before the model is rendered each frame.
     * We use it to toggle armor bone visibility based on entity.hasAnyArmorEquipped()
     * — real equipment now (HEAD/CHEST/LEGS/FEET slots), not a manual toggle.
     * Scaling to 0 is the standard GeckoLib way to hide bones at runtime
     * without modifying the geo file.
     */
    @Override
    public void renderEarly(GirlEntity entity, MatrixStack stack,
                            float ticks, IRenderTypeBuffer renderTypeBuffer,
                            IVertexBuilder vertexBuilder,
                            int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float alpha) {

        super.renderEarly(entity, stack, ticks, renderTypeBuffer, vertexBuilder,
                          packedLightIn, packedOverlayIn, red, green, blue, alpha);

        this.rtb = renderTypeBuffer; // needed by renderRecursively() below to draw the held weapon

        applyPhysicsSway(entity); // bell/ponytails/boobs/cowtail — exists on both geo files, so before the isDressed() gate below

        // Only applies to the dressed model — nude model has no armor bones
        if (!entity.isDressed()) return;

        boolean showArmor = entity.hasAnyArmorEquipped();

        // getGeoModelProvider() is statically typed to the bare GeoModelProvider<T>
        // base class, which has no animation-related methods at all — the actual
        // runtime object (the GirlModel passed to super() in the constructor above)
        // is an AnimatedGeoModel, which is where getAnimationProcessor() actually lives.
        @SuppressWarnings("unchecked")
        AnimatedGeoModel<GirlEntity> animatedModel = (AnimatedGeoModel<GirlEntity>) this.getGeoModelProvider();

        for (String boneName : ARMOR_BONES) {
            IBone bone = animatedModel.getAnimationProcessor().getBone(boneName);
            if (bone == null) continue;

            if (showArmor) {
                // Restore to full size
                bone.setScaleX(1f);
                bone.setScaleY(1f);
                bone.setScaleZ(1f);
            } else {
                // Scale to 0 = effectively invisible, no texture sampling
                bone.setScaleX(0f);
                bone.setScaleY(0f);
                bone.setScaleZ(0f);
            }
        }

        // NOTE: the "steve" partner-rig bone is no longer toggled here.
        // It's now controlled by render() below, which needs to flip it
        // between two full render passes (one per texture) rather than
        // once per frame — see render() for details.
    }

    // Tweak these if the sword looks offset/rotated wrong in-game — the
    // "weapon" bone's own local orientation in the geo file doesn't
    // necessarily line up with how Minecraft's item renderer expects to
    // draw a third-person held item, so some correction is normal and
    // expected to need adjusting by eye. These starting values are an
    // educated guess (not visually verified), following the same
    // corrective-rotation pattern GeckoLib mods commonly need for a
    // Blockbench hand/arm bone — expect to nudge them.
    private static final float  WEAPON_ROT_X_DEG = -90f;
    private static final float  WEAPON_ROT_Y_DEG = 0f;
    private static final float  WEAPON_ROT_Z_DEG = 0f;
    private static final double WEAPON_OFFSET_X  = 0.0;
    private static final double WEAPON_OFFSET_Y  = 0.0;
    private static final double WEAPON_OFFSET_Z  = 0.0;
    private static final float  WEAPON_SCALE     = 1.0f;

    /**
     * GeckoLib calls this once per bone during the render walk, with the
     * MatrixStack already translated/rotated to that specific bone's
     * current (posed, animated) transform — see the GeckoLib "render item
     * in hand" pattern this follows. We hook the "weapon" bone specifically
     * and draw whatever real vanilla item is in her MAINHAND slot there,
     * via Minecraft's own ItemRenderer — so it's always whatever actual
     * item/sword she has equipped, not a custom model.
     */
    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn,
                                   int packedLightIn, int packedOverlayIn,
                                   float red, float green, float blue, float partialTicks) {

        if (bone.getName().equals(WEAPON_BONE) && currentEntity != null) {
            ItemStack heldItem = currentEntity.getItemBySlot(EquipmentSlotType.MAINHAND);
            if (!heldItem.isEmpty()) {
                stack.pushPose();
                stack.mulPose(Vector3f.XP.rotationDegrees(WEAPON_ROT_X_DEG));
                stack.mulPose(Vector3f.YP.rotationDegrees(WEAPON_ROT_Y_DEG));
                stack.mulPose(Vector3f.ZP.rotationDegrees(WEAPON_ROT_Z_DEG));
                stack.translate(WEAPON_OFFSET_X, WEAPON_OFFSET_Y, WEAPON_OFFSET_Z);
                stack.scale(WEAPON_SCALE, WEAPON_SCALE, WEAPON_SCALE);

                Minecraft.getInstance().getItemRenderer().renderStatic(
                    heldItem, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
                    packedLightIn, packedOverlayIn, stack, this.rtb
                );
                stack.popPose();

                // GeckoLib expects to keep writing into its own vertex
                // builder for this bone afterward — restore it to the
                // current texture's RenderType, same as the reference
                // pattern this follows.
                bufferIn = this.rtb.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(currentEntity)));
            }
        }

        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }
}
