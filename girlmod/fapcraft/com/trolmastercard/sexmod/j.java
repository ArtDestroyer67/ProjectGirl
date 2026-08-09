package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class j extends GuiScreen {
   static final float f = 100.0F;
   static final float g = 15.0F;
   static final float j = 0.5F;
   static final ResourceLocation h = new ResourceLocation("sexmod", "textures/gui/command.png");
   static final HashSet<Material> l;
   public static boolean d;
   float m = 0.0F;
   float a = 0.0F;
   float k = 0.0F;
   float n = 0.0F;
   float i = 0.0F;
   IBlockState e;
   BlockPos c;
   EnumFacing b;

   public j() {
      Minecraft var1 = Minecraft.func_71410_x();

      label27: {
         try {
            this.c = var1.field_71476_x.func_178782_a();
            if (var1.field_71476_x.field_178784_b == null) {
               this.b = EnumFacing.NORTH;
               break label27;
            }
         } catch (NullPointerException var3) {
            throw a(var3);
         }

         this.b = var1.field_71476_x.field_178784_b.func_176734_d();
      }

      try {
         if (this.c == null) {
            this.c = BlockPos.field_177992_a;
         }
      } catch (NullPointerException var2) {
         throw a(var2);
      }

      this.e = var1.field_71441_e.func_180495_p(this.c);
   }

   public void func_146281_b() {
      super.func_146281_b();
      List var1 = Arrays.asList(this.a, this.k, this.n, this.i);
      float var2 = (Float)Collections.max(var1);

      try {
         if (var2 == 0.0F) {
            return;
         }
      } catch (NullPointerException var5) {
         throw a(var5);
      }

      try {
         if (this.a == var2) {
            this.b();
         }
      } catch (NullPointerException var7) {
         throw a(var7);
      }

      try {
         if (this.k == var2) {
            this.d();
         }
      } catch (NullPointerException var4) {
         throw a(var4);
      }

      try {
         if (this.n == var2) {
            this.c();
         }
      } catch (NullPointerException var6) {
         throw a(var6);
      }

      try {
         if (this.i == var2) {
            this.a();
         }

      } catch (NullPointerException var3) {
         throw a(var3);
      }
   }

   void b() {
      // $FF: Couldn't be decompiled
   }

   void d() {
      SimpleNetworkWrapper var10000;
      fj var10001;
      fj var10002;
      boolean var10003;
      label16: {
         try {
            var10000 = ge.b;
            var10001 = new fj;
            var10002 = var10001;
            if (!d) {
               var10003 = true;
               break label16;
            }
         } catch (NullPointerException var1) {
            throw a(var1);
         }

         var10003 = false;
      }

      var10002.<init>(var10003);
      var10000.sendToServer(var10001);
   }

   void c() {
      fa.a();
   }

   void a() {
      // $FF: Couldn't be decompiled
   }

   @Nullable
   Object[] e() {
      Material var1 = this.field_146297_k.field_71441_e.func_180495_p(this.c).func_185904_a();
      EntityPlayerSP var2 = this.field_146297_k.field_71439_g;

      try {
         if (!l.contains(var1)) {
            return null;
         }
      } catch (NullPointerException var4) {
         throw a(var4);
      }

      try {
         if (((EntityPlayer)var2).func_180425_c().func_177956_o() > this.c.func_177956_o()) {
            return null;
         }
      } catch (NullPointerException var6) {
         throw a(var6);
      }

      BlockPos var3;
      for(var3 = this.c; this.field_146297_k.field_71441_e.func_180495_p(var3.func_177977_b().func_177971_a(this.b.func_176734_d().func_176730_m())).func_177230_c() == Blocks.field_150350_a; var3 = var3.func_177977_b()) {
      }

      try {
         if (this.c.func_177956_o() - var3.func_177956_o() > 3) {
            return null;
         }
      } catch (NullPointerException var5) {
         throw a(var5);
      }

      return new Object[]{var3, this.b};
   }

   public void func_73863_a(int param1, int param2, float param3) {
      // $FF: Couldn't be decompiled
   }

   void a(float var1) {
      this.func_175174_a(-2.0F - var1 + this.n * 15.0F, -2.0F - var1 + this.n * 15.0F, 192, 64, 64, 64);
   }

   void c(float var1) {
      this.func_175174_a(-62.0F + var1 - this.k * 15.0F, -62.0F + var1 - this.k * 15.0F, 64, 64, 64, 64);
   }

   void e(float var1) {
      this.func_175174_a(-2.0F - var1 + this.i * 15.0F, -62.0F + var1 - this.i * 15.0F, 64, 0, 64, 64);
   }

   void b(float var1) {
      this.func_175174_a(-2.0F - var1 + this.i * 15.0F, -62.0F + var1 - this.i * 15.0F, 128, 0, 64, 64);
   }

   void f(float var1) {
      this.func_175174_a(-62.0F + var1 - this.a * 15.0F, -2.0F - var1 + this.a * 15.0F, 0, 64, 64, 64);
   }

   void d(float var1) {
      this.func_175174_a(-62.0F + var1 - this.a * 15.0F, -2.0F - var1 + this.a * 15.0F, 192, 0, 64, 64);
   }

   double a(double var1) {
      double var3 = 1.70158;
      double var5 = var3 + (double)1.0F;
      return (double)1.0F + var5 * Math.pow(var1 - (double)1.0F, (double)3.0F) + var3 * Math.pow(var1 - (double)1.0F, (double)2.0F);
   }

   protected void func_146286_b(int var1, int var2, int var3) {
      this.field_146297_k.field_71439_g.func_71053_j();
      super.func_146286_b(var1, var2, var3);
   }

   public boolean func_73868_f() {
      return false;
   }

   static {
      l = new HashSet(Arrays.asList(Material.field_151571_B, Material.field_151576_e, Material.field_151595_p, Material.field_151578_c));
      d = false;
   }

   private static NullPointerException a(NullPointerException var0) {
      return var0;
   }
}
