package com.trolmastercard.sexmod;

import net.minecraft.client.particle.ParticleDragonBreath;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ez extends ParticleDragonBreath {
   public static final float a = 0.2F;
   public static final float c = 0.5F;
   public static float b = 0.2F;

   public ez(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6, (double)0.0F, (double)0.0F, (double)0.0F);
   }

   public void func_180434_a(BufferBuilder var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.field_70544_f = b;
      float var9 = (float)this.field_94054_b / 16.0F;
      float var10 = var9 + 0.0624375F;
      float var11 = (float)this.field_94055_c / 16.0F;
      float var12 = var11 + 0.0624375F;
      float var13 = 0.1F * this.field_70544_f;
      if (this.field_187119_C != null) {
         var9 = this.field_187119_C.func_94209_e();
         var10 = this.field_187119_C.func_94212_f();
         var11 = this.field_187119_C.func_94206_g();
         var12 = this.field_187119_C.func_94210_h();
      }

      float var14 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * (double)var3 - field_70556_an);
      float var15 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * (double)var3 - field_70554_ao);
      float var16 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * (double)var3 - field_70555_ap);
      int var17 = this.func_189214_a(var3);
      int var18 = var17 >> 16 & '\uffff';
      int var19 = var17 & '\uffff';
      Vec3d[] var20 = new Vec3d[]{new Vec3d((double)(-var4 * var13 - var7 * var13), (double)(-var5 * var13), (double)(-var6 * var13 - var8 * var13)), new Vec3d((double)(-var4 * var13 + var7 * var13), (double)(var5 * var13), (double)(-var6 * var13 + var8 * var13)), new Vec3d((double)(var4 * var13 + var7 * var13), (double)(var5 * var13), (double)(var6 * var13 + var8 * var13)), new Vec3d((double)(var4 * var13 - var7 * var13), (double)(-var5 * var13), (double)(var6 * var13 - var8 * var13))};
      if (this.field_190014_F != 0.0F) {
         float var21 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * var3;
         float var22 = MathHelper.func_76134_b(var21 * 0.5F);
         float var23 = MathHelper.func_76126_a(var21 * 0.5F) * (float)field_190016_K.field_72450_a;
         float var24 = MathHelper.func_76126_a(var21 * 0.5F) * (float)field_190016_K.field_72448_b;
         float var25 = MathHelper.func_76126_a(var21 * 0.5F) * (float)field_190016_K.field_72449_c;
         Vec3d var26 = new Vec3d((double)var23, (double)var24, (double)var25);
         int var27 = 0;

         try {
            while(var27 < 4) {
               var20[var27] = var26.func_186678_a((double)2.0F * var20[var27].func_72430_b(var26)).func_178787_e(var20[var27].func_186678_a((double)(var22 * var22) - var26.func_72430_b(var26))).func_178787_e(var26.func_72431_c(var20[var27]).func_186678_a((double)(2.0F * var22)));
               ++var27;
            }
         } catch (RuntimeException var28) {
            throw a(var28);
         }
      }

      var1.func_181662_b((double)var14 + var20[0].field_72450_a, (double)var15 + var20[0].field_72448_b, (double)var16 + var20[0].field_72449_c).func_187315_a((double)var10, (double)var12).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(var18, var19).func_181675_d();
      var1.func_181662_b((double)var14 + var20[1].field_72450_a, (double)var15 + var20[1].field_72448_b, (double)var16 + var20[1].field_72449_c).func_187315_a((double)var10, (double)var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(var18, var19).func_181675_d();
      var1.func_181662_b((double)var14 + var20[2].field_72450_a, (double)var15 + var20[2].field_72448_b, (double)var16 + var20[2].field_72449_c).func_187315_a((double)var9, (double)var11).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(var18, var19).func_181675_d();
      var1.func_181662_b((double)var14 + var20[3].field_72450_a, (double)var15 + var20[3].field_72448_b, (double)var16 + var20[3].field_72449_c).func_187315_a((double)var9, (double)var12).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(var18, var19).func_181675_d();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
