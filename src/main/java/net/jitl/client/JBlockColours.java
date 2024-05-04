package net.jitl.client;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JBlockColours implements BlockColor, ItemColor {

    public static final JBlockColours BLOCK_COLOUR_INSTANCE = new JBlockColours();
    public static final JBlockColours ITEM_COLOUR_INSTANCE = new JBlockColours();
    public int CORBA_SWAMP = 0x6daa2c;

    @Override
    public int getColor(@NotNull BlockState pState, @Nullable BlockAndTintGetter pLevel, @Nullable BlockPos pPos, int pTintIndex) {
        assert pLevel != null;
        assert pPos != null;
        return BiomeColors.getAverageGrassColor(pLevel, pPos);
    }

    @Override
    public int getColor(@NotNull ItemStack pStack, int pTintIndex) {
        return CORBA_SWAMP;
    }

    @SubscribeEvent
    public static void registerBlockColours(RegisterColorHandlersEvent.Block event) {
        event.register(BLOCK_COLOUR_INSTANCE,
                JBlocks.CORBA_GRASS.get(),
                JBlocks.CORBA_TALL_GRASS.get(),
                JBlocks.BOGWOOD_LEAVES.get(),
                JBlocks.CORBA_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColours(RegisterColorHandlersEvent.Item event) {
        event.register(ITEM_COLOUR_INSTANCE,
                JBlocks.CORBA_GRASS.get(),
                JBlocks.CORBA_TALL_GRASS.get(),
                JBlocks.BOGWOOD_LEAVES.get(),
                JBlocks.CORBA_LEAVES.get());
    }
}
