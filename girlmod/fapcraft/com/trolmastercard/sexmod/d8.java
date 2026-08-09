package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d8 extends d_ {
   public d8(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   public void a(GeoModel param1, em param2, float param3, float param4, float param5, float param6, float param7) {
      // $FF: Couldn't be decompiled
   }

   protected void a(double var1, double var3, double var5) {
      try {
         if (this.j.y() == fp.NULL) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (this.j.h()) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (this.j.y().hideNameTag) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (i.func_175598_ae().field_78734_h == null) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      this.func_147906_a(this.j, this.j.ab(), var1, var3 + (double)this.j.i(), var5, 300);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
