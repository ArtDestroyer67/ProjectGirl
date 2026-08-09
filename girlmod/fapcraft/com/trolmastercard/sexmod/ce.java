package com.trolmastercard.sexmod;

import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class ce extends cv {
   public static final float h = 7.0F;
   public static final float k = 0.75F;
   static final float l = gc.c(140.0F);
   static final float m = gc.c(35.0F);
   static final float i = 90.0F;
   static final float g = gc.c(45.0F);
   static final float f = gc.c(-45.0F);
   public static final ResourceLocation j = new ResourceLocation("sexmod", "textures/entity/manglelie/manglelie.png");

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/manglelie/manglelie.geo.json"), new ResourceLocation("sexmod", "geo/manglelie/manglelie.geo.json"), new ResourceLocation("sexmod", "geo/galath/galath_con_mang.geo.json")};
   }

   public ResourceLocation a(em var1) {
      try {
         if (var1.field_70170_p instanceof gj) {
            return this.c[0];
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if (c(var1)) {
            return this.c[2];
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return this.c[(Integer)var1.func_184212_Q().func_187225_a(em.D)];
   }

   public static boolean c(em var0) {
      return fp.a(var0, fp.THREESOME_SLOW, fp.THREESOME_FAST, fp.THREESOME_CUM);
   }

   public ResourceLocation b() {
      return j;
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/manglelie/manglelie.animation.json");
   }

   public void a(em var1, Integer var2, AnimationEvent var3) {
      super.a(var1, var2, var3);
      a(var1, this.getAnimationProcessor(), var3.getPartialTick());
      this.b(var1);
      this.d(var1);
      this.a(var1);
      this.e(var1);
   }

   void e(em var1) {
      try {
         if (this.a.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (c(var1)) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      f_ var2 = f8.a(var1, false);

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (!fp.a(var2.y(), fp.CORRUPT_CUM, fp.CARRY_FAST, fp.CORRUPT_INTRO, fp.CORRUPT_SLOW)) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      AnimationProcessor var3 = this.getAnimationProcessor();
      IBone var4 = var3.getBone("legR");
      var4.setRotationY(var4.getRotationY() + f);
      IBone var5 = var3.getBone("lowerArmR");
      IBone var6 = var3.getBone("lowerArmL");
      var5.setRotationX(var5.getRotationX() + f);
      var6.setRotationX(var6.getRotationX() + f);
   }

   void a(em var1) {
      try {
         if (!(var1 instanceof f8)) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (c(var1)) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      f8 var2 = (f8)var1;
      f_ var3 = var2.a(false);

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      IBone var4 = this.getAnimationProcessor().getBone("body");

      IBone var10000;
      float var10001;
      float var10002;
      label32: {
         try {
            var10000 = var4;
            var10001 = var3.bw;
            if (this.a.func_147113_T()) {
               var10002 = 0.0F;
               break label32;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         var10002 = var4.getRotationY();
      }

      var10000.setRotationY(var10001 + var10002);
      var4.setScaleX(var3.bm);
      var4.setScaleY(var3.bm);
      var4.setScaleZ(var3.bm);
   }

   Vec3d a(@Nonnull Entity var1) {
      return ak.a(var1, this.a.func_184121_ak()).func_72441_c((double)0.0F, (double)var1.func_70047_e(), (double)0.0F);
   }

   void d(em param1) {
      // $FF: Couldn't be decompiled
   }

   a a(@Nonnull f8 param1, @Nonnull f_ param2, IBone param3, IBone param4, AnimationProcessor param5) {
      // $FF: Couldn't be decompiled
   }

   a a(f_ var1, IBone var2, IBone var3, IBone var4, IBone var5) {
      float var6 = var1.aE;
      a var7 = new a();

      try {
         if (var6 > 0.0F) {
            var7.c = new f7(var2.getRotationX() - var6, var2.getRotationY() - var6 * -25.0F / 45.0F, var2.getRotationZ() + var6 * 12.5F / 45.0F);
            var7.g = new f7(var3.getRotationX() - var6, var3.getRotationY() + var6 * 15.0F / 45.0F, var3.getRotationZ());
            var7.b = new f7(var4.getRotationX(), var4.getRotationY(), var4.getRotationZ());
            var7.h = new f7(var5.getRotationX(), var5.getRotationY(), var5.getRotationZ());
            return var7;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      var7.h = new f7(var5.getRotationX() + 2.0F * var6, var5.getRotationY(), var5.getRotationZ());
      var7.b = new f7(var4.getRotationX() + 2.2222223F * var6, var4.getRotationY(), var4.getRotationZ());
      var7.c = new f7(var2.getRotationX() - var6, var2.getRotationY(), var2.getRotationZ() + var6 * 5.0F / 45.0F);
      var7.g = new f7(var3.getRotationX() - var6, var3.getRotationY(), var3.getRotationZ() - var6 * 5.0F / 45.0F);
      return var7;
   }

   void b(em var1) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException var16) {
         throw a(var16);
      }

      try {
         if (this.a.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var24) {
         throw a(var24);
      }

      f8 var2 = (f8)var1;

      try {
         if (!dh.b(var2)) {
            return;
         }
      } catch (RuntimeException var23) {
         throw a(var23);
      }

      f_ var3 = var2.a(false);

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var22) {
         throw a(var22);
      }

      AnimationProcessor var4 = this.getAnimationProcessor();
      float var5 = var3.aE;
      var4.getBone("rotationTool").setRotationX(var5);
      IBone var6 = var4.getBone("head");
      IBone var7 = var4.getBone("upperBody");
      IBone var8 = var4.getBone("boobs");

      label87: {
         try {
            if (var5 > 0.0F) {
               var7.setRotationX(-1.1111112F * var5);
               var6.setRotationX(0.1333F * var5);
               var8.setRotationX(var5 * 22.5F / 45.0F);
               break label87;
            }
         } catch (RuntimeException var21) {
            throw a(var21);
         }

         var7.setRotationX(-1.6666666F * var5);
         var6.setRotationX(var5 * 0.666F);
      }

      float var9 = be.a((double)var2.T, (double)var2.af);
      float var10 = be.a((double)var2.ai, (double)var2.W);
      float var11 = (float)Minecraft.func_175610_ah();
      if (var11 == 0.0F) {
         var11 = 1.0F;
      }

      float var10000;
      float var10001;
      label112: {
         try {
            var10000 = 7.0F;
            if (Math.abs(var9) < 7.0F) {
               var10001 = var9;
               break label112;
            }
         } catch (RuntimeException var20) {
            throw a(var20);
         }

         try {
            if (var9 > 0.0F) {
               var10001 = 7.0F;
               break label112;
            }
         } catch (RuntimeException var19) {
            throw a(var19);
         }

         var10001 = -7.0F;
      }

      float var12 = var10000 * var10001 * (1.0F / var11);

      label113: {
         try {
            var10000 = 7.0F;
            if (Math.abs(var10) < 7.0F) {
               var10001 = var10;
               break label113;
            }
         } catch (RuntimeException var18) {
            throw a(var18);
         }

         try {
            if (var10 > 0.0F) {
               var10001 = 7.0F;
               break label113;
            }
         } catch (RuntimeException var17) {
            throw a(var17);
         }

         var10001 = -7.0F;
      }

      float var13 = var10000 * var10001 * (1.0F / var11);
      float var14 = var2.T + var12;
      float var15 = var2.ai + var13;
      var6.setRotationY(var6.getRotationY() + var14);
      var6.setRotationX(var6.getRotationX() + var15);
      var2.T = var14;
      var2.ai = var15;
   }

   public static void a(em var0, AnimationProcessor var1, float var2) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      boolean var3 = dh.a(var0);
      e(var1, var3);
      f(var1, var3);
      b(var0, var1, var2);
   }

   static void b(em param0, AnimationProcessor param1, float param2) {
      // $FF: Couldn't be decompiled
   }

   static void f(AnimationProcessor var0, boolean var1) {
      IBone var10000;
      boolean var10001;
      label16: {
         try {
            var10000 = var0.getBone("skirt");
            if (!var1) {
               var10001 = true;
               break label16;
            }
         } catch (RuntimeException var2) {
            throw a(var2);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   static void e(AnimationProcessor var0, boolean var1) {
      IBone var10000;
      boolean var10001;
      label28: {
         try {
            var0.getBone("cheekRBelowSkirt").setHidden(var1);
            var0.getBone("cheekLBelowSkirt").setHidden(var1);
            var0.getBone("sideRNoSkirt").setHidden(var1);
            var10000 = var0.getBone("sideRSkirt");
            if (!var1) {
               var10001 = true;
               break label28;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10001 = false;
      }

      label21: {
         try {
            var10000.setHidden(var10001);
            var0.getBone("sideLNoSkirt").setHidden(var1);
            var10000 = var0.getBone("sideLSkirt");
            if (!var1) {
               var10001 = true;
               break label21;
            }
         } catch (RuntimeException var2) {
            throw a(var2);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   private static class a {
      private f7 c;
      private f7 g;
      private f7 h;
      private f7 b;
      private float f;
      private float a;
      private float e;
      private float d;

      private a() {
         this.f = 1.0F;
         this.a = 1.0F;
         this.e = 0.0F;
         this.d = 0.0F;
      }

      static a a(a var0, a var1, float var2) {
         a var3 = new a();
         var3.c = b6.a(var0.c, var1.c, (double)var2);
         var3.g = b6.a(var0.g, var1.g, (double)var2);
         var3.h = b6.a(var0.h, var1.h, (double)var2);
         var3.b = b6.a(var0.b, var1.b, (double)var2);
         var3.f = b6.a(var0.f, var1.f, var2);
         var3.a = b6.a(var0.a, var1.a, var2);
         var3.e = b6.a(var0.e, var1.e, var2);
         var3.d = b6.a(var0.d, var1.d, var2);
         return var3;
      }

      // $FF: synthetic method
      static f7 access$000(a var0) {
         return var0.c;
      }

      // $FF: synthetic method
      static f7 access$100(a var0) {
         return var0.g;
      }

      // $FF: synthetic method
      static f7 access$200(a var0) {
         return var0.b;
      }

      // $FF: synthetic method
      static f7 access$300(a var0) {
         return var0.h;
      }

      // $FF: synthetic method
      static float access$400(a var0) {
         return var0.a;
      }

      // $FF: synthetic method
      static float access$500(a var0) {
         return var0.f;
      }

      // $FF: synthetic method
      static float access$600(a var0) {
         return var0.d;
      }

      // $FF: synthetic method
      static float access$700(a var0) {
         return var0.e;
      }

      // $FF: synthetic method
      static float access$402(a var0, float var1) {
         return var0.a = var1;
      }

      // $FF: synthetic method
      static float access$702(a var0, float var1) {
         return var0.e = var1;
      }

      // $FF: synthetic method
      static float access$502(a var0, float var1) {
         return var0.f = var1;
      }

      // $FF: synthetic method
      static float access$602(a var0, float var1) {
         return var0.d = var1;
      }
   }
}
