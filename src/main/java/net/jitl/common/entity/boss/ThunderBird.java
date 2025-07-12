package net.jitl.common.entity.boss;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.internal.JLootTables;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class ThunderBird extends JBossEntity {

    public ThunderBird(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.DEPTHS, 10);
    }

    @Override
    public boolean showName() {
        return true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 4800));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.OKOLOO.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.OKOLOO_HURT.get();
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.THUNDERBIRD_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.THUNDERBIRD_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.thunder_bird.idle");
    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.thunder_bird.walk");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
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

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.DEPTHS;
    }

    @Override
    public ResourceKey<LootTable> lootTable() {
        return JLootTables.THUNDER_BIRD_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return true;
    }
}