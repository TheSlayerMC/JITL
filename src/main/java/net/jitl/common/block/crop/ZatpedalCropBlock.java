package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class ZatpedalCropBlock extends JCropBlock {

    public ZatpedalCropBlock(BlockBehaviour.Properties props) {
        super(props, DimensionCrops.EUCA);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.ZATPEDAL_SEEDS.get();
    }
}
