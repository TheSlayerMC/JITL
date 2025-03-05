package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EyeBlasterEntity extends JThrowableProjectile {

    public EyeBlasterEntity(EntityType<EyeBlasterEntity> type, Level world) {
        super(type, world);
    }

    public EyeBlasterEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.EYE_BLASTER_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.HARM, 60);
        setFire(60);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.HELLSTONE_PROJECTILE.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }
}