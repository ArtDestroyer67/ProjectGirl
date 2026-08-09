package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class d3 {
   private static boolean c = true;
   public static boolean d = false;
   public static boolean a = false;
   public static MovementInput b;

   @SubscribeEvent
   public void a(InputUpdateEvent param1) {
      // $FF: Couldn't be decompiled
   }

   public static boolean b() {
      return c;
   }

   public static void a(boolean var0) {
      try {
         c = var0;
         if (!var0) {
            a();
         }

      } catch (RuntimeException var1) {
         throw a(var1);
      }
   }

   @SideOnly(Side.CLIENT)
   static void a() {
      EntityPlayerSP var0 = Minecraft.func_71410_x().field_71439_g;

      try {
         if (!ei.e((EntityPlayer)var0)) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      ((EntityPlayer)var0).func_146105_b(new TextComponentString("Jump to get out of the animation"), true);
   }

   @SubscribeEvent
   public void a(MouseEvent param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
