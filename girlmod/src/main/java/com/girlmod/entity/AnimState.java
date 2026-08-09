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
    IDLE        ("animation.ellie.idle",             LoopType.LOOP,               false, 0),
    WALK        ("animation.ellie.walk",             LoopType.LOOP,               false, 0),
    SIT         ("animation.ellie.sit",              LoopType.LOOP,               false, 0),
    SITDOWN     ("animation.ellie.sitdown",          LoopType.PLAY_ONCE,          false, 120),

    // ── Interaction ─────────────────────────────────────────────────────────
    HUG         ("animation.ellie.hug",              LoopType.HOLD_ON_LAST_FRAME, true,  0),
    HUGIDLE     ("animation.ellie.hugidle",          LoopType.LOOP,               true,  0),
    STRIP       ("animation.ellie.strip",            LoopType.PLAY_ONCE,          false, 100),

    // ── Cowgirl ─────────────────────────────────────────────────────────────
    COWGIRL_START ("animation.ellie.cowgirlstart",   LoopType.PLAY_ONCE,          true,  320),
    COWGIRL_SLOW  ("animation.ellie.cowgirlslow2",   LoopType.LOOP,               true,  0),
    COWGIRL_FAST  ("animation.ellie.cowgirlfast",    LoopType.LOOP,               true,  0),
    COWGIRL_CUM   ("animation.ellie.cowgirlcum",     LoopType.PLAY_ONCE,          true,  300),

    // ── Missionary ──────────────────────────────────────────────────────────
    MISSIONARY_START ("animation.ellie.missionary_start", LoopType.PLAY_ONCE,     true,  64),
    MISSIONARY_SLOW  ("animation.ellie.missionary_slow",  LoopType.LOOP,          true,  0),
    MISSIONARY_FAST  ("animation.ellie.missionary_fast",  LoopType.LOOP,          true,  0),
    MISSIONARY_CUM   ("animation.ellie.missionary_cum",   LoopType.PLAY_ONCE,     true,  280),

    // ── Carry ───────────────────────────────────────────────────────────────
    CARRY_INTRO ("animation.ellie.carry_intro",      LoopType.PLAY_ONCE,          true,  200),
    CARRY_SLOW  ("animation.ellie.carry_slow1",      LoopType.LOOP,               true,  0),
    CARRY_FAST  ("animation.ellie.carry_fast",       LoopType.LOOP,               true,  0),
    CARRY_CUM   ("animation.ellie.carry_cum",        LoopType.PLAY_ONCE,          true,  100);

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

    public final String   animName;
    public final LoopType loopType;
    public final boolean  hasPlayer;
    public final int      durationTicks;
    public AnimState      followUp; // mutable so static block can set it

    AnimState(String animName, LoopType loopType, boolean hasPlayer, int durationTicks) {
        this.animName      = animName;
        this.loopType      = loopType;
        this.hasPlayer     = hasPlayer;
        this.durationTicks = durationTicks;
        this.followUp      = null;
    }
}
