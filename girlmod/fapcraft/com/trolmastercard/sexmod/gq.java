package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class gq extends GuiListExtended {
   static final int c = 3809871;
   static final List<gw> f = Arrays.asList(gw.values());
   static final String a = "MMMMMMMMMM";
   protected static int i = 5;
   protected static int e = 200;
   private List<a> b = new ArrayList();
   com.trolmastercard.sexmod.a d;
   boolean h = false;
   float g = 0.0F;

   public gq(Minecraft var1, com.trolmastercard.sexmod.a var2) {
      super(var1, var2.field_146294_l / 2, var2.field_146295_m, 0, var2.field_146295_m, 30);
      e = var2.field_146294_l / 2;
      this.d = var2;
   }

   public GuiListExtended.IGuiListEntry func_148180_b(int var1) {
      return (GuiListExtended.IGuiListEntry)this.b.get(var1);
   }

   protected int func_148127_b() {
      return this.b.size();
   }

   protected int func_148137_d() {
      return 0;
   }

   protected void drawContainerBackground(Tessellator var1) {
   }

   public void func_178039_p() {
      try {
         if (!this.func_148141_e(this.field_148162_h)) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      int var1 = Mouse.getEventDWheel();

      try {
         if (var1 == 0) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      if (var1 > 0) {
         var1 = -1;
      } else {
         var1 = 1;
      }

      this.field_148169_q += (float)(var1 * this.field_148149_f / 2);
   }

   protected void func_148136_c(int var1, int var2, int var3, int var4) {
   }

   void a() {
      int var1 = this.b.size() * this.field_148149_f;

      try {
         if (var1 > this.field_148158_l) {
            this.field_148153_b = 0;
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      int var2 = this.field_148158_l - var1;
      this.field_148153_b = var2 / 2;
   }

   public void func_148128_a(int var1, int var2, float var3) {
      this.b.clear();
      int var4 = 0;

      for(Map.Entry var6 : com.trolmastercard.sexmod.a.m) {
         gw var7 = (gw)var6.getKey();
         Map.Entry var8 = (Map.Entry)var6.getValue();

         try {
            this.b.add(new a(var7, (List)var8.getKey(), (Integer)var8.getValue()));
            if (gw.CUSTOM_BONE.equals(var6.getKey())) {
               ++var4;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }
      }

      this.b.sort(Comparator.comparingInt((var0) -> f.indexOf(var0.d)));
      List var11 = (List)br.a(this.d.c).get(gw.CUSTOM_BONE);

      List var10000;
      a var10001;
      a var10002;
      gq var10003;
      boolean var10004;
      label33: {
         try {
            var11.add(0, "cross");
            var10000 = this.b;
            var10001 = new a;
            var10002 = var10001;
            var10003 = this;
            if (var4 > 1) {
               var10004 = true;
               break label33;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         var10004 = false;
      }

      var10002.<init>(var10004);
      var10000.add(var10001);
      this.a();
      this.a(var1, var2, var3);
      if (this.h) {
         this.func_148145_f(999999);
         this.h = false;
      }
   }

   void a(int var1, int var2, float var3) {
      try {
         if (!this.field_178041_q) {
            return;
         }
      } catch (RuntimeException var14) {
         throw a(var14);
      }

      this.field_148150_g = var1;
      this.field_148162_h = var2;
      this.func_148123_a();
      int var4 = this.func_148137_d();
      int var5 = var4 + 6;
      this.func_148121_k();
      GlStateManager.func_179140_f();
      GlStateManager.func_179106_n();
      Tessellator var6 = Tessellator.func_178181_a();
      BufferBuilder var7 = var6.func_178180_c();
      this.drawContainerBackground(var6);
      int var8 = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
      int var9 = this.field_148153_b + 4 - (int)this.field_148169_q;

      try {
         if (this.field_148165_u) {
            this.func_148129_a(var8, var9, var6);
         }
      } catch (RuntimeException var13) {
         throw a(var13);
      }

      this.func_192638_a(var8, var9, var1, var2, var3);
      GlStateManager.func_179097_i();
      this.func_148136_c(0, this.field_148153_b, 255, 255);
      this.func_148136_c(this.field_148154_c, this.field_148158_l, 255, 255);
      GlStateManager.func_179147_l();
      GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ZERO, DestFactor.ONE);
      GlStateManager.func_179118_c();
      GlStateManager.func_179103_j(7425);
      GlStateManager.func_179090_x();
      int var10 = this.func_148135_f();
      if (var10 > 0) {
         int var11 = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
         var11 = MathHelper.func_76125_a(var11, 32, this.field_148154_c - this.field_148153_b - 8);
         int var12 = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - var11) / var10 + this.field_148153_b;
         if (var12 < this.field_148153_b) {
            var12 = this.field_148153_b;
         }

         var7.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         var7.func_181662_b((double)var4, (double)this.field_148154_c, (double)0.0F).func_187315_a((double)0.0F, (double)1.0F).func_181669_b(0, 0, 0, 255).func_181675_d();
         var7.func_181662_b((double)var5, (double)this.field_148154_c, (double)0.0F).func_187315_a((double)1.0F, (double)1.0F).func_181669_b(0, 0, 0, 255).func_181675_d();
         var7.func_181662_b((double)var5, (double)this.field_148153_b, (double)0.0F).func_187315_a((double)1.0F, (double)0.0F).func_181669_b(0, 0, 0, 255).func_181675_d();
         var7.func_181662_b((double)var4, (double)this.field_148153_b, (double)0.0F).func_187315_a((double)0.0F, (double)0.0F).func_181669_b(0, 0, 0, 255).func_181675_d();
         var6.func_78381_a();
         var7.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         var7.func_181662_b((double)var4, (double)(var12 + var11), (double)0.0F).func_187315_a((double)0.0F, (double)1.0F).func_181669_b(128, 128, 128, 255).func_181675_d();
         var7.func_181662_b((double)var5, (double)(var12 + var11), (double)0.0F).func_187315_a((double)1.0F, (double)1.0F).func_181669_b(128, 128, 128, 255).func_181675_d();
         var7.func_181662_b((double)var5, (double)var12, (double)0.0F).func_187315_a((double)1.0F, (double)0.0F).func_181669_b(128, 128, 128, 255).func_181675_d();
         var7.func_181662_b((double)var4, (double)var12, (double)0.0F).func_187315_a((double)0.0F, (double)0.0F).func_181669_b(128, 128, 128, 255).func_181675_d();
         var6.func_78381_a();
         var7.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         var7.func_181662_b((double)var4, (double)(var12 + var11 - 1), (double)0.0F).func_187315_a((double)0.0F, (double)1.0F).func_181669_b(192, 192, 192, 255).func_181675_d();
         var7.func_181662_b((double)(var5 - 1), (double)(var12 + var11 - 1), (double)0.0F).func_187315_a((double)1.0F, (double)1.0F).func_181669_b(192, 192, 192, 255).func_181675_d();
         var7.func_181662_b((double)(var5 - 1), (double)var12, (double)0.0F).func_187315_a((double)1.0F, (double)0.0F).func_181669_b(192, 192, 192, 255).func_181675_d();
         var7.func_181662_b((double)var4, (double)var12, (double)0.0F).func_187315_a((double)0.0F, (double)0.0F).func_181669_b(192, 192, 192, 255).func_181675_d();
         var6.func_78381_a();
      }

      this.func_148142_b(var1, var2);
      GlStateManager.func_179098_w();
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179141_d();
      GlStateManager.func_179084_k();
   }

   public boolean func_148179_a(int var1, int var2, int var3) {
      this.a(var1, var2, var3);
      return super.func_148179_a(var1, var2, var3);
   }

   void a(int var1, int var2, int var3) {
      try {
         if (var1 > this.field_148155_a) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      int var4 = this.func_148148_g();
      float var5 = (float)(var4 + var2 - 5 - this.field_148153_b);
      int var6 = Math.round((float)Math.floor((double)(var5 / (float)this.field_148149_f)));
      int var7 = (int)Math.round(((double)(var5 / (float)this.field_148149_f) - Math.floor((double)(var5 / (float)this.field_148149_f))) * (double)this.field_148149_f);

      try {
         if (var6 < 0) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if (var6 < this.b.size()) {
            ((a)this.b.get(var6)).a(var1, var7, var3, var6);
         }

      } catch (RuntimeException var8) {
         throw a(var8);
      }
   }

   // $FF: synthetic method
   static Minecraft access$100(gq var0) {
      return var0.field_148161_k;
   }

   // $FF: synthetic method
   static Minecraft access$200(gq var0) {
      return var0.field_148161_k;
   }

   // $FF: synthetic method
   static Minecraft access$300(gq var0) {
      return var0.field_148161_k;
   }

   // $FF: synthetic method
   static Minecraft access$400(gq var0) {
      return var0.field_148161_k;
   }

   // $FF: synthetic method
   static Minecraft access$600(gq var0) {
      return var0.field_148161_k;
   }

   // $FF: synthetic method
   static Minecraft access$700(gq var0) {
      return var0.field_148161_k;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   @SideOnly(Side.CLIENT)
   public class a implements GuiListExtended.IGuiListEntry {
      static final int g = 4;
      public gw d;
      public List<String> b;
      public int f;
      FontRenderer c;
      boolean a = false;
      boolean e = false;

      public a(gw var2, List<String> var3, int var4) {
         this.d = var2;
         this.b = var3;
         this.f = var4;
         this.c = gq.this.field_148161_k.field_71466_p;
      }

      public a(boolean var2) {
         this.e = var2;
         this.a = true;
      }

      boolean b(int var1, int var2, int var3, int var4, int var5, int var6) {
         try {
            if (var1 < var3) {
               return false;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         try {
            if (var1 > var5) {
               return false;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         try {
            if (var2 < var4) {
               return false;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         try {
            return var2 <= var6;
         } catch (RuntimeException var7) {
            throw a(var7);
         }
      }

      void b(int param1, int param2, int param3) {
         // $FF: Couldn't be decompiled
      }

      void a(int param1, int param2, int param3) {
         // $FF: Couldn't be decompiled
      }

      int c(int var1, int var2, int var3, int var4) {
         com.trolmastercard.sexmod.a var10000;
         int var10001;
         int var10002;
         byte var10003;
         byte var10004;
         byte var10005;
         label28: {
            try {
               var10000 = gq.this.d;
               var10001 = var1;
               var10002 = var2;
               var10003 = 0;
               var10004 = 20;
               if (this.b(var3, var4, var1, var2, var1 + 20, var2 + 20)) {
                  var10005 = 2;
                  break label28;
               }
            } catch (RuntimeException var6) {
               throw a(var6);
            }

            var10005 = 1;
         }

         label21: {
            try {
               var10000.a(var10001, var10002, var10003, var10004 * var10005);
               var1 += 20;
               var10000 = gq.this.d;
               var10001 = var1;
               var10002 = var2;
               var10003 = 20;
               var10004 = 20;
               if (this.b(var3, var4, var1, var2, var1 + 20, var2 + 20)) {
                  var10005 = 2;
                  break label21;
               }
            } catch (RuntimeException var5) {
               throw a(var5);
            }

            var10005 = 1;
         }

         var10000.a(var10001, var10002, var10003, var10004 * var10005);
         return var1 + 40;
      }

      void a(int var1, int var2, int var3, int var4, int var5) {
         gq.this.d.func_73729_b(var1, var2, 140, 20, 79, 20);
         var1 += 4;
         int var7 = var1 + 71 - 4;
         float var8 = this.a(var2, var1, var7, var3, var4, var5);
         int var9 = (int)b6.a((float)var1, (float)var7, var8);

         com.trolmastercard.sexmod.a var10000;
         int var10001;
         int var10002;
         short var10003;
         label17: {
            try {
               var10000 = gq.this.d;
               var10001 = var9;
               var10002 = var2;
               if (this.b(var3, var4, var9, var2, var9 + 4, var2 + 20)) {
                  var10003 = 223;
                  break label17;
               }
            } catch (RuntimeException var10) {
               throw a(var10);
            }

            var10003 = 219;
         }

         var10000.func_73729_b(var10001, var10002, var10003, 20, 4, 20);
         gq.this.d.c.a(var5, (int)(var8 * 100.0F));
      }

      float a(int param1, int param2, int param3, int param4, int param5, int param6) {
         // $FF: Couldn't be decompiled
      }

      float a(int var1) {
         Map.Entry var2 = (Map.Entry)gq.this.d.c.d(gq.this.d.g).get(var1);
         return (float)(Integer)((Map.Entry)var2.getValue()).getValue() / 100.0F;
      }

      void b(int var1, int var2, int var3, int var4) {
         boolean var5 = gq.this.d.c.h(var4);

         label31: {
            try {
               gq.this.field_148161_k.field_71446_o.func_110577_a(com.trolmastercard.sexmod.a.k);
               if (var5) {
                  gq.this.d.func_73729_b(gq.i, var1, 0, 60, 119, 30);
                  break label31;
               }
            } catch (RuntimeException var8) {
               throw a(var8);
            }

            gq.this.d.func_73729_b(gq.i, var1, 0, 90, 95, 30);
         }

         int var6 = gq.i + 10;

         try {
            var1 += 5;
            gq.this.d.a(var6, var1, gq.this.d.c.g(var4));
            var6 += 25;
            if (var5) {
               this.a(var6, var1, var2, var3, var4);
               return;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         this.c(var6, var1, var2, var3);
      }

      public void func_192634_a(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9) {
         try {
            if (this.a) {
               this.b(var3, var6, var7);
               return;
            }
         } catch (RuntimeException var11) {
            throw a(var11);
         }

         try {
            if (this.d == gw.GIRL_SPECIFIC) {
               this.b(var3, var6, var7, var1);
               return;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         this.a(var3, var6, var7);
      }

      void a(String var1, int var2, int var3) {
         this.c.func_78276_b(var1, var2, var3, 3809871);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      }

      void b(int param1, int param2) {
         // $FF: Couldn't be decompiled
      }

      void a(int param1, int param2) {
         // $FF: Couldn't be decompiled
      }

      void c(int var1, int var2) {
         try {
            if (!gq.this.d.c.h(var2)) {
               this.a(var1, var2);
            }

         } catch (RuntimeException var3) {
            throw a(var3);
         }
      }

      public void a(int var1, int var2, int var3, int var4) {
         try {
            if (var3 != 0) {
               return;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }

         try {
            if (var2 < 5) {
               return;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }

         try {
            if (var2 > 25) {
               return;
            }
         } catch (RuntimeException var5) {
            throw a(var5);
         }

         try {
            if (this.a) {
               this.b(var1, var2);
               return;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         try {
            if (this.d == gw.GIRL_SPECIFIC) {
               this.c(var1, var4);
               return;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         this.a(var1, var4);
      }

      public void func_192633_a(int var1, int var2, int var3, float var4) {
      }

      public boolean func_148278_a(int var1, int var2, int var3, int var4, int var5, int var6) {
         return false;
      }

      public void func_148277_b(int var1, int var2, int var3, int var4, int var5, int var6) {
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
