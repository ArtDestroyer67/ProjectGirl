package com.trolmastercard.sexmod;

import java.util.function.Function;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;

public class bz<T extends IAnimatable> extends AnimationController<T> {
   public bz(T var1, String var2, float var3, AnimationController.IAnimationPredicate<T> var4) {
      super(var1, var2, var3, var4);
   }

   public bz(T var1, String var2, float var3, EasingType var4, AnimationController.IAnimationPredicate<T> var5) {
      super(var1, var2, var3, var4, var5);
   }

   public bz(T var1, String var2, float var3, Function<Double, Double> var4, AnimationController.IAnimationPredicate<T> var5) {
      super(var1, var2, var3, var4, var5);
   }
}
