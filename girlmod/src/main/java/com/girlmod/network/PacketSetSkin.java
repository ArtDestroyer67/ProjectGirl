package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player picks a skin from the GUI's skin
 * list. GirlEntity#setSkinId validates the id against SkinConfig itself
 * (falls back to "default" if unknown), so no validation is needed here.
 */
public class PacketSetSkin {

    private final int entityId;
    private final String skinId;

    public PacketSetSkin(int entityId, String skinId) {
        this.entityId = entityId;
        this.skinId = skinId;
    }

    public static void encode(PacketSetSkin msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.skinId, 64);
    }

    public static PacketSetSkin decode(PacketBuffer buf) {
        return new PacketSetSkin(buf.readInt(), buf.readUtf(64));
    }

    public static void handle(PacketSetSkin msg, Supplier<NetworkEvent.Context> ctxSupplier) {
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

            girl.setSkinId(msg.skinId);
        });
    }
}
