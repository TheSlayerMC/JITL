package net.jitl.common.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DamagingProjectileEntity extends ThrowableProjectile {

    private float damage;

    public DamagingProjectileEntity(EntityType<? extends DamagingProjectileEntity> type, Level world) {
        super(type, world);
    }

    public DamagingProjectileEntity(EntityType<? extends DamagingProjectileEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, thrower, world);
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

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
    protected void onHit(HitResult result) {
        if(!level().isClientSide) {
            if(result.getType() == HitResult.Type.ENTITY) {
                Entity target = ((EntityHitResult) result).getEntity();

                if(!Objects.equals(target, getOwner())) {
                    onEntityImpact(result, target);

                    remove(RemovalReason.DISCARDED);
                }
            } else if(result.getType() == HitResult.Type.BLOCK) {
                onBlockImpact((BlockHitResult) result);
            } else {
                remove(RemovalReason.DISCARDED);
            }
        }
    }

    protected void onEntityImpact(HitResult result, Entity target) {
        target.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage());
    }

    protected void onBlockImpact(BlockHitResult result) {
        remove(RemovalReason.DISCARDED);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.03F;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("damage", damage);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        damage = compound.getFloat("damage");
    }

}