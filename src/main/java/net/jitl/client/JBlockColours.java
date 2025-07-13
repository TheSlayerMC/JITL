package net.jitl.client;

import com.mojang.serialization.MapCodec;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = JITL.MOD_ID, value = Dist.CLIENT)
public class JBlockColours implements BlockColor, ItemTintSource {

    public static final JBlockColours BLOCK_COLOUR_INSTANCE = new JBlockColours();
    public static final JBlockColours ITEM_COLOUR_INSTANCE = new JBlockColours();
    public int CORBA_SWAMP = 0x6daa2c;

    @Override
    public int getColor(@NotNull BlockState pState, @Nullable BlockAndTintGetter pLevel, @Nullable BlockPos pPos, int pTintIndex) {
        assert pLevel != null;
        assert pPos != null;
        return BiomeColors.getAverageGrassColor(pLevel, pPos);
    }
    @SubscribeEvent
    public static void registerBlockColours(RegisterColorHandlersEvent.Block event) {
        event.register(BLOCK_COLOUR_INSTANCE,
                JBlocks.CORBA_GRASS.get(),
                JBlocks.CORBA_TALL_GRASS.get(),
                JBlocks.BOGWOOD_LEAVES.get(),
                JBlocks.CORBA_LEAVES.get());
    }

//    @SubscribeEvent
//    public static void registerItemColours(RegisterColorHandlersEvent.ItemTintSources event) {
//        event.register(ITEM_COLOUR_INSTANCE,
//                JBlocks.CORBA_GRASS.get(),
//                JBlocks.CORBA_TALL_GRASS.get(),
//                JBlocks.BOGWOOD_LEAVES.get(),
//                JBlocks.CORBA_LEAVES.get());
//    }

    @Override
    public int calculate(@NotNull ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity) {
        return CORBA_SWAMP;
    }

    @Override
    public @NotNull MapCodec<? extends ItemTintSource> type() {
        return MapCodec.unit(new JBlockColours());
    }
}
