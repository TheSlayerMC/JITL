package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MagicBombEntity extends AbstractArrow implements ItemSupplier {

    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(JItems.MAGIC_BOMB.get());


    public MagicBombEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public MagicBombEntity(Level worldIn, LivingEntity player, float damage, @Nullable ItemStack weapon) {
        super(JEntities.MAGIC_BOMB_TYPE.get(), player, worldIn, DEFAULT_ARROW_STACK, weapon);
        setBaseDamage(damage);
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return JSounds.KNIFE.get();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityRayTraceResult_) {
        Entity entity = entityRayTraceResult_.getEntity();
        if(entity instanceof LivingEntity && entity != this.getOwner()) {
            if(!level().isClientSide()) {
                if(entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float) getBaseDamage())) {
                    level().explode(this, position().x, position().y, position().z, 2.0F, Level.ExplosionInteraction.BLOCK);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
            this.playSound(JSounds.BOTTLE_PLUG.get(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        } else {
            super.onHitEntity(entityRayTraceResult_);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(isInGround()) {
            if (collidedWith() != null && collidedWith() != this.getOwner()) {
                if (!level().isClientSide) {
                    level().explode(this, position().x, position().y, position().z, 2.0F, Level.ExplosionInteraction.BLOCK);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        }
    }

    public Entity collidedWith() {
        for(Entity entity : this.level().getEntities(this, this.getBoundingBox())) {
            if(entity instanceof LivingEntity) {
                return entity;
            }
        }
        return null;
    }

    public boolean isInGround() {
        return this.inGround;
    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer);
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return new ItemStack(JItems.MAGIC_BOMB.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return getPickupItem();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.MAGIC_BOMB.get());
    }
}
