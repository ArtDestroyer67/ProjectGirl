package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class gn extends CommandBase {
   public static final gn a = new gn();

   public String func_71517_b() {
      return "locatenearestgoblinlair";
   }

   public String func_71518_a(ICommandSender var1) {
      return "/locatenearestgoblinlair";
   }

   public void func_184881_a(MinecraftServer param1, ICommandSender param2, String[] param3) throws CommandException {
      // $FF: Couldn't be decompiled
   }

   private static ConcurrentModificationException a(ConcurrentModificationException var0) {
      return var0;
   }
}
