package net.jitl.common.entity.overworld;

import net.jitl.common.entity.base.JTamableEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Ferret extends JTamableEntity {

    public Ferret(EntityType<? extends JTamableEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.FERRET.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.FERRET_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.FERRET_DEATH.get();
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.PET_ROBOT_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.PET_ROBOT_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.FAST_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.ferret.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.ferret.idle");
    private final RawAnimation SIT = RawAnimation.begin().thenPlayAndHold("animation.ferret.sit");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else if(isInSittingPose()) {
                return state.setAndContinue(SIT);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public int setBaseHealth() {
        return MobStats.PET_ROBOT_HEALTH;
    }

    @Override
    public int setTameHealth() {
        return MobStats.PET_ROBOT_HEALTH;
    }

    @Override
    public Item getTameItem() {
        return JItems.PET_FOOD.get();
    }
}