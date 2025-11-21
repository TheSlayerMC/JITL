package net.jitl.common.block.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public abstract class JBasePressurePlateBlock extends Block {
    private static final VoxelShape SHAPE_PRESSED = Block.column((double)14.0F, (double)0.0F, (double)0.5F);
    private static final VoxelShape SHAPE = Block.column((double)14.0F, (double)0.0F, (double)1.0F);
    protected static final AABB TOUCH_AABB = (AABB)Block.column((double)14.0F, (double)0.0F, (double)4.0F).toAabbs().getFirst();
    protected final BlockSetType type;

    public JBasePressurePlateBlock(BlockBehaviour.Properties properties, BlockSetType type) {
        super(properties);
        this.type = type;
    }

    protected abstract MapCodec<? extends JBasePressurePlateBlock> codec();

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getSignalForState(state) > 0 ? SHAPE_PRESSED : SHAPE;
    }

    protected int getPressedTime() {
        return 20;
    }

    public boolean isPossibleToRespawnInThis(BlockState p_279155_) {
        return true;
    }

    protected BlockState updateShape(BlockState p_49329_, LevelReader p_374394_, ScheduledTickAccess p_374263_, BlockPos p_49333_, Direction p_49330_, BlockPos p_49334_, BlockState p_49331_, RandomSource p_374547_) {
        return p_49330_ == Direction.DOWN && !p_49329_.canSurvive(p_374394_, p_49333_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_49329_, p_374394_, p_374263_, p_49333_, p_49330_, p_49334_, p_49331_, p_374547_);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return canSupportRigidBlock(level, blockpos) || canSupportCenter(level, blockpos, Direction.UP);
    }

    protected void tick(BlockState p_220768_, ServerLevel p_220769_, BlockPos p_220770_, RandomSource p_220771_) {
        int i = this.getSignalForState(p_220768_);
        if (i > 0) {
            this.checkPressed((Entity)null, p_220769_, p_220770_, p_220768_, i);
        }

    }

    protected void entityInside(BlockState p_49314_, Level p_49315_, BlockPos p_49316_, Entity p_49317_, InsideBlockEffectApplier p_405011_, boolean p_451773_) {
        if (!p_49315_.isClientSide()) {
            int i = this.getSignalForState(p_49314_);
            if (i == 0) {
                this.checkPressed(p_49317_, p_49315_, p_49316_, p_49314_, i);
            }
        }

    }

    private void checkPressed(@Nullable Entity entity, Level level, BlockPos pos, BlockState state, int currentSignal) {
        int i = this.getSignalStrength(level, pos);
        boolean flag = currentSignal > 0;
        boolean flag1 = i > 0;
        if (currentSignal != i) {
            BlockState blockstate = this.setSignalForState(state, i);
            level.setBlock(pos, blockstate, 2);
            this.updateNeighbours(level, pos);
            level.setBlocksDirty(pos, state, blockstate);
        }

        if (!flag1 && flag) {
            level.playSound((Entity)null, pos, this.type.pressurePlateClickOff(), SoundSource.BLOCKS);
            level.gameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (flag1 && !flag) {
            level.playSound((Entity)null, pos, this.type.pressurePlateClickOn(), SoundSource.BLOCKS);
            level.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }

        if (flag1) {
            level.scheduleTick(new BlockPos(pos), this, this.getPressedTime());
        }

    }

    protected void affectNeighborsAfterRemoval(BlockState p_394236_, ServerLevel p_394295_, BlockPos p_393547_, boolean p_394138_) {
        if (!p_394138_ && this.getSignalForState(p_394236_) > 0) {
            this.updateNeighbours(p_394295_, p_393547_);
        }

    }

    protected void updateNeighbours(Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return this.getSignalForState(blockState);
    }

    protected int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return side == Direction.UP ? this.getSignalForState(blockState) : 0;
    }

    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    protected static int getEntityCount(Level level, AABB box, Class<? extends Entity> entityClass) {
        return level.getEntitiesOfClass(entityClass, box, EntitySelector.NO_SPECTATORS.and((p_289691_) -> !p_289691_.isIgnoringBlockTriggers())).size();
    }

    protected abstract int getSignalStrength(Level var1, BlockPos var2);

    protected abstract int getSignalForState(BlockState var1);

    protected abstract BlockState setSignalForState(BlockState var1, int var2);
}
