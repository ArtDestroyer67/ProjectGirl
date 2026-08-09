package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.geo.raw.pojo.Converter;
import software.bernie.geckolib3.geo.raw.pojo.RawGeoModel;
import software.bernie.geckolib3.geo.raw.tree.RawGeometryTree;
import software.bernie.geckolib3.geo.render.GeoBuilder;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class br {
   public static final String a = "sexmod/custom_models";
   static final String b = "sexmod/custom_models/whitelisted_servers.txt";
   public static final String f = "sexmod_custom_models";
   static Map<String, b> c = new HashMap();
   public static boolean d = false;
   public static boolean e = false;

   public static Map<String, b> i() {
      return c;
   }

   public static boolean f(String var0) {
      boolean var10000;
      try {
         if (c.get(var0) != null) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a((Throwable)var1);
      }

      var10000 = false;
      return var10000;
   }

   public static int b(boolean var0) {
      a(var0);
      return c(var0);
   }

   static void b(Level var0, String var1) {
      try {
         if (Main.proxy instanceof ClientProxy) {
            a(var0, var1);
            return;
         }
      } catch (RuntimeException var2) {
         throw a((Throwable)var2);
      }

      Main.LOGGER.log(var0, var1);
   }

   public static void a(boolean var0) {
      try {
         if (var0) {
            c();
         }
      } catch (RuntimeException var1) {
         throw a((Throwable)var1);
      }

      c.clear();
   }

   public static void a() {
      ge.b.sendToServer(new g6());
   }

   @SideOnly(Side.CLIENT)
   public static boolean b() {
      String var0 = g();

      try {
         if (var0 == null) {
            return false;
         }
      } catch (RuntimeException var1) {
         throw a((Throwable)var1);
      }

      return l(var0);
   }

   public static void h(String var0) {
      File var1 = new File("sexmod/custom_models/whitelisted_servers.txt");
      var1.mkdirs();
      HashSet var2 = new HashSet();
      if (var1.exists()) {
         var2 = f();
      }

      var2.add(var0);
      var1.delete();
      var1 = new File("sexmod/custom_models/whitelisted_servers.txt");

      try {
         FileWriter var3 = new FileWriter(var1);
         Throwable var4 = null;

         try {
            for(String var6 : var2) {
               var3.write(var6 + "\n");
            }
         } catch (Throwable var18) {
            var4 = var18;
            throw var18;
         } finally {
            label108: {
               label107: {
                  try {
                     if (var3 == null) {
                        break label108;
                     }

                     if (var4 == null) {
                        break label107;
                     }
                  } catch (Throwable var17) {
                     throw a(var17);
                  }

                  try {
                     var3.close();
                  } catch (Throwable var16) {
                     var4.addSuppressed(var16);
                  }
                  break label108;
               }

               var3.close();
            }

         }
      } catch (IOException var20) {
         var20.printStackTrace();
      }

   }

   public static boolean l(String var0) {
      return f().contains(var0);
   }

   static HashSet<String> f() {
      File var0 = new File("sexmod/custom_models/whitelisted_servers.txt");

      try {
         var0.createNewFile();
      } catch (Exception var17) {
         var17.printStackTrace();
      }

      HashSet var1 = new HashSet();

      try {
         BufferedReader var2 = new BufferedReader(new FileReader(var0));
         Throwable var3 = null;

         try {
            while(true) {
               String var4;
               String var10000 = var4 = var2.readLine();

               try {
                  if (var10000 == null) {
                     return var1;
                  }

                  var1.add(var4);
               } catch (Exception var19) {
                  throw a((Throwable)var19);
               }
            }
         } catch (Throwable var20) {
            var3 = var20;
            throw var20;
         } finally {
            label120: {
               label119: {
                  try {
                     if (var2 == null) {
                        break label120;
                     }

                     if (var3 == null) {
                        break label119;
                     }
                  } catch (Exception var18) {
                     throw a((Throwable)var18);
                  }

                  try {
                     var2.close();
                  } catch (Throwable var16) {
                     var3.addSuppressed(var16);
                  }
                  break label120;
               }

               var2.close();
            }

         }
      } catch (IOException var22) {
         var22.printStackTrace();
         return new HashSet();
      }
   }

   public static float i(String var0) {
      b var1 = (b)c.get(var0);

      try {
         if (var1 == null) {
            return 0.0F;
         }
      } catch (RuntimeException var2) {
         throw a((Throwable)var2);
      }

      return var1.f();
   }

   @SideOnly(Side.CLIENT)
   static void c() {
      for(Map.Entry var1 : c.entrySet()) {
         b var2 = (b)var1.getValue();

         try {
            if (var2 == null) {
               continue;
            }
         } catch (RuntimeException var6) {
            throw a((Throwable)var6);
         }

         ResourceLocation var3 = var2.c();
         ResourceLocation var4 = var2.k();

         try {
            if (var3 != null) {
               GeckoLibCache.getInstance().getGeoModels().remove(var3);
            }
         } catch (RuntimeException var7) {
            throw a((Throwable)var7);
         }

         try {
            if (var4 != null) {
               Minecraft.func_71410_x().field_71446_o.func_147645_c(var4);
            }
         } catch (RuntimeException var5) {
            throw a((Throwable)var5);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   static void a(Level var0, String var1) {
      EntityPlayerSP var2 = Minecraft.func_71410_x().field_71439_g;

      try {
         if (var2 == null) {
            Main.LOGGER.log(var0, var1);
            return;
         }
      } catch (RuntimeException var4) {
         throw a((Throwable)var4);
      }

      TextFormatting var3;
      if (Level.DEBUG.equals(var0)) {
         var3 = TextFormatting.DARK_GREEN;
      } else if (Level.ERROR.equals(var0)) {
         var3 = TextFormatting.RED;
      } else {
         var3 = TextFormatting.WHITE;
      }

      ((EntityPlayer)var2).func_145747_a(new TextComponentString(var3.toString() + var1));
   }

   public static String h() {
      try {
         return Main.proxy instanceof ClientProxy ? d() : "sexmod_custom_models";
      } catch (RuntimeException var0) {
         throw a((Throwable)var0);
      }
   }

   @SideOnly(Side.CLIENT)
   public static String d() {
      String var0 = g();

      try {
         if (var0 == null) {
            return "sexmod/custom_models/singleplayer";
         }
      } catch (RuntimeException var1) {
         throw a((Throwable)var1);
      }

      return "sexmod/custom_models/" + var0;
   }

   @SideOnly(Side.CLIENT)
   @Nullable
   public static String g() {
      Minecraft var0 = Minecraft.func_71410_x();
      ServerData var1 = var0.func_147104_D();

      try {
         if (var1 == null) {
            return null;
         }
      } catch (RuntimeException var4) {
         throw a((Throwable)var4);
      }

      String var2 = var1.field_78845_b;
      int var3 = var2.indexOf(":");
      if (var3 != -1) {
         var2 = var2.substring(0, var3);
      }

      return var2;
   }

   public static int c(boolean var0) {
      b(Level.INFO, "loading up custom models...");
      String var1 = h();
      File var2 = new File(var1);
      var2.mkdirs();
      String[] var3 = var2.list((var0x, var1x) -> (new File(var0x, var1x)).isDirectory());

      try {
         if (var3 == null) {
            b(Level.ERROR, String.format("Something is wrong with the custom models folder at '%s'. Check if it exists, if not - make the directory yourself because Minecraft cannot do it itself for some reason", var2.getAbsolutePath()));
            return -1;
         }
      } catch (RuntimeException var12) {
         throw a((Throwable)var12);
      }

      b(Level.INFO, String.format("found %s custom model(s)", var3.length));
      int var4 = 0;

      for(String var8 : var3) {
         String var9 = a(var8, var1);

         try {
            if (!"".equals(var9)) {
               b(Level.ERROR, var9);
               return -1;
            }
         } catch (RuntimeException var11) {
            throw a((Throwable)var11);
         }

         var9 = a(var8, var1, var0);

         try {
            if (!"".equals(var9)) {
               b(Level.ERROR, var9);
               return -1;
            }
         } catch (RuntimeException var10) {
            throw a((Throwable)var10);
         }

         ++var4;
      }

      b(Level.DEBUG, String.format("successfully registered %s custom models", var4));
      e = true;
      return 0;
   }

   public static String a(String var0, String var1) {
      String var2 = String.format("%s/%s", var1, var0);
      File var3 = new File(String.format("%s/%s.geo.json", var2, var0));
      File var4 = new File(String.format("%s/%s.png", var2, var0));
      File var5 = new File(String.format("%s/%s.cfg", var2, var0));

      try {
         if (!var3.exists()) {
            return String.format("couldn't find model File for '%s'. It should have been at '%s'. Are you sure it exists?", var0, var3.getAbsolutePath());
         }
      } catch (RuntimeException var7) {
         throw a((Throwable)var7);
      }

      try {
         if (!var4.exists()) {
            return String.format("couldn't find texture File for '%s'. It should have been at '%s'. Are you sure it exists?", var0, var4.getAbsolutePath());
         }
      } catch (RuntimeException var8) {
         throw a((Throwable)var8);
      }

      try {
         return !var5.exists() ? String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", var0, var5.getAbsolutePath()) : "";
      } catch (RuntimeException var6) {
         throw a((Throwable)var6);
      }
   }

   @SideOnly(Side.CLIENT)
   static ResourceLocation a(String var0, File var1) throws Exception {
      BufferedImage var2 = ImageIO.read(var1);
      return Minecraft.func_71410_x().field_71446_o.func_110578_a(var0, new DynamicTexture(var2));
   }

   @SideOnly(Side.CLIENT)
   static RawGeoModel a(File var0) throws IOException {
      StringBuilder var1 = new StringBuilder();
      BufferedReader var2 = new BufferedReader(new FileReader(var0));
      Throwable var3 = null;

      try {
         while(true) {
            String var4;
            String var10000 = var4 = var2.readLine();

            try {
               if (var10000 == null) {
                  break;
               }

               var1.append(var4);
            } catch (Throwable var16) {
               throw a(var16);
            }
         }
      } catch (Throwable var17) {
         var3 = var17;
         throw var17;
      } finally {
         label97: {
            label96: {
               try {
                  if (var2 == null) {
                     break label97;
                  }

                  if (var3 == null) {
                     break label96;
                  }
               } catch (Throwable var15) {
                  throw a(var15);
               }

               try {
                  var2.close();
               } catch (Throwable var14) {
                  var3.addSuppressed(var14);
               }
               break label97;
            }

            var2.close();
         }

      }

      String var19 = var1.toString();
      return Converter.fromJsonString(var19);
   }

   public static String a(String var0, String var1, boolean var2) {
      try {
         if (c.get(var0) != null) {
            return String.format("already registered '%s'... honestly, unsure how this could happen lol", var0);
         }
      } catch (IOException var25) {
         throw a((Throwable)var25);
      }

      String var3 = String.format("%s/%s/", var1, var0);
      String var4 = var3 + var0 + ".cfg";
      File var5 = new File(var4);

      try {
         if (!var5.exists()) {
            return String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", var0, var4);
         }
      } catch (IOException var24) {
         throw a((Throwable)var24);
      }

      b var6 = new b(var5, var0);

      try {
         if (var6.h != null) {
            return var6.h;
         }
      } catch (IOException var23) {
         throw a((Throwable)var23);
      }

      String var8 = var3 + var0 + ".png";
      File var7 = new File(var8);

      try {
         if (!var7.exists()) {
            return String.format("The texture for the custom model '%s' couldn't be found at '%s' are you sure it exists?", var0, var8);
         }
      } catch (IOException var20) {
         throw a((Throwable)var20);
      }

      ResourceLocation var9 = null;
      if (var2) {
         try {
            var9 = a(var0, var7);
         } catch (IOException var18) {
            return String.format("The texture for the custom model '%s' at '%s' appears to be corrupted. Try making a new one", var0, var8);
         } catch (Exception var19) {
            return String.format("Couldn't load the texture for the custom model '%s' at '%s'. Maybe try increasing the amount of RAM of ur Minecraft client", var0, var7);
         }
      }

      ResourceLocation var10 = new ResourceLocation("sexmod", var0 + "Model");
      String var12 = var3 + var0 + ".geo.json";
      File var13 = new File(var12);

      try {
         if (!var13.exists()) {
            return String.format("The geo model for the custom model '%s' couldn't be found at '%s' are you sure it exists?", var0, var12);
         }
      } catch (IOException var22) {
         throw a((Throwable)var22);
      }

      if (var2) {
         RawGeoModel var11;
         try {
            var11 = a(var13);
         } catch (IOException var17) {
            return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", var0, var12);
         }

         try {
            RawGeometryTree var14 = RawGeometryTree.parseHierarchy(var11, var10);
            GeoModel var15 = GeoBuilder.getGeoBuilder(var10.func_110624_b()).constructGeoModel(var14);
            GeckoLibCache.getInstance().getGeoModels().put(var10, var15);
         } catch (Exception var16) {
            return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", var0, var12);
         }
      }

      try {
         if (var2) {
            var6.b(var10);
            var6.a(var9);
         }
      } catch (IOException var21) {
         throw a((Throwable)var21);
      }

      c.put(var0, var6);
      b(Level.DEBUG, String.format("successfully registered custom model '%s'", var0));
      return "";
   }

   public static ResourceLocation k(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static ResourceLocation c(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static GeoModel j(String var0) {
      return (GeoModel)GeckoLibCache.getInstance().getGeoModels().get(k(var0));
   }

   public static gw e(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static HashSet<fy> a(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static HashSet<String> g(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static String d(String param0) {
      // $FF: Couldn't be decompiled
   }

   @Nullable
   public static b b(String var0) {
      return (b)c.get(var0);
   }

   public static HashMap<gw, List<String>> a(em param0) {
      // $FF: Couldn't be decompiled
   }

   public static HashMap<String, Float> e() {
      HashMap var0 = new HashMap();

      for(Map.Entry var2 : i().entrySet()) {
         var0.put(var2.getKey(), ((b)var2.getValue()).f());
      }

      return var0;
   }

   private static Throwable a(Throwable var0) {
      return var0;
   }

   @SideOnly(Side.CLIENT)
   public static class a {
      boolean a = false;

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(ClientChatEvent var1) {
         String var2 = var1.getOriginalMessage();

         try {
            if (!"id".equals(var2)) {
               return;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         EntityPlayerSP var3 = Minecraft.func_71410_x().field_71439_g;
         List var4 = var3.field_70170_p.func_72872_a(em.class, ((EntityPlayer)var3).func_174813_aQ().func_186662_g((double)10.0F));
         em var5 = null;

         for(em var7 : var4) {
            if (var5 == null) {
               var5 = var7;
            } else if (((EntityPlayer)var3).func_70032_d(var7) < ((EntityPlayer)var3).func_70032_d(var5)) {
               var5 = var7;
            }
         }

         try {
            if (var5 == null) {
               return;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         ((EntityPlayer)var3).func_146105_b(new TextComponentString(var5.f().toString()), false);
         var1.setCanceled(true);
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(FMLNetworkEvent.ClientConnectedToServerEvent var1) {
         Minecraft var2 = Minecraft.func_71410_x();
         var2.func_152343_a(() -> br.c(true));
         this.a = false;
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(EntityJoinWorldEvent var1) {
         try {
            if (!var1.getEntity().equals(Minecraft.func_71410_x().field_71439_g)) {
               return;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         try {
            if (this.a) {
               return;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }

         try {
            this.a = true;
            if (br.b()) {
               br.a();
            }

         } catch (RuntimeException var2) {
            throw a(var2);
         }
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(FMLNetworkEvent.ClientDisconnectionFromServerEvent var1) {
         Minecraft.func_71410_x().func_152344_a(() -> br.a(true));
         this.a = false;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }

   public static class b {
      gw d;
      HashSet<fy> g;
      HashSet<String> b;
      String k;
      String j;
      boolean c;
      c8 e;
      float m;
      float a;
      ResourceLocation i;
      ResourceLocation f;
      public String h;
      float l;

      public b(File param1, String param2) {
         // $FF: Couldn't be decompiled
      }

      public String b() {
         return this.j;
      }

      public c8 i() {
         return this.e;
      }

      public float g() {
         return this.a;
      }

      public float d() {
         return this.m;
      }

      public gw j() {
         return this.d;
      }

      public HashSet<fy> l() {
         return this.g;
      }

      public String e() {
         return this.k;
      }

      public boolean a() {
         return this.c;
      }

      public HashSet<String> h() {
         return this.b;
      }

      public ResourceLocation k() {
         return this.i;
      }

      public void a(ResourceLocation var1) {
         this.i = var1;
      }

      public ResourceLocation c() {
         return this.f;
      }

      public void b(ResourceLocation var1) {
         this.f = var1;
      }

      public float f() {
         return this.l;
      }

      private static FileNotFoundException a(FileNotFoundException var0) {
         return var0;
      }
   }
}
