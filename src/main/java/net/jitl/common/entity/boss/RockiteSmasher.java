package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.base.AnimatableMonster;
import net.jitl.common.entity.base.IDontAttackWhenPeaceful;
import net.jitl.common.entity.base.JBossInfo;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.init.JITL;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class RockiteSmasher extends AnimatableMonster implements IJourneyBoss, IDontAttackWhenPeaceful {

    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
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
        this.level.broadcastEntityEvent(this, (byte)1);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean hurt = entity.hurt(DamageSource.mobAttack(this), f1);
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
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        JBossInfo.addInfo(player, BOSS_INFO, this);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        JBossInfo.removeInfo(player, BOSS_INFO, this);
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving() && !isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.walk", true));
            return PlayState.CONTINUE;
        }

        if(isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.swing", false));
            return PlayState.CONTINUE;
        }

        if(!isAttacking())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rockite_smasher.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level.getDifficulty() != Difficulty.PEACEFUL;
    }
}