package com.girlmod.client.renderer;

import com.girlmod.client.model.GirlModel;
import com.girlmod.entity.GirlEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.processor.IBone;
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
                            int packedLightIn, int packedOverlayIn) {

        super.renderEarly(entity, stack, ticks, renderTypeBuffer,
                          packedLightIn, packedOverlayIn);

        // Only applies to the dressed model — nude model has no armor bones
        if (!entity.isDressed()) return;

        boolean showArmor = entity.isArmored();

        for (String boneName : ARMOR_BONES) {
            IBone bone = this.getGeoModelProvider()
                             .getBone(boneName)
                             .orElse(null);
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
    }
}
