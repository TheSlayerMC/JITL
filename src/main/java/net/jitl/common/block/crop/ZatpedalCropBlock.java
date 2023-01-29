package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class ZatpedalCropBlock extends JCropBlock {

    public ZatpedalCropBlock() {
        super(DimensionCrops.EUCA);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.ZATPEDAL_SEEDS.get();
    }
}
