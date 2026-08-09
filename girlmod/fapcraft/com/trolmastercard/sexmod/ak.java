package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class ak {
   public static Vec3d b(Entity var0, EntityPlayer var1, float var2) {
      Vec3d var3 = b6.a(new Vec3d(var0.field_70142_S, var0.field_70137_T + (double)var1.func_70047_e(), var0.field_70136_U), var0.func_174791_d().func_72441_c((double)0.0F, (double)var1.func_70047_e(), (double)0.0F), (double)var2);
      Vec3d var4 = b6.a(new Vec3d(var1.field_70142_S, var1.field_70137_T, var1.field_70136_U), var1.func_174791_d(), (double)var2);
      return var3.func_178788_d(var4);
   }

   public static Vec3d a(Entity var0, EntityPlayer var1, float var2) {
      Vec3d var3 = a(var0, var2);

      try {
         if (var1 == null) {
            return var3;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      Vec3d var4 = a(var1, var2);
      return var3.func_178788_d(var4);
   }

   public static Vec3d a(Entity var0, float var1) {
      try {
         if (!(var0 instanceof em)) {
            return b(var0, var1);
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      em var2 = (em)var0;

      try {
         if (!var2.Q()) {
            return b(var0, var1);
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      return var2.o();
   }

   static Vec3d b(Entity var0, float var1) {
      return b6.a(new Vec3d(var0.field_70142_S, var0.field_70137_T, var0.field_70136_U), var0.func_174791_d(), (double)var1);
   }

   public static void a() {
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
