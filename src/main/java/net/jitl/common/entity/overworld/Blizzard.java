package net.jitl.common.entity.overworld;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JBlazeStyleEntity;
import net.jitl.common.entity.base.MobStats;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Blizzard extends JBlazeStyleEntity {

    public Blizzard(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.OVERWORLD, 5F);
        canSpawnSmoke(false);
    }

    @Override
    public void aiStep() {
        if(this.level().isClientSide) {
            if(this.random.nextInt(24) == 0 && !this.isSilent())
                this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);

            for(int i = 0; i < 1; i++) {
                this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, this.getRandomX(0.7D), this.getRandomY(), this.getRandomZ(0.7D), 0.0D, 0.0D, 0.0D);
            }
        }
        super.aiStep();
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.BLIZZARD_HEALTH)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.blizzard.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> state.setAndContinue(IDLE)));
    }
}