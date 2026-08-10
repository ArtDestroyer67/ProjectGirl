package com.girlmod.inventory;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Adapts GirlEntity's 4 vanilla armor equipment slots (HEAD/CHEST/LEGS/FEET
 * — the same slots already used for her mainhand weapon via
 * getItemBySlot/setItemSlot) into a plain 4-slot IInventory, so they can be
 * exposed as ordinary Slots in GirlContainer without inventing a separate
 * storage system.
 */
public class GirlEquipmentInventory implements IInventory {

    /** Index order used everywhere here matches vanilla's own inventory screen layout: helmet at top, boots at bottom. */
    public static final EquipmentSlotType[] ARMOR_SLOTS = {
        EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET
    };

    private final GirlEntity girl;

    public GirlEquipmentInventory(GirlEntity girl) {
        this.girl = girl;
    }

    @Override
    public int getContainerSize() { return ARMOR_SLOTS.length; }

    @Override
    public boolean isEmpty() {
        for (EquipmentSlotType slot : ARMOR_SLOTS) {
            if (!girl.getItemBySlot(slot).isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return girl.getItemBySlot(ARMOR_SLOTS[index]);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack current = getItem(index).copy();
        ItemStack split = current.split(count);
        girl.setItemSlot(ARMOR_SLOTS[index], current);
        return split;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack current = getItem(index);
        girl.setItemSlot(ARMOR_SLOTS[index], ItemStack.EMPTY);
        return current;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        girl.setItemSlot(ARMOR_SLOTS[index], stack);
    }

    @Override
    public void setChanged() { /* setItemSlot already triggers LivingEntity's own equipment-change sync */ }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return girl.isAlive() && player.distanceToSqr(girl) <= 64.0;
    }

    @Override
    public void clearContent() {
        for (EquipmentSlotType slot : ARMOR_SLOTS) girl.setItemSlot(slot, ItemStack.EMPTY);
    }
}
