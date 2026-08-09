package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class gi extends Entity {
   public static final int m = 15;
   private static final DataParameter<Integer> g;
   private static final DataParameter<Optional<UUID>> f;
   private boolean k;
   private int l;
   private int h;
   public int d;
   private int c;
   private int j;
   private float e;
   public Entity i;
   private a n;
   private int a;
   private int o;
   public static eb b;

   public gi(World var1, eb var2, double var3) {
      super(var1);
      this.n = gi.a.FLYING;
      this.a(var2);
      this.a(var3);
   }

   public gi(World var1) {
      super(var1);
      this.n = gi.a.FLYING;
   }

   private void a(eb var1) {
      this.func_70105_a(0.25F, 0.25F);
      this.field_70158_ak = true;
      var1.av = this;
   }

   protected void func_70088_a() {
      this.func_184212_Q().func_187214_a(g, 0);
      this.func_184212_Q().func_187214_a(f, Optional.of(b.f()));
   }

   public AxisAlignedBB func_184177_bl() {
      return this.func_174813_aQ().func_186662_g((double)10.0F);
   }

   eb b() {
      Optional var1 = (Optional)this.field_70180_af.func_187225_a(f);

      try {
         if (!var1.isPresent()) {
            return null;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      em var2 = em.a((UUID)var1.get());

      try {
         if (var2 == null) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (!(var2 instanceof eb)) {
            return null;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      return (eb)var2;
   }

   eb g() {
      Optional var1 = (Optional)this.field_70180_af.func_187225_a(f);

      try {
         if (!var1.isPresent()) {
            return null;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      em var2 = em.b((UUID)var1.get());

      try {
         if (!(var2 instanceof eb)) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return (eb)var2;
   }

   public void b(int var1) {
      this.o = var1;
   }

   public void a(int var1) {
      this.a = var1;
   }

   public void func_70030_z() {
      // $FF: Couldn't be decompiled
   }

   public void a(double var1) {
      eb var3 = this.b();

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var20) {
         throw a(var20);
      }

      BlockPos var4 = var3.ai;
      float var5 = (float)Math.sqrt(var3.func_174791_d().func_186679_c((double)var4.func_177958_n(), (double)var4.func_177956_o(), (double)var4.func_177952_p()));
      float var6 = -22.5F + 45.0F * (var5 / 7.0F);
      float var7 = var3.I();
      float var8 = MathHelper.func_76134_b(-var7 * ((float)Math.PI / 180F) - (float)Math.PI);
      float var9 = MathHelper.func_76126_a(-var7 * ((float)Math.PI / 180F) - (float)Math.PI);
      float var10 = -MathHelper.func_76134_b(-var6 * ((float)Math.PI / 180F));
      float var11 = MathHelper.func_76126_a(-var6 * ((float)Math.PI / 180F));
      double var12 = var3.field_70169_q + (var3.field_70165_t - var3.field_70169_q) - (double)var9 * 0.3;
      double var14 = var3.field_70167_r + (var3.field_70163_u - var3.field_70167_r) + (double)var3.func_70047_e();
      double var16 = var3.field_70166_s + (var3.field_70161_v - var3.field_70166_s) - (double)var8 * 0.3;
      this.func_70012_b(var12, var14, var16, var7, var6);
      this.field_70159_w = var1 * (double)(-var9);
      this.field_70181_x = var1 * (double)MathHelper.func_76131_a(-(var11 / var10), -5.0F, 5.0F);
      this.field_70179_y = var1 * (double)(-var8);
      float var18 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
      this.field_70159_w *= 0.6 / (double)var18 + (double)0.5F + this.field_70146_Z.nextGaussian() * 0.0045;
      this.field_70181_x *= 0.6 / (double)var18 + (double)0.5F + this.field_70146_Z.nextGaussian() * 0.0045;
      this.field_70179_y *= 0.6 / (double)var18 + (double)0.5F + this.field_70146_Z.nextGaussian() * 0.0045;
      float var19 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * (180D / Math.PI));
      this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)var19) * (180D / Math.PI));
      this.field_70126_B = this.field_70177_z;
      this.field_70127_C = this.field_70125_A;
   }

   public void func_184206_a(DataParameter<?> var1) {
      if (g.equals(var1)) {
         int var2 = (Integer)this.func_184212_Q().func_187225_a(g);

         gi var10000;
         Entity var10001;
         label19: {
            try {
               var10000 = this;
               if (var2 > 0) {
                  var10001 = this.field_70170_p.func_73045_a(var2 - 1);
                  break label19;
               }
            } catch (RuntimeException var3) {
               throw a(var3);
            }

            var10001 = null;
         }

         var10000.i = var10001;
      }

      super.func_184206_a(var1);
   }

   @SideOnly(Side.CLIENT)
   public boolean func_70112_a(double var1) {
      double var3 = (double)64.0F;

      boolean var10000;
      try {
         if (var1 < (double)4096.0F) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      var10000 = false;
      return var10000;
   }

   @SideOnly(Side.CLIENT)
   public void func_180426_a(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
   }

   public void func_70071_h_() {
      // $FF: Couldn't be decompiled
   }

   private boolean f() {
      return false;
   }

   private void h() {
      float var1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * (180D / Math.PI));
      this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)var1) * (180D / Math.PI));

      try {
         while(this.field_70125_A - this.field_70127_C < -180.0F) {
            this.field_70127_C -= 360.0F;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         while(this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         while(this.field_70177_z - this.field_70126_B < -180.0F) {
            this.field_70126_B -= 360.0F;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         while(this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
      this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
   }

   private void e() {
      // $FF: Couldn't be decompiled
   }

   private void a() {
      this.func_184212_Q().func_187227_b(g, this.i.func_145782_y() + 1);
   }

   private void a(BlockPos param1) {
      // $FF: Couldn't be decompiled
   }

   protected boolean a(Entity param1) {
      // $FF: Couldn't be decompiled
   }

   public void func_70014_b(NBTTagCompound var1) {
   }

   public void func_70037_a(NBTTagCompound var1) {
   }

   public int c() {
      // $FF: Couldn't be decompiled
   }

   protected void d() {
      eb var1 = this.b();
      if (var1 != null) {
         double var2 = var1.field_70165_t - this.field_70165_t;
         double var4 = var1.field_70163_u - this.field_70163_u;
         double var6 = var1.field_70161_v - this.field_70161_v;
         double var8 = 0.1;
         Entity var10000 = this.i;
         var10000.field_70159_w += var2 * 0.1;
         var10000 = this.i;
         var10000.field_70181_x += var4 * 0.1;
         var10000 = this.i;
         var10000.field_70179_y += var6 * 0.1;
      }

   }

   protected boolean func_70041_e_() {
      return false;
   }

   public void func_70020_e(NBTTagCompound var1) {
   }

   public NBTTagCompound func_189511_e(NBTTagCompound var1) {
      return null;
   }

   static {
      g = EntityDataManager.func_187226_a(gi.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
      f = EntityDataManager.func_187226_a(gi.class, DataSerializers.field_187203_m).func_187156_b().func_187161_a(110);
      b = null;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   static enum a {
      FLYING,
      HOOKED_IN_ENTITY,
      BOBBING;
   }
}
