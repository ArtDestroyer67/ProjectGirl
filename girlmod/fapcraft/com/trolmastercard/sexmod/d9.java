package com.trolmastercard.sexmod;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class d9 extends dm {
   protected static final Vec3i z = new Vec3i(255, 255, 255);
   static HashMap<Integer, Vec3i> A = new HashMap();

   public d9(RenderManager var1, AnimatedGeoModel var2) {
      super(var1, var2);
   }

   public static void e() {
      A.clear();
   }

   protected Vec3i a(GeoBone var1) {
      String var2 = var1.getName();
      int var3 = var2.hashCode() + this.j.getPersistentID().hashCode();
      Vec3i var4 = (Vec3i)A.get(var3);

      try {
         if (var4 != null) {
            return var4;
         }
      } catch (IllegalStateException var5) {
         throw a(var5);
      }

      var4 = this.a(var2);
      A.put(var3, var4);
      return var4;
   }

   protected abstract Vec3i a(String var1);

   protected void b(GeoBone var1, int var2) {
      List var3 = var1.childBones;

      for(int var5 = 0; var5 < var3.size(); ++var5) {
         GeoBone var6 = (GeoBone)var3.get(var5);
         if (var2 == var5) {
            var6.setHidden(false);
            return;
         }
      }

   }

   protected float a() {
      return 1.0F;
   }

   protected Vec3d a(ItemStack var1) {
      return new Vec3d((double)-90.0F, (double)0.0F, (double)0.0F);
   }

   protected GeoBone a(GeoBone var1, int var2) {
      List var3 = var1.childBones;
      GeoBone var4 = null;
      var3.sort(Comparator.comparingDouble(GeoBone::getPivotY));

      for(int var5 = 0; var5 < var3.size(); ++var5) {
         GeoBone var6 = (GeoBone)var3.get(var5);
         if (var2 == var5) {
            var4 = var6;
            var6.setHidden(false);
         } else {
            var6.setHidden(true);
         }
      }

      return var4;
   }

   protected Vec3i a(Vec3i var1) {
      return var1;
   }

   public void renderRecursively(BufferBuilder param1, GeoBone param2, float param3, float param4, float param5, float param6) {
      // $FF: Couldn't be decompiled
   }

   public void a(BufferBuilder param1, GeoCube param2, GeoBone param3, float param4, float param5, float param6, float param7, double param8) {
      // $FF: Couldn't be decompiled
   }

   protected boolean c(String var1) {
      return var1.startsWith("armor");
   }

   private static IllegalStateException a(IllegalStateException var0) {
      return var0;
   }
}
