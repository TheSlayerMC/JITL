package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FlamingHammerEntity extends AbstractHomingEntity {

    public FlamingHammerEntity(EntityType<FlamingHammerEntity> type, Level world) {
        super(type, world);
    }

    public FlamingHammerEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.FLAMING_TYPE.get(), damage, world, thrower);
        setFire(60);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}