package com.trolmastercard.sexmod;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class dm extends d_ {
   public static boolean v = false;
   ItemStack s;
   ItemStack x;
   boolean r;
   boolean u;
   protected ei w;
   protected float y;
   float t;

   public dm(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2, (double)0.0F);
      this.s = ItemStack.field_190927_a;
      this.x = ItemStack.field_190927_a;
      this.r = false;
      this.u = false;
      this.t = 0.0F;
   }

   public void func_76979_b(Entity var1, double var2, double var4, double var6, float var8, float var9) {
   }

   boolean a(em var1) {
      try {
         if (var1.h()) {
            return true;
         }
      } catch (IllegalStateException var3) {
         throw b(var3);
      }

      boolean var2 = v;
      v = false;
      return var2;
   }

   public void a(em var1, double var2, double var4, double var6, float var8, float var9) {
      try {
         if (!this.a(var1)) {
            return;
         }
      } catch (IllegalStateException var15) {
         throw b(var15);
      }

      ei var10 = (ei)var1;

      try {
         if (var10.m() == null) {
            return;
         }
      } catch (IllegalStateException var14) {
         throw b(var14);
      }

      EntityPlayer var11 = Minecraft.func_71410_x().field_71439_g.field_70170_p.func_152378_a(var10.m());

      try {
         if (var11 == null) {
            return;
         }
      } catch (IllegalStateException var12) {
         throw b(var12);
      }

      try {
         this.s = var11.func_184614_ca();
         this.x = var11.func_184592_cb();
         this.u = var10.ah;
         this.r = var10.ad;
         this.w = (ei)var1;
         this.y = var9;
         var10.f(var11);
         if (this.a(var11, var1)) {
            this.func_147906_a(var1, var11.func_70005_c_(), var2, var4 + (double)var10.i(), var6, 300);
         }
      } catch (IllegalStateException var13) {
         throw b(var13);
      }

      super.a(var1, var2, var4, var6, var8, var9);
   }

   public Entity c(em var1) {
      try {
         if (!(var1 instanceof ei)) {
            return var1;
         }
      } catch (IllegalStateException var5) {
         throw b(var5);
      }

      ei var2 = (ei)var1;
      EntityPlayer var3 = var2.k();

      try {
         return (Entity)(var3 == null ? var1 : var3);
      } catch (IllegalStateException var4) {
         throw b(var4);
      }
   }

   boolean a(EntityPlayer var1, em var2) {
      try {
         if (var1.getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
            return false;
         }
      } catch (IllegalStateException var6) {
         throw b(var6);
      }

      fp var3 = var2.y();

      try {
         if (var3 == null) {
            return true;
         }
      } catch (IllegalStateException var4) {
         throw b(var4);
      }

      boolean var10000;
      try {
         if (!var3.hideNameTag) {
            var10000 = true;
            return var10000;
         }
      } catch (IllegalStateException var5) {
         throw b(var5);
      }

      var10000 = false;
      return var10000;
   }

   protected void a(String var1, GeoBone var2) {
   }

   protected void a(String var1, GeoBone var2, ei var3, BufferBuilder var4) {
   }

   public void renderRecursively(BufferBuilder param1, GeoBone param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   boolean a() {
      // $FF: Couldn't be decompiled
   }

   void a(BufferBuilder var1, GeoBone var2, Color var3) {
      GlStateManager.func_179094_E();
      Tessellator.func_178181_a().func_78381_a();
      com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, var2);
      GL11.glEnable(2896);
      this.c();
      (new bu(this)).render(this.j, this.j.field_184619_aG, this.j.field_70721_aZ, this.y, 0.0F, 0.0F, 0.0F, var3);
      this.func_110776_a((ResourceLocation)Objects.requireNonNull(this.getEntityTexture(this.j)));
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      GlStateManager.func_179147_l();
      GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      GL11.glDisable(2896);
      GlStateManager.func_179121_F();
   }

   protected void c() {
   }

   void a(BufferBuilder param1, GeoBone param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   protected void a(boolean var1, ItemStack var2) {
      float var10000;
      label16: {
         try {
            if (var1) {
               var10000 = 200.0F;
               break label16;
            }
         } catch (IllegalStateException var3) {
            throw b(var3);
         }

         var10000 = 90.0F;
      }

      GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
   }

   protected void a(boolean var1) {
      GlStateManager.func_179114_b(20.0F, 1.0F, 0.0F, 0.0F);
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   private static IllegalStateException b(IllegalStateException var0) {
      return var0;
   }
}
