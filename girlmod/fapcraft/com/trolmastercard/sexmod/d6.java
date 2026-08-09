package com.trolmastercard.sexmod;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class d6<G extends e4> extends d_<G> {
   protected static final Vec3i r = new Vec3i(255, 255, 255);
   static HashMap<Integer, Vec3i> s = new HashMap();

   public d6(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   public static void c() {
      s.clear();
   }

   protected Vec3i a(GeoBone var1) {
      String var2 = var1.getName();
      int var3 = var2.hashCode() + ((e4)this.j).getPersistentID().hashCode();
      Vec3i var4 = (Vec3i)s.get(var3);

      try {
         if (var4 != null) {
            return var4;
         }
      } catch (RuntimeException var5) {
         throw c(var5);
      }

      var4 = this.a(var2);
      s.put(var3, var4);
      return var4;
   }

   protected abstract Vec3i a(String var1);

   protected static void b(GeoBone var0, int var1) {
      List var2 = var0.childBones;

      for(int var4 = 0; var4 < var2.size(); ++var4) {
         GeoBone var5 = (GeoBone)var2.get(var4);
         if (var1 == var4) {
            var5.setHidden(false);
            return;
         }
      }

   }

   protected void a(BufferBuilder var1, GeoBone var2) {
      ItemStack var3 = this.a((ItemStack)null);
      float var4 = this.a();
      Vec3d var5 = this.a(var3);

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var6) {
         throw c(var6);
      }

      GlStateManager.func_179094_E();
      Tessellator.func_178181_a().func_78381_a();
      com.trolmastercard.sexmod.p.a(IGeoRenderer.MATRIX_STACK, var2);
      GL11.glEnable(2896);
      GlStateManager.func_179152_a(var4, var4, var4);
      GlStateManager.func_179114_b((float)var5.field_72450_a, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b((float)var5.field_72448_b, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b((float)var5.field_72449_c, 0.0F, 0.0F, 1.0F);
      Minecraft.func_71410_x().func_175597_ag().func_178099_a(this.j, var3, TransformType.THIRD_PERSON_RIGHT_HAND);
      this.func_110776_a((ResourceLocation)Objects.requireNonNull(this.getEntityTexture(this.j)));
      var1.func_181668_a(7, DefaultVertexFormats.field_181712_l);
      GL11.glDisable(2896);
      GlStateManager.func_179121_F();
   }

   protected float a() {
      return 1.0F;
   }

   protected Vec3d a(ItemStack var1) {
      return new Vec3d((double)-90.0F, (double)0.0F, (double)0.0F);
   }

   protected static GeoBone a(GeoBone var0, int var1) {
      List var2 = var0.childBones;
      GeoBone var3 = null;
      var2.sort(Comparator.comparingDouble(GeoBone::getPivotY));

      for(int var4 = 0; var4 < var2.size(); ++var4) {
         GeoBone var5 = (GeoBone)var2.get(var4);
         if (var1 == var4) {
            var3 = var5;
            var5.setHidden(false);
         } else {
            var5.setHidden(true);
         }
      }

      return var3;
   }

   protected Vec3i a(Vec3i var1) {
      return var1;
   }

   public void a(BufferBuilder param1, GeoBone param2, float param3, float param4, float param5, float param6, double param7) {
      // $FF: Couldn't be decompiled
   }

   public void renderRecursively(BufferBuilder var1, GeoBone var2, float var3, float var4, float var5, float var6) {
      this.a(var1, var2, var3, var4, var5, var6, (double)0.0F);
   }

   public void a(BufferBuilder param1, GeoCube param2, GeoBone param3, float param4, float param5, float param6, float param7, double param8) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException c(RuntimeException var0) {
      return var0;
   }
}
