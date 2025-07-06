package net.jitl.common.items.curios;

import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.TooltipFiller;
import net.jitl.core.init.JITL;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Objects;
import java.util.function.Consumer;

public class JCurioItem extends JItem {//todo implements ICurioItem {
    private boolean hasOverview;
    private boolean hasAbility;
    private boolean hasNegativeEffects;

    public JCurioItem(Properties properties) {
        super(properties);
    }

    public JCurioItem overview(boolean hasOverview) {
        this.hasOverview = hasOverview;
        return this;
    }

    public JCurioItem ability(boolean hasAbility) {
        this.hasAbility = hasAbility;
        return this;
    }

    public JCurioItem drawback(boolean hasNegativeEffects) {
        this.hasNegativeEffects = hasNegativeEffects;
        return this;
    }

//    @Override
//    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
//        return true;
//    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> tip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(stack, pContext, display, tip, pTooltipFlag);
        TooltipFiller tooltipFiller = new TooltipFiller(tip, Objects.requireNonNull(JITL.getRegistryName(this)).getPath());
        if (hasOverview) {
            tooltipFiller.addOverview();
        }
        if (hasAbility) {
            tooltipFiller.addDetail();
        }
        if (hasNegativeEffects) {
            tooltipFiller.addDrawback();
        }
    }
}
