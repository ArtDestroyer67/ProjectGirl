package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ep {
   static final int c = 30;
   static final int k = 6;
   static final int f = 6;
   static final float b = 0.15F;
   List<an> g = new ArrayList();
   final int a;
   final ar i;
   final b8 d;
   final em e;
   final float j;
   final float h;

   public ep(int var1, ar var2, b8 var3, em var4, float var5, float var6) {
      this.a = var1;
      this.i = var2;
      this.d = var3;
      this.e = var4;
      this.j = var5;
      this.h = var6;
   }

   void a(Minecraft var1, Tessellator var2, BufferBuilder var3, float var4) {
      if (this.g.size() < this.a) {
         for(int var5 = 0; var5 < 6; ++var5) {
            Vec3d var6 = this.i.a(this.e);
            this.g.add(new an(var1.field_71441_e, this.d.a(this.e), new Vec3d(var6.field_72450_a + (double)((r.f.nextFloat() * 2.0F - 1.0F) * this.j), var6.field_72448_b + (double)((r.f.nextFloat() * 2.0F - 1.0F) * this.j), var6.field_72449_c + (double)((r.f.nextFloat() * 2.0F - 1.0F) * this.j))));
         }
      }

      GlStateManager.func_179129_p();
      GlStateManager.func_179118_c();
      Vec3d var11 = b6.a(new Vec3d(var1.field_71439_g.field_70142_S, var1.field_71439_g.field_70137_T, var1.field_71439_g.field_70136_U), var1.field_71439_g.func_174791_d(), (double)var4);
      var3.func_181668_a(9, DefaultVertexFormats.field_181706_f);
      this.b();
      Vec3d var12 = null;

      for(an var8 : this.g) {
         Vec3d var9 = b6.a(var8.d, var8.f, (double)var4);
         if (var12 == null) {
            var12 = var9;
         }

         try {
            if (var12.func_72438_d(var9) > (double)this.h) {
               var2.func_78381_a();
               var3.func_181668_a(9, DefaultVertexFormats.field_181706_f);
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         var3.func_181662_b(var9.field_72450_a - var11.field_72450_a, var9.field_72448_b - var11.field_72448_b, var9.field_72449_c - var11.field_72449_c).func_181669_b(255, 255, 255, 255).func_181675_d();
         var12 = var9;
      }

      var2.func_78381_a();
      GlStateManager.func_179089_o();
   }

   void a() {
      for(an var2 : this.g) {
         var2.a();
      }

   }

   void b() {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
