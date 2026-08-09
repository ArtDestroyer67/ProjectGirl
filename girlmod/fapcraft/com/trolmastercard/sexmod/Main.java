package com.trolmastercard.sexmod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(
   modid = "sexmod",
   name = "Fapcraft",
   version = "1.1.0",
   dependencies = "after:geckolib"
)
public class Main {
   @Instance
   public static Main instance;
   @SidedProxy(
      clientSide = "com.trolmastercard.sexmod.ClientProxy",
      serverSide = "com.trolmastercard.sexmod.CommonProxy"
   )
   public static CommonProxy proxy;
   public static final Logger LOGGER = LogManager.getLogger("sexmod");

   @EventHandler
   public void preInit(FMLPreInitializationEvent var1) {
      GeckoLib.initialize();
      proxy.preInitRegistries(var1);
   }

   @EventHandler
   public void init(FMLInitializationEvent var1) throws IOException {
      proxy.initRegistries(var1);
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent var1) throws IOException {
      proxy.postInit(var1);
   }

   @EventHandler
   public static void onWorldClosed(FMLServerStoppedEvent var0) {
      try {
         em.ad().clear();
         ax.a();
         ff.aY.clear();
         v.a();
         g3.b().a();
         fs.a();
         br.e = false;
         bj.a();
         if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            clientReset();
         }

      } catch (RuntimeException var1) {
         throw a(var1);
      }
   }

   @EventHandler
   public static void onWorldStart(FMLServerStartingEvent var0) {
      var0.registerServerCommand(gn.a);
      var0.registerServerCommand(aw.a);
   }

   @SideOnly(Side.CLIENT)
   static void clientReset() {
      gm.a();
      d6.c();
   }

   @SideOnly(Side.CLIENT)
   @EventHandler
   public void registerReplacedRenderers(FMLInitializationEvent var1) {
      GeckoLib.initialize();
   }

   public static void setConfigs() throws IOException {
      File var0 = new File("config");
      var0.mkdir();
      File var1 = new File("config/sexmod.json");
      if (!var1.exists()) {
         var1.createNewFile();
         FileWriter var2 = new FileWriter(var1);
         var2.write("{\"shouldGenBuildings\":true,\"shouldLoadOtherSkins\":false,\"allowFlying\":true}");
         var2.close();
      }

      StringBuilder var27 = new StringBuilder();
      BufferedReader var3 = new BufferedReader(new FileReader(var1));
      Throwable var4 = null;

      try {
         while(true) {
            String var5;
            String var10000 = var5 = var3.readLine();

            try {
               if (var10000 == null) {
                  break;
               }

               var27.append(var5);
            } catch (Throwable var23) {
               throw a(var23);
            }
         }
      } catch (Throwable var24) {
         var4 = var24;
         throw var24;
      } finally {
         label162: {
            label161: {
               try {
                  if (var3 == null) {
                     break label162;
                  }

                  if (var4 == null) {
                     break label161;
                  }
               } catch (Throwable var19) {
                  throw a(var19);
               }

               try {
                  var3.close();
               } catch (Throwable var18) {
                  var4.addSuppressed(var18);
               }
               break label162;
            }

            var3.close();
         }

      }

      String var28 = var27.toString();
      if (!var28.contains("shouldGenBuildings")) {
         var1.delete();
         var1 = new File("config/sexmod.json");
         var1.createNewFile();
         FileWriter var30 = new FileWriter(var1);
         var30.write("{\"shouldGenBuildings\":true,\"shouldLoadOtherSkins\":false,\"allowFlying\":true}");
         var30.close();
         g3.i = true;
         cv.d = false;
         ei.ag = true;
      } else {
         int var29 = var28.indexOf("shouldGenBuildings");
         int var31 = var28.indexOf("shouldLoadOtherSkins");
         int var6 = var28.indexOf("allowFlying");

         boolean var32;
         label184: {
            try {
               if ('t' == var28.charAt(var29 + 20)) {
                  var32 = true;
                  break label184;
               }
            } catch (Throwable var22) {
               throw a(var22);
            }

            var32 = false;
         }

         label177: {
            try {
               g3.i = var32;
               if ('t' == var28.charAt(var31 + 22)) {
                  var32 = true;
                  break label177;
               }
            } catch (Throwable var21) {
               throw a(var21);
            }

            var32 = false;
         }

         label170: {
            try {
               cv.d = var32;
               if ('t' == var28.charAt(var6 + 13)) {
                  var32 = true;
                  break label170;
               }
            } catch (Throwable var20) {
               throw a(var20);
            }

            var32 = false;
         }

         ei.ag = var32;
      }
   }

   private static Throwable a(Throwable var0) {
      return var0;
   }
}
