package com.girlmod.entity.goal;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * Simple ranged-attack goal: while she has a live attack target, keeps
 * her at roughly attackRadius distance and periodically calls
 * girl.shootArrowAt(target).
 *
 * Written from scratch instead of using vanilla's RangedBowAttackGoal
 * because that class is generically bound to
 * RangedBowAttackGoal<T extends MonsterEntity & IRangedAttackMob> —
 * GirlEntity extends CreatureEntity, not MonsterEntity, so it can never
 * satisfy that bound. Changing her base class to MonsterEntity just to
 * reuse one vanilla goal would pull in unwanted hostile-mob behavior
 * (despawns on Peaceful, counts toward the hostile mob cap, spawn-egg
 * categorization, etc), so a small custom goal is the better trade.
 */
public class CustomBowAttackGoal extends Goal {

    private final GirlEntity girl;
    private final double speed;
    private final int attackIntervalTicks;
    private final float attackRadius;

    private int seeTime;
    private int attackCooldown;

    public CustomBowAttackGoal(GirlEntity girl, double speed, int attackIntervalTicks, float attackRadius) {
        this.girl = girl;
        this.speed = speed;
        this.attackIntervalTicks = attackIntervalTicks;
        this.attackRadius = attackRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = girl.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return canUse() || !girl.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.attackCooldown = 0;
    }

    @Override
    public void stop() {
        this.seeTime = 0;
        girl.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = girl.getTarget();
        if (target == null || !target.isAlive()) return;

        double distSq = girl.distanceToSqr(target.getX(), target.getY(), target.getZ());
        boolean canSee = girl.getSensing().canSee(target);
        this.seeTime = canSee ? this.seeTime + 1 : 0;

        float rangeSq = attackRadius * attackRadius;
        double desiredRangeSq = (attackRadius * 0.75) * (attackRadius * 0.75);

        if (distSq > rangeSq || !canSee) {
            girl.getNavigation().moveTo(target, speed); // too far or can't see — approach
        } else if (distSq < desiredRangeSq) {
            // too close — back off in the opposite direction
            girl.getNavigation().moveTo(
                girl.getX() - (target.getX() - girl.getX()),
                girl.getY(),
                girl.getZ() - (target.getZ() - girl.getZ()),
                speed
            );
        } else {
            girl.getNavigation().stop(); // good range, hold position
        }

        girl.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (--this.attackCooldown <= 0 && canSee && distSq <= (double) rangeSq) {
            this.attackCooldown = attackIntervalTicks;
            girl.shootArrowAt(target);
        }
    }
}
