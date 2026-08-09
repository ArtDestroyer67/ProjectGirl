package com.girlmod.sound;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Maps GeckoLib sound keyframe effect names (from the animation JSON's
 * "sound_effects" blocks) to registered SoundEvents, loaded at runtime
 * from config/girlmod/sound_mappings.json.
 *
 * To remap a sound, add a new effect, or mark a new effect as a non-sound
 * game-logic trigger: edit the JSON and run /girlmod reload — no
 * recompile needed.
 *
 * IMPORTANT: each category (e.g. "girl.lipsound") must be ONE SoundEvent
 * registered in ModSounds/sounds.json. Do not map an effect to a numbered
 * sub-id like "girl.lipsound3" — those were never registered as separate
 * events; Minecraft's own sound engine picks a random .ogg variant from
 * the category automatically.
 */
public class SoundMapper {

    private static final Map<String, String> SOUND_MAP = new HashMap<>();
    private static final Set<String> NON_SOUND_EFFECTS = new HashSet<>();

    public static void load() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            writeDefaults(configPath);
        }

        Map<String, String> loadedMap = new HashMap<>();
        Set<String> loadedNonSound = new HashSet<>();

        try {
            String json = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            JsonObject mappings = root.getAsJsonObject("mappings");
            for (String effect : mappings.keySet()) {
                loadedMap.put(effect, mappings.get(effect).getAsString());
            }

            if (root.has("nonSoundEffects")) {
                root.getAsJsonArray("nonSoundEffects")
                    .forEach(e -> loadedNonSound.add(e.getAsString()));
            }

            SOUND_MAP.clear();
            SOUND_MAP.putAll(loadedMap);
            NON_SOUND_EFFECTS.clear();
            NON_SOUND_EFFECTS.addAll(loadedNonSound);

            System.out.println("[GirlMod] Loaded " + SOUND_MAP.size() + " sound mappings from " + configPath);

        } catch (Exception e) {
            System.out.println("[GirlMod] Failed to parse sound_mappings.json (" + e.getMessage()
                + "). Keeping previously loaded mappings.");
            e.printStackTrace();
        }
    }

    /** Re-read sound_mappings.json from disk. Call this from /girlmod reload. */
    public static void reload() {
        load();
    }

    /** Resolve an effect name to the SoundEvent registered for its category. Returns null for non-sound triggers or unmapped effects. */
    public static SoundEvent resolve(String effectName) {
        if (NON_SOUND_EFFECTS.contains(effectName)) return null;
        String category = SOUND_MAP.get(effectName);
        if (category == null) return null;
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("girlmod", category));
    }

    // ── internals ────────────────────────────────────────────────────────────

    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve("girlmod").resolve("sound_mappings.json");
    }

    private static void writeDefaults(Path path) {
        try {
            Files.createDirectories(path.getParent());
            try (InputStream in = SoundMapper.class.getClassLoader()
                    .getResourceAsStream("girlmod_default_sound_mappings.json")) {
                if (in == null) {
                    System.out.println("[GirlMod] Bundled default sound_mappings.json resource is missing from the jar!");
                    return;
                }
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[GirlMod] Wrote default sound_mappings.json to " + path);
        } catch (IOException e) {
            System.out.println("[GirlMod] Could not write default sound_mappings.json: " + e.getMessage());
        }
    }
}
