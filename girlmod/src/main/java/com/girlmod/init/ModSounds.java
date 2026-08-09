package com.girlmod.init;

import com.girlmod.GirlMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registers all girlmod sound events with Forge.
 * Each entry corresponds to one key in assets/girlmod/sounds.json.
 * GeckoLib's sound keyframe system resolves effect names via SoundMapper
 * which looks up these registered SoundEvents by ResourceLocation.
 */
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GirlMod.MODID);

    public static final RegistryObject<SoundEvent> GIRL_AFTERSESSIONMOAN =
        SOUNDS.register("girl.aftersessionmoan", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.aftersessionmoan")));

    public static final RegistryObject<SoundEvent> GIRL_AHH =
        SOUNDS.register("girl.ahh", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.ahh")));

    public static final RegistryObject<SoundEvent> GIRL_GIGGLE =
        SOUNDS.register("girl.giggle", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.giggle")));

    public static final RegistryObject<SoundEvent> GIRL_HAPPYOH =
        SOUNDS.register("girl.happyoh", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.happyoh")));

    public static final RegistryObject<SoundEvent> GIRL_HEAVYBREATHING =
        SOUNDS.register("girl.heavybreathing", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.heavybreathing")));

    public static final RegistryObject<SoundEvent> GIRL_HMPH =
        SOUNDS.register("girl.hmph", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.hmph")));

    public static final RegistryObject<SoundEvent> GIRL_HUH =
        SOUNDS.register("girl.huh", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.huh")));

    public static final RegistryObject<SoundEvent> GIRL_LIGHTBREATHING =
        SOUNDS.register("girl.lightbreathing", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.lightbreathing")));

    public static final RegistryObject<SoundEvent> GIRL_LIPSOUND =
        SOUNDS.register("girl.lipsound", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.lipsound")));

    public static final RegistryObject<SoundEvent> GIRL_MMM =
        SOUNDS.register("girl.mmm", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.mmm")));

    public static final RegistryObject<SoundEvent> GIRL_MOAN =
        SOUNDS.register("girl.moan", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.moan")));

    public static final RegistryObject<SoundEvent> GIRL_MOMMYHORNY =
        SOUNDS.register("girl.mommyhorny", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.mommyhorny")));
    public static final RegistryObject<SoundEvent> GIRL_COMETOMOMMY =
        SOUNDS.register("girl.cometomommy", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.cometomommy")));
    public static final RegistryObject<SoundEvent> GIRL_GOODBOY =
        SOUNDS.register("girl.goodboy", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.goodboy")));

    public static final RegistryObject<SoundEvent> GIRL_SIGH =
        SOUNDS.register("girl.sigh", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "girl.sigh")));

    public static final RegistryObject<SoundEvent> MISC_BEDRUSTLE =
        SOUNDS.register("misc.bedrustle", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "misc.bedrustle")));

    public static final RegistryObject<SoundEvent> MISC_INSERTS =
        SOUNDS.register("misc.inserts", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "misc.inserts")));

    public static final RegistryObject<SoundEvent> MISC_POUNDING =
        SOUNDS.register("misc.pounding", () ->
            new SoundEvent(new ResourceLocation(GirlMod.MODID, "misc.pounding")));

}