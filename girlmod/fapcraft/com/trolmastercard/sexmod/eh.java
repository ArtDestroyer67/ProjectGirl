package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum eh {
   RED(255, 0, 0),
   VIOLET(132, 30, 156),
   YELLOW(243, 247, 0),
   BROWN(105, 60, 9),
   TURKEY(0, 206, 217),
   BLUE(0, 0, 255);

   private final Vec3i b;

   private eh(int var3, int var4, int var5) {
      this.b = new Vec3i(var3, var4, var5);
   }

   public Vec3i a() {
      return this.b;
   }

   public static eh a(Vec3i var0) {
      for(eh var4 : values()) {
         try {
            if (var0.equals(var4.a())) {
               return var4;
            }
         } catch (RuntimeException var5) {
            throw a(var5);
         }
      }

      return RED;
   }

   public static int a(eh var0) {
      int var1 = 0;

      for(eh var5 : values()) {
         try {
            if (var0 == var5) {
               return var1;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         ++var1;
      }

      return var1;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
