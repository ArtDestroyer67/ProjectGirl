package com.girlmod.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

/** Same restriction vanilla's own PlayerContainer uses for its armor slots — only the matching ArmorItem type may be placed here. */
public class ArmorSlot extends Slot {

    private final EquipmentSlotType equipmentSlot;

    public ArmorSlot(IInventory inventory, int index, int x, int y, EquipmentSlotType equipmentSlot) {
        super(inventory, index, x, y);
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem
            && ((ArmorItem) stack.getItem()).getSlot() == equipmentSlot;
    }

    @Override
    public boolean mayPickup(PlayerEntity player) { return true; }

    @Override
    public int getMaxStackSize() { return 1; }
}
