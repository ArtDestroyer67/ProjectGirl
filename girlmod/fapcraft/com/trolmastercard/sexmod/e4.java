package com.trolmastercard.sexmod;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class e4 extends em {
   public static final DataParameter<String> N;
   public static final DataParameter<BlockPos> K;
   public static final DataParameter<String> M;
   String P = null;
   String O = null;
   BlockPos L = null;

   protected e4(World var1) {
      super(var1);
   }

   protected void func_70088_a() {
      // $FF: Couldn't be decompiled
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.c();
   }

   void c() {
      // $FF: Couldn't be decompiled
   }

   protected abstract void a();

   protected abstract String a(StringBuilder var1);

   public static void c(StringBuilder var0, int var1) {
      try {
         if (var1 < 10) {
            var0.append(0);
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      var0.append(var1);
      var0.append("-");
   }

   public static void a(StringBuilder var0, int var1) {
      int var2 = com.trolmastercard.sexmod.r.f.nextInt(var1 + 1);

      try {
         if (var2 < 10) {
            var0.append(0);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var0.append(var2);
      var0.append("-");
   }

   public static void b(StringBuilder var0) {
      double var1 = com.trolmastercard.sexmod.r.f.nextDouble();
      double var3 = Math.pow(Math.E, -Math.pow((double)-2.5F + (double)5.0F * var1, (double)2.0F));
      String var5 = String.format("%.2f", var3);
      String[] var6 = var5.split("\\.");
      if (var6.length < 2) {
         var6 = var5.split(",");
      }

      var5 = var6[1];
      var0.append(var5).append("-");
   }

   public static void b(StringBuilder var0, int var1) {
      int var2 = com.trolmastercard.sexmod.r.f.nextInt(var1);

      try {
         if (var2 < 10) {
            var0.append(0);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var0.append(var2);
      var0.append("-");
   }

   public static String[] a(em var0) {
      return ((String)var0.func_184212_Q().func_187225_a(M)).split("-");
   }

   static {
      N = EntityDataManager.func_187226_a(e4.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(119);
      K = EntityDataManager.func_187226_a(e4.class, DataSerializers.field_187200_j).func_187156_b().func_187161_a(120);
      M = EntityDataManager.func_187226_a(e4.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(121);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
