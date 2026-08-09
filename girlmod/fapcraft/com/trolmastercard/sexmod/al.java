package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class al extends EntityLiving {
   public static final long b = 60000L;
   public static final float g = 3.0F;
   static final float c = 30.0F;
   static final int h = 175;
   static final int i = 10;
   BlockPos f = null;
   int d = 0;
   boolean e = false;
   public int a = -1;

   public al(World var1) {
      super(var1);
   }

   protected void func_70619_bc() {
      super.func_70619_bc();
      this.a();
   }

   void a() {
      // $FF: Couldn't be decompiled
   }

   protected void d() {
      // $FF: Couldn't be decompiled
   }

   public boolean func_70097_a(DamageSource var1, float var2) {
      try {
         if (var1 == DamageSource.field_76380_i) {
            this.field_70170_p.func_72900_e(this);
            return true;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (!(var1.func_76346_g() instanceof EntityPlayer)) {
            return false;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (this.field_70170_p.field_72995_K) {
            this.b();
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      this.e = true;
      be.a(6250, () -> this.field_70170_p.func_72900_e(this));
      return false;
   }

   @SideOnly(Side.CLIENT)
   void b() {
      EntityPlayerSP var1 = Minecraft.func_71410_x().field_71439_g;
      this.a = var1.field_70173_aa;
      ((EntityPlayer)var1).func_184185_a(com.trolmastercard.sexmod.c.MISC_WEOWEO[3], 1.0F, 1.0F);
   }

   double c() {
      return Math.sqrt((double)1800.0F);
   }

   public boolean func_70601_bi() {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
