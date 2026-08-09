package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class fx extends CommandBase implements IClientCommand {
   public static final fx a = new fx();

   public boolean allowUsageWithoutPrefix(ICommandSender var1, String var2) {
      return false;
   }

   public String func_71517_b() {
      return "setmodelcode";
   }

   public String func_71518_a(ICommandSender var1) {
      return "/setmodelcode";
   }

   public boolean func_184882_a(MinecraftServer var1, ICommandSender var2) {
      return true;
   }

   public void func_184881_a(MinecraftServer var1, ICommandSender var2, String[] var3) throws CommandException {
      Minecraft var4 = Minecraft.func_71410_x();
      EntityPlayerSP var5 = var4.field_71439_g;
      String var6 = "";
      String var7 = "";
      if (var3.length > 0) {
         String[] var8 = var3[0].split("\\$");
         var6 = var8[0];
         if (var8.length > 1) {
            var7 = var8[1];
         }
      }

      RayTraceResult var12 = Minecraft.func_71410_x().field_71476_x;
      em var9 = this.a(var12);

      try {
         if (var9 == null) {
            ((EntityPlayer)var5).func_146105_b(new TextComponentString("You gotta transform into the girl you want to apply the model-code to"), true);
            return;
         }
      } catch (CommandException var10) {
         throw a((Exception)var10);
      }

      try {
         if ("".equals(var7)) {
            ge.b.sendToServer(new fw(var6, var9.f()));
            ((EntityPlayer)var5).func_146105_b(new TextComponentString(this.a(var9)), true);
            return;
         }
      } catch (CommandException var11) {
         throw a((Exception)var11);
      }

      ge.b.sendToServer(new fw(var6, var9.f(), em.c(var7)));
      ((EntityPlayer)var5).func_146105_b(new TextComponentString(this.a(var9)), true);
   }

   String a(em var1) {
      try {
         if (var1 instanceof ei) {
            return TextFormatting.YELLOW + "applied model code to your player-" + be.b(fy.a((Entity)var1).toString());
         }
      } catch (RuntimeException var2) {
         throw a((Exception)var2);
      }

      return TextFormatting.YELLOW + "applied model code to this " + var1.c();
   }

   @SideOnly(Side.CLIENT)
   em a(RayTraceResult var1) {
      try {
         if (var1 == null) {
            return ei.g(Minecraft.func_71410_x().field_71439_g);
         }
      } catch (RuntimeException var2) {
         throw a((Exception)var2);
      }

      try {
         if (em.a(var1.field_72308_g)) {
            return (em)var1.field_72308_g;
         }
      } catch (RuntimeException var3) {
         throw a((Exception)var3);
      }

      return ei.g(Minecraft.func_71410_x().field_71439_g);
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
