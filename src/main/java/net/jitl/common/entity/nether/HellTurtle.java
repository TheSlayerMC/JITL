package net.jitl.common.entity.nether;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JMonsterEntity;
import net.jitl.common.entity.base.JNeutralMonster;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class HellTurtle extends JNeutralMonster {

    public HellTurtle(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.NETHER, 5F);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.TURTLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.TURTLE_HURT.get();
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.HELL_TURTLE_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.HELL_TURTLE_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.HELL_TURTLE_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.hell_turtle.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.hell_turtle.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            }else {
                return state.setAndContinue(IDLE);
            }
        }));
    }
}