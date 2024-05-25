package net.jitl.common.items;

import net.jitl.client.gui.screen.LoreScrollEntryScreen;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.LoreScroll;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.scroll.ScrollAPI;
import net.jitl.common.scroll.ScrollCategory;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JDataComponents;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class LoreScrollItem extends Item {

    public LoreScrollItem() {
        super(JItems.itemProps().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack heldItem = playerIn.getItemInHand(handIn);
        if (worldIn.isClientSide) {
            ScrollEntry entry = getScrollEntry(heldItem);
            LoreScroll scroll = heldItem.get(JDataComponents.SCROLL);
            if (entry != null && scroll != null) {
                //ClientTools.playLocalSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                displayScrollGui(null, entry);
                if(!scroll.openedBefore()) {
                    if(!scroll.knowledge().isEmpty()) {
                        PlayerStats stats = playerIn.getData(JDataAttachments.PLAYER_STATS);
                        stats.addXP(EnumKnowledge.byName(scroll.knowledge()), scroll.xp(), playerIn);
                    }
                    heldItem.set(JDataComponents.SCROLL, new LoreScroll(scroll.entry(), scroll.knowledge(), scroll.xp(), true));
                }
            } else {
                //ChatUtils.sendInformativeError(JITL.MODID, playerIn, "Can't retrieve entry from provided itemstack.", Pair.of("Itemstack", heldItem), Pair.of("Tag Compound", heldItem.getTag()));
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldItem);
    }

    @OnlyIn(Dist.CLIENT)
    private static void displayScrollGui(ScrollCategory category, ScrollEntry entry) {
        Minecraft.getInstance().setScreen(new LoreScrollEntryScreen(category, entry));
    }

    /**
     * Writes scroll entry into provided itemstack.
     * If itemstack is not an ItemLoreScroll item, it will print the error and won't write nbt tag.
     */
    public static void bindScrollEntry(ItemStack stack, ScrollEntry entry, EnumKnowledge knowledge, float xp) {
        if (stack.getItem() instanceof LoreScrollItem) {
            stack.set(JDataComponents.SCROLL, new LoreScroll(entry.getId(), knowledge.getName(), xp, false));
        } else {
            JITL.LOGGER.error("Provided stack param is not of {}", LoreScrollItem.class, new IllegalArgumentException());
        }
    }

    /**
     * Returns scroll entry of provided itemstack.
     * If provided itemstack is not an ItemLoreScroll item, it will print an error and return null.
     * If provided itemstack doesn't have tag compound, or there is no 'entry' record in it, it will return null.
     *
     * @return scroll entry of provided itemstack
     */
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
}
