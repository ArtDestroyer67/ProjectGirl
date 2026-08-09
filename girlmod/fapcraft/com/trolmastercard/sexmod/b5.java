package com.trolmastercard.sexmod;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class b5 extends GuiScreen {
   List<EntityLivingBase> a = new ArrayList();
   int b = 0;
   static float c = 0.0F;

   public b5(HashMap<fy, String> var1) {
      this.field_146297_k = Minecraft.func_71410_x();

      for(fy var5 : fy.values()) {
         try {
            if (var5.isNpcOnly) {
               continue;
            }
         } catch (Exception var10) {
            throw a(var10);
         }

         try {
            Constructor var6 = var5.npcClass.getConstructor(World.class);
            em var7 = (em)var6.newInstance(this.field_146297_k.field_71441_e);
            var7.b(true);
            this.a.add(var7);
            String var8 = (String)var1.get(var5);
            if (var8 != null) {
               var7.a(em.c(var8));
            }
         } catch (Exception var9) {
            var9.printStackTrace();
         }
      }

      this.a.add(this.field_146297_k.field_71439_g);
   }

   public void func_73863_a(int var1, int var2, float var3) {
      super.func_73863_a(var1, var2, var3);
      this.field_146292_n.clear();
      a(this.field_146294_l / 2, this.field_146295_m / 2 + 20, 30, (EntityLivingBase)this.a.get(this.b));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 30, this.field_146295_m / 2 - 10, 20, 20, ">"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 - 50, this.field_146295_m / 2 - 10, 20, 20, "<"));
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 30, this.field_146295_m / 2 + 30, 60, 20, "pick"));
   }

   protected void func_146284_a(GuiButton param1) {
      // $FF: Couldn't be decompiled
   }

   public boolean func_73868_f() {
      return false;
   }

   public static void a(int var0, int var1, int var2, EntityLivingBase var3) {
      float var4 = var3.field_70761_aq;
      float var5 = var3.field_70177_z;
      float var6 = var3.field_70125_A;
      float var7 = var3.field_70758_at;
      float var8 = var3.field_70759_as;

      try {
         if (!(var3 instanceof EntityPlayer)) {
            var3.field_70165_t = (double)0.0F;
            var3.field_70163_u = (double)0.0F;
            var3.field_70161_v = (double)0.0F;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      var3.field_70761_aq = 0.0F;
      var3.field_70177_z = 0.0F;
      var3.field_70125_A = 0.0F;
      var3.field_70758_at = 0.0F;
      var3.field_70759_as = 0.0F;
      float var9 = (float)Minecraft.func_175610_ah();
      if (var9 == 0.0F) {
         var9 = 0.1F;
      }

      c += 60.0F / var9;
      GlStateManager.func_179142_g();
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)var0, (float)var1, 50.0F);
      GlStateManager.func_179152_a((float)(-var2), (float)var2, (float)var2);
      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.func_74519_b();
      GlStateManager.func_179114_b(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(c, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179109_b(0.0F, 0.0F, 0.0F);
      RenderManager var10 = Minecraft.func_71410_x().func_175598_ae();
      var10.func_178631_a(180.0F);
      var10.func_178633_a(false);
      var10.func_188391_a(var3, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, 1.2345679F, false);
      var10.func_178633_a(true);
      GlStateManager.func_179121_F();
      RenderHelper.func_74518_a();
      GlStateManager.func_179101_C();
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179090_x();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      var3.field_70761_aq = var4;
      var3.field_70177_z = var5;
      var3.field_70125_A = var6;
      var3.field_70758_at = var7;
      var3.field_70759_as = var8;
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
