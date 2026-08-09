package com.trolmastercard.sexmod;

import java.lang.reflect.Field;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.exception.GeoModelException;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class cm<T extends IAnimatable> extends AnimatedGeoModel<T> {
   protected cm() {
      try {
         Field var1 = Class.forName("software.bernie.geckolib3.model.AnimatedGeoModel").getDeclaredField("animationProcessor");
         var1.setAccessible(true);
         var1.set(this, new c1(this));
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public GeoModel getModel(ResourceLocation var1) {
      GeoModel var2 = super.getModel(var1);

      try {
         if (var2 == null) {
            throw new GeoModelException(var1, "Could not find model.");
         }
      } catch (GeoModelException var5) {
         throw a(var5);
      }

      this.getAnimationProcessor().clearModelRendererList();

      for(GeoBone var4 : var2.topLevelBones) {
         this.registerBone(var4);
      }

      return var2;
   }

   private static GeoModelException a(GeoModelException var0) {
      return var0;
   }
}
