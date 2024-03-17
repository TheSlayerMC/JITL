package net.jitl.common.items.base;

import net.jitl.core.init.internal.JItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JItem extends Item {

    public JItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        addItemDesc(JItems.SENTRY_EYE.get(), tooltip, "jitl.item.desc.sentry_eye");

        addItemDesc(JItems.ANCIENT_CHUNK.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_FRAGMENT.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_PIECE.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_SHARD.get(), tooltip, "jitl.item.desc.ancient_socket");
        addItemDesc(JItems.ANCIENT_EYE_OF_OPENING.get(), tooltip, "jitl.item.desc.ancient_catalyst");

    }

    public void addItemDesc(Item item, List<Component> tooltip, String descLoc) {
        if(this == item) {
            tooltip.add((Component.translatable(descLoc)));
        }
    }
}
