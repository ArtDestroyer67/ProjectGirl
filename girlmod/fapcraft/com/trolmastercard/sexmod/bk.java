package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class bk implements IMessage {
   boolean a = false;

   public void fromBytes(ByteBuf var1) {
      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
   }

   public static class a implements IMessageHandler<bk, IMessage> {
      public IMessage a(bk param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
