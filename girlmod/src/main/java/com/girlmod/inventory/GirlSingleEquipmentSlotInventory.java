package com.girlmod.inventory;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Single-slot IInventory adapter for one of GirlEntity's vanilla equipment
 * slots — used for the mainhand weapon slot in GirlContainer (armor uses
 * GirlEquipmentInventory instead, which covers all 4 armor slots at once).
 */
public class GirlSingleEquipmentSlotInventory implements IInventory {

    private final GirlEntity girl;
    private final EquipmentSlotType slot;

    public GirlSingleEquipmentSlotInventory(GirlEntity girl, EquipmentSlotType slot) {
        this.girl = girl;
        this.slot = slot;
    }

    @Override
    public int getContainerSize() { return 1; }

    @Override
    public boolean isEmpty() { return girl.getItemBySlot(slot).isEmpty(); }

    @Override
    public ItemStack getItem(int index) { return girl.getItemBySlot(slot); }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack current = getItem(0).copy();
        ItemStack split = current.split(count);
        girl.setItemSlot(slot, current);
        return split;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack current = getItem(0);
        girl.setItemSlot(slot, ItemStack.EMPTY);
        return current;
    }

    @Override
    public void setItem(int index, ItemStack stack) { girl.setItemSlot(slot, stack); }

    @Override
    public void setChanged() { }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return girl.isAlive() && player.distanceToSqr(girl) <= 64.0;
    }

    @Override
    public void clearContent() { girl.setItemSlot(slot, ItemStack.EMPTY); }
}
