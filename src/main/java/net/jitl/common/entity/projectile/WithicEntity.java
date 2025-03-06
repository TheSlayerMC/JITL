package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class WithicEntity extends AbstractHomingEntity {

    public WithicEntity(EntityType<WithicEntity> type, Level world) {
        super(type, world);
    }

    public WithicEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.WITHIC_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.WITHER, 40);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}