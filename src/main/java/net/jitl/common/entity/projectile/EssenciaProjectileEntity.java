package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class EssenciaProjectileEntity extends ThrowableProjectile {

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, Level world) {
        super(type, world);
    }

    public EssenciaProjectileEntity(Level world, LivingEntity thrower) {
        super(JEntities.ESSENCIA_PROJECTILE_TYPE.get(), thrower, world);
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
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE.get(), level);
        essenciaBoltEntity.setPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
        essenciaBoltEntity.setARGB(0xff4800);
        level.addFreshEntity(essenciaBoltEntity);
    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }

    @Override
    protected void defineSynchedData() { }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}