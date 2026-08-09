package com.trolmastercard.sexmod;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class m extends GuiScreen {
   final em g;
   final EntityPlayer i;
   final String[] h;
   @Nullable
   final ItemStack[] f;
   static final ResourceLocation c = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
   EntityDataManager l;
   final boolean k;
   float m = 0.0F;
   float n = 0.0F;
   String[] a = new String[]{"action.names.followme", "action.names.stopfollowme", "action.names.gohome", "action.names.setnewhome", "action.names.equipment"};
   int[] d = new int[]{0, 0, 0, 0, 0};
   int[] j = new int[]{64, 80, 47, 32, 96};
   int[] b = new int[]{4, 4, 5, 5, 4};
   int[] e = new int[]{50, 90, 50, 80, 60};

   public m(em var1, EntityPlayer var2) {
      this.g = var1;
      this.i = var2;
      this.h = new String[0];
      this.f = new ItemStack[0];
      this.k = true;
      this.l = var1.func_184212_Q();
   }

   public m(em var1, EntityPlayer var2, String[] var3, @Nullable ItemStack[] var4, boolean var5) {
      this.g = var1;
      this.i = var2;
      this.h = var3;
      this.f = var4;
      this.k = var5;
      this.l = var1.func_184212_Q();
   }

   public boolean func_73868_f() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void func_146281_b() {
      super.func_146281_b();
      this.g.ac();
   }

   protected void func_146284_a(GuiButton param1) {
      // $FF: Couldn't be decompiled
   }

   void a(GuiButton var1) {
      String var2;
      if (var1.field_146127_k < 5) {
         var2 = this.a[var1.field_146127_k];
      } else {
         var2 = this.h[var1.field_146127_k - 5];
      }

      this.g.a(var2, this.i.getPersistentID());
      Minecraft.func_71410_x().field_71439_g.func_71053_j();
   }

   public void func_73863_a(int param1, int param2, float param3) {
      // $FF: Couldn't be decompiled
   }

   void a(int param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   void a(List<String> var1, int var2, int var3, FontRenderer var4) {
      GlStateManager.func_179101_C();
      RenderHelper.func_74518_a();
      GlStateManager.func_179140_f();
      int var5 = 0;

      for(String var7 : var1) {
         int var8 = this.field_146289_q.func_78256_a(var7);
         if (var8 > var5) {
            var5 = var8;
         }
      }

      int var12 = var2 + 12;
      int var13 = var3 - 12;
      int var14 = 8;
      if (var1.size() > 1) {
         var14 += 2 + (var1.size() - 1) * 10;
      }

      if (var12 + var5 > this.field_146294_l) {
         var12 -= 28 + var5;
      }

      if (var13 + var14 + 6 > this.field_146295_m) {
         var13 = this.field_146295_m - var14 - 6;
      }

      this.func_73733_a(var12 - 3, var13 - 4, var12 + var5 + 3, var13 - 3, -267386864, -267386864);
      this.func_73733_a(var12 - 3, var13 + var14 + 3, var12 + var5 + 3, var13 + var14 + 4, -267386864, -267386864);
      this.func_73733_a(var12 - 3, var13 - 3, var12 + var5 + 3, var13 + var14 + 3, -267386864, -267386864);
      this.func_73733_a(var12 - 4, var13 - 3, var12 - 3, var13 + var14 + 3, -267386864, -267386864);
      this.func_73733_a(var12 + var5 + 3, var13 - 3, var12 + var5 + 4, var13 + var14 + 3, -267386864, -267386864);
      this.func_73733_a(var12 - 3, var13 - 3 + 1, var12 - 3 + 1, var13 + var14 + 3 - 1, 1347420415, 1344798847);
      this.func_73733_a(var12 + var5 + 2, var13 - 3 + 1, var12 + var5 + 3, var13 + var14 + 3 - 1, 1347420415, 1344798847);
      this.func_73733_a(var12 - 3, var13 - 3, var12 + var5 + 3, var13 - 3 + 1, 1347420415, 1347420415);
      this.func_73733_a(var12 - 3, var13 + var14 + 2, var12 + var5 + 3, var13 + var14 + 3, 1344798847, 1344798847);

      for(int var9 = 0; var9 < var1.size(); ++var9) {
         String var10 = (String)var1.get(var9);

         try {
            this.field_146289_q.func_175063_a(var10, (float)var12, (float)var13, -1);
            if (var9 == 0) {
               var13 += 2;
            }
         } catch (RuntimeException var11) {
            throw a(var11);
         }

         var13 += 10;
      }

      GlStateManager.func_179145_e();
      RenderHelper.func_74519_b();
      GlStateManager.func_179091_B();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
