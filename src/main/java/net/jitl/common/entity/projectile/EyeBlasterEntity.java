package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EyeBlasterEntity extends BouncingProjectileEntity {

    public EyeBlasterEntity(EntityType<EyeBlasterEntity> type, Level world) {
        super(type, world);
    }

    public EyeBlasterEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.EYE_BLASTER_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.INSTANT_DAMAGE, 60);
        setFire(60);
    }
}