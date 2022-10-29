package net.jitl.common.block;

import net.jitl.common.block.entity.JSpawnerBlockEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JBlockSpawner extends BaseEntityBlock {

    private final EntityType<?> entity;

    public JBlockSpawner(EntityType<?> entity) {
        super(JBlockProperties.SPAWNER);
        this.entity = entity;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        JSpawnerBlockEntity spawner = new JSpawnerBlockEntity(pos, state, this);
        spawner.getSpawner().setEntityId(this.entity);
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
        return createTickerHelper(blockEntityType_, JBlockEntities.JSPAWNER.get(), level_.isClientSide ? JSpawnerBlockEntity::clientTick : JSpawnerBlockEntity::serverTick);
    }
}
