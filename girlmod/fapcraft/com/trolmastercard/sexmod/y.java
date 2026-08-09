package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class y {
   public static final int a = 3;

   @SideOnly(Side.CLIENT)
   public static BufferedImage a(UUID var0) throws IOException {
      try {
         URL var1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + var0.toString().replace("-", ""));
         BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.openStream()));
         String var3 = (String)var2.lines().collect(Collectors.joining());
         int var4 = var3.indexOf("\"value\" : ");
         int var5 = var4 + 11;
         StringBuilder var6 = new StringBuilder();
         int var7 = 0;

         try {
            while(var3.charAt(var5 + var7) != '"') {
               var6.append(var3.charAt(var5 + var7));
               ++var7;
            }
         } catch (Exception var13) {
            throw a(var13);
         }

         String var15 = new String(Base64.getDecoder().decode(var6.toString()));
         int var8 = var15.indexOf("\"url\" : ");
         int var9 = var8 + 9;
         StringBuilder var10 = new StringBuilder();
         int var11 = 0;

         try {
            while(var15.charAt(var9 + var11) != '"') {
               var10.append(var15.charAt(var9 + var11));
               ++var11;
            }
         } catch (Exception var12) {
            throw a(var12);
         }

         URL var16 = new URL(var10.toString());
         return ImageIO.read(var16);
      } catch (Exception var14) {
         return ImageIO.read(Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("sexmod", "textures/player/steve.png")).func_110527_b());
      }
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
