package net.jitl.common.items;

import net.jitl.client.gui.overlay.JRecipeBookGUI;
import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class JRecipeBook extends JItem {

    public JRecipeBook() {
        super(JItems.itemProps());
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        if(level.isClientSide())
           displayBookGUI(player);
        return super.use(level, player, usedHand);
    }

    @OnlyIn(Dist.CLIENT)
    public static void displayBookGUI(Player player) {
        Minecraft.getInstance().setScreen(new JRecipeBookGUI(player.getInventory()));
    }
}
