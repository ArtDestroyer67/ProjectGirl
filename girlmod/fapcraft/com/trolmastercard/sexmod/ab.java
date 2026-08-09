package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ab implements IMessage {
   boolean c = false;
   UUID a;
   UUID b;

   public ab() {
   }

   public ab(UUID var1, UUID var2) {
      this.a = var1;
      this.b = var2;
   }

   public void fromBytes(ByteBuf var1) {
      try {
         this.a = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      } catch (Exception var4) {
         this.a = null;
      }

      try {
         this.b = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      } catch (Exception var3) {
         this.b = null;
      }

      this.c = true;
   }

   public void toBytes(ByteBuf var1) {
      ByteBuf var10000;
      String var10001;
      label28: {
         try {
            var10000 = var1;
            if (this.a == null) {
               var10001 = "trol was here";
               break label28;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         var10001 = this.a.toString();
      }

      label21: {
         try {
            ByteBufUtils.writeUTF8String(var10000, var10001);
            var10000 = var1;
            if (this.b == null) {
               var10001 = "trol was here";
               break label21;
            }
         } catch (RuntimeException var2) {
            throw a(var2);
         }

         var10001 = this.b.toString();
      }

      ByteBufUtils.writeUTF8String(var10000, var10001);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a implements IMessageHandler<ab, IMessage> {
      public IMessage a(ab param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
