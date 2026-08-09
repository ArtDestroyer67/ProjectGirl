package com.girlmod.entity.goal;

import com.girlmod.entity.GirlEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

/**
 * Makes the girl path toward the nearest player when isFollowing() is
 * true. Starts moving once the player is more than ~10 blocks away,
 * stops once within ~3 blocks — same general shape as vanilla's
 * FollowOwnerGoal (used by tamed wolves) but without any taming/love
 * mechanics, since this mod doesn't have an ownership system.
 *
 * Toggled on/off via the GUI ("Follow Me" / "Stop Following" button),
 * synced through PacketSetFlag.
 */
public class FollowPlayerGoal extends Goal {

    private static final double START_FOLLOW_DIST_SQ = 10.0 * 10.0;
    private static final double STOP_FOLLOW_DIST_SQ   = 3.0 * 3.0;
    private static final double SEARCH_RADIUS         = 32.0;

    private final GirlEntity girl;
    private final double speed;
    private PlayerEntity target;
    private int timeToRecalcPath;

    public FollowPlayerGoal(GirlEntity girl, double speed) {
        this.girl = girl;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!girl.isFollowing()) return false;
        PlayerEntity nearest = girl.level.getNearestPlayer(girl, SEARCH_RADIUS);
        if (nearest == null) return false;
        this.target = nearest;
        return girl.distanceToSqr(nearest) > START_FOLLOW_DIST_SQ;
    }

    @Override
    public boolean canContinueToUse() {
        if (!girl.isFollowing() || target == null) return false;
        return !girl.getNavigation().isDone() && girl.distanceToSqr(target) > STOP_FOLLOW_DIST_SQ;
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.target = null;
        girl.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (target == null) return;
        girl.getLookControl().setLookAt(target, 10.0F, girl.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!girl.isPassenger()) {
                girl.getNavigation().moveTo(target, speed);
            }
        }
    }
}
