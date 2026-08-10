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
    // Category shown as a tab/filter in the GUI (e.g. "Affection", "Sex",
    // "Combat") — purely organizational, has no effect on gameplay.
    // Defaults to "Misc" if not specified in states.json.
    public final String    group;
    // true = never shown as a button in the GUI, regardless of group —
    // for states that should only ever be triggered by game logic
    // (combat swings, DOWNED's mob-matched poses, etc), not picked
    // manually. Independent of isMovement (which is its own, always-
    // hidden category); this is a general-purpose hide flag for any state.
    public final boolean   hidden;

    public StateDefinition(String id, String animName, LoopType loopType,
                            boolean hasPlayer, int durationTicks,
                            boolean locksMovement, String followUpId,
                            boolean showPartnerRig, boolean isMovement,
                            String group, boolean hidden) {
        this.id             = id;
        this.animName       = animName;
        this.loopType       = loopType;
        this.hasPlayer      = hasPlayer;
        this.durationTicks  = durationTicks;
        this.locksMovement  = locksMovement;
        this.followUpId     = followUpId;
        this.showPartnerRig = showPartnerRig;
        this.isMovement     = isMovement;
        this.group          = group;
        this.hidden         = hidden;
    }
}
