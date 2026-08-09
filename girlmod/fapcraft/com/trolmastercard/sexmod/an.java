package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class an {
   public static final float a = 9.81F;
   public static final float g = 0.05F;
   public static final float b = 0.05F;
   public static final float c = 0.03F;
   World h;
   Vec3d d;
   Vec3d f;
   Vec3d e;

   public an(World var1, Vec3d var2, Vec3d var3) {
      this.h = var1;
      this.f = var2;
      this.d = var2;
      this.e = var3;
   }

   public void a() {
      try {
         if (Vec3d.field_186680_a.equals(this.e)) {
            this.d = this.f;
            return;
         }
      } catch (RuntimeException var27) {
         throw a(var27);
      }

      this.e = new Vec3d(this.e.field_72450_a * (double)0.95F, (this.e.field_72448_b - (double)0.49050003F) * (double)0.95F, this.e.field_72449_c * (double)0.95F);
      this.d = this.f;
      this.f = new Vec3d(this.f.field_72450_a + this.e.field_72450_a * (double)0.05F, this.f.field_72448_b + this.e.field_72448_b * (double)0.05F, this.f.field_72449_c + this.e.field_72449_c * (double)0.05F);
      BlockPos var1 = new BlockPos(this.d);
      BlockPos var2 = null;

      for(BlockPos var4 : a(new BlockPos(this.d), new BlockPos(this.f))) {
         if (this.h.func_180495_p(var4).func_177230_c() != Blocks.field_150350_a) {
            var2 = var4;
            break;
         }

         var1 = var4;
      }

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var23) {
         throw a(var23);
      }

      int var28 = var2.func_177958_n();
      int var29 = var1.func_177958_n();
      if (var28 - var29 != 0) {
         double var30 = (double)Math.max(var28, var29);
         double var32 = (this.d.field_72448_b - this.f.field_72448_b) / (this.d.field_72450_a - this.f.field_72450_a);
         double var34 = this.f.field_72448_b - var32 * this.f.field_72450_a;
         double var36 = var32 * var30 + var34;
         double var38 = (this.d.field_72449_c - this.f.field_72449_c) / (this.d.field_72450_a - this.f.field_72450_a);
         double var40 = this.f.field_72449_c - var38 * this.f.field_72450_a;
         double var42 = var38 * var30 + var40;

         an var45;
         Vec3d var47;
         Vec3d var49;
         double var51;
         float var53;
         byte var55;
         label50: {
            try {
               var45 = this;
               var47 = new Vec3d;
               var49 = var47;
               var51 = var30;
               var53 = 0.03F;
               if (var28 > var29) {
                  var55 = -1;
                  break label50;
               }
            } catch (RuntimeException var24) {
               throw a(var24);
            }

            var55 = 1;
         }

         var49.<init>(var51 + (double)(var53 * (float)var55), var36, var42);
         var45.f = var47;
         this.e = new Vec3d((double)0.0F, (double)0.0F, (double)0.0F);
      } else {
         int var5 = var2.func_177956_o();
         int var6 = var1.func_177956_o();
         if (var5 - var6 != 0) {
            double var31 = (double)Math.max(var5, var6);
            double var33 = (this.d.field_72450_a - this.f.field_72450_a) / (this.d.field_72448_b - this.f.field_72448_b);
            double var35 = this.f.field_72450_a - var33 * this.f.field_72448_b;
            double var37 = var33 * var31 + var35;
            double var39 = (this.d.field_72449_c - this.f.field_72449_c) / (this.d.field_72448_b - this.f.field_72448_b);
            double var41 = this.f.field_72449_c - var39 * this.f.field_72448_b;
            double var43 = var39 * var31 + var41;

            an var44;
            Vec3d var46;
            Vec3d var48;
            double var50;
            double var52;
            float var54;
            byte var56;
            label60: {
               try {
                  var44 = this;
                  var46 = new Vec3d;
                  var48 = var46;
                  var50 = var37;
                  var52 = var31;
                  var54 = 0.03F;
                  if (var5 > var6) {
                     var56 = -1;
                     break label60;
                  }
               } catch (RuntimeException var25) {
                  throw a(var25);
               }

               var56 = 1;
            }

            var48.<init>(var50, var52 + (double)(var54 * (float)var56), var43);
            var44.f = var46;
            this.e = new Vec3d((double)0.0F, (double)0.0F, (double)0.0F);
         } else {
            int var7 = var2.func_177952_p();
            int var8 = var1.func_177952_p();
            if (var7 - var8 != 0) {
               double var9 = (double)Math.max(var7, var8);
               double var11 = (this.d.field_72448_b - this.f.field_72448_b) / (this.d.field_72449_c - this.f.field_72449_c);
               double var13 = this.f.field_72448_b - var11 * this.f.field_72449_c;
               double var15 = var11 * var9 + var13;
               double var17 = (this.d.field_72450_a - this.f.field_72450_a) / (this.d.field_72449_c - this.f.field_72449_c);
               double var19 = this.f.field_72450_a - var17 * this.f.field_72449_c;
               double var21 = var17 * var9 + var19;

               an var10000;
               Vec3d var10001;
               Vec3d var10002;
               double var10003;
               double var10004;
               double var10005;
               float var10006;
               byte var10007;
               label70: {
                  try {
                     var10000 = this;
                     var10001 = new Vec3d;
                     var10002 = var10001;
                     var10003 = var21;
                     var10004 = var15;
                     var10005 = var9;
                     var10006 = 0.03F;
                     if (var7 > var8) {
                        var10007 = -1;
                        break label70;
                     }
                  } catch (RuntimeException var26) {
                     throw a(var26);
                  }

                  var10007 = 1;
               }

               var10002.<init>(var10003, var10004, var10005 + (double)(var10006 * (float)var10007));
               var10000.f = var10001;
               this.e = new Vec3d((double)0.0F, (double)0.0F, (double)0.0F);
            }
         }
      }
   }

   static List<BlockPos> a(BlockPos var0, BlockPos var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var0);
      int var3 = var0.func_177958_n();
      int var4 = var0.func_177956_o();
      int var5 = var0.func_177952_p();
      int var6 = var1.func_177958_n();
      int var7 = var1.func_177956_o();
      int var8 = var1.func_177952_p();
      int var9 = Math.abs(var6 - var3);
      int var10 = Math.abs(var7 - var4);
      int var11 = Math.abs(var8 - var5);

      byte var10000;
      label62: {
         try {
            if (var3 < var6) {
               var10000 = 1;
               break label62;
            }
         } catch (RuntimeException var25) {
            throw a(var25);
         }

         var10000 = -1;
      }

      byte var12 = var10000;

      label54: {
         try {
            if (var4 < var7) {
               var10000 = 1;
               break label54;
            }
         } catch (RuntimeException var24) {
            throw a(var24);
         }

         var10000 = -1;
      }

      byte var13 = var10000;

      label46: {
         try {
            if (var5 < var8) {
               var10000 = 1;
               break label46;
            }
         } catch (RuntimeException var23) {
            throw a(var23);
         }

         var10000 = -1;
      }

      byte var14 = var10000;
      int var15 = Math.max(var9, Math.max(var10, var11));
      int var16 = var3;
      int var17 = var4;
      int var18 = var5;
      int var19 = var15 / 2;
      int var20 = var15 / 2;
      int var21 = var15 / 2;

      for(int var22 = 0; var22 < var15; ++var22) {
         var2.add(new BlockPos(var16, var17, var18));
         var19 -= var9;
         var20 -= var10;
         var21 -= var11;
         if (var19 < 0) {
            var16 += var12;
            var19 += var15;
         } else if (var20 < 0) {
            var17 += var13;
            var20 += var15;
         } else if (var21 < 0) {
            var18 += var14;
            var21 += var15;
         }
      }

      var2.add(var1);
      return var2;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
