package net.jitl.common.block;


import com.mojang.serialization.MapCodec;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CrystallizedDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    
    public static final MapCodec<CrystallizedDripstoneBlock> CODEC = simpleCodec(CrystallizedDripstoneBlock::new);
    public static final EnumProperty<Direction> TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 16.0F, 11.0F);
    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0F, 5.0F, 5.0F, 11.0F, 16.0F, 11.0F);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 16.0F, 13.0F);
    private static final VoxelShape BASE_SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);

    @Override
    public @NotNull MapCodec<CrystallizedDripstoneBlock> codec() {
        return CODEC;
    }

    public CrystallizedDripstoneBlock(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_154157_) {
        p_154157_.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    @Override
    protected boolean canSurvive(BlockState s, @NotNull LevelReader l, @NotNull BlockPos p) {
        return isValidPointedDripstonePlacement(l, p, s.getValue(TIP_DIRECTION));
    }

    @Override
    protected BlockState updateShape(BlockState p_154147_, LevelReader p_374104_, ScheduledTickAccess p_374078_, BlockPos p_154151_, Direction p_154148_, BlockPos p_154152_, BlockState p_154149_, RandomSource p_374393_) {
        if ((Boolean)p_154147_.getValue(WATERLOGGED)) {
            p_374078_.scheduleTick(p_154151_, Fluids.WATER, Fluids.WATER.getTickDelay(p_374104_));
        }

        if (p_154148_ != Direction.UP && p_154148_ != Direction.DOWN) {
            return p_154147_;
        } else {
            Direction direction = (Direction)p_154147_.getValue(TIP_DIRECTION);
            if (direction == Direction.DOWN && p_374078_.getBlockTicks().hasScheduledTick(p_154151_, this)) {
                return p_154147_;
            } else if (p_154148_ == direction.getOpposite() && !this.canSurvive(p_154147_, p_374104_, p_154151_)) {
                if (direction == Direction.DOWN) {
                    p_374078_.scheduleTick(p_154151_, this, 2);
                } else {
                    p_374078_.scheduleTick(p_154151_, this, 1);
                }

                return p_154147_;
            } else {
                boolean flag = p_154147_.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstonethickness = calculateDripstoneThickness(p_374104_, p_154151_, direction, flag);
                return (BlockState)p_154147_.setValue(THICKNESS, dripstonethickness);
            }
        }
    }

    @Override
    protected void onProjectileHit(Level p_154042_, BlockState p_154043_, BlockHitResult p_154044_, Projectile p_154045_) {
        if (!p_154042_.isClientSide) {
            BlockPos blockpos = p_154044_.getBlockPos();
            if (p_154042_ instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)p_154042_;
                if (p_154045_.mayInteract(serverlevel, blockpos) && p_154045_.mayBreak(serverlevel) && p_154045_ instanceof ThrownTrident && p_154045_.getDeltaMovement().length() > 0.6) {
                    p_154042_.destroyBlock(blockpos, true);
                }
            }
        }

    }

    @Override
    public void fallOn(Level p_154047_, BlockState p_154048_, BlockPos p_154049_, Entity p_154050_, double p_397761_) {
        if (p_154048_.getValue(TIP_DIRECTION) == Direction.UP && p_154048_.getValue(THICKNESS) == DripstoneThickness.TIP) {
            p_154050_.causeFallDamage(p_397761_ + (double)2.5F, 2.0F, p_154047_.damageSources().stalagmite());
        } else {
            super.fallOn(p_154047_, p_154048_, p_154049_, p_154050_, p_397761_);
        }

    }

    @Override
    protected void tick(@NotNull BlockState s, @NotNull ServerLevel l, @NotNull BlockPos p, @NotNull RandomSource r) {
        if(isStalagmite(s) && !this.canSurvive(s, l, p)) {
            l.destroyBlock(p, true);
        } else {
            spawnFallingStalactite(s, l, p);
        }

    }

    @Override
    protected void randomTick(@NotNull BlockState s, @NotNull ServerLevel l, @NotNull BlockPos p, RandomSource r) {
        if(r.nextFloat() < 0.011377778F && isStalactiteStartPos(s, l, p)) {
            growStalactiteOrStalagmiteIfPossible(s, l, p, r);
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_154040_) {
        LevelAccessor levelaccessor = p_154040_.getLevel();
        BlockPos blockpos = p_154040_.getClickedPos();
        Direction direction = p_154040_.getNearestLookingVerticalDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if(direction1 == null) {
            return null;
        } else {
            boolean flag = !p_154040_.isSecondaryUseActive();
            DripstoneThickness dripstonethickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
            return dripstonethickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, direction1).setValue(THICKNESS, dripstonethickness).setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
        }
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState p_154235_) {
        return p_154235_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_154235_);
    }

    @Override
    protected @NotNull VoxelShape getOcclusionShape(BlockState p_154170_) {
        return Shapes.empty();
    }

    protected VoxelShape getShape(BlockState p_154117_, BlockGetter p_154118_, BlockPos p_154119_, CollisionContext p_154120_) {
        VoxelShape var10000;
        switch ((DripstoneThickness)p_154117_.getValue(THICKNESS)) {
            case TIP_MERGE -> var10000 = TIP_MERGE_SHAPE;
            case TIP -> var10000 = p_154117_.getValue(TIP_DIRECTION) == Direction.DOWN ? TIP_SHAPE_DOWN : TIP_SHAPE_UP;
            case FRUSTUM -> var10000 = FRUSTUM_SHAPE;
            case MIDDLE -> var10000 = MIDDLE_SHAPE;
            case BASE -> var10000 = BASE_SHAPE;
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        VoxelShape voxelshape = var10000;
        return voxelshape.move(p_154117_.getOffset(p_154119_));
    }
    
    @Override
    protected boolean isCollisionShapeFullBlock(BlockState p_181235_, BlockGetter p_181236_, BlockPos p_181237_) {
        return false;
    }

    @Override
    protected float getMaxHorizontalOffset() {
        return 0.125F;
    }

    @Override
    public void onBrokenAfterFall(Level p_154059_, BlockPos p_154060_, FallingBlockEntity p_154061_) {
        if(!p_154061_.isSilent()) {
            p_154059_.levelEvent(1045, p_154060_, 0);
        }

    }

    @Override
    public DamageSource getFallDamageSource(Entity p_254432_) {
        return p_254432_.damageSources().fallingStalactite(p_254432_);
    }

    private static void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(BlockState blockstate = state; isStalactite(blockstate); blockstate = level.getBlockState(blockpos$mutableblockpos)) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, blockpos$mutableblockpos, blockstate);
            if(isTip(blockstate, true)) {
                int i = Math.max(1 + pos.getY() - blockpos$mutableblockpos.getY(), 6);
                float f = 1.0F * (float)i;
                fallingblockentity.setHurtsEntities(f, 40);
                break;
            }

            blockpos$mutableblockpos.move(Direction.DOWN);
        }

    }

    @VisibleForTesting
    public static void growStalactiteOrStalagmiteIfPossible(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = level.getBlockState(pos.above(1));
        BlockState blockstate1 = level.getBlockState(pos.above(2));
        if(canGrow(blockstate, blockstate1)) {
            BlockPos blockpos = findTip(state, level, pos, 7, false);
            if(blockpos != null) {
                BlockState blockstate2 = level.getBlockState(blockpos);
                if(canDrip(blockstate2) && canTipGrow(blockstate2, level, blockpos)) {
                    if(random.nextBoolean()) {
                        grow(level, blockpos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, blockpos);
                    }
                }
            }
        }

    }

    @Override
    public void animateTick(BlockState p_221870_, Level p_221871_, BlockPos p_221872_, RandomSource p_221873_) {
        if(canDrip(p_221870_)) {
            float f = p_221873_.nextFloat();
            if(!(f > 0.12F)) {
               spawnDripParticle(p_221871_, p_221872_, p_221870_);
            }
        }

    }

    private static void growStalagmiteBelow(ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(int i = 0; i < 10; ++i) {
            blockpos$mutableblockpos.move(Direction.DOWN);
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
            if(!blockstate.getFluidState().isEmpty()) {
                return;
            }

            if(isUnmergedTipWithDirection(blockstate, Direction.UP) && canTipGrow(blockstate, level, blockpos$mutableblockpos)) {
                grow(level, blockpos$mutableblockpos, Direction.UP);
                return;
            }

            if(isValidPointedDripstonePlacement(level, blockpos$mutableblockpos, Direction.UP) && !level.isWaterAt(blockpos$mutableblockpos.below())) {
                grow(level, blockpos$mutableblockpos.below(), Direction.UP);
                return;
            }
        }
    }

    private static void grow(ServerLevel server, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = server.getBlockState(blockpos);
        if(isUnmergedTipWithDirection(blockstate, direction.getOpposite())) {
            createMergedTips(blockstate, server, blockpos);
        } else if(blockstate.isAir() || blockstate.is(Blocks.WATER)) {
            createDripstone(server, blockpos, direction, DripstoneThickness.TIP);
        }

    }

    private static void createDripstone(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
        BlockState blockstate = JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get().defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
        level.setBlock(pos, blockstate, 3);
    }

    private static void createMergedTips(BlockState state, LevelAccessor level, BlockPos pos) {
        BlockPos blockpos;
        BlockPos blockpos1;
        if(state.getValue(TIP_DIRECTION) == Direction.UP) {
            blockpos1 = pos;
            blockpos = pos.above();
        } else {
            blockpos = pos;
            blockpos1 = pos.below();
        }
        createDripstone(level, blockpos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(level, blockpos1, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    private static void spawnDripParticle(Level level, BlockPos pos, BlockState state) {
        Vec3 vec3 = state.getOffset(pos);
        double d1 = (double)pos.getX() + (double)0.5F + vec3.x;
        double d2 = (double)((float)(pos.getY() + 1) - 0.6875F) - (double)0.0625F;
        double d3 = (double)pos.getZ() + (double)0.5F + vec3.z;
        Fluid fluid = Fluids.WATER;
        ParticleOptions particleoptions = fluid.getFluidType().getDripInfo() != null ? fluid.getFluidType().getDripInfo().dripParticle() : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        if(particleoptions != null) {
            level.addParticle(particleoptions, d1, d2, d3, (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }

    @Nullable
    private static BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int maxIterations, boolean isTipMerge) {
        if (isTip(state, isTipMerge)) {
            return pos;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> bipredicate = (p_373975_, p_373976_) -> p_373976_.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE) && p_373976_.getValue(TIP_DIRECTION) == direction;
            return (BlockPos)findBlockVertical(level, pos, direction.getAxisDirection(), bipredicate, (p_154168_) -> isTip(p_154168_, isTipMerge), maxIterations).orElse((BlockPos)null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction dir) {
        Direction direction;
        if(isValidPointedDripstonePlacement(level, pos, dir)) {
            direction = dir;
        } else {
            if(!isValidPointedDripstonePlacement(level, pos, dir.getOpposite())) 
                return null;
            direction = dir.getOpposite();
        }
        return direction;
    }

    private static DripstoneThickness calculateDripstoneThickness(LevelReader level, BlockPos pos, Direction dir, boolean isTipMerge) {
        Direction direction = dir.getOpposite();
        BlockState blockstate = level.getBlockState(pos.relative(dir));
        if(!isPointedDripstoneWithDirection(blockstate, direction)) {
            if(!isPointedDripstoneWithDirection(blockstate, dir)) {
                return DripstoneThickness.TIP;
            } else {
                DripstoneThickness dripstonethickness = blockstate.getValue(THICKNESS);
                if(dripstonethickness != DripstoneThickness.TIP && dripstonethickness != DripstoneThickness.TIP_MERGE) {
                    BlockState blockstate1 = level.getBlockState(pos.relative(direction));
                    return !isPointedDripstoneWithDirection(blockstate1, dir) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
                } else {
                    return DripstoneThickness.FRUSTUM;
                }
            }
        } else {
            return !isTipMerge && blockstate.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        }
    }

    public static boolean canDrip(BlockState state) {
        return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !(Boolean)state.getValue(WATERLOGGED);
    }

    private static boolean canTipGrow(BlockState state, ServerLevel level, BlockPos pos) {
        Direction direction = state.getValue(TIP_DIRECTION);
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);
        if(!blockstate.getFluidState().isEmpty()) {
            return false;
        } else {
            return blockstate.isAir() || isUnmergedTipWithDirection(blockstate, direction.getOpposite());
        }
    }

    private static boolean isValidPointedDripstonePlacement(LevelReader level, BlockPos pos, Direction dir) {
        BlockPos blockpos = pos.relative(dir.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, dir) || isPointedDripstoneWithDirection(blockstate, dir);
    }

    private static boolean isTip(BlockState state, boolean isTipMerge) {
        if(!state.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get())) {
            return false;
        } else {
            DripstoneThickness dripstonethickness = state.getValue(THICKNESS);
            return dripstonethickness == DripstoneThickness.TIP || isTipMerge && dripstonethickness == DripstoneThickness.TIP_MERGE;
        }
    }

    private static boolean isUnmergedTipWithDirection(BlockState state, Direction dir) {
        return isTip(state, false) && state.getValue(TIP_DIRECTION) == dir;
    }

    private static boolean isStalactite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
        return isStalactite(state) && !level.getBlockState(pos.above()).is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get());
    }

    protected boolean isPathfindable(@NotNull BlockState b, @NotNull PathComputationType p) {
        return false;
    }

    private static boolean isPointedDripstoneWithDirection(BlockState state, Direction dir) {
        return state.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get()) && state.getValue(TIP_DIRECTION) == dir;
    }
    
    private static boolean canGrow(BlockState dripstoneState, BlockState state) {
        return dripstoneState.is(JBlocks.CRYSTALLIZED_DRIPSTONE) && state.is(Blocks.WATER) && state.getFluidState().isSource();
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection axis, BiPredicate<BlockPos, BlockState> positionalStatePredicate, Predicate<BlockState> statePredicate, int maxIterations) {
        Direction direction = Direction.get(axis, Direction.Axis.Y);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(int i = 1; i < maxIterations; ++i) {
            blockpos$mutableblockpos.move(direction);
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
            if (statePredicate.test(blockstate)) {
                return Optional.of(blockpos$mutableblockpos.immutable());
            }

            if (level.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !positionalStatePredicate.test(blockpos$mutableblockpos, blockstate)) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
