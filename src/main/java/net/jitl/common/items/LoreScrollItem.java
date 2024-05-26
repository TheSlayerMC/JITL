package net.jitl.common.items;

import net.jitl.client.gui.overlay.KnowledgeToast;
import net.jitl.client.gui.screen.LoreScrollEntryScreen;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.util.ClientTools;
import net.jitl.common.capability.player.LoreScroll;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.items.base.JItem;
import net.jitl.common.scroll.ScrollAPI;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JDataComponents;
import net.jitl.core.init.internal.JItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class LoreScrollItem extends JItem {

    public LoreScrollItem() {
        super(JItems.itemProps().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack heldItem = playerIn.getItemInHand(handIn);
        ScrollEntry entry = getScrollEntry(heldItem);
        LoreScroll scroll = heldItem.get(JDataComponents.SCROLL);
        PlayerStats stats = playerIn.getData(JDataAttachments.PLAYER_STATS);

        if(entry != null && scroll != null) {
            if(!worldIn.isClientSide) {
                if(!scroll.openedBefore()) {
                    stats.addLevel(EnumKnowledge.byName(scroll.knowledge()), scroll.level());
                    heldItem.set(JDataComponents.SCROLL, new LoreScroll(scroll.entry(), scroll.knowledge(), scroll.level(), true));
                }
            } else {
                ClientTools.playLocalSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                displayScrollGui(entry);
                if(!scroll.openedBefore())
                    displayToast(scroll);
            }
        } else {
            if(!worldIn.isClientSide) {
                MutableComponent msg = Component.translatable("scroll.jitl.fail");
                msg.withStyle(ChatFormatting.RED);
                playerIn.sendSystemMessage(msg);
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldItem);
    }

    @OnlyIn(Dist.CLIENT)
    private static void displayToast(LoreScroll scroll) {
        Minecraft.getInstance().getToasts().addToast(new KnowledgeToast(EnumKnowledge.byName(scroll.knowledge()), true));
    }

    @OnlyIn(Dist.CLIENT)
    private static void displayScrollGui(ScrollEntry entry) {
        Minecraft.getInstance().setScreen(new LoreScrollEntryScreen(entry));
    }

    public static void bindScrollEntry(ItemStack stack, ScrollEntry entry, EnumKnowledge knowledge, int level) {
        if(stack.getItem() instanceof LoreScrollItem) {
            stack.set(JDataComponents.SCROLL, new LoreScroll(entry.getId(), knowledge.getName(), level, false));
        } else {
            JITL.LOGGER.error("Provided stack param is not of {}", LoreScrollItem.class, new IllegalArgumentException());
        }
    }

    @Nullable
    public static ScrollEntry getScrollEntry(ItemStack stack) {
        LoreScroll scroll = stack.get(JDataComponents.SCROLL);
        if (stack.getItem() instanceof LoreScrollItem) {
            if (scroll != null) {
                String id = scroll.entry();
                return ScrollAPI.getEntry(id);
            }
        } else {
            JITL.LOGGER.error("Provided stack param is not an {}", LoreScrollItem.class, new IllegalArgumentException());
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack item, TooltipContext c, List<Component> list, TooltipFlag tip) {
        Component overworld = Component.translatable("scroll.jitl.chapter.one").setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA));
        Component nether = Component.translatable("scroll.jitl.chapter.two").setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
        Component end = Component.translatable("scroll.jitl.chapter.three").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE));
        Component boil = Component.translatable("scroll.jitl.chapter.four").setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW));
        Component euca = Component.translatable("scroll.jitl.chapter.five").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD));
        Component depths = Component.translatable("scroll.jitl.chapter.six").setStyle(Style.EMPTY.withColor(ChatFormatting.BLUE));
        Component corba = Component.translatable("scroll.jitl.chapter.seven").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN));
        Component terrania = Component.translatable("scroll.jitl.chapter.eight").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE));
        Component cloudia = Component.translatable("scroll.jitl.chapter.nine").setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE));
        Component senterian = Component.translatable("scroll.jitl.chapter.ten").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY));

        LoreScroll scroll = item.get(JDataComponents.SCROLL);
        if(scroll != null) {
            if(scroll.entry().contains("senterian_gospel")) {
                list.add(corba);
                list.add(Component.translatable("scroll.jitl.name.sentry_gospel").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
            }

            if(scroll.entry().contains("my_last_words")) {
                list.add(overworld);
                list.add(Component.translatable("scroll.jitl.name.my_last_words").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
            }
        }
    }
}
