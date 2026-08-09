package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class b6 {
   public static Vec3d a(Vec3d var0, Vec3d var1, int var2) {
      try {
         if (var2 == 0) {
            return var1;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      Vec3d var3 = var1.func_178788_d(var0);
      return var0.func_72441_c(var3.field_72450_a / (double)var2, var3.field_72448_b / (double)var2, var3.field_72449_c / (double)var2);
   }

   public static double b(double var0, double var2, double var4) {
      return var0 + (var2 - var0) * var4;
   }

   public static float a(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static float a(float var0, float var1, double var2) {
      float var4;
      for(var4 = var1 - var0; (double)var4 < -Math.PI; var4 = (float)((double)var4 + (Math.PI * 2D))) {
      }

      while((double)var4 >= Math.PI) {
         var4 = (float)((double)var4 - (Math.PI * 2D));
      }

      return (float)((double)var0 + (double)var4 * var2);
   }

   public static float b(float var0, float var1, double var2) {
      double var4 = Math.toRadians((double)var0);
      double var6 = Math.toRadians((double)var1);
      return (float)Math.toDegrees((double)a((float)var4, (float)var6, var2));
   }

   public static Vec3d a(Vec3d var0, Vec3d var1, double var2) {
      Vec3d var4 = var1.func_178788_d(var0);
      return var0.func_178787_e(new Vec3d(var4.field_72450_a * var2, var4.field_72448_b * var2, var4.field_72449_c * var2));
   }

   public static f7 a(f7 var0, f7 var1, double var2) {
      f7 var4 = var1.b(var0);
      return var0.a(var4.a((float)var2));
   }

   public static Vec3i a(Vec3i var0, Vec3i var1, double var2) {
      Vec3d var4 = new Vec3d((double)(var1.func_177958_n() - var0.func_177958_n()), (double)(var1.func_177956_o() - var0.func_177956_o()), (double)(var1.func_177952_p() - var0.func_177952_p()));
      return new Vec3i((double)var0.func_177958_n() + var4.field_72450_a * var2, (double)var0.func_177956_o() + var4.field_72448_b * var2, (double)var0.func_177952_p() + var4.field_72449_c * var2);
   }

   public static gv a(gv var0, gv var1, double var2) {
      gv var4 = new gv(var1.a - var0.a, var1.d - var0.d, var1.c - var0.c, var1.b - var0.b);
      return new gv((int)((double)var0.a + (double)var4.a * var2), (int)((double)var0.d + (double)var4.d * var2), (int)((double)var0.c + (double)var4.c * var2), (int)((double)var0.b + (double)var4.b * var2));
   }

   public static double e(double var0) {
      return (double)1.0F - Math.pow((double)1.0F - var0, (double)4.0F);
   }

   public static double g(double var0) {
      return (double)1.0F - Math.pow((double)1.0F - var0, (double)3.0F);
   }

   public static double c(double var0) {
      double var2 = 1.70158;
      double var4 = var2 + (double)1.0F;
      return (double)1.0F + var4 * Math.pow(var0 - (double)1.0F, (double)3.0F) + var2 * Math.pow(var0 - (double)1.0F, (double)2.0F);
   }

   public static double d(double var0) {
      double var2 = 1.70158;
      double var4 = var2 + (double)1.0F;
      return var4 * var0 * var0 * var0 - var2 * var0 * var0;
   }

   public static double b(double var0) {
      return Math.sin(var0 * Math.PI / (double)2.0F);
   }

   public static double a(double var0) {
      return var0 * var0 * var0;
   }

   public static double h(double var0) {
      return -(Math.cos(Math.PI * var0) - (double)1.0F) / (double)2.0F;
   }

   public static double f(double var0) {
      return (double)1.0F - Math.cos(Math.PI * var0 / (double)2.0F);
   }

   public static double a(double var0, double var2, double var4) {
      double var6 = ((double)1.0F - Math.cos(var4 * Math.PI)) / (double)2.0F;
      return var0 * ((double)1.0F - var6) + var2 * var6;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
