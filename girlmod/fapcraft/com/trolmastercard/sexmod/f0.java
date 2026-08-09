package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class f0 extends GeoItemRenderer<ap> {
   Minecraft a = Minecraft.func_71410_x();
   static ResourceLocation b = null;

   public f0() {
      super(new a9());
   }

   ResourceLocation a() {
      if (b == null) {
         try {
            URL var1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + Minecraft.func_71410_x().field_71439_g.getPersistentID().toString().replace("-", ""));
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.openStream()));
            String var3 = (String)var2.lines().collect(Collectors.joining());
            int var4 = var3.indexOf("\"value\" : ");
            int var5 = var4 + 11;
            StringBuilder var6 = new StringBuilder();
            int var7 = 0;

            try {
               while(var3.charAt(var5 + var7) != '"') {
                  var6.append(var3.charAt(var5 + var7));
                  ++var7;
               }
            } catch (Exception var19) {
               throw a(var19);
            }

            String var21 = new String(Base64.getDecoder().decode(var6.toString()));
            int var8 = var21.indexOf("\"url\" : ");
            int var9 = var8 + 9;
            StringBuilder var10 = new StringBuilder();
            int var11 = 0;

            try {
               while(var21.charAt(var9 + var11) != '"') {
                  var10.append(var21.charAt(var9 + var11));
                  ++var11;
               }
            } catch (Exception var18) {
               throw a(var18);
            }

            URL var22 = new URL(var10.toString());
            BufferedImage var12 = ImageIO.read(var22);
            BufferedImage var13 = ImageIO.read(this.a.func_110442_L().func_110536_a((new a9()).c(new ap())).func_110527_b());

            for(int var14 = 0; var14 < var13.getWidth(); ++var14) {
               for(int var15 = 0; var15 < var13.getHeight(); ++var15) {
                  int var16 = var12.getRGB(var14, var15);

                  try {
                     if (var16 != 0) {
                        var13.setRGB(var14, var15, var16);
                     }
                  } catch (Exception var17) {
                     throw a(var17);
                  }
               }
            }

            b = Minecraft.func_71410_x().func_175598_ae().field_78724_e.func_110578_a("lamptex", new DynamicTexture(var13));
         } catch (Exception var20) {
            b = (new a9()).c(new ap());
         }
      }

      return b;
   }

   public void a(GeoModel var1, ap var2, float var3, float var4, float var5, float var6, float var7) {
      GlStateManager.func_179129_p();
      GlStateManager.func_179091_B();
      this.renderEarly(var2, var3, var4, var5, var6, var7);
      this.renderLate(var2, var3, var4, var5, var6, var7);
      BufferBuilder var8 = Tessellator.func_178181_a().func_178180_c();
      var8.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      for(GeoBone var10 : var1.topLevelBones) {
         this.a(var8, var2, var10, var4, var5, var6, var7);
      }

      Tessellator.func_178181_a().func_78381_a();
      this.renderAfter(var2, var3, var4, var5, var6, var7);
      GlStateManager.func_179101_C();
      GlStateManager.func_179089_o();
   }

   public void a(BufferBuilder var1, ap var2, GeoBone var3, float var4, float var5, float var6, float var7) {
      try {
         MATRIX_STACK.push();
         MATRIX_STACK.translate(var3);
         MATRIX_STACK.moveToPivot(var3);
         MATRIX_STACK.rotate(var3);
         MATRIX_STACK.scale(var3);
         MATRIX_STACK.moveBackFromPivot(var3);
         this.a.field_71446_o.func_110577_a(this.a());
         if (this.a(var3.getName())) {
            this.b(var1, var2, var3, var4, var5, var6, var7);
         }
      } catch (RuntimeException var8) {
         throw a((Exception)var8);
      }

      MATRIX_STACK.pop();
   }

   boolean a(String param1) {
      // $FF: Couldn't be decompiled
   }

   void b(BufferBuilder var1, ap var2, GeoBone var3, float var4, float var5, float var6, float var7) {
      if (!var3.isHidden) {
         for(GeoCube var9 : var3.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.func_179094_E();
            this.renderCube(var1, var9, var4, var5, var6, var7);
            GlStateManager.func_179121_F();
            MATRIX_STACK.pop();
         }

         for(GeoBone var11 : var3.childBones) {
            this.a(var1, var2, var11, var4, var5, var6, var7);
         }
      }

   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
