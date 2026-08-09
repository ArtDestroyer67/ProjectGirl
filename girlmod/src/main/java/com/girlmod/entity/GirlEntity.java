package com.girlmod.entity;

import com.girlmod.config.StateConfig;
import com.girlmod.config.StateDefinition;
import com.girlmod.entity.goal.CustomBowAttackGoal;
import com.girlmod.entity.goal.FollowPlayerGoal;
import com.girlmod.entity.goal.IdleGatedGoal;
import com.girlmod.sound.SoundMapper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Random;

/**
 * State is a plain String id (e.g. "HUG", "COWGIRL_SLOW") looked up
 * against StateConfig, which loads its definitions from
 * config/girlmod/states.json at runtime — see StateConfig.java.
 *
 * On top of the pose system, this entity now also supports:
 *   - Following the player (toggle via GUI, see FollowPlayerGoal)
 *   - Combat: melee or bow, auto-selected based on what's in her hand
 *     (see reassessWeaponGoal()) — attacks nearby hostile mobs like an
 *     ally/companion (NearestAttackableTargetGoal<MonsterEntity>)
 *   - Equipping a weapon: shift+right-click while holding a sword or
 *     bow puts it in her mainhand
 *   - Dress/strip toggle that swaps the actual GeoModel between
 *     girl.geo.json (nude) and girl_dressed.geo.json — see GirlModel.java
 *
 * All AI behaviors are wrapped in IdleGatedGoal so nothing (wandering,
 * following, combat) runs while a locksMovement pose from states.json
 * is playing.
 */
public class GirlEntity extends CreatureEntity implements IAnimatable {

    public static final String DEFAULT_STATE_ID = "IDLE";

    private static final DataParameter<String>  STATE     =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.STRING);
    private static final DataParameter<Boolean> FOLLOWING =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DRESSED   =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ARMORED   =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> PARTNER_FORCED =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DOWNED =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.BOOLEAN);
    // "" = no mob identity currently applied (default steve.png partner texture).
    // Otherwise the ForgeRegistries.ENTITIES path of whichever mob most
    // recently downed her / is nearby while she's downed, e.g. "zombie".
    private static final DataParameter<String> PARTNER_SKIN_KEY =
        EntityDataManager.defineId(GirlEntity.class, DataSerializers.STRING);

    /** Radius used both to find a mob to "blame" for a downed sequence and to re-check for one each second while downed. */
    private static final double MOB_INTERACT_RADIUS = 6.0;
    /** Radius within which hostile mobs get un-targeted from her while she's busy (animation playing). */
    private static final double MOB_IGNORE_RADIUS = 12.0;

    private int downedTicks = 0;
    // The mob currently "attached" to her for a downed encounter — hidden,
    // AI-frozen, and pinned to her position for the duration (see
    // applyMobIdentity/detachMob). Not persisted across a world reload;
    // if that happens mid-encounter the mob just stays hidden/frozen where
    // it was until she's manually recovered (rare edge case).
    private MonsterEntity attachedMob;

    private final AnimationFactory factory = new AnimationFactory(this);
    private final Random random = new Random();

    @OnlyIn(Dist.CLIENT)
    private String lastRenderedStateId;

    private int animTicksInState  = 0;
    private int animDurationTicks = 0;

    // Combat goal instances — kept as fields so reassessWeaponGoal() can
    // swap which one is active in the goalSelector without recreating them.
    // NOT initialized here via field initializer: registerGoals() is called
    // by the superclass constructor (super(type, world) below), which runs
    // BEFORE any of this class's own field initializers execute. A field
    // initializer here would still be null the first time registerGoals()
    // needs it, causing a NullPointerException on every construction —
    // including deserializing a previously-saved girl from disk, which
    // is why she'd silently vanish from existing saves. Created instead
    // inside registerGoals() itself, see below.
    private MeleeAttackGoal meleeGoalRaw;
    private CustomBowAttackGoal bowGoalRaw;
    private Goal currentWeaponGoal; // the currently-added IdleGatedGoal wrapper, so we can remove it before swapping

    public GirlEntity(EntityType<? extends GirlEntity> type, World world) {
        super(type, world);
        this.maxUpStep = 0.6f;
    }

    // ── Attributes ────────────────────────────────────────────────────────────

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return CreatureEntity.createLivingAttributes()
            .add(Attributes.MAX_HEALTH,             20.0)
            .add(Attributes.MOVEMENT_SPEED,          0.25)
            .add(Attributes.FOLLOW_RANGE,           16.0)
            .add(Attributes.ATTACK_DAMAGE,            3.0)
            .add(Attributes.ATTACK_KNOCKBACK,         0.0)
            .add(Attributes.ARMOR,                    0.0)
            .add(Attributes.ARMOR_TOUGHNESS,          0.0)
            .add(Attributes.KNOCKBACK_RESISTANCE,     0.0);
    }

    // ── AI ────────────────────────────────────────────────────────────────────

    @Override
    protected void registerGoals() {
        this.meleeGoalRaw = new MeleeAttackGoal(this, 1.2, true);
        this.bowGoalRaw   = new CustomBowAttackGoal(this, 1.0, 20, 15.0F);

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new IdleGatedGoal(this, new FollowPlayerGoal(this, 1.15)));
        this.goalSelector.addGoal(1, new IdleGatedGoal(this, new RandomWalkingGoal(this, 1.0)));
        this.goalSelector.addGoal(3, new IdleGatedGoal(this, new LookAtGoal(this, PlayerEntity.class, 8.0f)));

        // Melee/bow goal is added dynamically by reassessWeaponGoal() at
        // priority 2 — see setItemSlot() override below, which calls it
        // whenever mainhand equipment changes (including on spawn).
        reassessWeaponGoal();

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new IdleGatedGoal(this,
            new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true)));
    }

    /** True when the current animation should freeze movement/wandering/combat/following. */
    public boolean isBusy() {
        return getStateDef().locksMovement;
    }

    /**
     * Swaps her active combat goal based on what's in her mainhand:
     * bow -> RangedBowAttackGoal, anything else (including empty hand
     * or a sword) -> MeleeAttackGoal. Mirrors how vanilla Skeletons/
     * Piglins switch between ranged and melee based on their held item.
     */
    private void reassessWeaponGoal() {
        if (currentWeaponGoal != null) {
            this.goalSelector.removeGoal(currentWeaponGoal);
        }
        Goal raw = (this.getMainHandItem().getItem() instanceof BowItem) ? bowGoalRaw : meleeGoalRaw;
        currentWeaponGoal = new IdleGatedGoal(this, raw);
        this.goalSelector.addGoal(2, currentWeaponGoal);
    }

    @Override
    public void setItemSlot(EquipmentSlotType slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!this.level.isClientSide && slot == EquipmentSlotType.MAINHAND) {
            reassessWeaponGoal();
        }
    }

    // ── Data ──────────────────────────────────────────────────────────────────

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, DEFAULT_STATE_ID);
        this.entityData.define(FOLLOWING, false);
        this.entityData.define(DRESSED, false);
        this.entityData.define(ARMORED, false);
        this.entityData.define(PARTNER_FORCED, false);
        this.entityData.define(DOWNED, false);
        this.entityData.define(PARTNER_SKIN_KEY, "");
    }

    @Override
    public boolean removeWhenFarAway(double dist) { return false; }

    @Override
    public boolean isInvulnerableTo(DamageSource source) { return isDowned(); }

    // ── Following ─────────────────────────────────────────────────────────────

    public boolean isFollowing() { return this.entityData.get(FOLLOWING); }

    public void setFollowing(boolean following) {
        this.entityData.set(FOLLOWING, following);
        if (!following) {
            this.getNavigation().stop();
        }
    }

    // ── Dress / strip ─────────────────────────────────────────────────────────

    /** True = wearing the dressed model, false = nude model. See GirlModel.java. */
    public boolean isDressed() { return this.entityData.get(DRESSED); }

    public void setDressed(boolean dressed) {
        this.entityData.set(DRESSED, dressed);
    }

    // ── Armor ─────────────────────────────────────────────────────────────────

    /** True = armor bones visible on the dressed model. See GirlRenderer.ARMOR_BONES. */
    public boolean isArmored() { return this.entityData.get(ARMORED); }

    public void setArmored(boolean armored) {
        this.entityData.set(ARMORED, armored);
    }

    // ── Partner rig test override ────────────────────────────────────────────

    /**
     * Debug/test toggle: forces the embedded "steve" partner rig visible
     * (with the player texture) regardless of the current pose's
     * showPartnerRig flag — see GuiGirlInteract's "Partner Rig" button.
     * GirlRenderer OR's this with StateDefinition.showPartnerRig.
     */
    public boolean isPartnerForced() { return this.entityData.get(PARTNER_FORCED); }

    public void setPartnerForced(boolean forced) {
        this.entityData.set(PARTNER_FORCED, forced);
    }

    // ── Downed / HP system ───────────────────────────────────────────────────

    /** True while she's on the invincible "downed" recovery sequence (see hurt() below). */
    public boolean isDowned() { return this.entityData.get(DOWNED); }

    private void setDowned(boolean downed) { this.entityData.set(DOWNED, downed); }

    /** Registry path (e.g. "zombie") of whichever mob's identity the partner rig is currently wearing, or "" for the default player skin. */
    public String getPartnerSkinKey() { return this.entityData.get(PARTNER_SKIN_KEY); }

    private void setPartnerSkinKey(String key) { this.entityData.set(PARTNER_SKIN_KEY, key == null ? "" : key); }

    /**
     * Intercepts what would otherwise be a killing blow: instead of dying,
     * she's put into the invincible DOWNED state until the encounter
     * naturally finishes and she returns to IDLE (see tick()'s PLAY_ONCE/
     * followUp handling and recoverFromDowned()). Damage that wouldn't be
     * lethal is applied normally via super.hurt() so knockback, hurt
     * sound/animation, etc. still work as usual.
     *
     * She's no longer unconditionally invulnerable (see isInvulnerableTo
     * above) — that flag now only covers the downed/recovery window itself.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.level.isClientSide) return false;
        if (isDowned()) return false; // fully invincible mid-recovery

        float prospectiveHealth = this.getHealth() - amount;
        if (prospectiveHealth <= 0.0F) {
            triggerDowned(source);
            return true;
        }
        return super.hurt(source, amount);
    }

    private void triggerDowned(DamageSource source) {
        this.setHealth(1.0F); // keep her alive at minimal HP through the sequence
        setDowned(true);
        downedTicks = 0;

        Entity attacker = source.getEntity();
        MonsterEntity mob = (attacker instanceof MonsterEntity)
            ? (MonsterEntity) attacker
            : findNearestMonster(MOB_INTERACT_RADIUS);

        if (mob != null) {
            applyMobIdentity(mob);
        } else {
            setPartnerSkinKey("");
            setState("DOWNED");
        }
    }

    /** Substring matched against StateDefinition.animName to find poses to reuse for a mob encounter — see applyMobIdentity(). */
    private static final String MOB_ENCOUNTER_ANIM_KEYWORD = "start";

    /**
     * Adopts a mob's identity for the duration of the downed sequence: the
     * partner-rig texture switches to textures/player/<mobName>.png if
     * that file exists (falls back to the default player skin otherwise —
     * see GirlModel), and one of the existing "start" poses (COWGIRL_START,
     * MISSIONARY_START, ...) is picked at random to reuse instead of the
     * generic DOWNED animation, if any exist. No new states/animations
     * are required per mob type — only a matching player-skin PNG if you
     * want her to actually look like that mob during the scene.
     */
    private void applyMobIdentity(MonsterEntity mob) {
        String key = registryKeyOf(mob);
        setPartnerSkinKey(key);
        attachMob(mob);

        List<String> matches = StateConfig.getIdsWithAnimationContaining(MOB_ENCOUNTER_ANIM_KEYWORD);
        if (matches.isEmpty()) {
            if (!getStateId().equals("DOWNED")) setState("DOWNED");
            return;
        }
        if (!matches.contains(getStateId())) {
            setState(matches.get(random.nextInt(matches.size())));
        }
    }

    /**
     * Hides the interacting mob and freezes its AI so it visually
     * "disappears" and is represented entirely by the reskinned partner
     * rig instead of standing there as a separate, still-visible entity.
     * Pinned to her exact position each tick while attached (see tick()).
     */
    private void attachMob(MonsterEntity mob) {
        if (attachedMob == mob) return; // already attached to this one
        if (attachedMob != null) detachMob(); // release whichever mob we had before

        attachedMob = mob;
        mob.setInvisible(true);
        mob.setNoAi(true);
        mob.setSilent(true);
        mob.setPos(this.getX(), this.getY(), this.getZ());
    }

    /** Restores whichever mob is currently attached (if it's still alive) and clears the reference. */
    private void detachMob() {
        if (attachedMob == null) return;
        if (attachedMob.isAlive()) {
            attachedMob.setInvisible(false);
            attachedMob.setNoAi(false);
            attachedMob.setSilent(false);
        }
        attachedMob = null;
    }

    private static String registryKeyOf(Entity entity) {
        ResourceLocation id = ForgeRegistries.ENTITIES.getKey(entity.getType());
        return id != null ? id.getPath() : "unknown";
    }

    private MonsterEntity findNearestMonster(double radius) {
        List<MonsterEntity> nearby =
            this.level.getEntitiesOfClass(MonsterEntity.class, this.getBoundingBox().inflate(radius));
        MonsterEntity closest = null;
        double closestDistSq = Double.MAX_VALUE;
        for (MonsterEntity m : nearby) {
            double d = m.distanceToSqr(this);
            if (d < closestDistSq) {
                closestDistSq = d;
                closest = m;
            }
        }
        return closest;
    }

    /** Un-targets any nearby hostile mob currently targeting her — see tick(). */
    private void clearHostileTargetsOnMe() {
        List<MonsterEntity> nearby =
            this.level.getEntitiesOfClass(MonsterEntity.class, this.getBoundingBox().inflate(MOB_IGNORE_RADIUS));
        for (MonsterEntity m : nearby) {
            if (m.getTarget() == this) {
                m.setTarget(null);
            }
        }
    }

    private void recoverFromDowned() {
        this.setHealth(this.getMaxHealth()); // full heal, 20 hearts
        setDowned(false);
        setPartnerSkinKey("");
        downedTicks = 0;
        detachMob(); // release the interacting mob back into the world
        setState("IDLE");
    }

    // ── Interaction ───────────────────────────────────────────────────────────

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        boolean isWeapon = heldItem.getItem() instanceof SwordItem || heldItem.getItem() instanceof BowItem;

        // Shift + right-click while holding a weapon = equip it to her mainhand.
        if (player.isShiftKeyDown() && isWeapon) {
            if (!this.level.isClientSide) {
                ItemStack toEquip = heldItem.copy();
                toEquip.setCount(1);
                this.setItemSlot(EquipmentSlotType.MAINHAND, toEquip);
                if (!player.abilities.instabuild) {
                    heldItem.shrink(1);
                }
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }

        // Otherwise: open the pose/action GUI as before.
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
            this.getNavigation().stop(); // cancel any in-progress wander/follow/combat path immediately
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

    // ── Combat ────────────────────────────────────────────────────────────────

    /** Triggered by MeleeAttackGoal via vanilla's attack pipeline. */
    @Override
    public boolean doHurtTarget(Entity target) {
        boolean result = super.doHurtTarget(target);
        if (result && !this.level.isClientSide) {
            String[] swings = { "ATTACK0", "ATTACK1", "ATTACK2" };
            setState(swings[random.nextInt(swings.length)]);
        }
        return result;
    }

    /** Called directly by CustomBowAttackGoal when she's in range and lines up a shot. */
    public void shootArrowAt(LivingEntity target) {
        ArrowEntity arrow = new ArrowEntity(this.level, this);
        double dx = target.getX() - this.getX();
        double dy = target.getY(0.3333) - arrow.getY();
        double dz = target.getZ() - this.getZ();
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);
        arrow.shoot(dx, dy + horizontalDist * 0.2, dz, 1.6F, 14.0F - this.level.getDifficulty().getId() * 4.0F);
        arrow.setBaseDamage(2.0);

        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);

        if (!this.level.isClientSide) {
            setState("BOWCHARGE");
        }
    }

    // ── Tick ──────────────────────────────────────────────────────────────────

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (isBusy()) {
                this.getNavigation().stop(); // belt-and-braces: never let her drift while busy
                // Task: mobs ignore her while any animation (other than IDLE/WALK)
                // is playing — checked every 5 ticks rather than every tick to
                // keep the entity-scan cheap.
                if (this.tickCount % 5 == 0) {
                    clearHostileTargetsOnMe();
                }
            }

            if (isDowned()) {
                downedTicks++;
                // Keep the attached mob (if any) pinned to her position and
                // hidden for as long as the encounter lasts.
                if (attachedMob != null) {
                    if (!attachedMob.isAlive()) {
                        attachedMob = null; // it died/was removed elsewhere; drop the stale reference
                    } else {
                        attachedMob.setPos(this.getX(), this.getY(), this.getZ());
                    }
                }
                // Re-check for a nearby mob once a second so a mob that
                // wanders in after the downed sequence already started
                // (e.g. she was downed by fall damage, not an attack)
                // still gets picked up and swaps in its identity/animation.
                if (downedTicks % 20 == 0) {
                    MonsterEntity nearby = findNearestMonster(MOB_INTERACT_RADIUS);
                    if (nearby != null) {
                        applyMobIdentity(nearby);
                    }
                }
                // No fixed-timer recovery anymore: she stays downed/invincible
                // and ignored by mobs for as long as it takes — recovery only
                // happens when the encounter animation actually finishes and
                // she'd naturally return to IDLE (see the PLAY_ONCE/followUp
                // block below). If no mob ever triggers a "start" pose, she
                // stays in the generic DOWNED loop until one does.
                //
                // Deliberately NOT returning here — the followUp/duration
                // block below still needs to run so a mob-triggered pose
                // like COWGIRL_START can progress and eventually recover
                // instead of freezing on its last frame. The IDLE/WALK
                // auto-switch further down is a no-op in this branch anyway
                // since DOWNED and any mob-encounter pose are never IDLE/WALK.
            }

            StateDefinition current = getStateDef();

            // Auto-switch between IDLE and WALK based on actual horizontal
            // movement — scoped so it ONLY applies while she's currently in
            // one of those two "default" states. This means it never fights
            // a manually-triggered pose (HUG, COWGIRL_SLOW, ...) or a
            // combat animation (ATTACK0/1/2, BOWCHARGE) that happens to be
            // playing while she's also moving; those own their own state
            // via followUp/duration and are left alone here.
            if (current.id.equals(DEFAULT_STATE_ID) || current.id.equals("WALK")) {
                boolean moving = isMovingHorizontally();
                if (moving && current.id.equals(DEFAULT_STATE_ID)) {
                    setState("WALK");
                } else if (!moving && current.id.equals("WALK")) {
                    setState(DEFAULT_STATE_ID);
                }
            }

            if (current.loopType == StateDefinition.LoopType.PLAY_ONCE && current.followUpId != null) {
                animTicksInState++;
                if (animTicksInState >= animDurationTicks && animDurationTicks > 0) {
                    if (isDowned()) {
                        // The mob-encounter reaction animation finished
                        // playing — treat that as her returning to idle and
                        // recover here, rather than following into whatever
                        // loop state (e.g. COWGIRL_SLOW) the pose would
                        // normally chain into for a real player-driven scene.
                        recoverFromDowned();
                    } else {
                        setState(current.followUpId);
                    }
                }
            }
        }
    }

    /**
     * True if she's actually moving horizontally right now (walking,
     * following, chasing a combat target) — used to auto-switch between
     * IDLE and WALK. Checks her velocity vector rather than comparing
     * position deltas, since that's the standard signal vanilla itself
     * uses for movement-based animation state.
     */
    private boolean isMovingHorizontally() {
        net.minecraft.util.math.vector.Vector3d vel = this.getDeltaMovement();
        double horizontalSpeedSq = vel.x * vel.x + vel.z * vel.z;
        return horizontalSpeedSq > 0.0025; // ~0.05 blocks/tick — below walk speed, above idle jitter
    }

    // ── NBT ───────────────────────────────────────────────────────────────────

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("GirlState", getStateId());
        nbt.putBoolean("Following", isFollowing());
        nbt.putBoolean("Dressed", isDressed());
        nbt.putBoolean("Armored", isArmored());
        nbt.putBoolean("PartnerForced", isPartnerForced());
        nbt.putBoolean("Downed", isDowned());
        nbt.putInt("DownedTicks", downedTicks);
        nbt.putString("PartnerSkinKey", getPartnerSkinKey());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("GirlState")) {
            setState(nbt.getString("GirlState")); // setState() already falls back to IDLE for unknown ids
        }
        if (nbt.contains("Following")) {
            setFollowing(nbt.getBoolean("Following"));
        }
        if (nbt.contains("Dressed")) {
            setDressed(nbt.getBoolean("Dressed"));
        }
        if (nbt.contains("Armored")) {
            setArmored(nbt.getBoolean("Armored"));
        }
        if (nbt.contains("PartnerForced")) {
            setPartnerForced(nbt.getBoolean("PartnerForced"));
        }
        if (nbt.contains("Downed")) {
            setDowned(nbt.getBoolean("Downed"));
        }
        if (nbt.contains("DownedTicks")) {
            downedTicks = nbt.getInt("DownedTicks");
        }
        if (nbt.contains("PartnerSkinKey")) {
            setPartnerSkinKey(nbt.getString("PartnerSkinKey"));
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
