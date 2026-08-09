package com.trolmastercard.sexmod;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class bn {
   public static void a(boolean var0) throws IOException {
      try {
         MinecraftForge.EVENT_BUS.register(new ah());
         MinecraftForge.EVENT_BUS.register(new eo());
         MinecraftForge.EVENT_BUS.register(new q());
         MinecraftForge.EVENT_BUS.register(new co());
         MinecraftForge.EVENT_BUS.register(new gu());
         MinecraftForge.EVENT_BUS.register(new ho.a());
         MinecraftForge.EVENT_BUS.register(new g.a());
         MinecraftForge.EVENT_BUS.register(ap.b);
         MinecraftForge.EVENT_BUS.register(hy.b);
         MinecraftForge.EVENT_BUS.register(hj.a);
         MinecraftForge.EVENT_BUS.register(new gp());
         MinecraftForge.EVENT_BUS.register(new fu());
         MinecraftForge.EVENT_BUS.register(new eb.a());
         MinecraftForge.EVENT_BUS.register(new ey());
         MinecraftForge.EVENT_BUS.register(dw.a);
         MinecraftForge.EVENT_BUS.register(new ff.c());
         MinecraftForge.EVENT_BUS.register(new hy.a());
         MinecraftForge.EVENT_BUS.register(new ax.b("tribes"));
         MinecraftForge.EVENT_BUS.register(new c7());
         MinecraftForge.EVENT_BUS.register(new am());
         MinecraftForge.EVENT_BUS.register(new e3.c());
         MinecraftForge.EVENT_BUS.register(new eq.a());
         MinecraftForge.EVENT_BUS.register(new ap.a());
         MinecraftForge.EVENT_BUS.register(new ad());
         MinecraftForge.EVENT_BUS.register(new f_.a());
         MinecraftForge.EVENT_BUS.register(new v());
         MinecraftForge.EVENT_BUS.register(cc.r);
         MinecraftForge.EVENT_BUS.register(aj.b);
         MinecraftForge.EVENT_BUS.register(new fq());
         MinecraftForge.EVENT_BUS.register(new gy());
         MinecraftForge.EVENT_BUS.register(new bj());
         MinecraftForge.EVENT_BUS.register(g3.b());
         MinecraftForge.EVENT_BUS.register(new f8.b());
         MinecraftForge.EVENT_BUS.register(new f4());
         if (var0) {
            b();
         }

      } catch (IOException var1) {
         throw a(var1);
      }
   }

   @SideOnly(Side.CLIENT)
   static void b() {
      label16: {
         try {
            if (a()) {
               MinecraftForge.EVENT_BUS.register(new fr());
               break label16;
            }
         } catch (RuntimeException var0) {
            throw a(var0);
         }

         g2.a = false;
      }

      MinecraftForge.EVENT_BUS.register(new ds());
      MinecraftForge.EVENT_BUS.register(new fh());
      MinecraftForge.EVENT_BUS.register(new d3());
      MinecraftForge.EVENT_BUS.register(new l());
      MinecraftForge.EVENT_BUS.register(new bq());
      MinecraftForge.EVENT_BUS.register(new cn());
      MinecraftForge.EVENT_BUS.register(new e_());
      MinecraftForge.EVENT_BUS.register(new w());
      MinecraftForge.EVENT_BUS.register(new dv.a());
      MinecraftForge.EVENT_BUS.register(new gm());
      MinecraftForge.EVENT_BUS.register(new c6());
      MinecraftForge.EVENT_BUS.register(new a.b());
      MinecraftForge.EVENT_BUS.register(new br.a());
      MinecraftForge.EVENT_BUS.register(new gb());
      MinecraftForge.EVENT_BUS.register(new ga());
      MinecraftForge.EVENT_BUS.register(new hf());
   }

   static boolean a() {
      File var0 = new File("sexmod/dontAskAgain");

      boolean var10000;
      try {
         var0.getParentFile().mkdirs();
         if (!var0.exists()) {
            var10000 = true;
            return var10000;
         }
      } catch (RuntimeException var1) {
         throw a(var1);
      }

      var10000 = false;
      return var10000;
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
