package com.girlmod.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Loads available animation sets (which .animation.json file supplies the
 * actual GeckoLib clips for every state's animName) from config/girlmod/
 * animation_sets.json — same load/reload pattern as the other configs.
 *
 * This is independent of the state system and the skin system: states.json
 * still says WHICH clip name (e.g. "animation.ellie.hug") plays for a
 * given state, skins.json still says which geo+texture she's wearing — an
 * animation set just says which .animation.json FILE those clip names are
 * looked up in. Swapping sets lets you use an entirely different set of
 * keyframed clips (e.g. a smoother/alternate animator's work) without
 * touching states.json at all, as long as the set defines the same clip
 * names states.json references.
 */
public class AnimationSetConfig {

    public static final String DEFAULT_SET_ID = "default";

    public static final class AnimationSetDefinition {
        public final String id;
        public final String displayName;
        public final String animationFile;

        public AnimationSetDefinition(String id, String displayName, String animationFile) {
            this.id = id;
            this.displayName = displayName;
            this.animationFile = animationFile;
        }
    }

    private static final Map<String, AnimationSetDefinition> SETS = new LinkedHashMap<>();

    /** Load (or first-time create) animation_sets.json. Safe to call multiple times. */
    public static void load() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            writeDefaults(configPath);
        }

        Map<String, AnimationSetDefinition> loaded = new LinkedHashMap<>();
        try {
            String json = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
            JsonObject root = new JsonParser().parse(json).getAsJsonObject();
            JsonObject sets = root.getAsJsonObject("animationSets");

            for (Map.Entry<String, JsonElement> entry : sets.entrySet()) {
                String id = entry.getKey();
                JsonObject s = entry.getValue().getAsJsonObject();

                if (!s.has("animationFile")) {
                    System.out.println("[GirlMod] Animation set '" + id + "' is missing 'animationFile' — skipping.");
                    continue;
                }

                String displayName = s.has("displayName") ? s.get("displayName").getAsString() : id;
                loaded.put(id, new AnimationSetDefinition(id, displayName, s.get("animationFile").getAsString()));
            }
        } catch (IOException e) {
            System.out.println("[GirlMod] ERROR reading animation_sets.json: " + e.getMessage());
        }

        if (!loaded.containsKey(DEFAULT_SET_ID)) {
            System.out.println("[GirlMod] WARNING: animation_sets.json has no '" + DEFAULT_SET_ID + "' set defined. "
                + "This is the fallback used whenever an unknown/removed set id is selected — things may break.");
        }

        SETS.clear();
        SETS.putAll(loaded);
        System.out.println("[GirlMod] Loaded " + SETS.size() + " animation set(s) from " + configPath);
    }

    /** Re-read animation_sets.json from disk. Call this from /girlmod reload. */
    public static void reload() {
        load();
    }

    /** Get an animation set by id. Falls back to "default" if the id is unknown (e.g. removed from config, or a stale save). */
    public static AnimationSetDefinition get(String id) {
        AnimationSetDefinition def = SETS.get(id);
        if (def != null) return def;
        return SETS.get(DEFAULT_SET_ID);
    }

    public static boolean exists(String id) {
        return SETS.containsKey(id);
    }

    public static Map<String, AnimationSetDefinition> getAll() {
        return SETS;
    }

    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve("girlmod").resolve("animation_sets.json");
    }

    private static void writeDefaults(Path path) {
        try {
            Files.createDirectories(path.getParent());
            try (InputStream in = AnimationSetConfig.class.getClassLoader()
                    .getResourceAsStream("girlmod_default_animation_sets.json")) {
                if (in == null) {
                    System.out.println("[GirlMod] Bundled default animation_sets.json resource is missing from the jar!");
                    return;
                }
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[GirlMod] Wrote default animation_sets.json to " + path);
        } catch (IOException e) {
            System.out.println("[GirlMod] Could not write default animation_sets.json: " + e.getMessage());
        }
    }
}
