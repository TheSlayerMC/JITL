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
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public class HealingItem extends JItem implements IEssenceItem {

    public float amount = 0.0F;

    public HealingItem(Properties props, float amount) {
        super(props.stacksTo(1).durability(1));
        this.amount = amount;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        if(!level.isClientSide) {
            player.heal(this.amount == -1F ? player.getMaxHealth() : this.amount);
            player.getItemInHand(usedHand).shrink(1);
            return InteractionResult.CONSUME;
        } else {
            level.playSound(player, player.getOnPos(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag pTooltipFlag) {
        if (amount == -1F) {
            tooltip.accept(Component.translatable("jitl.item.desc.full_health"));
        } else {
            tooltip.accept(Component.literal("Restores " + amount + " health"));
        }
    }
}
