package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dx extends dm {
   static final HashSet<String> z = new HashSet(Arrays.asList("kneeL", "kneeR", "shinL", "shinR", "armorHelmet", "sockL", "sockR", "braBoobL", "braBoobR", "armorNippleR", "armorNippleL", "slip", "turnable", "static"));

   public dx(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   @Nullable
   protected f7 e(em var1) {
      try {
         if (var1.field_70170_p instanceof gj) {
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if (((b7)((b7)var1)).c()) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return da.y;
   }

   public HashSet<String> a() {
      HashSet var1 = da.E;
      da.E.addAll(gx.a);
      return da.E;
   }

   protected void b(Tessellator var1, BufferBuilder var2, em var3, f7 var4, float var5) {
      a(var1, var2, var3, var4, var5);
   }

   public void a(em param1, double param2, double param4, double param6, float param8, float param9) {
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

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   protected Vector4f a(String var1, float var2, float var3, float var4) {
      try {
         if (!z.contains(var1)) {
            return this.a(var2, var3, var4);
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      try {
         if ("armorHelmet".equals(var1)) {
            return super.a(var1, var2, var3, var4);
         }
      } catch (RuntimeException var15) {
         throw a(var15);
      }

      ItemStack var5 = ItemStack.field_190927_a;
      String var6 = var1;
      int var7 = -1;

      label155: {
         label154: {
            label153: {
               label152: {
                  label151: {
                     label150: {
                        label149: {
                           label148: {
                              label147: {
                                 label146: {
                                    label145: {
                                       label144: {
                                          label143: {
                                             try {
                                                switch (var6.hashCode()) {
                                                   case -1626323751:
                                                      break label144;
                                                   case -1626323745:
                                                      break label143;
                                                   case -892481938:
                                                      break label146;
                                                   case -65677861:
                                                      if (!var6.equals("braBoobL")) {
                                                         break label155;
                                                      }
                                                      break label154;
                                                   case -65677855:
                                                      break;
                                                   case 3533120:
                                                      break label147;
                                                   case 102194025:
                                                      break label152;
                                                   case 102194031:
                                                      break label153;
                                                   case 109407570:
                                                      break label148;
                                                   case 109407576:
                                                      break label149;
                                                   case 109610248:
                                                      break label150;
                                                   case 109610254:
                                                      break label151;
                                                   case 134582103:
                                                      break label145;
                                                   default:
                                                      break label155;
                                                }
                                             } catch (RuntimeException var14) {
                                                throw a(var14);
                                             }

                                             if (var1.equals("braBoobR")) {
                                                var7 = 1;
                                             }
                                             break label155;
                                          }

                                          if (var1.equals("armorNippleR")) {
                                             var7 = 2;
                                          }
                                          break label155;
                                       }

                                       if (var1.equals("armorNippleL")) {
                                          var7 = 3;
                                       }
                                       break label155;
                                    }

                                    if (var1.equals("turnable")) {
                                       var7 = 4;
                                    }
                                    break label155;
                                 }

                                 if (var1.equals("static")) {
                                    var7 = 5;
                                 }
                                 break label155;
                              }

                              if (var1.equals("slip")) {
                                 var7 = 6;
                              }
                              break label155;
                           }

                           if (var1.equals("shinL")) {
                              var7 = 7;
                           }
                           break label155;
                        }

                        if (var1.equals("shinR")) {
                           var7 = 8;
                        }
                        break label155;
                     }

                     if (var1.equals("sockL")) {
                        var7 = 9;
                     }
                     break label155;
                  }

                  if (var1.equals("sockR")) {
                     var7 = 10;
                  }
                  break label155;
               }

               if (var1.equals("kneeL")) {
                  var7 = 11;
               }
               break label155;
            }

            if (var1.equals("kneeR")) {
               var7 = 12;
            }
            break label155;
         }

         var7 = 0;
      }

      switch (var7) {
         case 0:
         case 1:
         case 2:
         case 3:
            var5 = (ItemStack)this.j.func_184212_Q().func_187225_a(e2.T);
            break;
         case 4:
         case 5:
         case 6:
            var5 = (ItemStack)this.j.func_184212_Q().func_187225_a(e2.U);
            break;
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
            var5 = (ItemStack)this.j.func_184212_Q().func_187225_a(e2.W);
      }

      try {
         if (!(var5.func_77973_b() instanceof ItemArmor)) {
            return this.a(var2, var3, var4);
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      ItemArmor var19 = (ItemArmor)var5.func_77973_b();

      try {
         switch (var19.func_82812_d()) {
            case GOLD:
               return new Vector4f(var2, var3, var4, -0.15625F);
            case IRON:
            case CHAIN:
               return new Vector4f(var2, var3, var4, -0.125F);
            case LEATHER:
               break;
            default:
               return new Vector4f(var2, var3, var4, -0.1875F);
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      var7 = var19.func_82814_b(var5);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      var2 *= var8;
      var3 *= var9;
      var4 *= var10;
      return new Vector4f(var2, var3, var4, -0.09375F);
   }

   protected void a(GeoModel var1, BufferBuilder var2, em var3, float var4, float var5, float var6, float var7, float var8) {
      GeoBone var9 = (GeoBone)var1.topLevelBones.get(0);
      GeoBone var10 = null;
      GeoBone var11 = null;

      for(GeoBone var13 : var9.childBones) {
         String var14 = var13.getName();
         byte var15 = -1;

         label47: {
            label46: {
               try {
                  switch (var14.hashCode()) {
                     case 3029410:
                        break;
                     case 109761491:
                        if (!var14.equals("steve")) {
                           break label47;
                        }
                        break label46;
                     default:
                        break label47;
                  }
               } catch (IOException var17) {
                  throw a(var17);
               }

               if (var14.equals("body")) {
                  var15 = 1;
               }
               break label47;
            }

            var15 = 0;
         }

         switch (var15) {
            case 0:
               var11 = var13;
               break;
            case 1:
               var10 = var13;
         }
      }

      MATRIX_STACK.push();
      MATRIX_STACK.translate(var9);
      MATRIX_STACK.moveToPivot(var9);
      MATRIX_STACK.rotate(var9);
      MATRIX_STACK.scale(var9);
      MATRIX_STACK.moveBackFromPivot(var9);
      this.renderRecursively(var2, var10, var4, var5, var6, var7);
      Tessellator.func_178181_a().func_78381_a();
      var2.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      try {
         Minecraft.func_71410_x().field_71446_o.func_110577_a(this.d(this.j));
      } catch (IOException var16) {
         var16.printStackTrace();
      }

      this.renderRecursively(var2, var11, var4, var5, var6, this.j.v());
      Tessellator.func_178181_a().func_78381_a();
      MATRIX_STACK.pop();
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
