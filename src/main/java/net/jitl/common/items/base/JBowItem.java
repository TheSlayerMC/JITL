package net.jitl.common.items.base;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.helper.JEnchantmentHelper;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class JBowItem extends BowItem {

    public static final int DEFAULT_DURATION = 72000;
    protected int maxUseDuration;
    protected float damage;
    protected Item arrow_item;
    protected int uses;
    protected int essence_use;
    public EnumSet<EssenceArrowEntity.BowEffects> effect;
    public static final Predicate<ItemStack> ESSENCE_ARROW = (tag) -> tag.is(JTags.ESSENCE_ARROW);

    public JBowItem(Properties p, float damage, int uses, EnumSet<EssenceArrowEntity.BowEffects> effect, int pullbackSpeed) {
        super(p.stacksTo(1).durability(uses));
        this.effect = effect;
        this.arrow_item = JItems.ESSENCE_ARROW.get();
        this.damage = damage;
        this.uses = uses;
        this.maxUseDuration = pullbackSpeed;
    }

    public JBowItem setEssenceUse(int amount) {
        this.essence_use = amount;
        return this;
    }

    public float getScaledArrowVelocity(int charge) {
        float timeRatio = ((float) DEFAULT_DURATION / (float) this.maxUseDuration);
        float f = ((float) charge / 20.0F) * timeRatio;
        f = (f * f + f * 2.0F) / 2.0F;

        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    @Override
    public boolean releaseUsing(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player && worldIn instanceof ServerLevel level) {
            boolean emptyPickup = player.isCreative()
                    || JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.INFINITY) > 0
                    || effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE);

            ItemStack itemstack = this.findAmmo(player);
            if(!worldIn.isClientSide) {

                int i = this.maxUseDuration - timeLeft;
                i = EventHooks.onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || emptyPickup);
                if (i < 0) return emptyPickup;

                if (!itemstack.isEmpty() || emptyPickup) {
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(arrow_item);
                    }

                    float f = getScaledArrowVelocity(i);
                    if ((double) f >= 0.1D) {
                        EssenceArrowEntity entityarrow = new EssenceArrowEntity(worldIn, player, this.effect, this.damage, stack);
                        EssenceArrowEntity entityarrow2 = new EssenceArrowEntity(worldIn, player, this.effect, this.damage, stack);

                        /*
                         * shoot 2 arrows if bow is Wasteful Bow
                         */
                        if (effect.contains(EssenceArrowEntity.BowEffects.DOUBLE_ARROW)) {

                            entityarrow.shootFromRotation(player, player.getXRot(), player.getYRot() + 3.25F, 0.0F, f * 3.0F, 1.0F);
                            entityarrow2.shootFromRotation(player, player.getXRot(), player.getYRot() - 3.25F, 0.0F, f * 3.0F, 1.0F);

                            int j = JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.POWER);
                            if(j == 1.0F) {
                                entityarrow.setCritArrow(true);
                                entityarrow2.setCritArrow(true);
                            }

                            int k = JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.PUNCH);
                            if(k > 0) {
//                                entityarrow.setKnockback(k);
//                                entityarrow2.setKnockback(k);
                            }

                            if(JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.FLAME) > 0) {
                                entityarrow.setRemainingFireTicks(100 * 20);
                                entityarrow2.setRemainingFireTicks(100 * 20);
                            }

                            entityarrow.setBaseDamage(this.damage);
                            entityarrow2.setBaseDamage(this.damage);

                            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

                            if(!emptyPickup) {
                                entityarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                                entityarrow2.pickup = AbstractArrow.Pickup.ALLOWED;
                            } else {
                                entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                                entityarrow2.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            worldIn.addFreshEntity(entityarrow);
                            worldIn.addFreshEntity(entityarrow2);
                        }
                        /*
                         * shoot 1 arrow if bow isn't Wasteful Bow
                         */
                        else {
                            entityarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                            int j = JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.POWER);
                            if(j == 1.0F)
                                entityarrow.setCritArrow(true);

                            int k = JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.PUNCH);
                            //if(k > 0)
                            //entityarrow.setKnockback(k);

                            if(JEnchantmentHelper.getEnchantmentAmount(player, level, Enchantments.FLAME) > 0)
                                entityarrow.setRemainingFireTicks(100 * 20);

                            entityarrow.setBaseDamage(this.damage);

                            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

                            if(!emptyPickup) {
                                entityarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                            } else {
                                entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            if(effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE)) {
                                if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, essence_use)) {
                                    worldIn.addFreshEntity(entityarrow);
                                }
                            }

                            if(!effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE)) {
                                worldIn.addFreshEntity(entityarrow);
                            }
                        }
                    }

                    worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if(!emptyPickup) {
                        if(effect.contains(EssenceArrowEntity.BowEffects.DOUBLE_ARROW)) {
                            itemstack.shrink(2);
                        } else {
                            itemstack.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
        return true;
    }

    public ItemStack findAmmo(Player player) {
        if (this.isArrow(player.getItemInHand(InteractionHand.OFF_HAND))) {
            return player.getItemInHand(InteractionHand.OFF_HAND);

        } else if (this.isArrow(player.getItemInHand(InteractionHand.MAIN_HAND))) {
            return player.getItemInHand(InteractionHand.MAIN_HAND);

        } else if (effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE)) {
            return ItemStack.EMPTY;

        } else {
            for (int i = 0; i < player.inventoryMenu.getItems().size(); ++i) {
                ItemStack itemstack = player.inventoryMenu.getItems().get(i);

                if (this.isArrow(itemstack)) {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, display, tooltip, pTooltipFlag);
        tooltip.accept(Component.translatable("Damage: " + ChatFormatting.GOLD + damage + " - " + ChatFormatting.GOLD + damage * 4));
        float maxUse = (float) DEFAULT_DURATION / (float) this.maxUseDuration;
        DecimalFormat df = new DecimalFormat("#.##");
        tooltip.accept(Component.translatable("Pull Back Speed: " + ChatFormatting.GOLD + df.format(maxUse)));

        if(effect != null) {
            if(effect.contains(EssenceArrowEntity.BowEffects.WITHER))
                tooltip.accept(Component.translatable(ChatFormatting.DARK_GRAY + "Ability: Withers foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.FLAME))
                tooltip.accept(Component.translatable(ChatFormatting.GOLD + "Ability: Sets foe ablaze"));

            if(effect.contains(EssenceArrowEntity.BowEffects.POISON))
                tooltip.accept(Component.translatable(ChatFormatting.GREEN + "Ability: Poisons foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.SLOWNESS))
                tooltip.accept(Component.translatable(ChatFormatting.BLUE + "Ability: Stuns foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.DOUBLE_ARROW))
                tooltip.accept(Component.translatable(ChatFormatting.BLUE + "Ability: Shoots 2 arrows"));

            if(effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE))
                tooltip.accept(Component.translatable(ChatFormatting.GREEN + "Ability: Consumes " + essence_use + " Essence instead of arrows"));
        }
        tooltip.accept(Component.translatable("Uses remaining: " + ChatFormatting.GRAY + uses));
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ESSENCE_ARROW;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity p_345962_) {
        return maxUseDuration;
    }

    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == JItems.ESSENCE_ARROW.get();
    }
}
