package net.jitl.common.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class JSpawnerEntity extends BlockEntity implements Spawner {

    public abstract BaseSpawner getBaseSpawner();

    public JSpawnerEntity(BlockEntityType<?> e, BlockPos pPos, BlockState pBlockState) {
        super(e, pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.getBaseSpawner().load(this.level, this.worldPosition, pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
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
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag compoundtag = this.saveCustomOnly(pRegistries);
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
        this.setChanged();
    }

    public BaseSpawner getSpawner() {
        return this.getBaseSpawner();
    }
}
