package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class cl extends cv {
   public cl() {
      this.c = this.a();
   }

   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/bia/bianude.geo.json"), new ResourceLocation("sexmod", "geo/bia/biadressed.geo.json")};
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/bia/bia.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/bia/bia.animation.json");
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] g() {
      return new String[]{"leaf7", "leaf8"};
   }

   public String[] f() {
      return new String[]{"armorChest", "armorBoobs", "armorShoulderR", "armorShoulderL"};
   }

   public String[] a() {
      return new String[]{"bra", "upperBodyR", "upperBodyL"};
   }

   public String[] h() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   public String[] e() {
      return new String[]{"slip", "fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }
}
