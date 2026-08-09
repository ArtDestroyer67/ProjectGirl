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

    private final GirlModel girlModel = new GirlModel();

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
        super(manager, new GirlModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) {
        return girlModel.getTextureLocation(entity);
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

        // Steve (embedded male partner rig) — visible only during penetrative
        // poses (Cowgirl/Missionary/Carry), hidden the rest of the time
        // (idle, walking, combat, hug, etc). Same "scale to 0" technique as
        // the armor bones above; this is a single root bone so hiding it
        // hides its entire subtree in one call.
        boolean showPartner = entity.getStateDef().showPartnerRig;
        IBone steveBone = animatedModel.getAnimationProcessor().getBone(PARTNER_RIG_BONE);
        if (steveBone != null) {
            float scale = showPartner ? 1f : 0f;
            steveBone.setScaleX(scale);
            steveBone.setScaleY(scale);
            steveBone.setScaleZ(scale);
        }
    }
}
