package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FireBombEntity extends DamagingProjectileEntity implements ItemSupplier {

    public FireBombEntity(EntityType<FireBombEntity> type, Level world) {
        super(type, world);
    }

    public FireBombEntity(EntityType<FireBombEntity> type, Level world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    @Override
    protected void onEntityImpact(HitResult result, Entity target) {
        if(target instanceof LivingEntity && target.hurt(this.damageSources().thrown(this, this.getOwner()), getDamage())) {
            //target.hurt(JDamageSources.FIRE_BOMB, this.getDamage());//TODO
            target.hurt(this.damageSources().cactus(), this.getDamage());

            target.setRemainingFireTicks(60);
            if(!this.level().isClientSide) {
                this.level().broadcastEntityEvent(this, (byte)1);
                this.discard();
            }
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        ParticleOptions particleoptions = ParticleTypes.EFFECT;

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
    protected void onHit(HitResult result) {
        super.onHit(result);
        if(result.getType() == HitResult.Type.BLOCK) {
            if (!this.level().isClientSide) {
                this.level().broadcastEntityEvent(this, (byte)2);
                this.discard();
            }
        }
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.FIRE_BOMB.get());
    }
}