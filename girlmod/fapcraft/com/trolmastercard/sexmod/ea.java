package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ea extends GuiScreen {
   static final float j = 100.0F;
   static final float c = 15.0F;
   static final float k = 5.0F;
   static final float l = 0.5F;
   static final float b = 0.5F;
   static final ResourceLocation i = new ResourceLocation("sexmod", "textures/gui/command.png");
   float a = 0.0F;
   float g = 0.0F;
   float e = 0.0F;
   float d = 0.0F;
   float m = 0.0F;
   em f;
   boolean h = false;

   public ea(em var1) {
      this.f = var1;
      this.h = var1 instanceof e3;
   }

   public void func_146281_b() {
      // $FF: Couldn't be decompiled
   }

   void a() {
      try {
         if (this.h) {
            ((e3)this.f).c(Minecraft.func_71410_x().field_71439_g.getPersistentID());
         }

      } catch (NullPointerException var1) {
         throw a(var1);
      }
   }

   void b() {
      ((e3)this.f).b(Minecraft.func_71410_x().field_71439_g.getPersistentID());
   }

   void c() {
      try {
         if (this.f.ae() != null) {
            return;
         }
      } catch (NullPointerException var1) {
         throw a(var1);
      }

      this.f.b(fp.START_THROWING);
   }

   public void func_146282_l() throws IOException {
      // $FF: Couldn't be decompiled
   }

   public void func_73863_a(int var1, int var2, float var3) {
      super.func_73863_a(var1, var2, var3);
      GL11.glEnable(3042);
      OpenGlHelper.func_148821_a(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);

      try {
         this.a = Math.min(1.0F, this.a + this.field_146297_k.func_193989_ak() / 5.0F);
      } catch (NullPointerException var7) {
      }

      float var4 = (float)this.a((double)this.a);
      float var5 = (1.0F - var4) * 100.0F;

      ea var10000;
      float var10001;
      byte var10002;
      label78: {
         try {
            var10000 = this;
            var10001 = this.g;
            if (var1 < this.field_146294_l / 2) {
               var10002 = 1;
               break label78;
            }
         } catch (NullPointerException var12) {
            throw a(var12);
         }

         var10002 = -1;
      }

      label71: {
         try {
            var10000.g = var10001 + (float)var10002 * this.field_146297_k.func_193989_ak();
            var10000 = this;
            var10001 = this.e;
            if (var1 > this.field_146294_l / 2) {
               var10002 = 1;
               break label71;
            }
         } catch (NullPointerException var11) {
            throw a(var11);
         }

         var10002 = -1;
      }

      label64: {
         try {
            var10000.e = var10001 + (float)var10002 * this.field_146297_k.func_193989_ak();
            var10000 = this;
            var10001 = this.d;
            if (var2 < this.field_146295_m / 2 - 1) {
               var10002 = 1;
               break label64;
            }
         } catch (NullPointerException var10) {
            throw a(var10);
         }

         var10002 = -1;
      }

      label57: {
         try {
            var10000.d = var10001 + (float)var10002 * this.field_146297_k.func_193989_ak();
            var10000 = this;
            var10001 = this.m;
            if (var2 > this.field_146295_m / 2) {
               var10002 = 1;
               break label57;
            }
         } catch (NullPointerException var9) {
            throw a(var9);
         }

         var10002 = -1;
      }

      try {
         var10000.m = var10001 + (float)var10002 * this.field_146297_k.func_193989_ak();
         this.g = be.b(this.g, 0.0F, 1.0F);
         this.e = be.b(this.e, 0.0F, 1.0F);
         this.d = be.b(this.d, 0.0F, 1.0F);
         this.m = be.b(this.m, 0.0F, 1.0F);
         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b((float)this.field_146294_l / 2.0F, (float)this.field_146295_m / 2.0F, 0.0F);
         GlStateManager.func_179152_a(var4, var4, var4);
         this.field_146297_k.field_71446_o.func_110577_a(i);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(1.0F + this.g * 0.5F, 1.0F + this.g * 0.5F, 1.0F);
         this.func_175174_a(-62.0F + var5 - this.g * 15.0F, var5 - 32.0F, 0, 0, 64, 64);
         this.func_175174_a(-62.0F + var5 - this.g * 15.0F, var5 - 32.0F, 64, 128, 64, 64);
         GlStateManager.func_179121_F();
         if (!this.h) {
            GlStateManager.func_179121_F();
            GL11.glDisable(3042);
            return;
         }
      } catch (NullPointerException var6) {
         throw a(var6);
      }

      try {
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(1.0F - this.e, 1.0F - this.e, 1.0F);
         this.func_175174_a(-2.0F - var5 + this.e * 32.0F, -var5 - 32.0F, 0, 0, 64, 64);
         this.func_175174_a(-2.0F - var5 + this.e * 32.0F, -var5 - 32.0F, 0, 128, 64, 64);
         GlStateManager.func_179121_F();
         if (this.e > 0.0F) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(-1.0F + this.e + 1.0F + this.d * 0.5F, -1.0F + this.e + 1.0F + this.d * 0.5F, 1.0F);
            this.func_175174_a(-2.0F - var5 + this.d * 5.0F, -var5 - 64.0F - this.d * 5.0F / 2.0F, 0, 0, 64, 64);
            this.func_175174_a(-2.0F - var5 + this.d * 5.0F, -var5 - 64.0F - this.d * 5.0F / 2.0F, 128, 128, 64, 64);
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(-1.0F + this.e + 1.0F + this.m * 0.5F, -1.0F + this.e + 1.0F + this.m * 0.5F, 1.0F);
            this.func_175174_a(-2.0F - var5 + this.m * 5.0F, -var5 + this.m * 5.0F / 2.0F, 0, 0, 64, 64);
            this.func_175174_a(-2.0F - var5 + this.m * 5.0F, -var5 + this.m * 5.0F / 2.0F, 192, 128, 64, 64);
            GlStateManager.func_179121_F();
         }
      } catch (NullPointerException var8) {
         throw a(var8);
      }

      GlStateManager.func_179121_F();
      GL11.glDisable(3042);
   }

   double a(double var1) {
      double var3 = 1.70158;
      double var5 = var3 + (double)1.0F;
      return (double)1.0F + var5 * Math.pow(var1 - (double)1.0F, (double)3.0F) + var3 * Math.pow(var1 - (double)1.0F, (double)2.0F);
   }

   public boolean func_73868_f() {
      return false;
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
