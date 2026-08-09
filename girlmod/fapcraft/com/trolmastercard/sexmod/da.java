package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class da extends d_<f_> implements c3 {
   public static final int D = 14;
   public static final HashSet<String> E = new HashSet<String>() {
      {
         this.add("static");
         this.add("turnable");
         this.add("slip");
         this.add("boobs");
         this.add("booty");
         this.add("vagina");
         this.add("fuckhole");
         this.add("futaBallLR");
         this.add("futaBallLL");
         this.add("coin");
         this.add("pentagram");
      }
   };
   public static final f7 y = new f7(0.0F, 0.0F, 0.0F);
   static final gv H = new gv(152, 45, 62, 255);
   static final gv I = new gv(84, 66, 88, 255);
   static final bm C = new bm(0.25F, 0.125F);
   static final bm x = new bm(0.375F, 0.125F);
   static final float F = 0.125F;
   static final ResourceLocation w = new ResourceLocation("sexmod", "textures/star.png");
   static final int v = 105;
   static final int A = 125;
   static final float B = 0.0296875F;
   static final float J = 0.06484375F;
   static final float z = 0.026124999F;
   static final float u = 0.0570625F;
   static final ef.b G;
   static final ef.b t;
   boolean r = false;
   float s = 0.0F;

   public da(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   @Nullable
   protected f7 a(f_ var1) {
      try {
         if (var1.field_70170_p instanceof gj) {
            return null;
         }
      } catch (RuntimeException var2) {
         throw a((Exception)var2);
      }

      try {
         if (var1.bb) {
            return null;
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      return y;
   }

   public HashSet<String> a() {
      try {
         if (!this.r) {
            E.addAll(gx.a);
            E.addAll(dh.B);
            this.r = true;
         }
      } catch (RuntimeException var1) {
         throw a((Exception)var1);
      }

      return E;
   }

   protected void b(Tessellator var1, BufferBuilder var2, em var3, f7 var4, float var5) {
      a(var1, var2, var3, var4, var5);
   }

   protected void b(f_ var1) {
      try {
         if (var1.y() != fp.MASTERBATE) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      float var2 = var1.I();
      var1.field_70177_z = var2;
      var1.field_70760_ar = var2;
      var1.field_70761_aq = var2;
      var1.field_70758_at = var2;
      var1.field_70759_as = var2;
   }

   public void a(f_ var1, double var2, double var4, double var6, float var8, float var9) {
      Vec3d var10 = a(var1, var9);

      try {
         if (var10 != null) {
            var1.a((Vec3d)var10);
         }
      } catch (RuntimeException var12) {
         throw a((Exception)var12);
      }

      try {
         var1.aG = var10;
         f_.a(var1, var9);
         this.d(var1);
         this.c(var1);
         super.a(var1, var2, var4, var6, var8, var9);
         a((em)var1, var9);
         if (var1.b()) {
            dh.a((em)var1, var9);
         }

      } catch (RuntimeException var11) {
         throw a((Exception)var11);
      }
   }

   void c(f_ var1) {
      try {
         if (var1.y() != fp.RAPE_CHARGE) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a((Exception)var2);
      }

      var1.field_70761_aq = var1.I();
      var1.field_70760_ar = var1.field_70761_aq;
   }

   void d(f_ var1) {
      try {
         if (!(Boolean)var1.func_184212_Q().func_187225_a(f_.bP)) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a((Exception)var8);
      }

      Vec3d var2 = new Vec3d(var1.field_70142_S, var1.field_70137_T, var1.field_70136_U);
      Vec3d var3 = var1.func_174791_d().func_178788_d(var2);

      boolean var10000;
      label35: {
         try {
            if (Math.abs(var3.field_72450_a) + Math.abs(var3.field_72449_c) < (double)0.05F) {
               var10000 = true;
               break label35;
            }
         } catch (RuntimeException var7) {
            throw a((Exception)var7);
         }

         var10000 = false;
      }

      boolean var4 = var10000;

      try {
         if (var4) {
            var1.field_70761_aq = this.s;
            var1.field_70760_ar = this.s;
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      float var5 = (float)(gc.b(Math.atan2(var3.field_72449_c, var3.field_72450_a)) - (double)90.0F);
      var1.field_70761_aq = var5;
      var1.field_70760_ar = var5;
      this.s = var5;
   }

   @Nullable
   public static Vec3d a(f_ param0, float param1) {
      // $FF: Couldn't be decompiled
   }

   public static void a(em var0, float var1) {
      EntityPlayerSP var2 = i.field_71439_g;

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a((Exception)var5);
      }

      Tessellator var3 = Tessellator.func_178181_a();
      BufferBuilder var4 = var3.func_178180_c();
      GlStateManager.func_179094_E();
      af.a(i, var0, var1);
      i.func_110434_K().func_110577_a(e);
      GlStateManager.func_179129_p();
      GlStateManager.func_179140_f();
      a(var0, var4, var3, b6.a(var0.field_70760_ar, var0.field_70761_aq, var1));
      b(var0, var4, var3, var1);
      a(var0, var4, var3);
      GlStateManager.func_179121_F();
      GlStateManager.func_179089_o();
      GlStateManager.func_179145_e();
   }

   static void b(em var0, BufferBuilder var1, Tessellator var2, float var3) {
      try {
         if (!(var0 instanceof f_)) {
            return;
         }
      } catch (RuntimeException var18) {
         throw a((Exception)var18);
      }

      try {
         if (!(Boolean)var0.func_184212_Q().func_187225_a(f_.bP)) {
            return;
         }
      } catch (RuntimeException var16) {
         throw a((Exception)var16);
      }

      try {
         if ((Boolean)var0.func_184212_Q().func_187225_a(f_.L)) {
            return;
         }
      } catch (RuntimeException var17) {
         throw a((Exception)var17);
      }

      GlStateManager.func_179094_E();
      Vec3d var4 = var0.b("stars");
      GlStateManager.func_179137_b(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c);
      float var5 = (float)i.field_71441_e.func_82737_E() + var3;
      float var6 = (float)(Math.sin((double)var5 * 0.2) * (double)5.0F);
      float var7 = (float)(Math.cos((double)var5 * 0.2) * (double)5.0F);
      float var8 = (float)((double)var5 * (double)3.0F);
      GlStateManager.func_179114_b(var6, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(var8, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(var7, 0.0F, 0.0F, 1.0F);
      float var9 = gc.c((double)9.0F);
      f7 var10 = f_.aa;
      i.func_110434_K().func_110577_a(e);
      var1.func_181668_a(3, DefaultVertexFormats.field_181709_i);
      GlStateManager.func_187441_d(a(var0, var3, 1.0F, 3.0F));

      for(float var11 = 0.0F; (double)var11 < (Math.PI * 2D); var11 += var9) {
         double var12 = Math.sin((double)var11) * (double)0.3F;
         double var14 = Math.cos((double)var11) * (double)0.3F;
         var1.func_181662_b(var12, (double)0.0F, var14).func_187315_a((double)0.0F, (double)0.0F).func_181666_a(var10.a, var10.c, var10.b, 1.0F).func_181675_d();
      }

      var2.func_78381_a();
      i.func_110434_K().func_110577_a(w);
      var1.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      var9 = gc.c((double)60.0F);

      for(float var20 = 0.0F; (double)var20 < (Math.PI * 2D); var20 += var9) {
         double var21 = Math.sin((double)var20) * (double)0.3F;
         double var22 = Math.cos((double)var20) * (double)0.3F;
         var1.func_181662_b(var21 - (double)0.1F, (double)0.1F, var22).func_187315_a((double)0.0F, (double)0.0F).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
         var1.func_181662_b(var21 + (double)0.1F, (double)0.1F, var22).func_187315_a((double)1.0F, (double)0.0F).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
         var1.func_181662_b(var21 + (double)0.1F, (double)-0.1F, var22).func_187315_a((double)1.0F, (double)1.0F).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
         var1.func_181662_b(var21 - (double)0.1F, (double)-0.1F, var22).func_187315_a((double)0.0F, (double)1.0F).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      }

      var2.func_78381_a();
      GlStateManager.func_179121_F();
   }

   static void a(em param0, BufferBuilder param1, Tessellator param2, float param3) {
      // $FF: Couldn't be decompiled
   }

   static void a(em var0, BufferBuilder var1, Tessellator var2) {
      try {
         if (!((b7)var0).a()) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a((Exception)var7);
      }

      i.func_110434_K().func_110577_a(cb.h);
      Vec3d[] var3 = new Vec3d[14];
      Vec3d[] var4 = new Vec3d[14];
      int var5 = 0;

      try {
         while(var5 < 14) {
            var3[var5] = var0.b("wingRV" + var5);
            var4[var5] = var0.b("wingLV" + var5);
            ++var5;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      a(var1, var2, var3);
      a(var1, var2, var4);
   }

   static void a(BufferBuilder var0, Tessellator var1, Vec3d[] var2) {
      var0.func_181668_a(4, DefaultVertexFormats.field_181709_i);
      var0.func_181662_b(var2[0].field_72450_a, var2[0].field_72448_b, var2[0].field_72449_c).func_187315_a((double)C.c, (double)C.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[1].field_72450_a, var2[1].field_72448_b, var2[1].field_72449_c).func_187315_a((double)(C.c + 0.125F), (double)C.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[2].field_72450_a, var2[2].field_72448_b, var2[2].field_72449_c).func_187315_a((double)(C.c + 0.125F), (double)(C.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[11].field_72450_a, var2[11].field_72448_b, var2[11].field_72449_c).func_187315_a((double)C.c, (double)C.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[12].field_72450_a, var2[12].field_72448_b, var2[12].field_72449_c).func_187315_a((double)(C.c + 0.125F), (double)C.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[13].field_72450_a, var2[13].field_72448_b, var2[13].field_72449_c).func_187315_a((double)(C.c + 0.125F), (double)(C.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var1.func_78381_a();
      var0.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      var0.func_181662_b(var2[3].field_72450_a, var2[3].field_72448_b, var2[3].field_72449_c).func_187315_a((double)x.c, (double)(x.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[4].field_72450_a, var2[4].field_72448_b, var2[4].field_72449_c).func_187315_a((double)x.c, (double)x.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[5].field_72450_a, var2[5].field_72448_b, var2[5].field_72449_c).func_187315_a((double)(x.c + 0.125F), (double)x.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[6].field_72450_a, var2[6].field_72448_b, var2[6].field_72449_c).func_187315_a((double)(x.c + 0.125F), (double)(x.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[7].field_72450_a, var2[7].field_72448_b, var2[7].field_72449_c).func_187315_a((double)x.c, (double)(x.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[8].field_72450_a, var2[8].field_72448_b, var2[8].field_72449_c).func_187315_a((double)x.c, (double)x.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[9].field_72450_a, var2[9].field_72448_b, var2[9].field_72449_c).func_187315_a((double)(x.c + 0.125F), (double)x.a).func_181669_b(255, 255, 255, 255).func_181675_d();
      var0.func_181662_b(var2[10].field_72450_a, var2[10].field_72448_b, var2[10].field_72449_c).func_187315_a((double)(x.c + 0.125F), (double)(x.a + 0.125F)).func_181669_b(255, 255, 255, 255).func_181675_d();
      var1.func_78381_a();
   }

   protected void a(GeoModel var1, BufferBuilder var2, f_ var3, float var4, float var5, float var6, float var7, float var8) {
      GeoBone var9 = (GeoBone)var1.topLevelBones.get(0);
      GeoBone var10 = null;
      GeoBone var11 = null;
      GeoBone var12 = null;
      GeoBone var13 = null;

      for(GeoBone var15 : var9.childBones) {
         String var16 = var15.getName();
         byte var17 = -1;

         label73: {
            label72: {
               label71: {
                  label70: {
                     try {
                        switch (var16.hashCode()) {
                           case 3029410:
                              break;
                           case 3059345:
                              break label70;
                           case 93911760:
                              break label71;
                           case 109761491:
                              if (!var16.equals("steve")) {
                                 break label73;
                              }
                              break label72;
                           default:
                              break label73;
                        }
                     } catch (IOException var20) {
                        throw a((Exception)var20);
                     }

                     if (var16.equals("body")) {
                        var17 = 1;
                     }
                     break label73;
                  }

                  if (var16.equals("coin")) {
                     var17 = 2;
                  }
                  break label73;
               }

               if (var16.equals("body2")) {
                  var17 = 3;
               }
               break label73;
            }

            var17 = 0;
         }

         switch (var17) {
            case 0:
               var12 = var15;
               break;
            case 1:
               var10 = var15;
               break;
            case 2:
               var11 = var15;
               break;
            case 3:
               var13 = var15;
         }
      }

      MATRIX_STACK.push();
      MATRIX_STACK.translate(var9);
      MATRIX_STACK.moveToPivot(var9);
      MATRIX_STACK.rotate(var9);
      MATRIX_STACK.scale(var9);
      MATRIX_STACK.moveBackFromPivot(var9);
      this.renderRecursively(var2, var10, var4, var5, var6, var7);
      Tessellator.func_178181_a().func_78381_a();
      this.a(var2, var11, var3, var8);
      var2.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      try {
         Minecraft.func_71410_x().field_71446_o.func_110577_a(this.d((em)this.j));
      } catch (IOException var18) {
         var18.printStackTrace();
      }

      try {
         this.renderRecursively(var2, var12, var4, var5, var6, ((f_)this.j).v());
         Tessellator.func_178181_a().func_78381_a();
         if (var13 != null) {
            var2.func_181668_a(7, DefaultVertexFormats.field_181712_l);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(ce.j);
            this.renderRecursively(var2, var13, var4, var5, var6, ((f_)this.j).v());
            Tessellator.func_178181_a().func_78381_a();
         }
      } catch (IOException var19) {
         throw a((Exception)var19);
      }

      MATRIX_STACK.pop();
   }

   protected void a(BufferBuilder param1, String param2, GeoBone param3) {
      // $FF: Couldn't be decompiled
   }

   void e(BufferBuilder var1, GeoBone var2) {
      try {
         if (fp.a(this.j, fp.PUSSY_LICKING, fp.MASTERBATE_SITTING)) {
            this.f(var1, var2);
            return;
         }
      } catch (RuntimeException var4) {
         throw a((Exception)var4);
      }

      try {
         if (fp.a(this.j, fp.MORNING_BLOWJOB_SLOW)) {
            this.d(var1, var2);
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

   }

   void c(BufferBuilder param1, GeoBone param2) {
      // $FF: Couldn't be decompiled
   }

   void d(GeoBone var1) {
      try {
         if (!fp.a(this.j, fp.MORNING_BLOWJOB_SLOW, fp.MORNING_BLOWJOB_FAST)) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a((Exception)var7);
      }

      try {
         if (i.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a((Exception)var9);
      }

      float var2 = (float)i.field_71439_g.field_70173_aa + i.func_184121_ak();
      float var3 = (float)(Math.sin((double)(var2 * 0.1F)) * (double)0.1F) + 0.2F;
      float var4 = (float)Math.sin((double)(var2 * 0.1F)) * 0.1F;

      try {
         if (fp.a(this.j, fp.MORNING_BLOWJOB_SLOW)) {
            var1.setRotationY(var1.getRotationY() + var3);
            var1.setRotationZ(var1.getRotationZ() + var4);
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      try {
         if (!(this.j).aD) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a((Exception)var8);
      }

      float var5 = 1.0F - Math.min(0.5F, fp.a(this.j, i.func_184121_ak())) / 0.5F;
      var1.setRotationY(var1.getRotationY() + var3 * var5);
      var1.setRotationZ(var1.getRotationZ() + var4 * var5);
   }

   void c(GeoBone var1) {
      try {
         if (!fp.a(this.j, fp.MORNING_BLOWJOB_SLOW, fp.MORNING_BLOWJOB_FAST)) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a((Exception)var7);
      }

      try {
         if (i.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a((Exception)var9);
      }

      float var2 = (float)i.field_71439_g.field_70173_aa + i.func_184121_ak();
      float var3 = (float)Math.sin((double)(var2 * -0.1F)) * 0.1F;
      float var4 = (float)Math.sin((double)(var2 * 0.1F)) * 0.1F;

      try {
         if (fp.a(this.j, fp.MORNING_BLOWJOB_SLOW)) {
            var1.setRotationY(var1.getRotationY() + var3);
            var1.setRotationZ(var1.getRotationZ() + var4);
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      try {
         if (!(this.j).aD) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a((Exception)var8);
      }

      float var5 = Math.min(0.5F, fp.a(this.j, i.func_184121_ak())) / 0.5F;
      var1.setRotationY(var1.getRotationY() + var3 * var5);
      var1.setRotationZ(var1.getRotationZ() + var4 * var5);
   }

   void a(GeoBone var1) {
      try {
         if (!fp.a(this.j, fp.MORNING_BLOWJOB_SLOW)) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      try {
         if (i.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a((Exception)var4);
      }

      float var2 = (float)i.field_71439_g.field_70173_aa + i.func_184121_ak();
      var1.setPositionX((float)((double)var1.getPositionX() + Math.sin((double)(var2 * 0.1F)) * (double)-0.1F));
   }

   void b(GeoBone var1) {
      try {
         if (!fp.a(this.j, fp.MORNING_BLOWJOB_SLOW)) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      try {
         if (i.func_147113_T()) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a((Exception)var4);
      }

      float var2 = (float)i.field_71439_g.field_70173_aa + i.func_184121_ak();
      var1.setPositionX((float)((double)var1.getPositionX() + Math.sin((double)(var2 * 0.1F)) * (double)-0.15F));
   }

   void a(BufferBuilder var1, GeoBone var2, float var3) {
      float var4 = fp.d(this.j, i.func_184121_ak());
      float var5 = var3 * (float)((double)0.02F * ((double)-0.4F * Math.cos((Math.PI * 2D) * (double)var4 + 1.05) + (double)0.6F));
      ef.b var6 = new ef.b(H, 0.0F, 12, var5, (var2x, var3x) -> var3 * (float)(Math.cos((Math.PI * 2D) * (double)var4 + (double)0.35F + (double)(-0.2F * (float)var2x)) * (double)-10.0F), (var0, var1x) -> 0.0F, (var2x, var3x) -> var3 * (float)(Math.cos((Math.PI * 2D) * (double)var4 + (double)1.25F + (double)(-0.1F * (float)var2x)) * (double)-5.0F), 0.03F, 0.005F);
      this.a(var1, var2, var6);
   }

   void d(BufferBuilder var1, GeoBone var2) {
      float var3 = fp.d(this.j, i.func_184121_ak());
      ef.b var4 = new ef.b(H, 0.0F, 12, 0.02F, (var1x, var2x) -> (float)(Math.cos((Math.PI * 2D) * (double)var3 + (double)(-0.2F * (float)var1x)) * (double)15.0F), (var1x, var2x) -> (float)(Math.cos((Math.PI * 2D) * (double)var3 + (double)(-0.2F * (float)var1x)) * (double)5.0F), (var0, var1x) -> 0.0F, 0.03F, 0.005F);
      this.a(var1, var2, var4);
   }

   void f(BufferBuilder var1, GeoBone var2) {
      float var3 = ((f_)this.j).b(i.func_184121_ak());

      try {
         if (var3 == 0.0F) {
            this.a(var1, var2, G);
            return;
         }
      } catch (RuntimeException var5) {
         throw a((Exception)var5);
      }

      try {
         if (var3 == 1.0F) {
            this.a(var1, var2, t);
            return;
         }
      } catch (RuntimeException var6) {
         throw a((Exception)var6);
      }

      ef.b var4 = G.a();
      var4.g = b6.a(G.g, 0.0F, var3);
      var4.e = b6.a(G.e, 0.0F, var3);
      this.a(var1, var2, var4);
   }

   void a(BufferBuilder var1, GeoBone var2, ef.b var3) {
      GlStateManager.func_179094_E();
      Tessellator.func_178181_a().func_78381_a();
      com.trolmastercard.sexmod.p.a(MATRIX_STACK, var2);
      GlStateManager.func_179129_p();
      this.func_110776_a(e);
      ef.a(var1, Tessellator.func_178181_a(), i, var3);
      this.func_110776_a((ResourceLocation)Objects.requireNonNull(this.getEntityTexture(this.j)));
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      GlStateManager.func_179089_o();
      GlStateManager.func_179121_F();
   }

   void a(BufferBuilder var1, GeoBone var2, f_ var3, float var4) {
      try {
         if (var3.y() != fp.GIVE_COIN) {
            return;
         }
      } catch (RuntimeException var14) {
         throw a((Exception)var14);
      }

      n = var1;
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(var2);
      MATRIX_STACK.moveToPivot(var2);
      MATRIX_STACK.rotate(var2);
      MATRIX_STACK.scale(var2);
      MATRIX_STACK.moveBackFromPivot(var2);
      if (!this.p.contains(var2.getName())) {
         for(GeoCube var6 : var2.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.func_179094_E();
            this.q = var2;
            this.a(var1, var6, 1.0F, 1.0F, 1.0F, 1.0F, (double)0.0F);
            GlStateManager.func_179121_F();
            MATRIX_STACK.pop();
         }
      }

      Tessellator.func_178181_a().func_78381_a();
      GeoBone var15 = (GeoBone)var2.childBones.get(0);
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      GL11.glDisable(2896);
      float var16 = be.b((float)fp.GIVE_COIN.ticksPlaying[1] + var4, 105.0F, 125.0F);
      float var7 = (var16 - 105.0F) / 20.0F;
      float var8 = b6.a(120.0F, 240.0F, var7);
      f7 var9 = b6.a(av.f, av.e, (double)var7);
      float var10 = OpenGlHelper.lastBrightnessX;
      float var11 = OpenGlHelper.lastBrightnessY;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, var8, var8);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(var15);
      MATRIX_STACK.moveToPivot(var15);
      MATRIX_STACK.rotate(var15);
      MATRIX_STACK.scale(var15);
      MATRIX_STACK.moveBackFromPivot(var15);
      if (!this.p.contains(var15.getName())) {
         for(GeoCube var13 : var15.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.func_179094_E();
            this.q = var15;
            this.a(var1, var13, var9.a, var9.c, var9.b, 1.0F, (double)0.0F);
            GlStateManager.func_179121_F();
            MATRIX_STACK.pop();
         }
      }

      MATRIX_STACK.pop();
      MATRIX_STACK.pop();
      Tessellator.func_178181_a().func_78381_a();
      GL11.glEnable(2896);
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, var10, var11);
   }

   protected Vec3d a(f_ var1, float var2, Vec3d var3) {
      if (var1.y() == fp.RUN) {
         float var4 = var1.I();
         var1.field_70177_z = var4;
         var1.field_70760_ar = var4;
         var1.field_70761_aq = var4;
         var1.field_70758_at = var4;
         var1.field_70759_as = var4;
      }

      return var3;
   }

   static {
      G = new ef.b(H, 0.1F, 12, 0.035F, (var0, var1) -> (float)(Math.sin((double)var1 * 0.3 + -0.2 * (double)var0) * (double)15.0F), (var0, var1) -> (float)(Math.sin((double)var1 * -0.15 + -0.2 * (double)var0) * (double)3.0F), (var0, var1) -> 0.0F, 0.03F, 0.005F);
      t = new ef.b(H, 0.0F, 12, 0.0F, (var0, var1) -> (float)(Math.sin((double)var1 * 0.3 + -0.2 * (double)var0) * (double)15.0F), (var0, var1) -> (float)(Math.sin((double)var1 * -0.15 + -0.2 * (double)var0) * (double)3.0F), (var0, var1) -> 0.0F, 0.03F, 0.005F);
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
