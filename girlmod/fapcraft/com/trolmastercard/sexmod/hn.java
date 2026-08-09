package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class hn extends GeoItemRenderer<c7> {
   ItemStack a = null;

   public hn() {
      super(new f6());
   }

   public void a(c7 var1, ItemStack var2) {
      this.a = var2;
      super.render(var1, var2);
   }

   public void renderRecursively(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6) {
      String var7 = var2.getName();
      if ("shell".equals(var7)) {
         var3 = (float)cp.b.getRed() / 255.0F;
         var4 = (float)cp.b.getGreen() / 255.0F;
         var5 = (float)cp.b.getBlue() / 255.0F;
      }

      if ("colorSpots".equals(var7)) {
         Vec3i var8 = this.a(this.a).getMainColor();
         var3 = (float)var8.func_177958_n() / 255.0F;
         var4 = (float)var8.func_177956_o() / 255.0F;
         var5 = (float)var8.func_177952_p() / 255.0F;
      }

      super.renderRecursively(var1, var2, var3, var4, var5, var6);
   }

   EyeAndKoboldColor a(ItemStack var1) {
      return EyeAndKoboldColor.getColorByWoolId(var1.func_77960_j());
   }
}
