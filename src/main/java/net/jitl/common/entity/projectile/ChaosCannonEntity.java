package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ChaosCannonEntity extends BouncingProjectileEntity {

    public ChaosCannonEntity(EntityType<ChaosCannonEntity> type, Level world) {
        super(type, world);
    }

    public ChaosCannonEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.CHAOS_CANNON.get(), damage, world, thrower);
        setPotionEffect(MobEffects.LEVITATION, 60);
    }
}