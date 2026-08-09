package com.girlmod.config;

/**
 * One animation state's data, loaded from config/girlmod/states.json.
 *
 * Replaces the old hardcoded AnimState enum. Every field here used to be
 * an enum constant argument that required a recompile to change — now
 * it's plain data read from disk at runtime.
 */
public class StateDefinition {

    public enum LoopType { LOOP, PLAY_ONCE, HOLD_ON_LAST_FRAME }

    public final String    id;             // e.g. "HUG" — matches the JSON key and the DataParameter value
    public final String    animName;       // e.g. "animation.ellie.hug" — key in the .animation.json file
    public final LoopType  loopType;
    public final boolean   hasPlayer;      // face the nearest player on entry
    public final int       durationTicks;  // only used when loopType == PLAY_ONCE
    public final boolean   locksMovement;  // true = disable wandering AI while in this state
    public final String    followUpId;     // nullable — state id to switch to when PLAY_ONCE finishes

    public StateDefinition(String id, String animName, LoopType loopType,
                            boolean hasPlayer, int durationTicks,
                            boolean locksMovement, String followUpId) {
        this.id            = id;
        this.animName      = animName;
        this.loopType      = loopType;
        this.hasPlayer     = hasPlayer;
        this.durationTicks = durationTicks;
        this.locksMovement = locksMovement;
        this.followUpId    = followUpId;
    }
}
