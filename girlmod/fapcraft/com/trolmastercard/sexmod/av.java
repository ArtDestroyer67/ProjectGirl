package com.trolmastercard.sexmod;

import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class av extends GeoItemRenderer<cc> {
   public static final f7 e = new f7(0.84705883F, 0.11764706F, 0.35686275F);
   public static final f7 f = new f7(0.44705883F, 0.44705883F, 0.44705883F);
   public static final float b = 240.0F;
   public static final float g = 120.0F;
   static final float h = 0.05F;
   static final Minecraft a = Minecraft.func_71410_x();
   boolean c = false;
   f7 d;

   public av() {
      super(new as());
   }

   public void a(GeoModel var1, cc var2, float var3, float var4, float var5, float var6, float var7) {
      GlStateManager.func_179129_p();
      GlStateManager.func_179091_B();
      BufferBuilder var8 = Tessellator.func_178181_a().func_178180_c();
      var8.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      GeoBone var9 = null;
      this.c = false;
      GeoBone var10 = (GeoBone)var1.topLevelBones.get(0);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(var10);
      MATRIX_STACK.moveToPivot(var10);
      MATRIX_STACK.rotate(var10);
      MATRIX_STACK.scale(var10);
      MATRIX_STACK.moveBackFromPivot(var10);

      for(GeoBone var12 : var10.childBones) {
         if ("pentagram".equals(var12.getName())) {
            var9 = var12;
         } else {
            this.renderRecursively(var8, var12, var4, var5, var6, var7);
         }
      }

      Tessellator.func_178181_a().func_78381_a();
      float var14 = this.a(var3);

      try {
         this.d = this.a();
         if (!v.f) {
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, var14, var14);
            GL11.glDisable(2896);
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      var8.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      this.c = true;
      this.renderRecursively(var8, var9, var4, var5, var6, var7);
      Tessellator.func_178181_a().func_78381_a();
      GL11.glEnable(2896);
      MATRIX_STACK.pop();
      GlStateManager.func_179101_C();
      GlStateManager.func_179089_o();
      GlStateManager.func_179117_G();
   }

   float a(float param1) {
      // $FF: Couldn't be decompiled
   }

   float b(long var1, long var3, float var5) {
      float var6 = (float)(var1 - var3);

      try {
         if (var6 < 1000.0F) {
            return 120.0F;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         return var6 <= 3000.0F ? b6.a(120.0F, 240.0F, (var6 - 1000.0F) / 2000.0F) : 240.0F;
      } catch (RuntimeException var7) {
         throw a(var7);
      }
   }

   float a(long var1, long var3, float var5) {
      float var6 = (float)(var1 - var3);

      try {
         if (var6 < 1000.0F) {
            return 240.0F;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         return var6 <= 3000.0F ? b6.a(240.0F, 120.0F, (var6 - 1000.0F) / 2000.0F) : 120.0F;
      } catch (RuntimeException var7) {
         throw a(var7);
      }
   }

   f7 a() {
      // $FF: Couldn't be decompiled
   }

   f7 a(long var1, long var3) {
      float var5 = (float)(var3 - var1);

      try {
         if (var5 < 1000.0F) {
            return f;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var5 <= 3000.0F) {
            return b6.a(f, e, (double)((var5 - 1000.0F) / 2000.0F));
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      return e;
   }

   f7 b(long var1, long var3) {
      float var5 = (float)(var3 - var1);

      try {
         if (var5 < 1000.0F) {
            return e;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var5 <= 3000.0F) {
            return b6.a(e, f, (double)((var5 - 1000.0F) / 2000.0F));
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      return f;
   }

   float b(float var1) {
      return (float)((double)60.0F * Math.sin((double)(((float)a.field_71439_g.field_70173_aa + var1) * 0.05F)) + (double)180.0F);
   }

   void a(BufferBuilder var1, GeoCube var2) {
      for(GeoQuad var6 : var2.quads) {
         try {
            if (var6 == null) {
               continue;
            }
         } catch (RuntimeException var12) {
            throw a(var12);
         }

         for(GeoVertex var10 : var6.vertices) {
            Vector4f var11 = new Vector4f(var10.position.getX(), var10.position.getY(), var10.position.getZ(), 1.0F);
            MATRIX_STACK.getModelMatrix().transform(var11);
            var1.func_181662_b((double)var11.getX(), (double)var11.getY(), (double)var11.getZ()).func_187315_a((double)var10.textureU, (double)var10.textureV).func_181666_a(this.d.a, this.d.c, this.d.b, 1.0F).func_181675_d();
         }
      }

   }

   public void renderCube(BufferBuilder param1, GeoCube param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
