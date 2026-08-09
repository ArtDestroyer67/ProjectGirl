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
    public final boolean   showPartnerRig; // true = the embedded "steve" male rig is visible during this pose
    // true = this is a locomotion state (IDLE/WALK) driven automatically by
    // movement, not something a player picks from the GUI. Everything else
    // (poses, combat swings, DOWNED) is an "interaction" the GUI can offer,
    // gated on !isMovement — see GuiGirlInteract#buildButtons.
    public final boolean   isMovement;

    public StateDefinition(String id, String animName, LoopType loopType,
                            boolean hasPlayer, int durationTicks,
                            boolean locksMovement, String followUpId,
                            boolean showPartnerRig, boolean isMovement) {
        this.id             = id;
        this.animName       = animName;
        this.loopType       = loopType;
        this.hasPlayer      = hasPlayer;
        this.durationTicks  = durationTicks;
        this.locksMovement  = locksMovement;
        this.followUpId     = followUpId;
        this.showPartnerRig = showPartnerRig;
        this.isMovement     = isMovement;
    }
}
