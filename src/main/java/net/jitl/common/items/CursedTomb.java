package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class CursedTomb extends JItem implements IEssenceItem {

    public CursedTomb(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.LEVITATION, 100);
        if(level instanceof ServerLevel server) {
            if(player.getData(JDataAttachments.ESSENCE).consumeEssence(player, 5)) {
                player.addEffect(effectInstance);
            }
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext pContext, TooltipDisplay display, Consumer<Component> comp, TooltipFlag pTooltipFlag) {
        comp.accept(Component.translatable("jitl.item.desc.cursed_tomb"));
        comp.accept(Component.translatable("jitl.tooltip.essence_usage", 3));
        comp.accept(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}
