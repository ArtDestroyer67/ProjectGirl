package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class gf implements IMessage {
   boolean a = false;
   boolean b;

   public gf() {
   }

   public gf(boolean var1) {
      this.b = var1;
   }

   public void fromBytes(ByteBuf var1) {
      this.b = var1.readBoolean();
      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
      var1.writeBoolean(this.b);
   }

   public static class a implements IMessageHandler<gf, IMessage> {
      public IMessage a(gf param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
