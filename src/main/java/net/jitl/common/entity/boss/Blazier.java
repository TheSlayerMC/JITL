package net.jitl.common.entity.boss;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.common.entity.base.JBossEntity;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.goal.IdleHealGoal;
import net.jitl.common.entity.nether.InfernoBlaze;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JLootTables;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
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
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.EnumSet;
import java.util.Objects;

public class Blazier extends JBossEntity {

    private final ServerBossEvent BOSS_INFO = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_6);
    private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.rl("textures/gui/bossbars/blazier.png"));
    private int spawnTimer;

    public Blazier(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.spawnTimer = 0;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new Blazier.BlazierAttackGoal(this));
        this.goalSelector.addGoal(1, new IdleHealGoal(this, 2400));
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
        return JSounds.BLAZIER_IDLE.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.BLAZIER_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return JSounds.BLAZIER_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getHealth() <= 250) {
            if(this.spawnTimer == 0 && !level().isClientSide()) {
                InfernoBlaze z = new InfernoBlaze(JEntities.INFERNO_BLAZE_TYPE.get(), this.level());
                double posX = position().x;
                double posY = position().y;
                double posZ = position().z;
                z.setPos(posX + 3, posY, posZ);
                InfernoBlaze z1 = new InfernoBlaze(JEntities.INFERNO_BLAZE_TYPE.get(), this.level());
                z1.setPos(posX - 3, posY, posZ);
                InfernoBlaze z2 = new InfernoBlaze(JEntities.INFERNO_BLAZE_TYPE.get(), this.level());
                z2.setPos(posX, posY, posZ + 3);
                InfernoBlaze z3 = new InfernoBlaze(JEntities.INFERNO_BLAZE_TYPE.get(), this.level());
                z3.setPos(posX, posY, posZ - 3);
                this.level().addFreshEntity(z);
                this.level().addFreshEntity(z1);
                this.level().addFreshEntity(z2);
                this.level().addFreshEntity(z3);
                this.spawnTimer = 500;
            }
            this.spawnTimer--;
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        this.BOSS_INFO.removePlayer(player);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        if(showBarWhenSpawned())
            this.BOSS_INFO.addPlayer(player);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 500)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    @Override
    public BossBarRenderer getBossBar() {
        return BOSS_BAR;
    }

    @Override
    public ServerBossEvent getEvent() {
        return BOSS_INFO;
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.blazier.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
                return state.setAndContinue(IDLE);
        }));
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level().getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.NETHER;
    }

    @Override
    public ResourceLocation lootTable() {
        return JLootTables.OKOLOO_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return false;
    }

    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }


    static class BlazierAttackGoal extends Goal {
        private final Blazier blazier;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public BlazierAttackGoal(Blazier blazier) {
            this.blazier = blazier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.blazier.getTarget();
            return livingentity != null && livingentity.isAlive() && this.blazier.canAttack(livingentity);
        }

        @Override
        public void start() {
            this.attackStep = 0;
        }

        @Override
        public void stop() {
            this.lastSeen = 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.attackTime--;
            LivingEntity livingentity = this.blazier.getTarget();
            if (livingentity != null) {
                boolean flag = this.blazier.getSensing().hasLineOfSight(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    this.lastSeen++;
                }

                double d0 = this.blazier.distanceToSqr(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.blazier.doHurtTarget(livingentity);
                    }

                    this.blazier.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.blazier.getX();
                    double d2 = livingentity.getY(0.5D) - this.blazier.getY(0.5D);
                    double d3 = livingentity.getZ() - this.blazier.getZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                        }

                        if (this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                            if (!this.blazier.isSilent()) {
                                this.blazier.level().levelEvent(null, 1018, this.blazier.blockPosition(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
                                LargeFireball fireball = new LargeFireball(this.blazier.level(), this.blazier, this.blazier.getRandom().triangle(d1, 2.297D * d4), d2, this.blazier.getRandom().triangle(d3, 2.297D * d4), 1);
                                fireball.setPos(fireball.getX(), this.blazier.getY(0.5D) + 0.5D, fireball.getZ());
                                this.blazier.level().addFreshEntity(fireball);
                            }
                        }
                    }

                    this.blazier.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.blazier.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.blazier.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
}