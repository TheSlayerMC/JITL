package net.jitl.common.block;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.MapCodec;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.jitl.common.block.base.JBlock;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

public class CrystallizedDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    
    public static final MapCodec<CrystallizedDripstoneBlock> CODEC = simpleCodec(CrystallizedDripstoneBlock::new);
    public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
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
    protected @NotNull BlockState updateShape(BlockState s, @NotNull Direction d, @NotNull BlockState s2, @NotNull LevelAccessor level, @NotNull BlockPos pos1, @NotNull BlockPos pos2) {
        if(s.getValue(WATERLOGGED)) 
            level.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        
        if(d != Direction.UP && d != Direction.DOWN) {
            return s;
        } else {
            Direction direction = s.getValue(TIP_DIRECTION);
            if(direction == Direction.DOWN && level.getBlockTicks().hasScheduledTick(pos1, this)) {
                return s;
            } else if(d == direction.getOpposite() && !this.canSurvive(s, level, pos1)) {
                if(direction == Direction.DOWN) {
                    level.scheduleTick(pos1, this, 2);
                } else {
                    level.scheduleTick(pos1, this, 1);
                }
                return s;
            } else {
                boolean flag = s.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstonethickness = calculateDripstoneThickness(level, pos1, direction, flag);
                return s.setValue(THICKNESS, dripstonethickness);
            }
        }
    }

    @Override
    protected void onProjectileHit(Level l, @NotNull BlockState s, @NotNull BlockHitResult b, @NotNull Projectile p) {
        if(!l.isClientSide) {
            BlockPos blockpos = b.getBlockPos();
            if(p.mayInteract(l, blockpos) && p.mayBreak(l) && p instanceof ThrownTrident && p.getDeltaMovement().length() > 0.6) 
                l.destroyBlock(blockpos, true);
        }
    }

    @Override
    public void fallOn(@NotNull Level l, BlockState d, @NotNull BlockPos p, @NotNull Entity e, float f) {
        if(d.getValue(TIP_DIRECTION) == Direction.UP && d.getValue(THICKNESS) == DripstoneThickness.TIP) {
            e.causeFallDamage(f + 2.0F, 2.0F, l.damageSources().stalagmite());
        } else {
            super.fallOn(l, d, p, e, f);
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
    protected @NotNull VoxelShape getOcclusionShape(BlockState p_154170_, BlockGetter p_154171_, BlockPos p_154172_) {
        return Shapes.empty();
    }

    protected @NotNull VoxelShape getShape(BlockState p_154117_, BlockGetter p_154118_, BlockPos p_154119_, CollisionContext p_154120_) {
        DripstoneThickness dripstonethickness = p_154117_.getValue(THICKNESS);
        VoxelShape voxelshape;
        if(dripstonethickness == DripstoneThickness.TIP_MERGE) {
            voxelshape = TIP_MERGE_SHAPE;
        } else if(dripstonethickness == DripstoneThickness.TIP) {
            if(p_154117_.getValue(TIP_DIRECTION) == Direction.DOWN) {
                voxelshape = TIP_SHAPE_DOWN;
            } else {
                voxelshape = TIP_SHAPE_UP;
            }
        } else if(dripstonethickness == DripstoneThickness.FRUSTUM) {
            voxelshape = FRUSTUM_SHAPE;
        } else if(dripstonethickness == DripstoneThickness.MIDDLE) {
            voxelshape = MIDDLE_SHAPE;
        } else {
            voxelshape = BASE_SHAPE;
        }

        Vec3 vec3 = p_154117_.getOffset(p_154118_, p_154119_);
        return voxelshape.move(vec3.x, 0.0F, vec3.z);
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
        Vec3 vec3 = state.getOffset(level, pos);
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
        if(isTip(state, isTipMerge)) {
            return pos;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> bipredicate = (p_202023_, p_202024_) -> p_202024_.is(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get()) && p_202024_.getValue(TIP_DIRECTION) == direction;
            return findBlockVertical(level, pos, direction.getAxisDirection(), bipredicate, (p_154168_) -> isTip(p_154168_, isTipMerge), maxIterations).orElse(null);
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
        Direction direction = Direction.get(axis, Axis.Y);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(int i = 1; i < maxIterations; i++) {
            blockpos$mutableblockpos.move(direction);
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
            if(statePredicate.test(blockstate)) 
                return Optional.of(blockpos$mutableblockpos.immutable());
            
            if(level.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !positionalStatePredicate.test(blockpos$mutableblockpos, blockstate)) 
                return Optional.empty();
        }

        return Optional.empty();
    }
}
