package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class bu extends GeoLayerRenderer {
   private static final ResourceLocation b = new ResourceLocation("textures/entity/elytra.png");
   private final ModelElytra a = new ModelElytra();

   public bu(IGeoRenderer var1) {
      super(var1);
   }

   public void render(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, Color var8) {
      try {
         if (!(var1 instanceof e2)) {
            return;
         }
      } catch (RuntimeException var16) {
         throw a(var16);
      }

      e2 var9 = (e2)var1;
      ItemStack var10 = (ItemStack)var9.func_184212_Q().func_187225_a(e2.T);
      EntityPlayer var11 = null;
      if (var9 instanceof ei) {
         UUID var12 = ((ei)var9).m();
         if (var12 != null) {
            var11 = var1.field_70170_p.func_152378_a(var12);
         }
      }

      try {
         if (var10.func_77973_b() != Items.field_185160_cR) {
            return;
         }
      } catch (RuntimeException var15) {
         throw a(var15);
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179147_l();
      GlStateManager.func_187401_a(SourceFactor.ONE, DestFactor.ZERO);
      Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110577_a(b);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, 0.0F, 0.125F);
      float var17 = this.a();

      ModelElytra var10000;
      float var10001;
      float var10002;
      float var10003;
      float var10004;
      float var10005;
      float var10006;
      Object var10007;
      label42: {
         try {
            var10000 = this.a;
            var10001 = var2;
            var10002 = var3;
            var10003 = var5;
            var10004 = var6;
            var10005 = var7;
            var10006 = var17;
            if (var11 == null) {
               var10007 = var1;
               break label42;
            }
         } catch (RuntimeException var14) {
            throw a(var14);
         }

         var10007 = var11;
      }

      label35: {
         try {
            var10000.func_78087_a(var10001, var10002, var10003, var10004, var10005, var10006, (Entity)var10007);
            var10000 = this.a;
            if (var11 == null) {
               var19 = var1;
               break label35;
            }
         } catch (RuntimeException var13) {
            throw a(var13);
         }

         var19 = var11;
      }

      var10000.func_78088_a((Entity)var19, var2, var3, var5, var6, var7, var17);
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
   }

   public float a() {
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      GlStateManager.func_179109_b(0.0F, -1.501F, 0.0F);
      return 0.0625F;
   }

   public void func_177141_a(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
   }

   public boolean func_177142_b() {
      return false;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
