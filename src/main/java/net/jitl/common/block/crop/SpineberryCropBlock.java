package net.jitl.common.block.crop;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import org.jetbrains.annotations.NotNull;

public class SpineberryCropBlock extends CropBlock {

    public SpineberryCropBlock() {
        super(JBlockProperties.CROP);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.SPINEBERRY_SEEDS.get();
    }
}
