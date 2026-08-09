package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class bx extends Container {
   private final IInventory a;
   private final int d;
   public static List<bx> b = new ArrayList();
   public UUID c;

   public bx(IInventory var1, IInventory var2, EntityPlayer var3, UUID var4) {
      this.c = var4;
      b.add(this);
      this.a = var2;
      var2.func_174889_b(var3);
      this.d = 3;
      byte var5 = -18;

      for(int var6 = 0; var6 < 3; ++var6) {
         int var7 = 0;

         try {
            while(var7 < 9) {
               this.func_75146_a(new Slot(var2, var7 + var6 * 9, 8 + var7 * 18, 18 + var6 * 18));
               ++var7;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }
      }

      for(int var11 = 0; var11 < 3; ++var11) {
         int var13 = 0;

         try {
            while(var13 < 9) {
               this.func_75146_a(new Slot(var1, var13 + var11 * 9 + 9, 8 + var13 * 18, 103 + var11 * 18 + var5));
               ++var13;
            }
         } catch (RuntimeException var9) {
            throw a(var9);
         }
      }

      int var12 = 0;

      try {
         while(var12 < 9) {
            this.func_75146_a(new Slot(var1, var12, 8 + var12 * 18, 161 + var5));
            ++var12;
         }

      } catch (RuntimeException var8) {
         throw a(var8);
      }
   }

   public boolean func_75145_c(EntityPlayer var1) {
      return this.a.func_70300_a(var1);
   }

   public ItemStack func_82846_b(EntityPlayer param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public void func_75134_a(EntityPlayer var1) {
      super.func_75134_a(var1);
      this.a.func_174886_c(var1);
   }

   public IInventory a() {
      return this.a;
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
