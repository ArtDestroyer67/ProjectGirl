package com.girlmod.init;

import com.girlmod.GirlMod;
import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITIES, GirlMod.MODID);

    public static final RegistryObject<EntityType<GirlEntity>> GIRL =
        ENTITY_TYPES.register("girl", () ->
            EntityType.Builder.<GirlEntity>of(
                // Direct constructor reference — Forge needs this exact form
                GirlEntity::new,
                EntityClassification.CREATURE
            )
            .sized(0.6f, 1.8f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build(new ResourceLocation(GirlMod.MODID, "girl").toString())
        );
}
