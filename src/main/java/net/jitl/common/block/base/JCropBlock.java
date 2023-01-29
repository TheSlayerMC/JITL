package net.jitl.common.block.base;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class JCropBlock extends CropBlock {

    private final Block farmBlock;

    public JCropBlock(DimensionCrops farmBlock) {
        super(JBlockProperties.CROP);
        this.farmBlock = farmBlock.getGrowBlock();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter pLevel, @NotNull BlockPos pos) {
        return state.is(this.farmBlock);
    }

    public enum DimensionCrops {

        OVERWORLD(Blocks.FARMLAND),
        EUCA(Blocks.FARMLAND),
        DEPTHS(Blocks.FARMLAND),
        CORBA(Blocks.FARMLAND)
            ;

        private final Block growOn;

        DimensionCrops(Block canGrowOn) {
            growOn = canGrowOn;
        }

        public Block getGrowBlock() {
            return growOn;
        }
    }
}
