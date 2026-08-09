package com.girlmod.entity;

import com.girlmod.sound.SoundMapper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GirlEntity extends CreatureEntity implements IAnimatable {

    private static final DataParameter<String> STATE =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.STRING);

    private final AnimationFactory factory = new AnimationFactory(this);

    @OnlyIn(Dist.CLIENT)
    private AnimState lastRenderedState;

    private int animTicksInState  = 0;
    private int animDurationTicks = 0;

    public GirlEntity(EntityType<? extends GirlEntity> type, World world) {
        super(type, world);
        this.maxUpStep = 0.6f;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return CreatureEntity.createLivingAttributes()
            .add(Attributes.MAX_HEALTH,     20.0)
            .add(Attributes.MOVEMENT_SPEED,  0.25)
            .add(Attributes.FOLLOW_RANGE,   16.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WanderIfIdleGoal(this, 1.0));
        this.goalSelector.addGoal(2, new LookAtIfIdleGoal(this, PlayerEntity.class, 8.0f));
    }

    /** True when the current animation should freeze movement/wandering. */
    public boolean isBusy() {
        return getAnimState().locksMovement;
    }

    /** Wander goal that only runs while not mid-animation. */
    private static class WanderIfIdleGoal extends RandomWalkingGoal {
        private final GirlEntity girl;
        WanderIfIdleGoal(GirlEntity girl, double speed) {
            super(girl, speed);
            this.girl = girl;
        }
        @Override public boolean canUse()           { return !girl.isBusy() && super.canUse(); }
        @Override public boolean canContinueToUse() { return !girl.isBusy() && super.canContinueToUse(); }
    }

    /** Look-at goal that only runs while not mid-animation (avoids fighting the manual face-player snap). */
    private static class LookAtIfIdleGoal extends LookAtGoal {
        private final GirlEntity girl;
        LookAtIfIdleGoal(GirlEntity girl, Class<PlayerEntity> target, float range) {
            super(girl, target, range);
            this.girl = girl;
        }
        @Override public boolean canUse()           { return !girl.isBusy() && super.canUse(); }
        @Override public boolean canContinueToUse() { return !girl.isBusy() && super.canContinueToUse(); }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, AnimState.IDLE.name());
    }

    @Override
    public boolean removeWhenFarAway(double dist) { return false; }

    @Override
    public boolean isInvulnerableTo(DamageSource source) { return true; }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (this.level.isClientSide) {
            net.minecraft.client.Minecraft.getInstance().setScreen(
                new com.girlmod.client.gui.GuiGirlInteract(this, player)
            );
        }
        return ActionResultType.sidedSuccess(this.level.isClientSide);
    }

    public void setState(AnimState state) {
        this.entityData.set(STATE, state.name());
        this.animTicksInState  = 0;
        this.animDurationTicks = state.durationTicks;

        if (state.locksMovement) {
            this.getNavigation().stop(); // cancel any in-progress wander path immediately
        }

        if (state.hasPlayer) {
            PlayerEntity nearest = this.level.getNearestPlayer(this, 8.0);
            if (nearest != null) {
                double dx = nearest.getX() - this.getX();
                double dz = nearest.getZ() - this.getZ();
                this.yRot     = (float)(Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0f;
                this.yHeadRot = this.yRot;
                this.yBodyRot = this.yRot;
            }
        }
    }

    public AnimState getAnimState() {
        try {
            return AnimState.valueOf(this.entityData.get(STATE));
        } catch (IllegalArgumentException e) {
            return AnimState.IDLE;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (isBusy()) {
                this.getNavigation().stop(); // belt-and-braces: never let her drift while busy
            }
            AnimState current = getAnimState();
            if (current.loopType == AnimState.LoopType.PLAY_ONCE && current.followUp != null) {
                animTicksInState++;
                if (animTicksInState >= animDurationTicks && animDurationTicks > 0) {
                    setState(current.followUp);
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("GirlState", getAnimState().name());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("GirlState")) {
            try { setState(AnimState.valueOf(nbt.getString("GirlState"))); }
            catch (IllegalArgumentException e) { setState(AnimState.IDLE); }
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<GirlEntity> controller =
            new AnimationController<>(this, "action", 5.0f, this::animationPredicate);
        controller.registerSoundListener(this::onSoundKeyframe);
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() { return factory; }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        AnimState state = getAnimState();
        if (this.level.isClientSide && state != lastRenderedState) {
            lastRenderedState = state;
            event.getController().setAnimation(buildAnimation(state));
        }
        return PlayState.CONTINUE;
    }

    private AnimationBuilder buildAnimation(AnimState state) {
        AnimationBuilder builder = new AnimationBuilder();
        switch (state.loopType) {
            case LOOP:               builder.addAnimation(state.animName, true);  break;
            case HOLD_ON_LAST_FRAME: builder.addAnimation(state.animName, false); break;
            case PLAY_ONCE: default: builder.addAnimation(state.animName, false); break;
        }
        return builder;
    }

    private <E extends IAnimatable> void onSoundKeyframe(SoundKeyframeEvent<E> event) {
        SoundEvent sound = SoundMapper.resolve(event.sound);
        if (sound == null) return;
        this.level.playLocalSound(
            this.getX(), this.getY(), this.getZ(),
            sound, SoundCategory.NEUTRAL, 1.0f, 1.0f, false
        );
    }
}
