package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class gb extends Gui {
   static final ResourceLocation l = new ResourceLocation("sexmod", "textures/gui/escape_minigame_ui.png");
   static final int f = 52;
   static final float a = 20.0F;
   static final int p = 35;
   static final float n = 0.08F;
   static final float h = 0.006F;
   static final int m = 2;
   static final float i = 0.33F;
   static boolean g = false;
   static gr q = null;
   static float k = 0.0F;
   static float j = 0.0F;
   static boolean b = true;
   static float d = 0.0F;
   static boolean c = false;
   static Minecraft e = Minecraft.func_71410_x();
   static boolean o = false;

   public static void e() {
      // $FF: Couldn't be decompiled
   }

   static void b() {
      gr var0 = q;
      Random var1 = new Random();

      try {
         do {
            q = gr.values()[var1.nextInt(gr.values().length)];
         } while(var0 == q);

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   static void c() {
      try {
         if (!g) {
            return;
         }
      } catch (RuntimeException var0) {
         throw a(var0);
      }

      try {
         if (o) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      o = true;
      ge.b.sendToServer(new cd());
      d();
   }

   public static void a() {
      g = true;
      o = false;
      j = 0.0F;
      k = 0.0F;
      d = 0.0F;
      c = false;
   }

   public static void d() {
      c = true;
      d = 0.0F;
   }

   @SubscribeEvent
   public void a(RenderGameOverlayEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(TickEvent.ClientTickEvent var1) {
      try {
         if (var1.phase == Phase.END) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      e();
   }

   @SubscribeEvent
   public void a(InputEvent.KeyInputEvent param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
