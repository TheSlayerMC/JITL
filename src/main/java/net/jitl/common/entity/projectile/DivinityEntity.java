package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DivinityEntity extends JThrowableProjectile {

    public DivinityEntity(EntityType<DivinityEntity> type, Level world) {
        super(type, world);
    }

    public DivinityEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.DIVINITY_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.MINING_FATIGUE, 100);
    }

    @Override
    public void tick() {
        super.tick();
        for(int i = 0; i < 1; ++i)
            this.level().addParticle(JParticleManager.WIZARDS_STAR.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }
}