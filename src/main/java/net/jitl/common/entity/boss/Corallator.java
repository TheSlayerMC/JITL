package net.jitl.common.entity.boss;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JFlyingBossEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.AttackWhenDifficultGoal;
import net.jitl.common.entity.projectile.FloroMudEntity;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Corallator extends JFlyingBossEntity implements RangedAttackMob {

    public Corallator(EntityType<? extends Corallator> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.EUCA, 10);
    }

    @Override
    public boolean showName() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void addGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new AttackWhenDifficultGoal(this, this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.WITHER_HURT;
    }


    private double getHeadX(int pHead) {
        if (pHead <= 0) {
            return this.getX();
        } else {
            float f = (this.yBodyRot + (float)(180 * (pHead - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);
            return this.getX() + (double)f1 * 1.3D;
        }
    }

    private double getHeadY(int pHead) {
        return pHead <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
    }

    private double getHeadZ(int pHead) {
        if (pHead <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (pHead - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            return this.getZ() + (double)f1 * 1.3D;
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        this.performRangedAttack(0);
    }

    private void performRangedAttack(int pHead) {
        if (!this.isSilent()) {
            this.level().levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double d0 = this.getHeadX(pHead);
        double d1 = this.getHeadY(pHead);
        double d2 = this.getHeadZ(pHead);

        FloroMudEntity projectile = new FloroMudEntity(this.level(), this, 5);
        projectile.setPosRaw(d0, d1, d2);

        this.level().addFreshEntity(projectile);
    }

    @Override
    public boolean despawnInPeaceful() {
        return false;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.CORALLATOR_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.CORALLATOR_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_BOSS_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.corallator.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {

                return state.setAndContinue(IDLE);

        }));
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity living) {
        return level().getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    protected @Nullable BossCrystal.Type getDeathCrystalType() {
        return BossCrystal.Type.EUCA;
    }

    @Override
    public ResourceKey<LootTable> lootTable() {
        return JLootTables.CORALLATOR_CRYSTAL;
    }

    @Override
    public boolean showBarWhenSpawned() {
        return true;
    }
}