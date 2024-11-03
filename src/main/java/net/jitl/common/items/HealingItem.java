package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HealingItem extends JItem implements IEssenceItem {

    public float amount = 0.0F;

    public HealingItem(float amount) {
        super(JItems.itemProps().stacksTo(1).durability(1));
        this.amount = amount;
    }

    @Override
    public InteractionResult use(Level world, @NotNull Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!world.isClientSide) {
            player.heal(this.amount == -1F ? player.getMaxHealth() : this.amount);
            player.getItemInHand(hand).shrink(1);
            return InteractionResult.CONSUME;
        } else {
            world.playSound(player, player.getOnPos(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        if (amount == -1F) {
            tooltip.add(Component.translatable("jitl.item.desc.full_health"));
        } else {
            tooltip.add(Component.literal("Restores " + amount + " health"));
        }
    }
}
