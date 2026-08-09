package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;

public class gc {
   public static double a(Vec3d var0, Vec3d var1) {
      double var2 = var1.field_72450_a - var0.field_72450_a;
      double var4 = var1.field_72448_b - var0.field_72448_b;
      double var6 = var1.field_72449_c - var0.field_72449_c;
      return Math.atan2(var6, Math.sqrt(var2 * var2 + var4 * var4));
   }

   public static float b(float var0) {
      var0 %= 360.0F;
      if (var0 < 0.0F) {
         var0 += 360.0F;
      }

      return var0;
   }

   public static float a(float var0) {
      float var2;
      float var10000 = var2 = var0 % 360.0F;

      try {
         if (var10000 >= 0.0F) {
            var10000 = var2;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      var10000 = var2 + 360.0F;
      return var10000;
   }

   public static double a(double var0) {
      double var3;
      double var10000 = var3 = var0 % (double)360.0F;

      try {
         if (var10000 >= (double)0.0F) {
            var10000 = var3;
            return var10000;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      var10000 = var3 + (double)360.0F;
      return var10000;
   }

   public static float c(float var0) {
      return (float)((Math.PI * 2D) / ((double)360.0F / (double)var0));
   }

   public static float c(double var0) {
      return (float)((Math.PI * 2D) / ((double)360.0F / var0));
   }

   public static float d(float var0) {
      return (float)((180D / Math.PI) * (double)var0);
   }

   public static double b(double var0) {
      return (180D / Math.PI) * var0;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
