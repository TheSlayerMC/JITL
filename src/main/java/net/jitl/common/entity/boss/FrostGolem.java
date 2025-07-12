package net.jitl.common.entity.boss;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class FrostGolem extends JBossEntity {

    public FrostGolem(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.FROZEN, 10);
    }

    @Override
    public boolean showName() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 4800));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_DAMAGE;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        this.level().broadcastEntityEvent(this, (byte)1);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        DamageSource damagesource = this.damageSources().mobAttack(this);
        boolean hurt = entity.hurtServer(level, damagesource, f1);
        if(hurt) {
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, 0.4F, 0.0D));
            if (this.level() instanceof ServerLevel serverlevel) {
                EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
            }
        }
        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return hurt;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if(id == 1) {
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        }
        super.handleEntityEvent(id);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.FROST_GOLEM_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.FROST_GOLEM_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.FROST_GOLEM_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.frost_golem.walk");
    private final RawAnimation ATTACK = RawAnimation.begin().thenLoop("animation.frost_golem.swing");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.frost_golem.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            }
            else if(isAttacking()) {
                return state.setAndContinue(ATTACK);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.FROZEN;
    }

    @Override
    public ResourceKey<LootTable> lootTable() {
        return JLootTables.FROST_GOLEM_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return false;
    }

}