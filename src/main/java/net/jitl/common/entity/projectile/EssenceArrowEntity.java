package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class EssenceArrowEntity extends AbstractArrow implements ItemSupplier {

    private EnumSet<BowEffects> effects;

    public EssenceArrowEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public EssenceArrowEntity(Level level, LivingEntity player, EnumSet<BowEffects> effects, float damage) {
        super(JEntities.ESSENCE_ARROW_TYPE.get(), player, level);
        this.effects = effects;
        this.setBaseDamage(damage);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult r) {
        super.onHitEntity(r);
        if(r.getEntity() instanceof LivingEntity entity) {
            if(effects != null) {
                if(effects.contains(BowEffects.WITHER)) {
                    applyPotionEffect(entity, MobEffects.WITHER, 100, 2);
                }
                if(effects.contains(BowEffects.SLOWNESS)) {
                    applyPotionEffect(entity, MobEffects.MOVEMENT_SLOWDOWN, 100, 2);
                }
                if(effects.contains(BowEffects.FLAME)) {
                    entity.setSecondsOnFire(3);
                }
                if(effects.contains(BowEffects.POISON)) {
                    applyPotionEffect(entity, MobEffects.POISON, 100, 2);
                }
            }
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return getItem();
    }

    private void applyPotionEffect(LivingEntity effectedEntity, MobEffect potionEffect, int duration, int amplifier) {
        effectedEntity.addEffect(new MobEffectInstance(potionEffect, duration, amplifier));
    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return !(effects == null) && !effects.contains(BowEffects.CONSUMES_ESSENCE);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(JItems.ESSENCE_ARROW.get());
    }


    public enum BowEffects {
        WITHER, SLOWNESS, FLAME, POISON, DOUBLE_ARROW, CONSUMES_ESSENCE
    }
}
