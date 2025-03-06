package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CrystallizedEntity extends AbstractHomingEntity {

    public CrystallizedEntity(EntityType<CrystallizedEntity> type, Level world) {
        super(type, world);
    }

    public CrystallizedEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.CRYSTALLIZED_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.GLOWING, 100);
        setPotionEffect(MobEffects.MOVEMENT_SLOWDOWN, 100);
    }

    @Override
    public int getHomingRadius() {
        return 4;
    }
}