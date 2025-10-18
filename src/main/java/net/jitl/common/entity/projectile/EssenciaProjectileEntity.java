package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class EssenciaProjectileEntity extends JThrowableProjectile {

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, Level world) {
        super(type, world);
    }

    public EssenciaProjectileEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.ESSENCIA_PROJECTILE_TYPE.get(), damage, world, thrower);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.ESSENCIA_LIGHTNING.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE.get(), level());
        essenciaBoltEntity.setPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
        essenciaBoltEntity.setARGB(0xff4800);
        this.level().addFreshEntity(essenciaBoltEntity);
    }

    @Override
    protected void onHit(@NotNull HitResult r) {
        super.onHit(r);
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.003F;
    }

}