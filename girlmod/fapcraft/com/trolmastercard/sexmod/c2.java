package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class c2 extends cv {
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/allie/allie.geo.json"), new ResourceLocation("sexmod", "geo/allie/armored.geo.json"), new ResourceLocation("sexmod", "geo/allie/allie.geo.json")};
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
         if (var1 instanceof e5) {
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
      return new ResourceLocation("sexmod", "textures/entity/allie/allie.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/allie/allie.animation.json");
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] f() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   public String[] a() {
      return new String[]{"boobsFlesh", "clothes", "clothesR", "clothesL"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
