package com.trolmastercard.sexmod;

import javax.swing.JFrame;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class fr extends JFrame {
   public boolean a = false;

   @SubscribeEvent
   public void a(TickEvent.ClientTickEvent var1) {
      try {
         if (this.a) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      this.a = true;
      g2.a();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
