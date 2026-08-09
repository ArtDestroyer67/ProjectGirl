package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class c9 extends cv {
   static final float g = 1.2F;
   static final float f = 1.0F;

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/kobold/kobold.geo.json"), new ResourceLocation("sexmod", "geo/kobold/armored.geo.json")};
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/kobold/kobold.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/kobold/kobold.animation.json");
   }

   public void a(em param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   void b(em var1, AnimationProcessor var2) {
      try {
         if (var1.C.getAnimationState() != AnimationState.Transitioning) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      float var3 = (Float)var1.func_184212_Q().func_187225_a(ff.aE);
      var3 = 0.25F - var3;
      switch (var1.y()) {
         case SUCKBLOWJOB_BLINK:
         case THRUSTBLOWJOB:
         case CUMBLOWJOB:
            IBone var8 = var2.getBone("body");
            var8.setPositionZ(11.43F + var3 * -7.0F);
            return;
         case KOBOLD_ANAL_SLOW:
         case ANAL_FAST:
         case ANAL_CUM:
         case ANAL_START:
            IBone var7 = var2.getBone("body");
            var7.setPositionX(1.78F + var3 * -1.5F);
            var7.setPositionY(13.07F + var3 * -11.0F);
            var7.setPositionZ(2.05F + var3 * -8.0F);
            return;
         case MATING_PRESS_CUM:
         case MATING_PRESS_HARD:
         case MATING_PRESS_SOFT:
         case MATING_PRESS_START:
            IBone var4 = var2.getBone("body");
            var4.setPositionX(0.0F);
            var4.setPositionY(2.85F);
            var4.setPositionZ(-7.0F + var3 * 4.7F);
            return;
         default:
      }
   }

   void a(em var1, AnimationProcessor var2, String var3) {
      int var4 = Integer.parseInt(var3);
      IBone var5 = var2.getBone("backpack");
      IBone var6 = var2.getBone("tailpack");

      label38: {
         label37: {
            label36: {
               try {
                  switch (var4) {
                     case 0:
                        var5.setHidden(false);
                        var6.setHidden(true);
                        break label38;
                     case 1:
                        break label37;
                     case 2:
                        break label36;
                     case 3:
                        break;
                     default:
                        break label38;
                  }
               } catch (RuntimeException var8) {
                  throw a(var8);
               }

               var5.setHidden(true);
               var6.setHidden(true);
               break label38;
            }

            var5.setHidden(true);
            var6.setHidden(false);
            break label38;
         }

         var5.setHidden(false);
         var6.setHidden(false);
      }

      try {
         if (var1.y() == fp.PAYMENT) {
            var5.setHidden(false);
         }

      } catch (RuntimeException var7) {
         throw a(var7);
      }
   }

   void d(AnimationProcessor var1, String var2) {
      int var3 = Integer.parseInt(var2);
      IBone var4 = var1.getBone("frecklesHR1");
      IBone var5 = var1.getBone("frecklesHR2");
      IBone var6 = var1.getBone("frecklesHL1");
      IBone var7 = var1.getBone("frecklesHL2");

      IBone var10000;
      boolean var10001;
      label53: {
         try {
            var10000 = var6;
            if (var3 != 1) {
               var10001 = true;
               break label53;
            }
         } catch (RuntimeException var11) {
            throw a(var11);
         }

         var10001 = false;
      }

      label46: {
         try {
            var10000.setHidden(var10001);
            var10000 = var4;
            if (var3 != 1) {
               var10001 = true;
               break label46;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         var10001 = false;
      }

      label39: {
         try {
            var10000.setHidden(var10001);
            var10000 = var7;
            if (var3 != 2) {
               var10001 = true;
               break label39;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         var10001 = false;
      }

      label32: {
         try {
            var10000.setHidden(var10001);
            var10000 = var5;
            if (var3 != 2) {
               var10001 = true;
               break label32;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   void a(AnimationProcessor var1, String var2) {
      int var3 = Integer.parseInt(var2);
      IBone var4 = var1.getBone("frecklesAR1");
      IBone var5 = var1.getBone("frecklesAR2");
      IBone var6 = var1.getBone("frecklesAL1");
      IBone var7 = var1.getBone("frecklesAL2");

      IBone var10000;
      boolean var10001;
      label53: {
         try {
            var10000 = var6;
            if (var3 != 1) {
               var10001 = true;
               break label53;
            }
         } catch (RuntimeException var11) {
            throw a(var11);
         }

         var10001 = false;
      }

      label46: {
         try {
            var10000.setHidden(var10001);
            var10000 = var4;
            if (var3 != 1) {
               var10001 = true;
               break label46;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         var10001 = false;
      }

      label39: {
         try {
            var10000.setHidden(var10001);
            var10000 = var7;
            if (var3 != 2) {
               var10001 = true;
               break label39;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         var10001 = false;
      }

      label32: {
         try {
            var10000.setHidden(var10001);
            var10000 = var5;
            if (var3 != 2) {
               var10001 = true;
               break label32;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   void a(AnimationProcessor var1, String var2, float var3, float var4) {
      try {
         if (Minecraft.func_71410_x().func_147113_T()) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      float var5 = Float.parseFloat(var2);
      var5 /= 100.0F;
      var5 = var3 + (var4 - var3) * var5 - 1.0F;
      IBone var6 = var1.getBone("eyeL");
      var6.setPositionX(var6.getPositionX() + var5);
      IBone var7 = var1.getBone("eyeR");
      var7.setPositionX(var7.getPositionX() - var5);
   }

   void a(AnimationProcessor var1, String var2, float var3, float var4, String... var5) {
      float var6 = Float.parseFloat(var2);
      var6 /= 100.0F;
      var6 = var3 + (var4 - var3) * var6;

      for(String var10 : var5) {
         IBone var11 = var1.getBone(var10);

         try {
            if (var11 == null) {
               continue;
            }
         } catch (RuntimeException var12) {
            throw a(var12);
         }

         var11.setScaleX(var6);
         var11.setScaleY(var6);
         var11.setScaleZ(var6);
      }

   }

   void e(AnimationProcessor var1, String var2) {
      List var3 = this.c(var1, "hornDL");
      List var4 = this.c(var1, "hornDR");
      this.a(var3);
      this.a(var4);
      int var5 = new Integer(var2);
      var1.getBone("hornDL" + var5).setHidden(false);
      var1.getBone("hornDR" + var5).setHidden(false);
   }

   void b(AnimationProcessor var1, String var2) {
      List var3 = this.c(var1, "hornUL");
      List var4 = this.c(var1, "hornUR");
      this.a(var3);
      this.a(var4);
      int var5 = new Integer(var2);
      var1.getBone("hornUL" + var5).setHidden(false);
      var1.getBone("hornUR" + var5).setHidden(false);
   }

   List<IBone> c(AnimationProcessor var1, String var2) {
      ArrayList var3 = new ArrayList();
      int var4 = 0;

      while(true) {
         IBone var5 = var1.getBone(var2 + var4);

         try {
            if (var5 == null) {
               return var3;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         var3.add(var5);
         ++var4;
      }
   }

   void a(List<IBone> var1) {
      for(IBone var3 : var1) {
         var3.setHidden(true);
      }

   }

   protected void a(em param1, AnimationProcessor param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] f() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
   }

   public String[] h() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip", "armorKneeR", "armorKneeL"};
   }

   public String[] e() {
      return new String[]{"fleshL", "fleshR", "vagina", "fuckhole", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   public String[] d() {
      return new String[]{"toesR", "toesL"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
