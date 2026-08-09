package com.trolmastercard.sexmod;

import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class cp extends GeoEntityRenderer<i> {
   public static final Color b = new Color(223, 206, 155);
   i a;

   public cp(RenderManager var1, AnimatedGeoModel<i> var2) {
      super(var1, var2);
   }

   public void a(GeoModel var1, i var2, float var3, float var4, float var5, float var6, float var7) {
      this.a = var2;
      super.render(var1, var2, var3, var4, var5, var6, var7);
   }

   public void renderRecursively(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6) {
      String var7 = var2.getName();
      if ("shell".equals(var7)) {
         var3 = (float)b.getRed() / 255.0F;
         var4 = (float)b.getGreen() / 255.0F;
         var5 = (float)b.getBlue() / 255.0F;
      }

      if ("colorSpots".equals(var7)) {
         Vec3i var8 = EyeAndKoboldColor.safeValueOf((String)this.a.func_184212_Q().func_187225_a(i.b)).getMainColor();
         var3 = (float)var8.func_177958_n() / 255.0F;
         var4 = (float)var8.func_177956_o() / 255.0F;
         var5 = (float)var8.func_177952_p() / 255.0F;
      }

      super.renderRecursively(var1, var2, var3, var4, var5, var6);
   }
}
