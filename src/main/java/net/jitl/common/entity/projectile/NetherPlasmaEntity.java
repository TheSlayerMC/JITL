package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class NetherPlasmaEntity extends JThrowableProjectile {

    public NetherPlasmaEntity(EntityType<NetherPlasmaEntity> type, Level world) {
        super(type, world);
    }

    public NetherPlasmaEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.NETHER_PLASMA_TYPE.get(), damage, world, thrower);
        setFire(60);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.DOOMSBRINGER.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }
}