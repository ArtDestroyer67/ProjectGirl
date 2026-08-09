package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public abstract class cv<T extends em> extends cm<T> implements gs {
   public static final List<String> b = Arrays.asList("braStringMidStartR", "braStringMidMid1R", "braStringMidMid2R", "braStringMidMid3R", "braStringMidEndR", "braStringBackR", "braStringRightEndR", "braStringRightStartR", "braStringRightL", "braStringMidMid1L", "braStringMidMid2L", "braStringMidMid3L", "braStringMidEndL", "braStringBackL", "braStringLeftEndL", "braStringLeftStartL", "braStringMidStartL", "braStringRightR");
   public static final List<String> e = Arrays.asList("boyCam", "girlCam");
   public static boolean d = true;
   protected ResourceLocation[] c = this.a();
   protected Minecraft a = Minecraft.func_71410_x();

   protected cv() {
   }

   protected abstract ResourceLocation[] a();

   public abstract ResourceLocation b();

   public abstract ResourceLocation b(em var1);

   public ResourceLocation c(em var1) {
      return this.b(var1);
   }

   public ResourceLocation a(em var1) {
      try {
         if (var1.field_70170_p instanceof gj) {
            return this.c[0];
         }
      } catch (RuntimeException var2) {
         throw b(var2);
      }

      try {
         if ((Integer)var1.func_184212_Q().func_187225_a(em.D) > this.c.length) {
            System.out.println("Girl doesn't have an outfit Nr." + var1.func_184212_Q().func_187225_a(em.D) + " so im just making her nude lol");
            return this.c[0];
         }
      } catch (RuntimeException var3) {
         throw b(var3);
      }

      return this.c[(Integer)var1.func_184212_Q().func_187225_a(em.D)];
   }

   public ResourceLocation g(em var1) {
      return this.b();
   }

   public void setMolangQueries(IAnimatable var1, double var2) {
      try {
         if (Minecraft.func_71410_x().field_71441_e != null) {
            super.setMolangQueries(var1, var2);
         }

      } catch (RuntimeException var4) {
         throw b(var4);
      }
   }

   public void a(T param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3d d(em var0) {
      return a(new Vec3d(var0.field_70142_S, var0.field_70137_T, var0.field_70136_U), var0.func_174791_d());
   }

   public static Vec3d a(em var0, Vec3d var1) {
      return a(var1, var0.func_174791_d());
   }

   public static Vec3d a(Vec3d var0, Vec3d var1) {
      Vec3d var2 = var1.func_178788_d(var0);
      Vec3d var3 = new Vec3d(Math.abs(var2.field_72450_a), Math.abs(var2.field_72448_b), Math.abs(var2.field_72449_c));
      double var4 = var3.field_72450_a / (var3.field_72450_a + var3.field_72448_b + var3.field_72449_c);
      double var6 = var3.field_72448_b / (var3.field_72450_a + var3.field_72448_b + var3.field_72449_c);
      double var8 = var3.field_72449_c / (var3.field_72450_a + var3.field_72448_b + var3.field_72449_c);

      Vec3d var10000;
      Vec3d var10001;
      byte var10002;
      label51: {
         try {
            var10000 = new Vec3d;
            var10001 = var10000;
            if (var2.field_72450_a > (double)0.0F) {
               var10002 = 1;
               break label51;
            }
         } catch (RuntimeException var17) {
            throw b(var17);
         }

         var10002 = -1;
      }

      var10001.<init>((double)var10002 * var4, (double)(var2.field_72448_b > (double)0.0F ? 1 : -1) * var6, (double)(var2.field_72449_c > (double)0.0F ? 1 : -1) * var8);
      Vec3d var10 = var10000;
      double var11 = var10.field_72448_b / (double)2.0F + (double)0.5F;
      float var13 = (float)b6.b((double)-180.0F, (double)0.0F, var11);
      if (Float.isNaN(var13)) {
         var13 = -90.0F;
      }

      label43: {
         try {
            if (var11 < (double)0.5F) {
               var18 = 0.0F;
               break label43;
            }
         } catch (RuntimeException var16) {
            throw b(var16);
         }

         var18 = (float)b6.b((double)0.0F, (double)16.0F, -var11);
      }

      float var14 = var18;
      if (Float.isNaN(var14)) {
         var14 = 0.0F;
      }

      float var15 = (float)((double)4.0F - Math.sin((Math.PI / 2D) + var11 * (double)2.0F * Math.PI) * (double)4.0F);
      if (Float.isNaN(var15)) {
         var15 = 8.0F;
      }

      return new Vec3d((double)gc.c(var13), (double)var14, (double)var15);
   }

   void a(AnimationProcessor<T> var1, ItemStack var2, ItemStack var3, ItemStack var4, ItemStack var5) {
      cv var10000;
      AnimationProcessor var10001;
      boolean var10002;
      label40: {
         try {
            var10000 = this;
            var10001 = var1;
            if (!var2.func_190926_b()) {
               var10002 = true;
               break label40;
            }
         } catch (RuntimeException var8) {
            throw b(var8);
         }

         var10002 = false;
      }

      label33: {
         try {
            var10000.c(var10001, var10002);
            this.b(var1, var3.func_77973_b() instanceof ItemArmor);
            var10000 = this;
            var10001 = var1;
            if (!var4.func_190926_b()) {
               var10002 = true;
               break label33;
            }
         } catch (RuntimeException var7) {
            throw b(var7);
         }

         var10002 = false;
      }

      label26: {
         try {
            var10000.d(var10001, var10002);
            var10000 = this;
            var10001 = var1;
            if (!var5.func_190926_b()) {
               var10002 = true;
               break label26;
            }
         } catch (RuntimeException var6) {
            throw b(var6);
         }

         var10002 = false;
      }

      var10000.a(var10001, var10002);
   }

   protected void a(AnimationProcessor<T> var1) {
      this.c(var1, false);
      this.b(var1, false);
      this.d(var1, false);
      this.a(var1, false);
   }

   void c(AnimationProcessor var1, boolean var2) {
      cv var10000;
      String[] var10001;
      boolean var10002;
      label16: {
         try {
            this.a(this.c(), var2, var1);
            var10000 = this;
            var10001 = this.g();
            if (!var2) {
               var10002 = true;
               break label16;
            }
         } catch (RuntimeException var3) {
            throw b(var3);
         }

         var10002 = false;
      }

      var10000.a(var10001, var10002, var1);
   }

   void b(AnimationProcessor<T> var1, boolean var2) {
      cv var10000;
      String[] var10001;
      boolean var10002;
      label16: {
         try {
            this.a(this.f(), var2, var1);
            var10000 = this;
            var10001 = this.a();
            if (!var2) {
               var10002 = true;
               break label16;
            }
         } catch (RuntimeException var3) {
            throw b(var3);
         }

         var10002 = false;
      }

      var10000.a(var10001, var10002, var1);
   }

   void d(AnimationProcessor<T> var1, boolean var2) {
      cv var10000;
      String[] var10001;
      boolean var10002;
      label16: {
         try {
            this.a(this.h(), var2, var1);
            var10000 = this;
            var10001 = this.e();
            if (!var2) {
               var10002 = true;
               break label16;
            }
         } catch (RuntimeException var3) {
            throw b(var3);
         }

         var10002 = false;
      }

      var10000.a(var10001, var10002, var1);
   }

   void a(AnimationProcessor<T> var1, boolean var2) {
      cv var10000;
      String[] var10001;
      boolean var10002;
      label16: {
         try {
            this.a(this.b(), var2, var1);
            var10000 = this;
            var10001 = this.d();
            if (!var2) {
               var10002 = true;
               break label16;
            }
         } catch (RuntimeException var3) {
            throw b(var3);
         }

         var10002 = false;
      }

      var10000.a(var10001, var10002, var1);
   }

   void a(String[] var1, boolean var2, AnimationProcessor<T> var3) {
      for(String var7 : var1) {
         this.a(var7, var2, var3);
      }

   }

   void a(String var1, boolean var2, AnimationProcessor<T> var3) {
      try {
         if (var3.getBone(var1) == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw b(var4);
      }

      IBone var10000;
      boolean var10001;
      label22: {
         try {
            var10000 = var3.getBone(var1);
            if (!var2) {
               var10001 = true;
               break label22;
            }
         } catch (RuntimeException var5) {
            throw b(var5);
         }

         var10001 = false;
      }

      var10000.setHidden(var10001);
   }

   protected boolean f(T var1) {
      UUID var2 = var1.ae();

      try {
         if (var2 == null) {
            return true;
         }
      } catch (RuntimeException var6) {
         throw b(var6);
      }

      World var3 = var1.field_70170_p;
      AbstractClientPlayer var4 = (AbstractClientPlayer)var3.func_152378_a(var2);

      try {
         if (var4 == null) {
            return true;
         }
      } catch (RuntimeException var5) {
         throw b(var5);
      }

      return "default".equals(var4.func_175154_l());
   }

   void a(T param1, AnimationProcessor<T> param2) {
      // $FF: Couldn't be decompiled
   }

   protected boolean e(T var1) {
      return true;
   }

   protected void a(T param1, AnimationProcessor<T> param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   public ItemStack a(em var1, String var2) {
      try {
         if (Arrays.asList(this.c()).contains(var2)) {
            return (ItemStack)var1.m.func_187225_a(e2.X);
         }
      } catch (RuntimeException var4) {
         throw b(var4);
      }

      try {
         if (Arrays.asList(this.f()).contains(var2)) {
            return (ItemStack)var1.m.func_187225_a(e2.T);
         }
      } catch (RuntimeException var6) {
         throw b(var6);
      }

      try {
         if (Arrays.asList(this.h()).contains(var2)) {
            return (ItemStack)var1.m.func_187225_a(e2.U);
         }
      } catch (RuntimeException var3) {
         throw b(var3);
      }

      try {
         if (Arrays.asList(this.b()).contains(var2)) {
            return (ItemStack)var1.m.func_187225_a(e2.W);
         }
      } catch (RuntimeException var5) {
         throw b(var5);
      }

      return ItemStack.field_190927_a;
   }

   private static RuntimeException b(RuntimeException var0) {
      return var0;
   }
}
