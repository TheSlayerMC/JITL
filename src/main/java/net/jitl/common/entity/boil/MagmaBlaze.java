package net.jitl.common.entity.boil;

import net.jitl.common.entity.base.JBlazeStyleEntity;
import net.jitl.common.entity.base.MobStats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class MagmaBlaze extends JBlazeStyleEntity {

    public MagmaBlaze(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.MAGMA_BLAZE_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.MAGMA_BLAZE_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.magma_blaze.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> state.setAndContinue(IDLE)));
    }
}