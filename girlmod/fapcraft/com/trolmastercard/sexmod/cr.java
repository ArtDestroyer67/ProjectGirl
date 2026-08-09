package com.trolmastercard.sexmod;

import javax.vecmath.Vector3f;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class cr extends cv {
   fp[] f;

   public cr() {
      this.f = new fp[]{fp.STARTDOGGY, fp.DOGGYCUM, fp.DOGGYSLOW, fp.DOGGYFAST, fp.DOGGYCUM, fp.DOGGYSTART, fp.WAITDOGGY};
   }

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/slime/nude.geo.json"), new ResourceLocation("sexmod", "geo/slime/armored.geo.json"), new ResourceLocation("sexmod", "geo/slime/dressed.geo.json")};
   }

   public ResourceLocation a(em var1) {
      try {
         if (var1.field_70170_p instanceof gj) {
            return this.c[0];
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if ((Integer)var1.func_184212_Q().func_187225_a(em.D) > this.c.length) {
            System.out.println("Girl doesn't have an outfit Nr." + var1.func_184212_Q().func_187225_a(em.D) + " so im just making her nude lol");
            return this.c[0];
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var1 instanceof ec) {
            return this.c[(Integer)var1.func_184212_Q().func_187225_a(em.D)];
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if ((Integer)var1.func_184212_Q().func_187225_a(em.D) == 1) {
            return this.c[2];
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      return this.c[0];
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/slime/slime.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
   }

   public void a(em param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   void a(String[] var1, String var2) {
      AnimationProcessor var3 = this.getAnimationProcessor();
      IBone var4 = var3.getBone(var2);
      IBone[] var5 = new IBone[var1.length];
      int var6 = 0;

      try {
         while(var6 < var5.length) {
            var5[var6] = var3.getBone(var1[var6]);
            ++var6;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      Vector3f var13 = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f var7 = new Vector3f(0.0F, 0.0F, 0.0F);

      for(IBone var11 : var5) {
         var13.add(new Vector3f(var11.getRotationX(), var11.getRotationY(), var11.getRotationZ()));
         var7.add(new Vector3f(var11.getPositionX(), var11.getPositionY(), var11.getPositionZ()));
      }

      var4.setRotationX(var13.x);
      var4.setRotationY(var13.y);
      var4.setRotationZ(var13.z);
      var4.setPositionX(var7.x);
      var4.setPositionY(var7.y);
      var4.setPositionZ(var7.z);
      var4.setPositionZ(var7.z);
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] g() {
      return new String[]{"bigblob"};
   }

   public String[] f() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR", "cloth"};
   }

   public String[] h() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   public String[] e() {
      return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
