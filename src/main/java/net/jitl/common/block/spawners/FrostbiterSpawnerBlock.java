package net.jitl.common.block.spawners;

import net.jitl.common.block.entity.spawner.FrostbiterSpawnerEntity;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrostbiterSpawnerBlock extends SpawnerBlock {

    public FrostbiterSpawnerBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        FrostbiterSpawnerEntity spawner = new FrostbiterSpawnerEntity(pos, state);
        spawner.setEntityId(JEntities.FROZEN_FROSTBITER_TYPE.get(), RandomSource.create());
        return spawner;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JBlockEntities.FROSTBITER_SPAWNER.get(), level_.isClientSide ? FrostbiterSpawnerEntity::clientTick : FrostbiterSpawnerEntity::serverTick);
    }
}
