package com.trolmastercard.sexmod;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ey {
   static final int a = 3;

   @SubscribeEvent
   public void a(BlockEvent.BreakEvent var1) {
      Block var2 = var1.getState().func_177230_c();

      try {
         if (var2 != Blocks.field_150324_C) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      BlockPos var3 = var1.getPos();
      AxisAlignedBB var4 = new AxisAlignedBB((double)(var3.func_177958_n() - 3), (double)(var3.func_177956_o() - 3), (double)(var3.func_177952_p() - 3), (double)(var3.func_177958_n() + 3), (double)(var3.func_177956_o() + 3), (double)(var3.func_177952_p() + 3));
      List var5 = var1.getWorld().func_72872_a(em.class, var4);
      boolean var6 = false;

      for(em var8 : var5) {
         try {
            if (var8.field_70128_L || !(Boolean)var8.func_184212_Q().func_187225_a(em.G)) {
               continue;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         var6 = true;
         break;
      }

      try {
         if (!var6) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      var1.getPlayer().func_146105_b(new TextComponentString("this bed is currently used by a girl.. pls don't disturb okay? ... you are kinda mean rn"), true);
      var1.setCanceled(true);
   }

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void a(PlayerSPPushOutOfBlocksEvent var1) {
      try {
         if (em.d(var1.getEntityPlayer()) != null) {
            var1.setCanceled(true);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
