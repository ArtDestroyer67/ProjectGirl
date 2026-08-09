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

public class d4 extends Container {
   em b;
   public Slot[] d;
   public UUID a;
   public static List<d4> c = new ArrayList();

   public d4(em var1, InventoryPlayer var2, UUID var3) {
      this.a = var3;
      c.add(this);
      if (var1.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
         IItemHandler var4 = (IItemHandler)var1.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
         this.b = var1;
         this.d = new Slot[]{new fe(fe.b.WEAPON, var4, fe.b.WEAPON.id, 31, 60), new fe(fe.b.BOW, var4, fe.b.BOW.id, 50, 60), new fe(fe.b.HELMET, var4, fe.b.HELMET.id, 72, 60), new fe(fe.b.CHEST_PLATE, var4, fe.b.CHEST_PLATE.id, 91, 60), new fe(fe.b.PANTS, var4, fe.b.PANTS.id, 110, 60), new fe(fe.b.SHOES, var4, fe.b.SHOES.id, 129, 60)};
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

         for(Slot var9 : this.d) {
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
