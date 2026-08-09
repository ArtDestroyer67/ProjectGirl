package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ch extends GuiScreen {
   fo c;
   EntityPlayer a;
   boolean e;
   static final ResourceLocation b = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
   double d = (double)0.0F;

   public ch(fo var1, EntityPlayer var2) {
      this.c = var1;
      this.a = var2;
      this.e = !"".equals(var1.func_184212_Q().func_187225_a(em.v));
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73863_a(int var1, int var2, float var3) {
      super.func_73863_a(var1, var2, var3);
      this.field_146292_n.clear();
      ScaledResolution var4 = new ScaledResolution(this.field_146297_k);
      int var5 = var4.func_78326_a();

      List var10000;
      GuiButton var10001;
      GuiButton var10002;
      byte var10003;
      int var10004;
      byte var10005;
      int var10006;
      byte var10007;
      String var10008;
      label22: {
         try {
            this.d = Math.min((double)1.0F, this.d + (double)(this.field_146297_k.func_193989_ak() / 5.0F));
            var10000 = this.field_146292_n;
            var10001 = new GuiButton;
            var10002 = var10001;
            var10003 = 0;
            var10004 = var5 / 2 - 119 + (int)((double)100.0F - (double)100.0F * this.d);
            var10005 = 30;
            var10006 = (int)(this.d * (double)100.0F);
            var10007 = 20;
            if (this.e) {
               var10008 = I18n.func_135052_a("action.names.stopfollowme", new Object[0]);
               break label22;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         var10008 = I18n.func_135052_a("action.names.followme", new Object[0]);
      }

      var10002.<init>(var10003, var10004, var10005, var10006, var10007, var10008);
      var10000.add(var10001);
      this.field_146292_n.add(new GuiButton(1, var5 / 2 + 19, 30, (int)(this.d * (double)100.0F), 20, I18n.func_135052_a("action.names.gohome", new Object[0])));
      this.field_146297_k.field_71446_o.func_110577_a(b);
      this.func_73729_b(var5 / 2 - 7, 61 - (int)((double)15.0F - this.d * (double)15.0F), 32, 0, 15, 15);
      this.field_146292_n.add(new GuiButton(2, var5 / 2 - 10, 59 - (int)((double)15.0F - this.d * (double)15.0F), 20, 20, ""));
      this.func_73729_b(var5 / 2 - 20, 20, (Boolean)this.c.func_184212_Q().func_187225_a(fo.K) ? 0 : 40, 130, 40, 40);
   }

   protected void func_73864_a(int param1, int param2, int param3) throws IOException {
      // $FF: Couldn't be decompiled
   }

   protected void func_146284_a(GuiButton param1) throws IOException {
      // $FF: Couldn't be decompiled
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
