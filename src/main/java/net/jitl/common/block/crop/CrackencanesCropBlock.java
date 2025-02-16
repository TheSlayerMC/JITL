package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class CrackencanesCropBlock extends JCropBlock {

    public CrackencanesCropBlock() {
        super(DimensionCrops.DEPTHS);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.CRACKENCANE_SEEDS.get();
    }
}
