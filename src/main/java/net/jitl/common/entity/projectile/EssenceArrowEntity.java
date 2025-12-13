package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class EssenceArrowEntity extends AbstractArrow {

    private EnumSet<BowEffects> effects;
    private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(JItems.ESSENCE_ARROW.get());

    public EssenceArrowEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public EssenceArrowEntity(Level pLevel, LivingEntity pOwner, @Nullable ItemStack pFiredFromWeapon) {
        super(JEntities.ESSENCE_ARROW_TYPE.get(), pOwner, pLevel, DEFAULT_ARROW_STACK, pFiredFromWeapon);
    }

    public EssenceArrowEntity(Level pLevel, double pX, double pY, double pZ, @Nullable ItemStack pFiredFromWeapon) {
        super(JEntities.ESSENCE_ARROW_TYPE.get(), pX, pY, pZ, pLevel, DEFAULT_ARROW_STACK, pFiredFromWeapon);
    }

    public EssenceArrowEntity(Level level, LivingEntity player, EnumSet<BowEffects> effects, float damage, @Nullable ItemStack weapon) {
        super(JEntities.ESSENCE_ARROW_TYPE.get(), player, level, DEFAULT_ARROW_STACK, weapon);
        this.effects = effects;
        this.setBaseDamage(damage);
        this.setPickupItemStack(DEFAULT_ARROW_STACK);
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
                    applyPotionEffect(entity, MobEffects.SLOWNESS, 100, 2);
                }
                if(effects.contains(BowEffects.FLAME)) {
                    entity.setRemainingFireTicks(60);
                }
                if(effects.contains(BowEffects.POISON)) {
                    applyPotionEffect(entity, MobEffects.POISON, 100, 2);
                }
            }
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(JItems.ESSENCE_ARROW.get());
    }

    private void applyPotionEffect(LivingEntity effectedEntity, Holder<MobEffect> potionEffect, int duration, int amplifier) {
        effectedEntity.addEffect(new MobEffectInstance(potionEffect, duration, amplifier));
    }

    @Override
    protected boolean tryPickup(Player pPlayer) {
        return !effects.contains(BowEffects.CONSUMES_ESSENCE);
    }


    public enum BowEffects {
        WITHER, SLOWNESS, FLAME, POISON, DOUBLE_ARROW, CONSUMES_ESSENCE
    }
}
