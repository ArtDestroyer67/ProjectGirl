package com.girlmod;

import com.girlmod.client.effect.ClientEffects;
import com.girlmod.client.gui.GirlContainerScreen;
import com.girlmod.client.renderer.GirlRenderer;
import com.girlmod.config.AnimationSetConfig;
import com.girlmod.config.MobInteractConfig;
import com.girlmod.config.SkinConfig;
import com.girlmod.config.StateConfig;
import com.girlmod.entity.GirlEntity;
import com.girlmod.init.ModContainers;
import com.girlmod.init.ModEntities;
import com.girlmod.init.ModSounds;
import com.girlmod.network.PacketHandler;
import com.girlmod.sound.SoundMapper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(GirlMod.MODID)
public class GirlMod {

    public static final String MODID = "girlmod";

    public GirlMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        GeckoLib.initialize();

        ModEntities.ENTITY_TYPES.register(modBus);
        ModSounds.SOUNDS.register(modBus);
        ModContainers.CONTAINERS.register(modBus);

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::clientSetup);
        modBus.addListener(this::onAttributeCreate);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PacketHandler.register();
        // Load config files after the event queue settles — avoids doing file
        // I/O directly on the parallel mod-loading thread pool.
        event.enqueueWork(() -> {
            StateConfig.load();
            SoundMapper.load();
            MobInteractConfig.load();
            SkinConfig.load();
            AnimationSetConfig.load();
        });
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(
            ModEntities.GIRL.get(),
            manager -> new GirlRenderer(manager)
        );
        event.enqueueWork(() ->
            ScreenManager.register(ModContainers.GIRL_CONTAINER.get(), GirlContainerScreen::new)
        );
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(ClientEffects.class);
    }

    private void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GIRL.get(), GirlEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        registerCommands(event.getServer().getCommands().getDispatcher());
    }

    private void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            Commands.literal("spawngirl")
                .requires(src -> src.hasPermission(2))
                .executes(ctx -> {
                    CommandSource src = ctx.getSource();
                    PlayerEntity player;
                    try { player = src.getPlayerOrException(); }
                    catch (Exception e) {
                        src.sendFailure(new StringTextComponent("Must be run by a player"));
                        return 0;
                    }

                    World world = player.level;
                    GirlEntity girl = ModEntities.GIRL.get().create(world);
                    if (girl == null) return 0;

                    girl.moveTo(player.getX(), player.getY(), player.getZ(), player.yRot, 0);
                    girl.finalizeSpawn(
                        (ServerWorld) world,
                        world.getCurrentDifficultyAt(new BlockPos(girl.blockPosition())),
                        SpawnReason.COMMAND, null, null
                    );
                    world.addFreshEntity(girl);

                    src.sendSuccess(
                        new StringTextComponent(TextFormatting.GREEN + "[GirlMod] Girl spawned!"),
                        true
                    );
                    return 1;
                })
        );

        dispatcher.register(
            Commands.literal("girlmod")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("reload")
                    .executes(ctx -> {
                        StateConfig.reload();
                        SoundMapper.reload();
                        MobInteractConfig.reload();
                        SkinConfig.reload();
                        AnimationSetConfig.reload();
                        ctx.getSource().sendSuccess(
                            new StringTextComponent(TextFormatting.GREEN
                                + "[GirlMod] Reloaded states.json, sound_mappings.json, mob_interact.json, skins.json, and animation_sets.json from config/girlmod/"),
                            true
                        );
                        return 1;
                    })
                )
        );
    }
}
