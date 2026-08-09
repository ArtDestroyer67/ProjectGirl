package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ft implements IMessage {
   boolean a;
   UUID c;
   NBTTagCompound b;

   public ft() {
   }

   public ft(UUID var1, NBTTagCompound var2) {
      this.c = var1;
      this.b = var2;
   }

   public void fromBytes(ByteBuf var1) {
      this.c = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      this.b = ByteBufUtils.readTag(var1);
      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
      ByteBufUtils.writeUTF8String(var1, this.c.toString());
      ByteBufUtils.writeTag(var1, this.b);
   }

   public static class a implements IMessageHandler<ft, IMessage> {
      public IMessage a(ft var1, MessageContext var2) {
         try {
            if (!var1.a) {
               System.out.println("received an invalid message @UpdateEquipment :(");
               return null;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
            for(em var3 : em.g(var1.c)) {
               try {
                  if (var3 instanceof e2) {
                     ((e2)var3).Q.deserializeNBT(var1.b);
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
