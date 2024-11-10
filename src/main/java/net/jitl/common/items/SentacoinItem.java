package net.jitl.common.items;

import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SentacoinItem extends JItem {

    private final Sentacoin.Type type;

    public SentacoinItem(Properties props, Sentacoin.Type type) {
        super(props);
        this.type = type;
    }

    @Override
    public InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        Sentacoin entity = new Sentacoin(type == Sentacoin.Type.BAG ? JEntities.SENTACOIN_BAG_TYPE.get() : JEntities.SENTACOIN_TYPE.get(), player);
        if(!level.isClientSide()) {
            level.addFreshEntity(entity);
            player.getItemInHand(hand).shrink(1);
        }
        return super.use(level, player, hand);
    }
}
