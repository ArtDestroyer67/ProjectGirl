package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ad {
   static final int a = 60;
   public static float[] b;

   public ad() {
      if (a()) {
         b = new float[60];
      }

   }

   public static boolean a() {
      return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(ClientChatEvent var1) {
      try {
         if (!a()) {
            return;
         }
      } catch (NullPointerException var2) {
         throw a((Exception)var2);
      }

      try {
         if (!"resetcolor".equalsIgnoreCase(var1.getMessage())) {
            return;
         }
      } catch (NullPointerException var3) {
         throw a((Exception)var3);
      }

      dj.c();
      de.e();
      dy.c();
      dg.e();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void c(ClientChatEvent var1) {
      try {
         if (!a()) {
            return;
         }
      } catch (Exception var10) {
         throw a(var10);
      }

      String var2 = var1.getOriginalMessage();
      String[] var3 = var2.split(" ");

      try {
         if (var3.length != 3) {
            return;
         }
      } catch (Exception var9) {
         throw a(var9);
      }

      try {
         if (!"set".equalsIgnoreCase(var3[0])) {
            return;
         }
      } catch (Exception var7) {
         throw a(var7);
      }

      int var4;
      float var5;
      try {
         var4 = Integer.parseInt(var3[1]);
         var5 = Float.parseFloat(var3[2]);
         if (b.length - 1 < var4) {
            return;
         }
      } catch (Exception var8) {
         return;
      }

      Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(String.format("%sSet dev float N.%s from %s to %s", TextFormatting.GRAY, var4, b[var4], var5)));
      b[var4] = var5;
      var1.setCanceled(true);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void b(ClientChatEvent var1) {
      try {
         if (!a()) {
            return;
         }
      } catch (Exception var9) {
         throw a(var9);
      }

      String var2 = var1.getOriginalMessage();
      String[] var3 = var2.split(" ");

      try {
         if (var3.length != 2) {
            return;
         }
      } catch (Exception var8) {
         throw a(var8);
      }

      try {
         if (!"get".equalsIgnoreCase(var3[0])) {
            return;
         }
      } catch (Exception var6) {
         throw a(var6);
      }

      int var4;
      try {
         var4 = Integer.parseInt(var3[1]);
         if (b.length - 1 < var4) {
            return;
         }
      } catch (Exception var7) {
         return;
      }

      Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(String.format("%sdev float N.%s is %s", TextFormatting.YELLOW, var4, b[var4])));
      var1.setCanceled(true);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(LivingHurtEvent var1) {
      try {
         if (!a()) {
            return;
         }
      } catch (NullPointerException var12) {
         throw a((Exception)var12);
      }

      EntityPlayerSP var2 = Minecraft.func_71410_x().field_71439_g;
      EntityLivingBase var3 = var1.getEntityLiving();

      try {
         if (!(var3 instanceof ff)) {
            return;
         }
      } catch (NullPointerException var11) {
         throw a((Exception)var11);
      }

      ff var4 = (ff)var3;
      UUID var5 = ax.a(((EntityPlayer)var2).getPersistentID());

      for(bs var8 : ax.p(var5)) {
         this.a("task: " + var8.d().name());
         this.a("workers involved: ");

         for(ff var10 : var8.c()) {
            this.a(var10.c() + " " + var10.f());
         }
      }

      this.a("tribe contains my exact reference: " + ax.n(var5).contains(var4));
      this.a("tribe contains my ID: ");
      boolean var13 = false;

      for(ff var16 : ax.n(var5)) {
         if (var16.f().equals(var4.f())) {
            var13 = true;
         }
      }

      boolean var15 = false;

      for(Map.Entry var18 : ax.a(var5, var2.field_70170_p).entrySet()) {
         if (((UUID)var18.getKey()).equals(var4.f())) {
            var15 = true;
         }
      }

      this.a("loaded : " + var13);
      this.a("saved : " + var15);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void d(ClientChatEvent var1) {
      try {
         if (!a()) {
            return;
         }
      } catch (NullPointerException var13) {
         throw a((Exception)var13);
      }

      String var2 = var1.getOriginalMessage().toLowerCase();
      EntityPlayerSP var3 = Minecraft.func_71410_x().field_71439_g;

      try {
         if ("time".equals(var2)) {
            ((EntityPlayer)var3).func_145747_a(new TextComponentString(String.valueOf(var3.field_70170_p.func_82737_E())));
         }
      } catch (NullPointerException var11) {
         throw a((Exception)var11);
      }

      if ("girls".equals(var2)) {
         List var4 = var3.field_70170_p.func_175644_a(em.class, (var0) -> true);
         ((EntityPlayer)var3).func_145747_a(new TextComponentString(String.valueOf(var4.size())));

         for(em var6 : var4) {
            System.out.printf("%s at %s %s %s\n", var6, var6.field_70165_t, var6.field_70163_u, var6.field_70161_v);
         }
      }

      if ("kobs".equals(var2)) {
         UUID var14 = ax.a(((EntityPlayer)var3).getPersistentID());
         int var16 = ax.h(var14);

         for(ff var8 : ax.n(var14)) {
            ad var10000;
            String var10001;
            label64: {
               try {
                  this.a(String.format("alive member %s at %s world.isremote? %s isdead %s girlID %s entityID %s", var8.c(), var8.func_180425_c(), var8.field_70170_p.field_72995_K, var8.field_70128_L, var8.f(), var8.func_145782_y()));
                  var10000 = this;
                  if (var3.field_70170_p.func_72872_a(ff.class, new AxisAlignedBB(var8.func_180425_c())).isEmpty()) {
                     var10001 = "couldn't be located";
                     break label64;
                  }
               } catch (NullPointerException var12) {
                  throw a((Exception)var12);
               }

               var10001 = "appears to actually exist";
            }

            var10000.a(var10001);
         }

         HashMap var19 = ax.a(var14, var3.field_70170_p);

         for(Map.Entry var9 : var19.entrySet()) {
            this.a(String.format("saved pos of %s at %s", ((UUID)var9.getKey()).toString(), ((BlockPos)var9.getValue()).toString()));
         }

         this.a("total amount members: " + var16);
      }

      if (var2.startsWith("setcumtime ")) {
         String[] var15 = var2.split(" ");

         long var17;
         try {
            var17 = Long.parseLong(var15[1]);
         } catch (NullPointerException var10) {
            System.out.println("long: " + var15[1]);
            var10.printStackTrace();
            return;
         }

         v.a(((EntityPlayer)var3).getPersistentID(), var17);
         ((EntityPlayer)var3).func_145747_a(new TextComponentString("set to: " + var17));
      }

   }

   @SideOnly(Side.CLIENT)
   void a(String var1) {
      Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(var1));
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
