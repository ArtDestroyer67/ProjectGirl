package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ga {
   static final ResourceLocation b = new ResourceLocation("sexmod", "textures/cummy.png");
   static Minecraft c = Minecraft.func_71410_x();
   static List<ep> a = new ArrayList();

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderWorldLastEvent var1) {
      c.field_71446_o.func_110577_a(b);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Tessellator var2 = Tessellator.func_178181_a();
      BufferBuilder var3 = var2.func_178180_c();
      float var4 = var1.getPartialTicks();

      try {
         GlStateManager.func_179140_f();
         GlStateManager.func_179141_d();
         if (c.field_71439_g == null) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      for(ep var6 : a) {
         var6.a(c, var2, var3, var4);
      }

      GlStateManager.func_179126_j();
      GlStateManager.func_179145_e();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(TickEvent.ClientTickEvent var1) {
      try {
         if (var1.phase == Phase.END) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      for(ep var3 : a) {
         var3.a();
      }

   }

   public static void a(ep var0) {
      a.add(var0);
   }

   public static void a(int var0, ar var1, b8 var2, em var3, float var4, float var5) {
      a.add(new ep(var0, var1, var2, var3, var4, var5));
   }

   public static void a(@Nonnull em var0) {
      ArrayList var1 = new ArrayList();

      for(ep var3 : a) {
         try {
            if (var3.e.f().equals(var0.f())) {
               var1.add(var3);
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }
      }

      a.removeAll(var1);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
