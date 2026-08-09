package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dy extends d6<e3> {
   static final Vec3i w = new Vec3i(255, 255, 255);
   static final float K = -420.69F;
   static final float A = 8.0F;
   static final float L = 3.0F;
   static final Vec3d G = new Vec3d((double)10.0F, (double)-20.0F, (double)-10.0F);
   static final float J = 0.1F;
   static final HashSet<String> D = new HashSet(Arrays.asList("meatTorso", "meatCheekR", "meatCheekL", "meatFootR", "meatFootL", "meatShinR", "meatShinL", "meatLegL", "meatLegR", "nippleR", "nippleL", "preggy", "shoeL", "shoeR", "frontAndInside", "Lside", "Rside", "cheekR", "cheekL", "fuckhole", "head", "nose", "neck", "armL", "lowerArmL", "armR", "lowerArmR", "torso", "LegL", "LegR", "shinL", "shinR"));
   static final HashSet<String> M = new HashSet(Arrays.asList("lashR", "lashL", "closedR", "closedL", "browL", "browR", "closedL", "closedL"));
   static final HashSet<String> C = new HashSet(Arrays.asList("meatLegR", "meatShinR", "meatFootR", "boobR", "boobR1", "boobR2"));
   static Minecraft y;
   float v = 0.0F;
   boolean u = false;
   boolean F = false;
   static float B = 0.0F;
   float z = 0.0F;
   static float H = 0.0F;
   static float t = 0.0F;
   static float I = 0.0F;
   static float E = 0.0F;
   static float N = 0.0F;
   static float x = 0.0F;

   public dy(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
      y = Minecraft.func_71410_x();
   }

   protected ResourceLocation a(e3 var1) throws IOException {
      UUID var3 = var1.ae();
      if (var3 == null) {
         var3 = var1.e();
      }

      ResourceLocation var2;
      label43: {
         try {
            if (!(var1.field_70170_p instanceof gj) && var3 != null) {
               break label43;
            }
         } catch (IOException var6) {
            throw a((Exception)var6);
         }

         var2 = (ResourceLocation)l.get(y.func_110432_I().func_148256_e().getId());

         try {
            if (var2 == null) {
               return this.a((UUID)y.func_110432_I().func_148256_e().getId(), (World)var1.field_70170_p);
            }

            return var2;
         } catch (IOException var5) {
            throw a((Exception)var5);
         }
      }

      var2 = (ResourceLocation)l.get(var3);

      try {
         if (var2 == null) {
            return this.a((UUID)var3, (World)var1.field_70170_p);
         }
      } catch (IOException var4) {
         throw a((Exception)var4);
      }

      return var2;
   }

   public static void a(em var0, float var1) {
      y.func_175598_ae().func_188391_a(var0, (double)0.0F, (double)0.0F, (double)0.0F, -420.69F, var1, false);
   }

   public static void a(float var0) {
      try {
         if (!(y.func_175606_aa() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      EntityPlayer var1 = (EntityPlayer)y.func_175606_aa();
      float var2 = var1.field_70140_Q - var1.field_70141_P;
      float var3 = -(var1.field_70140_Q + var2 * var0);
      float var4 = var1.field_71107_bF + (var1.field_71109_bG - var1.field_71107_bF) * var0;
      float var5 = MathHelper.func_76126_a(var3 * (float)Math.PI) * var4 * 0.5F;
      GlStateManager.func_179137_b(Math.cos((double)y.field_71439_g.field_70177_z * (Math.PI / 180D)) * (double)var5, (double)Math.abs(MathHelper.func_76134_b(var3 * (float)Math.PI) * var4), Math.sin((double)y.field_71439_g.field_70177_z * (Math.PI / 180D)) * (double)var5);
   }

   public void a(GeoModel var1, e3 var2, float var3, float var4, float var5, float var6, float var7) {
      super.a(var1, var2, var3, var4, var5, var6, var2.ar);
   }

   public void func_76979_b(Entity param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3d a(World var0, em var1, UUID var2, double var3, double var5, double var7) {
      try {
         if (var0 == null) {
            return new Vec3d(var3, var5, var7);
         }
      } catch (RuntimeException var15) {
         throw a((Exception)var15);
      }

      try {
         if (var2 == null) {
            return new Vec3d(var3, var5, var7);
         }
      } catch (RuntimeException var12) {
         throw a((Exception)var12);
      }

      try {
         if (var1 == null) {
            return new Vec3d(var3, var5, var7);
         }
      } catch (RuntimeException var14) {
         throw a((Exception)var14);
      }

      EntityPlayer var9 = var0.func_152378_a(var2);

      try {
         if (var9 == null) {
            return new Vec3d(var3, var5, var7);
         }
      } catch (RuntimeException var13) {
         throw a((Exception)var13);
      }

      Vec3d var10 = var9.func_174791_d();
      Vec3d var11 = y.field_71439_g.func_174791_d();
      var1.field_70760_ar = var9.field_70758_at;
      var1.field_70761_aq = var9.field_70759_as;
      var1.b(fp.START_THROWING);
      return var10.func_178788_d(var11);
   }

   public void a(e3 param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   public static boolean a(em param0, fp param1) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3d a(em var0, UUID var1, float var2) {
      try {
         if (var1 == null) {
            return Vec3d.field_186680_a;
         }
      } catch (RuntimeException var7) {
         throw a((Exception)var7);
      }

      EntityPlayer var3 = var0.field_70170_p.func_152378_a(var1);

      try {
         if (var3 == null) {
            return Vec3d.field_186680_a;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      Vec3d var4 = b6.a(new Vec3d(var3.field_70169_q, var3.field_70167_r, var3.field_70166_s), var3.func_174791_d(), (double)var2);
      Vec3d var5 = b6.a(new Vec3d(y.field_71439_g.field_70169_q, y.field_71439_g.field_70167_r, y.field_71439_g.field_70166_s), y.field_71439_g.func_174791_d(), (double)var2);
      return var4.func_178788_d(var5);
   }

   public static Vector4f a(EntityPlayer var0, float var1) {
      EntityPlayerSP var2 = y.field_71439_g;
      float var3 = b6.a(var0.field_70760_ar, var0.field_70761_aq, var1);
      Vec3d var4 = b6.a(new Vec3d(var0.field_70142_S, var0.field_70137_T, var0.field_70136_U), var0.func_174791_d(), (double)var1);
      Vec3d var5 = b6.a(new Vec3d(var2.field_70142_S, var2.field_70137_T, var2.field_70136_U), ((EntityPlayer)var2).func_174791_d(), (double)var1);
      Vec3d var6 = var4.func_178788_d(var5);
      return new Vector4f((float)var6.field_72450_a, (float)var6.field_72448_b, (float)var6.field_72449_c, var3);
   }

   protected Vec3i a(String param1) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3i b(String var0) {
      return eh.values()[Integer.parseInt(var0)].a();
   }

   public static Vec3i c(String var0) {
      return by.values()[Integer.parseInt(var0)].a();
   }

   public static Vec3i d(String var0) {
      return g5.values()[Integer.parseInt(var0)].a();
   }

   protected void a(BufferBuilder var1, String var2, GeoBone var3) {
      try {
         if ((this.j).field_70170_p instanceof gj) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a((Exception)var11);
      }

      String[] var4 = e4.a(this.j);

      try {
         if (var4.length < 8) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a((Exception)var10);
      }

      String var5 = var2;
      byte var6 = -1;

      label131: {
         label130: {
            label129: {
               label128: {
                  label127: {
                     label126: {
                        label125: {
                           label124: {
                              try {
                                 switch (var5.hashCode()) {
                                    case -1383396097:
                                       break label128;
                                    case -1383396096:
                                       break label129;
                                    case 2364452:
                                       break label126;
                                    case 3029410:
                                       break label125;
                                    case 3105718:
                                       if (!var5.equals("earL")) {
                                          break label131;
                                       }
                                       break label130;
                                    case 3105724:
                                       break;
                                    case 3194850:
                                       break label124;
                                    case 93921650:
                                       break label127;
                                    default:
                                       break label131;
                                 }
                              } catch (RuntimeException var9) {
                                 throw a((Exception)var9);
                              }

                              if (var2.equals("earR")) {
                                 var6 = 1;
                              }
                              break label131;
                           }

                           if (var2.equals("hair")) {
                              var6 = 2;
                           }
                           break label131;
                        }

                        if (var2.equals("body")) {
                           var6 = 3;
                        }
                        break label131;
                     }

                     if (var2.equals("LegR")) {
                        var6 = 4;
                     }
                     break label131;
                  }

                  if (var2.equals("boobR")) {
                     var6 = 5;
                  }
                  break label131;
               }

               if (var2.equals("boobR1")) {
                  var6 = 6;
               }
               break label131;
            }

            if (var2.equals("boobR2")) {
               var6 = 7;
            }
            break label131;
         }

         var6 = 0;
      }

      label94: {
         label93: {
            label92: {
               label91: {
                  label90: {
                     label89: {
                        label88: {
                           try {
                              switch (var6) {
                                 case 0:
                                    a(var3, var4[0], var4[1], var4[3]);
                                    break label94;
                                 case 1:
                                    break label93;
                                 case 2:
                                    break label92;
                                 case 3:
                                    break label91;
                                 case 4:
                                    break label90;
                                 case 5:
                                    break label89;
                                 case 6:
                                    break label88;
                                 case 7:
                                    break;
                                 default:
                                    break label94;
                              }
                           } catch (RuntimeException var8) {
                              throw a((Exception)var8);
                           }

                           a(this.u, var3, 5.0F, 3.0F);
                           break label94;
                        }

                        a(this.u, var3, 10.0F, 15.0F);
                        break label94;
                     }

                     a(this.u, var3, 30.0F, 30.0F);
                     break label94;
                  }

                  a(this.u, var3, 25.0F, 25.0F);
                  break label94;
               }

               var3.setPivotY(-0.15F);
               a(this.j, var3);
               break label94;
            }

            a(var3, var4[5]);
            break label94;
         }

         a(var3, var4[0], var4[2], var4[4]);
      }

      try {
         if (var2.contains("crown")) {
            a(this.j, var3, var4[9]);
         }

      } catch (RuntimeException var7) {
         throw a((Exception)var7);
      }
   }

   public static void a(em var0, GeoBone var1, String var2) {
      try {
         if (var0.h()) {
            var1.setHidden(true);
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      if (var0 instanceof e3) {
         int var3 = Integer.parseInt(var2);

         GeoBone var10000;
         boolean var10001;
         label30: {
            try {
               var10000 = var1;
               if (var3 == 0) {
                  var10001 = true;
                  break label30;
               }
            } catch (RuntimeException var5) {
               throw a((Exception)var5);
            }

            var10001 = false;
         }

         var10000.setHidden(var10001);
      } else {
         try {
            if (var0 instanceof eq) {
               var1.setHidden(((ItemStack)var0.func_184212_Q().func_187225_a(e2.X)).func_190926_b());
            }
         } catch (RuntimeException var4) {
            throw a((Exception)var4);
         }
      }

   }

   public static void a(boolean var0, GeoBone var1, float var2, float var3) {
      try {
         if (y.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a((Exception)var4);
      }

      try {
         if (!var0) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a((Exception)var5);
      }

      var1.setRotationX(var1.getRotationX() + gc.c(be.b(x, -var2, var2)));
      var1.setRotationZ(var1.getRotationZ() + gc.c(be.b(N, -var3, var3)));
   }

   public static void a(em param0, GeoBone param1) {
      // $FF: Couldn't be decompiled
   }

   public static void a(GeoBone var0, String var1) {
      int var2 = Integer.parseInt(var1);
      a(var0, var2);
   }

   static HashSet<Integer> b(int var0, String var1) {
      int var2 = Integer.parseInt(var1);
      int var3 = var0 - 1;

      ArrayList var4;
      for(var4 = a(var3); var2 >= var4.size(); var2 -= var4.size()) {
      }

      return (HashSet)var4.get(var2);
   }

   static ArrayList<HashSet<Integer>> a(int var0) {
      ArrayList var1 = new ArrayList();
      a(0, new HashSet(), var0, var1);
      return var1;
   }

   static void a(int var0, HashSet<Integer> var1, int var2, ArrayList<HashSet<Integer>> var3) {
      try {
         if (var0 > var2) {
            var3.add(var1);
            return;
         }
      } catch (RuntimeException var5) {
         throw a((Exception)var5);
      }

      HashSet var4 = new HashSet(var1);
      a(var0 + 1, var1, var2, var3);
      var4.add(var0);
      a(var0 + 1, var4, var2, var3);
   }

   static HashSet<Integer> a(int var0, String var1) {
      HashSet var2 = new HashSet();
      int var3 = Integer.parseInt(var1);
      var3 = (int)(0.01F * (float)var3 * (float)var3);
      int var4 = Math.round((float)var3 / 100.0F * (float)var0);
      Random var5 = new Random((long)var3);

      for(int var6 = 0; var6 < var4; ++var6) {
         int var7 = var5.nextInt(var0);

         try {
            if (!var2.contains(var7)) {
               var2.add(var7);
               continue;
            }
         } catch (RuntimeException var8) {
            throw a((Exception)var8);
         }

         --var6;
      }

      return var2;
   }

   static void a(GeoBone var0, String var1, String var2, String var3) {
      GeoBone var4 = a(var0, Integer.parseInt(var1));
      GeoBone var5 = a(var4, Integer.parseInt(var2));
      List var6 = var5.childBones;
      int var7 = var6.size();
      HashSet var8 = b(var7, var3);
      var5.childBones.forEach((var0x) -> var0x.setHidden(true));
      var8.forEach((var1x) -> b(var5, var1x));
   }

   protected Vec3i a(Vec3i param1) {
      // $FF: Couldn't be decompiled
   }

   protected ItemStack a(@Nullable ItemStack param1) {
      // $FF: Couldn't be decompiled
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

   protected float a() {
      try {
         return ((e3)this.j).y() == fp.CATCH ? 0.5F : 1.0F;
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }
   }

   protected Vec3d a(ItemStack param1) {
      // $FF: Couldn't be decompiled
   }

   public void a(BufferBuilder param1, GeoCube param2, GeoBone param3, float param4, float param5, float param6, float param7, double param8) {
      // $FF: Couldn't be decompiled
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
