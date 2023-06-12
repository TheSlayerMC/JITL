package net.jitl.common.items.curios;

import net.jitl.core.helper.TooltipFiller;
import net.jitl.core.init.JITL;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class JCurioItem extends Item implements ICurioItem {
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
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> text, @NotNull TooltipFlag flag) {
        TooltipFiller tooltipFiller = new TooltipFiller(text, Objects.requireNonNull(JITL.getRegistryName(this)).getPath());
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
