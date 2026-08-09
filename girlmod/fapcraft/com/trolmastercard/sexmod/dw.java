package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class dw extends BlockFire {
   public static final Block a = new dw();

   public void func_180650_b(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   public static void a() {
      a.setRegistryName("sexmod", "fire");
      a.func_149663_c("fire");
      MinecraftForge.EVENT_BUS.register(dw.class);
   }

   @SubscribeEvent
   public static void a(RegistryEvent.Register<Block> var0) {
      var0.getRegistry().register(a);
   }
}
