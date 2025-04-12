package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractHomingEntity extends JThrowableProjectile {

    private LivingEntity target = null;

    public AbstractHomingEntity(EntityType<? extends AbstractHomingEntity> type, Level world) {
        super(type, world);
    }

    public AbstractHomingEntity(EntityType<? extends ThrowableProjectile> e, int damage, Level world, LivingEntity thrower) {
        super(e, damage, world, thrower);
        setOwner(thrower);
    }

    @Override
    public void tick() {
        super.tick();
        List<LivingEntity> mobs = level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(getHomingRadius(), getHomingRadius(), getHomingRadius()));
        boolean findNewTarget = target == null || target.isDeadOrDying();
        for(int i = 0; i < 4; i++)
            this.level().addParticle(JParticleManager.WIZARDS_STAR.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        for(LivingEntity e : mobs) {
            if(e != getOwner()) {
                float targetDist = target == null ? 0 : distanceTo(target);
                float compareDist = distanceTo(e);
                if(findNewTarget && (target == null || compareDist < targetDist)) target = e;
            }
        }
        if(target != null && target != getOwner()) {
            Vec3 dir = new Vec3(target.xo - xo, (target.yo + target.getEyeHeight()) - yo, target.zo - zo).normalize();
            setDeltaMovement(dir.x / 1.25, dir.y / 1.25, dir.z / 1.25);
        }
        if(tickCount > 100) discard();
    }

    public abstract int getHomingRadius();

    @Override
    public boolean isNoGravity() {
        return true;
    }
}