package net.jitl.common.block.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class JButtonBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final MapCodec<JButtonBlock> CODEC = RecordCodecBuilder.mapCodec((p_432634_) -> p_432634_.group(BlockSetType.CODEC.fieldOf("block_set_type").forGetter((p_304607_) -> p_304607_.type), Codec.intRange(1, 1024).fieldOf("ticks_to_stay_pressed").forGetter((p_304953_) -> p_304953_.ticksToStayPressed), propertiesCodec()).apply(p_432634_, JButtonBlock::new));
    public static final BooleanProperty POWERED;
    private final BlockSetType type;
    private final int ticksToStayPressed;
    private final Function<BlockState, VoxelShape> shapes;

    public MapCodec<JButtonBlock> codec() {
        return CODEC;
    }

    public JButtonBlock(BlockSetType type, int ticksToStayPressed, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(POWERED, false)).setValue(FACE, AttachFace.WALL));
        this.ticksToStayPressed = ticksToStayPressed;
        this.shapes = this.makeShapes();
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        VoxelShape voxelshape = Block.cube((double)14.0F);
        VoxelShape voxelshape1 = Block.cube((double)12.0F);
        Map<AttachFace, Map<Direction, VoxelShape>> map = Shapes.rotateAttachFace(Block.boxZ((double)6.0F, (double)4.0F, (double)8.0F, (double)16.0F));
        return this.getShapeForEachState((p_393345_) -> Shapes.join((VoxelShape)((Map)map.get(p_393345_.getValue(FACE))).get(p_393345_.getValue(FACING)), (Boolean)p_393345_.getValue(POWERED) ? voxelshape : voxelshape1, BooleanOp.ONLY_FIRST));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (VoxelShape)this.shapes.apply(state);
    }

    protected InteractionResult useWithoutItem(BlockState p_316833_, Level p_316124_, BlockPos p_316184_, Player p_316845_, BlockHitResult p_316247_) {
        if ((Boolean)p_316833_.getValue(POWERED)) {
            return InteractionResult.CONSUME;
        } else {
            this.press(p_316833_, p_316124_, p_316184_, p_316845_);
            return InteractionResult.SUCCESS;
        }
    }

    protected void onExplosionHit(BlockState p_312395_, ServerLevel p_364251_, BlockPos p_311817_, Explosion p_312150_, BiConsumer<ItemStack, BlockPos> p_311898_) {
        if (p_312150_.canTriggerBlocks() && !(Boolean)p_312395_.getValue(POWERED)) {
            this.press(p_312395_, p_364251_, p_311817_, (Player)null);
        }

        super.onExplosionHit(p_312395_, p_364251_, p_311817_, p_312150_, p_311898_);
    }

    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlock(pos, (BlockState)state.setValue(POWERED, true), 3);
        this.updateNeighbours(state, level, pos);
        level.scheduleTick(pos, this, this.ticksToStayPressed);
        this.playSound(player, level, pos, true);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    protected void playSound(@Nullable Player player, LevelAccessor level, BlockPos pos, boolean hitByArrow) {
        level.playSound(hitByArrow ? player : null, pos, this.getSound(hitByArrow), SoundSource.BLOCKS);
    }

    protected SoundEvent getSound(boolean isOn) {
        return isOn ? this.type.buttonClickOn() : this.type.buttonClickOff();
    }

    protected void affectNeighborsAfterRemoval(BlockState p_394009_, ServerLevel p_394160_, BlockPos p_394096_, boolean p_393730_) {
        if (!p_393730_ && (Boolean)p_394009_.getValue(POWERED)) {
            this.updateNeighbours(p_394009_, p_394160_, p_394096_);
        }

    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (Boolean)blockState.getValue(POWERED) ? 15 : 0;
    }

    protected int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (Boolean)blockState.getValue(POWERED) && getConnectedDirection(blockState) == side ? 15 : 0;
    }

    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    protected void tick(BlockState p_220903_, ServerLevel p_220904_, BlockPos p_220905_, RandomSource p_220906_) {
        if ((Boolean)p_220903_.getValue(POWERED)) {
            this.checkPressed(p_220903_, p_220904_, p_220905_);
        }

    }

    protected void entityInside(BlockState p_51083_, Level p_51084_, BlockPos p_51085_, Entity p_51086_, InsideBlockEffectApplier p_405475_, boolean p_451774_) {
        if (!p_51084_.isClientSide() && this.type.canButtonBeActivatedByArrows() && !(Boolean)p_51083_.getValue(POWERED)) {
            this.checkPressed(p_51083_, p_51084_, p_51085_);
        }

    }

    protected void checkPressed(BlockState state, Level level, BlockPos pos) {
        AbstractArrow abstractarrow = this.type.canButtonBeActivatedByArrows() ? (AbstractArrow)level.getEntitiesOfClass(AbstractArrow.class, state.getShape(level, pos).bounds().move(pos)).stream().findFirst().orElse((AbstractArrow) null) : null;
        boolean flag = abstractarrow != null;
        boolean flag1 = (Boolean)state.getValue(POWERED);
        if (flag != flag1) {
            level.setBlock(pos, (BlockState)state.setValue(POWERED, flag), 3);
            this.updateNeighbours(state, level, pos);
            this.playSound((Player)null, level, pos, flag);
            level.gameEvent(abstractarrow, flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
        }

        if (flag) {
            level.scheduleTick(new BlockPos(pos), this, this.ticksToStayPressed);
        }

    }

    private void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(level, direction, direction.getAxis().isHorizontal() ? Direction.UP : (Direction)state.getValue(FACING));
        level.updateNeighborsAt(pos, this, orientation);
        level.updateNeighborsAt(pos.relative(direction), this, orientation);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, POWERED, FACE});
    }

    static {
        POWERED = BlockStateProperties.POWERED;
    }
}
