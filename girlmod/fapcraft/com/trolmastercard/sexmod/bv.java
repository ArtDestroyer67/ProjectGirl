package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class bv implements IMessage {
   Vec3d a;
   boolean c;
   boolean b = false;

   public bv() {
   }

   public bv(Vec3d var1, boolean var2) {
      this.a = var1;
      this.c = var2;
   }

   public void fromBytes(ByteBuf var1) {
      this.a = new Vec3d(var1.readDouble(), var1.readDouble(), var1.readDouble());
      this.c = var1.readBoolean();
      this.b = true;
   }

   public void toBytes(ByteBuf var1) {
      var1.writeDouble(this.a.field_72450_a);
      var1.writeDouble(this.a.field_72448_b);
      var1.writeDouble(this.a.field_72449_c);
      var1.writeBoolean(this.c);
   }

   public static class a implements IMessageHandler<bv, IMessage> {
      public IMessage a(bv param1, MessageContext param2) {
         // $FF: Couldn't be decompiled
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
