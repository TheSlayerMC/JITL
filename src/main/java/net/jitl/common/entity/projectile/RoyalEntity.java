package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class RoyalEntity extends AbstractHomingEntity {

    public RoyalEntity(EntityType<RoyalEntity> type, Level world) {
        super(type, world);
    }

    public RoyalEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.ROYAL_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.SLOWNESS, 100);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}