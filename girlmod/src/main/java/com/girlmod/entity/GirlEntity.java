package com.girlmod.entity;

import com.girlmod.config.StateConfig;
import com.girlmod.config.StateDefinition;
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

/**
 * State is now a plain String id (e.g. "HUG", "COWGIRL_SLOW") looked up
 * against StateConfig, which loads its definitions from
 * config/girlmod/states.json at runtime. This means adding/editing
 * animation states no longer requires touching this class or recompiling
 * the mod — see StateConfig.java and DEVELOPMENT.md for details.
 */
public class GirlEntity extends CreatureEntity implements IAnimatable {

    public static final String DEFAULT_STATE_ID = "IDLE";

    private static final DataParameter<String> STATE =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.STRING);

    private final AnimationFactory factory = new AnimationFactory(this);

    @OnlyIn(Dist.CLIENT)
    private String lastRenderedStateId;

    private int animTicksInState  = 0;
    private int animDurationTicks = 0;

    public GirlEntity(EntityType<? extends GirlEntity> type, World world) {
        super(type, world);
        this.maxUpStep = 0.6f;
    }

    // ── Attributes ────────────────────────────────────────────────────────────

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return CreatureEntity.createLivingAttributes()
            .add(Attributes.MAX_HEALTH,     20.0)
            .add(Attributes.MOVEMENT_SPEED,  0.25)
            .add(Attributes.FOLLOW_RANGE,   16.0);
    }

    // ── AI ────────────────────────────────────────────────────────────────────

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WanderIfIdleGoal(this, 1.0));
        this.goalSelector.addGoal(2, new LookAtIfIdleGoal(this, PlayerEntity.class, 8.0f));
    }

    /** True when the current animation should freeze movement/wandering. */
    public boolean isBusy() {
        return getStateDef().locksMovement;
    }

    private static class WanderIfIdleGoal extends RandomWalkingGoal {
        private final GirlEntity girl;
        WanderIfIdleGoal(GirlEntity girl, double speed) {
            super(girl, speed);
            this.girl = girl;
        }
        @Override public boolean canUse()           { return !girl.isBusy() && super.canUse(); }
        @Override public boolean canContinueToUse() { return !girl.isBusy() && super.canContinueToUse(); }
    }

    private static class LookAtIfIdleGoal extends LookAtGoal {
        private final GirlEntity girl;
        LookAtIfIdleGoal(GirlEntity girl, Class<PlayerEntity> target, float range) {
            super(girl, target, range);
            this.girl = girl;
        }
        @Override public boolean canUse()           { return !girl.isBusy() && super.canUse(); }
        @Override public boolean canContinueToUse() { return !girl.isBusy() && super.canContinueToUse(); }
    }

    // ── Data ──────────────────────────────────────────────────────────────────

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, DEFAULT_STATE_ID);
    }

    @Override
    public boolean removeWhenFarAway(double dist) { return false; }

    @Override
    public boolean isInvulnerableTo(DamageSource source) { return true; }

    // ── Interaction ───────────────────────────────────────────────────────────

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (this.level.isClientSide) {
            net.minecraft.client.Minecraft.getInstance().setScreen(
                new com.girlmod.client.gui.GuiGirlInteract(this, player)
            );
        }
        return ActionResultType.sidedSuccess(this.level.isClientSide);
    }

    // ── State ─────────────────────────────────────────────────────────────────

    /** Set state by id (e.g. "HUG"). Unknown ids fall back to IDLE via StateConfig.get(). */
    public void setState(String stateId) {
        StateDefinition def = StateConfig.get(stateId);
        this.entityData.set(STATE, def.id); // use the resolved id, so bad input becomes "IDLE" consistently
        this.animTicksInState  = 0;
        this.animDurationTicks = def.durationTicks;

        if (def.locksMovement) {
            this.getNavigation().stop(); // cancel any in-progress wander path immediately
        }

        if (def.hasPlayer) {
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

    public String getStateId() {
        return this.entityData.get(STATE);
    }

    public StateDefinition getStateDef() {
        return StateConfig.get(getStateId());
    }

    // ── Tick ──────────────────────────────────────────────────────────────────

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (isBusy()) {
                this.getNavigation().stop(); // belt-and-braces: never let her drift while busy
            }
            StateDefinition current = getStateDef();
            if (current.loopType == StateDefinition.LoopType.PLAY_ONCE && current.followUpId != null) {
                animTicksInState++;
                if (animTicksInState >= animDurationTicks && animDurationTicks > 0) {
                    setState(current.followUpId);
                }
            }
        }
    }

    // ── NBT ───────────────────────────────────────────────────────────────────

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("GirlState", getStateId());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("GirlState")) {
            setState(nbt.getString("GirlState")); // setState() already falls back to IDLE for unknown ids
        }
    }

    // ── GeckoLib ──────────────────────────────────────────────────────────────

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
        String stateId = getStateId();
        if (this.level.isClientSide && !stateId.equals(lastRenderedStateId)) {
            lastRenderedStateId = stateId;
            event.getController().setAnimation(buildAnimation(StateConfig.get(stateId)));
        }
        return PlayState.CONTINUE;
    }

    private AnimationBuilder buildAnimation(StateDefinition def) {
        AnimationBuilder builder = new AnimationBuilder();
        switch (def.loopType) {
            case LOOP:               builder.addAnimation(def.animName, true);  break;
            case HOLD_ON_LAST_FRAME: builder.addAnimation(def.animName, false); break;
            case PLAY_ONCE: default: builder.addAnimation(def.animName, false); break;
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
