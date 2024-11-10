package net.jitl.common.block.base;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class JCropBlock extends CropBlock {

    private final Block farmBlock;

    public JCropBlock(BlockBehaviour.Properties props, DimensionCrops farmBlock) {
        super(props);
        this.farmBlock = farmBlock.getGrowBlock();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NotNull BlockGetter pLevel, @NotNull BlockPos pos) {
        return state.is(this.farmBlock);
    }

    public enum DimensionCrops {

        OVERWORLD(Blocks.FARMLAND),
        EUCA(JBlocks.GOLDITE_FARMLAND.get()),
        DEPTHS(JBlocks.DEPTHS_FARMLAND.get()),
        CORBA(JBlocks.CORBA_FARMLAND.get()),
        CLOUDIA(JBlocks.CLOUDIA_FARMLAND.get())
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
