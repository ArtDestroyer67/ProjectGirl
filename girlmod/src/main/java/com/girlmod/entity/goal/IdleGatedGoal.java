package com.girlmod.entity.goal;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.ai.goal.Goal;

/**
 * Wraps any vanilla or custom Goal so it only activates while the girl
 * is not mid-animation (isBusy() == false).
 *
 * This is how every AI behavior — wandering, looking around, following
 * the player, melee/ranged combat — stays out of the way while a pose
 * from states.json is playing. Drawing a sword or pathing toward a
 * target mid-hug would look absurd otherwise.
 *
 * One generic wrapper instead of a near-duplicate subclass per goal
 * (WanderIfIdleGoal, MeleeIfIdleGoal, etc) — any future goal gets
 * idle-gating for free just by wrapping it.
 */
public class IdleGatedGoal extends Goal {

    private final GirlEntity girl;
    private final Goal delegate;

    public IdleGatedGoal(GirlEntity girl, Goal delegate) {
        this.girl = girl;
        this.delegate = delegate;
        this.setFlags(delegate.getFlags());
    }

    @Override public boolean canUse()          { return !girl.isBusy() && delegate.canUse(); }
    @Override public boolean canContinueToUse() { return !girl.isBusy() && delegate.canContinueToUse(); }
    @Override public boolean isInterruptable()   { return delegate.isInterruptable(); }
    @Override public void start()                 { delegate.start(); }
    @Override public void stop()                   { delegate.stop(); }
    @Override public void tick()                    { delegate.tick(); }
}
