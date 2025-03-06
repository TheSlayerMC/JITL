package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EarthenEntity extends AbstractHomingEntity {

    public EarthenEntity(EntityType<EarthenEntity> type, Level world) {
        super(type, world);
    }

    public EarthenEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.EARTHEN_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.LEVITATION, 100);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}