package net.jitl.common.items;

import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.knowledge.PlayerKnowledgeProvider;
import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.client.stats.PlayerStatsProvider;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class TestBugItem extends Item implements IEssenceItem {

    public TestBugItem() {
        super(JItems.miscProps().stacksTo(1));
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
                stats.setBlizzard(!stats.hasBlizzard());
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}