package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class DamagingProjectileEntity extends ThrowableItemProjectile {

    private float damage;

    public DamagingProjectileEntity(EntityType<? extends DamagingProjectileEntity> type, Level world) {
        super(type, world);
    }

    public DamagingProjectileEntity(EntityType<? extends DamagingProjectileEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world);
        this.damage = damage;
        this.setOwner(thrower);
    }

    public float getDamage() {
        return damage;
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return getItem().getItem();
    }

    @Override
    public void tick() {
        super.tick();
        if(!level().isClientSide) {
            if(shouldDespawn()) {
                remove(RemovalReason.DISCARDED);
            }
        } else {
            onClientTick();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onClientTick() { }

    protected boolean shouldDespawn() {
        return tickCount >= 20 * 10;
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        if(!level().isClientSide) {
            if(result.getType() == HitResult.Type.ENTITY) {
                Entity target = ((EntityHitResult) result).getEntity();

                if(!Objects.equals(target, getOwner())) {
                    onEntityImpact(result, target);

                    remove(RemovalReason.DISCARDED);
                }
            } else if(result.getType() == HitResult.Type.BLOCK) {
                onHitBlock((BlockHitResult) result);
            } else {
                remove(RemovalReason.DISCARDED);
            }
        }
    }

    protected void onEntityImpact(HitResult result, Entity target) {
        if(level() instanceof ServerLevel level)
            target.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), getDamage());
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        remove(RemovalReason.DISCARDED);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.03F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("damage", damage);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        damage = compound.getFloat("damage");
    }

}