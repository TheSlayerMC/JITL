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
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JShovelItem extends ShovelItem implements JGear {

    private final IAbility ability;

    public JShovelItem(Properties p, JToolTiers tier, IAbility ability) {
        super(tier.getTier(), tier.getDamage(), tier.getSpeedModifier(), p);
        this.ability = ability;
    }

    @Override
    public IAbility getAbility() {
        return this.ability;
    }

    @Override
    public @NotNull InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ability.rightClick(playerIn, handIn, worldIn);
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, pTooltipComponents, pTooltipFlag);
        ability.fillTooltips(stack, pTooltipComponents);
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
