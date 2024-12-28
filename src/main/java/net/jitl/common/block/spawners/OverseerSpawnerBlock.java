package net.jitl.common.block.spawners;

import net.jitl.common.block.entity.spawner.OverseerSpawnerEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
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

public class OverseerSpawnerBlock extends SpawnerBlock {

    public OverseerSpawnerBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        OverseerSpawnerEntity spawner = new OverseerSpawnerEntity(pos, state);
        spawner.setEntityId(JEntities.OVERSEER_TYPE.get(), RandomSource.create());
        return spawner;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JBlockEntities.OVERSEER_SPAWNER.get(), level_.isClientSide ? OverseerSpawnerEntity::clientTick : OverseerSpawnerEntity::serverTick);
    }
}
