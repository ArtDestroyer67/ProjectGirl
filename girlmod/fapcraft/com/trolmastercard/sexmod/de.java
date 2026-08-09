package com.trolmastercard.sexmod;

import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class de extends d9 {
   public de(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected Vec3i a(String param1) {
      // $FF: Couldn't be decompiled
   }

   protected Vector4f a(String var1, float var2, float var3, float var4) {
      if ("mouth".equals(var1)) {
         String[] var5 = e4.a(this.j);
         int var6 = Integer.parseInt(var5[7]);

         try {
            if (var6 == 1) {
               return new Vector4f(var2, var3, var4, -0.078125F);
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }
      }

      return super.a(var1, var2, var3, var4);
   }

   protected void d() {
      float var1 = 0.25F - (Float)this.j.func_184212_Q().func_187225_a(e7.aA);
      GlStateManager.func_179152_a(1.0F - var1, 1.0F - var1, 1.0F - var1);
   }

   protected void b() {
      float var1 = 0.25F - (Float)this.j.func_184212_Q().func_187225_a(e7.aA);
      double var2 = (double)1.0F / ((double)1.0F - (double)var1);
      GlStateManager.func_179139_a(var2, var2, var2);
   }

   protected void c() {
      GlStateManager.func_179137_b((double)0.0F, (double)-0.8F, 0.05);
      GlStateManager.func_179139_a((double)0.5F, (double)0.5F, (double)0.5F);
   }

   protected void a(boolean param1, ItemStack param2) {
      // $FF: Couldn't be decompiled
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
