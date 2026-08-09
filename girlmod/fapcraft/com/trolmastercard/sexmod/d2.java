package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d2 extends dm {
   public d2(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void a(boolean var1, ItemStack var2) {
      float var10000;
      label16: {
         try {
            if (var1) {
               var10000 = 290.0F;
               break label16;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10000 = 90.0F;
      }

      GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -0.6F, 0.0F);
      GlStateManager.func_179152_a(0.4F, 0.4F, 0.4F);
   }

   protected void a(boolean var1) {
      try {
         super.a(var1);
         if (var1) {
            GlStateManager.func_179137_b(0.1, (double)0.0F, (double)0.0F);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
