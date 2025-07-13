package net.jitl.common.items;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.ScrollEntries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TestBugItem extends JItem implements IEssenceItem {


    public TestBugItem(Properties p) {
        super(p.stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {

            ItemStack scrollStack = new ItemStack(JItems.LORE_SCROLL.asItem());
            LoreScrollItem.bindScrollEntry(scrollStack, ScrollEntries.TEST, EnumKnowledge.EUCA, 25);
            System.out.println(scrollStack.getComponents());
            player.addItem(scrollStack);

            PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);
            PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
                stats.setLevel(EnumKnowledge.OVERWORLD, 100);
                stats.addSentacoins(1000);

        }
        return InteractionResult.SUCCESS;
    }
}