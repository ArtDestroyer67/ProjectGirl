package com.trolmastercard.sexmod;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ef {
   public static void a(BufferBuilder var0, Tessellator var1, Minecraft var2, b var3) {
      Vec3d[] var4 = new Vec3d[]{new Vec3d((double)(-var3.f), (double)(-var3.a), (double)0.0F), new Vec3d((double)(-var3.f), (double)var3.a, (double)0.0F), new Vec3d((double)var3.f, (double)var3.a, (double)0.0F), new Vec3d((double)var3.f, (double)(-var3.a), (double)0.0F)};
      Vec3d var5 = new Vec3d((double)0.0F, (double)0.0F, (double)(-var3.g));
      Vec3d var6 = ck.a(var5.func_72432_b(), (double)var3.e);
      Vec3d[] var7 = new Vec3d[4];
      System.arraycopy(var4, 0, var7, 0, 4);
      ArrayList var8 = new ArrayList();
      float var9 = (float)var2.field_71439_g.field_70173_aa + var2.func_184121_ak();

      for(int var10 = 0; var10 <= var3.c; ++var10) {
         Vec3d[] var11 = new Vec3d[4];
         float var12 = 1.0F - (float)var10 / (float)var3.c;

         for(int var13 = 0; var13 < 4; ++var13) {
            Vec3d var14 = var4[var13];
            var11[var13] = (new Vec3d(var14.field_72450_a * (double)var12, var14.field_72448_b, var14.field_72449_c)).func_178787_e(var6);
         }

         var8.add(var11);
         var5 = ck.a(var5, var3.i.a(var10, var9), var3.b.a(var10, var9), var3.d.a(var10, var9));
         var6 = var6.func_178787_e(var5);
      }

      var0.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      a(var0, var7, (Vec3d[])var8.get(0), var3.h);

      for(int var15 = 0; var15 < var3.c - 1; ++var15) {
         Vec3d[] var16 = (Vec3d[])var8.get(var15);
         Vec3d[] var17 = (Vec3d[])var8.get(var15 + 1);
         a(var0, var16, var17, var3.h);
      }

      var1.func_78381_a();
   }

   static float a(float var0, float var1, float var2, int var3, float var4) {
      return (float)(Math.sin((double)(var0 * var1 + var2 * (float)var3)) * (double)var4);
   }

   static void a(BufferBuilder var0, Vec3d[] var1, Vec3d[] var2, gv var3) {
      var0.func_181662_b(var1[1].field_72450_a, var1[1].field_72448_b, var1[1].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[2].field_72450_a, var1[2].field_72448_b, var1[2].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[2].field_72450_a, var2[2].field_72448_b, var2[2].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[1].field_72450_a, var2[1].field_72448_b, var2[1].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[0].field_72450_a, var1[0].field_72448_b, var1[0].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[1].field_72450_a, var1[1].field_72448_b, var1[1].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[1].field_72450_a, var2[1].field_72448_b, var2[1].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[0].field_72450_a, var2[0].field_72448_b, var2[0].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[2].field_72450_a, var1[2].field_72448_b, var1[2].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[3].field_72450_a, var1[3].field_72448_b, var1[3].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[3].field_72450_a, var2[3].field_72448_b, var2[3].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[2].field_72450_a, var2[2].field_72448_b, var2[2].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[0].field_72450_a, var1[0].field_72448_b, var1[0].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var1[3].field_72450_a, var1[3].field_72448_b, var1[3].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[3].field_72450_a, var2[3].field_72448_b, var2[3].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
      var0.func_181662_b(var2[0].field_72450_a, var2[0].field_72448_b, var2[0].field_72449_c).func_181669_b(var3.a, var3.d, var3.c, var3.b).func_181675_d();
   }

   @FunctionalInterface
   public interface a {
      float a(int var1, float var2);
   }

   public static class b {
      public gv h;
      public float e;
      public int c;
      public float g;
      public a i;
      public a b;
      public a d;
      public float f;
      public float a;

      public b(gv var1, float var2, int var3, float var4, a var5, a var6, a var7, float var8, float var9) {
         this.h = var1;
         this.e = var2;
         this.c = var3;
         this.g = var4;
         this.i = var5;
         this.b = var6;
         this.d = var7;
         this.f = var8;
         this.a = var9;
      }

      public b a() {
         return new b(this.h, this.e, this.c, this.g, this.i, this.b, this.d, this.f, this.a);
      }
   }
}
