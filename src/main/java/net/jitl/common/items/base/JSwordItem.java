package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import java.util.function.Consumer;

public class JSwordItem extends JItem implements JGear {

    private final IAbility ability;

    public JSwordItem(Properties p, JToolTiers tier, IAbility swordAbility) {
        super(p.sword(tier.getTier(), tier.getDamage(), tier.getSpeedModifier()).durability(tier.getTier().durability()));
        this.ability = swordAbility;
    }

    @Override
    public IAbility getAbility() {
        return ability;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ability.rightClick(player, hand, level);
        return super.use(level, player, hand);
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
}