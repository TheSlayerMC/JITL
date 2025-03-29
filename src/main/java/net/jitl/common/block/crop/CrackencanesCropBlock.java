package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class CrackencanesCropBlock extends JCropBlock {

    public CrackencanesCropBlock(BlockBehaviour.Properties props) {
        super(props, DimensionCrops.DEPTHS);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.CRACKENCANE_SEEDS.get();
    }
}
