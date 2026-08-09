package com.trolmastercard.sexmod;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ds extends Gui {
   static ResourceLocation e = new ResourceLocation("sexmod", "textures/gui/buttons.png");
   static ResourceLocation b = new ResourceLocation("sexmod", "textures/gui/hornymeter.png");
   public static boolean d = false;
   public static double c = (double)0.0F;
   static double a;
   static float f;
   static float g;
   static boolean i;
   static boolean h;

   public static void d() {
      try {
         if (d) {
            return;
         }
      } catch (RuntimeException var0) {
         throw a(var0);
      }

      b();
      d = true;
      h = true;
   }

   public static void a(boolean var0) {
      try {
         if (d) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      b();
      d = true;
      h = var0;
   }

   public static void c() {
      b();
      d = false;
      h = true;
   }

   public static boolean a() {
      return d;
   }

   @SubscribeEvent
   public void a(RenderGameOverlayEvent param1) {
      // $FF: Couldn't be decompiled
   }

   public static void a(double var0) {
      double var10000;
      label16: {
         try {
            c += var0;
            if (c > (double)1.0F) {
               var10000 = (double)1.0F;
               break label16;
            }
         } catch (RuntimeException var2) {
            throw a(var2);
         }

         var10000 = c;
      }

      c = var10000;
   }

   public static void b() {
      c = (double)0.0F;
      i = false;
   }

   static {
      a = c;
      f = 0.0F;
      g = 0.0F;
      i = false;
      h = true;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
