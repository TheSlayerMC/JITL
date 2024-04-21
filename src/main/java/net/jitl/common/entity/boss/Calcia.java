package net.jitl.common.entity.boss;

import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.internal.JLootTables;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class Calcia extends JBossEntity {

    private int FIRE_TICK = 0;
    private static final EntityDataAccessor<Boolean> IS_INVISIBLE = SynchedEntityData.defineId(Calcia.class, EntityDataSerializers.BOOLEAN);

    public Calcia(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_INVISIBLE, false);
    }

    public boolean isInvisible() {
        return this.entityData.get(IS_INVISIBLE);
    }

    public void setInvisible(boolean isInvis) {
        this.entityData.set(IS_INVISIBLE, isInvis);
    }


    @Override
    public void tick() {
        super.tick();
        int FIRE_MAX = 400;
        if(FIRE_MAX == this.FIRE_TICK && this.FIRE_TICK != 0) {
            this.setInvisible(true);
            this.FIRE_TICK = 0;
        } else {
            this.FIRE_TICK++;
        }

        int FIRE_MAX_2 = 300;
        if(FIRE_MAX_2 == this.FIRE_TICK && this.FIRE_TICK != 0) {
            this.setInvisible(false);
            this.FIRE_TICK = 0;
        } else {
            this.FIRE_TICK++;
        }

        if(isInvisible()) {
            if(this.level().isClientSide) {
                for (int i = 0; i < 5; i++)
                    this.level().addParticle(ParticleTypes.ENCHANT, this.position().x + (this.random.nextDouble() - 0.5D), this.position().y + this.random.nextDouble(), this.position().z + (this.random.nextDouble() - 0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }

            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 25));

            LivingEntity entity = getLastAttacker();
            if(entity != null)
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 25));
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 1200));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.CALCIA_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.CALCIA_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.CALCIA_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.calcia.idle");
    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.calcia.walk");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            }
            else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level().getDifficulty() != Difficulty.PEACEFUL;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.CALCIA_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return JSounds.CALCIA_HURT.get();
    }

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.NETHER;
    }

    @Override
    public ResourceLocation lootTable() {
        return JLootTables.CALCIA_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return true;
    }
}