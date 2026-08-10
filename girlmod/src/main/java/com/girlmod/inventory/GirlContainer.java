package com.girlmod.inventory;

import com.girlmod.entity.GirlEntity;
import com.girlmod.init.ModContainers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

/**
 * Armor/inventory screen for a GirlEntity — 4 armor slots bound to her real
 * equipment (GirlEquipmentInventory) plus the player's own 27+9 inventory
 * slots, laid out at the exact same coordinates vanilla's own inventory
 * screen uses so the shared inventory.png background lines up (see
 * GirlContainerScreen).
 */
public class GirlContainer extends Container {

    public final GirlEntity girl;
    private final GirlEquipmentInventory equipment;

    /** Server-side / logical constructor. */
    public GirlContainer(int windowId, PlayerInventory playerInv, GirlEntity girl) {
        super(ModContainers.GIRL_CONTAINER.get(), windowId);
        this.girl = girl;
        this.equipment = new GirlEquipmentInventory(girl);

        // Armor slots — same x/y and top-to-bottom order (HEAD..FEET) as
        // vanilla's own inventory screen.
        EquipmentSlotType[] slots = GirlEquipmentInventory.ARMOR_SLOTS;
        for (int i = 0; i < slots.length; i++) {
            this.addSlot(new ArmorSlot(equipment, i, 8, 8 + i * 18, slots[i]));
        }

        // Player's own inventory (3x9 main + hotbar), positioned exactly
        // like vanilla's inventory screen so it lines up with inventory.png.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    /** Client-side constructor used by ModContainers' ContainerType factory when the server opens this via NetworkHooks.openGui. */
    public static GirlContainer fromNetwork(int windowId, PlayerInventory playerInv, PacketBuffer buf) {
        int entityId = buf.readInt();
        Entity entity = playerInv.player.level.getEntity(entityId);
        if (!(entity instanceof GirlEntity)) {
            throw new IllegalStateException("GirlContainer opened for entity id " + entityId + " which is not a GirlEntity");
        }
        return new GirlContainer(windowId, playerInv, (GirlEntity) entity);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return girl.isAlive() && player.distanceToSqr(girl) <= 64.0;
    }

    /** Shift-click: armor slot -> player inventory, or player inventory -> matching armor slot if empty. */
    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack original = slot.getItem();
        ItemStack moving   = original.copy();

        int armorSlotCount  = GirlEquipmentInventory.ARMOR_SLOTS.length; // 4
        int playerInvStart  = armorSlotCount;
        int playerInvEnd    = this.slots.size(); // 4 + 36

        if (index < armorSlotCount) {
            // From an armor slot -> into the player's inventory
            if (!this.moveItemStackTo(original, playerInvStart, playerInvEnd, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From the player's inventory -> try the matching armor slot first
            boolean movedToArmor = false;
            if (original.getItem() instanceof ArmorItem) {
                ArmorItem armor = (ArmorItem) original.getItem();
                for (int i = 0; i < armorSlotCount; i++) {
                    if (GirlEquipmentInventory.ARMOR_SLOTS[i] == armor.getSlot()) {
                        movedToArmor = this.moveItemStackTo(original, i, i + 1, false);
                        break;
                    }
                }
            }
            if (!movedToArmor && !this.moveItemStackTo(original, playerInvStart, playerInvEnd, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (original.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (original.getCount() == moving.getCount()) return ItemStack.EMPTY;

        slot.onTake(player, original);
        return moving;
    }
}
