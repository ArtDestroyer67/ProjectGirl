package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class gz implements IMessage {
   boolean a;
   boolean b;

   public gz(boolean var1) {
      this.b = var1;
      this.a = true;
   }

   public gz() {
      this.a = false;
   }

   public void fromBytes(ByteBuf var1) {
      this.b = var1.readBoolean();
      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
      var1.writeBoolean(this.b);
      this.a = true;
   }

   public static class a implements IMessageHandler<gz, IMessage> {
      public IMessage a(gz param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static Exception a(Exception var0) {
         return var0;
      }
   }
}
