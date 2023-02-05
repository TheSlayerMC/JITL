package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JDamageSources;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class DemonicBombEntity extends DamagingProjectileEntity implements ItemSupplier {

    public DemonicBombEntity(EntityType<DemonicBombEntity> type, Level world) {
        super(type, world);
    }

    public DemonicBombEntity(EntityType<DemonicBombEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public DemonicBombEntity(Level world, LivingEntity thrower, float damage) {
        super(JEntities.DEMONIC_BOMB_TYPE.get(), world, thrower, damage);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientTick() {
        super.onClientTick();

    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        if(target instanceof LivingEntity && target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage())) {
            target.hurt(JDamageSources.DEMONIC_BOMB, this.getDamage());
            if (!level.isClientSide) {
                int count = 2;
                Vec3 vector3d = this.getDeltaMovement();
                double d0 = this.getX() + vector3d.x;
                double d1 = this.getY() + vector3d.y;
                double d2 = this.getZ() + vector3d.z;
                for (int i = 0; i < count; ++i) {
                    this.level.addParticle(ParticleTypes.EFFECT,
                            d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                            d1 - vector3d.y + 0.25F,
                            d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                            vector3d.x,
                            vector3d.y,
                            vector3d.z);
                }
                level.playSound(null, new BlockPos(result.getLocation()), SoundType.GLASS.getBreakSound(), SoundSource.AMBIENT, 1.0F, 1.5F);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level.isClientSide) {
            if(result.getType() != HitResult.Type.MISS) {
                int count = 2;
                Vec3 vector3d = this.getDeltaMovement();
                double d0 = this.getX() + vector3d.x;
                double d1 = this.getY() + vector3d.y;
                double d2 = this.getZ() + vector3d.z;
                for(int i = 0; i < count; ++i) {
                    this.level.addParticle(ParticleTypes.EFFECT,
                            d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                            d1 - vector3d.y + 0.25F,
                            d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                            vector3d.x,
                            vector3d.y,
                            vector3d.z);
                }
                level.playSound(null, new BlockPos(result.getLocation()), SoundType.GLASS.getBreakSound(), SoundSource.AMBIENT, 1.0F, 1.5F);
            }
        } else {
            remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected float getGravity() {
        return 0.31F;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.DEMONIC_BOMB.get());
    }
}