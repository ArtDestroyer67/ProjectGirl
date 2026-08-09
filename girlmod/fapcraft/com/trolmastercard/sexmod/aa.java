package com.trolmastercard.sexmod;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class aa extends EntityAINearestAttackableTarget<ff> {
   private final int a;
   private final boolean b;

   public aa(EntityCreature var1, boolean var2, boolean var3) {
      this(var1, var2, false, var3);
   }

   public aa(EntityCreature var1, boolean var2, boolean var3, boolean var4) {
      this(var1, 10, var2, var3, (Predicate)null, var4);
   }

   public aa(EntityCreature var1, int var2, boolean var3, boolean var4, @Nullable Predicate var5, boolean var6) {
      super(var1, ff.class, var2, var3, var4, var5);
      this.a = var2;
      this.b = var6;
   }

   public boolean func_75250_a() {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
