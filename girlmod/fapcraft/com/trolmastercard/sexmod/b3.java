package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Vector4d;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class b3 implements IMessage {
   boolean a = false;
   boolean b;
   List<Vector4d> c;

   public b3() {
      this.b = false;
      this.c = new ArrayList();
   }

   public b3(boolean var1, List<Vector4d> var2) {
      this.b = var1;
      this.c = var2;
   }

   static b3 a() {
      return new b3(false, new ArrayList());
   }

   public void fromBytes(ByteBuf var1) {
      this.b = var1.readBoolean();
      int var2 = var1.readInt();
      int var3 = 0;

      try {
         while(var3 < var2) {
            this.c.add(new Vector4d((double)var1.readInt(), (double)var1.readInt(), (double)var1.readInt(), (double)var1.readInt()));
            ++var3;
         }
      } catch (RuntimeException var4) {
         throw a(var4);
      }

      this.a = true;
   }

   public void toBytes(ByteBuf var1) {
      var1.writeBoolean(this.b);
      var1.writeInt(this.c.size());

      for(Vector4d var3 : this.c) {
         var1.writeInt((int)var3.getX());
         var1.writeInt((int)var3.getY());
         var1.writeInt((int)var3.getZ());
         var1.writeInt((int)var3.getW());
      }

   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a implements IMessageHandler<b3, IMessage> {
      public IMessage a(b3 var1, MessageContext var2) {
         try {
            if (!var1.a) {
               System.out.println("received an invalid message @GetTribeUIValues :(");
               return null;
            }
         } catch (RuntimeException var3) {
            throw a(var3);
         }

         try {
            if (var2.side.isClient()) {
               j.d = var1.b;
               ff.aY = var1.c;
               return null;
            }
         } catch (RuntimeException var4) {
            throw a(var4);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> {
            UUID var1 = ax.a(var2.getServerHandler().field_147369_b.getPersistentID());

            try {
               if (var1 == null) {
                  ge.b.sendTo(b3.a(), var2.getServerHandler().field_147369_b);
                  return;
               }
            } catch (RuntimeException var15) {
               throw a(var15);
            }

            boolean var2x = ax.c(var1);
            EntityPlayerMP var3 = var2.getServerHandler().field_147369_b;
            HashMap var4 = ax.a(var1, var3.field_70170_p);
            List var5 = ax.n(var1);
            ArrayList var6 = new ArrayList();
            int var7 = ax.l(var1).getWoolMeta();
            HashSet var8 = new HashSet();

            for(ff var10 : var5) {
               try {
                  if (var10.field_70128_L) {
                     continue;
                  }
               } catch (RuntimeException var14) {
                  throw a(var14);
               }

               UUID var11 = var10.f();

               try {
                  if (var8.contains(var11)) {
                     continue;
                  }
               } catch (RuntimeException var13) {
                  throw a(var13);
               }

               if (var10.aA) {
                  var7 = EyeAndKoboldColor.safeValueOf((String)var10.func_184212_Q().func_187225_a(e4.N)).getWoolMeta();
               }

               var6.add(new Vector4d(var10.field_70165_t, var10.field_70163_u, var10.field_70161_v, (double)var7));
               var8.add(var11);
            }

            for(Map.Entry var17 : var4.entrySet()) {
               try {
                  if (var8.contains(var17.getKey())) {
                     continue;
                  }
               } catch (RuntimeException var12) {
                  throw a(var12);
               }

               BlockPos var18 = (BlockPos)var17.getValue();
               var6.add(new Vector4d((double)var18.func_177958_n(), (double)var18.func_177956_o(), (double)var18.func_177952_p(), (double)var7));
            }

            ge.b.sendTo(new b3(var2x, var6), var3);
         });
         return null;
      }

      private static RuntimeException a(RuntimeException var0) {
         return var0;
      }
   }
}
