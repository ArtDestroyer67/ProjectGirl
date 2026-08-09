package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class ay extends EntityLiving {
   public static int b = 8400;
   public static List<ay> g = new ArrayList();
   private static final DataParameter<Integer> d;
   private static final DataParameter<Integer> c;
   public float a;
   public float e;
   public float h;
   private boolean f;

   public ay(World var1) {
      super(var1);
      this.field_70765_h = new b(this);
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(1, new d(this));
      this.field_70714_bg.func_75776_a(5, new c(this));
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(c, 1);
      this.field_70180_af.func_187214_a(d, 0);
   }

   public void func_180430_e(float var1, float var2) {
   }

   protected boolean func_70692_ba() {
      return false;
   }

   protected void a(int var1, boolean var2) {
      try {
         this.field_70180_af.func_187227_b(c, var1);
         this.func_70105_a(0.51000005F * (float)var1, 0.51000005F * (float)var1);
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(var1 * var1));
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)(0.2F + 0.1F * (float)var1));
         if (var2) {
            this.func_70606_j(this.func_110138_aP());
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      this.field_70728_aV = var1;
   }

   public int h() {
      return (Integer)this.field_70180_af.func_187225_a(c);
   }

   public static void a(DataFixer var0) {
      EntityLiving.func_189752_a(var0, ay.class);
   }

   public void func_70014_b(NBTTagCompound var1) {
      super.func_70014_b(var1);
      var1.func_74768_a("Size", this.h() - 1);
      var1.func_74757_a("wasOnGround", this.f);
      var1.func_74768_a("ageInTicks", (Integer)this.field_70180_af.func_187225_a(d));
   }

   public void func_70037_a(NBTTagCompound var1) {
      super.func_70037_a(var1);
      int var2 = var1.func_74762_e("Size");
      if (var2 < 0) {
         var2 = 0;
      }

      this.a(var2 + 1, false);
      this.f = var1.func_74767_n("wasOnGround");
      this.field_70180_af.func_187227_b(d, var1.func_74762_e("ageInTicks"));
   }

   public boolean j() {
      boolean var10000;
      try {
         if (this.h() <= 1) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = false;
      return var10000;
   }

   protected EnumParticleTypes g() {
      return EnumParticleTypes.SLIME;
   }

   public static ArrayList<ay> a(Vec3d var0) {
      ArrayList var1 = a(var0, 0.1);
      if (var1.isEmpty()) {
         var1 = a(var0, (double)0.5F);
      }

      return var1;
   }

   private static ArrayList<ay> a(Vec3d param0, double param1) {
      // $FF: Couldn't be decompiled
   }

   public Vec3d e() {
      return new Vec3d(this.field_70169_q, this.field_70167_r, this.field_70166_s);
   }

   void a(EnumParticleTypes var1) {
      double var2 = r.f.nextGaussian() * 0.02;
      double var4 = r.f.nextGaussian() * 0.02;
      double var6 = r.f.nextGaussian() * 0.02;
      this.field_70170_p.func_175688_a(var1, this.field_70165_t + (double)(r.f.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.15 + (double)(r.f.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(r.f.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, var2, var4, var6, new int[0]);
   }

   public void func_70071_h_() {
      // $FF: Couldn't be decompiled
   }

   protected void b() {
      this.a *= 0.6F;
   }

   protected int a() {
      return this.field_70146_Z.nextInt(100) + 50;
   }

   protected ay d() {
      return new ay(this.field_70170_p);
   }

   public void func_184206_a(DataParameter<?> param1) {
      // $FF: Couldn't be decompiled
   }

   public void func_70106_y() {
      // $FF: Couldn't be decompiled
   }

   public float func_70047_e() {
      return 0.625F * this.field_70131_O;
   }

   protected SoundEvent func_184601_bQ(DamageSource var1) {
      SoundEvent var10000;
      try {
         if (this.j()) {
            var10000 = SoundEvents.field_187898_fy;
            return var10000;
         }
      } catch (RuntimeException var2) {
         throw a((Exception)var2);
      }

      var10000 = SoundEvents.field_187880_fp;
      return var10000;
   }

   protected SoundEvent func_184615_bR() {
      SoundEvent var10000;
      try {
         if (this.j()) {
            var10000 = SoundEvents.field_187896_fx;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = SoundEvents.field_187874_fm;
      return var10000;
   }

   protected SoundEvent f() {
      SoundEvent var10000;
      try {
         if (this.j()) {
            var10000 = SoundEvents.field_187900_fz;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = SoundEvents.field_187886_fs;
      return var10000;
   }

   protected Item func_146068_u() {
      Item var10000;
      try {
         if (this.h() == 1) {
            var10000 = Items.field_151123_aH;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = null;
      return var10000;
   }

   @Nullable
   protected ResourceLocation func_184647_J() {
      ResourceLocation var10000;
      try {
         if (this.h() == 1) {
            var10000 = LootTableList.field_186378_ac;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = LootTableList.field_186419_a;
      return var10000;
   }

   protected float func_70599_aP() {
      return 0.4F * (float)this.h();
   }

   public int func_70646_bf() {
      return 0;
   }

   protected boolean i() {
      boolean var10000;
      try {
         if (this.h() > 0) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = false;
      return var10000;
   }

   protected void func_70664_aZ() {
      this.field_70181_x = (double)0.42F;
      this.field_70160_al = true;
   }

   @Nullable
   public IEntityLivingData func_180482_a(DifficultyInstance var1, @Nullable IEntityLivingData var2) {
      this.a(1, true);
      return super.func_180482_a(var1, var2);
   }

   protected SoundEvent c() {
      SoundEvent var10000;
      try {
         if (this.j()) {
            var10000 = SoundEvents.field_189110_fE;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      var10000 = SoundEvents.field_187882_fq;
      return var10000;
   }

   protected boolean k() {
      return false;
   }

   static {
      d = EntityDataManager.func_187226_a(ay.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
      c = EntityDataManager.func_187226_a(ay.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(110);
   }

   private static Exception a(Exception var0) {
      return var0;
   }

   static class a extends EntityAIBase {
      private final ay b;
      private float a;
      private int c;

      public a(ay var1) {
         this.b = var1;
         this.func_75248_a(2);
      }

      public boolean func_75250_a() {
         // $FF: Couldn't be decompiled
      }

      public void func_75246_d() {
         try {
            if (--this.c <= 0) {
               this.c = 40 + this.b.func_70681_au().nextInt(60);
               this.a = (float)this.b.func_70681_au().nextInt(360);
            }
         } catch (RuntimeException var1) {
            throw a(var1);
         }

         ((b)this.b.func_70605_aq()).a(this.a, false);
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }

   static class b extends EntityMoveHelper {
      private float b;
      private int c;
      private final ay d;
      private boolean a;

      public b(ay var1) {
         super(var1);
         this.d = var1;
         this.b = 180.0F * var1.field_70177_z / (float)Math.PI;
      }

      public void a(float var1, boolean var2) {
         this.b = var1;
         this.a = var2;
      }

      public void a(double var1) {
         this.field_75645_e = var1;
         this.field_188491_h = Action.MOVE_TO;
      }

      public void func_75641_c() {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }

   static class c extends EntityAIBase {
      private final ay a;

      public c(ay var1) {
         this.a = var1;
         this.func_75248_a(5);
      }

      public boolean func_75250_a() {
         return true;
      }

      public void func_75246_d() {
         ((b)this.a.func_70605_aq()).a((double)1.0F);
      }
   }

   static class d extends EntityAIBase {
      private final ay a;

      public d(ay var1) {
         this.a = var1;
         this.func_75248_a(5);
         ((PathNavigateGround)var1.func_70661_as()).func_179693_d(true);
      }

      public boolean func_75250_a() {
         // $FF: Couldn't be decompiled
      }

      public void func_75246_d() {
         try {
            if (this.a.func_70681_au().nextFloat() < 0.8F) {
               this.a.func_70683_ar().func_75660_a();
            }
         } catch (RuntimeException var1) {
            throw a(var1);
         }

         ((b)this.a.func_70605_aq()).a(1.2);
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
