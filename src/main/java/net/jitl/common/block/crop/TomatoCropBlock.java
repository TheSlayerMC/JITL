package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class TomatoCropBlock extends JCropBlock {

    public TomatoCropBlock() {
        super(DimensionCrops.OVERWORLD);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.TOMATO_SEEDS.get();
    }
}
