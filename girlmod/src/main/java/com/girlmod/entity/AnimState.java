package com.girlmod.entity;

/**
 * Every animation state the GirlEntity can be in.
 *
 * IMPORTANT: Java enums cannot forward-reference other enum constants in
 * constructor arguments. States that need a followUp must use a null
 * placeholder and set followUp via the static initializer block below.
 */
public enum AnimState {

    // ── Idle / movement ─────────────────────────────────────────────────────
    IDLE        ("animation.ellie.idle",             LoopType.LOOP,               false, 0,   false),
    WALK        ("animation.ellie.walk",              LoopType.LOOP,               false, 0,   false),
    SIT         ("animation.ellie.sit",               LoopType.LOOP,               false, 0,   true),
    SITDOWN     ("animation.ellie.sitdown",           LoopType.PLAY_ONCE,          false, 120, true),

    // ── Interaction ─────────────────────────────────────────────────────────
    HUG         ("animation.ellie.hug",               LoopType.HOLD_ON_LAST_FRAME, true,  0,   true),
    HUGIDLE     ("animation.ellie.hugidle",           LoopType.LOOP,               true,  0,   true),
    STRIP       ("animation.ellie.strip",             LoopType.PLAY_ONCE,          false, 100, true),

    // ── Cowgirl ─────────────────────────────────────────────────────────────
    COWGIRL_START ("animation.ellie.cowgirlstart",    LoopType.PLAY_ONCE,          true,  320, true),
    COWGIRL_SLOW  ("animation.ellie.cowgirlslow2",    LoopType.LOOP,               true,  0,   true),
    COWGIRL_FAST  ("animation.ellie.cowgirlfast",     LoopType.LOOP,               true,  0,   true),
    COWGIRL_CUM   ("animation.ellie.cowgirlcum",      LoopType.PLAY_ONCE,          true,  300, true),

    // ── Missionary ──────────────────────────────────────────────────────────
    MISSIONARY_START ("animation.ellie.missionary_start", LoopType.PLAY_ONCE,      true,  64,  true),
    MISSIONARY_SLOW  ("animation.ellie.missionary_slow",  LoopType.LOOP,           true,  0,   true),
    MISSIONARY_FAST  ("animation.ellie.missionary_fast",  LoopType.LOOP,           true,  0,   true),
    MISSIONARY_CUM   ("animation.ellie.missionary_cum",   LoopType.PLAY_ONCE,      true,  280, true),

    // ── Carry ───────────────────────────────────────────────────────────────
    CARRY_INTRO ("animation.ellie.carry_intro",       LoopType.PLAY_ONCE,          true,  200, true),
    CARRY_SLOW  ("animation.ellie.carry_slow1",       LoopType.LOOP,               true,  0,   true),
    CARRY_FAST  ("animation.ellie.carry_fast",        LoopType.LOOP,               true,  0,   true),
    CARRY_CUM   ("animation.ellie.carry_cum",         LoopType.PLAY_ONCE,          true,  100, true);

    // ── Static followUp wiring ───────────────────────────────────────────────
    // Done here because Java enums can't forward-reference other constants
    // inside the enum constructor.
    static {
        SITDOWN.followUp       = SIT;
        STRIP.followUp         = IDLE;
        COWGIRL_START.followUp = COWGIRL_SLOW;
        COWGIRL_CUM.followUp   = IDLE;
        MISSIONARY_START.followUp = MISSIONARY_SLOW;
        MISSIONARY_CUM.followUp   = IDLE;
        CARRY_INTRO.followUp   = CARRY_SLOW;
        CARRY_CUM.followUp     = IDLE;
    }

    // ── Loop type ────────────────────────────────────────────────────────────
    public enum LoopType {
        LOOP, PLAY_ONCE, HOLD_ON_LAST_FRAME
    }

    public final String    animName;
    public final LoopType  loopType;
    public final boolean   hasPlayer;
    public final int       durationTicks;
    public final boolean   locksMovement; // true = disable wandering AI while in this state
    public AnimState       followUp;      // mutable so static block can set it

    AnimState(String animName, LoopType loopType, boolean hasPlayer,
              int durationTicks, boolean locksMovement) {
        this.animName      = animName;
        this.loopType      = loopType;
        this.hasPlayer     = hasPlayer;
        this.durationTicks = durationTicks;
        this.locksMovement = locksMovement;
        this.followUp      = null;
    }
}
