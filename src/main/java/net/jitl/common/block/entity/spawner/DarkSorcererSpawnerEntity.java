package net.jitl.common.block.entity.spawner;

import net.jitl.common.block.entity.base.JSpawnerEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DarkSorcererSpawnerEntity extends JSpawnerEntity {

    private final BaseSpawner spawner = new BaseSpawner() {

        @Override
        public void broadcastEvent(@NotNull Level l, @NotNull BlockPos pos, int id) {
            l.blockEvent(pos, JBlocks.DARK_SORCERER_SPAWNER.get(), id, 0);
        }

        @Override
        public void setNextSpawnData(@Nullable Level l, @NotNull BlockPos p, @NotNull SpawnData s) {
            super.setNextSpawnData(l, p, s);
            if(l != null) {
                BlockState blockstate = l.getBlockState(p);
                l.sendBlockUpdated(p, blockstate, blockstate, 4);
            }
        }

        @Override
        public @NotNull BlockEntity getSpawnerBlockEntity(){
            return DarkSorcererSpawnerEntity.this;
        }
    };

    public DarkSorcererSpawnerEntity(BlockPos pPos, BlockState pBlockState) {
        super(JBlockEntities.DARK_SORCERER_SPAWNER.get(), pPos, pBlockState);
    }

    @Override
    public BaseSpawner getBaseSpawner() {
        return spawner;
    }
}
