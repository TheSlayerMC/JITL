package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class ConjuringProjectileEntity extends ThrowableProjectile {


    public ConjuringProjectileEntity(EntityType<ConjuringProjectileEntity> type, Level world) {
        super(type, world);
    }

    public ConjuringProjectileEntity(Level world, LivingEntity thrower) {
        super(JEntities.CONJURING_PROJECTILE_TYPE.get(), thrower, world);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = ParticleTypes.ITEM_SNOWBALL;

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if(entity instanceof LivingEntity && entity.hurt(DamageSource.thrown(this, this.getOwner()), getDamage())) {
            MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.POISON, 60);
            ((LivingEntity)entity).addEffect(effectInstance);
            entity.hurt(DamageSource.thrown(this, this.getOwner()), getDamage());
        }
    }

    private float getDamage() {
        return 5;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
