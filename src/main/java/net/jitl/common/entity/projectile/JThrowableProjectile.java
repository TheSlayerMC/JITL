package net.jitl.common.entity.projectile;

import net.minecraft.core.Holder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public abstract class JThrowableProjectile extends ThrowableProjectile {

    private int damage = 0, fireTicks = 0, potionTicks = 0;
    private boolean isBouncy = false, isFire = false, isPotion = false;
    private Holder<MobEffect> potion;

    protected JThrowableProjectile(EntityType<? extends ThrowableProjectile> e, Level l) {
        super(e, l);
    }

    public JThrowableProjectile(EntityType<? extends ThrowableProjectile> e, int damage, Level world, LivingEntity thrower) {
        super(e, world);
        this.damage = damage;
    }

    public void setPotionEffect(Holder<MobEffect> potion, int ticks) {
        this.isPotion = true;
        this.potionTicks = ticks;
        this.potion = potion;
    }

    public void setFire(int fireTicks) {
        this.isFire = true;
        this.fireTicks = fireTicks;
    }

    public void setBouncy() {
        this.isBouncy = true;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult res) {
        super.onHitEntity(res);
        Entity entity = res.getEntity();
        if(entity instanceof LivingEntity) {
            if(this.isPotion && this.potion != null) {
                MobEffectInstance effectInstance = new MobEffectInstance(potion, potionTicks);
                ((LivingEntity)entity).addEffect(effectInstance);
            }

            if(this.damage != 0)
                entity.hurt(this.damageSources().thrown(this, this.getOwner()), this.damage);

            if(this.isFire)
                entity.setRemainingFireTicks(this.fireTicks);

        }
    }

    public int getEntityDamage() {
        return damage;
    }

    @Override
    protected void onHit(@NotNull HitResult res) {
        super.onHit(res);
        if(!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            if(!isBouncy)
                discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder b) { }
}