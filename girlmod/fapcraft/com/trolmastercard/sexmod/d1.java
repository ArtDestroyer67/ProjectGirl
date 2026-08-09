package com.trolmastercard.sexmod;

import java.util.HashSet;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class d1 extends d_ {
   public d1(RenderManager var1, AnimatedGeoModel var2, double var3) {
      super(var1, var2, var3);
   }

   public HashSet<String> a() {
      HashSet var1 = super.a();
      var1.add("figure");
      return var1;
   }
}
