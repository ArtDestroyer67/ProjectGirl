package com.girlmod.client.model;

import com.girlmod.entity.GirlEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class GirlModel extends AnimatedGeoModel<GirlEntity> {

    private static final ResourceLocation GEO =
        new ResourceLocation("girlmod", "geo/girl/girl.geo.json");
    private static final ResourceLocation TEX =
        new ResourceLocation("girlmod", "textures/entity/girl/girl.png");
    private static final ResourceLocation ANIM =
        new ResourceLocation("girlmod", "animations/girl/girl.animation.json");

    @Override
    public ResourceLocation getModelLocation(GirlEntity entity) { return GEO; }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) { return TEX; }

    @Override
    public ResourceLocation getAnimationFileLocation(GirlEntity entity) { return ANIM; }
}
