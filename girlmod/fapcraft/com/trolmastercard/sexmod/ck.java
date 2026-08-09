package com.trolmastercard.sexmod;

import javax.vecmath.Vector3f;
import net.minecraft.util.math.Vec3d;

public class ck {
   public static Vec3d a(Vec3d var0, double var1) {
      return new Vec3d(var0.field_72450_a * var1, var0.field_72448_b * var1, var0.field_72449_c * var1);
   }

   public static double a(Vector3f var0, Vec3d var1) {
      return (double)var0.x * var1.field_72450_a + (double)var0.y * var1.field_72448_b + (double)var0.z * var1.field_72449_c;
   }

   public static double a(Vec3d var0, Vec3d var1) {
      return var0.field_72450_a * var1.field_72450_a + var0.field_72448_b * var1.field_72448_b + var0.field_72449_c * var1.field_72449_c;
   }

   public static Vec3d b(Vec3d var0, Vec3d var1) {
      return new Vec3d(var0.field_72448_b * var1.field_72449_c - var0.field_72449_c * var1.field_72448_b, var0.field_72449_c * var1.field_72450_a - var0.field_72450_a * var1.field_72449_c, var0.field_72450_a * var1.field_72448_b - var0.field_72448_b * var1.field_72450_a);
   }

   public static Vec3d a(double var0, double var2, double var4, float var6) {
      return a(new Vec3d(var0, var2, var4), var6);
   }

   public static Vec3d a(Vec3d var0, float var1) {
      return a(var0, 0.0F, var1);
   }

   public static Vec3d a(Vec3d var0, float var1, float var2) {
      Vec3d var3 = new Vec3d(var0.field_72450_a, var0.field_72448_b * Math.cos((double)var1 * (Math.PI / 180D)) - var0.field_72449_c * Math.sin((double)var1 * (Math.PI / 180D)), var0.field_72448_b * Math.sin((double)var1 * (Math.PI / 180D)) + var0.field_72449_c * Math.cos((double)var1 * (Math.PI / 180D)));
      Vec3d var4 = new Vec3d(-Math.sin((double)(var2 + 90.0F) * (Math.PI / 180D)) * var3.field_72450_a - Math.sin((double)var2 * (Math.PI / 180D)) * var3.field_72449_c, var3.field_72448_b, Math.cos((double)(var2 + 90.0F) * (Math.PI / 180D)) * var3.field_72450_a + Math.cos((double)var2 * (Math.PI / 180D)) * var3.field_72449_c);
      return var4;
   }

   public static Vec3d a(double var0, double var2, double var4, float var6, float var7) {
      return a(new Vec3d(var0, var2, var4), var6, var7);
   }

   public static Vec3d a(Vec3d var0, float var1, float var2, float var3) {
      var1 = gc.c(var1);
      var2 = gc.c(var2);
      var3 = gc.c(var3);
      double var4 = (double)((float)Math.sin((double)var1));
      double var6 = (double)((float)Math.cos((double)var1));
      double var8 = (double)((float)Math.sin((double)var2));
      double var10 = (double)((float)Math.cos((double)var2));
      double var12 = (double)((float)Math.sin((double)var3));
      double var14 = (double)((float)Math.cos((double)var3));
      double var16 = var0.field_72448_b * var6 - var0.field_72449_c * var4;
      double var18 = var0.field_72448_b * var4 + var0.field_72449_c * var6;
      var0 = new Vec3d(var0.field_72450_a, var16, var18);
      double var20 = var0.field_72450_a * var10 + var0.field_72449_c * var8;
      var18 = -var0.field_72450_a * var8 + var0.field_72449_c * var10;
      var0 = new Vec3d(var20, var0.field_72448_b, var18);
      var20 = var0.field_72450_a * var14 - var0.field_72448_b * var12;
      var16 = var0.field_72450_a * var12 + var0.field_72448_b * var14;
      return new Vec3d(var20, var16, var0.field_72449_c);
   }

   public static Vec3d c(Vec3d var0) {
      return new Vec3d(-var0.field_72450_a, var0.field_72448_b, -var0.field_72449_c);
   }

   public static Vec3d a(Vec3d var0) {
      return new Vec3d(-var0.field_72450_a, -var0.field_72448_b, var0.field_72449_c);
   }

   public static Vec3d b(Vec3d var0) {
      return new Vec3d(var0.field_72450_a, -var0.field_72448_b, -var0.field_72449_c);
   }

   static double a(double var0, double var2, double var4) {
      return (var4 - var0) / (var2 - var0);
   }

   public static double a(Vec3d var0, Vec3d var1, Vec3d var2) {
      return a(var0.field_72450_a, var1.field_72450_a, var2.field_72450_a);
   }
}
