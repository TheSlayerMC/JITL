package net.jitl.common.items;

import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SentacoinItem extends Item {

    private final Sentacoin.Type type;

    public SentacoinItem(Sentacoin.Type type) {
        super(JItems.itemProps());
        this.type = type;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        Sentacoin entity = new Sentacoin(type == Sentacoin.Type.BAG ? JEntities.SENTACOIN_BAG_TYPE.get() : JEntities.SENTACOIN_TYPE.get(), player);
        if(!level.isClientSide()) {
            level.addFreshEntity(entity);
            player.getItemInHand(hand).shrink(1);
        }
        return super.use(level, player, hand);
    }
}
