package net.jitl.common.entity.corba;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JMonsterEntity;
import net.jitl.common.entity.base.JNeutralMonster;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.helper.MathHelper;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class TreeGolem extends JNeutralMonster {

    public TreeGolem(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setKnowledge(EnumKnowledge.CORBA, 5F);
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.TREE_GOLEM_HEALTH)
                .add(Attributes.ATTACK_DAMAGE, MobStats.TREE_GOLEM_ATTACK)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.TREE_GOLEM_SPEED).build();
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.BUSH.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.BUSH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.BUSH_DEATH.get();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.tree_golem.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.tree_golem.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        this.level().broadcastEntityEvent(this, (byte)1);
        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int)damage > 0 ? damage / 2.0F + (float)this.random.nextInt((int)damage) : damage;
        boolean hurt = entity.hurtServer(level, this.damageSources().mobAttack(this), f1);
        if(hurt) {
            entity.setDeltaMovement(entity.getDeltaMovement().add((double) (-MathHelper.sin(yRotO * (float) Math.PI / 180.0F)) * 4, 0.1D, (double) (MathHelper.cos(yRotO * (float) Math.PI / 180.0F)) * 4));
        }
        return hurt;
    }
}