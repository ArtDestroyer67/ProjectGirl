package com.trolmastercard.sexmod;

import java.util.HashMap;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class c1<T extends IAnimatable> extends AnimationProcessor<T> {
   HashMap<String, IBone> a = new HashMap();

   public c1(IAnimatableModel var1) {
      super(var1);
   }

   public IBone getBone(String var1) {
      return (IBone)this.a.get(var1);
   }

   public void registerModelRenderer(IBone var1) {
      super.registerModelRenderer(var1);
      this.a.put(var1.getName(), var1);
   }

   public void clearModelRendererList() {
      super.clearModelRendererList();
      this.a.clear();
   }
}
