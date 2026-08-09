package com.trolmastercard.sexmod;

import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class cx extends cv {
   HashMap<Integer, float[]> f = new HashMap<Integer, float[]>() {
      {
         this.put(0, new float[]{0.0F, -1.2F, 1.2F});
         this.put(-90, new float[]{2.0F, -71.56F, -68.0F});
         this.put(90, new float[]{-2.0F, 68.0F, 70.5F});
      }
   };

   public cx() {
      this.c = this.a();
   }

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/ellie/nude.geo.json"), new ResourceLocation("sexmod", "geo/ellie/dressed.geo.json")};
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/ellie/ellie.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/ellie/ellie.animation.json");
   }

   public void a(em param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] g() {
      return new String[]{"headband"};
   }

   public String[] f() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
   }

   public String[] h() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   public String[] e() {
      return new String[]{"fleshL", "fleshR", "vagina", "hotpants", "slip", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
