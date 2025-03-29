package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BouncingDivinityEntity extends BouncingProjectileEntity {

    public BouncingDivinityEntity(EntityType<BouncingDivinityEntity> type, Level world) {
        super(type, world);
    }

    public BouncingDivinityEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.BOUNCING_DIVINITY_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.MINING_FATIGUE, 100);
    }
}