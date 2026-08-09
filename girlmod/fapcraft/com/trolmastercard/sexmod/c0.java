package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;

public class c0 extends cv {
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/bee/bee.geo.json"), new ResourceLocation("sexmod", "geo/bee/armored.geo.json")};
   }

   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/bee/bee.png");
   }

   public ResourceLocation b(em var1) {
      return new ResourceLocation("sexmod", "animations/bee/bee.animation.json");
   }

   public void a(em param1, Integer param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   protected void a(em param1, AnimationProcessor param2, AnimationEvent param3) {
      // $FF: Couldn't be decompiled
   }

   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   public String[] g() {
      return new String[]{"band", "feeler", "feeler2", "brow", "brow2", "brow3", "brow4"};
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
      return new String[]{"sideL", "sideR", "fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
