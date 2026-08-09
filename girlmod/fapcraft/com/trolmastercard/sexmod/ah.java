package com.trolmastercard.sexmod;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ah {
   @SubscribeEvent
   public void b(LivingAttackEvent var1) {
      try {
         if (var1.getSource() == DamageSource.field_76380_i) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      try {
         if (!(var1.getEntity() instanceof em)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      em var2 = (em)var1.getEntity();

      try {
         if (var2 instanceof ei) {
            var1.setCanceled(true);
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      LivingAttackEvent var10000;
      boolean var10001;
      label32: {
         try {
            var10000 = var1;
            if (var2.ae() != null) {
               var10001 = true;
               break label32;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }

         var10001 = false;
      }

      var10000.setCanceled(var10001);
   }

   @SubscribeEvent
   public void a(LivingAttackEvent param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
