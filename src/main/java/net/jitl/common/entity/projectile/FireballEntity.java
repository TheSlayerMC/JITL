package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class FireballEntity extends ThrowableProjectile {

    public FireballEntity(EntityType<FireballEntity> type, Level world) {
        super(type, world);
    }

    public FireballEntity(Level world, LivingEntity thrower) {
        super(JEntities.FIREBALL_TYPE.get(), world);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.HELLSTONE.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(level() instanceof ServerLevel level) {
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity && entity.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), getDamage())) {
                entity.setRemainingFireTicks(20);
                entity.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), getDamage());
            }
        }
    }

    private float getDamage() {
        return 5;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }

}
