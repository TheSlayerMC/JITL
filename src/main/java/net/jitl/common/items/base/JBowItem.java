package net.jitl.common.items.base;

import net.jitl.common.entity.projectile.EssenceArrowEntity;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.List;
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

    public JBowItem(float damage, int uses, EnumSet<EssenceArrowEntity.BowEffects> effect, int pullbackSpeed) {
        super(JItems.itemProps().stacksTo(1).durability(uses));
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
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            boolean emptyPickup = player.isCreative() || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0
                    || effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE);

            ItemStack itemstack = this.findAmmo(player);

            int i = this.maxUseDuration - timeLeft;
            i = EventHooks.onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || emptyPickup);
            if (i < 0) return;

            if (!itemstack.isEmpty() || emptyPickup) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(arrow_item);
                }

                float f = getScaledArrowVelocity(i);
                if ((double) f >= 0.1D) {
                    if(!worldIn.isClientSide) {
                        EssenceArrowEntity entityarrow = null;
                        EssenceArrowEntity entityarrow2 = null;
                        try {
                            entityarrow = new EssenceArrowEntity(worldIn, player, this.effect, this.damage);
                            entityarrow2 = new EssenceArrowEntity(worldIn, player, this.effect, this.damage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        /*
                         * shoot 2 arrows if bow is Wasteful Bow
                         */
                        if (effect.contains(EssenceArrowEntity.BowEffects.DOUBLE_ARROW)) {
                            assert entityarrow != null;
                            entityarrow.shootFromRotation(player, player.getXRot(), player.getYRot() + 3.25F, 0.0F, f * 3.0F, 1.0F);
                            assert entityarrow2 != null;
                            entityarrow2.shootFromRotation(player, player.getXRot(), player.getYRot() - 3.25F, 0.0F, f * 3.0F, 1.0F);

                            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                            if(j == 1.0F) {
                                entityarrow.setCritArrow(true);
                                entityarrow2.setCritArrow(true);
                            }

                            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                            if(k > 0) {
                                entityarrow.setKnockback(k);
                                entityarrow2.setKnockback(k);
                            }

                            if(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                                entityarrow.setSecondsOnFire(100);
                                entityarrow2.setSecondsOnFire(100);
                            }

                            entityarrow.setBaseDamage(this.damage);
                            entityarrow2.setBaseDamage(this.damage);

                            stack.hurtAndBreak(1, player, (onBroken) -> onBroken.broadcastBreakEvent(player.getUsedItemHand()));

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
                            assert entityarrow != null;
                            entityarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                            if(j == 1.0F)
                                entityarrow.setCritArrow(true);

                            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                            if(k > 0)
                                entityarrow.setKnockback(k);

                            if(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0)
                                entityarrow.setSecondsOnFire(100);

                            entityarrow.setBaseDamage(this.damage);

                            stack.hurtAndBreak(1, player, (onBroken) -> {
                                onBroken.broadcastBreakEvent(player.getUsedItemHand());
                            });

                            if(!emptyPickup) {
                                entityarrow.pickup = AbstractArrow.Pickup.ALLOWED;
                            } else {
                                entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            if(effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE)) {
                                EssenceArrowEntity finalEntityarrow = entityarrow;
                                    if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, essence_use)) {
                                        worldIn.addFreshEntity(finalEntityarrow);
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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comp, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, comp, isAdvanced);
        comp.add(Component.translatable("Damage: " + ChatFormatting.GOLD + damage + " - " + ChatFormatting.GOLD + damage * 4));
        float maxUse = (float) DEFAULT_DURATION / (float) this.maxUseDuration;
        DecimalFormat df = new DecimalFormat("#.##");
        comp.add(Component.translatable("Pull Back Speed: " + ChatFormatting.GOLD + df.format(maxUse)));

        if(effect != null) {
            if(effect.contains(EssenceArrowEntity.BowEffects.WITHER))
                comp.add(Component.translatable(ChatFormatting.DARK_GRAY + "Ability: Withers foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.FLAME))
                comp.add(Component.translatable(ChatFormatting.GOLD + "Ability: Sets foe ablaze"));

            if(effect.contains(EssenceArrowEntity.BowEffects.POISON))
                comp.add(Component.translatable(ChatFormatting.GREEN + "Ability: Poisons foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.SLOWNESS))
                comp.add(Component.translatable(ChatFormatting.BLUE + "Ability: Stuns foe"));

            if(effect.contains(EssenceArrowEntity.BowEffects.DOUBLE_ARROW))
                comp.add(Component.translatable(ChatFormatting.BLUE + "Ability: Shoots 2 arrows"));

            if(effect.contains(EssenceArrowEntity.BowEffects.CONSUMES_ESSENCE))
                comp.add(Component.translatable(ChatFormatting.GREEN + "Ability: Consumes " + essence_use + " Essence instead of arrows"));
        }
        comp.add(Component.translatable("Uses remaining: " + ChatFormatting.GRAY + uses));
    }


    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ESSENCE_ARROW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return maxUseDuration;
    }

    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == JItems.ESSENCE_ARROW.get();
    }
}
