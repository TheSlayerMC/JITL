package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class SpineberryCropBlock extends JCropBlock {

    public SpineberryCropBlock() {
        super(DimensionCrops.EUCA);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.SPINEBERRY_SEEDS.get();
    }
}
