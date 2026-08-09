package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class en implements IMessage {
   boolean d = false;
   UUID c;
   String b;
   int a;

   public en() {
   }

   public en(UUID var1, String var2) {
      this.c = var1;
      this.b = var2;
      this.a = 1;
   }

   public en(UUID var1, String var2, int var3) {
      this.c = var1;
      this.b = var2;
      this.a = var3;
   }

   public void fromBytes(ByteBuf var1) {
      this.c = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      this.b = ByteBufUtils.readUTF8String(var1);
      this.a = var1.readInt();
      this.d = true;
   }

   public void toBytes(ByteBuf var1) {
      ByteBufUtils.writeUTF8String(var1, this.c.toString());
      ByteBufUtils.writeUTF8String(var1, this.b);
      var1.writeInt(this.a);
   }

   public static class a implements IMessageHandler<en, IMessage> {
      public IMessage a(en param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
