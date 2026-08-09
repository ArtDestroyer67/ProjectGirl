package com.trolmastercard.sexmod;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class fe extends SlotItemHandler {
   b a;

   public fe(b var1, IItemHandler var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.a = var1;
   }

   public static boolean a(ItemStack var0, int var1) {
      return a(var0, fe.b.a(var1));
   }

   public boolean func_75214_a(ItemStack var1) {
      return a(var1, this.a);
   }

   static boolean a(ItemStack param0, b param1) {
      // $FF: Couldn't be decompiled
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }

   public static enum b {
      WEAPON(0),
      BOW(1),
      HELMET(2),
      CHEST_PLATE(3),
      PANTS(4),
      SHOES(5),
      ROD(6);

      public int id;

      public static b a(int var0) {
         try {
            switch (var0) {
               case 0:
                  return WEAPON;
               case 1:
                  return BOW;
               case 2:
                  return HELMET;
               case 3:
                  return CHEST_PLATE;
               case 4:
                  return PANTS;
               case 5:
                  return SHOES;
               case 6:
                  return ROD;
            }
         } catch (NullPointerException var1) {
            throw a(var1);
         }
      }

      private b(int var3) {
         this.id = var3;
      }

      private static NullPointerException a(NullPointerException var0) {
         return var0;
      }
   }
}
