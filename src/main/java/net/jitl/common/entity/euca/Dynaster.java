package net.jitl.common.entity.euca;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JNeutralMonster;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Dynaster extends JNeutralMonster {

    public Dynaster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.EUCA, 5F);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.DYNASTER_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.DYNASTER_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.DYNASTER.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.DYNASTER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.DYNASTER_DEATH.get();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.dynaster.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> state.setAndContinue(IDLE)));
    }
}