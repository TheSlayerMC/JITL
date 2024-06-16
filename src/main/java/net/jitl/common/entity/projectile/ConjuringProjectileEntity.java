package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
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

public class ConjuringProjectileEntity extends ThrowableProjectile {


    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, Level world) {
        super(type, world);
    }

    public ConjuringProjectileEntity(Level world, LivingEntity thrower) {
        super(JEntities.CONJURING_PROJECTILE_TYPE.get(), thrower, world);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.CONJURING.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if(entity instanceof LivingEntity && entity.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage())) {
            MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.POISON, 60);
            ((LivingEntity)entity).addEffect(effectInstance);
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage());
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
