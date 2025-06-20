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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class JSpawnerEntity extends BlockEntity implements Spawner {

    public abstract BaseSpawner getBaseSpawner();

    public JSpawnerEntity(BlockEntityType<?> e, BlockPos pPos, BlockState pBlockState) {
        super(e, pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(ValueInput pTag) {
        super.loadAdditional(pTag);
        this.getBaseSpawner().load(this.level, this.worldPosition, pTag);
    }
    
    @Override
    protected void saveAdditional(ValueOutput pTag) {
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

    public void setEntityId(EntityType<?> e, RandomSource r) {
        this.getBaseSpawner().setEntityId(e, this.level, r, this.worldPosition);
        this.setChanged();
    }

    public BaseSpawner getSpawner() {
        return this.getBaseSpawner();
    }
}
