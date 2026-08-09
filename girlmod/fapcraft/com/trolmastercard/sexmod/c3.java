package com.trolmastercard.sexmod;

import java.util.HashSet;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public interface c3 {
   default HashSet<String> a() {
      return gx.a;
   }

   default boolean a(HashSet<String> var1, GeoBone var2) {
      for(; var2.parent != null; var2 = var2.parent) {
         String var3 = var2.getName();

         try {
            if (var1.contains(var3)) {
               return false;
            }
         } catch (RuntimeException var4) {
            throw b(var4);
         }

         try {
            if (var3.startsWith("armor")) {
               return false;
            }
         } catch (RuntimeException var5) {
            throw b(var5);
         }
      }

      return true;
   }

   private static RuntimeException b(RuntimeException var0) {
      return var0;
   }
}
