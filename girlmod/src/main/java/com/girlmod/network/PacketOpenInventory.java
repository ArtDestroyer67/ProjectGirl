package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player clicks the GUI's "Inventory" button.
 * NetworkHooks.openGui (called from GirlEntity#openArmorInventory) must run
 * server-side, so this just relays the request there.
 */
public class PacketOpenInventory {

    private final int entityId;

    public PacketOpenInventory(int entityId) {
        this.entityId = entityId;
    }

    public static void encode(PacketOpenInventory msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
    }

    public static PacketOpenInventory decode(PacketBuffer buf) {
        return new PacketOpenInventory(buf.readInt());
    }

    public static void handle(PacketOpenInventory msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.setPacketHandled(true);

        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;

            ServerWorld world  = player.getLevel();
            Entity      entity = world.getEntity(msg.entityId);

            if (!(entity instanceof GirlEntity)) return;
            GirlEntity girl = (GirlEntity) entity;

            // Distance check — player must be within 10 blocks, same as the other packets
            if (player.distanceToSqr(girl) > 100.0) return;

            girl.openArmorInventory(player);
        });
    }
}
