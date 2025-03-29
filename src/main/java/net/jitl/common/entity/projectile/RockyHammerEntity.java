package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class RockyHammerEntity extends AbstractHomingEntity {

    public RockyHammerEntity(EntityType<RockyHammerEntity> type, Level world) {
        super(type, world);
    }

    public RockyHammerEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.ROCKY_HAMMER_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.MINING_FATIGUE, 100);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}