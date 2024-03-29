package net.jitl.common.entity.senterian;

import net.jitl.common.entity.base.JMonsterEntity;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class MiniSentryWalker extends JMonsterEntity {

    public MiniSentryWalker(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.mini_sentry_walker.idle");
    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.mini_sentry_walker.walk");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public void die(DamageSource d) {
        super.die(d);
        if(!level().isClientSide) {
            for(int i = 0; i < 1 + random.nextInt(4); i++) {
                this.level().addFreshEntity(new Sentacoin(JEntities.SENTACOIN_TYPE.get(), this));
            }
            if(random.nextInt(10) == 0) {
                this.level().addFreshEntity(new Sentacoin(JEntities.SENTACOIN_BAG_TYPE.get(), this));
            }
        }
    }
}