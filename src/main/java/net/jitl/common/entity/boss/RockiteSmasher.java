package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
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
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.Objects;

public class RockiteSmasher extends JBossEntity {

    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.rl("textures/gui/bossbars/rockite_smasher.png"));

    public RockiteSmasher(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 1200));
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
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if(source.getEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() instanceof PickaxeItem) {
                return super.hurt(source, amount);
            }
        }
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.level().broadcastEntityEvent(this, (byte)1);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean hurt = entity.hurt(this.damageSources().mobAttack(this), f1);
        if(hurt) {
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, 0.4F, 0.0D));
            this.doEnchantDamageEffects(this, entity);
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
                .add(Attributes.MAX_HEALTH, MobStats.ROCKITE_SMASHER_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.ROCKITE_SMASHER_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.ROCKITE_SMASHER_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.rockite_smasher.walk");
    private final RawAnimation ATTACK = RawAnimation.begin().thenLoop("animation.rockite_smasher.swing");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.rockite_smasher.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
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
        return BossCrystal.Type.COMMON;
    }

    @Override
    public ResourceLocation lootTable() {
        return JLootTables.ROCKITE_SMASHER_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return false;
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public ServerBossEvent getEvent() {
        return BOSS_INFO;
    }
}