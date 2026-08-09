package com.trolmastercard.sexmod;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class c4 extends EntityLiving {
   public static final float d = 0.4F;
   public static final float e = 0.3F;
   static final int b = 200;
   static final int k = 100;
   static final float a = 0.5F;
   static final float l = 0.15F;
   public static final float j = 0.75F;
   public double g;
   Vec3d h;
   boolean c;
   boolean i;
   f_ f;

   public c4(World var1) {
      super(var1);
      this.g = (double)1.0F;
      this.h = Vec3d.field_186680_a;
      this.c = false;
      this.i = true;
      this.func_70105_a(0.5F, 0.5F);
   }

   public c4(World var1, f_ var2) {
      super(var1);
      this.g = (double)1.0F;
      this.h = Vec3d.field_186680_a;
      this.c = false;
      this.i = true;
      this.func_70105_a(0.5F, 0.5F);
      this.f = var2;
   }

   public c4(World var1, f_ var2, Vec3d var3) {
      this(var1);
      this.h = var3;
      this.f = var2;
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_82167_n(Entity var1) {
   }

   public void func_70071_h_() {
      try {
         if (this.field_70128_L) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         this.field_70145_X = true;
         this.func_189654_d(true);
         this.field_70159_w = this.h.field_72450_a;
         this.field_70181_x = this.h.field_72448_b;
         this.field_70179_y = this.h.field_72449_c;
         super.func_70071_h_();
         if (this.field_70170_p.field_72995_K) {
            this.a();
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      try {
         this.c();
         if (this.field_70170_p.func_175623_d(this.func_180425_c())) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.b();
      this.field_70170_p.func_72900_e(this);
   }

   void c() {
      try {
         if (this.field_70170_p.field_72995_K) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (!this.c) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      Vec3d var1 = this.func_174791_d();
      Vec3d var2 = var1.func_178786_a((double)0.75F, (double)0.75F, (double)0.75F);
      Vec3d var3 = var1.func_72441_c((double)0.75F, (double)0.75F, (double)0.75F);
      AxisAlignedBB var4 = new AxisAlignedBB(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, var3.field_72450_a, var3.field_72448_b, var3.field_72449_c);
      List var5 = this.field_70170_p.func_72872_a(f_.class, var4);

      try {
         if (var5.isEmpty()) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, true);

      for(f_ var7 : var5) {
         var7.f(this.func_174791_d());
      }

      this.field_70170_p.func_72900_e(this);
   }

   void a() {
      this.a(b6.b(this.field_70142_S, this.field_70165_t, (double)0.5F), b6.b(this.field_70137_T, this.field_70163_u, (double)0.5F), b6.b(this.field_70136_U, this.field_70161_v, (double)0.5F));
      this.a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   void a(double var1, double var3, double var5) {
      Random var7 = this.func_70681_au();
      this.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, var1 + var7.nextDouble() * (double)0.3F, var3 + (double)0.25F + var7.nextDouble() * (double)0.3F, var5 + var7.nextDouble() * (double)0.3F, (double)0.0F, (double)0.0F, (double)0.0F, new int[0]);
   }

   void b() {
      try {
         if (this.field_70170_p.field_72995_K) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (this.field_70128_L) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (!this.i) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      Vec3d var1 = new Vec3d(this.field_70165_t, (double)(this.func_180425_c().func_177956_o() + 1), this.field_70161_v);

      try {
         if (!this.b(var1)) {
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, true);
            this.i = false;
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      EntityWitherSkeleton var2 = new EntityWitherSkeleton(this.field_70170_p);
      var2.func_184611_a(EnumHand.MAIN_HAND, new ItemStack(Items.field_151052_q));
      var2.func_70634_a(var1.field_72450_a, var1.field_72448_b, var1.field_72449_c);
      this.field_70170_p.func_72838_d(var2);
      ge.b.sendToAllTracking(new bv(var1, true), this);
      this.f.bI.add(var2);
   }

   boolean b(Vec3d var1) {
      try {
         if (this.f == null) {
            return true;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      EntityLivingBase var2 = this.f.M();

      try {
         if (var2 == null) {
            return true;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      boolean var10000;
      try {
         if (var2.func_70011_f(var1.field_72450_a, var1.field_72448_b, var1.field_72449_c) < (double)15.0F) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      var10000 = false;
      return var10000;
   }

   @SideOnly(Side.CLIENT)
   public static void a(Vec3d var0) {
      WorldClient var1 = Minecraft.func_71410_x().field_71441_e;
      float var2 = gc.c(1.8F);
      Random var3 = r.f;

      for(float var4 = 0.0F; (double)var4 < (Math.PI * 2D); var4 += var2) {
         double var5 = Math.sin((double)var4);
         double var7 = Math.cos((double)var4);
         double var9 = var0.field_72450_a + var5 * (double)0.5F;
         double var11 = var5 * (double)0.15F;
         double var13 = var0.field_72449_c + var7 * (double)0.5F;
         double var15 = var7 * (double)0.15F;
         double var17 = var0.field_72448_b;
         double var19 = var3.nextDouble() * (double)0.15F;
         ((World)var1).func_175688_a(EnumParticleTypes.SMOKE_NORMAL, var9, var17, var13, var11, var19, var15, new int[0]);
      }

   }

   @SideOnly(Side.CLIENT)
   public static void c(Vec3d var0) {
      WorldClient var1 = Minecraft.func_71410_x().field_71441_e;
      Random var2 = r.f;
      int var3 = 0;

      try {
         while(var3 < 100) {
            ((World)var1).func_175688_a(EnumParticleTypes.DRAGON_BREATH, var0.field_72450_a, var0.field_72448_b, var0.field_72449_c, var2.nextDouble() * (double)0.15F, var2.nextDouble() * (double)0.15F, var2.nextDouble() * (double)0.15F, new int[0]);
            ++var3;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      ((World)var1).func_184134_a(var0.field_72450_a, var0.field_72448_b, var0.field_72449_c, com.trolmastercard.sexmod.c.MISC_SHATTER[0], SoundCategory.AMBIENT, 0.7F, 1.0F, false);
   }

   public boolean func_70097_a(DamageSource var1, float var2) {
      try {
         if (DamageSource.field_76380_i.equals(var1)) {
            this.func_70606_j(0.0F);
            this.i = false;
            this.field_70170_p.func_72900_e(this);
            return true;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      label53: {
         try {
            if (!this.field_70170_p.field_72995_K && "arrow".equals(var1.field_76373_n)) {
               break label53;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         Entity var3 = var1.func_76346_g();

         try {
            if (!(var3 instanceof EntityPlayer)) {
               return false;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         this.h = var3.func_70040_Z();
         this.c = true;
         return true;
      }

      this.func_70606_j(0.0F);
      this.i = false;
      ge.b.sendToAllTracking(new bv(this.func_174791_d(), false), this);
      Entity var8 = var1.func_76364_f();

      try {
         if (var8 != null) {
            this.field_70170_p.func_72900_e(var8);
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      this.field_70170_p.func_72900_e(this);
      return true;
   }

   public void func_70037_a(NBTTagCompound var1) {
      this.field_70170_p.func_72900_e(this);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
