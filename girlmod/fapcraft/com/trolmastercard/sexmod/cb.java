package com.trolmastercard.sexmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class cb extends cv {
   public static ResourceLocation h = new ResourceLocation("sexmod", "textures/entity/galath/galath.png");
   float g = 0.0F;
   long f = -1L;
   long i = -1L;

   public cb() {
      this.c = this.a();
   }

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/galath/galath.geo.json"), new ResourceLocation("sexmod", "geo/galath/galath.geo.json"), new ResourceLocation("sexmod", "geo/galath/galath_con_mang.geo.json")};
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
         if (((b7)var1).b()) {
            return this.c[2];
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return this.c[(Integer)var1.func_184212_Q().func_187225_a(em.D)];
   }

   public ResourceLocation b() {
      return h;
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/galath/galath.animation.json");
   }

   protected boolean e(em var1) {
      try {
         if (!(var1 instanceof f_)) {
            return true;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      f_ var2 = (f_)var1;

      try {
         if (var2.k()) {
            return true;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         return var2.M() == null;
      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   public void a(em var1, Integer var2, AnimationEvent var3) {
      try {
         this.k(var1);
         super.a(var1, var2, var3);
         this.a(var1);
         this.h(var1);
         this.f(var1);
         this.b(var1);
         this.e(var1);
         this.g(var1);
         this.j(var1);
         this.a();
         this.c(var1);
         this.i(var1);
         this.d(var1);
         if (!(var1 instanceof f_)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      f_ var4 = (f_)var1;

      try {
         var4.aE = this.getAnimationProcessor().getBone("head").getRotationX();
         if (var4.b()) {
            ce.a(var4, this.getAnimationProcessor(), var3.getPartialTick());
         }

      } catch (RuntimeException var5) {
         throw a(var5);
      }
   }

   void i(em param1) {
      // $FF: Couldn't be decompiled
   }

   f7 a(f_ var1, float var2) {
      return b6.a(this.a(var2), f7.d, (double)var1.b(this.a.func_184121_ak()));
   }

   f7 a(float var1) {
      return new f7((float)Math.sin((double)(var1 * 0.3F)) * gc.c(10.0F), (float)Math.sin((double)(var1 * 0.15F)) * gc.c(7.0F), (float)Math.sin((double)var1 * -0.15) * gc.c(7.0F));
   }

   void c(em var1) {
      try {
         if (!(var1 instanceof f_)) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      f_ var2 = (f_)var1;
      AnimationProcessor var3 = this.getAnimationProcessor();
      IBone var4 = var3.getBone("body");
      var2.bw = var4.getRotationY();
      var2.bm = var4.getScaleY();
   }

   void d(em var1) {
      try {
         if (var1.C.getAnimationState() != AnimationState.Transitioning) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      AnimationProcessor var2 = this.getAnimationProcessor();
      fp var3 = var1.y();
      if (var3 == fp.HUG_MANG) {
         IBone var4 = var2.getBone("body2");

         try {
            if (var4 == null) {
               return;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         var4.setPositionX(0.0F);
         var4.setPositionY(-0.53F);
         var4.setPositionZ(-40.05F);
      }

   }

   void k(em var1) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      try {
         if (var1.y() != fp.MASTERBATE) {
            return;
         }
      } catch (RuntimeException var14) {
         throw a(var14);
      }

      Object var2 = var1.z();
      if (var2 == null) {
         var2 = this.a.field_71439_g;
      }

      MolangParser var3 = GeckoLibCache.getInstance().parser;
      Vec3d var4 = ak.b(var1, (EntityPlayer)var2, this.a.func_184121_ak()).func_178787_e(var1.b("head"));
      float var5 = (float)gc.b(Math.atan2(var4.field_72449_c, var4.field_72450_a)) - var1.I();
      float var6 = (float)gc.b(Math.atan2(var4.field_72448_b, Math.sqrt(var4.field_72450_a * var4.field_72450_a + var4.field_72449_c * var4.field_72449_c)));
      double var7 = Math.abs(var4.field_72450_a) + Math.abs(var4.field_72448_b) + Math.abs(var4.field_72449_c);
      double var9 = var7 * (double)7.0F + (double)-20.0F;
      double var11 = var7 * (double)5.0F + (double)-20.0F;
      var3.setValue("pitch", var9 + (double)var6 - (double)80.0F);
      var3.setValue("armpitch", var11 + (double)var6 + (double)-110.0F);
      var3.setValue("armyaw", (double)(var5 + 80.0F));
      var3.setValue("yaw", (double)(var5 + 90.0F));
   }

   void a() {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      IBone var10000;
      boolean var10001;
      label46: {
         try {
            var10000 = this.getAnimationProcessor().getBone("futaCock");
            if (!a_.e) {
               var10001 = true;
               break label46;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }

         var10001 = false;
      }

      label39: {
         try {
            var10000.setHidden(var10001);
            var10000 = this.getAnimationProcessor().getBone("futaBallLL");
            if (!a_.e) {
               var10001 = true;
               break label39;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10001 = false;
      }

      label32: {
         try {
            var10000.setHidden(var10001);
            var10000 = this.getAnimationProcessor().getBone("futaBallLR");
            if (!a_.e) {
               var10001 = true;
               break label32;
            }
         } catch (RuntimeException var2) {
            throw a(var2);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   void j(em var1) {
      try {
         if (!(var1 instanceof ei)) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.getAnimationProcessor().getBone("coin").setHidden(true);
   }

   void g(em var1) {
      IBone var10000;
      boolean var10001;
      label16: {
         try {
            var10000 = this.getAnimationProcessor().getBone("wings");
            if (!((b7)var1).a()) {
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

   void e(em param1) {
      // $FF: Couldn't be decompiled
   }

   void b(em var1) {
      try {
         if (!(var1 instanceof f_)) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      try {
         if (!(Boolean)var1.func_184212_Q().func_187225_a(f_.bP)) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (var1.y() != fp.KNOCK_OUT_FLY) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      IBone var2 = this.getAnimationProcessor().getBone("body");
      Vec3d var3 = new Vec3d(var1.field_70142_S, var1.field_70137_T, var1.field_70136_U);
      Vec3d var4 = var1.func_174791_d().func_178788_d(var3);

      boolean var10000;
      label45: {
         try {
            if (Math.abs(var4.field_72450_a) + Math.abs(var4.field_72449_c) < (double)0.01F) {
               var10000 = true;
               break label45;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         var10000 = false;
      }

      boolean var5 = var10000;

      try {
         if (var5) {
            var2.setRotationX(gc.c(-90.0F));
            var2.setPositionY(0.0F);
            var2.setPositionZ(0.0F);
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      Vec3d var6 = d(var1);
      var2.setRotationX(-((float)var6.field_72450_a));
      var2.setPositionY((float)var6.field_72448_b);
      var2.setPositionZ((float)var6.field_72449_c);
   }

   void h(em var1) {
      try {
         if (!(var1 instanceof f_)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var1.y() != fp.RAPE_CHARGE) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      Vec3d var2 = d(var1);
      IBone var3 = this.getAnimationProcessor().getBone("body");
      IBone var4 = this.getAnimationProcessor().getBone("rotationTool");
      var4.setRotationX((float)var2.field_72450_a);
      var3.setPositionY((float)var2.field_72448_b);
      var3.setPositionZ((float)var2.field_72449_c);
      float var5 = (Float)var1.func_184212_Q().func_187225_a(f_.bO);
      var3.setRotationY(gc.c(var5 * 180.0F));
   }

   void f(em param1) {
      // $FF: Couldn't be decompiled
   }

   void a(em param1) {
      // $FF: Couldn't be decompiled
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
