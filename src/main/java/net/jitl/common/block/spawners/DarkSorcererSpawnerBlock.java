package net.jitl.common.block.spawners;

import net.jitl.common.block.entity.spawner.DarkSorcererSpawnerEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DarkSorcererSpawnerBlock extends SpawnerBlock {

    public DarkSorcererSpawnerBlock() {
        super(JBlockProperties.SPAWNER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        DarkSorcererSpawnerEntity spawner = new DarkSorcererSpawnerEntity(pos, state);
        spawner.setEntityId(JEntities.DARK_SORCERER_TYPE.get(), RandomSource.create());
        return spawner;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JBlockEntities.DARK_SORCERER_SPAWNER.get(), level_.isClientSide ? DarkSorcererSpawnerEntity::clientTick : DarkSorcererSpawnerEntity::serverTick);
    }
}
