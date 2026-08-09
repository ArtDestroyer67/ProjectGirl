package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class e extends Render<al> {
   static final ResourceLocation g = new ResourceLocation("sexmod", "textures/entity/pyrocinical/standing.png");
   static final ResourceLocation f = new ResourceLocation("sexmod", "textures/entity/pyrocinical/praising.png");
   static final ResourceLocation a = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking1.png");
   static final ResourceLocation b = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking2.png");
   static final String e = "textures/entity/pyrocinical/fat/";
   static final int j = 30;
   static final float c = 1.4F;
   static final float h = 0.75F;
   Minecraft d = Minecraft.func_71410_x();
   ResourceLocation k = null;
   long i = 0L;

   public e(RenderManager var1) {
      super(var1);
   }

   @Nullable
   protected ResourceLocation a(al var1) {
      return null;
   }

   public void a(al param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   ResourceLocation a(al var1, double var2) {
      try {
         if (var1.a != -1) {
            return new ResourceLocation("sexmod", String.format("%s%s.png", "textures/entity/pyrocinical/fat/", this.b(var1)));
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (var2 < (double)3.0F) {
            return f;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      Vec3d var4 = (new Vec3d(var1.field_70142_S, var1.field_70137_T, var1.field_70136_U)).func_178788_d(var1.func_174791_d());

      try {
         if (Math.abs(var4.field_72450_a) + Math.abs(var4.field_72448_b) + Math.abs(var4.field_72449_c) == (double)0.0F) {
            return g;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      ResourceLocation var10000;
      try {
         if (Math.sin((double)((float)this.d.field_71439_g.field_70173_aa * 0.75F)) > (double)0.0F) {
            var10000 = a;
            return var10000;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      var10000 = b;
      return var10000;
   }

   double a(ResourceLocation param1) {
      // $FF: Couldn't be decompiled
   }

   int b(al var1) {
      try {
         if (var1.a == -1) {
            return 0;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return (int)be.b((float)(this.d.field_71439_g.field_70173_aa - var1.a), 1.0F, 30.0F);
   }

   float a(al var1, float var2) {
      try {
         if (var1.a == -1) {
            return 0.0F;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      int var3 = this.b(var1);

      try {
         if (var3 == 30) {
            return 1.0F;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      return ((float)var3 + var2) / 30.0F;
   }

   float b(al var1, float var2) {
      try {
         if (var1.a == -1) {
            return 1.0F;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (this.d.field_71439_g.field_70173_aa - var1.a > 120) {
            return 0.0F;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      byte var3 = 90;
      float var4 = be.b((float)(this.d.field_71439_g.field_70173_aa - var1.a), (float)var3, 120.0F) - (float)var3;
      float var5 = (var4 + var2) / 30.0F;
      return 1.0F - var5;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
