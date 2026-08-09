package com.trolmastercard.sexmod;

import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dg extends d9 {
   eq B = null;
   boolean C = false;
   boolean E = false;
   boolean D = false;

   public dg(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected Vec3i a(String param1) {
      // $FF: Couldn't be decompiled
   }

   protected Vector4f a(String var1, float var2, float var3, float var4) {
      if (var1.startsWith("crown")) {
         ItemStack var5 = (ItemStack)this.j.func_184212_Q().func_187225_a(e2.X);

         try {
            if (var5.func_190926_b()) {
               return super.a(var1, var2, var3, var4);
            }
         } catch (RuntimeException var13) {
            throw a(var13);
         }

         ItemArmor var6 = (ItemArmor)var5.func_77973_b();
         ItemArmor.ArmorMaterial var7 = var6.func_82812_d();
         float var8 = 0.0F;
         switch (var7) {
            case GOLD:
               var8 = 1.0F;
               break;
            case CHAIN:
            case IRON:
               var8 = 2.0F;
               break;
            case LEATHER:
               var8 = 4.0F;
               int var9 = var6.func_82814_b(var5);
               float var10 = (float)(var9 >> 16 & 255) / 255.0F;
               float var11 = (float)(var9 >> 8 & 255) / 255.0F;
               float var12 = (float)(var9 & 255) / 255.0F;
               var2 = var10;
               var3 = var11;
               var4 = var12;
         }

         return new Vector4f(var2, var3, var4, 72.0F * var8 / 4096.0F);
      } else {
         return super.a(var1, var2, var3, var4);
      }
   }

   protected boolean c(String var1) {
      try {
         if (var1.startsWith("crown")) {
            return true;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return super.c(var1);
   }

   public HashSet<String> a() {
      return new HashSet<String>() {
         {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
            this.add("preggy");
            this.add("LegL");
            this.add("LegR");
            this.add("cheekR");
            this.add("cheekL");
         }
      };
   }

   protected void a(String var1, GeoBone var2) {
      String[] var3 = e4.a(this.j);

      try {
         if (var3.length < 8) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      String var4 = var1;
      byte var5 = -1;

      label127: {
         label126: {
            label125: {
               label124: {
                  label123: {
                     label122: {
                        label121: {
                           label120: {
                              try {
                                 switch (var4.hashCode()) {
                                    case -1383396097:
                                       break label124;
                                    case -1383396096:
                                       break label125;
                                    case 2364452:
                                       break label122;
                                    case 3029410:
                                       break label121;
                                    case 3105718:
                                       if (!var4.equals("earL")) {
                                          break label127;
                                       }
                                       break label126;
                                    case 3105724:
                                       break;
                                    case 3194850:
                                       break label120;
                                    case 93921650:
                                       break label123;
                                    default:
                                       break label127;
                                 }
                              } catch (RuntimeException var8) {
                                 throw a(var8);
                              }

                              if (var1.equals("earR")) {
                                 var5 = 1;
                              }
                              break label127;
                           }

                           if (var1.equals("hair")) {
                              var5 = 2;
                           }
                           break label127;
                        }

                        if (var1.equals("body")) {
                           var5 = 3;
                        }
                        break label127;
                     }

                     if (var1.equals("LegR")) {
                        var5 = 4;
                     }
                     break label127;
                  }

                  if (var1.equals("boobR")) {
                     var5 = 5;
                  }
                  break label127;
               }

               if (var1.equals("boobR1")) {
                  var5 = 6;
               }
               break label127;
            }

            if (var1.equals("boobR2")) {
               var5 = 7;
            }
            break label127;
         }

         var5 = 0;
      }

      label90: {
         label89: {
            label88: {
               label87: {
                  label86: {
                     label85: {
                        label84: {
                           try {
                              switch (var5) {
                                 case 0:
                                    dy.a(var2, var3[0], var3[1], var3[3]);
                                    break label90;
                                 case 1:
                                    break label89;
                                 case 2:
                                    break label88;
                                 case 3:
                                    break label87;
                                 case 4:
                                    break label86;
                                 case 5:
                                    break label85;
                                 case 6:
                                    break label84;
                                 case 7:
                                    break;
                                 default:
                                    break label90;
                              }
                           } catch (RuntimeException var7) {
                              throw a(var7);
                           }

                           dy.a(this.C, var2, 5.0F, 3.0F);
                           break label90;
                        }

                        dy.a(this.C, var2, 10.0F, 15.0F);
                        break label90;
                     }

                     dy.a(this.C, var2, 30.0F, 30.0F);
                     break label90;
                  }

                  dy.a(this.C, var2, 25.0F, 25.0F);
                  break label90;
               }

               var2.setPivotY(-0.15F);
               dy.a(this.j, var2);
               break label90;
            }

            dy.a(var2, var3[5]);
            break label90;
         }

         dy.a(var2, var3[0], var3[2], var3[4]);
      }

      try {
         if (var1.contains("crown")) {
            dy.a(this.j, var2, var3[9]);
         }

      } catch (RuntimeException var6) {
         throw a(var6);
      }
   }

   public void a(em param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   protected void b(Tessellator var1, BufferBuilder var2, em var3, f7 var4, float var5) {
      a(var1, var2, var3, var4, var5);
   }

   @Nullable
   protected f7 e(em param1) {
      // $FF: Couldn't be decompiled
   }

   protected void c() {
      GlStateManager.func_179137_b((double)0.0F, -0.77, -0.05);
      GlStateManager.func_179139_a((double)0.5F, (double)0.5F, (double)0.5F);
   }

   protected void a(boolean param1, ItemStack param2) {
      // $FF: Couldn't be decompiled
   }

   protected void a(boolean var1) {
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
