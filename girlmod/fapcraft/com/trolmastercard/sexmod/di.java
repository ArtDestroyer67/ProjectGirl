package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class di extends dm {
   float z = 0.0F;

   public di(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -1.0F, 0.0F);
      GlStateManager.func_179152_a(0.65F, 0.65F, 0.65F);
   }

   protected ItemStack a(@Nullable ItemStack var1) {
      switch (this.j.y()) {
         case FISHING_IDLE:
         case FISHING_START:
            ItemStack var2 = ((eb)this.j).ao;
            this.j.func_184611_a(EnumHand.MAIN_HAND, var2);
            return var2;
         default:
            return var1;
      }
   }

   boolean b() {
      return (Boolean)this.j.func_184212_Q().func_187225_a(em.G);
   }

   protected void a(String var1, GeoBone var2) {
      try {
         if (Minecraft.func_71410_x().func_147113_T()) {
            return;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      String var3 = var1;
      byte var4 = -1;

      label83: {
         label82: {
            label81: {
               label80: {
                  try {
                     switch (var3.hashCode()) {
                        case -345841663:
                           break label80;
                        case -345841657:
                           break label81;
                        case 3198432:
                           if (!var3.equals("head")) {
                              break label83;
                           }
                           break label82;
                        case 2120576361:
                           break;
                        default:
                           break label83;
                     }
                  } catch (RuntimeException var11) {
                     throw a(var11);
                  }

                  if (var1.equals("backHair")) {
                     var4 = 1;
                  }
                  break label83;
               }

               if (var1.equals("frontHairL")) {
                  var4 = 2;
               }
               break label83;
            }

            if (var1.equals("frontHairR")) {
               var4 = 3;
            }
            break label83;
         }

         var4 = 0;
      }

      label108: {
         try {
            switch (var4) {
               case 0:
                  this.z = var2.getRotationX();
                  return;
               case 1:
                  break;
               case 2:
               case 3:
                  break label108;
               default:
                  return;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         try {
            if (this.b()) {
               return;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         if (this.z > 0.0F) {
            double var5 = (double)(this.z / gc.c(45.0F));
            float var7 = (float)b6.b((double)0.0F, (double)0.75F, var5);
            var2.setPositionZ(var7);
            var2.setPositionY(var7);
            var2.setRotationX(-this.z);
         }

         return;
      }

      try {
         if (this.b()) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      var2.setRotationX(-this.z);
   }

   protected void a(boolean var1, ItemStack var2) {
      try {
         super.a(var1, var2);
         switch (var2.func_77973_b().func_77661_b(var2)) {
            case BLOCK:
            case BOW:
               return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      float var10000;
      label21: {
         try {
            if (var1) {
               var10000 = 60.0F;
               break label21;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10000 = 150.0F;
      }

      GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179137_b((double)0.0F, 0.08, -0.05);
   }

   protected void a(boolean var1) {
      float var10000;
      label24: {
         try {
            if (var1) {
               var10000 = 60.0F;
               break label24;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10000 = 150.0F;
      }

      try {
         GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
         if (var1) {
            GlStateManager.func_179137_b(0.12, (double)0.0F, (double)0.0F);
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
