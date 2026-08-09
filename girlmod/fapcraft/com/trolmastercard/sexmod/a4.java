package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class a4 implements LayerRenderer<ay> {
   private final bp b;
   private final ModelBase a = new ModelSlime(0);

   public a4(bp var1) {
      this.b = var1;
   }

   public void a(ay var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      try {
         if (!var1.func_82150_aj()) {
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179108_z();
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            this.a.func_178686_a(this.b.func_177087_b());
            this.a.func_78088_a(var1, var2, var3, var5, var6, var7, var8);
            GlStateManager.func_179084_k();
            GlStateManager.func_179133_A();
         }

      } catch (RuntimeException var9) {
         throw a(var9);
      }
   }

   public boolean func_177142_b() {
      return true;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
