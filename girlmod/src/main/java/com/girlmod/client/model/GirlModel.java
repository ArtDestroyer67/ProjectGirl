package com.girlmod.client.model;

import com.girlmod.entity.GirlEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * GeoModel for GirlEntity.
 *
 * getModelLocation() now checks entity.isDressed() and returns one of
 * two geo files — GeckoLib re-queries this every render, so toggling
 * the DRESSED flag (via the GUI "Dress"/"Strip" button) swaps her
 * actual 3D model live, no animation needed for the swap itself.
 *
 * Both geo files were copied from fapcraft's Ellie as a starting point:
 *   girl.geo.json         - nude body
 *   girl_dressed.geo.json - clothed body
 * Replace either (or both) with your own once you have final assets —
 * nothing else needs to change as long as the filenames match.
 */
@OnlyIn(Dist.CLIENT)
public class GirlModel extends AnimatedGeoModel<GirlEntity> {

    private static final ResourceLocation GEO_NUDE =
        new ResourceLocation("girlmod", "geo/girl/girl.geo.json");
    private static final ResourceLocation GEO_DRESSED =
        new ResourceLocation("girlmod", "geo/girl/girl_dressed.geo.json");

    private static final ResourceLocation TEX =
        new ResourceLocation("girlmod", "textures/entity/girl/girl.png");
    private static final ResourceLocation ANIM =
        new ResourceLocation("girlmod", "animations/girl/girl.animation.json");

    @Override
    public ResourceLocation getModelLocation(GirlEntity entity) {
        return entity.isDressed() ? GEO_DRESSED : GEO_NUDE;
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) { return TEX; }

    @Override
    public ResourceLocation getAnimationFileLocation(GirlEntity entity) { return ANIM; }
}
