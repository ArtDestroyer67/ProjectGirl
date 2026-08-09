package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class cn {
   Minecraft f;
   float g = 2.0F;
   boolean c = false;
   private static final ResourceLocation e = new ResourceLocation("textures/map/map_background.png");
   at d;
   ResourceLocation h;
   Vec3i b;
   float a = 0.0F;

   @SubscribeEvent
   public void a(RenderSpecificHandEvent param1) {
      // $FF: Couldn't be decompiled
   }

   void a(ItemStack param1, float param2, AbstractClientPlayer param3, float param4, float param5) {
      // $FF: Couldn't be decompiled
   }

   void a(EnumHandSide param1, float param2, float param3, ItemStack param4) {
      // $FF: Couldn't be decompiled
   }

   void a(ItemStack var1, AbstractClientPlayer var2, float var3, float var4) {
      float var5 = var2.field_70127_C + (var2.field_70125_A - var2.field_70127_C) * var4;
      float var6 = MathHelper.func_76129_c(var3);
      float var7 = -0.2F * MathHelper.func_76126_a(var3 * (float)Math.PI);
      float var8 = -0.4F * MathHelper.func_76126_a(var6 * (float)Math.PI);
      GlStateManager.func_179109_b(0.0F, -var7 / 2.0F, var8);
      float var9 = this.a(var5);
      GlStateManager.func_179109_b(0.0F, 0.04F + (this.g - 1.0F) * -1.2F + var9 * -0.5F, -0.72F);
      GlStateManager.func_179114_b(var9 * -85.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179129_p();
      GlStateManager.func_179094_E();
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      this.a(EnumHandSide.RIGHT);
      this.a(EnumHandSide.LEFT);
      GlStateManager.func_179121_F();
      GlStateManager.func_179089_o();
      float var10 = MathHelper.func_76126_a(var6 * (float)Math.PI);
      GlStateManager.func_179114_b(var10 * 20.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
      this.a(var1);
      GlStateManager.func_179145_e();
   }

   void a(ItemStack var1) {
      GlStateManager.func_179117_G();
      GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179152_a(0.38F, 0.38F, 0.38F);
      GlStateManager.func_179140_f();
      this.f.func_110434_K().func_110577_a(e);
      Tessellator var2 = Tessellator.func_178181_a();
      BufferBuilder var3 = var2.func_178180_c();
      GlStateManager.func_179109_b(-0.5F, -0.5F, 0.0F);
      GlStateManager.func_179152_a(0.0078125F, 0.0078125F, 0.0078125F);
      var3.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      var3.func_181662_b((double)-7.0F, (double)135.0F, (double)0.0F).func_187315_a((double)0.0F, (double)1.0F).func_181675_d();
      var3.func_181662_b((double)135.0F, (double)135.0F, (double)0.0F).func_187315_a((double)1.0F, (double)1.0F).func_181675_d();
      var3.func_181662_b((double)135.0F, (double)-7.0F, (double)0.0F).func_187315_a((double)1.0F, (double)0.0F).func_181675_d();
      var3.func_181662_b((double)-7.0F, (double)-7.0F, (double)0.0F).func_187315_a((double)0.0F, (double)0.0F).func_181675_d();
      var2.func_78381_a();
      MapData var4 = ((ItemMap)var1.func_77973_b()).func_77873_a(var1, this.f.field_71441_e);

      try {
         if (var4 != null) {
            this.f.field_71460_t.func_147701_i().func_148250_a(var4, false);
         }
      } catch (RuntimeException var5) {
         throw a((Exception)var5);
      }

      GlStateManager.func_179124_c((float)this.b.func_177958_n() / 255.0F, (float)this.b.func_177956_o() / 255.0F, (float)this.b.func_177952_p() / 255.0F);
   }

   private void a(EnumHandSide var1) {
      float var10000;
      label30: {
         try {
            GlStateManager.func_179094_E();
            if (var1 == EnumHandSide.RIGHT) {
               var10000 = 1.0F;
               break label30;
            }
         } catch (RuntimeException var4) {
            throw a((Exception)var4);
         }

         var10000 = -1.0F;
      }

      float var2 = var10000;

      label22: {
         try {
            GlStateManager.func_179114_b(92.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(45.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(var2 * -41.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179109_b(var2 * 0.3F, -1.1F, 0.45F);
            if (var1 == EnumHandSide.RIGHT) {
               GlStateManager.func_179109_b(0.63F, 0.36F, 0.0F);
               break label22;
            }
         } catch (RuntimeException var3) {
            throw a((Exception)var3);
         }

         GlStateManager.func_179109_b(1.6F, 0.35F, 0.0F);
      }

      Minecraft.func_71410_x().func_110434_K().func_110577_a(this.h);
      this.d.a().func_78785_a(0.175F);
      GlStateManager.func_179121_F();
   }

   private float a(float var1) {
      float var2 = 1.0F - var1 / 45.0F + 0.1F;
      var2 = MathHelper.func_76131_a(var2, 0.0F, 1.0F);
      var2 = -MathHelper.func_76134_b(var2 * (float)Math.PI) * 0.5F + 0.5F;
      return var2;
   }

   void a(float var1, float var2) {
      GlStateManager.func_179129_p();
      GlStateManager.func_179094_E();
      this.a(this.g, var1, EnumHandSide.RIGHT);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(this.h);
      this.d.a().func_78785_a(0.175F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179089_o();
      GlStateManager.func_179121_F();
   }

   private void a(float var1, float var2, EnumHandSide var3) {
      boolean var10000;
      label30: {
         try {
            if (var3 != EnumHandSide.LEFT) {
               var10000 = true;
               break label30;
            }
         } catch (RuntimeException var13) {
            throw a((Exception)var13);
         }

         var10000 = false;
      }

      boolean var4 = var10000;

      label22: {
         try {
            if (var4) {
               var14 = 1.0F;
               break label22;
            }
         } catch (RuntimeException var12) {
            throw a((Exception)var12);
         }

         var14 = -1.0F;
      }

      float var5 = var14;
      float var6 = MathHelper.func_76129_c(var2);
      float var7 = -0.3F * MathHelper.func_76126_a(var6 * (float)Math.PI);
      float var8 = 0.4F * MathHelper.func_76126_a(var6 * ((float)Math.PI * 2F));
      float var9 = -0.4F * MathHelper.func_76126_a(var2 * (float)Math.PI);
      GlStateManager.func_179109_b(var5 * (var7 + 0.64000005F), var8 + -0.6F + var1 * -0.6F, var9 + -0.71999997F);
      GlStateManager.func_179114_b(var5 * 45.0F, 0.0F, 1.0F, 0.0F);
      float var10 = MathHelper.func_76126_a(var2 * var2 * (float)Math.PI);
      float var11 = MathHelper.func_76126_a(var6 * (float)Math.PI);
      GlStateManager.func_179114_b(var5 * var11 * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(var5 * var10 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179109_b(var5 * -1.0F, 3.6F, 3.5F);
      GlStateManager.func_179114_b(var5 * 120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(var5 * -135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179109_b(var5 * 5.6F, 0.0F, 0.0F);
      GlStateManager.func_179109_b(0.5F, 1.1F, 0.0F);
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
