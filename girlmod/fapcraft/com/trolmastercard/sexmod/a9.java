package com.trolmastercard.sexmod;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class a9 extends AnimatedGeoModel<ap> {
   ResourceLocation a = null;

   public ResourceLocation b(ap var1) {
      return new ResourceLocation("sexmod", "geo/allie/lamp.geo.json");
   }

   public ResourceLocation c(ap var1) {
      try {
         if (this.a != null) {
            return this.a;
         }
      } catch (IOException var6) {
         throw a(var6);
      }

      try {
         Minecraft var2 = Minecraft.func_71410_x();
         BufferedImage var3 = y.a(var2.field_71439_g.getPersistentID());
         Graphics var4 = var3.getGraphics();
         var4.setColor(new Color(185, 254, 255));
         var4.fillRect(0, 0, 2, 2);
         var4.setColor(new Color(255, 255, 255));
         var4.fillRect(2, 0, 1, 2);
         var4.setColor(new Color(0, 0, 0));
         var4.fillRect(3, 0, 1, 2);
         this.a = var2.field_71446_o.func_110578_a("alliesLamp", new DynamicTexture(var3));
      } catch (IOException var5) {
         var5.printStackTrace();
         this.a = new ResourceLocation("sexmod", "textures/entity/allie/lamp.png");
      }

      return this.a;
   }

   public ResourceLocation a(ap var1) {
      return new ResourceLocation("sexmod", "animations/allie/lamp.animation.json");
   }

   private static IOException a(IOException var0) {
      return var0;
   }
}
