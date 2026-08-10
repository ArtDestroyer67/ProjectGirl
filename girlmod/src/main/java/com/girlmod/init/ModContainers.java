package com.girlmod.init;

import com.girlmod.GirlMod;
import com.girlmod.inventory.GirlContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS =
        DeferredRegister.create(ForgeRegistries.CONTAINERS, GirlMod.MODID);

    public static final RegistryObject<ContainerType<GirlContainer>> GIRL_CONTAINER =
        CONTAINERS.register("girl_inventory", () ->
            new ContainerType<>((IContainerFactory<GirlContainer>) GirlContainer::fromNetwork));
}
