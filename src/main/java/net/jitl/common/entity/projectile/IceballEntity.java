package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class IceballEntity extends JThrowableProjectile {

    public IceballEntity(EntityType<IceballEntity> type, Level world) {
        super(type, world);
    }

    public IceballEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.ICEBALL_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.MOVEMENT_SLOWDOWN, 60);
        setPotionEffect(MobEffects.DIG_SLOWDOWN, 60);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.ICEBALL.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }
}