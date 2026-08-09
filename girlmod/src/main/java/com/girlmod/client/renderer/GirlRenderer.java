package com.girlmod.client.renderer;

import com.girlmod.client.model.GirlModel;
import com.girlmod.entity.GirlEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GirlRenderer extends GeoEntityRenderer<GirlEntity> {

    public GirlRenderer(EntityRendererManager manager) {
        super(manager, new GirlModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(GirlEntity entity) {
        return this.modelProvider.getTextureLocation(entity);
    }
}
