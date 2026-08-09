package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class am {
   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderWorldLastEvent var1) {
      Minecraft var2 = Minecraft.func_71410_x();

      try {
         if (var2.field_71474_y.field_74320_O != 0) {
            return;
         }
      } catch (ConcurrentModificationException var15) {
         throw a(var15);
      }

      UUID var3 = var2.field_71439_g.getPersistentID();
      em var4 = null;

      try {
         for(em var6 : em.ad()) {
            try {
               if (var6 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException var13) {
               throw a(var13);
            }

            try {
               if (var6.field_70128_L) {
                  continue;
               }
            } catch (ConcurrentModificationException var12) {
               throw a(var12);
            }

            try {
               if (!var6.field_70170_p.field_72995_K) {
                  continue;
               }
            } catch (ConcurrentModificationException var11) {
               throw a(var11);
            }

            try {
               if (!(var6 instanceof ai)) {
                  continue;
               }
            } catch (ConcurrentModificationException var10) {
               throw a(var10);
            }

            ai var7 = (ai)var6;
            if (var3.equals(var7.e())) {
               var4 = var6;
               break;
            }
         }
      } catch (ConcurrentModificationException var14) {
      }

      try {
         if (var4 == null) {
            return;
         }
      } catch (ConcurrentModificationException var9) {
         throw a(var9);
      }

      Render var16 = var2.func_175598_ae().func_78713_a(var4);

      try {
         if (var16 == null) {
            return;
         }
      } catch (ConcurrentModificationException var8) {
         throw a(var8);
      }

      float var17 = var2.field_71439_g.field_70177_z;
      dy.N = (float)((double)var2.field_71439_g.field_71158_b.field_78902_a * dy.G.field_72450_a);
      dy.N += -(var17 - dy.H) * 3.0F;
      dy.N = b6.a(dy.I, dy.N, 0.1F);
      float var18 = -var2.field_71439_g.field_70125_A;
      dy.x = (float)((double)var2.field_71439_g.field_71158_b.field_192832_b * dy.G.field_72449_c + (double)((float)var2.field_71439_g.field_70181_x) * dy.G.field_72448_b);
      dy.x += -(var18 - dy.t) * 3.0F;
      dy.x = b6.a(dy.E, dy.x, 0.1F);
      dy.a(var4, var1.getPartialTicks());
      dy.H = var17;
      dy.I = dy.N;
      dy.t = var18;
      dy.E = dy.x;
      GlStateManager.func_179145_e();
      GlStateManager.func_179126_j();
      GlStateManager.func_179141_d();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void b(RenderWorldLastEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderHandEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderPlayerEvent.Pre param1) {
      // $FF: Couldn't be decompiled
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
