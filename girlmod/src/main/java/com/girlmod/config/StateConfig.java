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
import java.util.*;

/**
 * Loads animation state definitions from config/girlmod/states.json.
 *
 * This is the whole point of the config system: to add a new pose, tweak
 * a duration, change whether an animation locks movement, or set up a
 * PLAY_ONCE → followUp chain, you edit the JSON and run /girlmod reload —
 * no Java code, no recompile, no jar rebuild.
 *
 * If the file doesn't exist yet, a copy of the bundled default (every
 * state Girlmod ships with) is written out on first run, so there's
 * always a working starting point sitting right there to edit.
 */
public class StateConfig {

    private static final Map<String, StateDefinition> STATES = new LinkedHashMap<>();

    /** Load (or first-time create) states.json. Safe to call multiple times. */
    public static void load() {
        Path configPath = getConfigPath();
        if (!Files.exists(configPath)) {
            writeDefaults(configPath);
        }

        Map<String, StateDefinition> loaded = new LinkedHashMap<>();
        try {
            String json = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
            // Use the instance-based parse() rather than the static parseString() —
            // Minecraft 1.16.5 bundles Gson 2.8.0, and parseString() wasn't added
            // until Gson 2.8.9. This older form works on every Gson version.
            JsonObject root = new JsonParser().parse(json).getAsJsonObject();
            JsonObject states = root.getAsJsonObject("states");

            // entrySet() rather than keySet() — MC 1.16.5's bundled Gson 2.8.0
            // doesn't have JsonObject.keySet() at all (added in a later Gson
            // version). entrySet() has existed since Gson's earliest releases.
            for (Map.Entry<String, JsonElement> entry : states.entrySet()) {
                String id = entry.getKey();
                JsonObject s = entry.getValue().getAsJsonObject();

                String animName   = s.get("animation").getAsString();
                String loopStr     = s.get("loopType").getAsString();
                boolean hasPlayer  = s.get("hasPlayer").getAsBoolean();
                int duration       = s.get("durationTicks").getAsInt();
                boolean locks      = s.get("locksMovement").getAsBoolean();
                String followUp    = (s.has("followUp") && !s.get("followUp").isJsonNull())
                                      ? s.get("followUp").getAsString() : null;
                // Optional field, defaults to false — existing states.json files on
                // disk from before this feature was added won't have this key at all.
                boolean showPartnerRig = s.has("showPartnerRig") && s.get("showPartnerRig").getAsBoolean();
                // Optional field. Defaults to true for IDLE/WALK and false for
                // everything else so existing states.json files (saved before
                // this feature existed, with no "isMovement" key at all) still
                // behave correctly without needing to be edited by hand.
                boolean isMovement = s.has("isMovement")
                    ? s.get("isMovement").getAsBoolean()
                    : (id.equals("IDLE") || id.equals("WALK"));
                // Optional, defaults to "Misc" — existing states.json files
                // won't have this key at all.
                String group = s.has("group") ? s.get("group").getAsString() : "Misc";
                // Optional, defaults to false — existing states.json files
                // won't have this key at all, so nothing is hidden unless
                // explicitly opted into.
                boolean hidden = s.has("hidden") && s.get("hidden").getAsBoolean();

                StateDefinition.LoopType loopType;
                try {
                    loopType = StateDefinition.LoopType.valueOf(loopStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("[GirlMod] State '" + id + "' has invalid loopType '" + loopStr
                        + "' (must be LOOP, PLAY_ONCE, or HOLD_ON_LAST_FRAME). Skipping this state.");
                    continue;
                }

                loaded.put(id, new StateDefinition(id, animName, loopType, hasPlayer, duration, locks, followUp, showPartnerRig, isMovement, group, hidden));
            }

            // Validate followUp references point at states that actually exist —
            // catches typos in the JSON early with a clear log message instead of
            // silently falling back to IDLE at runtime with no explanation.
            for (StateDefinition def : loaded.values()) {
                if (def.followUpId != null && !loaded.containsKey(def.followUpId)) {
                    System.out.println("[GirlMod] WARNING: state '" + def.id + "' has followUp '"
                        + def.followUpId + "' which doesn't exist. It will fall back to IDLE when reached.");
                }
            }

            if (!loaded.containsKey("IDLE")) {
                System.out.println("[GirlMod] WARNING: states.json has no 'IDLE' state defined. "
                    + "This is used as the fallback for unknown/invalid states — things may break.");
            }
            if (!loaded.containsKey("DOWNED")) {
                System.out.println("[GirlMod] WARNING: states.json has no 'DOWNED' state defined. "
                    + "The invincible downed/recovery sequence (see GirlEntity#hurt) will silently "
                    + "fall back to IDLE instead of playing animation.ellie.downed. Add a 'DOWNED' "
                    + "entry (see the bundled girlmod_default_states.json for the exact fields) or "
                    + "delete your states.json to regenerate the shipped defaults.");
            }

            STATES.clear();
            STATES.putAll(loaded);
            System.out.println("[GirlMod] Loaded " + STATES.size() + " animation states from " + configPath);

        } catch (Exception e) {
            System.out.println("[GirlMod] Failed to parse states.json (" + e.getMessage()
                + "). Keeping previously loaded states" + (STATES.isEmpty() ? " — using minimal safe fallback." : "."));
            e.printStackTrace();
            if (STATES.isEmpty()) {
                loadMinimalFallback();
            }
        }
    }

    /** Re-read states.json from disk. Call this from /girlmod reload. */
    public static void reload() {
        load();
    }

    /** Get a state by id. Falls back to "IDLE" if the id is unknown (and IDLE itself if that's also missing). */
    public static StateDefinition get(String id) {
        StateDefinition def = STATES.get(id);
        if (def != null) return def;
        StateDefinition idle = STATES.get("IDLE");
        if (idle != null) return idle;
        // Truly nothing loaded — return a hardcoded safety net so the game never crashes
        return new StateDefinition("IDLE", "animation.ellie.idle",
            StateDefinition.LoopType.LOOP, false, 0, false, null, false, true, "Misc", false);
    }

    /** All loaded state ids, in the order they appear in the JSON (used to build GUI buttons). */
    public static List<String> getAllIds() {
        return new ArrayList<>(STATES.keySet());
    }

    public static boolean exists(String id) {
        return STATES.containsKey(id);
    }

    /**
     * Distinct groups among states the GUI would ever show (excludes
     * isMovement and hidden states, same filter GuiGirlInteract itself
     * applies), in first-seen order — used for the GUI's group filter.
     */
    public static List<String> getGroups() {
        List<String> groups = new ArrayList<>();
        for (StateDefinition def : STATES.values()) {
            if (def.isMovement || def.hidden) continue;
            if (!groups.contains(def.group)) groups.add(def.group);
        }
        return groups;
    }

    /**
     * All state ids whose *animation name* contains the given substring
     * (case-insensitive) — e.g. "start" matches COWGIRL_START
     * (animation.ellie.cowgirlstart) and MISSIONARY_START
     * (animation.ellie.missionary_start). Used to pick a random existing
     * pose to reuse for a mob encounter rather than requiring dedicated
     * per-mob animations/states.
     */
    public static List<String> getIdsWithAnimationContaining(String substring) {
        List<String> result = new ArrayList<>();
        String needle = substring.toLowerCase(Locale.ROOT);
        for (StateDefinition def : STATES.values()) {
            if (def.animName != null && def.animName.toLowerCase(Locale.ROOT).contains(needle)) {
                result.add(def.id);
            }
        }
        return result;
    }

    // ── internals ────────────────────────────────────────────────────────────

    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve("girlmod").resolve("states.json");
    }

    private static void writeDefaults(Path path) {
        try {
            Files.createDirectories(path.getParent());
            try (InputStream in = StateConfig.class.getClassLoader()
                    .getResourceAsStream("girlmod_default_states.json")) {
                if (in == null) {
                    System.out.println("[GirlMod] Bundled default states.json resource is missing from the jar!");
                    return;
                }
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("[GirlMod] Wrote default states.json to " + path);
        } catch (IOException e) {
            System.out.println("[GirlMod] Could not write default states.json: " + e.getMessage());
        }
    }

    private static void loadMinimalFallback() {
        STATES.clear();
        STATES.put("IDLE", new StateDefinition("IDLE", "animation.ellie.idle",
            StateDefinition.LoopType.LOOP, false, 0, false, null, false, true, "Misc", false));
    }
}
