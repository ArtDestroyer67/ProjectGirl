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
    }
}
