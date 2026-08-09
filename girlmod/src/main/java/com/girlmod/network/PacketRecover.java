package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player clicks the GUI's "Recover" button
 * while the girl is downed. Manual-only by design: she otherwise stays
 * downed/invincible indefinitely (see GirlEntity#hurt / recoverFromDowned),
 * this is the one way out.
 */
public class PacketRecover {

    private final int entityId;

    public PacketRecover(int entityId) {
        this.entityId = entityId;
    }

    public static void encode(PacketRecover msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
    }

    public static PacketRecover decode(PacketBuffer buf) {
        return new PacketRecover(buf.readInt());
    }

    public static void handle(PacketRecover msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.setPacketHandled(true);

        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;

            ServerWorld world  = player.getLevel();
            Entity      entity = world.getEntity(msg.entityId);

            if (!(entity instanceof GirlEntity)) return;
            GirlEntity girl = (GirlEntity) entity;

            // Distance check — player must be within 10 blocks, same as PacketSetState/PacketSetFlag
            if (player.distanceToSqr(girl) > 100.0) return;

            girl.forceRecover(); // no-op if she isn't currently downed
        });
    }
}
