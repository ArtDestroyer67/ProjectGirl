package com.trolmastercard.sexmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ho extends EntityEnderPearl {
   public ho(World var1) {
      super(var1);
   }

   public ho(World var1, EntityLivingBase var2) {
      super(var1, var2);
   }

   protected void func_70184_a(RayTraceResult param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a {
      @SubscribeEvent
      public void a(EnderTeleportEvent var1) {
         if (var1.getEntityLiving() instanceof em) {
            em var2 = (em)var1.getEntityLiving();
            var2.q = null;
            var2.b(fp.NULL);
            var2.func_184212_Q().func_187227_b(em.G, false);
            var2.x();
         }

      }
   }
}
