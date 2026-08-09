package com.trolmastercard.sexmod;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public abstract class d_<T extends em & IAnimatable> extends GeoEntityRenderer<T> implements c3 {
   protected static final ResourceLocation e = new ResourceLocation("sexmod", "textures/line.png");
   static final float m = 1.5F;
   protected double c;
   protected T j;
   protected static Minecraft i;
   protected static HashMap<UUID, ResourceLocation> l = new HashMap();
   Color f = new Color(245, 199, 165);
   Color o = new Color(245, 157, 169);
   boolean h = false;
   protected HashSet<String> p = new HashSet();
   Integer k = null;
   Integer b = null;
   Integer d = null;
   float a = 0.0F;
   public static BufferBuilder n;
   Matrix4f g = null;
   protected GeoBone q = null;

   public d_(RenderManager var1, AnimatedGeoModel<T> var2, double var3) {
      super(var1, var2);
      this.c = var3;
      i = Minecraft.func_71410_x();
      this.field_76989_e = 0.2F;
   }

   protected ResourceLocation d(T var1) throws IOException {
      ResourceLocation var2;
      label38: {
         try {
            if (!(var1.field_70170_p instanceof gj) && var1.ae() != null) {
               break label38;
            }
         } catch (IOException var5) {
            throw b(var5);
         }

         var2 = (ResourceLocation)l.get(i.func_110432_I().func_148256_e().getId());

         try {
            if (var2 == null) {
               return this.a(i.func_110432_I().func_148256_e().getId(), var1.field_70170_p);
            }

            return var2;
         } catch (IOException var4) {
            throw b(var4);
         }
      }

      var2 = (ResourceLocation)l.get(var1.ae());

      try {
         if (var2 == null) {
            return this.a(var1.ae(), var1.field_70170_p);
         }
      } catch (IOException var3) {
         throw b(var3);
      }

      return var2;
   }

   protected ResourceLocation a(UUID var1, World var2) throws IOException {
      BufferedImage var3;
      try {
         var3 = y.a(var1);
         Graphics var4 = var3.getGraphics();
         var4.setColor(this.f);
         var4.fillRect(0, 0, 4, 3);
         var4.setColor(this.o);
         var4.fillRect(4, 0, 3, 3);
      } catch (Exception var6) {
         try {
            if (!this.h) {
               this.h = true;
            }
         } catch (Exception var5) {
            throw b(var5);
         }

         var3 = ImageIO.read(i.func_110442_L().func_110536_a(new ResourceLocation("sexmod", "textures/player/steve.png")).func_110527_b());
      }

      l.put(var1, this.field_76990_c.field_78724_e.func_110578_a("player" + var1, new DynamicTexture(var3)));
      return (ResourceLocation)l.get(var1);
   }

   protected static float a(em var0, float var1) {
      float var10000;
      try {
         if (var0.Q()) {
            var10000 = var0.I();
            return var10000;
         }
      } catch (IllegalStateException var2) {
         throw b(var2);
      }

      var10000 = b6.a(var0.field_70760_ar, var0.field_70761_aq, var1);
      return var10000;
   }

   protected void d() {
   }

   protected void b() {
   }

   float a(World var1, Vec3d var2, float var3, float var4) {
      RayTraceResult var5 = this.a(var2, var2.func_178787_e(ck.a(new Vec3d((double)0.0F, (double)0.0F, (double)-4.0F), var3, var4)), var1);

      try {
         if (var5 == null) {
            return 4.0F;
         }
      } catch (IllegalStateException var8) {
         throw b(var8);
      }

      Vec3d var6 = var5.field_72307_f;

      try {
         if (var6 == null) {
            return 4.0F;
         }
      } catch (IllegalStateException var7) {
         throw b(var7);
      }

      return (float)var2.func_72438_d(var6);
   }

   boolean a(T var1, EntityPlayer var2) {
      try {
         if (var1 instanceof ei) {
            return true;
         }
      } catch (IllegalStateException var20) {
         throw b(var20);
      }

      World var3 = var1.field_70170_p;
      Vec3d var4 = var1.func_174791_d();
      float var5 = var1.field_70130_N * 1.5F;
      float var6 = var1.field_70131_O * 1.5F;
      Vec3d var7 = var2.func_174791_d().func_72441_c((double)0.0F, (double)var2.func_70047_e(), (double)0.0F);
      int var8 = i.field_71474_y.field_74320_O;

      try {
         if (var8 != 0) {
            return true;
         }
      } catch (IllegalStateException var19) {
         throw b(var19);
      }

      if (var8 > 0) {
         float var9 = var2.field_70177_z;
         float var10 = var2.field_70125_A;
         if (var8 == 2) {
            var10 += 180.0F;
         }

         float var11 = 4.0F;
         Vec3d var12 = var7.func_72441_c((double)(MathHelper.func_76126_a(var9 * ((float)Math.PI / 180F)) * MathHelper.func_76134_b(var10 * ((float)Math.PI / 180F)) * var11), (double)(MathHelper.func_76126_a(var10 * ((float)Math.PI / 180F)) * var11), (double)(-MathHelper.func_76134_b(var9 * ((float)Math.PI / 180F)) * MathHelper.func_76134_b(var10 * ((float)Math.PI / 180F)) * var11));
         BlockPos var13 = new BlockPos(var12);
         boolean var14 = var3.func_175623_d(var13);
         if (!var14) {
            var7 = var12;
         } else if (var3.func_175623_d(var13.func_177982_a(0, 1, 0))) {
            var7 = new Vec3d(var12.field_72450_a, (double)(var13.func_177956_o() + 1), var12.field_72449_c);
         }
      }

      Vec3d[] var21 = new Vec3d[]{var4.func_72441_c((double)(-var5 / 2.0F), (double)0.0F, (double)(-var5 / 2.0F)), var4.func_72441_c((double)(-var5 / 2.0F), (double)0.0F, (double)(var5 / 2.0F)), var4.func_72441_c((double)(var5 / 2.0F), (double)0.0F, (double)(-var5 / 2.0F)), var4.func_72441_c((double)(var5 / 2.0F), (double)0.0F, (double)(var5 / 2.0F)), var4.func_72441_c((double)(-var5 / 2.0F), (double)var6, (double)(-var5 / 2.0F)), var4.func_72441_c((double)(-var5 / 2.0F), (double)var6, (double)(var5 / 2.0F)), var4.func_72441_c((double)(var5 / 2.0F), (double)var6, (double)(-var5 / 2.0F)), var4.func_72441_c((double)(var5 / 2.0F), (double)var6, (double)(var5 / 2.0F))};

      for(Vec3d var25 : var21) {
         RayTraceResult var26 = this.a(var7, var25, var3);

         try {
            if (var26 == null) {
               return true;
            }
         } catch (IllegalStateException var18) {
            throw b(var18);
         }

         IBlockState var15 = var3.func_180495_p(var26.func_178782_a());

         try {
            if (var15.func_185895_e()) {
               return true;
            }
         } catch (IllegalStateException var16) {
            throw b(var16);
         }

         try {
            if (var15.func_177230_c().func_180664_k() != BlockRenderLayer.SOLID) {
               return true;
            }
         } catch (IllegalStateException var17) {
            throw b(var17);
         }
      }

      return false;
   }

   HashSet<String> a(Boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   public void a(GeoModel param1, T param2, float param3, float param4, float param5, float param6, float param7) {
      // $FF: Couldn't be decompiled
   }

   protected void a(GeoModel var1, BufferBuilder var2, T var3, float var4, float var5, float var6, float var7, float var8) {
      GeoBone var9 = null;

      for(GeoBone var11 : var1.topLevelBones) {
         if (var11.getName().equals("steve")) {
            var9 = var11;
         } else {
            this.renderRecursively(var2, var11, var4, var5, var6, var7);
         }
      }

      label30: {
         IOException var14;
         try {
            Tessellator.func_178181_a().func_78381_a();
            this.b();
            if (var9 == null) {
               return;
            }

            var2.func_181668_a(7, DefaultVertexFormats.field_181712_l);

            try {
               Minecraft.func_71410_x().field_71446_o.func_110577_a(this.d(this.j));
               break label30;
            } catch (IOException var12) {
               var14 = var12;
            }
         } catch (IllegalStateException var13) {
            throw b(var13);
         }

         var14.printStackTrace();
      }

      this.renderRecursively(var2, var9, var4, var5, var6, this.j.v());
      Tessellator.func_178181_a().func_78381_a();
   }

   String a(String var1) {
      StringBuilder var2 = new StringBuilder();

      try {
         BufferedReader var3 = new BufferedReader(new FileReader(var1));

         while(true) {
            String var4;
            String var10000 = var4 = var3.readLine();

            try {
               if (var10000 == null) {
                  break;
               }

               var2.append(var4).append("//\n");
            } catch (IOException var5) {
               throw b(var5);
            }
         }

         var3.close();
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      return var2.toString();
   }

   protected void a(double var1, double var3, double var5) {
      try {
         if (this.j.h()) {
            return;
         }
      } catch (IllegalStateException var9) {
         throw b(var9);
      }

      try {
         if (this.j.y().hideNameTag) {
            return;
         }
      } catch (IllegalStateException var7) {
         throw b(var7);
      }

      try {
         if (i.func_175598_ae().field_78734_h == null) {
            return;
         }
      } catch (IllegalStateException var8) {
         throw b(var8);
      }

      this.func_147906_a(this.j, this.j.ab(), var1, var3 + (double)this.j.i(), var5, 300);
   }

   Vec3d a(EntityPlayer var1, float var2) {
      EntityLiving var3 = (EntityLiving)var1.func_184187_bx();
      EntityPlayerSP var4 = i.field_71439_g;
      Vec3d var5 = var3.func_70040_Z();
      Vec3d var6 = b6.a(new Vec3d(var1.field_70142_S, var1.field_70137_T, var1.field_70136_U), var1.func_174791_d(), (double)var2);
      Vec3d var7 = b6.a(new Vec3d(var4.field_70142_S, var4.field_70137_T, var4.field_70136_U), ((EntityPlayer)var4).func_174791_d(), (double)var2);
      var7 = var6.func_178788_d(var7);
      this.j.field_70761_aq = var3.field_70761_aq;
      return new Vec3d(var7.field_72450_a + var5.field_72450_a * (double)-0.5F, var7.field_72448_b + (double)0.15F, var7.field_72449_c + var5.field_72449_c * (double)-0.5F);
   }

   protected Vec3d a(T var1, float var2, Vec3d var3) {
      return var3;
   }

   Vec3d a(T param1, float param2, double param3, double param5, double param7) {
      // $FF: Couldn't be decompiled
   }

   protected void b(T var1) {
   }

   public void a(T param1, double param2, double param4, double param6, float param8, float param9) {
      // $FF: Couldn't be decompiled
   }

   void a(T var1) {
      ArrayList var2 = new ArrayList(cv.e);
      var2.addAll(var1.p);

      for(String var4 : var2) {
         em var10000;
         String var10001;
         boolean var10002;
         label23: {
            try {
               var10000 = var1;
               var10001 = var4;
               if (!var1.h()) {
                  var10002 = true;
                  break label23;
               }
            } catch (IllegalStateException var8) {
               throw b(var8);
            }

            var10002 = false;
         }

         MatrixStack var5 = var10000.a(var10001, var10002);
         Matrix4f var6 = var5.getModelMatrix();
         Vec3d var7 = new Vec3d((double)(-var6.m03), (double)var6.m13, (double)(-var6.m23));
         var1.a(var4, var7);
      }

   }

   @Nullable
   protected f7 e(T var1) {
      return null;
   }

   public Entity c(em var1) {
      return var1;
   }

   void a(em var1, float var2, f7 var3) {
      EntityPlayerSP var4 = i.field_71439_g;
      var3 = new f7(var3.a / 255.0F, var3.c / 255.0F, var3.b / 255.0F);
      Tessellator var5 = Tessellator.func_178181_a();
      BufferBuilder var6 = var5.func_178180_c();
      GlStateManager.func_179094_E();
      GlStateManager.func_179137_b((double)0.0F, 0.01, (double)0.0F);
      Entity var7 = this.c(var1);

      Vec3d var10000;
      label17: {
         try {
            if (var1.Q()) {
               var10000 = var1.o();
               break label17;
            }
         } catch (IllegalStateException var12) {
            throw b(var12);
         }

         var10000 = b6.a(new Vec3d(var7.field_70142_S, var7.field_70137_T, var7.field_70136_U), var7.func_174791_d(), (double)var2);
      }

      Vec3d var8 = var10000;
      Vec3d var9 = b6.a(new Vec3d(var4.field_70142_S, var4.field_70137_T, var4.field_70136_U), ((EntityPlayer)var4).func_174791_d(), (double)var2);
      Vec3d var10 = var8.func_178788_d(var9);
      GlStateManager.func_179137_b(var10.field_72450_a, var10.field_72448_b, var10.field_72449_c);
      i.func_110434_K().func_110577_a(e);
      float var11 = a(var1, var2, 1.0F, 5.0F);
      this.b(var5, var6, var1, var3, var11);
      GlStateManager.func_179121_F();
   }

   protected static float a(em var0, float var1, float var2, float var3) {
      EntityPlayerSP var4 = i.field_71439_g;
      Entity var5 = ((d_)i.func_175598_ae().func_78713_a(var0)).c(var0);

      Vec3d var10000;
      label17: {
         try {
            if (var0.Q()) {
               var10000 = var0.o();
               break label17;
            }
         } catch (IllegalStateException var11) {
            throw b(var11);
         }

         var10000 = b6.a(new Vec3d(var5.field_70142_S, var5.field_70137_T, var5.field_70136_U), var5.func_174791_d(), (double)var1);
      }

      Vec3d var6 = var10000;
      Vec3d var7 = b6.a(new Vec3d(var4.field_70142_S, var4.field_70137_T, var4.field_70136_U), ((EntityPlayer)var4).func_174791_d(), (double)var1);
      Vec3d var8 = ActiveRenderInfo.getCameraPosition().func_178787_e(var7);
      float var9 = (float)var8.func_72438_d(var6);
      float var10 = Math.abs(var9) / 5.0F;
      return b6.a(var3, var2, be.b(var10, 0.0F, 1.0F));
   }

   protected void b(Tessellator var1, BufferBuilder var2, em var3, f7 var4, float var5) {
   }

   protected static void a(BufferBuilder var0, Tessellator var1, em var2, String var3, String var4, float var5, float var6, float var7, float var8) {
      var0.func_181668_a(1, DefaultVertexFormats.field_181709_i);
      GlStateManager.func_187441_d(var8);
      Vec3d var9 = var2.b(var3);
      Vec3d var10 = var2.b(var4);
      var0.func_181662_b(var9.field_72450_a, var9.field_72448_b, var9.field_72449_c).func_187315_a((double)0.0F, (double)0.0F).func_181666_a(var5, var6, var7, 1.0F).func_181675_d();
      var0.func_181662_b(var10.field_72450_a, var10.field_72448_b, var10.field_72449_c).func_187315_a((double)0.0F, (double)0.0F).func_181666_a(var5, var6, var7, 1.0F).func_181675_d();
      var1.func_78381_a();
   }

   protected static void a(Tessellator var0, BufferBuilder var1, em var2, f7 var3, float var4) {
      a(var1, var0, var2, "braStringMidStartR", "braStringMidMid1R", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid1R", "braStringMidMid2R", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid2R", "braStringMidMid3R", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid3R", "braStringMidEndR", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidEndR", "braStringBackR", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringBackR", "braStringRightEndR", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringRightEndR", "braStringRightStartR", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringRightR", "braStringRightL", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidStartL", "braStringMidMid1L", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid1L", "braStringMidMid2L", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid2L", "braStringMidMid3L", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidMid3L", "braStringMidEndL", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringMidEndL", "braStringBackL", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringBackL", "braStringLeftEndL", var3.a, var3.c, var3.b, var4);
      a(var1, var0, var2, "braStringLeftEndL", "braStringLeftStartL", var3.a, var3.c, var3.b, var4);
   }

   protected void b(T var1, float var2, float var3, float var4) {
      try {
         super.applyRotations(var1, var2, var3, var4);
         if (!(var1 instanceof ei)) {
            return;
         }
      } catch (IllegalStateException var22) {
         throw b(var22);
      }

      UUID var5 = ((ei)var1).m();

      try {
         if (var5 == null) {
            return;
         }
      } catch (IllegalStateException var21) {
         throw b(var21);
      }

      EntityPlayer var6 = var1.field_70170_p.func_152378_a(var5);

      try {
         if (var6 == null) {
            return;
         }
      } catch (IllegalStateException var18) {
         throw b(var18);
      }

      try {
         if (!var6.func_184613_cA()) {
            return;
         }
      } catch (IllegalStateException var20) {
         throw b(var20);
      }

      float var7 = (float)var6.func_184599_cB() + var4;
      float var8 = MathHelper.func_76131_a(var7 * var7 / 100.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(var8 * (-90.0F - var6.field_70125_A), 1.0F, 0.0F, 0.0F);
      Vec3d var9 = var6.func_70676_i(var4);
      double var10 = var6.field_70159_w * var6.field_70159_w + var6.field_70179_y * var6.field_70179_y;
      double var12 = var9.field_72450_a * var9.field_72450_a + var9.field_72449_c * var9.field_72449_c;

      try {
         if (!(var10 > (double)0.0F) || !(var12 > (double)0.0F)) {
            return;
         }
      } catch (IllegalStateException var19) {
         throw b(var19);
      }

      double var14 = (var6.field_70159_w * var9.field_72450_a + var6.field_70179_y * var9.field_72449_c) / (Math.sqrt(var10) * Math.sqrt(var12));
      double var16 = var6.field_70159_w * var9.field_72449_c - var6.field_70179_y * var9.field_72450_a;
      GlStateManager.func_179114_b((float)(Math.signum(var16) * Math.acos(var14)) * 180.0F / (float)Math.PI, 0.0F, 1.0F, 0.0F);
   }

   protected void a(BufferBuilder var1, String var2, GeoBone var3) {
   }

   protected void a(em var1, double var2, double var4, double var6, float var8) {
      Entity var9 = var1.func_110166_bE();
      var4 -= (1.6 - (double)var1.field_70131_O) * (double)0.5F;
      Tessellator var10 = Tessellator.func_178181_a();
      BufferBuilder var11 = var10.func_178180_c();
      double var12 = (double)b6.a(var9.field_70126_B, var9.field_70177_z, var8 * 0.5F) * (double)((float)Math.PI / 180F);
      double var14 = (double)b6.a(var9.field_70127_C, var9.field_70125_A, var8 * 0.5F) * (double)((float)Math.PI / 180F);
      double var16 = Math.cos(var12);
      double var18 = Math.sin(var12);
      double var20 = Math.sin(var14);
      if (var9 instanceof EntityHanging) {
         var16 = (double)0.0F;
         var18 = (double)0.0F;
         var20 = (double)-1.0F;
      }

      double var22 = Math.cos(var14);
      double var24 = b6.b(var9.field_70169_q, var9.field_70165_t, (double)var8) - var16 * 0.7 - var18 * (double)0.5F * var22;
      double var26 = b6.b(var9.field_70167_r + (double)var9.func_70047_e() * 0.7, var9.field_70163_u + (double)var9.func_70047_e() * 0.7, (double)var8) - var20 * (double)0.5F - (double)0.25F;
      double var28 = b6.b(var9.field_70166_s, var9.field_70161_v, (double)var8) - var18 * 0.7 + var16 * (double)0.5F * var22;
      double var30 = (double)b6.a(var1.field_70760_ar, var1.field_70761_aq, var8) * (double)((float)Math.PI / 180F) + (Math.PI / 2D);
      var16 = Math.cos(var30) * (double)var1.field_70130_N * 0.4;
      var18 = Math.sin(var30) * (double)var1.field_70130_N * 0.4;
      double var32 = b6.b(var1.field_70169_q, var1.field_70165_t, (double)var8) + var16;
      double var34 = b6.b(var1.field_70167_r, var1.field_70163_u, (double)var8);
      double var36 = b6.b(var1.field_70166_s, var1.field_70161_v, (double)var8) + var18;
      var2 += var16;
      var6 += var18;
      double var38 = (double)((float)(var24 - var32));
      double var40 = (double)((float)(var26 - var34));
      double var42 = (double)((float)(var28 - var36));
      GlStateManager.func_179090_x();
      GlStateManager.func_179140_f();
      GlStateManager.func_179129_p();
      var11.func_181668_a(5, DefaultVertexFormats.field_181706_f);

      for(int var44 = 0; var44 <= 24; ++var44) {
         float var45 = 0.5F;
         float var46 = 0.4F;
         float var47 = 0.3F;
         if (var44 % 2 == 0) {
            var45 *= 0.7F;
            var46 *= 0.7F;
            var47 *= 0.7F;
         }

         float var48 = (float)var44 / 24.0F;
         var11.func_181662_b(var2 + var38 * (double)var48 + (double)0.0F, var4 + var40 * (double)(var48 * var48 + var48) * (double)0.5F + (double)((24.0F - (float)var44) / 18.0F + 0.125F), var6 + var42 * (double)var48).func_181666_a(var45, var46, var47, 1.0F).func_181675_d();
         var11.func_181662_b(var2 + var38 * (double)var48 + 0.025, var4 + var40 * (double)(var48 * var48 + var48) * (double)0.5F + (double)((24.0F - (float)var44) / 18.0F + 0.125F) + 0.025, var6 + var42 * (double)var48).func_181666_a(var45, var46, var47, 1.0F).func_181675_d();
      }

      var10.func_78381_a();
      var11.func_181668_a(5, DefaultVertexFormats.field_181706_f);

      for(int var54 = 0; var54 <= 24; ++var54) {
         float var55 = 0.5F;
         float var56 = 0.4F;
         float var57 = 0.3F;
         if (var54 % 2 == 0) {
            var55 *= 0.7F;
            var56 *= 0.7F;
            var57 *= 0.7F;
         }

         float var58 = (float)var54 / 24.0F;
         var11.func_181662_b(var2 + var38 * (double)var58 + (double)0.0F, var4 + var40 * (double)(var58 * var58 + var58) * (double)0.5F + (double)((24.0F - (float)var54) / 18.0F + 0.125F) + 0.025, var6 + var42 * (double)var58).func_181666_a(var55, var56, var57, 1.0F).func_181675_d();
         var11.func_181662_b(var2 + var38 * (double)var58 + 0.025, var4 + var40 * (double)(var58 * var58 + var58) * (double)0.5F + (double)((24.0F - (float)var54) / 18.0F + 0.125F), var6 + var42 * (double)var58 + 0.025).func_181666_a(var55, var56, var57, 1.0F).func_181675_d();
      }

      var10.func_78381_a();
      GlStateManager.func_179145_e();
      GlStateManager.func_179098_w();
      GlStateManager.func_179089_o();
   }

   public void renderRecursively(BufferBuilder param1, GeoBone param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   protected Vector4f a(float var1, float var2, float var3) {
      return new Vector4f(var1, var2, var3, 0.0F);
   }

   boolean b(String var1) {
      try {
         if (!var1.startsWith("armor")) {
            return true;
         }
      } catch (IllegalStateException var2) {
         throw b(var2);
      }

      return this.j instanceof e2;
   }

   protected Vector4f a(String var1, float var2, float var3, float var4) {
      try {
         if (!var1.startsWith("armor")) {
            return this.a(var2, var3, var4);
         }
      } catch (IllegalStateException var19) {
         throw b(var19);
      }

      try {
         if (!(this.j instanceof e2)) {
            return this.a(var2, var3, var4);
         }
      } catch (IllegalStateException var15) {
         throw b(var15);
      }

      try {
         if ((Integer)this.j.m.func_187225_a(em.D) == 0) {
            return this.a(var2, var3, var4);
         }
      } catch (IllegalStateException var18) {
         throw b(var18);
      }

      GeoModelProvider var5 = this.getGeoModelProvider();

      try {
         if (!(var5 instanceof cv)) {
            return this.a(var2, var3, var4);
         }
      } catch (IllegalStateException var17) {
         throw b(var17);
      }

      cv var6 = (cv)var5;
      ItemStack var7 = var6.a(this.j, var1);

      try {
         if (!(var7.func_77973_b() instanceof ItemArmor)) {
            return this.a(var2, var3, var4);
         }
      } catch (IllegalStateException var16) {
         throw b(var16);
      }

      ItemArmor var8 = (ItemArmor)var7.func_77973_b();
      ItemArmor.ArmorMaterial var9 = var8.func_82812_d();
      float var10 = 0.0F;
      switch (var9) {
         case GOLD:
            var10 = 1.0F;
            break;
         case CHAIN:
         case IRON:
            var10 = 2.0F;
            break;
         case LEATHER:
            var10 = 4.0F;
            int var11 = var8.func_82814_b(var7);
            float var12 = (float)(var11 >> 16 & 255) / 255.0F;
            float var13 = (float)(var11 >> 8 & 255) / 255.0F;
            float var14 = (float)(var11 & 255) / 255.0F;
            var2 *= var12;
            var3 *= var13;
            var4 *= var14;
      }

      return new Vector4f(var2, var3, var4, 72.0F * var10 / 4096.0F);
   }

   public void a(T var1, float var2, float var3, float var4, float var5, float var6) {
      this.g = (Matrix4f)MATRIX_STACK.getModelMatrix().clone();
   }

   public void a(BufferBuilder param1, GeoBone param2, float param3, float param4, float param5, float param6, double param7) {
      // $FF: Couldn't be decompiled
   }

   protected boolean c() {
      try {
         if (!this.j.n()) {
            return true;
         }
      } catch (IllegalStateException var1) {
         throw b(var1);
      }

      boolean var10000;
      try {
         if (i.field_71474_y.field_74320_O != 0) {
            var10000 = true;
            return var10000;
         }
      } catch (IllegalStateException var2) {
         throw b(var2);
      }

      var10000 = false;
      return var10000;
   }

   public void a(BufferBuilder param1, GeoCube param2, float param3, float param4, float param5, float param6, double param7) {
      // $FF: Couldn't be decompiled
   }

   protected ItemStack a() {
      String var1 = (String)this.j.m.func_187225_a(em.h);
      byte var2 = -1;

      label87: {
         label86: {
            label85: {
               label84: {
                  label83: {
                     label82: {
                        try {
                           switch (var1.hashCode()) {
                              case -20842805:
                                 break;
                              case 113766:
                                 break label85;
                              case 64419037:
                                 break label83;
                              case 95761198:
                                 if (!var1.equals("doggy")) {
                                    break label87;
                                 }
                                 break label86;
                              case 109773592:
                                 break label82;
                              case 2014427283:
                                 break label84;
                              default:
                                 break label87;
                           }
                        } catch (IllegalStateException var4) {
                           throw b(var4);
                        }

                        if (var1.equals("blowjob")) {
                           var2 = 1;
                        }
                        break label87;
                     }

                     if (var1.equals("strip")) {
                        var2 = 2;
                     }
                     break label87;
                  }

                  if (var1.equals("boobjob")) {
                     var2 = 3;
                  }
                  break label87;
               }

               if (var1.equals("touch_boobs")) {
                  var2 = 4;
               }
               break label87;
            }

            if (var1.equals("sex")) {
               var2 = 5;
            }
            break label87;
         }

         var2 = 0;
      }

      try {
         switch (var2) {
            case 0:
               return new ItemStack(Items.field_151045_i, 2);
            case 1:
               return new ItemStack(Items.field_151166_bC, 3);
            case 2:
               return new ItemStack(Items.field_151043_k, 1);
            case 3:
               return new ItemStack(Items.field_151079_bi, 2);
            case 4:
               return new ItemStack(Items.field_151115_aP, 2, 1);
            case 5:
               return new ItemStack(Items.field_151115_aP, 3, 0);
            default:
               return null;
         }
      } catch (IllegalStateException var3) {
         throw b(var3);
      }
   }

   protected void b(BufferBuilder param1, GeoBone param2) {
      // $FF: Couldn't be decompiled
   }

   protected ItemStack a(@Nullable ItemStack var1) {
      return var1;
   }

   protected void a(BufferBuilder param1, GeoBone param2) {
      // $FF: Couldn't be decompiled
   }

   RayTraceResult a(Vec3d param1, Vec3d param2, World param3) {
      // $FF: Couldn't be decompiled
   }

   private static Exception b(Exception var0) {
      return var0;
   }
}
