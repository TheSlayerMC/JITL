package net.jitl.common.block.crop;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TomatoCropBlock extends CropBlock {

    public TomatoCropBlock() {
        super(JBlockProperties.CROP);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.TOMATO_SEEDS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos) || pState.is(JBlocks.GOLDITE_FARMLAND.get());
    }
}
