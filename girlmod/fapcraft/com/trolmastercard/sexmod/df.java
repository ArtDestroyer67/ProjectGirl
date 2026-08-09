package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest2;

public class df extends EntityAIWatchClosest2 {
   public boolean a = true;

   public df(EntityLiving var1, Class<? extends Entity> var2, float var3, float var4) {
      super(var1, var2, var3, var4);
   }

   public void func_75246_d() {
      try {
         if (this.a) {
            super.func_75246_d();
         }

      } catch (RuntimeException var1) {
         throw a(var1);
      }
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
