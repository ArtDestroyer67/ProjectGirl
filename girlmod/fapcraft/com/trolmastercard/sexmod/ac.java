package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ac implements IMessage {
   boolean a;
   UUID b;

   public ac() {
      this.a = false;
   }

   public ac(UUID var1) {
      this.b = var1;
   }

   public void fromBytes(ByteBuf var1) {
      this.b = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
      ByteBufUtils.writeUTF8String(var1, this.b.toString());
   }

   public static class a implements IMessageHandler<ac, IMessage> {
      public IMessage a(ac var1, MessageContext var2) {
         try {
            if (!var1.a) {
               System.out.println("received an invalid message @SendGirlToSex :(");
               return null;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
            for(em var3 : em.g(var1.b)) {
               try {
                  if (var3.field_70170_p.field_72995_K) {
                     continue;
                  }
               } catch (RuntimeException var5) {
                  throw a(var5);
               }

               try {
                  if (var3 instanceof fg) {
                     ((fg)var3).a();
                  }
               } catch (RuntimeException var4) {
                  throw a(var4);
               }
            }

         });
         return null;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
