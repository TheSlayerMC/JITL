package net.jitl.common.block.spawners;

import net.jitl.common.block.entity.spawner.FrostbiterSpawnerEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrostbiterSpawnerBlock extends SpawnerBlock {

    public FrostbiterSpawnerBlock() {
        super(JBlockProperties.SPAWNER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        FrostbiterSpawnerEntity spawner = new FrostbiterSpawnerEntity(pos, state);
        spawner.setEntityId(JEntities.FROZEN_FROSTBITER_TYPE.get(), RandomSource.create());
        return spawner;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JBlockEntities.FROSTBITER_SPAWNER.get(), level_.isClientSide ? FrostbiterSpawnerEntity::clientTick : FrostbiterSpawnerEntity::serverTick);
    }
}
