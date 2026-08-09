package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class dh extends d_<f8> {
   static final gv C = new gv(115, 108, 188, 255);
   static final f7 D = new f7(0.05F, 0.04F, 0.0F);
   static final f7 v = new f7(0.0F, 0.065F, 0.0F);
   static final f7 z = new f7(0.0F, 0.03F, 0.03F);
   static final gv r = new gv(63, 59, 150, 255);
   static final gv x = new gv(79, 74, 188, 255);
   static final float A = 0.5F;
   static final float w = 0.5F;
   static final int s = 40;
   static final float y = 0.01F;
   static final float t = 0.03F;
   public static final HashSet<String> B = new HashSet<String>() {
      {
         this.add("boobs2");
         this.add("booty2");
         this.add("vagina2");
         this.add("fuckhole2");
      }
   };
   boolean u = false;

   public dh(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   public HashSet<String> a() {
      try {
         if (!this.u) {
            B.addAll(gx.a);
            this.u = true;
         }
      } catch (NumberFormatException var1) {
         throw a((Exception)var1);
      }

      return B;
   }

   public void a(f8 var1, double var2, double var4, double var6, float var8, float var9) {
      try {
         if (this.d(var1)) {
            return;
         }
      } catch (NumberFormatException var11) {
         throw a((Exception)var11);
      }

      try {
         if (this.a(var1)) {
            return;
         }
      } catch (NumberFormatException var13) {
         throw a((Exception)var13);
      }

      try {
         if (c(var1, 0.5F)) {
            return;
         }
      } catch (NumberFormatException var10) {
         throw a((Exception)var10);
      }

      try {
         if (this.c(var1)) {
            return;
         }
      } catch (NumberFormatException var12) {
         throw a((Exception)var12);
      }

      super.a(var1, var2, var4, var6, var8, var9);
      a((em)var1, var9);
   }

   boolean c(f8 var1) {
      f_ var2 = var1.a(false);

      try {
         if (var2 == null) {
            return false;
         }
      } catch (NumberFormatException var4) {
         throw a((Exception)var4);
      }

      try {
         switch (var2.y()) {
            case CONTROLLED_FLIGHT:
            case BOOST:
               return true;
            default:
               return false;
         }
      } catch (NumberFormatException var3) {
         throw a((Exception)var3);
      }
   }

   boolean a(f8 var1) {
      try {
         if (var1.y() != fp.RIDE_MOMMY_HEAD) {
            return false;
         }
      } catch (NumberFormatException var2) {
         throw a((Exception)var2);
      }

      boolean var10000;
      try {
         if (var1.a(false) == null) {
            var10000 = true;
            return var10000;
         }
      } catch (NumberFormatException var3) {
         throw a((Exception)var3);
      }

      var10000 = false;
      return var10000;
   }

   boolean d(f8 var1) {
      f_ var2 = var1.a(false);

      try {
         if (var2 == null) {
            return false;
         }
      } catch (NumberFormatException var3) {
         throw a((Exception)var3);
      }

      try {
         if (var2.field_70128_L) {
            var1.a((UUID)null);
            return false;
         }
      } catch (NumberFormatException var4) {
         throw a((Exception)var4);
      }

      return var2.b();
   }

   public void func_76979_b(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      try {
         if (!(var1 instanceof f8)) {
            super.func_76979_b(var1, var2, var4, var6, var8, var9);
            return;
         }
      } catch (NumberFormatException var13) {
         throw a((Exception)var13);
      }

      f8 var10 = (f8)var1;

      try {
         if (this.d(var10)) {
            return;
         }
      } catch (NumberFormatException var11) {
         throw a((Exception)var11);
      }

      try {
         if (var10.r()) {
            return;
         }
      } catch (NumberFormatException var12) {
         throw a((Exception)var12);
      }

      super.func_76979_b(var1, var2, var4, var6, var8, var9);
   }

   static boolean c(em var0, float var1) {
      try {
         if (!(var0 instanceof f8)) {
            return false;
         }
      } catch (NumberFormatException var5) {
         throw a((Exception)var5);
      }

      f_ var2 = ((f8)var0).a(false);

      try {
         if (var2 == null) {
            return false;
         }
      } catch (NumberFormatException var3) {
         throw a((Exception)var3);
      }

      boolean var10000;
      try {
         if (var2.bm < var1) {
            var10000 = true;
            return var10000;
         }
      } catch (NumberFormatException var4) {
         throw a((Exception)var4);
      }

      var10000 = false;
      return var10000;
   }

   public static void a(em var0, float var1) {
      EntityPlayerSP var2 = i.field_71439_g;

      try {
         if (var2 == null) {
            return;
         }
      } catch (NumberFormatException var5) {
         throw a((Exception)var5);
      }

      try {
         if (c(var0, 0.5F)) {
            return;
         }
      } catch (NumberFormatException var7) {
         throw a((Exception)var7);
      }

      Tessellator var3 = Tessellator.func_178181_a();
      BufferBuilder var4 = var3.func_178180_c();

      label29: {
         try {
            GlStateManager.func_179094_E();
            if (var0.h()) {
               GlStateManager.func_179137_b((double)0.0F, 0.01, (double)0.0F);
               break label29;
            }
         } catch (NumberFormatException var6) {
            throw a((Exception)var6);
         }

         af.a(i, var0, var1);
         b(var0, var1);
      }

      i.func_110434_K().func_110577_a(e);
      GlStateManager.func_179129_p();
      GlStateManager.func_179140_f();
      a(var0, var4, var3, a((em)var0, var1));
      a(var0, var4, var3);
      GlStateManager.func_179121_F();
      GlStateManager.func_179089_o();
      GlStateManager.func_179145_e();
   }

   static void b(em var0, float var1) {
      try {
         if (!(var0 instanceof f8)) {
            return;
         }
      } catch (NumberFormatException var7) {
         throw a((Exception)var7);
      }

      f8 var2 = (f8)var0;

      try {
         if (!var2.r()) {
            return;
         }
      } catch (NumberFormatException var4) {
         throw a((Exception)var4);
      }

      try {
         if (ce.c(var2)) {
            return;
         }
      } catch (NumberFormatException var6) {
         throw a((Exception)var6);
      }

      f_ var3 = var2.a(false);

      try {
         if (var3 == null) {
            return;
         }
      } catch (NumberFormatException var5) {
         throw a((Exception)var5);
      }

      GlStateManager.func_179114_b(-b6.b(var0.field_70760_ar, var0.field_70761_aq, (double)var1), 0.0F, 1.0F, 0.0F);
   }

   static boolean a(em var0) {
      if (var0 instanceof f_) {
         var0 = ((f_)var0).a(false);
      }

      try {
         if (var0 == null) {
            return false;
         }
      } catch (NumberFormatException var1) {
         throw a((Exception)var1);
      }

      boolean var10000;
      try {
         if (!fp.a((em)var0, fp.THREESOME_SLOW, fp.THREESOME_FAST, fp.THREESOME_CUM)) {
            var10000 = true;
            return var10000;
         }
      } catch (NumberFormatException var2) {
         throw a((Exception)var2);
      }

      var10000 = false;
      return var10000;
   }

   static void a(em var0, BufferBuilder var1, Tessellator var2) {
      try {
         if (!a(var0)) {
            return;
         }
      } catch (NumberFormatException var5) {
         throw a((Exception)var5);
      }

      var1.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      int var3 = 0;

      try {
         while(var3 < 39) {
            a(var0, var1, var3, var3 + 1);
            ++var3;
         }
      } catch (NumberFormatException var4) {
         throw a((Exception)var4);
      }

      a(var0, var1, 39, 0);
      var2.func_78381_a();
   }

   static void a(em var0, BufferBuilder var1, int var2, int var3) {
      Vec3d var4 = var0.b("skirt_" + var2 + "_0");
      Vec3d var5 = var0.b("skirt_" + var2 + "_1");
      Vec3d var6 = var0.b("skirt_" + var2 + "_2");
      Vec3d var7 = var0.b("skirt_" + var3 + "_0");
      Vec3d var8 = var0.b("skirt_" + var3 + "_1");
      Vec3d var9 = var0.b("skirt_" + var3 + "_2");

      gv var10000;
      label17: {
         try {
            if (var2 % 2 == 0) {
               var10000 = x;
               break label17;
            }
         } catch (NumberFormatException var11) {
            throw a((Exception)var11);
         }

         var10000 = r;
      }

      gv var10 = var10000;
      var1.func_181662_b(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var5.field_72450_a, var5.field_72448_b, var5.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var8.field_72450_a, var8.field_72448_b, var8.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var7.field_72450_a, var7.field_72448_b, var7.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var5.field_72450_a, var5.field_72448_b, var5.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var8.field_72450_a, var8.field_72448_b, var8.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var9.field_72450_a, var9.field_72448_b, var9.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
      var1.func_181662_b(var6.field_72450_a, var6.field_72448_b, var6.field_72449_c).func_181669_b(var10.a, var10.d, var10.c, var10.b).func_181675_d();
   }

   protected void a(BufferBuilder param1, String param2, GeoBone param3) {
      // $FF: Couldn't be decompiled
   }

   void a(BufferBuilder var1, GeoBone var2, boolean var3) {
      ItemRenderer var4 = Minecraft.func_71410_x().func_175597_ag();

      label22: {
         try {
            GlStateManager.func_179094_E();
            Tessellator.func_178181_a().func_78381_a();
            com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, var2);
            GL11.glEnable(2896);
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            if (var3) {
               GlStateManager.func_179137_b(-0.01, (double)0.0F, (double)0.0F);
               GlStateManager.func_179114_b(120.0F, 1.0F, 0.0F, 0.0F);
               break label22;
            }
         } catch (NumberFormatException var8) {
            throw a((Exception)var8);
         }

         GlStateManager.func_179137_b(0.15, (double)0.0F, -0.05);
         GlStateManager.func_179114_b(-140.0F, 1.0F, 0.0F, 0.0F);
      }

      GlStateManager.func_179139_a(0.7, 0.7, 0.7);
      ItemStack var5 = new ItemStack(Items.field_151031_f);
      float var6 = ((f8)this.j).b(i.func_184121_ak());
      if (var6 < 1.0F) {
         float var7 = (float)b6.e((double)var6);
         ((f8)this.j).d((int)(11.0F * (1.0F - var7) + 71980.0F));
         ((f8)this.j).a((ItemStack)var5);
         ((f8)this.j).func_184598_c(EnumHand.MAIN_HAND);
         ((f8)this.j).W();
      } else {
         ((f8)this.j).a((ItemStack)ItemStack.field_190927_a);
         ((f8)this.j).K();
      }

      var4.func_178099_a(this.j, var5, TransformType.THIRD_PERSON_RIGHT_HAND);
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      this.func_110776_a((ResourceLocation)Objects.requireNonNull(this.getEntityTexture(this.j)));
      GL11.glDisable(2896);
      GlStateManager.func_179121_F();
      GlStateManager.func_179147_l();
      GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
   }

   public static void a(em param0, String param1, GeoBone param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   static int a(String var0) {
      int var1 = var0.indexOf(95);
      int var2 = var0.indexOf(95, var1 + 1);

      try {
         if (var1 == -1 || var2 == -1) {
            return -1;
         }
      } catch (NumberFormatException var6) {
         throw a((Exception)var6);
      }

      String var3 = var0.substring(var1 + 1, var2);

      try {
         return Integer.parseInt(var3);
      } catch (NumberFormatException var5) {
         return -1;
      }
   }

   protected void a(GeoModel var1, BufferBuilder var2, f8 var3, float var4, float var5, float var6, float var7, float var8) {
      try {
         if (!ce.c(var3)) {
            super.a(var1, var2, var3, var4, var5, var6, var7, var8);
            return;
         }
      } catch (IOException var18) {
         throw a((Exception)var18);
      }

      GeoBone var9 = (GeoBone)var1.topLevelBones.get(0);
      GeoBone var10 = null;
      GeoBone var11 = null;

      for(GeoBone var13 : var9.childBones) {
         String var14 = var13.getName();
         byte var15 = -1;

         label51: {
            label50: {
               try {
                  switch (var14.hashCode()) {
                     case 93911760:
                        break;
                     case 109761491:
                        if (!var14.equals("steve")) {
                           break label51;
                        }
                        break label50;
                     default:
                        break label51;
                  }
               } catch (IOException var17) {
                  throw a((Exception)var17);
               }

               if (var14.equals("body2")) {
                  var15 = 1;
               }
               break label51;
            }

            var15 = 0;
         }

         switch (var15) {
            case 0:
               var11 = var13;
               break;
            case 1:
               var10 = var13;
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
      var2.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      try {
         Minecraft.func_71410_x().field_71446_o.func_110577_a(this.d(this.j));
      } catch (IOException var16) {
         var16.printStackTrace();
      }

      this.renderRecursively(var2, var11, var4, var5, var6, ((f8)this.j).v());
      Tessellator.func_178181_a().func_78381_a();
      MATRIX_STACK.pop();
   }

   static void a(em var0, BufferBuilder var1, Tessellator var2, float var3) {
      var1.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      Vec3d[][] var4 = af.a(var0, var3, "clothBoobLconStart", "clothBoobLconEnd", D, v);
      Vec3d[][] var5 = af.a(var0, var3, "clothBoobRconStart", "clothBoobRconEnd", D, v);
      Vec3d[][] var6 = af.a(var0, var3, "clothBoobMidconStart", "clothBoobMidconEnd", z, z);
      af.a(var1, var4, C);
      af.a(var1, var5, C);
      af.a(var1, var6, C);
      var2.func_78381_a();
   }

   public boolean a(HashSet var1, GeoBone var2) {
      for(; var2.parent != null; var2 = var2.parent) {
         String var3 = var2.getName();

         try {
            if (var3.contains("clothBoob")) {
               return true;
            }
         } catch (NumberFormatException var6) {
            throw a((Exception)var6);
         }

         try {
            if (var1.contains(var3)) {
               return false;
            }
         } catch (NumberFormatException var4) {
            throw a((Exception)var4);
         }

         try {
            if (var3.startsWith("armor")) {
               return false;
            }
         } catch (NumberFormatException var5) {
            throw a((Exception)var5);
         }
      }

      return true;
   }

   protected Vec3d a(f8 var1, float var2, Vec3d var3) {
      if (var1.y() == fp.RUN) {
         float var6 = var1.I();
         var1.field_70177_z = var6;
         var1.field_70760_ar = var6;
         var1.field_70761_aq = var6;
         var1.field_70758_at = var6;
         var1.field_70759_as = var6;
         return var3;
      } else {
         if (b(var1)) {
            f_ var4 = var1.a(false);

            try {
               if (var4 != null) {
                  a((f_)var4, var2, (EntityLivingBase)var1);
                  return b(var4, var2);
               }
            } catch (NumberFormatException var5) {
               throw a((Exception)var5);
            }
         }

         return var3;
      }
   }

   public static void a(f_ var0, float var1, EntityLivingBase var2) {
      boolean var3 = var0.Q();

      float var10000;
      label35: {
         try {
            if (var3) {
               var10000 = var0.I();
               break label35;
            }
         } catch (NumberFormatException var8) {
            throw a((Exception)var8);
         }

         var10000 = var0.field_70759_as;
      }

      float var4 = var10000;

      label27: {
         try {
            if (var3) {
               var10000 = var0.I();
               break label27;
            }
         } catch (NumberFormatException var7) {
            throw a((Exception)var7);
         }

         var10000 = var0.field_70758_at;
      }

      float var5 = var10000;
      Float var6 = f_.a(var0, var1);
      if (var6 != null) {
         var4 = var6;
         var5 = var6;
      }

      var2.field_70177_z = var4;
      var2.field_70760_ar = var5;
      var2.field_70761_aq = var4;
      var2.field_70758_at = var5;
      var2.field_70759_as = var4;
   }

   public static boolean b(f8 param0) {
      // $FF: Couldn't be decompiled
   }

   public static Vec3d b(f_ var0, float var1) {
      return ak.a(var0, i.field_71439_g, var1).func_178787_e(var0.b((String)"mangPos"));
   }

   public static Vec3d a(f_ var0, float var1) {
      return ak.a(var0, var1).func_178787_e(var0.b((String)"mangPos"));
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
