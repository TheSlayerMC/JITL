package net.jitl.common.items;

import net.jitl.client.knowledge.PlayerKnowledgeProvider;
import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TestBugItem extends JItem implements IEssenceItem {

    public TestBugItem() {
        super(JItems.itemProps().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {

            });
            player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {

            });
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                //stats.setBlizzard(!stats.hasBlizzard());
                stats.addSentacoins(10);
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}