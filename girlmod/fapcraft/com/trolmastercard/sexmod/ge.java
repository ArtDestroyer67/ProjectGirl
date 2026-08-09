package com.trolmastercard.sexmod;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ge {
   public static SimpleNetworkWrapper b;
   private static int a = 0;

   private static int b() {
      return a++;
   }

   public static void a() {
      b = NetworkRegistry.INSTANCE.newSimpleChannel("sexmodchannel");
      b.registerMessage(gh.a.class, gh.class, b(), Side.CLIENT);
      b.registerMessage(gh.a.class, gh.class, b(), Side.SERVER);
      b.registerMessage(gz.a.class, gz.class, b(), Side.CLIENT);
      b.registerMessage(a8.a.class, a8.class, b(), Side.SERVER);
      b.registerMessage(ac.a.class, ac.class, b(), Side.SERVER);
      b.registerMessage(b0.a.class, b0.class, b(), Side.SERVER);
      b.registerMessage(dc.a.class, dc.class, b(), Side.SERVER);
      b.registerMessage(a1.a.class, a1.class, b(), Side.CLIENT);
      b.registerMessage(a1.a.class, a1.class, b(), Side.SERVER);
      b.registerMessage(s.a.class, s.class, b(), Side.SERVER);
      b.registerMessage(n.a.class, n.class, b(), Side.SERVER);
      b.registerMessage(bo.a.class, bo.class, b(), Side.SERVER);
      b.registerMessage(gg.a.class, gg.class, b(), Side.SERVER);
      b.registerMessage(a6.a.class, a6.class, b(), Side.SERVER);
      b.registerMessage(b1.a.class, b1.class, b(), Side.SERVER);
      b.registerMessage(t.a.class, t.class, b(), Side.SERVER);
      b.registerMessage(bg.a.class, bg.class, b(), Side.SERVER);
      b.registerMessage(cz.a.class, cz.class, b(), Side.SERVER);
      b.registerMessage(bw.a.class, bw.class, b(), Side.SERVER);
      b.registerMessage(b_.a.class, b_.class, b(), Side.SERVER);
      b.registerMessage(g4.a.class, g4.class, b(), Side.SERVER);
      b.registerMessage(g4.a.class, g4.class, b(), Side.CLIENT);
      b.registerMessage(eu.a.class, eu.class, b(), Side.SERVER);
      b.registerMessage(f3.a.class, f3.class, b(), Side.SERVER);
      b.registerMessage(ej.a.class, ej.class, b(), Side.SERVER);
      b.registerMessage(gk.a.class, gk.class, b(), Side.SERVER);
      b.registerMessage(dq.a.class, dq.class, b(), Side.SERVER);
      b.registerMessage(g9.a.class, g9.class, b(), Side.SERVER);
      b.registerMessage(b3.a.class, b3.class, b(), Side.SERVER);
      b.registerMessage(b3.a.class, b3.class, b(), Side.CLIENT);
      b.registerMessage(fj.a.class, fj.class, b(), Side.SERVER);
      b.registerMessage(fc.a.class, fc.class, b(), Side.SERVER);
      b.registerMessage(h6.a.class, h6.class, b(), Side.CLIENT);
      b.registerMessage(h6.a.class, h6.class, b(), Side.SERVER);
      b.registerMessage(au.a.class, au.class, b(), Side.SERVER);
      b.registerMessage(en.a.class, en.class, b(), Side.CLIENT);
      b.registerMessage(z.a.class, z.class, b(), Side.SERVER);
      b.registerMessage(e6.a.class, e6.class, b(), Side.SERVER);
      b.registerMessage(bd.a.class, bd.class, b(), Side.CLIENT);
      b.registerMessage(gd.a.class, gd.class, b(), Side.CLIENT);
      b.registerMessage(fw.a.class, fw.class, b(), Side.SERVER);
      b.registerMessage(g_.a.class, g_.class, b(), Side.SERVER);
      b.registerMessage(ct.a.class, ct.class, b(), Side.SERVER);
      b.registerMessage(g6.a.class, g6.class, b(), Side.SERVER);
      b.registerMessage(g6.a.class, g6.class, b(), Side.CLIENT);
      b.registerMessage(cu.a.class, cu.class, b(), Side.SERVER);
      b.registerMessage(cu.a.class, cu.class, b(), Side.CLIENT);
      b.registerMessage(bv.a.class, bv.class, b(), Side.CLIENT);
      b.registerMessage(cd.a.class, cd.class, b(), Side.SERVER);
      b.registerMessage(gf.a.class, gf.class, b(), Side.CLIENT);
      b.registerMessage(bk.a.class, bk.class, b(), Side.SERVER);
      b.registerMessage(ab.a.class, ab.class, b(), Side.CLIENT);
      b.registerMessage(aq.a.class, aq.class, b(), Side.CLIENT);
   }
}
