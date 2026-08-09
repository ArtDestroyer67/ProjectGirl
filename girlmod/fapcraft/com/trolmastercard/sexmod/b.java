package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class b extends GeoEntityRenderer<cy> {
   public static final float e = 1.876945F;
   public static final float i = 2.876945F;
   Minecraft a = Minecraft.func_71410_x();
   cy c = null;
   br.b b = null;
   HashMap<String, String> h = new HashMap();
   HashMap<String, String> f = new HashMap();
   HashMap<String, gt> g = new HashMap();
   public static boolean k = false;
   Vec3d d = new Vec3d((double)1.0F, (double)1.0F, (double)1.0F);
   Vec3d j;

   public b(RenderManager var1, AnimatedGeoModel<cy> var2) {
      super(var1, var2);
      this.a();
   }

   void a() {
      this.h.put("customLegL", "legL");
      this.h.put("customShinL", "shinL");
      this.h.put("customLegR", "legR");
      this.h.put("customShinR", "shinR");
      this.f.put("top", "upperBody");
      this.f.put("customArmL", "armL");
      this.f.put("customLowerArmL", "lowerArmL");
      this.f.put("customArmR", "armR");
      this.f.put("customLowerArmR", "lowerArmR");
      this.g.put("lowerArmR", (gt)(var0) -> gc.c(var0.ai()));
      this.g.put("lowerArmL", (gt)(var0) -> gc.c(var0.T()));
   }

   boolean d(cy var1) {
      String var2 = var1.a();

      try {
         if (var1.f) {
            return false;
         }
      } catch (IllegalStateException var10) {
         throw a(var10);
      }

      try {
         if (br.f(var2)) {
            return false;
         }
      } catch (IllegalStateException var7) {
         throw a(var7);
      }

      try {
         if (br.g() != null) {
            return true;
         }
      } catch (IllegalStateException var9) {
         throw a(var9);
      }

      UUID var3 = var1.b();
      em var4 = em.b(var3);

      try {
         if (var4 == null) {
            return true;
         }
      } catch (IllegalStateException var8) {
         throw a(var8);
      }

      HashSet var5 = var4.Y();
      var5.remove(var2);
      String var6 = em.a(var5);
      ge.b.sendToServer(new fw(var6, var1.b()));
      return true;
   }

   @SideOnly(Side.CLIENT)
   public static void a(em var0, float var1) {
      try {
         if (var0.field_70128_L) {
            return;
         }
      } catch (IllegalStateException var8) {
         throw a(var8);
      }

      try {
         if (!var0.field_70170_p.field_72995_K) {
            return;
         }
      } catch (IllegalStateException var6) {
         throw a(var6);
      }

      try {
         if (!var0.H()) {
            return;
         }
      } catch (IllegalStateException var7) {
         throw a(var7);
      }

      RenderManager var2 = Minecraft.func_71410_x().func_175598_ae();

      for(String var4 : var0.Y()) {
         cy var5 = new cy(var0.field_70170_p, var0.f(), var4);
         k = true;
         var2.func_188391_a(var5, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, var1, false);
      }

   }

   public boolean a(cy var1, ICamera var2, double var3, double var5, double var7) {
      return super.func_177071_a(var1, var2, var3, var5, var7);
   }

   boolean a(float var1) {
      try {
         if (var1 == 2.876945F) {
            return true;
         }
      } catch (IllegalStateException var3) {
         throw a(var3);
      }

      try {
         if (var1 == 1.876945F) {
            return true;
         }
      } catch (IllegalStateException var4) {
         throw a(var4);
      }

      try {
         if (k) {
            k = false;
            return true;
         } else {
            return false;
         }
      } catch (IllegalStateException var2) {
         throw a(var2);
      }
   }

   void a(br.b param1, cy param2, float param3) {
      // $FF: Couldn't be decompiled
   }

   public void a(cy param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3d a(Minecraft var0, cy var1, EntityLivingBase var2, em var3, float var4) {
      Vec3d var5;
      if (var3.Q()) {
         Vec3d var6 = var3.o();
         float var7 = var3.I();
         var1.field_70169_q = var6.field_72450_a;
         var1.field_70167_r = var6.field_72448_b;
         var1.field_70166_s = var6.field_72449_c;
         var1.field_70142_S = var6.field_72450_a;
         var1.field_70137_T = var6.field_72448_b;
         var1.field_70136_U = var6.field_72449_c;
         var1.field_70165_t = var6.field_72450_a;
         var1.field_70163_u = var6.field_72448_b;
         var1.field_70161_v = var6.field_72449_c;
         var1.field_70177_z = var7;
         var1.field_70126_B = var7;
         var1.field_70759_as = var7;
         var1.field_70758_at = var7;
         var1.field_70761_aq = var7;
         var1.field_70760_ar = var7;
         var1.field_70125_A = var7;
         var1.field_70127_C = var7;
         var5 = var6;
      } else {
         var1.field_70177_z = var2.field_70177_z;
         var1.field_70126_B = var2.field_70126_B;
         var1.field_70759_as = var2.field_70759_as;
         var1.field_70758_at = var2.field_70758_at;
         var1.field_70761_aq = var2.field_70761_aq;
         var1.field_70760_ar = var2.field_70760_ar;
         var1.field_70125_A = var2.field_70125_A;
         var1.field_70127_C = var2.field_70127_C;
         var1.field_70169_q = var2.field_70169_q;
         var1.field_70167_r = var2.field_70167_r;
         var1.field_70166_s = var2.field_70166_s;
         var1.field_70142_S = var2.field_70142_S;
         var1.field_70137_T = var2.field_70137_T;
         var1.field_70136_U = var2.field_70136_U;
         var1.field_70165_t = var2.field_70165_t;
         var1.field_70163_u = var2.field_70163_u;
         var1.field_70161_v = var2.field_70161_v;
         var5 = b6.a(new Vec3d(var2.field_70142_S, var2.field_70137_T, var2.field_70136_U), var2.func_174791_d(), (double)var4);
      }

      EntityPlayerSP var8 = var0.field_71439_g;
      Vec3d var9 = b6.a(new Vec3d(var8.field_70142_S, var8.field_70137_T, var8.field_70136_U), ((EntityPlayer)var8).func_174791_d(), (double)var4);
      return var5.func_178788_d(var9);
   }

   public void a(GeoModel var1, cy var2, float var3, float var4, float var5, float var6, float var7) {
      GlStateManager.func_179129_p();
      GlStateManager.func_179091_B();
      BufferBuilder var8 = Tessellator.func_178181_a().func_178180_c();
      var8.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      for(GeoBone var10 : var1.topLevelBones) {
         try {
            if (var3 != 1.876945F) {
               this.a(var2, var10, var3);
            }
         } catch (IllegalStateException var11) {
            throw a(var11);
         }

         var2.c.translate(-var10.getPivotX() / 16.0F, -var10.getPivotY() / 16.0F, -var10.getPivotZ() / 16.0F);
         this.renderRecursively(var8, var10, var4, var5, var6, var7);
      }

      Tessellator.func_178181_a().func_78381_a();
      GlStateManager.func_179101_C();
      GlStateManager.func_179089_o();
   }

   EntityLivingBase c(cy var1) {
      em var3 = this.b(var1);

      try {
         if (var3 == null) {
            return null;
         }
      } catch (IllegalStateException var5) {
         throw a(var5);
      }

      Object var2;
      if (!(var3 instanceof ei)) {
         var2 = var3;
      } else {
         EntityPlayer var4 = var1.field_70170_p.func_152378_a(((ei)var3).m());

         Object var10000;
         label28: {
            try {
               if (var4 == null) {
                  var10000 = var3;
                  break label28;
               }
            } catch (IllegalStateException var6) {
               throw a(var6);
            }

            var10000 = var4;
         }

         var2 = var10000;
      }

      return (EntityLivingBase)var2;
   }

   em b(cy var1) {
      UUID var2 = var1.b();
      em var3 = fs.a(var2);

      try {
         if (var3 != null) {
            return var3;
         }
      } catch (IllegalStateException var4) {
         throw a(var4);
      }

      return em.b(var2);
   }

   void a(cy var1, GeoBone var2, float var3) {
      String var4 = this.a(var1);

      try {
         if (var4 == null) {
            return;
         }
      } catch (IllegalStateException var5) {
         throw a(var5);
      }

      this.a(var1, var2, var3, var4);
   }

   void a(cy param1, GeoBone param2, float param3, String param4) {
      // $FF: Couldn't be decompiled
   }

   String a(cy var1) {
      try {
         if (var1.f) {
            return var1.d.boneName;
         }
      } catch (IllegalStateException var5) {
         throw a(var5);
      }

      br.b var2 = br.b(var1.a());

      try {
         if (var2 == null) {
            return null;
         }
      } catch (IllegalStateException var3) {
         throw a(var3);
      }

      try {
         if (gw.CUSTOM_BONE.equals(var2.j())) {
            return var2.b();
         }
      } catch (IllegalStateException var4) {
         throw a(var4);
      }

      return var2.j().boneName;
   }

   public void renderRecursively(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6) {
      this.c.c.push();
      this.c.c.translate(var2);
      this.c.c.moveToPivot(var2);
      this.c.c.rotate(var2);
      this.c.c.scale(var2);
      this.c.c.moveBackFromPivot(var2);
      if (!var2.isHidden()) {
         for(GeoCube var8 : var2.childCubes) {
            this.c.c.push();
            GlStateManager.func_179094_E();
            this.renderCube(var1, var8, var3, var4, var5, var6);
            GlStateManager.func_179121_F();
            this.c.c.pop();
         }
      }

      if (!var2.childBonesAreHiddenToo()) {
         for(GeoBone var11 : var2.childBones) {
            this.renderRecursively(var1, var11, var3, var4, var5, var6);
         }
      }

      try {
         this.c.c.pop();
      } catch (IllegalStateException var9) {
      }

   }

   public void renderCube(BufferBuilder param1, GeoCube param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   private static IllegalStateException a(IllegalStateException var0) {
      return var0;
   }
}
