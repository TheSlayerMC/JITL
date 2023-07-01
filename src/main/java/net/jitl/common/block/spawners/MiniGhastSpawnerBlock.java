package net.jitl.common.block.spawners;

import net.jitl.common.block.entity.spawner.MiniGhastSpawnerEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MiniGhastSpawnerBlock extends SpawnerBlock {

    public MiniGhastSpawnerBlock() {
        super(JBlockProperties.SPAWNER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        MiniGhastSpawnerEntity spawner = new MiniGhastSpawnerEntity(pos, state);
        spawner.setEntityId(JEntities.MINI_GHAST_TYPE.get(), RandomSource.create());
        return spawner;
    }

    @NotNull
    @Override
    public ItemStack getCloneItemStack(BlockGetter level_, BlockPos pos_, BlockState state_) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JBlockEntities.MINI_GHAST_SPAWNER.get(), level_.isClientSide ? MiniGhastSpawnerEntity::clientTick : MiniGhastSpawnerEntity::serverTick);
    }
}
