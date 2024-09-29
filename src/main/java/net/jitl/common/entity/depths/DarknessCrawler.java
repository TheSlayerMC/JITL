package net.jitl.common.entity.depths;

import net.jitl.client.knowledge.EnumKnowledge;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class DarknessCrawler extends JTamableEntity {

    public DarknessCrawler(EntityType<? extends JTamableEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.DEPTHS, 5F, true);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.DARKNESS_CRAWLER_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.DARKNESS_CRAWLER_DAMAGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.DEPTHS_HUNTER.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.SPIKED_BEAST_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.SPIKED_BEAST_HURT.get();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.darkness_crawler.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.darkness_crawler.idle");
    private final RawAnimation SIT = RawAnimation.begin().thenPlayAndHold("animation.darkness_crawler.sit");

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
        return MobStats.ROC_HEALTH;
    }

    @Override
    public int setTameHealth() {
        return MobStats.TAMED_ROC_HEALTH;
    }

    @Override
    public Item getTameItem() {
        return JItems.BEASTLY_STOMACH.get();
    }
}