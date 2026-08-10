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
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Loads which mob types are allowed to approach and interact with a downed
 * girl from config/girlmod/mob_interact.json — same load-on-first-run/
 * edit-and-/girlmod-reload pattern as StateConfig and SoundMapper.
 *
 * Two modes:
 *   BLACKLIST — every mob is eligible EXCEPT the ones listed (default,
 *               empty list, so nothing is excluded out of the box).
 *   WHITELIST — ONLY the mobs listed are eligible, everything else ignored.
 *
 * Mob names are the plain registry path, e.g. "zombie", "skeleton",
 * "husk" — not "minecraft:zombie". Works for modded mobs too as long as
 * you use their correct registry path (namespace is intentionally not
 * required/checked, so "zombie" matches any mod's "zombie" if such a
 * clash existed — simplest common case is what matters here).
 */
public class MobInteractConfig {

    public enum Mode { BLACKLIST, WHITELIST }

    private static Mode mode = Mode.BLACKLIST;
    private static final Set<String> MOBS = new HashSet<>();

    /** Load (or first-time create) mob_interact.json. Safe to call multiple times. */
    public static void load() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            writeDefaults(configPath);
        }

        Mode loadedMode = Mode.BLACKLIST;
        Set<String> loadedMobs = new HashSet<>();

        try {
            String json = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
            JsonObject root = new JsonParser().parse(json).getAsJsonObject();

            if (root.has("mode")) {
                String m = root.get("mode").getAsString().toUpperCase(Locale.ROOT);
                try {
                    loadedMode = Mode.valueOf(m);
                } catch (IllegalArgumentException e) {
                    System.out.println("[GirlMod] WARNING: mob_interact.json has invalid mode '" + m
                        + "' (must be BLACKLIST or WHITELIST). Falling back to BLACKLIST.");
                }
            }

            if (root.has("mobs")) {
                for (JsonElement el : root.getAsJsonArray("mobs")) {
                    loadedMobs.add(el.getAsString().toLowerCase(Locale.ROOT));
                }
            }

            if (loadedMode == Mode.WHITELIST && loadedMobs.isEmpty()) {
                System.out.println("[GirlMod] WARNING: mob_interact.json mode is WHITELIST but the "
                    + "'mobs' list is empty — no mob will ever be eligible to approach a downed girl.");
            }

        } catch (IOException e) {
            System.out.println("[GirlMod] ERROR reading mob_interact.json: " + e.getMessage());
        }

        mode = loadedMode;
        MOBS.clear();
        MOBS.addAll(loadedMobs);
        System.out.println("[GirlMod] Loaded mob_interact.json: mode=" + mode + ", " + MOBS.size() + " entries");
    }

    /** Re-read mob_interact.json from disk. Call this from /girlmod reload. */
    public static void reload() {
        load();
    }

    /** mobRegistryName should be the plain path, e.g. "zombie" (see registryKeyOf in GirlEntity). */
    public static boolean isEligible(String mobRegistryName) {
        String key = mobRegistryName == null ? "" : mobRegistryName.toLowerCase(Locale.ROOT);
        boolean listed = MOBS.contains(key);
        return mode == Mode.WHITELIST ? listed : !listed;
    }

    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve("girlmod").resolve("mob_interact.json");
    }

    private static void writeDefaults(Path path) {
        try {
            Files.createDirectories(path.getParent());
            try (InputStream in = MobInteractConfig.class.getClassLoader()
                    .getResourceAsStream("girlmod_default_mob_interact.json")) {
                if (in == null) {
                    System.out.println("[GirlMod] Bundled default mob_interact.json resource is missing from the jar!");
                    return;
                }
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[GirlMod] Wrote default mob_interact.json to " + path);
        } catch (IOException e) {
            System.out.println("[GirlMod] Could not write default mob_interact.json: " + e.getMessage());
        }
    }
}
