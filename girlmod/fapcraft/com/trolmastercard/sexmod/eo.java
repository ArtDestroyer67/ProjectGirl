package com.trolmastercard.sexmod;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class eo {
   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void a(LivingDeathEvent var1) {
      if (var1.getEntity() instanceof em) {
         em var2 = (em)var1.getEntity();
         em.ad().remove(var2);
      }

   }
}
