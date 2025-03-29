package net.jitl.common.items;

import net.jitl.common.capability.essence.PlayerEssence;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class BottleEssenciaItem extends JItem implements IEssenceItem {

    private boolean strong;

    public BottleEssenciaItem(Properties p, boolean strong) {
        super(p);
        this.strong = strong;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        Player player = pEntityLiving instanceof Player ? (Player)pEntityLiving : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, pStack);
        }

        if(player != null) {
            if(!pLevel.isClientSide) {
                PlayerEssence essence = player.getData(JDataAttachments.ESSENCE);
                essence.addEssence(player, strong ? 8 : 4);
            }

            if (!player.getAbilities().instabuild) {
                pStack.shrink(1);
            }
        }
        pEntityLiving.gameEvent(GameEvent.DRINK);
        return pStack;
    }

    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    public ItemUseAnimation getUseAnimation(ItemStack pStack) {
        return ItemUseAnimation.DRINK;
    }

    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return this.strong;
    }
}