package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
   public void preInitRegistries(FMLPreInitializationEvent var1) {
      GameRegistry.registerWorldGenerator(g3.b(), 0);
      bi.a();
      f9.a();
   }

   public void initRegistries(FMLInitializationEvent var1) throws IOException {
      Main.setConfigs();
      c.a();
      NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new et());
      bn.a(false);
      ge.a();
   }

   public void postInit(FMLPostInitializationEvent var1) throws IOException {
      this.setUpCustomModelsOnServer();
   }

   void setUpCustomModelsOnServer() {
      try {
         if (!FMLCommonHandler.instance().getMinecraftServerInstance().func_71262_S()) {
            return;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      br.c(false);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
