package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class k extends AnimatedGeoModel<i> {
   public ResourceLocation b(i var1) {
      return new ResourceLocation("sexmod", "geo/kobold/koboldegg.geo.json");
   }

   public ResourceLocation c(i var1) {
      return new ResourceLocation("sexmod", "textures/entity/kobold/koboldegg.png");
   }

   public ResourceLocation a(i var1) {
      return new ResourceLocation("sexmod", "animations/kobold/egg.animation.json");
   }
}
