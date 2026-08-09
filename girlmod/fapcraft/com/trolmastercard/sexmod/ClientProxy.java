package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {
   public static boolean IS_PRELOADING = false;
   public static KeyBinding[] keyBindings;

   public void postInit(FMLPostInitializationEvent var1) throws IOException {
   }

   public void preInitRegistries(FMLPreInitializationEvent var1) {
      super.preInitRegistries(var1);
      fk.a();
   }

   public void initRegistries(FMLInitializationEvent var1) throws IOException {
      keyBindings = new KeyBinding[2];
      keyBindings[0] = new KeyBinding("Interact with your goblin", 34, "Sex mod");
      keyBindings[1] = new KeyBinding("open character customisation menu", 76, "Sex mod");

      for(KeyBinding var5 : keyBindings) {
         ClientRegistry.registerKeyBinding(var5);
      }

      Main.setConfigs();
      c.a();
      NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new et(true));
      bn.a(true);
      ge.a();
      Minecraft var10 = Minecraft.func_71410_x();
      RenderManager var11 = var10.func_175598_ae();
      gj var12 = new gj();
      IS_PRELOADING = true;

      try {
         for(fy var8 : fy.values()) {
            var11.func_188391_a((Entity)var8.npcClass.getDeclaredConstructor(World.class).newInstance(var12), (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, false);
         }
      } catch (Exception var9) {
         System.out.println("error while preloading:");
         var9.printStackTrace();
      }

      IS_PRELOADING = false;
      w.a = new w();
      ClientCommandHandler.instance.func_71560_a(fd.a);
      ClientCommandHandler.instance.func_71560_a(fx.a);
      ClientCommandHandler.instance.func_71560_a(a_.b);
      Minecraft.func_71410_x().field_71452_i.func_178929_a(625115, (var0, var1x, var2, var4, var6, var8x, var10x, var12x, var14) -> new ez(var1x, var2, var4, var6));
   }
}
