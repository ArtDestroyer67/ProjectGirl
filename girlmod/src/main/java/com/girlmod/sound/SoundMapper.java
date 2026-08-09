package com.girlmod.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class SoundMapper {

    private static final Random RNG = new Random();

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

    public static SoundEvent resolve(String effectName) {
        if (NON_SOUND_EFFECTS.contains(effectName)) return null;
        String[] candidates = SOUND_MAP.get(effectName);
        if (candidates == null) return null;
        String chosen = candidates[RNG.nextInt(candidates.length)];
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("girlmod", chosen));
    }

    private static final Map<String, String[]> SOUND_MAP = new HashMap<>();

    static {
        put("lipsound",  range("girl.lipsound",   0, 9));
        put("pound",     range("misc.pounding",   0, 35));
        put("cum",       range("misc.inserts",    0, 4));
        put("bedRustle", range("misc.bedrustle",  0, 1));

        put("hugMSG2",         range("girl.giggle",        0, 4));
        put("hugMSG3",         range("girl.mmm",           0, 8));
        put("hugMSG4",         range("girl.happyoh",       0, 2));
        put("hugMSG5",         range("girl.lightbreathing",0, 7));
        put("hugselectedMSG1", range("girl.giggle",        0, 4));
        put("hugselectedMSG2", range("girl.mmm",           0, 8));

        put("sitdownMSG1", range("girl.sigh",  0, 1));
        put("stripMSG1",   range("girl.hmph",  0, 3));

        put("cowgirlStartMSG0", range("girl.mommyhorny",    0, 1));
        put("cowgirlStartMSG1", range("girl.lightbreathing",0, 7));
        put("cowgirlStartMSG2", range("girl.moan",          0, 8));

        put("cowgirlcumMSG1", range("girl.moan",            0, 8));
        put("cowgirlcumMSG2", range("girl.ahh",             0, 9));
        put("cowgirlcumMSG3", range("girl.heavybreathing",  0, 8));
        put("cowgirlcumMSG4", range("girl.ahh",             0, 9));
        put("cowgirlcumMSG5", range("girl.moan",            0, 8));
        put("cowgirlcumMSG6", range("girl.aftersessionmoan",0, 4));

        put("cowgirlfastMSG1",     range("girl.mmm",           0, 8));
        put("missionary_fastMSG1", range("girl.moan",          0, 8));
        put("missionary_slowMSG1", range("girl.lightbreathing",0, 7));
        put("missionary_cumMSG1",  range("girl.ahh",           0, 9));
        put("missionary_cumMSG2",  range("girl.aftersessionmoan",0,4));

        put("carry_introMSG1", range("girl.happyoh",   0, 2));
        put("carry_introMSG2", range("girl.mommyhorny",0, 1));
        put("dashMSG1",        range("girl.huh",       0, 1));
    }

    private static void put(String effect, String[] sounds) { SOUND_MAP.put(effect, sounds); }

    private static String[] range(String prefix, int min, int max) {
        String[] result = new String[max - min + 1];
        for (int i = min; i <= max; i++) result[i - min] = prefix + i;
        return result;
    }
}
