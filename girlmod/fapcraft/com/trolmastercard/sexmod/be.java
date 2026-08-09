package com.trolmastercard.sexmod;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.UUID;
import net.minecraft.util.math.Vec3d;

public class be {
   public static float a(double var0, double var2) {
      var0 = (var0 + (Math.PI * 2D)) % (Math.PI * 2D);
      var2 = (var2 + (Math.PI * 2D)) % (Math.PI * 2D);

      double var4;
      for(var4 = var2 - var0; var4 < -Math.PI; var4 += (Math.PI * 2D)) {
      }

      while(var4 >= Math.PI) {
         var4 -= (Math.PI * 2D);
      }

      return (float)var4;
   }

   public static bm a(Vec3d var0, Vec3d var1) {
      Vec3d var2 = var1.func_178788_d(var0).func_72432_b();
      return new bm((float)Math.atan2(var2.field_72450_a, var2.field_72449_c), (float)Math.atan2(var2.field_72448_b, Math.sqrt(var2.field_72450_a * var2.field_72450_a + var2.field_72449_c * var2.field_72449_c)));
   }

   public static void a(String var0) {
      Clipboard var1 = Toolkit.getDefaultToolkit().getSystemClipboard();
      StringSelection var2 = new StringSelection(var0);
      var1.setContents(var2, (ClipboardOwner)null);
   }

   public static String b(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static boolean a(double var0, double var2, double var4) {
      try {
         if (var0 < var2) {
            return false;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         return !(var0 >= var4);
      } catch (RuntimeException var6) {
         throw a(var6);
      }
   }

   public static int a(int var0) {
      try {
         if (var0 <= 0) {
            return var0;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      Random var1 = new Random();
      int var2 = 0;

      for(int var3 = 0; var3 <= var0; ++var3) {
         var2 += var3;
      }

      int var8 = var1.nextInt(var2) + 1;
      int var4 = 0;

      for(int var5 = 0; var5 <= var0; ++var5) {
         var4 += var5;

         try {
            if (var4 >= var8) {
               return var5;
            }
         } catch (RuntimeException var6) {
            throw a(var6);
         }
      }

      return var0;
   }

   public static int a() {
      byte var10000;
      try {
         if (r.f.nextBoolean()) {
            var10000 = 1;
            return var10000;
         }
      } catch (RuntimeException var0) {
         throw a(var0);
      }

      var10000 = -1;
      return var10000;
   }

   public static float b(float var0, float var1, float var2) {
      return Math.max(var1, Math.min(var2, var0));
   }

   public static double b(double var0, double var2, double var4) {
      return Math.max(var2, Math.min(var4, var0));
   }

   public static float a(float param0, boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public static float a(float param0, float param1, float param2) {
      // $FF: Couldn't be decompiled
   }

   public static int a(double var0) {
      return Math.round((float)var0);
   }

   public static void a(int var0, Runnable var1) {
      String var2 = UUID.randomUUID().toString();

      Thread var10000;
      Thread var10001;
      Runnable var10002;
      StringBuilder var10003;
      String var10004;
      label17: {
         try {
            var10000 = new Thread;
            var10001 = var10000;
            var10002 = () -> {
               try {
                  Thread.sleep((long)var0);
               } catch (Exception var3) {
                  var3.printStackTrace();
               }

               var1.run();
            };
            var10003 = new StringBuilder();
            if (g0.a()) {
               var10004 = "server sexmod thread ";
               break label17;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10004 = "client sexmod thread ";
      }

      var10001.<init>(var10002, var10003.append(var10004).append(var2).toString());
      var10000.start();
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
