package com.girlmod.client.renderer;

import com.girlmod.client.model.GirlModel;
import com.girlmod.entity.GirlEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
     * When isArmored() is false these bones are scaled to 0 so they vanish
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

        @SuppressWarnings("unchecked")
        AnimatedGeoModel<GirlEntity> animatedModel =
            (AnimatedGeoModel<GirlEntity>) this.getGeoModelProvider();

        IBone steveBone = animatedModel.getAnimationProcessor().getBone(PARTNER_RIG_BONE);
        boolean showPartner = entity.getStateDef().showPartnerRig && steveBone != null;

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
     * We use it to toggle armor bone visibility based on entity.isArmored().
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

        // Only applies to the dressed model — nude model has no armor bones
        if (!entity.isDressed()) return;

        boolean showArmor = entity.isArmored();

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
}
