package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractKnifeEntity extends AbstractArrow {

    private boolean dealtDamage;
    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(JItems.ESSENCE_ARROW.get());

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public AbstractKnifeEntity(EntityType<? extends AbstractArrow> entityType, Level worldIn, LivingEntity player, @Nullable ItemStack weapon) {
        super(entityType, player, worldIn, DEFAULT_ARROW_STACK, weapon);
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