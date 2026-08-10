package com.girlmod.network;

import com.girlmod.GirlMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(GirlMod.MODID, "main"),
        () -> PROTOCOL,
        PROTOCOL::equals,   // client accepts
        PROTOCOL::equals    // server accepts
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(
            id++,
            PacketSetState.class,
            PacketSetState::encode,
            PacketSetState::decode,
            PacketSetState::handle
        );
        CHANNEL.registerMessage(
            id++,
            PacketSetFlag.class,
            PacketSetFlag::encode,
            PacketSetFlag::decode,
            PacketSetFlag::handle
        );
        CHANNEL.registerMessage(
            id++,
            PacketRecover.class,
            PacketRecover::encode,
            PacketRecover::decode,
            PacketRecover::handle
        );
        CHANNEL.registerMessage(
            id++,
            PacketOpenInventory.class,
            PacketOpenInventory::encode,
            PacketOpenInventory::decode,
            PacketOpenInventory::handle
        );
        CHANNEL.registerMessage(
            id++,
            PacketSetSkin.class,
            PacketSetSkin::encode,
            PacketSetSkin::decode,
            PacketSetSkin::handle
        );
        CHANNEL.registerMessage(
            id++,
            PacketSetAnimSet.class,
            PacketSetAnimSet::encode,
            PacketSetAnimSet::decode,
            PacketSetAnimSet::handle
        );
    }
}
