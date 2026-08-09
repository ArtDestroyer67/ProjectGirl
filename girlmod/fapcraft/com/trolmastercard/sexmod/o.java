package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class o extends AnimatedGeoModel<cy> {
   public ResourceLocation a(cy var1) {
      try {
         if (var1.f) {
            return new ResourceLocation("sexmod", "geo/cross.geo.json");
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return br.k(var1.a());
   }

   public ResourceLocation c(cy var1) {
      try {
         if (var1.f) {
            return new ResourceLocation("sexmod", "textures/cross.png");
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return br.c(var1.a());
   }

   public ResourceLocation b(cy var1) {
      return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
   }

   public void a(cy var1, Integer var2, @Nullable AnimationEvent var3) {
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
