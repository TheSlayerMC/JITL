package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BouncingOvergrownEntity extends BouncingProjectileEntity {

    public BouncingOvergrownEntity(EntityType<BouncingOvergrownEntity> type, Level world) {
        super(type, world);
    }

    public BouncingOvergrownEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.BOUNCING_OVERGROWN_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.BLINDNESS, 100);
    }
}