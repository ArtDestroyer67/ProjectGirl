package com.trolmastercard.sexmod;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ew extends ei {
   public static final DataParameter<String> as;
   public static final DataParameter<BlockPos> au;
   public static final DataParameter<String> at;
   boolean ar = true;
   String ap = null;
   String av = null;
   BlockPos aq = null;

   protected ew(World var1) {
      super(var1);
   }

   protected ew(World var1, UUID var2) {
      super(var1, var2);
   }

   protected void func_70088_a() {
      // $FF: Couldn't be decompiled
   }

   protected abstract String a(StringBuilder var1);

   public static String[] a(em var0) {
      return ((String)var0.func_184212_Q().func_187225_a(at)).split("-");
   }

   public void func_70071_h_() {
      try {
         super.func_70071_h_();
         this.b();
         if (!this.ar) {
            return;
         }
      } catch (RuntimeException var4) {
         throw d(var4);
      }

      try {
         if (this.field_70170_p.field_72995_K) {
            this.a();
            this.ar = true;
            return;
         }
      } catch (RuntimeException var6) {
         throw d(var6);
      }

      EntityPlayer var1 = this.k();

      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw d(var5);
      }

      String var2 = var1.getEntityData().func_74779_i("sexmod:GirlSpecific" + fy.a((Entity)this));

      try {
         this.ar = false;
         if (!"".equals(var2)) {
            this.a((List)c(var2));
         }

      } catch (RuntimeException var3) {
         throw d(var3);
      }
   }

   void b() {
      // $FF: Couldn't be decompiled
   }

   protected abstract void a();

   static {
      as = EntityDataManager.func_187226_a(ew.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(119);
      au = EntityDataManager.func_187226_a(ew.class, DataSerializers.field_187200_j).func_187156_b().func_187161_a(120);
      at = EntityDataManager.func_187226_a(ew.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(121);
   }

   private static RuntimeException d(RuntimeException var0) {
      return var0;
   }
}
