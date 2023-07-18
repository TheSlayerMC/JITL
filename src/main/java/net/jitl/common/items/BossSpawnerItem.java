package net.jitl.common.items;

import net.jitl.common.block.OkolooPedestalBlock;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BossSpawnerItem extends Item {

    public BossSpawnerItem() {
        super(JItems.itemProps());
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedBlockPos = context.getClickedPos();
        BlockState block = level.getBlockState(clickedBlockPos);
        Player player = context.getPlayer();
        if(player != null) {
            if(this == JItems.BROKEN_OKOLOO_CLUB.get()) {
                if(block == JBlocks.OKOLOO_PEDESTAL.get().defaultBlockState()) {
                    if(!block.getValue(OkolooPedestalBlock.HAS_CLUB)) {
                        BlockState filled_state = block.setValue(OkolooPedestalBlock.HAS_CLUB, Boolean.TRUE);
                        level.setBlock(clickedBlockPos, filled_state, 2);
                        if(!player.isCreative())
                            player.getMainHandItem().shrink(1);
                    }
                }
            }

            if (this == JItems.EUDOR_CROWN.get()) {

            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
