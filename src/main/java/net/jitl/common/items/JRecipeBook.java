package net.jitl.common.items;

import net.jitl.client.gui.overlay.JRecipeBookGUI;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class JRecipeBook extends Item {

    public JRecipeBook() {
        super(JItems.itemProps());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!level.isClientSide()) {
            Minecraft.getInstance().setScreen(new JRecipeBookGUI(player.getInventory()));
        }
        return super.use(level, player, hand);
    }
}
