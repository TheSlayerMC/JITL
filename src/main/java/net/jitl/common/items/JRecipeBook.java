package net.jitl.common.items;

import net.jitl.client.gui.overlay.JRecipeBookGUI;
import net.jitl.client.util.ClientUtils;
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

    public JRecipeBook(Properties p) {
        super(p);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        if(level.isClientSide())
            ClientUtils.displayBookGUI(player);
        return super.use(level, player, usedHand);
    }
}
