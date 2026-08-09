package com.trolmastercard.sexmod;

import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.world.World;

public class bb extends MultiPartEntityPart {
   public boolean a = false;

   public bb(World var1) {
      super((IEntityMultiPart)null, "", 0.0F, 0.0F);
   }

   public bb(IEntityMultiPart var1, String var2, float var3, float var4) {
      super(var1, var2, var3, var4);
   }

   public boolean func_70067_L() {
      return this.a;
   }
}
