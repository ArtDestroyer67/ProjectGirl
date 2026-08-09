package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class fi extends Render<gi> {
   static final double b = 0.1896224320030116;
   static final double d = (double)-0.5F;
   static final double c = 0.08742380916962415;
   private static final ResourceLocation a = new ResourceLocation("textures/particle/particles.png");

   public fi(RenderManager var1) {
      super(var1);
   }

   public void a(gi param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   @Nullable
   protected ResourceLocation a(gi var1) {
      return a;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
