package net.jitl.common.items;

import com.mojang.datafixers.util.Pair;
import net.jitl.core.helper.JToolTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class BonemealHoeItem extends HoeItem {


    public BonemealHoeItem(Properties p, JToolTiers tier, int dam) {
        super(tier.getTier(), dam, tier.getSpeedModifier(), p);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of((Predicate) (ctx) -> true, changeIntoState(toolModifiedState));
        if(!context.getPlayer().isCrouching()) {
            if(pair == null) {
                return InteractionResult.PASS;
            } else {
                Predicate<UseOnContext> predicate = pair.getFirst();
                Consumer<UseOnContext> consumer = pair.getSecond();
                if(predicate.test(context)) {
                    Player player = context.getPlayer();
                    level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if(!level.isClientSide()) {
                        consumer.accept(context);
                        if(player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        } else {
            if(context.getPlayer() instanceof Player player) {
                if(BoneMealItem.applyBonemeal(context.getItemInHand(), level, blockpos, context.getPlayer())) {
                    if(!level.isClientSide()) {
                        context.getPlayer().gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                        level.levelEvent(1505, blockpos, 15);
                        context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    }
                    return InteractionResult.SUCCESS;
                } else {
                    if(level.getBlockState(blockpos).isFaceSturdy(level, blockpos, context.getClickedFace())
                            && BoneMealItem.growWaterPlant(context.getItemInHand(), level, blockpos, context.getClickedFace())) {
                        if(!level.isClientSide()) {
                            context.getPlayer().gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                            level.levelEvent(1505, blockpos, 15);
                            context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                        }
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.PASS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> comp, TooltipFlag pTooltipFlag) {
        comp.accept(Component.translatable("jitl.item.desc.bonemeal_hoe"));
        comp.accept(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}
