package net.jitl.common.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public abstract class JSpawnerEntity extends BlockEntity {

    public abstract BaseSpawner getBaseSpawner();

    public JSpawnerEntity(BlockEntityType<?> e, BlockPos pPos, BlockState pBlockState) {
        super(e, pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.getBaseSpawner().load(this.level, this.worldPosition, pTag);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        this.getBaseSpawner().save(pTag);
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, JSpawnerEntity pBlockEntity) {
        pBlockEntity.getBaseSpawner().clientTick(pLevel, pPos);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, JSpawnerEntity pBlockEntity) {
        pBlockEntity.getBaseSpawner().serverTick((ServerLevel)pLevel, pPos);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compoundtag = this.saveWithoutMetadata();
        compoundtag.remove("SpawnPotentials");
        return compoundtag;
    }

    @Override
    public boolean triggerEvent(int pId, int pType) {
        assert this.level != null;
        return this.getBaseSpawner().onEventTriggered(this.level, pId) || super.triggerEvent(pId, pType);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public void setEntityId(EntityType<?> e, RandomSource r) {
        this.getBaseSpawner().setEntityId(e, this.level, r, this.worldPosition);
    }

    public BaseSpawner getSpawner() {
        return this.getBaseSpawner();
    }
}
