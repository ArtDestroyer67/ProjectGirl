package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class i extends EntityLivingBase implements IAnimatable {
   static final int e = 12000;
   private final AnimationFactory d = new AnimationFactory(this);
   public UUID f = null;
   static AnimationController<i> a;
   public static final DataParameter<String> b;
   public static final DataParameter<Integer> c;

   public i(World var1) {
      super(var1);
      this.func_70105_a(0.5F, 0.5F);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(b, ff.aJ.toString());
      this.field_70180_af.func_187214_a(c, 0);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      int var1 = (Integer)this.field_70180_af.func_187225_a(c);

      try {
         if (var1 >= 12000) {
            this.a();
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_187227_b(c, var1 + 1);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   public boolean canTrample(World var1, Block var2, BlockPos var3, float var4) {
      return false;
   }

   public boolean func_70097_a(DamageSource var1, float var2) {
      boolean var3 = super.func_70097_a(var1, var2);

      try {
         if (!var3) {
            return false;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      this.func_70106_y();
      return true;
   }

   void a() {
      // $FF: Couldn't be decompiled
   }

   void a(ff var1) {
      EntityPlayer var2 = var1.z();

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      EntityPlayerMP var3 = (EntityPlayerMP)var2;
      EyeAndKoboldColor var4 = ax.l(this.f);
      var2.func_145747_a(new TextComponentString(String.format("%s%s %shas become a %snew tribe member%s!", var4.getTextColor(), var1.c(), TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE)));
      var3.field_71135_a.func_147359_a(new SPacketSoundEffect(SoundEvents.field_187734_u, SoundCategory.NEUTRAL, var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, 1.0F, 1.0F));
      var3.field_71135_a.func_147359_a(new SPacketSoundEffect(SoundEvents.field_187640_br, SoundCategory.NEUTRAL, var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, 1.0F, 1.0F));
   }

   public void registerControllers(AnimationData var1) {
      a = new AnimationController<i>(this, "controller", 5.0F, this::a);
      var1.addAnimationController(a);
   }

   public AnimationFactory getFactory() {
      return this.d;
   }

   public void func_70014_b(NBTTagCompound var1) {
      try {
         if (this.f != null) {
            var1.func_74778_a("tribeID", this.f.toString());
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      var1.func_74778_a("egg_color", (String)this.field_70180_af.func_187225_a(b));
      var1.func_74768_a("eggAge", (Integer)this.field_70180_af.func_187225_a(c));
      super.func_70014_b(var1);
   }

   public void func_70037_a(NBTTagCompound var1) {
      super.func_70037_a(var1);
      String var2 = var1.func_74779_i("tribeID");

      try {
         if (!"".equals(var2)) {
            this.f = UUID.fromString(var2);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      this.field_70180_af.func_187227_b(b, var1.func_74779_i("egg_color"));
      this.field_70180_af.func_187227_b(c, var1.func_74762_e("eggAge"));
   }

   protected <E extends IAnimatable> PlayState a(AnimationEvent<E> var1) {
      int var2 = (Integer)this.field_70180_af.func_187225_a(c);

      try {
         if (12000 - var2 < 20) {
            var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.model.hatch", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      float var3 = (float)var2 / 12000.0F;

      try {
         if ((double)var3 > 0.98) {
            var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.model.veryfast", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if ((double)var3 > 0.85) {
            var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.model.fast", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if ((double)var3 > (double)0.75F) {
            var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.model.medium", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if ((double)var3 > (double)0.5F) {
            var1.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.model.slow", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      return PlayState.CONTINUE;
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
      b = EntityDataManager.func_187226_a(i.class, DataSerializers.field_187194_d).func_187156_b().func_187161_a(115);
      c = EntityDataManager.func_187226_a(i.class, DataSerializers.field_187192_b).func_187156_b().func_187161_a(116);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
