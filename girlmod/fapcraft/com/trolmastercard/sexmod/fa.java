package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class fa extends GeoItemRenderer<hy> {
   private static final ResourceLocation c = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   private final e8 q = new e8();
   static final float p = 10.0F;
   static final float f = 1.5F;
   static final float m = 0.175F;
   static final float r = 0.1F;
   static final float g = 0.04F;
   static final float d = 8.0F;
   static final float i = 6.0F;
   static final float a = 1.3F;
   static final Vector2f[] l = new Vector2f[]{new Vector2f(1.0F, 0.0F), new Vector2f(0.0F, 1.0F), new Vector2f(0.0F, 0.0F), new Vector2f(0.5F, 0.5F), new Vector2f(0.75F, 0.25F), new Vector2f(0.25F, 0.75F), new Vector2f(0.25F, 0.75F)};
   static boolean o = false;
   Minecraft e = Minecraft.func_71410_x();
   Vector2f j;
   double b = (double)0.0F;
   EntityPlayer k;
   ItemStack h;
   static HashMap<ItemStack, Vector3f> n = new HashMap();

   public fa() {
      super(new bc());
   }

   public static boolean b() {
      return o;
   }

   public static void a() {
      boolean var10000;
      label16: {
         try {
            if (!o) {
               var10000 = true;
               break label16;
            }
         } catch (RuntimeException var0) {
            throw a(var0);
         }

         var10000 = false;
      }

      o = var10000;
   }

   public void a(hy var1, ItemStack var2) {
      EntityPlayer var3 = null;

      for(EntityPlayer var5 : this.e.field_71441_e.field_73010_i) {
         if (var5.field_71071_by.field_70462_a.contains(var2)) {
            var3 = var5;
            break;
         }

         if (var5.field_71071_by.field_184439_c.contains(var2)) {
            var3 = var5;
            break;
         }
      }

      if (var3 != null) {
         double var11 = var3.field_70165_t - var3.field_70142_S;
         double var6 = var3.field_70161_v - var3.field_70136_U;
         double var8 = (Math.PI / 180D) * (double)var3.field_70177_z;
         this.j = new Vector2f((float)(var11 * Math.cos(var8) + var6 * Math.sin(var8)), (float)(-var11 * Math.sin(var8) + var6 * Math.cos(var8)));
      } else {
         this.j = new Vector2f(0.0F, 0.0F);
      }

      try {
         if (!Minecraft.func_71410_x().func_147113_T()) {
            this.b = (double)((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa + this.e.func_184121_ak());
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      this.h = var2;
      this.k = var3;
      super.render(var1, var2);
   }

   public void renderRecursively(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6) {
      if ("staff".equals(var2.getName())) {
         GlStateManager.func_179094_E();
         Tessellator.func_178181_a().func_78381_a();
         com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, var2);
         GlStateManager.func_179137_b((double)0.0F, (double)1.5F + 0.001 * Math.sin(0.005 * this.b) + 0.001, (double)0.0F);
         Vector3f var7 = (Vector3f)n.get(this.h);
         GlStateManager.func_179139_a(this.d(), this.d(), this.d());
         if (var7 == null) {
            var7 = new Vector3f(0.0F, 0.0F, 0.0F);
         }

         Vector3f var10000;
         Vector3f var10001;
         Vector3f var10002;
         float var10003;
         float var10004;
         label25: {
            try {
               var10000 = var7;
               var10001 = new Vector3f;
               var10002 = var10001;
               var10003 = this.j.x;
               if (this.k == null) {
                  var10004 = 0.0F;
                  break label25;
               }
            } catch (RuntimeException var8) {
               throw a(var8);
            }

            var10004 = (float)(this.k.field_70163_u - this.k.field_70137_T);
         }

         var10002.<init>(var10003, var10004, this.j.y);
         var10000.add(var10001);
         GlStateManager.func_179114_b(var7.z * 10.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(var7.x * 10.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(-var7.y * 10.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179114_b((float)(this.b * (double)0.1F), 1.0F, 1.0F, 1.0F);
         n.put(this.h, var7);
         this.e.func_110434_K().func_110577_a(c);
         this.q.func_78088_a(Minecraft.func_71410_x().field_71439_g, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GlStateManager.func_179121_F();
         if (this.k != null) {
            this.c();
         }

         this.e.func_110434_K().func_110577_a((new bc()).a((hy)null));
         var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      }

      super.renderRecursively(var1, var2, var3, var4, var5, var6);
   }

   void c() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();

      for(Vector4d var4 : ff.aY) {
         var1.add((int)var4.getW());
         var2.add(new Vec3d(var4.getX(), var4.getY(), var4.getZ()));
      }

      try {
         if (var1.size() == 0) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (o) {
            this.a((List)var1, (List)var2);
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      this.a(var1);
   }

   void a(List<Integer> var1, List<Vec3d> var2) {
      for(int var3 = 0; var3 < var1.size(); ++var3) {
         float var4 = b6.a(this.k.field_70758_at, this.k.field_70759_as, this.e.func_184121_ak());
         float var5 = b6.a(this.k.field_70127_C, this.k.field_70125_A, this.e.func_184121_ak());
         Vec3d var6 = b6.a(new Vec3d(this.k.field_70169_q, this.k.field_70167_r + (double)this.k.func_70047_e(), this.k.field_70166_s), this.k.func_174791_d().func_72441_c((double)0.0F, (double)this.k.func_70047_e(), (double)0.0F), (double)this.e.func_184121_ak());
         Vec3d var7 = var6.func_178788_d((Vec3d)var2.get(var3));
         var7 = ck.a(var7, -var5, var4);
         double var8 = Math.abs(var7.field_72450_a) + Math.abs(var7.field_72449_c) + Math.abs(var7.field_72448_b);
         double var10 = -var7.field_72450_a / var8;
         double var12 = -var7.field_72448_b / var8;
         double var14 = var7.field_72449_c / var8;
         var10 = this.a(var10);
         var12 = this.a(var12);
         var14 = this.a(var14);
         var10 *= (double)1.3F;
         var12 *= (double)1.3F;
         var14 *= (double)1.3F;
         this.b((Integer)var1.get(var3), (float)var10, (float)var12, (float)var14);
      }

   }

   void a(List<Integer> var1) {
      float var2 = 1.0F / (float)var1.size();
      float var3 = 0.0F;

      for(int var4 = 0; var4 < var1.size(); ++var4) {
         var3 += var2;
         this.a((Integer)var1.get(var4), 1.0F - var3, 0.0F + var3, (float)b6.b((double)0.8F, (double)1.2F, (double)var4 / (double)var1.size()));
      }

   }

   double a(double var1) {
      return var1 * Math.sqrt((double)1.0F - var1 * var1 / (double)2.0F);
   }

   double d() {
      return (double)0.175F + 0.025 * Math.sin(0.005 * this.b) + 0.025;
   }

   void a(int var1, float var2, float var3, float var4) {
      this.a(new ItemStack(Blocks.field_150325_L, 1, var1), var2, var3, var4);
   }

   void b(int var1, float var2, float var3, float var4) {
      this.b(new ItemStack(Blocks.field_150325_L, 1, var1), var2, var3, var4);
   }

   void b(ItemStack var1, float var2, float var3, float var4) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179137_b((double)0.0F, (double)1.5F + 0.001 * Math.sin(0.005 * this.b) + 0.001, (double)0.0F);
      GlStateManager.func_179152_a(0.04F, 0.04F, 0.04F);
      GlStateManager.func_179109_b(var2 * 6.0F, var3 * 6.0F, var4 * 6.0F);
      this.e.func_175597_ag().func_178099_a(Minecraft.func_71410_x().field_71439_g, var1, TransformType.NONE);
      GlStateManager.func_179121_F();
   }

   void a(ItemStack var1, float var2, float var3, float var4) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179137_b((double)0.0F, (double)1.5F + 0.001 * Math.sin(0.005 * this.b) + 0.001, (double)0.0F);
      GlStateManager.func_179152_a(0.04F, 0.04F, 0.04F);
      GlStateManager.func_179114_b((float)(this.b * (double)8.0F * (double)var4), 0.0F, var2, var3);
      GlStateManager.func_179109_b(6.0F, 0.0F, 0.0F);
      this.e.func_175597_ag().func_178099_a(Minecraft.func_71410_x().field_71439_g, var1, TransformType.NONE);
      GlStateManager.func_179121_F();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
