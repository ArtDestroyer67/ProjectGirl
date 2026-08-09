package com.trolmastercard.sexmod;

import java.util.HashSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d0 extends dm {
   public d0(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void c() {
      GlStateManager.func_179137_b((double)0.0F, (double)-1.0F, -0.05);
      GlStateManager.func_179152_a(0.65F, 0.65F, 0.65F);
   }

   protected void a(boolean var1) {
      try {
         super.a(var1);
         if (var1) {
            GlStateManager.func_179137_b(0.15, (double)0.0F, (double)0.0F);
         }

      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   public HashSet<String> a() {
      return new HashSet<String>() {
         {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
            this.add("leaf7");
            this.add("leaf8");
         }
      };
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
