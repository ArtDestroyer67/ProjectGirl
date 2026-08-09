package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ei extends e2 {
   public static final String aa = "sexmod:CustomModel";
   public static final String ae = "sexmod:GirlSpecific";
   public static final float ac = 0.0F;
   public static final int am = 100;
   public static final int Y = 65;
   public static boolean ag = true;
   public Vector2f ao;
   public boolean ad;
   public boolean aj;
   public boolean ak;
   public boolean af;
   public boolean ah;
   protected static final DataParameter<Optional<UUID>> ai;
   public static Hashtable<UUID, ei> al;
   public static List<ei> Z;
   int an;
   public boolean ab;

   protected ei(World var1) {
      super(var1);
      this.ao = new Vector2f(0.0F, 0.0F);
      this.ad = false;
      this.aj = false;
      this.ak = false;
      this.af = true;
      this.ah = false;
      this.an = -1;
      this.ab = true;
      this.func_70105_a(0.01F, 0.01F);
      Z.add(this);
   }

   protected ei(World var1, UUID var2) {
      this(var1);
      this.m.func_187227_b(ai, Optional.of(var2));
   }

   @Nullable
   public static ei d(UUID var0) {
      return (ei)al.get(var0);
   }

   @Nullable
   public static ei g(@Nonnull EntityPlayer var0) {
      return (ei)al.get(var0.getPersistentID());
   }

   @Nullable
   public static ei a(UUID var0) {
      try {
         for(em var2 : ad()) {
            try {
               if (var2.field_70170_p.field_72995_K) {
                  continue;
               }
            } catch (ConcurrentModificationException var5) {
               throw a(var5);
            }

            try {
               if (!(var2 instanceof ei)) {
                  continue;
               }
            } catch (ConcurrentModificationException var4) {
               throw a(var4);
            }

            ei var3 = (ei)var2;
            if (var0.equals(var3.m())) {
               return var3;
            }
         }
      } catch (ConcurrentModificationException var6) {
      }

      return null;
   }

   public NetworkRegistry.TargetPoint P() {
      return new NetworkRegistry.TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u - (double)0.0F, this.field_70161_v, (double)50.0F);
   }

   public void a(int var1, fp var2) {
      ge.b.sendToAllTracking(new gd(this.m(), var1, var2), this.P());
   }

   public EntityPlayer c(EntityPlayer var1) {
      return var1;
   }

   public boolean z() {
      return true;
   }

   public Vec3d c(Vec3d var1, float var2) {
      return var1;
   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean v() {
      return true;
   }

   public boolean q() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void H() {
   }

   public boolean p() {
      return true;
   }

   public boolean a(String var1) {
      return false;
   }

   public boolean A() {
      return true;
   }

   public String c() {
      if (((Optional)this.m.func_187225_a(ai)).isPresent()) {
         EntityPlayer var1 = this.field_70170_p.func_152378_a((UUID)((Optional)this.m.func_187225_a(ai)).get());

         try {
            if (var1 != null) {
               return var1.func_70005_c_();
            }
         } catch (ConcurrentModificationException var2) {
            throw a(var2);
         }
      }

      return "anonymous horny girl";
   }

   public void u() {
   }

   public abstract void b(String var1, UUID var2);

   public abstract at a(int var1);

   public abstract String c(int var1);

   public Vec3i b(int var1) {
      return new Vec3i(255, 255, 255);
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70058_J() {
      return true;
   }

   public boolean F() {
      return false;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.m.func_187214_a(ai, Optional.absent());
   }

   @SideOnly(Side.CLIENT)
   public static void i() {
      ei var0 = d(Minecraft.func_71410_x().field_71439_g.getPersistentID());

      try {
         if (var0 == null) {
            return;
         }
      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }

      var0.r();
   }

   public void r() {
      try {
         this.B = null;
         this.func_189654_d(false);
         if (this.field_70170_p.field_72995_K) {
            this.V();
         }

      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }
   }

   @SideOnly(Side.CLIENT)
   protected void V() {
      try {
         if (!this.n() && !this.f()) {
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      d3.a(true);
      EntityPlayerSP var1 = Minecraft.func_71410_x().field_71439_g;
      ((EntityPlayer)var1).func_82142_c(false);
      ((EntityPlayer)var1).func_189654_d(false);
      var1.field_70145_X = false;
      this.m.func_187227_b(G, false);
      ge.b.sendToServer(new s(this.f()));
   }

   @SideOnly(Side.CLIENT)
   public boolean H() {
      // $FF: Couldn't be decompiled
   }

   protected void c(boolean var1) {
      try {
         if (!ag) {
            return;
         }
      } catch (ConcurrentModificationException var4) {
         throw a(var4);
      }

      try {
         if (this.m() == null) {
            return;
         }
      } catch (ConcurrentModificationException var6) {
         throw a(var6);
      }

      EntityPlayer var2 = this.field_70170_p.func_152378_a(this.m());

      try {
         if (var2 == null) {
            return;
         }
      } catch (ConcurrentModificationException var3) {
         throw a(var3);
      }

      try {
         var2.field_71075_bZ.field_75101_c = var1;
         if (!var1) {
            var2.field_71075_bZ.field_75100_b = false;
         }
      } catch (ConcurrentModificationException var5) {
         throw a(var5);
      }

      var2.func_71016_p();
   }

   public static boolean e(UUID var0) {
      C();

      for(Map.Entry var2 : al.entrySet()) {
         UUID var3 = (UUID)var2.getKey();

         try {
            if (var0.equals(var3)) {
               return true;
            }
         } catch (ConcurrentModificationException var4) {
            throw a(var4);
         }
      }

      return false;
   }

   public static boolean e(EntityPlayer var0) {
      try {
         if (var0 == null) {
            return false;
         }
      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }

      return e(var0.getPersistentID());
   }

   public AxisAlignedBB func_174813_aQ() {
      return super.func_174813_aQ().func_72317_d((double)0.0F, (double)0.5F, (double)0.0F);
   }

   protected EntityPlayer j() {
      List var1 = this.field_70170_p.field_73010_i;
      EntityPlayer var2 = null;

      for(EntityPlayer var4 : var1) {
         try {
            if (var4.getPersistentID().equals(((Optional)this.m.func_187225_a(ai)).get())) {
               continue;
            }
         } catch (ConcurrentModificationException var9) {
            throw a(var9);
         }

         if (var2 == null) {
            var2 = var4;
         } else {
            double var5 = var2.func_70092_e(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c);
            double var7 = var4.func_70092_e(this.w().field_72450_a, this.w().field_72448_b, this.w().field_72449_c);
            if (var7 < var5) {
               var2 = var4;
            }
         }
      }

      return var2;
   }

   @SideOnly(Side.CLIENT)
   public boolean e() {
      EntityPlayer var1 = this.j();

      try {
         if (var1 == null) {
            return false;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      return var1.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
   }

   public Vec3d w() {
      return new Vec3d(this.field_70165_t, this.field_70163_u - (double)0.0F, this.field_70161_v);
   }

   protected void b(UUID var1) {
      EntityPlayerMP var2 = (EntityPlayerMP)this.field_70170_p.func_152378_a(var1);
      EntityPlayerMP var3 = (EntityPlayerMP)this.field_70170_p.func_152378_a((UUID)((Optional)this.m.func_187225_a(ai)).get());
      ge.b.sendTo(new gz(false), var2);
      ge.b.sendTo(new gz(false), var3);
      this.e((UUID)var1);
      this.field_70177_z = 0.0F;
      this.field_70759_as = 0.0F;
      var2.field_70177_z = 180.0F;
      var2.field_70759_as = 180.0F;
      var2.func_189654_d(true);
      var2.field_70145_X = true;
      Vec3d var4 = this.func_174791_d();
      var2.func_70634_a(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c + (double)1.0F);
      var2.field_71075_bZ.field_75100_b = true;
      var3.field_71075_bZ.field_75100_b = true;
      this.j(var1);
      this.m.func_187227_b(G, true);
      this.c((Vec3d)var4);
      this.b(0.0F);
   }

   protected void func_180429_a(BlockPos var1, Block var2) {
      super.func_180429_a(var1, var2);
   }

   public AxisAlignedBB a(EntityPlayer var1) {
      return var1.func_174813_aQ();
   }

   public void func_70071_h_() {
      try {
         this.field_70145_X = true;
         this.func_189654_d(true);
         super.func_70071_h_();
         this.D();
         if (!this.field_70170_p.field_72995_K) {
            return;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      try {
         if (this.f()) {
            com.trolmastercard.sexmod.w.a.a();
         }

      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }
   }

   @SideOnly(Side.CLIENT)
   void h() {
      Minecraft.func_71410_x().field_71439_g.eyeHeight = this.func_70047_e();
   }

   @SideOnly(Side.CLIENT)
   public boolean f() {
      try {
         if (!((Optional)this.m.func_187225_a(ai)).isPresent()) {
            return false;
         }
      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }

      return ((UUID)((Optional)this.m.func_187225_a(ai)).get()).equals(Minecraft.func_71410_x().field_71439_g.getPersistentID());
   }

   public boolean E() {
      return false;
   }

   void d(EntityPlayer var1) {
      NBTTagCompound var2 = var1.getEntityData();
      String var3 = var2.func_74779_i("sexmod:CustomModel" + fy.a((Entity)this));
      this.f(var3);
   }

   public void func_70619_bc() {
      // $FF: Couldn't be decompiled
   }

   void D() {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   void n() {
      if (this.f()) {
         Minecraft var1 = Minecraft.func_71410_x();
         var1.field_71474_y.field_74320_O = 0;
         var1.field_71460_t.func_175066_a(var1.func_175606_aa());
         d3.a(true);
      }

   }

   public boolean o() {
      return this.Q();
   }

   public Vec3d b(Vec3d var1, float var2) {
      return var1;
   }

   public boolean a(fp var1, EntityPlayer var2) {
      return false;
   }

   public boolean l() {
      return true;
   }

   public void b(EntityPlayer var1) {
   }

   public void b(fp param1) {
      // $FF: Couldn't be decompiled
   }

   void f(EntityPlayer var1) {
      this.m.func_187227_b(X, ItemStack.field_190927_a);
      this.m.func_187227_b(T, ItemStack.field_190927_a);
      this.m.func_187227_b(U, ItemStack.field_190927_a);
      this.m.func_187227_b(W, ItemStack.field_190927_a);

      for(ItemStack var3 : var1.func_184193_aE()) {
         try {
            if (var3.func_77973_b() instanceof ItemElytra) {
               this.m.func_187227_b(T, var3);
               continue;
            }
         } catch (ConcurrentModificationException var7) {
            throw a(var7);
         }

         try {
            if (!(var3.func_77973_b() instanceof ItemArmor)) {
               continue;
            }
         } catch (ConcurrentModificationException var5) {
            throw a(var5);
         }

         ItemArmor var4 = (ItemArmor)var3.func_77973_b();

         label47: {
            label46: {
               try {
                  switch (var4.func_185083_B_()) {
                     case HEAD:
                        this.m.func_187227_b(X, var3);
                        continue;
                     case CHEST:
                        break label47;
                     case LEGS:
                        break label46;
                     case FEET:
                        break;
                     default:
                        continue;
                  }
               } catch (ConcurrentModificationException var6) {
                  throw a(var6);
               }

               this.m.func_187227_b(W, var3);
               continue;
            }

            this.m.func_187227_b(U, var3);
            continue;
         }

         this.m.func_187227_b(T, var3);
      }

   }

   public UUID m() {
      try {
         return ((Optional)this.m.func_187225_a(ai)).isPresent() ? (UUID)((Optional)this.m.func_187225_a(ai)).get() : null;
      } catch (ConcurrentModificationException var1) {
         throw a(var1);
      }
   }

   @Nullable
   public EntityPlayer k() {
      UUID var1 = this.m();

      try {
         if (var1 == null) {
            return null;
         }
      } catch (ConcurrentModificationException var2) {
         throw a(var2);
      }

      return this.field_70170_p.func_152378_a(var1);
   }

   public void a(Optional<UUID> var1) {
      this.m.func_187227_b(ai, var1);
   }

   public void y() {
   }

   public void B() {
   }

   public static void C() {
      ArrayList var0 = new ArrayList();

      try {
         for(ei var2 : Z) {
            try {
               if (var2.m() != null) {
                  al.put(var2.m(), var2);
                  var0.add(var2);
               }
            } catch (ConcurrentModificationException var3) {
               throw a(var3);
            }
         }
      } catch (ConcurrentModificationException var4) {
      }

      for(ei var6 : var0) {
         Z.remove(var6);
      }

      t();
   }

   static void t() {
      ArrayList var0 = new ArrayList();

      for(Map.Entry var2 : al.entrySet()) {
         try {
            if (((ei)var2.getValue()).field_70128_L) {
               var0.add(var2.getKey());
            }
         } catch (ConcurrentModificationException var3) {
            throw a(var3);
         }
      }

      for(UUID var5 : var0) {
         al.remove(var5);
      }

   }

   protected boolean c(UUID var1) {
      try {
         if (var1 == null) {
            return false;
         }
      } catch (ConcurrentModificationException var4) {
         throw a(var4);
      }

      ei var2 = d(var1);

      boolean var10000;
      try {
         if (var2 != null) {
            var10000 = true;
            return var10000;
         }
      } catch (ConcurrentModificationException var3) {
         throw a(var3);
      }

      var10000 = false;
      return var10000;
   }

   public void a(String var1, UUID var2) {
      try {
         if (this.a(var1)) {
            return;
         }
      } catch (ConcurrentModificationException var3) {
         throw a(var3);
      }

      try {
         if (!((Optional)this.m.func_187225_a(ai)).isPresent()) {
            return;
         }
      } catch (ConcurrentModificationException var4) {
         throw a(var4);
      }

      ge.b.sendToServer(new g4(var1, var2, (UUID)((Optional)this.m.func_187225_a(ai)).get(), this.ab));
      this.ab = true;
   }

   public void func_70014_b(NBTTagCompound var1) {
      super.func_70014_b(var1);
      var1.func_74778_a("owner", ((UUID)((Optional)this.m.func_187225_a(ai)).get()).toString());
   }

   public void func_70037_a(NBTTagCompound var1) {
      super.func_70037_a(var1);
      this.m.func_187227_b(ai, Optional.of(UUID.fromString(var1.func_74779_i("owner"))));
      Z.add(this);
   }

   public void a(SoundEvent var1, float var2, float var3) {
      Vec3d var4 = this.w();

      try {
         if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_184134_a(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c, var1, SoundCategory.NEUTRAL, var2, var3, false);
            return;
         }
      } catch (ConcurrentModificationException var5) {
         throw a(var5);
      }

      this.field_70170_p.func_184133_a((EntityPlayer)null, new BlockPos(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c), var1, SoundCategory.PLAYERS, var2, var3);
   }

   public void a(SoundEvent var1) {
      this.a(var1, 1.0F, 1.0F);
   }

   public void a(SoundEvent[] var1) {
      this.a(var1[this.func_70681_au().nextInt(var1.length)], 1.0F, 1.0F);
   }

   public void a(SoundEvent var1, float var2) {
      this.a(var1, var2, 1.0F);
   }

   protected void U() {
   }

   static {
      ai = EntityDataManager.func_187226_a(em.class, DataSerializers.field_187203_m).func_187156_b().func_187161_a(118);
      al = new Hashtable();
      Z = new ArrayList();
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
