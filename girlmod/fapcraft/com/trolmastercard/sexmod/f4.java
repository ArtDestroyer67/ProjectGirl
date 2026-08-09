package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class f4 {
   @SubscribeEvent
   public void a(PlayerInteractEvent.EntityInteractSpecific var1) {
      Entity var2 = var1.getTarget();

      try {
         if (!(var2 instanceof em)) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      EntityPlayer var3 = var1.getEntityPlayer();
      ItemStack var4;
      if (var3.func_184614_ca().func_77973_b() == Items.field_151057_cb) {
         var4 = var3.func_184614_ca();
      } else {
         if (var3.func_184592_cb().func_77973_b() != Items.field_151057_cb) {
            return;
         }

         var4 = var3.func_184592_cb();
      }

      String var5 = var4.func_82833_r();

      try {
         if ("".equals(var5)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         ((em)var2).g(var5);
         if (!var3.field_71075_bZ.field_75098_d) {
            var4.func_190918_g(1);
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      var1.setCanceled(true);
      var1.setResult(Result.DENY);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
