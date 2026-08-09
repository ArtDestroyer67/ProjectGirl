package com.trolmastercard.sexmod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class g0 {
   public static boolean a() {
      String var0 = Thread.currentThread().getName().toLowerCase();

      try {
         if (var0.contains("server")) {
            return true;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (var0.contains("client")) {
            return false;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      MinecraftServer var1 = FMLCommonHandler.instance().getMinecraftServerInstance();

      try {
         if (var1 == null) {
            return false;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      boolean var2 = var1.func_152345_ab();
      Main.LOGGER.warn("couldn't clarify if is running on a server or client thread. Came to the solution onServer=" + var2);
      return var2;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
