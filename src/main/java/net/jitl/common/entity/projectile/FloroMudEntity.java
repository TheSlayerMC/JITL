package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JParticleManager;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class FloroMudEntity extends DamagingProjectileEntity implements ItemSupplier {

    public FloroMudEntity(EntityType<FloroMudEntity> type, Level world) {
        super(type, world);
    }

    public FloroMudEntity(EntityType<FloroMudEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public FloroMudEntity(Level world, LivingEntity thrower, float damage) {
        super(JEntities.FLORO_MUD_TYPE.get(), world, thrower, damage);
    }

    @Override
    public void onClientTick() {
        super.onClientTick();
        int count = 2;
        Vec3 vector3d = this.getDeltaMovement();
        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        for(int i = 0; i < count; ++i) {
            this.level().addParticle(JParticleManager.MUD.get(),
                    d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y + 0.25F,
                    d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        if(level() instanceof ServerLevel level) {
            if(target instanceof LivingEntity && target.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), getDamage())) {
                MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.SLOWNESS, 20);
                ((LivingEntity) target).addEffect(effectInstance);
            }
        }
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) {
            if(result.getType() != HitResult.Type.MISS) {
                level().playSound(null, BlockPos.containing(result.getLocation()), JSounds.MUD_BLOCK_BREAK.get(), SoundSource.AMBIENT, 1.0F, 1.5F);
            }
        } else {
            remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.03F;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.MUD_BALL.get());
    }
}