package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class e2 extends em {
   public int S = 1;
   public int P;
   public int O = 0;
   public int K;
   public Vec3d V;
   public boolean N;
   public ItemStackHandler Q;
   public static final DataParameter<ItemStack> L;
   public static final DataParameter<ItemStack> R;
   public static final DataParameter<ItemStack> X;
   public static final DataParameter<ItemStack> T;
   public static final DataParameter<ItemStack> U;
   public static final DataParameter<ItemStack> W;
   public static final DataParameter<Integer> M;

   protected e2(World var1) {
      super(var1);
      this.V = Vec3d.field_186680_a;
      this.Q = new ItemStackHandler(7);
      if (this.Q.getStackInSlot(0) == ItemStack.field_190927_a) {
         this.Q.setStackInSlot(0, new ItemStack(Items.field_151040_l));
      }

      try {
         if (this.Q.getStackInSlot(1) == ItemStack.field_190927_a) {
            this.Q.setStackInSlot(1, new ItemStack(Items.field_151031_f));
         }

      } catch (RuntimeException var2) {
         throw b(var2);
      }
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.m.func_187214_a(M, 0);
      this.m.func_187214_a(L, ItemStack.field_190927_a);
      this.m.func_187214_a(R, ItemStack.field_190927_a);
      this.m.func_187214_a(X, ItemStack.field_190927_a);
      this.m.func_187214_a(T, ItemStack.field_190927_a);
      this.m.func_187214_a(U, ItemStack.field_190927_a);
      this.m.func_187214_a(W, ItemStack.field_190927_a);
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70714_bg.func_75776_a(1, new g(this));
   }

   public void c() {
   }

   public void func_70619_bc() {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   public void a(String var1, UUID var2) {
      try {
         if ("action.names.followme".equals(var1)) {
            this.a("master", var2.toString());
            return;
         }
      } catch (RuntimeException var7) {
         throw b(var7);
      }

      try {
         if ("action.names.stopfollowme".equals(var1)) {
            this.x();
            return;
         }
      } catch (RuntimeException var6) {
         throw b(var6);
      }

      if ("action.names.equipment".equals(var1)) {
         EntityPlayerSP var3 = Minecraft.func_71410_x().field_71439_g;
         ge.b.sendToServer(new bo(this.f(), ((EntityPlayer)var3).getPersistentID()));
      } else {
         try {
            if ("action.names.gohome".equals(var1)) {
               this.x();
               ge.b.sendToServer(new gg(this.f()));
               return;
            }
         } catch (RuntimeException var5) {
            throw b(var5);
         }

         try {
            if ("action.names.setnewhome".equals(var1)) {
               this.c();
               ge.b.sendToServer(new a6(this.f(), new Vec3d(this.func_180425_c())));
            }
         } catch (RuntimeException var4) {
            throw b(var4);
         }
      }

   }

   public void func_70014_b(NBTTagCompound var1) {
      var1.func_74782_a("inventory", this.Q.serializeNBT());
      super.func_70014_b(var1);
   }

   public void func_70037_a(NBTTagCompound var1) {
      super.func_70037_a(var1);
      this.Q.deserializeNBT(var1.func_74775_l("inventory"));
   }

   public boolean hasCapability(Capability<?> param1, EnumFacing param2) {
      // $FF: Couldn't be decompiled
   }

   public <T> T getCapability(Capability<T> var1, EnumFacing var2) {
      Object var10000;
      try {
         if (var1 == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            var10000 = this.Q;
            return (T)var10000;
         }
      } catch (RuntimeException var3) {
         throw b(var3);
      }

      var10000 = super.getCapability(var1, var2);
      return (T)var10000;
   }

   static {
      L = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(117);
      R = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(116);
      X = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(115);
      T = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(114);
      U = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(113);
      W = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187196_f).func_187156_b().func_187161_a(112);
      M = EntityDataManager.func_187226_a(e2.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(111);
   }

   private static RuntimeException b(RuntimeException var0) {
      return var0;
   }
}
