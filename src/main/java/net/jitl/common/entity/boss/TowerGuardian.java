package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.base.AnimatableMonster;
import net.jitl.common.entity.base.IDontAttackWhenPeaceful;
import net.jitl.common.entity.base.JBossInfo;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.core.helper.JMusic;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class TowerGuardian extends AnimatableMonster implements IJourneyBoss, IDontAttackWhenPeaceful {

    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.rl("textures/gui/bossbars/tower_guardian.png"));
    private static final JMusic BOSS_TRACK = new JMusic(JSounds.TEMPLE_GUARDIAN_MUSIC.get(), 2, 0, 0);

    public TowerGuardian(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.level.broadcastEntityEvent(this, (byte)1);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float explosionRadius = 3;
        double particleWidth = 4;
        Vec3 vec3 = this.getDeltaMovement();
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean hurt = entity.hurt(DamageSource.mobAttack(this), f1);
        int x = Mth.floor(entity.getX());
        int y = Mth.floor(entity.getY() - (double)0.2F);
        int z = Mth.floor(entity.getZ());
        BlockPos blockpos = new BlockPos(x, y, z);
        BlockState blockstate = this.level.getBlockState(blockpos);
        if(hurt) {
            if(random.nextInt(15) == 0) {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), explosionRadius, Level.ExplosionInteraction.NONE);
            }
            if(blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                for(int i = 0; i < 50; i++) {
                    this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate).setPos(blockpos), this.getX() + (this.random.nextDouble() - 0.5D) * particleWidth, this.getY() + 0.1D, this.getZ() + (this.random.nextDouble() - 0.5D) * particleWidth, vec3.x * -4.0D, 1.5D, vec3.z * -4.0D);
                }
            }
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
    public JMusic getBossMusic() {
        return BOSS_TRACK;
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.tower_guardian.walk");
    private final RawAnimation ATTACK = RawAnimation.begin().thenLoop("animation.tower_guardian.smash");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.tower_guardian.idle");

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
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level.getDifficulty() != Difficulty.PEACEFUL;
    }
}