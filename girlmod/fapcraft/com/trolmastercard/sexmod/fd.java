package com.trolmastercard.sexmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IClientCommand;

public class fd extends CommandBase implements IClientCommand {
   public static final fd a = new fd();

   public String func_71517_b() {
      return "whitelistserver";
   }

   public String func_71518_a(ICommandSender var1) {
      return "/whitelistserver";
   }

   public boolean allowUsageWithoutPrefix(ICommandSender var1, String var2) {
      return false;
   }

   public boolean func_184882_a(MinecraftServer var1, ICommandSender var2) {
      return true;
   }

   public void func_184881_a(MinecraftServer param1, ICommandSender param2, String[] param3) throws CommandException {
      // $FF: Couldn't be decompiled
   }

   private static CommandException a(CommandException var0) {
      return var0;
   }
}
