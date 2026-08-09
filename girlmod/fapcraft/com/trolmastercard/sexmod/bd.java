package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class bd implements IMessage {
   boolean c = false;
   EntityPlayer b;
   HashMap<fy, String> a = new HashMap();

   public bd() {
   }

   public bd(EntityPlayer var1) {
      this.b = var1;
   }

   public void fromBytes(ByteBuf var1) {
      int var2 = var1.readInt();
      int var3 = 0;

      try {
         while(var3 < var2) {
            this.a.put(fy.valueOf(ByteBufUtils.readUTF8String(var1)), ByteBufUtils.readUTF8String(var1));
            ++var3;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      this.c = true;
   }

   public void toBytes(ByteBuf var1) {
      for(fy var5 : fy.values()) {
         try {
            if (!var5.hasSpecifics) {
               continue;
            }
         } catch (RuntimeException var8) {
            throw a(var8);
         }

         String var6 = this.b.getEntityData().func_74779_i("sexmod:GirlSpecific" + var5);

         try {
            if ("".equals(var6)) {
               continue;
            }
         } catch (RuntimeException var7) {
            throw a(var7);
         }

         this.a.put(var5, var6);
      }

      var1.writeInt(this.a.size());

      for(Map.Entry var10 : this.a.entrySet()) {
         ByteBufUtils.writeUTF8String(var1, ((fy)var10.getKey()).toString());
         ByteBufUtils.writeUTF8String(var1, (String)var10.getValue());
      }

   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a implements IMessageHandler<bd, IMessage> {
      public IMessage a(bd param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      @SideOnly(Side.CLIENT)
      public void a(HashMap<fy, String> var1) {
         Minecraft var2 = Minecraft.func_71410_x();
         var2.func_152344_a(() -> var2.func_147108_a(new b5(var1)));
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
