package net.jitl.common.entity.projectile;

import net.jitl.core.data.JDamageSources;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.server.level.ServerLevel;
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

public class DemonicBombEntity extends AbstractArrow implements ItemSupplier {

    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(JItems.DEMONIC_BOMB.get());

    public DemonicBombEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public DemonicBombEntity(Level worldIn, LivingEntity player, float damage, @Nullable ItemStack weapon) {
        super(JEntities.DEMONIC_BOMB_TYPE.get(), player, worldIn, DEFAULT_ARROW_STACK, weapon);
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
            if(level() instanceof ServerLevel level) {
                if(entity instanceof LivingEntity && entity.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), 5)) {
                    entity.hurtServer(level, JDamageSources.hurt(entity, JDamageSources.DEMONIC_BOMB), 5);
                    if (!this.level().isClientSide()) {
                        this.level().broadcastEntityEvent(this, (byte) 1);
                        this.discard();
                    }
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
                if (level() instanceof ServerLevel level) {
                    if(collidedWith() instanceof LivingEntity entity && entity.hurtServer(level, this.damageSources().thrown(this, this.getOwner()), 4)) {
                        entity.hurtServer(level, JDamageSources.hurt(entity, JDamageSources.DEMONIC_BOMB), 4);
                        if(!this.level().isClientSide()) {
                            this.level().broadcastEntityEvent(this, (byte) 1);
                            this.discard();
                        }
                    }
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

    @Override
    public void handleEntityEvent(byte id) {
        ParticleOptions particleoptions = SpellParticleOption.create(ParticleTypes.EFFECT, -1, 1F);

        if(id == 1) {
            for(int i = 0; i < 15; ++i)
                this.level().addParticle(particleoptions, getX() + level().random.nextDouble(), getY(), getZ() + level().random.nextDouble(), 1, 0.0D, 0.0D);
        }

        if(id == 2) {
            for(int i = 0; i < 15; ++i)
                this.level().addParticle(particleoptions, getX() + level().random.nextDouble(), getY() + 1, getZ() + level().random.nextDouble(), 1, 0.0D, 0.0D);
        }
    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer);
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return new ItemStack(JItems.DEMONIC_BOMB.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return getPickupItem();
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.DEMONIC_BOMB.get());
    }
}