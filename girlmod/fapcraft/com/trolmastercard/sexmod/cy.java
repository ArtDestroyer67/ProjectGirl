package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.MatrixStack;

public class cy extends EntityLivingBase implements IAnimatable {
   static final float e = 11000.0F;
   public static final DataParameter<String> a;
   public static final DataParameter<String> b;
   AnimationFactory g;
   public boolean f;
   public MatrixStack c;
   gw d;

   public cy(World var1) {
      super(var1);
      this.g = new AnimationFactory(this);
      this.f = false;
      this.c = new MatrixStack();
      this.d = null;
      this.field_70130_N = 0.1F;
      this.field_70131_O = 0.1F;
   }

   public cy(World var1, UUID var2, String var3) {
      this(var1);
      this.field_70180_af.func_187227_b(a, var2.toString());
      this.field_70180_af.func_187227_b(b, var3);
   }

   public static cy a(World var0, UUID var1, gw var2) {
      cy var3 = new cy(var0);
      var3.func_184212_Q().func_187227_b(a, var1.toString());
      var3.f = true;
      var3.d = var2;
      return var3;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(a, "");
      this.field_70180_af.func_187214_a(b, "");
   }

   public AxisAlignedBB func_184177_bl() {
      BlockPos var1 = this.func_180425_c();
      Vec3i var2 = new Vec3i((double)0.5F, (double)0.5F, (double)0.5F);
      return new AxisAlignedBB(var1.func_177973_b(var2), var1.func_177971_a(var2));
   }

   @SideOnly(Side.CLIENT)
   public boolean func_145770_h(double var1, double var3, double var5) {
      double var7 = this.field_70165_t - var1;
      double var9 = this.field_70163_u - var3;
      double var11 = this.field_70161_v - var5;
      double var13 = var7 * var7 + var9 * var9 + var11 * var11;
      return this.func_70112_a(var13);
   }

   @SideOnly(Side.CLIENT)
   public boolean func_70112_a(double var1) {
      boolean var10000;
      try {
         if (var1 < (double)11000.0F) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var10000 = false;
      return var10000;
   }

   @Nullable
   public UUID b() {
      String var1 = (String)this.field_70180_af.func_187225_a(a);

      try {
         if ("".equals(var1)) {
            return null;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return UUID.fromString(var1);
   }

   public boolean func_70097_a(DamageSource var1, float var2) {
      try {
         if (var1 != DamageSource.field_76380_i) {
            return false;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return super.func_70097_a(var1, var2);
   }

   @Nullable
   public String a() {
      String var1 = (String)this.field_70180_af.func_187225_a(b);

      try {
         return "".equals(var1) ? null : var1;
      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70067_L() {
      return false;
   }

   public void func_70645_a(DamageSource var1) {
      super.func_70645_a(var1);
   }

   public AnimationFactory getFactory() {
      return this.g;
   }

   public void registerControllers(AnimationData var1) {
   }

   public Iterable<ItemStack> func_184193_aE() {
      return new ArrayList();
   }

   public ItemStack func_184582_a(EntityEquipmentSlot var1) {
      return ItemStack.field_190927_a;
   }

   public void func_184201_a(EntityEquipmentSlot var1, ItemStack var2) {
   }

   public EnumHandSide func_184591_cq() {
      return EnumHandSide.LEFT;
   }

   static {
      a = EntityDataManager.func_187226_a(cy.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(101);
      b = EntityDataManager.func_187226_a(cy.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(102);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
