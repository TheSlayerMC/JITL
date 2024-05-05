package net.jitl.common.items.base;

import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.JGear;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class JSwordItem extends SwordItem implements JGear {
    IAbility ability;

    public JSwordItem(JToolTiers tier, IAbility swordAbility) {
        super(tier.getTier(), JItems.itemProps().attributes(createAttributes(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())));
        ability = swordAbility;
    }

    public JSwordItem(JToolTiers tier, IAbility swordAbility, Properties p) {
        super(tier.getTier(), p.attributes(createAttributes(tier.getTier(), tier.getDamage(), tier.getSpeedModifier())));
        ability = swordAbility;
    }

    @Override
    public IAbility getAbility() {
        return ability;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
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