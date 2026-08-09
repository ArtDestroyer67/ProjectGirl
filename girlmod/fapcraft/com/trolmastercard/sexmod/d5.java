package com.trolmastercard.sexmod;

import java.util.HashSet;
import javax.vecmath.Vector3f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d5 extends dm {
   Vector3f A = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f D = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f F = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f E = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f z = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f B = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f C = new Vector3f(0.0F, 0.0F, 0.0F);

   public d5(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   protected void c() {
      GlStateManager.func_179109_b(0.0F, -1.25F, 0.0F);
      GlStateManager.func_179152_a(0.8F, 0.8F, 0.8F);
   }

   protected void a(String var1, GeoBone var2) {
      try {
         if ("slime".equals(var1)) {
            this.F = new Vector3f(var2.getRotationX(), var2.getRotationY(), var2.getRotationZ());
            this.A = new Vector3f(var2.getScaleX(), var2.getScaleY(), var2.getScaleZ());
            this.D = new Vector3f(var2.getPositionX(), var2.getPositionY(), var2.getPositionZ());
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if ("upperBody".equals(var1)) {
            this.B = new Vector3f(var2.getRotationX(), var2.getRotationY(), var2.getRotationZ());
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      try {
         if ("torso".equals(var1)) {
            this.E = new Vector3f(var2.getRotationX(), var2.getRotationY(), var2.getRotationZ());
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if ("head".equals(var1)) {
            this.C = new Vector3f(var2.getRotationX(), var2.getRotationY(), var2.getRotationZ());
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if ("boobs".equals(var1)) {
            this.z = new Vector3f(var2.getRotationX(), var2.getRotationY(), var2.getRotationZ());
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if ("figure".equals(var1)) {
            var2.setRotationX(this.F.x);
            var2.setRotationY(this.F.y);
            var2.setRotationZ(this.F.z);
            var2.setScaleX(this.A.x);
            var2.setScaleY(this.A.y);
            var2.setScaleZ(this.A.z);
            var2.setPositionX(this.D.x);
            var2.setPositionY(this.D.y);
            var2.setPositionZ(this.D.z);
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if ("dress".equals(var1)) {
            var2.setRotationX(this.B.x);
            var2.setRotationY(this.B.y);
            var2.setRotationZ(this.B.z);
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if ("hat".equals(var1)) {
            var2.setRotationX(this.C.x);
            var2.setRotationY(this.C.y);
            var2.setRotationZ(this.C.z);
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if ("boobsSlime".equals(var1)) {
            var2.setRotationX(this.z.x);
            var2.setRotationY(this.z.y);
            var2.setRotationZ(this.z.z);
         }

      } catch (RuntimeException var3) {
         throw a(var3);
      }
   }

   protected void a(boolean var1) {
      try {
         super.a(var1);
         if (var1) {
            GlStateManager.func_179109_b(0.15F, 0.0F, 0.0F);
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      GlStateManager.func_179137_b(-0.02, (double)0.0F, (double)0.0F);
      GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
   }

   public HashSet<String> a() {
      HashSet var1 = super.a();
      var1.add("figure");
      return var1;
   }

   protected void a(boolean param1, boolean param2) {
      // $FF: Couldn't be decompiled
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

      float var10000;
      label21: {
         try {
            if (var1) {
               var10000 = 30.0F;
               break label21;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10000 = 135.0F;
      }

      GlStateManager.func_179114_b(var10000, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179137_b((double)0.0F, 0.05, -0.05);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
