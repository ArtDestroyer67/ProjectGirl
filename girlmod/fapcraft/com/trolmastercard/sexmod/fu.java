package com.trolmastercard.sexmod;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class fu {
   static final int a = 284453;

   @SubscribeEvent
   public void a(PlayerSleepInBedEvent var1) {
      EntityPlayer var2 = var1.getEntityPlayer();
      ei var3 = ei.g(var2);

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      try {
         if (!var2.func_70093_af()) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      var1.setResult(SleepResult.OTHER_PROBLEM);
   }

   @SubscribeEvent
   public void a(GetCollisionBoxesEvent var1) {
   }

   @SubscribeEvent
   public void a(PlayerInteractEvent.RightClickBlock var1) {
      ei var2 = ei.d(var1.getEntityPlayer().getPersistentID());
      BlockPos var3 = var1.getPos();
      World var4 = var1.getEntityPlayer().field_70170_p;
      EntityPlayer var5 = var1.getEntityPlayer();

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var29) {
         throw a(var29);
      }

      try {
         if (!var2.v()) {
            return;
         }
      } catch (RuntimeException var21) {
         throw a(var21);
      }

      try {
         if (!cj.a(var4, var3, var1.getHitVec(), var1.getFace(), var5)) {
            return;
         }
      } catch (RuntimeException var28) {
         throw a(var28);
      }

      try {
         if ((Boolean)var2.func_184212_Q().func_187225_a(em.G)) {
            var1.setCanceled(true);
            return;
         }
      } catch (RuntimeException var20) {
         throw a(var20);
      }

      try {
         if (!var5.func_70093_af()) {
            return;
         }
      } catch (RuntimeException var27) {
         throw a(var27);
      }

      ArrayList var6 = new ArrayList();

      try {
         if (var4.func_180495_p(var3.func_177978_c()).func_177230_c() == Blocks.field_150350_a) {
            var6.add(var3.func_177978_c());
         }
      } catch (RuntimeException var19) {
         throw a(var19);
      }

      try {
         if (var4.func_180495_p(var3.func_177974_f()).func_177230_c() == Blocks.field_150350_a) {
            var6.add(var3.func_177974_f());
         }
      } catch (RuntimeException var26) {
         throw a(var26);
      }

      try {
         if (var4.func_180495_p(var3.func_177968_d()).func_177230_c() == Blocks.field_150350_a) {
            var6.add(var3.func_177968_d());
         }
      } catch (RuntimeException var18) {
         throw a(var18);
      }

      try {
         if (var4.func_180495_p(var3.func_177976_e()).func_177230_c() == Blocks.field_150350_a) {
            var6.add(var3.func_177976_e());
         }
      } catch (RuntimeException var25) {
         throw a(var25);
      }

      BlockPos var7 = null;

      for(BlockPos var9 : var6) {
         if (var7 == null) {
            var7 = var9;
         } else {
            Vec3d var10 = var5.func_174791_d();
            double var11 = this.a((double)var9.func_177958_n(), (double)var9.func_177956_o(), (double)var9.func_177952_p(), var10.field_72450_a, var10.field_72448_b, var10.field_72449_c);
            double var13 = this.a((double)var7.func_177958_n(), (double)var7.func_177956_o(), (double)var7.func_177952_p(), var10.field_72450_a, var10.field_72448_b, var10.field_72449_c);
            if (var11 < var13) {
               var7 = var9;
            }
         }
      }

      try {
         if (var7 == null) {
            var5.func_145747_a(new TextComponentString("Bed is obscured"));
            return;
         }
      } catch (RuntimeException var17) {
         throw a(var17);
      }

      try {
         var5.func_70107_b((double)var7.func_177958_n() + (double)0.5F, (double)var7.func_177956_o(), (double)var7.func_177952_p() + (double)0.5F);
         if (var3.func_177978_c().equals(var7)) {
            var5.field_70177_z = 0.0F;
         }
      } catch (RuntimeException var24) {
         throw a(var24);
      }

      try {
         if (var3.func_177974_f().equals(var7)) {
            var5.field_70177_z = 90.0F;
         }
      } catch (RuntimeException var16) {
         throw a(var16);
      }

      try {
         if (var3.func_177968_d().equals(var7)) {
            var5.field_70177_z = 180.0F;
         }
      } catch (RuntimeException var23) {
         throw a(var23);
      }

      try {
         if (var3.func_177976_e().equals(var7)) {
            var5.field_70177_z = -90.0F;
         }
      } catch (RuntimeException var15) {
         throw a(var15);
      }

      try {
         if (var1.getWorld().field_72995_K) {
            d3.a(false);
            var2.H();
            return;
         }
      } catch (RuntimeException var22) {
         throw a(var22);
      }

      var2.c((Vec3d)(new Vec3d((double)var7.func_177958_n() + (double)0.5F, (double)((float)var7.func_177956_o() + 0.0F), (double)var7.func_177952_p() + (double)0.5F)));
      var2.b(var5.field_70177_z);
      var2.func_184212_Q().func_187227_b(em.G, true);
      var2.u();
   }

   double a(double var1, double var3, double var5, double var7, double var9, double var11) {
      double var13 = var1 - var7;
      double var15 = var3 - var9;
      double var17 = var5 - var11;
      return Math.sqrt(var13 * var13 + var15 * var15 + var17 * var17);
   }

   @SubscribeEvent
   public void a(PlayerEvent.PlayerRespawnEvent var1) {
      EntityPlayer var2 = var1.player;

      try {
         if (var2 == null) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      ei var3 = ei.a(var2.getPersistentID());

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      Vec3d var4 = var2.func_174791_d();
      var3.field_71093_bK = var2.field_71093_bK;
      var3.func_70634_a(var4.field_72450_a, var4.field_72448_b, var4.field_72449_c);
      var3.func_70619_bc();
      System.out.println(var2.field_70170_p.func_175697_a(var3.func_180425_c(), 2));
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void b(PlayerInteractEvent.EntityInteract var1) {
      try {
         if (!(var1.getTarget() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException var12) {
         throw a(var12);
      }

      try {
         if (var1.getEntityPlayer().func_70093_af()) {
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (!var1.getEntityPlayer().getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
            return;
         }
      } catch (RuntimeException var11) {
         throw a(var11);
      }

      EntityPlayerSP var2 = Minecraft.func_71410_x().field_71439_g;
      ei var3 = ei.d(((EntityPlayer)var2).getPersistentID());
      EntityPlayer var4 = (EntityPlayer)var1.getTarget();
      ei var5 = ei.g(var4);

      try {
         if (var5 == null) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      try {
         if (var3 != null) {
            ((EntityPlayer)var2).func_146105_b(new TextComponentString("no lesbo yet owo"), true);
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (!var5.l()) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      try {
         if (var5.p()) {
            var5.b((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
         }

      } catch (RuntimeException var6) {
         throw a(var6);
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(PlayerInteractEvent.EntityInteract var1) {
      try {
         if (!(var1.getTarget() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      try {
         if (!var1.getEntityPlayer().getPersistentID().equals(Minecraft.func_71410_x().field_71439_g.getPersistentID())) {
            return;
         }
      } catch (RuntimeException var10) {
         throw a(var10);
      }

      EntityPlayerSP var2 = Minecraft.func_71410_x().field_71439_g;
      ei var3 = ei.d(((EntityPlayer)var2).getPersistentID());

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var9) {
         throw a(var9);
      }

      EntityPlayer var4 = (EntityPlayer)var1.getTarget();
      ei var5 = ei.d(var4.getPersistentID());

      try {
         if (var5 != null) {
            var4.func_146105_b(new TextComponentString("no lesbo yet owo"), true);
            return;
         }
      } catch (RuntimeException var8) {
         throw a(var8);
      }

      try {
         if (var3.p()) {
            var3.ab = false;
            var3.b((EntityPlayer)var4);
         }

      } catch (RuntimeException var6) {
         throw a(var6);
      }
   }

   @SubscribeEvent
   public void b(PlayerInteractEvent.RightClickBlock param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(LivingHurtEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(GuiScreenEvent.InitGuiEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(GuiScreenEvent.ActionPerformedEvent param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(LivingDamageEvent var1) {
      try {
         if (var1.getSource() != DamageSource.field_76379_h) {
            return;
         }
      } catch (RuntimeException var7) {
         throw a(var7);
      }

      EntityLivingBase var2 = var1.getEntityLiving();

      try {
         if (!(var2 instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException var6) {
         throw a(var6);
      }

      ei var3 = ei.d(var2.getPersistentID());

      try {
         if (var3 == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      try {
         if (var3 instanceof ec) {
            var1.setResult(Result.DENY);
            var1.setAmount(0.0F);
            var1.setCanceled(true);
         }

      } catch (RuntimeException var4) {
         throw a(var4);
      }
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
