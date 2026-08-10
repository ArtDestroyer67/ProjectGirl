package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player picks an animation set from the
 * GUI's list. GirlEntity#setAnimationSetId validates the id against
 * AnimationSetConfig itself (falls back to "default" if unknown), so no
 * validation is needed here.
 */
public class PacketSetAnimSet {

    private final int entityId;
    private final String setId;

    public PacketSetAnimSet(int entityId, String setId) {
        this.entityId = entityId;
        this.setId = setId;
    }

    public static void encode(PacketSetAnimSet msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.setId, 64);
    }

    public static PacketSetAnimSet decode(PacketBuffer buf) {
        return new PacketSetAnimSet(buf.readInt(), buf.readUtf(64));
    }

    public static void handle(PacketSetAnimSet msg, Supplier<NetworkEvent.Context> ctxSupplier) {
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

            girl.setAnimationSetId(msg.setId);
        });
    }
}
