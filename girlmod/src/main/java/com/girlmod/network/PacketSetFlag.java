package com.girlmod.network;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetFlag {

    public static final String FLAG_FOLLOWING = "FOLLOWING";
    public static final String FLAG_DRESSED   = "DRESSED";
    public static final String FLAG_ARMOR     = "ARMOR";   // NEW: toggle armor pieces on dressed model

    private final int     entityId;
    private final String  flag;
    private final boolean value;

    public PacketSetFlag(int entityId, String flag, boolean value) {
        this.entityId = entityId;
        this.flag     = flag;
        this.value    = value;
    }

    public static void encode(PacketSetFlag msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.flag, 32);
        buf.writeBoolean(msg.value);
    }

    public static PacketSetFlag decode(PacketBuffer buf) {
        return new PacketSetFlag(buf.readInt(), buf.readUtf(32), buf.readBoolean());
    }

    public static void handle(PacketSetFlag msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.setPacketHandled(true);
        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player == null) return;
            ServerWorld world  = player.getLevel();
            Entity      entity = world.getEntity(msg.entityId);
            if (!(entity instanceof GirlEntity)) return;
            GirlEntity girl = (GirlEntity) entity;
            if (player.distanceToSqr(girl) > 100.0) return;

            switch (msg.flag) {
                case FLAG_FOLLOWING: girl.setFollowing(msg.value); break;
                case FLAG_DRESSED:   girl.setDressed(msg.value);   break;
                case FLAG_ARMOR:     girl.setArmored(msg.value);   break;  // NEW
                default:
                    System.out.println("[GirlMod] Unknown flag in PacketSetFlag: " + msg.flag);
            }
        });
    }
}
