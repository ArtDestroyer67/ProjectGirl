package com.trolmastercard.sexmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class co extends Potion {
   public static final Potion b = new co("horny potion", false, 16736968, 0, 0);
   public static final PotionType a;

   public co() {
      super(false, 0);
   }

   public co(String var1, boolean var2, int var3, int var4, int var5) {
      super(var2, var3);
      this.func_76390_b(var1);
      this.func_76399_b(var4, var5);
      this.setRegistryName(new ResourceLocation("sexmod:" + var1));
   }

   public static void a() {
      ForgeRegistries.POTIONS.register(b);
      ForgeRegistries.POTION_TYPES.register(a);
      PotionHelper.func_193357_a(PotionTypes.field_185231_c, Item.func_150898_a(Blocks.field_150328_O), a);
   }

   @SubscribeEvent
   public void a(TickEvent.PlayerTickEvent var1) {
      EntityPlayer var2 = var1.player;
      PotionEffect var3 = var2.func_70660_b(b);

      try {
         if (var2.field_70170_p.field_72995_K) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (var3.func_76459_b() > 3500) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      var2.func_184589_d(b);
      ge.b.sendTo(new bd(var2), (EntityPlayerMP)var2);
   }

   @SubscribeEvent
   public void a(LivingEvent.LivingUpdateEvent param1) {
      // $FF: Couldn't be decompiled
   }

   static {
      a = (PotionType)(new PotionType("horny_potion", new PotionEffect[]{new PotionEffect(b, 3600), new PotionEffect(MobEffects.field_76431_k, 200, 1)})).setRegistryName("horny_potion");
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
