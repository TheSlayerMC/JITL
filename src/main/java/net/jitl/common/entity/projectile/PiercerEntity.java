package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class PiercerEntity extends AbstractArrow implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> STACK = SynchedEntityData.defineId(PiercerEntity.class, EntityDataSerializers.ITEM_STACK);

    private float velocityMultiplier;
    private double rangeAddend;
    private int flameAddend;
    private int faithfulLevel;

    private boolean launch = false;
    private int currentBounces;
    private int maxBounces;
    public int soundTickCount;
    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(JItems.ESSENCE_ARROW.get());

    public PiercerEntity(LivingEntity shooter, Level worldIn, ItemStack stack, int maxBounces, float damage, @Nullable ItemStack weapon) {
        super(JEntities.PIERCER_TYPE.get(), shooter, worldIn, DEFAULT_ARROW_STACK, weapon);
        setStack(stack.copy());
        this.setSoundEvent(JSounds.PIERCER.get());
        this.maxBounces = maxBounces;
        setBaseDamage(damage);
    }

    public PiercerEntity(EntityType<PiercerEntity> piercer, Level world) {
        super(piercer, world);
        this.setSoundEvent(JSounds.PIERCER.get());
    }

    public void setVelocityMultiplier(float velocityMultiplier) {
        this.velocityMultiplier = velocityMultiplier;
    }

    public float getVelocityMultiplier() {
        return velocityMultiplier;
    }

    public void setRangeAddend(double rangeAddend) {
        this.rangeAddend = rangeAddend;
    }

    public double getRangeAddend() {
        return rangeAddend;
    }

    public void setFlameAddend(int flameAddend) {
        this.flameAddend = flameAddend;
    }

    public void setFaithfulLevel(int level) {
        this.faithfulLevel = level;
    }

    public int getFlameAddend() {
        return flameAddend;
    }

    @Override
    public void tick() {
        super.tick();

        if (!isNoPhysics() && !isInGround() && !isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, 0.025, 0));
        }

        if (launch) {
            Entity bounceTo = null;
            if (++currentBounces <= maxBounces) {
                List<LivingEntity> entitiesNear = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4D + getRangeAddend()));
                for (LivingEntity e : entitiesNear) {
                    if (e != this.getOwner() && this.pathTo(e) && e.invulnerableTime == 0 && !e.isDeadOrDying() && e.getClassification(false) == MobCategory.MONSTER) { //check whether this entity is a valid target
                        if (bounceTo == null || this.distanceTo(e) < this.distanceTo(bounceTo)) {
                            bounceTo = e;
                        }
                    }
                }
            }
            if(bounceTo == null) bounceTo = getOwner();

            if(bounceTo != null) {
                Vec3 movement = new Vec3(bounceTo.getX(), bounceTo.getY(0.8), bounceTo.getZ()).subtract(this.getX(), this.getY(0.5), this.getZ());
                this.setDeltaMovement(movement.scale(((0.7 + getVelocityMultiplier() / 6.5) / movement.length()) * this.getDeltaMovement().length()));
            }
            launch = false;
        }
        if(faithfulLevel > 0) {
            Entity entity = this.getOwner();
            if(isInGround() && isAcceptableReturnOwner() && entity != null) {
                this.setNoPhysics(true);
                Vec3 vector3d = new Vec3(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
                this.setPosRaw(this.getX(), this.getY() + vector3d.y * 0.015D * (double) faithfulLevel, this.getZ());
                if(this.level().isClientSide)
                    this.yOld = this.getY();

                double d0 = 0.15D * (double) faithfulLevel;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vector3d.normalize().scale(d0)));
                if(this.soundTickCount == 0)
                    this.playSound(JSounds.PIERCER_RETURN.get(), 10.0F, Mth.nextFloat(random, 0.8F, 1.2F));

                this.soundTickCount++;
            }
        }
        if(getStack().isEmpty()) {
            level().playSound(null, blockPosition(), SoundEvents.ITEM_BREAK.value(), SoundSource.AMBIENT, 1F, 1F);
            discard();
        }
    }

    private boolean isAcceptableReturnOwner() {
        Entity entity = this.getOwner();
        if(entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    private boolean pathTo(Entity entityIn) {
        Vec3 vector3d = new Vec3(this.getX(), this.getY(0.5), this.getZ());
        Vec3 vector3d1 = new Vec3(entityIn.getX(), entityIn.getY(0.8), entityIn.getZ());
        return this.level().clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityRayTraceResult_) {
        Entity entity = entityRayTraceResult_.getEntity();
        if(entity instanceof LivingEntity && entity != this.getOwner()) {
            if(level() instanceof ServerLevel level) {
                if(getOwner() instanceof ServerPlayer player) {
                    getStack().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }

                if(entity.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), (float)baseDamage)) {
                    if (getFlameAddend() > 0) {
                        entity.setRemainingFireTicks(getFlameAddend() * 4 * 20);
                    }
                    launch = true;
                }
                this.playSound(JSounds.PIERCER.get(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            }
        }
    }

    @Override
    public void playerTouch(@NotNull Player entityIn) {
        if(!this.level().isClientSide) {
            boolean isOwner = this.getOwner().getUUID() == entityIn.getUUID();
            if((isOwner && currentBounces > 0) || ((this.isInGround() || this.isNoPhysics()) && this.shakeTime <= 0)) {
                boolean flag = this.pickup == Pickup.ALLOWED || this.pickup == Pickup.CREATIVE_ONLY && entityIn.canUseGameMasterBlocks() || this.isNoPhysics() && isOwner;
                if(this.pickup == Pickup.ALLOWED && !entityIn.getInventory().add(this.getPickupItem()))
                    flag = false;

                if(flag) {
                    entityIn.take(this, 1);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("stack", getStack().save(this.registryAccess()));
        nbt.putInt("bounces", currentBounces);
        nbt.putInt("maxBounces", maxBounces);
        nbt.putFloat("velocityMultiplier", velocityMultiplier);
        nbt.putDouble("rangeAddend", rangeAddend);
        nbt.putInt("flameAddend", flameAddend);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        setStack(ItemStack.parse(this.registryAccess(), nbt.getCompoundOrEmpty("stack")).orElse(this.getDefaultPickupItem()));
        if (getStack().isEmpty()) remove(RemovalReason.DISCARDED);
        currentBounces = nbt.getIntOr("bounces", 0);
        maxBounces = nbt.getIntOr("maxBounces", 0);
        velocityMultiplier = nbt.getFloatOr("velocityMultiplier", 0F);
        rangeAddend = nbt.getDoubleOr("rangeAddend", 0D);
        flameAddend = nbt.getIntOr("flameAddend", 0);
    }

    private void setStack(ItemStack stack) {
        this.getEntityData().set(STACK, stack);
    }

    private ItemStack getStack() {
        return this.getEntityData().get(STACK);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return getStack().copy();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return getPickupItem();
    }

    @Override
    public @NotNull ItemStack getItem() {
        ItemStack stack = getStack();
        return stack.isEmpty() ? new ItemStack(JItems.PIERCER.get()) : stack;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(STACK, ItemStack.EMPTY);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        if(key == STACK)
            getStack().setEntityRepresentation(this);
        super.onSyncedDataUpdated(key);
    }
}
