package net.jitl.common.entity.euca;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JTamableEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class EucaHopper extends JTamableEntity {

    public EucaHopper(EntityType<? extends EucaHopper> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.EUCA, 5F, true);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.EUCA_HOPPER_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.EUCA_HOPPER_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.euca_hopper.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.euca_hopper.idle");
    private final RawAnimation SIT = RawAnimation.begin().thenPlayAndHold("animation.euca_hopper.sit");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
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
        return MobStats.EUCA_HOPPER_HEALTH;
    }

    @Override
    public int setTameHealth() {
        return MobStats.EUCA_HOPPER_HEALTH;
    }

    @Override
    public Item getTameItem() {
        return JItems.EUCA_MEAT.get();
    }

}
