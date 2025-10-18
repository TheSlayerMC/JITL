package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BouncingProjectileEntity extends JThrowableProjectile {

    private int bounces = 0;

    public BouncingProjectileEntity(EntityType<? extends BouncingProjectileEntity> type, Level world) {
        super(type, world);
    }

    public BouncingProjectileEntity(EntityType<? extends BouncingProjectileEntity> type, int dam, Level world, LivingEntity thrower) {
        super(type, dam, world, thrower);
        setBouncy();
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if(pId == 3) {
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.CLOUDIA_PORTAL.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onHitBlock(BlockHitResult result) {
        Vec3 vec = getDeltaMovement();
        double x = vec.x, y = vec.y, z = vec.z;
        if(result.getDirection() == Direction.DOWN || result.getDirection() == Direction.UP)
            lerpMotion(new Vec3(x * 0.8, y * -0.8, z * 0.8));
        else if(result.getDirection() == Direction.EAST || result.getDirection() == Direction.WEST)
            lerpMotion(new Vec3(x * -0.8, y * 0.8, z * 0.8));
        else if(result.getDirection() == Direction.NORTH || result.getDirection() == Direction.SOUTH)
            lerpMotion(new Vec3(x * 0.8, y * 0.8, z * -0.8));
        if(this.bounces > 6) discard();
        this.bounces++;
    }
}