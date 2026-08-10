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
 * Loads available skins (a geo model + texture set, one for each of nude
 * and dressed) from config/girlmod/skins.json — same load/first-run-copy/
 * /girlmod-reload pattern as StateConfig, SoundMapper, and
 * MobInteractConfig.
 *
 * Add a new skin by adding your geo/texture files under assets/girlmod/
 * (or a resource pack) and a matching entry here — no rebuild needed,
 * just /girlmod reload (or a restart if the files themselves are new
 * resources rather than an edit to an existing one, since new resource
 * files need a resource reload — F3+T — to be picked up).
 *
 * Paths may optionally include a namespace (e.g. "othermod:geo/x.geo.json")
 * to reuse assets from another mod/resource pack; with no colon they're
 * assumed to be under "girlmod", same convention as SoundMapper's mappings.
 */
public class SkinConfig {

    public static final String DEFAULT_SKIN_ID = "default";

    public static final class SkinDefinition {
        public final String id;
        public final String displayName;
        public final String geoNude;
        public final String geoDressed;
        public final String texture;

        public SkinDefinition(String id, String displayName, String geoNude, String geoDressed, String texture) {
            this.id = id;
            this.displayName = displayName;
            this.geoNude = geoNude;
            this.geoDressed = geoDressed;
            this.texture = texture;
        }
    }

    private static final Map<String, SkinDefinition> SKINS = new LinkedHashMap<>();

    /** Load (or first-time create) skins.json. Safe to call multiple times. */
    public static void load() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            writeDefaults(configPath);
        }

        Map<String, SkinDefinition> loaded = new LinkedHashMap<>();
        try {
            String json = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
            JsonObject root = new JsonParser().parse(json).getAsJsonObject();
            JsonObject skins = root.getAsJsonObject("skins");

            for (Map.Entry<String, JsonElement> entry : skins.entrySet()) {
                String id = entry.getKey();
                JsonObject s = entry.getValue().getAsJsonObject();

                if (!s.has("geoNude") || !s.has("geoDressed") || !s.has("texture")) {
                    System.out.println("[GirlMod] Skin '" + id + "' is missing geoNude/geoDressed/texture — skipping.");
                    continue;
                }

                String displayName = s.has("displayName") ? s.get("displayName").getAsString() : id;
                loaded.put(id, new SkinDefinition(
                    id, displayName,
                    s.get("geoNude").getAsString(),
                    s.get("geoDressed").getAsString(),
                    s.get("texture").getAsString()
                ));
            }
        } catch (IOException e) {
            System.out.println("[GirlMod] ERROR reading skins.json: " + e.getMessage());
        }

        if (!loaded.containsKey(DEFAULT_SKIN_ID)) {
            System.out.println("[GirlMod] WARNING: skins.json has no '" + DEFAULT_SKIN_ID + "' skin defined. "
                + "This is the fallback used whenever an unknown/removed skin id is selected — things may break.");
        }

        SKINS.clear();
        SKINS.putAll(loaded);
        System.out.println("[GirlMod] Loaded " + SKINS.size() + " skin(s) from " + configPath);
    }

    /** Re-read skins.json from disk. Call this from /girlmod reload. */
    public static void reload() {
        load();
    }

    /** Get a skin by id. Falls back to the "default" skin if the id is unknown (e.g. removed from config, or a stale save). */
    public static SkinDefinition get(String id) {
        SkinDefinition def = SKINS.get(id);
        if (def != null) return def;
        return SKINS.get(DEFAULT_SKIN_ID);
    }

    public static boolean exists(String id) {
        return SKINS.containsKey(id);
    }

    public static Map<String, SkinDefinition> getAll() {
        return SKINS;
    }

    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve("girlmod").resolve("skins.json");
    }

    private static void writeDefaults(Path path) {
        try {
            Files.createDirectories(path.getParent());
            try (InputStream in = SkinConfig.class.getClassLoader()
                    .getResourceAsStream("girlmod_default_skins.json")) {
                if (in == null) {
                    System.out.println("[GirlMod] Bundled default skins.json resource is missing from the jar!");
                    return;
                }
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[GirlMod] Wrote default skins.json to " + path);
        } catch (IOException e) {
            System.out.println("[GirlMod] Could not write default skins.json: " + e.getMessage());
        }
    }
}
