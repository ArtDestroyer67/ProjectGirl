package com.trolmastercard.sexmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;

public class fl {
   public static BlockPos a(Path var0) {
      try {
         if (var0 == null) {
            return BlockPos.field_177992_a;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      PathPoint var1 = var0.func_75870_c();

      try {
         if (var1 == null) {
            return BlockPos.field_177992_a;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      return new BlockPos(var1.field_75839_a, var1.field_75837_b, var1.field_75838_c);
   }

   public static BlockPos a(EntityLiving var0) {
      PathNavigate var1 = var0.func_70661_as();
      Path var2 = var1.func_75505_d();
      return a(var2);
   }

   public static boolean a(Path param0, BlockPos[] param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
