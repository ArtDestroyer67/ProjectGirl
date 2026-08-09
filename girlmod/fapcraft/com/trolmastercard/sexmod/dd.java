package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class dd extends GeoItemRenderer<aj> {
   static final Vec3d a = new Vec3d((double)0.0F, (double)1.0F, (double)0.0F);

   public dd() {
      super(new a2());
   }

   public void a(aj var1, ItemStack var2) {
      try {
         if (ad.b[0] == 0.0F) {
            GL11.glDisable(2896);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      super.render(var1, var2);
      GL11.glEnable(2896);
   }

   public void renderCube(BufferBuilder param1, GeoCube param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
