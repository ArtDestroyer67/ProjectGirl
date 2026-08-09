package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dv extends dm {
   static final float E = 8.0F;
   static final float K = 1.68F;
   static final float M = 5.0F;
   static Collection<dv> J = new ArrayList();
   double C = (double)0.0F;
   double z = (double)0.0F;
   double A = (double)0.0F;
   double D = (double)0.0F;
   float F = 0.0F;
   float B = 0.0F;
   float G;
   float I;
   double H = (double)0.0F;
   double L = (double)0.0F;

   public dv(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
      J.add(this);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -1.1F, 0.0F);
      GlStateManager.func_179152_a(0.7F, 0.7F, 0.7F);
   }

   protected void a(boolean var1, ItemStack var2) {
      try {
         super.a(var1, var2);
         switch (var2.func_77973_b().func_77661_b(var2)) {
            case BLOCK:
            case BOW:
               return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (!var1) {
            GlStateManager.func_179114_b(20.0F, 1.0F, 0.0F, 0.0F);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      GlStateManager.func_179137_b((double)0.0F, 0.05, (double)0.0F);
   }

   protected void a(boolean var1) {
      try {
         super.a(var1);
         if (var1) {
            GlStateManager.func_179137_b(0.15, (double)0.0F, (double)0.0F);
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      GlStateManager.func_179137_b(-0.05, (double)0.0F, (double)0.0F);
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   protected void a(String var1, GeoBone var2) {
      try {
         if ((Boolean)this.w.func_184212_Q().func_187225_a(em.G)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if ("tail".equals(var1)) {
            this.a(var2, 0.0F, 0.0F, 1.0F);
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if ("body".equals(var1)) {
            this.a(var2);
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (this.w.y() == fp.BOW) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if ("armL".equals(var1)) {
            this.a(var2, 0.0F, -0.34906584F, 0.15F);
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (this.w.y() == fp.ATTACK) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if ("armR".equals(var1)) {
            this.a(var2, 0.0F, 0.34906584F, 0.15F);
         }

      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   void a(GeoBone var1, float var2, float var3, float var4) {
      double var5 = this.C - this.A;
      double var7 = this.z - this.D;
      double var9 = (Math.PI / 180D) * (double)this.w.field_70177_z;
      Vec2f var11 = new Vec2f((float)(var5 * Math.cos(var9) + var7 * Math.sin(var9)), (float)(-var5 * Math.sin(var9) + var7 * Math.cos(var9)));
      this.G = var11.field_189983_j * -8.0F;
      this.I = var11.field_189982_i * 8.0F;
      this.G = be.b(this.G, -1.68F, 1.68F);
      this.I = be.b(this.I, -1.68F, 1.68F);
      this.G = b6.a(this.F, this.G, this.y);
      this.I = b6.a(this.B, this.I, this.y);
      var1.setRotationX(var2 + this.G * var4);
      var1.setRotationZ(var3 + this.I * var4);
   }

   void a(GeoBone var1) {
      double var2 = this.C - this.A;
      double var4 = this.z - this.D;

      try {
         this.L = (Math.abs(var2) + Math.abs(var4)) * (double)5.0F;
         this.L = (double)be.b((float)this.L, 0.0F, 1.0F);
         var1.setPositionY((float)b6.a((double)5.0F, (double)0.0F, b6.b(this.H, this.L, (double)this.y)));
         if (this.w instanceof e5) {
            ((e5)this.w).aq = (float)b6.a((double)0.3F, (double)0.0F, b6.b(this.H, this.L, (double)this.y));
         }

      } catch (RuntimeException var6) {
         throw a(var6);
      }
   }

   void a() {
      try {
         if (this.w == null) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         this.F = this.G;
         this.B = this.I;
         this.H = this.L;
         if (this.w.m() == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      EntityPlayer var1 = this.j.field_70170_p.func_152378_a(this.w.m());

      try {
         if (var1 == null) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      this.A = this.C;
      this.D = this.z;
      this.C = var1.field_70165_t;
      this.z = var1.field_70161_v;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a {
      @SubscribeEvent
      public void a(TickEvent.ClientTickEvent var1) {
         for(dv var3 : dv.J) {
            var3.a();
         }

      }
   }
}
