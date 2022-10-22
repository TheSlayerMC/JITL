package net.jitl.common.items.base;

import net.jitl.core.helper.EnumJTier;
import net.jitl.core.helper.EnumSwordType;
import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JSwordItem extends SwordItem {

    private EnumSwordType type;
    private float health = 0F;

    public JSwordItem(EnumJTier tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.weaponProps());
    }

    public JSwordItem(EnumSwordType type, EnumJTier tier) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), JItems.weaponProps());
        this.type = type;
    }

    public JSwordItem(EnumSwordType type, EnumJTier tier, float health) {
        this(type, tier);
        this.health = health;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, LivingEntity player) {
        float hearts = player.getHealth();
        RandomSource rand = target.getRandom();
        if(type != null) {
            switch (type) {
                case FREEZE:
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
                    target.clearFire();
                    break;
                case POISON:
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
                    break;
                case FIRE:
                    target.setSecondsOnFire(5);
                    break;
                case FIRE_HEALTH:
                    target.setSecondsOnFire(5);
                    if ((hearts >= 1F) & (hearts < 20F)) {
                        player.setHealth(hearts + this.health);
                    } else if(rand.nextInt(4) == 0) {
                        player.setHealth(hearts - 10);
                    }
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                    break;
                case FIRE_WITHER:
                    target.setSecondsOnFire(5);
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 20));
                    break;
                case HEALTH:
                    if (hearts >= 1F) {
                        player.setHealth(hearts + health);
                    } else if(rand.nextInt(2) == 0) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 2));
                    } else if (rand.nextInt(4) == 0) {
                        player.setHealth(hearts - 4);
                    }
                    break;
                case NIGHT_VISION_HEALTH:
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 20));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10, 200));
                    break;
                case POISON_HEALTH:
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
                    if (hearts >= 1F) {
                        player.setHealth(hearts + this.health);
                    } else if(rand.nextInt(2) == 0) {
                        player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
                    }
                    break;
                case REGEN:
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
                    break;
                case STUN:
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 70, 1));
                    target.addEffect(new MobEffectInstance(MobEffects.HARM, 50, 1));
                    break;
                case STUN_WITHER:
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1000, 200));
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                    break;
                case WITHER:
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 5));
                    break;
                case NIGHT_VISION:
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 10, 2));
                    break;
                case BUBBLE:
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 10, 2));
                    target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 2));
                    break;
                case LOGGER:
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 1));
                    break;
                default:
                    break;
            }
        }
        return super.hurtEnemy(stack, target, player);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack item, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        if(type != null) {
            switch(type) {
                case FREEZE -> list.add(addText("jitl.tooltip.freeze"));
                case POISON -> list.add(addText("jitl.tooltip.poison"));
                case FIRE -> list.add(addText("jitl.tooltip.fire"));
                case FIRE_HEALTH -> {
                    list.add(addText("jitl.tooltip.fire_health1"));
                    list.add(addText("jitl.tooltip.fire_health2"));
                    list.add(addText("jitl.tooltip.fire_health3"));
                }
                case FIRE_WITHER -> list.add(addText("jitl.tooltip.fire_wither"));
                case HEALTH -> {
                    list.add(addText("jitl.tooltip.health1"));
                    list.add(addText("jitl.tooltip.health2"));
                    list.add(addText("jitl.tooltip.health3"));
                }
                case NIGHT_VISION_HEALTH -> list.add(addText("jitl.tooltip.night_health"));
                case POISON_HEALTH -> {
                    list.add(addText("jitl.tooltip.poison_health1"));
                    list.add(addText("jitl.tooltip.poison_health2"));
                }
                case REGEN -> list.add(addText("jitl.tooltip.regen"));
                case STUN -> list.add(addText("jitl.tooltip.stun"));
                case STUN_WITHER -> list.add(addText("jitl.tooltip.stun_wither"));
                case WITHER -> list.add(addText("jitl.tooltip.wither"));
                case NIGHT_VISION -> list.add(addText("jitl.tooltip.night"));
                case BUBBLE -> list.add(addText("jitl.tooltip.bubble"));
                case LOGGER -> list.add(addText("jitl.tooltip.logger"));
                default -> {
                }
            }
        }
        if(item.getMaxDamage() != -1)
            list.add(addText(item.getMaxDamage() - item.getDamageValue() + " " + "jitl.tooltip.uses_remaining"));
    }

    public Component addText(String text) {
        return Component.translatable(text);
    }
}
