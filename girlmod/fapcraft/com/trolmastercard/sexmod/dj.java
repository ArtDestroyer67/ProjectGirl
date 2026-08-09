package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class dj extends d6<ff> {
   static final HashSet<String> t = new HashSet(Arrays.asList("colorSpots", "neck", "head", "snout", "midSectionR", "midSectionL", "innerCheekLR", "innerCheekRR", "gayL", "gayR", "legR", "legL", "shinL", "toesL", "kneeL", "curvesL", "shinR", "toesR", "kneeR", "curvesR", "sideL", "sideR", "hip", "torsoL", "torsoR", "armR", "lowerArmR", "ellbowR", "armL", "lowerArmL", "ellbowL", "hornUL", "hornUR", "tail", "tail2", "tail3", "tail4", "tail5", "hornDL2", "hornDR2", "hornDR3M", "hornDL3M", "frecklesAL1", "frecklesAL2", "frecklesAR1", "frecklesAR2", "frecklesHL1", "frecklesHL2", "frecklesHR1", "frecklesHR2"));
   static final HashSet<String> u = new HashSet(Arrays.asList("boobR", "boobL", "frontNeck", "Rside", "Lside", "frontAndInside", "innerCheekLL", "innerCheekRL", "layer", "layer2", "down", "down2", "down3", "down4", "down5", "fuckhole", "hornDR3S", "hornDL3S", "assholeCoverUp", "assholeCoverUp2"));
   Minecraft w = Minecraft.func_71410_x();
   Vector3f v;

   public dj(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   protected Vec3i a(String param1) {
      // $FF: Couldn't be decompiled
   }

   protected ItemStack a(@Nullable ItemStack param1) {
      // $FF: Couldn't be decompiled
   }

   public void a(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6, double var7) {
      try {
         if ((this.j).field_70170_p instanceof gj) {
            return;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      String var9 = var2.getName();
      if ("blowOpening".equals(var9)) {
         var7 = (double)0.0F;
      }

      if ("mouth".equals(var9)) {
         String[] var10 = e4.a(this.j);
         int var11 = Integer.parseInt(var10[7]);
         if (var11 == 1) {
            var7 = (double)-0.078125F;
         }
      }

      super.a(var1, var2, var3, var4, var5, var6, var7);
   }

   protected void d() {
      float var1 = 0.25F - (Float)((ff)this.j).func_184212_Q().func_187225_a(e7.aA);
      GlStateManager.func_179152_a(1.0F - var1, 1.0F - var1, 1.0F - var1);
   }

   protected void b() {
      float var1 = 0.25F - (Float)((ff)this.j).func_184212_Q().func_187225_a(e7.aA);
      double var2 = (double)1.0F / ((double)1.0F - (double)var1);
      GlStateManager.func_179139_a(var2, var2, var2);
   }

   protected ItemStack a() {
      String var1 = (String)((ff)this.j).func_184212_Q().func_187225_a(em.h);

      try {
         if ("STARTBLOWJOB".equals(var1)) {
            return new ItemStack(Items.field_151035_b);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         return "ANAL_START".equals(var1) ? new ItemStack(Items.field_151043_k, 3) : null;
      } catch (RuntimeException var2) {
         throw a(var2);
      }
   }

   public void a(ff var1, double var2, double var4, double var6, float var8, float var9) {
      String var10 = (String)var1.func_184212_Q().func_187225_a(e4.N);

      try {
         if (var1.as == null) {
            var1.as = var10;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      try {
         if (!var1.as.equals(var10)) {
            c();
            var1.as = var10;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      this.v = new Vector3f((float)var2, (float)var4, (float)var6);
      super.a(var1, var2, var4, var6, var8, var9);
   }

   protected void a(double var1, double var3, double var5) {
      EntityDataManager var7 = ((ff)this.j).func_184212_Q();
      String var8 = (String)var7.func_187225_a(ff.aU);

      try {
         if ("null".equals(var8)) {
            super.a(var1, var3, var5);
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      EyeAndKoboldColor var9 = EyeAndKoboldColor.valueOf((String)var7.func_187225_a(ff.N));
      var8 = var9.getTextColor() + " -" + var8 + "-";
      this.func_147906_a(this.j, ((ff)this.j).ab() + var8, var1, var3 + (double)((ff)this.j).i(), var5, 300);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
