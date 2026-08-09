package com.girlmod.network;

import com.girlmod.entity.AnimState;
import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player picks an animation from the GUI.
 *
 * 1.16.5 network change from 1.12.2:
 *   - SimpleNetworkWrapper → SimpleChannel
 *   - IMessage / IMessageHandler → encode/decode/handle static methods
 *   - onMessage context gives a Supplier<NetworkEvent.Context> instead of MessageContext
 *
 * Server validates:
 *   - Entity exists and is a GirlEntity
 *   - Sending player is within 10 blocks
 * Then calls entity.setState(state).
 */
public class PacketSetState {

    private final int       entityId;
    private final String    stateName;

    public PacketSetState(int entityId, AnimState state) {
        this.entityId  = entityId;
        this.stateName = state.name();
    }

    // ── Serialisation ─────────────────────────────────────────────────────────

    public static void encode(PacketSetState msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.stateName, 64);
    }

    public static PacketSetState decode(PacketBuffer buf) {
        int    entityId  = buf.readInt();
        String stateName = buf.readUtf(64);
        // Reuse the two-arg constructor via a temporary AnimState
        AnimState state;
        try {
            state = AnimState.valueOf(stateName);
        } catch (IllegalArgumentException e) {
            state = AnimState.IDLE;
        }
        return new PacketSetState(entityId, state);
    }

    // ── Server-side handler ───────────────────────────────────────────────────

    public static void handle(PacketSetState msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.setPacketHandled(true);

        // enqueueWork schedules onto the server thread (packet arrives on netty thread)
        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;

            ServerWorld world  = player.getLevel();
            Entity      entity = world.getEntity(msg.entityId);

            if (!(entity instanceof GirlEntity)) return;
            GirlEntity girl = (GirlEntity) entity;

            // Distance check — player must be within 10 blocks
            if (player.distanceToSqr(girl) > 100.0) return;

            AnimState state;
            try {
                state = AnimState.valueOf(msg.stateName);
            } catch (IllegalArgumentException e) {
                return;
            }

            girl.setState(state);
        });
    }
}
