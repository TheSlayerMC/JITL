package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class KnifeEntity extends AbstractKnifeEntity implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> STACK = SynchedEntityData.defineId(KnifeEntity.class, EntityDataSerializers.ITEM_STACK);
    private int durability;
    public KnifeEntity(EntityType<KnifeEntity> type, Level world) {
        super(type, world);
    }

    public KnifeEntity(LivingEntity owner, Level worldIn, ItemStack stack, float damage) {
        super(JEntities.KNIFE_TYPE.get(), worldIn, owner);
        setBaseDamage(damage);
        setStack(stack.copy());
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return JSounds.KNIFE.get();
    }

    @Override
    public void onClientTick() {
        super.onClientTick();
        if(isFireKnife(getStack().getItem())) {
            double d0 = getX() + 0D;
            double d1 = getY() + 0D;
            double d2 = getZ() + 0D;
            level().addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            level().addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean isFireKnife(Item item) {
        return item == JItems.MOLTEN_KNIFE.get() || item == JItems.CHARRED_KNIFE.get();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityRayTraceResult_) {
        Entity entity = entityRayTraceResult_.getEntity();
        if(entity instanceof LivingEntity && entity != this.getOwner()) {
            if(!level().isClientSide()) {
                if(entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float) getBaseDamage())) {
                    if(isFireKnife(getStack().getItem())) {
                        entity.setSecondsOnFire(10);
                    }
                }
                this.playSound(JSounds.KNIFE.get(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            }
        } else {
            super.onHitEntity(entityRayTraceResult_);
        }
    }

    public boolean isInGround() {
        return this.inGround;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("dur", getStack().getDamageValue());
        nbt.put("stack", getStack().save(new CompoundTag()));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        durability = nbt.getInt("dur");
        setStack(ItemStack.of(nbt.getCompound("stack")));
        if(getStack().isEmpty()) remove(RemovalReason.DISCARDED);
    }

    private void setStack(ItemStack stack) {
        this.getEntityData().set(STACK, stack);
    }

    private ItemStack getStack() {
        return this.getEntityData().get(STACK);
    }

    @Override
    public @NotNull Item pickupItem() {
        ItemStack item = getStack().copy();
        item.setDamageValue(durability);
        return item.getItem();
    }

    @Override
    public @NotNull ItemStack getItem() {
        ItemStack stack = getStack();
        return stack.isEmpty() ? new ItemStack(JItems.IRON_THROWING_KNIFE.get()) : stack;
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(STACK, ItemStack.EMPTY);
        super.defineSynchedData();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if(key == STACK) {
            getStack().setEntityRepresentation(this);
        }
        super.onSyncedDataUpdated(key);
    }
}
