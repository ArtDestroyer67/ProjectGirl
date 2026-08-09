package com.trolmastercard.sexmod;

import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class dp extends d_ {
   float r;

   public dp(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   protected ItemStack a(@Nullable ItemStack var1) {
      switch (this.j.y()) {
         case FISHING_IDLE:
         case FISHING_START:
            ItemStack var2 = ((eb)this.j).ao;
            ItemStack var3 = (ItemStack)this.j.func_184212_Q().func_187225_a(eb.az);

            try {
               if (var3.equals(ItemStack.field_190927_a)) {
                  return var2;
               }
            } catch (RuntimeException var5) {
               throw a(var5);
            }

            Map var4 = EnchantmentHelper.func_82781_a(var3);
            EnchantmentHelper.func_82782_a(var4, var2);
            this.j.func_184611_a(EnumHand.MAIN_HAND, var2);
            return var2;
         default:
            return var1;
      }
   }

   boolean a() {
      return (Boolean)this.j.func_184212_Q().func_187225_a(em.G);
   }

   protected void a(BufferBuilder var1, String var2, GeoBone var3) {
      try {
         if (Minecraft.func_71410_x().func_147113_T()) {
            return;
         }
      } catch (RuntimeException var18) {
         throw a(var18);
      }

      String var4 = var2;
      byte var5 = -1;

      label137: {
         label136: {
            label135: {
               label134: {
                  label133: {
                     label132: {
                        label131: {
                           try {
                              switch (var4.hashCode()) {
                                 case -1870254701:
                                    break label132;
                                 case -1870254695:
                                    break label131;
                                 case -1548738978:
                                    break label135;
                                 case -345841663:
                                    break label133;
                                 case -345841657:
                                    break label134;
                                 case 3198432:
                                    if (!var4.equals("head")) {
                                       break label137;
                                    }
                                    break label136;
                                 case 2120576361:
                                    break;
                                 default:
                                    break label137;
                              }
                           } catch (RuntimeException var17) {
                              throw a(var17);
                           }

                           if (var2.equals("backHair")) {
                              var5 = 1;
                           }
                           break label137;
                        }

                        if (var2.equals("sideHairR")) {
                           var5 = 2;
                        }
                        break label137;
                     }

                     if (var2.equals("sideHairL")) {
                        var5 = 3;
                     }
                     break label137;
                  }

                  if (var2.equals("frontHairL")) {
                     var5 = 4;
                  }
                  break label137;
               }

               if (var2.equals("frontHairR")) {
                  var5 = 5;
               }
               break label137;
            }

            if (var2.equals("offhand")) {
               var5 = 6;
            }
            break label137;
         }

         var5 = 0;
      }

      label173: {
         label174: {
            label149: {
               try {
                  switch (var5) {
                     case 0:
                        this.r = var3.getRotationX();
                        return;
                     case 1:
                        break label149;
                     case 2:
                     case 3:
                        break;
                     case 4:
                     case 5:
                        break label174;
                     case 6:
                        break label173;
                     default:
                        return;
                  }
               } catch (RuntimeException var16) {
                  throw a(var16);
               }

               try {
                  if (this.a()) {
                     return;
                  }
               } catch (RuntimeException var15) {
                  throw a(var15);
               }

               double var6 = (double)(this.r / gc.c(45.0F));
               float var8 = (float)b6.b((double)0.0F, (double)1.3F, var6);
               var3.setPositionZ(-var8);
               var3.setPositionY(var8);
               break label174;
            }

            try {
               if (this.a()) {
                  return;
               }
            } catch (RuntimeException var14) {
               throw a(var14);
            }

            double var19 = (double)(this.r / gc.c(45.0F));
            float var20 = (float)b6.b((double)0.0F, (double)0.75F, var19);
            var3.setPositionZ(var20);
            var3.setPositionY(var20);
            var3.setRotationX(-this.r);
            return;
         }

         try {
            if (this.a()) {
               return;
            }
         } catch (RuntimeException var13) {
            throw a(var13);
         }

         var3.setRotationX(-this.r);
         return;
      }

      eb var9 = (eb)this.j;
      ItemStack var10 = (ItemStack)this.j.func_184212_Q().func_187225_a(eb.ag);

      try {
         if (var10.equals(ItemStack.field_190927_a)) {
            return;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      try {
         if (var9.Z != 1.0F) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      GlStateManager.func_179094_E();
      Tessellator.func_178181_a().func_78381_a();
      com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, var3);
      GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(var9.aa, var9.aa, var9.aa);
      Minecraft.func_71410_x().func_175597_ag().func_178099_a(this.j, var10, TransformType.THIRD_PERSON_RIGHT_HAND);
      d_.n.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      this.func_110776_a((ResourceLocation)Objects.requireNonNull(this.getEntityTexture(this.j)));
      GlStateManager.func_179121_F();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
