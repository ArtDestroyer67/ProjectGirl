package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class db extends dm {
   public db(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -1.25F, 0.0F);
      GlStateManager.func_179152_a(0.8F, 0.8F, 0.8F);
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   protected void a(boolean var1) {
      try {
         super.a(var1);
         if (var1) {
            GlStateManager.func_179137_b(0.15, (double)0.0F, (double)0.0F);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
