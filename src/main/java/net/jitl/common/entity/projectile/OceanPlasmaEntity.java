package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class OceanPlasmaEntity extends BouncingProjectileEntity {

    public OceanPlasmaEntity(EntityType<OceanPlasmaEntity> type, Level world) {
        super(type, world);
    }

    public OceanPlasmaEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.OCEAN_PLASMA_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.INSTANT_DAMAGE, 60);
    }
}