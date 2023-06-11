package net.jitl.common.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractKnifeEntity extends AbstractArrow {

    private boolean dealtDamage;

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> entityType, Level worldIn, LivingEntity player) {
        super(entityType, player, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void onClientTick() { }

    @Override
    public void tick() {
        if(this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getOwner();
        if((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if(!this.isAcceptableReturnOwner()) {
                if(!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }
                this.remove(RemovalReason.DISCARDED);
            }
        }
        if(level().isClientSide) {
            onClientTick();
        }
        super.tick();
    }

    private boolean isAcceptableReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(this::pickupItem);
    }

    public abstract @NotNull Item pickupItem();

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("damage dealt", dealtDamage);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        dealtDamage = compound.getBoolean("damage dealt");
    }
}