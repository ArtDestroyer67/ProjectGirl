package com.girlmod.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

/**
 * Maps GeckoLib sound keyframe effect names to registered SoundEvents.
 *
 * IMPORTANT: each category (e.g. "girl.lipsound") is registered as ONE
 * SoundEvent in ModSounds. The multiple .ogg variants for that category
 * are listed under that single event in sounds.json — Minecraft's own
 * sound engine picks a random variant at playback time. We must NOT try
 * to look up numbered sub-IDs like "girl.lipsound3" as separate events;
 * they were never registered and the lookup silently returns null,
 * which is why sound was previously not playing at all.
 */
public class SoundMapper {

    private static final Set<String> NON_SOUND_EFFECTS = new HashSet<>(Arrays.asList(
        "becomeNude", "stripDone", "startStrip",
        "cowgirlStartDone", "cowgirlcumDone", "cowgirlfastDone", "cowgirlfastdomDone",
        "missionary_startDone", "missionary_cumDone", "missionary_fastDone",
        "carry_introDone", "carry_slowDone", "carry_fastDone", "carry_cumDone",
        "carry_introCam", "carry_introCam2",
        "hugDone", "sitdownDone", "dashDone", "dashReady",
        "attackDone", "attackSound",
        "openSexUi", "sexUI", "blackScreen",
        "pearl", "cowgirlfastReady", "missionary_fastReady"
    ));

    /** Resolve an effect name to the SoundEvent registered for its category. */
    public static SoundEvent resolve(String effectName) {
        if (NON_SOUND_EFFECTS.contains(effectName)) return null;
        String category = SOUND_MAP.get(effectName);
        if (category == null) return null;
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("girlmod", category));
    }

    // ── effect name → registered sound event category (matches sounds.json keys) ──
    private static final Map<String, String> SOUND_MAP = new HashMap<>();

    static {
        SOUND_MAP.put("lipsound",  "girl.lipsound");
        SOUND_MAP.put("pound",     "misc.pounding");
        SOUND_MAP.put("cum",       "misc.inserts");
        SOUND_MAP.put("bedRustle", "misc.bedrustle");

        SOUND_MAP.put("hugMSG2",         "girl.giggle");
        SOUND_MAP.put("hugMSG3",         "girl.mmm");
        SOUND_MAP.put("hugMSG4",         "girl.happyoh");
        SOUND_MAP.put("hugMSG5",         "girl.lightbreathing");
        SOUND_MAP.put("hugselectedMSG1", "girl.giggle");
        SOUND_MAP.put("hugselectedMSG2", "girl.mmm");

        SOUND_MAP.put("sitdownMSG1", "girl.sigh");
        SOUND_MAP.put("stripMSG1",   "girl.hmph");

        SOUND_MAP.put("cowgirlStartMSG0", "girl.mommyhorny");
        SOUND_MAP.put("cowgirlStartMSG1", "girl.lightbreathing");
        SOUND_MAP.put("cowgirlStartMSG2", "girl.moan");

        SOUND_MAP.put("cowgirlcumMSG1", "girl.moan");
        SOUND_MAP.put("cowgirlcumMSG2", "girl.ahh");
        SOUND_MAP.put("cowgirlcumMSG3", "girl.heavybreathing");
        SOUND_MAP.put("cowgirlcumMSG4", "girl.ahh");
        SOUND_MAP.put("cowgirlcumMSG5", "girl.moan");
        SOUND_MAP.put("cowgirlcumMSG6", "girl.aftersessionmoan");

        SOUND_MAP.put("cowgirlfastMSG1",     "girl.mmm");
        SOUND_MAP.put("missionary_fastMSG1", "girl.moan");
        SOUND_MAP.put("missionary_slowMSG1", "girl.lightbreathing");
        SOUND_MAP.put("missionary_cumMSG1",  "girl.ahh");
        SOUND_MAP.put("missionary_cumMSG2",  "girl.aftersessionmoan");

        SOUND_MAP.put("carry_introMSG1", "girl.happyoh");
        SOUND_MAP.put("carry_introMSG2", "girl.mommyhorny");
        SOUND_MAP.put("dashMSG1",        "girl.huh");
    }
}
