package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ca extends Container {
   eb d;
   public Slot[] b;
   public UUID a;
   public static List<ca> c = new ArrayList();

   public ca(eb var1, InventoryPlayer var2, UUID var3) {
      this.a = var3;
      c.add(this);
      if (var1.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
         IItemHandler var4 = (IItemHandler)var1.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
         this.d = var1;
         this.b = new Slot[]{new fe(fe.b.WEAPON, var4, fe.b.WEAPON.id, 41, 60), new fe(fe.b.BOW, var4, fe.b.BOW.id, 59, 60), new fe(fe.b.HELMET, var4, fe.b.HELMET.id, 81, 60), new fe(fe.b.CHEST_PLATE, var4, fe.b.CHEST_PLATE.id, 100, 60), new fe(fe.b.PANTS, var4, fe.b.PANTS.id, 119, 60), new fe(fe.b.SHOES, var4, fe.b.SHOES.id, 138, 60), new fe(fe.b.ROD, var4, fe.b.ROD.id, 22, 60)};
         ArrayList var5 = new ArrayList();

         for(int var6 = 0; var6 < 3; ++var6) {
            int var7 = 0;

            try {
               while(var7 < 9) {
                  var5.add(new Slot(var2, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
                  ++var7;
               }
            } catch (RuntimeException var11) {
               throw a(var11);
            }
         }

         int var12 = 0;

         try {
            while(var12 < 9) {
               var5.add(new Slot(var2, var12, 8 + var12 * 18, 142));
               ++var12;
            }
         } catch (RuntimeException var10) {
            throw a(var10);
         }

         for(Slot var9 : this.b) {
            this.func_75146_a(var9);
         }

         for(Slot var16 : var5) {
            this.func_75146_a(var16);
         }
      }

   }

   public ItemStack func_82846_b(EntityPlayer param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public void func_75141_a(int var1, ItemStack var2) {
      super.func_75141_a(var1, var2);
   }

   public boolean func_75145_c(EntityPlayer var1) {
      return true;
   }

   public void func_75134_a(EntityPlayer var1) {
      super.func_75134_a(var1);
   }

   private static RuntimeException a(RuntimeException var0) {
      return var0;
   }
}
