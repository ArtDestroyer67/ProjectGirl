package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class l {
   Vec3d b = null;
   Vec3d a = null;

   @SubscribeEvent
   public void a(RenderPlayerEvent.Pre param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(RenderHandEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(TickEvent.RenderTickEvent param1) {
      // $FF: Couldn't be decompiled
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
