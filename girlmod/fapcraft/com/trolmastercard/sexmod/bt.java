package com.trolmastercard.sexmod;

import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class bt extends EntityAIAvoidEntity<EntityPlayer> {
   final f8 a;
   final float b;

   public bt(f8 var1, float var2, double var3, double var5) {
      super(var1, EntityPlayer.class, var2, var3, var5);
      this.a = var1;
      this.b = var2;
   }

   boolean a() {
      try {
         if (this.a.v() != null) {
            return true;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      BlockPos var1 = this.a.func_180425_c();
      BlockPos var2 = new BlockPos((double)this.b, (double)this.b, (double)this.b);

      for(f_ var5 : this.a.field_70170_p.func_72872_a(f_.class, new AxisAlignedBB(var1.func_177971_a(var2), var1.func_177973_b(var2)))) {
         try {
            if (var5.field_70170_p.field_72995_K) {
               continue;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         try {
            if (var5.field_70128_L) {
               continue;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         try {
            if (var5.k()) {
               return true;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }
      }

      return false;
   }

   public boolean func_75250_a() {
      try {
         if (this.a()) {
            return false;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      return super.func_75250_a();
   }

   public boolean func_75253_b() {
      try {
         if (this.a()) {
            return false;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      return super.func_75253_b();
   }

   public void func_75249_e() {
      this.a.func_184212_Q().func_187227_b(f8.ar, true);
      super.func_75249_e();
   }

   public void func_75251_c() {
      this.a.func_184212_Q().func_187227_b(f8.ar, false);
      super.func_75251_c();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
