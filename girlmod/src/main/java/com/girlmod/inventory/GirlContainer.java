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
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.PacketBuffer;

/**
 * Armor/inventory screen for a GirlEntity — 4 armor slots + 1 mainhand
 * weapon slot bound to her real equipment (GirlEquipmentInventory /
 * GirlSingleEquipmentSlotInventory) plus the player's own 27+9 inventory
 * slots. Armor slots are laid out at the exact same coordinates vanilla's
 * own inventory screen uses so the shared inventory.png background lines
 * up (see GirlContainerScreen); the weapon slot has no matching art in
 * that texture so it renders as a plain unframed slot.
 */
public class GirlContainer extends Container {

    /** Slot index of the mainhand weapon slot — right after the 4 armor slots (0-3). */
    private static final int WEAPON_SLOT_INDEX = GirlEquipmentInventory.ARMOR_SLOTS.length; // 4
    /** Total slots owned by this container before the player's own inventory starts. */
    private static final int SPECIAL_SLOT_COUNT = WEAPON_SLOT_INDEX + 1; // 4 armor + 1 weapon = 5

    public final GirlEntity girl;
    private final GirlEquipmentInventory equipment;
    private final GirlSingleEquipmentSlotInventory mainHand;

    /** Server-side / logical constructor. */
    public GirlContainer(int windowId, PlayerInventory playerInv, GirlEntity girl) {
        super(ModContainers.GIRL_CONTAINER.get(), windowId);
        this.girl = girl;
        this.equipment = new GirlEquipmentInventory(girl);
        this.mainHand  = new GirlSingleEquipmentSlotInventory(girl, EquipmentSlotType.MAINHAND);

        // Armor slots — same x/y and top-to-bottom order (HEAD..FEET) as
        // vanilla's own inventory screen.
        EquipmentSlotType[] slots = GirlEquipmentInventory.ARMOR_SLOTS;
        for (int i = 0; i < slots.length; i++) {
            this.addSlot(new ArmorSlot(equipment, i, 8, 8 + i * 18, slots[i]));
        }

        // Mainhand weapon slot — placed near vanilla's own off-hand slot
        // position; unrestricted (accepts anything, matching how vanilla's
        // own mainhand slot behaves), same as this.addSlot(new Slot(...)).
        this.addSlot(new Slot(mainHand, 0, 77, 62));

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

    /**
     * Shift-click:
     *  - armor/weapon slot  -> player inventory
     *  - player inventory   -> matching armor slot if it's an ArmorItem,
     *                           else the weapon slot if it's a sword/bow,
     *                           else just shuffled within the player inventory
     */
    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack original = slot.getItem();
        ItemStack moving   = original.copy();

        int armorSlotCount = GirlEquipmentInventory.ARMOR_SLOTS.length; // 4
        int playerInvStart = SPECIAL_SLOT_COUNT;
        int playerInvEnd   = this.slots.size();

        if (index < SPECIAL_SLOT_COUNT) {
            // From an armor or weapon slot -> into the player's inventory
            if (!this.moveItemStackTo(original, playerInvStart, playerInvEnd, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From the player's inventory -> try the matching armor slot,
            // then the weapon slot if it's a sword/bow, before falling
            // back to just shuffling within the player's own inventory.
            boolean movedToSpecial = false;
            if (original.getItem() instanceof ArmorItem) {
                ArmorItem armor = (ArmorItem) original.getItem();
                for (int i = 0; i < armorSlotCount; i++) {
                    if (GirlEquipmentInventory.ARMOR_SLOTS[i] == armor.getSlot()) {
                        movedToSpecial = this.moveItemStackTo(original, i, i + 1, false);
                        break;
                    }
                }
            } else if (original.getItem() instanceof SwordItem || original.getItem() instanceof BowItem) {
                movedToSpecial = this.moveItemStackTo(original, WEAPON_SLOT_INDEX, WEAPON_SLOT_INDEX + 1, false);
            }
            if (!movedToSpecial && !this.moveItemStackTo(original, playerInvStart, playerInvEnd, false)) {
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
