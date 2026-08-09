package com.trolmastercard.sexmod;

import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class hz extends EntityAIBase {
   protected EntityLiving c;
   protected BlockPos b;
   protected BlockDoor d;
   boolean e;
   float f;
   float a;
   int g;

   public hz(EntityLiving var1) {
      this.b = BlockPos.field_177992_a;
      this.g = 10;
      this.c = var1;
      if (!(var1.func_70661_as() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   public boolean func_75250_a() {
      // $FF: Couldn't be decompiled
   }

   public boolean func_75253_b() {
      boolean var10000;
      try {
         if (this.g >= 0) {
            var10000 = true;
            return var10000;
         }
      } catch (IllegalArgumentException var1) {
         throw a(var1);
      }

      var10000 = false;
      return var10000;
   }

   public void func_75249_e() {
      this.e = false;
      this.f = (float)((double)((float)this.b.func_177958_n() + 0.5F) - this.c.field_70165_t);
      this.a = (float)((double)((float)this.b.func_177952_p() + 0.5F) - this.c.field_70161_v);
      this.d.func_176512_a(this.c.field_70170_p, this.b, true);
   }

   public void func_75246_d() {
      // $FF: Couldn't be decompiled
   }

   public void func_75251_c() {
      this.g = 10;
   }

   private BlockDoor a(BlockPos param1) {
      // $FF: Couldn't be decompiled
   }

   private static IllegalArgumentException a(IllegalArgumentException var0) {
      return var0;
   }
}
