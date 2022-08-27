package net.jitl.common.items;

import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class StaffItem extends Item implements IEssenceItem {

    public StaffItem() {
        super(JItems.rangedProps());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if(!level.isClientSide()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if(essence.consumeEssence(player, 1)) {

                }
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}