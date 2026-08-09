package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class f3 implements IMessage {
   boolean b = false;
   UUID a;
   UUID c;

   public f3() {
   }

   public f3(UUID var1, UUID var2) {
      this.a = var1;
      this.c = var2;
   }

   public void fromBytes(ByteBuf var1) {
      this.a = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      this.c = UUID.fromString(ByteBufUtils.readUTF8String(var1));
      this.b = true;
   }

   public void toBytes(ByteBuf var1) {
      ByteBufUtils.writeUTF8String(var1, this.a.toString());
      ByteBufUtils.writeUTF8String(var1, this.c.toString());
   }

   public static class a implements IMessageHandler<f3, IMessage> {
      public IMessage a(f3 var1, MessageContext var2) {
         try {
            if (!var1.b) {
               System.out.println("received an invalid message @BeeOpenChest :(");
               return null;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
            ArrayList var1x = em.g(var1.a);
            Iterator var2 = var1x.iterator();

            while(true) {
               if (var2.hasNext()) {
                  em var3 = (em)var2.next();

                  try {
                     if (var3.field_70170_p.field_72995_K) {
                        continue;
                     }
                  } catch (RuntimeException var9) {
                     throw a(var9);
                  }

                  try {
                     if (!(var3 instanceof fz)) {
                        continue;
                     }
                  } catch (RuntimeException var8) {
                     throw a(var8);
                  }

                  fz var4 = (fz)var3;

                  try {
                     if (!(Boolean)var4.func_184212_Q().func_187225_a(fz.M)) {
                        continue;
                     }
                  } catch (RuntimeException var7) {
                     throw a(var7);
                  }

                  EntityPlayerMP var5 = (EntityPlayerMP)var4.field_70170_p.func_152378_a(var1.c);

                  try {
                     if (var5 == null) {
                        continue;
                     }
                  } catch (RuntimeException var6) {
                     throw a(var6);
                  }

                  var5.openGui(Main.instance, 1, var3.field_70170_p, var3.func_180425_c().func_177958_n(), var3.func_180425_c().func_177956_o(), var3.func_180425_c().func_177952_p());
                  return;
               }

               return;
            }
         });
         return null;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
