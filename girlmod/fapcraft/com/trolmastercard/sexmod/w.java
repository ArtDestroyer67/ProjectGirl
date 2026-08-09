package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class w {
   public static w a;
   private a b;

   public void a() {
      try {
         if (a.b == null) {
            return;
         }
      } catch (RuntimeException var2) {
         throw a(var2);
      }

      try {
         if (--a.b.e <= 0.0F) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_135052_a("genderswap.sexpromt.timeout", new Object[0])));
            this.c();
         }

      } catch (RuntimeException var1) {
         throw a(var1);
      }
   }

   public a b() {
      return a.b;
   }

   void c() {
      a.b = null;
   }

   public void a(@Nonnull a param1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void a(ClientChatEvent var1) {
      try {
         if (a.b() == null) {
            return;
         }
      } catch (RuntimeException var5) {
         throw a(var5);
      }

      String var2 = var1.getMessage().toLowerCase();
      if (var2.equals(I18n.func_135052_a("genderswap.sexpromt.accept", new Object[0]).toLowerCase())) {
         a var3 = a.b();
         this.a(var3.a, var3.d, var3.c);
         this.c();
         var1.setCanceled(true);
      }

      try {
         if (var2.equals(I18n.func_135052_a("genderswap.sexpromt.decline", new Object[0]).toLowerCase())) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_135052_a("genderswap.sexpromt.declineconformation", new Object[0])));
            this.c();
            var1.setCanceled(true);
         }

      } catch (RuntimeException var4) {
         throw a(var4);
      }
   }

   void a(String var1, UUID var2, UUID var3) {
      ge.b.sendToServer(new eu(var2, var3, var1));
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static class a {
      public String a;
      public UUID c;
      public UUID d;
      public float e;
      boolean b;

      public a(String var1, UUID var2, UUID var3, boolean var4) {
         this.a = var1;
         this.c = var2;
         this.d = var3;
         this.e = 1200.0F;
         this.b = var4;
      }
   }
}
