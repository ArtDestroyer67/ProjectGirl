package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.vecmath.Vector3f;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public class gx {
   public static final Vec3d c = new Vec3d(0.95, 0.65, 0.85);
   public static final Vec3d e = new Vec3d((double)0.0F, 0.2, 0.3);
   public static final float f = 0.1F;
   public static final HashSet<String> a = new HashSet<String>() {
      {
         this.add("boobs");
         this.add("booty");
         this.add("vagina");
         this.add("fuckhole");
      }
   };
   protected static HashMap<c3, HashMap<String, Boolean>> d = new HashMap();
   public static Vec3d b;

   static boolean a(c3 var0, GeoBone var1) {
      HashMap var2 = (HashMap)d.get(var0);
      if (var2 == null) {
         var2 = new HashMap();
         boolean var6 = var0.a(var0.a(), var1);
         var2.put(var1.getName(), var6);
         d.put(var0, var2);
         return var6;
      } else {
         Boolean var3 = (Boolean)var2.get(var1.getName());
         if (var3 == null) {
            var3 = var0.a(var0.a(), var1);
            var2.put(var1.getName(), var3);
            d.put(var0, var2);
            return var3;
         } else {
            return var3;
         }
      }
   }

   public static Vec3d a(c3 var0, GeoBone var1, Vec3d var2, Vector3f var3) {
      try {
         if (!a(var0, var1)) {
            return var2;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      return a(var2, var3, b);
   }

   public static Vec3d a(Vec3d var0, Vector3f var1, Vec3d var2) {
      double var3 = ck.a(var1, var2);
      double var5 = b6.e(Math.abs(var3));
      var5 *= (double)0.1F;

      Vec3d var10000;
      Vec3d var10001;
      try {
         var10000 = var0;
         if (var3 > (double)0.0F) {
            var10001 = c;
            return b6.a(var10000, var10001, var5);
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      var10001 = e;
      return b6.a(var10000, var10001, var5);
   }

   public static void a(EntityLivingBase var0, float var1) {
      b = cj.a(var0, var1);
   }

   public static void a(List<IBone> var0, HashSet<String> var1, c3 var2) {
      try {
         if (d.get(var2) != null) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      HashMap var3 = new HashMap();

      for(IBone var5 : var0) {
         var3.put(var5.getName(), var2.a(var1, (GeoBone)var5));
      }

      d.put(var2, var3);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
