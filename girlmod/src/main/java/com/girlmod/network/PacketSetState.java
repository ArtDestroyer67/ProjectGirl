package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent client → server when a player picks an animation from the GUI.
 * stateId is a plain String (e.g. "HUG") looked up against StateConfig
 * server-side — no enum involved, so new states added via states.json
 * work immediately without any packet/network code changes.
 */
public class PacketSetState {

    private final int    entityId;
    private final String stateId;

    public PacketSetState(int entityId, String stateId) {
        this.entityId = entityId;
        this.stateId  = stateId;
    }

    public static void encode(PacketSetState msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.stateId, 64);
    }

    public static PacketSetState decode(PacketBuffer buf) {
        int entityId = buf.readInt();
        String stateId = buf.readUtf(64);
        return new PacketSetState(entityId, stateId);
    }

    public static void handle(PacketSetState msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.setPacketHandled(true);

        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;

            ServerWorld world  = player.getLevel();
            Entity      entity = world.getEntity(msg.entityId);

            if (!(entity instanceof GirlEntity)) return;
            GirlEntity girl = (GirlEntity) entity;

            // Distance check — player must be within 10 blocks
            if (player.distanceToSqr(girl) > 100.0) return;

            // While downed/recovering, the state is driven entirely by the
            // downed sequence itself (generic DOWNED clip or a mob-matched
            // one — see GirlEntity#applyMobIdentity/tick), so a pose picked
            // from the GUI mid-recovery is ignored rather than interrupting it.
            if (girl.isDowned()) return;

            girl.setState(msg.stateId); // setState() validates/falls back internally
        });
    }
}
