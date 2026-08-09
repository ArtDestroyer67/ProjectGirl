package com.trolmastercard.sexmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class aw extends CommandBase {
   public static final aw a = new aw();

   public String func_71517_b() {
      return "reloadcustommodels";
   }

   public String func_71518_a(ICommandSender var1) {
      return "/reloadcustommodels";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_184881_a(MinecraftServer var1, ICommandSender var2, String[] var3) throws CommandException {
      br.b(false);

      for(EntityPlayerMP var5 : var1.func_184103_al().func_181057_v()) {
         var1.func_152344_a(() -> ge.b.sendTo(new g6(br.e()), var5));
      }

   }
}
