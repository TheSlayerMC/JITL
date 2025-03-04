package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class HellstoneEntity extends ThrowableProjectile {

    public HellstoneEntity(EntityType<HellstoneEntity> type, Level world) {
        super(type, world);
    }

    public HellstoneEntity(Level world, LivingEntity thrower) {
        super(JEntities.HELLSTONE_TYPE.get(), thrower, world);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if(pId == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.HELLSTONE_PROJECTILE.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult res) {
        super.onHitEntity(res);
        Entity entity = res.getEntity();
        if(entity instanceof LivingEntity && entity.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage())) {
            entity.setRemainingFireTicks(60);
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage());
        }
    }

    private float getDamage() {
        return 7;
    }

    @Override
    protected void onHit(@NotNull HitResult res) {
        super.onHit(res);
        if(!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder b) { }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }
}