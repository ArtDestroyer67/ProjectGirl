package com.trolmastercard.sexmod;

import java.io.File;
import java.util.ConcurrentModificationException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class et implements IGuiHandler {
   File b;
   File c;
   boolean a = false;

   public et() {
   }

   public et(boolean var1) {
      this.a();
   }

   @SideOnly(Side.CLIENT)
   void a() {
      // $FF: Couldn't be decompiled
   }

   public Object getServerGuiElement(int param1, EntityPlayer param2, World param3, int param4, int param5, int param6) {
      // $FF: Couldn't be decompiled
   }

   public Object getClientGuiElement(int param1, EntityPlayer param2, World param3, int param4, int param5, int param6) {
      // $FF: Couldn't be decompiled
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
