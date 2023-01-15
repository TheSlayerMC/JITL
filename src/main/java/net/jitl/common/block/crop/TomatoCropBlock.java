package net.jitl.common.block.crop;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import org.jetbrains.annotations.NotNull;

public class TomatoCropBlock extends CropBlock {

    public TomatoCropBlock() {
        super(JBlockProperties.CROP);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.TOMATO_SEEDS.get();
    }
}
