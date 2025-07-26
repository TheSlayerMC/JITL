package net.jitl.common.items.base;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MultitoolItem extends JItem implements JGear {

    private final IAbility ability;
    private final JToolTiers tier;

    public MultitoolItem(Properties p, JToolTiers tier, IAbility ability) {
        super(p.pickaxe(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())
                .axe(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())
                .shovel(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())
                .hoe(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())
                .sword(tier.getTier(), tier.getDamage(), tier.getSpeedModifier()));
        this.tier = tier;
        this.ability = ability;
    }

    @Override
    public IAbility getAbility() {
        return this.ability;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, display, tooltip, pTooltipFlag);
        ability.fillTooltips(stack, tooltip);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ability.animate(super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged), oldStack, newStack, slotChanged);
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return ability.resetBreak(super.shouldCauseBlockBreakReset(oldStack, newStack), oldStack, newStack);
    }

    public static Consumer<UseOnContext> changeIntoState(BlockState state) {
        return (p_316061_) -> {
            p_316061_.getLevel().setBlock(p_316061_.getClickedPos(), state, 11);
            p_316061_.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, p_316061_.getClickedPos(), GameEvent.Context.of(p_316061_.getPlayer(), state));
        };
    }

    public static Consumer<UseOnContext> changeIntoStateAndDropItem(BlockState state, ItemLike itemToDrop) {
        return (p_316064_) -> {
            p_316064_.getLevel().setBlock(p_316064_.getClickedPos(), state, 11);
            p_316064_.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, p_316064_.getClickedPos(), GameEvent.Context.of(p_316064_.getPlayer(), state));
            Block.popResourceFromFace(p_316064_.getLevel(), p_316064_.getClickedPos(), p_316064_.getClickedFace(), new ItemStack(itemToDrop));
        };
    }

    public static boolean onlyIfAirAbove(UseOnContext context) {
        return context.getClickedFace() != Direction.DOWN && context.getLevel().getBlockState(context.getClickedPos().above()).isAir();
    }

    public InteractionResult useOn(UseOnContext context) {

        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            Player player = context.getPlayer();
            Level level = context.getLevel();
            BlockPos blockpos = context.getClickedPos();
            BlockState blockstate = level.getBlockState(blockpos);

            if(player.isCrouching()) {
                BlockState blockstate1 = blockstate.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
                BlockState blockstate2 = null;
                if (blockstate1 != null && level.getBlockState(blockpos.above()).isAir()) {
                    level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    blockstate2 = blockstate1;
                } else if ((blockstate2 = blockstate.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false)) != null && !level.isClientSide()) {
                    level.levelEvent((Entity) null, 1009, blockpos, 0);
                }

                if (blockstate2 != null) {
                    if (!level.isClientSide) {
                        level.setBlock(blockpos, blockstate2, 11);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                        }
                    }

                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.PASS;
                }
            } else {

                BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
                Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of((Predicate) (ctx) -> true, changeIntoState(toolModifiedState));
                if (pair == null) {
                    return InteractionResult.PASS;
                } else {
                    Predicate<UseOnContext> predicate = (Predicate) pair.getFirst();
                    Consumer<UseOnContext> consumer = (Consumer) pair.getSecond();
                    if (predicate.test(context)) {
                        level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!level.isClientSide) {
                            consumer.accept(context);
                            if (player != null) {
                                context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                            }
                        }

                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.PASS;
                    }
                }
            }
        }
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility) || ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack pStack, @NotNull BlockState pState) {
        return this.tier.getTier().speed();
    }

    @Override
    public void hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(2, pAttacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, @NotNull BlockState state) {
        return true;
    }
}
