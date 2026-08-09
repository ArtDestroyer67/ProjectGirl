package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class e_ {
   public static final float c = 1.2345679F;
   Vec3d b = null;
   Vec3d d = null;
   ei a = null;
   boolean e = false;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderPlayerEvent.Pre var1) {
      try {
         if (var1.getPartialRenderTick() == 1.2345679F) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      ei.C();
      ei var2 = ei.d(var1.getEntityPlayer().getPersistentID());

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var3) {
         throw a(var3);
      }

      var1.setCanceled(true);
      a(var2, var1.getEntityPlayer(), var1.getX(), var1.getY(), var1.getZ(), var1.getPartialRenderTick());
   }

   @SideOnly(Side.CLIENT)
   public static void a(ei param0, EntityPlayer param1, double param2, double param4, double param6, float param8) {
      // $FF: Couldn't be decompiled
   }

   static float a(ei param0, EntityPlayer param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(TickEvent.RenderTickEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(EntityViewRenderEvent.CameraSetup var1) {
      Minecraft var2 = Minecraft.func_71410_x();

      try {
         if (var2.field_71439_g == null) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      ei var3 = ei.d(var2.field_71439_g.getPersistentID());

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      try {
         if (!var3.F()) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (!var3.Q()) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      var1.setRoll(180.0F);
      var1.setPitch(-var1.getPitch());
      var1.setYaw(-var1.getYaw());
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderWorldLastEvent var1) {
      Minecraft var2 = Minecraft.func_71410_x();

      try {
         if (this.b == null) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (var2.field_71474_y.field_74320_O != 0) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      ei var3 = ei.d(var2.field_71439_g.getPersistentID());

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      Vec3d var4 = var2.field_71439_g.func_174791_d();
      Vec3d var5 = b6.a(this.d, this.b, (double)var1.getPartialTicks());
      Vec3d var6 = var5.func_178788_d(var4);
      a(var3, var2.field_71439_g, var6.field_72450_a, var6.field_72448_b, var6.field_72449_c, var1.getPartialTicks());
      GlStateManager.func_179145_e();
      GlStateManager.func_179126_j();
      GlStateManager.func_179141_d();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void b(TickEvent.RenderTickEvent param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
