package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dl extends dm {
   public dl(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -1.5F, 0.0F);
   }

   protected void a(boolean var1, ItemStack var2) {
      try {
         super.a(var1, var2);
         switch (var2.func_77973_b().func_77661_b(var2)) {
            case BLOCK:
            case BOW:
               return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      float var10000;
      label31: {
         try {
            if (var1) {
               var10000 = 90.0F;
               break label31;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }

         var10000 = 180.0F;
      }

      try {
         GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
         if (var1) {
            GlStateManager.func_179137_b((double)0.0F, 0.23900000452995301, (double)-0.1F);
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      GlStateManager.func_179137_b((double)0.0F, 0.1, -0.07);
   }

   protected void a(boolean var1) {
      float var10000;
      label24: {
         try {
            if (var1) {
               var10000 = 90.0F;
               break label24;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10000 = 180.0F;
      }

      try {
         GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
         if (var1) {
            GlStateManager.func_179137_b(0.2, -0.2, (double)0.0F);
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
