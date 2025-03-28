package net.jitl.common.block.base;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class JWallBlock extends Block implements SimpleWaterloggedBlock {
   public static final BooleanProperty UP = BlockStateProperties.UP;
   public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;
   public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;
   public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
   public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
   private final Map<BlockState, VoxelShape> shapeByIndex;
   private final Map<BlockState, VoxelShape> collisionShapeByIndex;
   private static final VoxelShape POST_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
   private static final VoxelShape NORTH_TEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
   private static final VoxelShape SOUTH_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
   private static final VoxelShape WEST_TEST = Block.box(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
   private static final VoxelShape EAST_TEST = Block.box(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);

   public JWallBlock(BlockBehaviour.Properties pProperties) {
      super(pProperties);
      this.registerDefaultState(this.stateDefinition.any().setValue(UP, Boolean.TRUE).setValue(NORTH_WALL, WallSide.NONE).setValue(EAST_WALL, WallSide.NONE).setValue(SOUTH_WALL, WallSide.NONE).setValue(WEST_WALL, WallSide.NONE).setValue(WATERLOGGED, Boolean.FALSE));
      this.shapeByIndex = this.makeShapes(16.0F, 14.0F, 16.0F);
      this.collisionShapeByIndex = this.makeShapes(24.0F, 24.0F, 24.0F);
   }

   private static VoxelShape applyWallShape(VoxelShape pBaseShape, WallSide pHeight, VoxelShape pLowShape, VoxelShape pTallShape) {
      if (pHeight == WallSide.TALL) {
         return Shapes.or(pBaseShape, pTallShape);
      } else {
         return pHeight == WallSide.LOW ? Shapes.or(pBaseShape, pLowShape) : pBaseShape;
      }
   }

   private Map<BlockState, VoxelShape> makeShapes(float pWallPostHeight, float pWallLowHeight, float pWallTallHeight) {
      float f = 8.0F - (float) 4.0;
      float f1 = 8.0F + (float) 4.0;
      float f2 = 8.0F - (float) 3.0;
      float f3 = 8.0F + (float) 3.0;
      VoxelShape voxelshape = Block.box(f, 0.0D, f, f1, pWallPostHeight, f1);
      VoxelShape voxelshape1 = Block.box(f2, (float) 0.0, 0.0D, f3, pWallLowHeight, f3);
      VoxelShape voxelshape2 = Block.box(f2, (float) 0.0, f2, f3, pWallLowHeight, 16.0D);
      VoxelShape voxelshape3 = Block.box(0.0D, (float) 0.0, f2, f3, pWallLowHeight, f3);
      VoxelShape voxelshape4 = Block.box(f2, (float) 0.0, f2, 16.0D, pWallLowHeight, f3);
      VoxelShape voxelshape5 = Block.box(f2, (float) 0.0, 0.0D, f3, pWallTallHeight, f3);
      VoxelShape voxelshape6 = Block.box(f2, (float) 0.0, f2, f3, pWallTallHeight, 16.0D);
      VoxelShape voxelshape7 = Block.box(0.0D, (float) 0.0, f2, f3, pWallTallHeight, f3);
      VoxelShape voxelshape8 = Block.box(f2, (float) 0.0, f2, 16.0D, pWallTallHeight, f3);
      ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

      for(Boolean obool : UP.getPossibleValues()) {
         for(WallSide wallside : EAST_WALL.getPossibleValues()) {
            for(WallSide wallside1 : NORTH_WALL.getPossibleValues()) {
               for(WallSide wallside2 : WEST_WALL.getPossibleValues()) {
                  for(WallSide wallside3 : SOUTH_WALL.getPossibleValues()) {
                     VoxelShape voxelshape9 = Shapes.empty();
                     voxelshape9 = applyWallShape(voxelshape9, wallside, voxelshape4, voxelshape8);
                     voxelshape9 = applyWallShape(voxelshape9, wallside2, voxelshape3, voxelshape7);
                     voxelshape9 = applyWallShape(voxelshape9, wallside1, voxelshape1, voxelshape5);
                     voxelshape9 = applyWallShape(voxelshape9, wallside3, voxelshape2, voxelshape6);
                     if (obool) {
                        voxelshape9 = Shapes.or(voxelshape9, voxelshape);
                     }

                     BlockState blockstate = this.defaultBlockState().setValue(UP, obool).setValue(EAST_WALL, wallside).setValue(WEST_WALL, wallside2).setValue(NORTH_WALL, wallside1).setValue(SOUTH_WALL, wallside3);
                     builder.put(blockstate.setValue(WATERLOGGED, Boolean.FALSE), voxelshape9);
                     builder.put(blockstate.setValue(WATERLOGGED, Boolean.TRUE), voxelshape9);
                  }
               }
            }
         }
      }

      return builder.build();
   }

   @Override
   public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
      return this.shapeByIndex.get(pState);
   }

   @Override
   public @NotNull VoxelShape getCollisionShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
      return this.collisionShapeByIndex.get(pState);
   }

   @Override
   protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
      return false;
   }

   private boolean connectsTo(BlockState pState, boolean pSideSolid, Direction pDirection) {
      Block block = pState.getBlock();
      boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(pState, pDirection);
      return pState.is(BlockTags.WALLS) || block instanceof JWallBlock || block instanceof WallBlock || !isExceptionForConnection(pState) && pSideSolid || block instanceof IronBarsBlock || flag;
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext pContext) {
      LevelReader levelreader = pContext.getLevel();
      BlockPos blockpos = pContext.getClickedPos();
      FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
      BlockPos blockpos1 = blockpos.north();
      BlockPos blockpos2 = blockpos.east();
      BlockPos blockpos3 = blockpos.south();
      BlockPos blockpos4 = blockpos.west();
      BlockPos blockpos5 = blockpos.above();
      BlockState blockstate = levelreader.getBlockState(blockpos1);
      BlockState blockstate1 = levelreader.getBlockState(blockpos2);
      BlockState blockstate2 = levelreader.getBlockState(blockpos3);
      BlockState blockstate3 = levelreader.getBlockState(blockpos4);
      BlockState blockstate4 = levelreader.getBlockState(blockpos5);
      boolean flag = this.connectsTo(blockstate, blockstate.isFaceSturdy(levelreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
      boolean flag1 = this.connectsTo(blockstate1, blockstate1.isFaceSturdy(levelreader, blockpos2, Direction.WEST), Direction.WEST);
      boolean flag2 = this.connectsTo(blockstate2, blockstate2.isFaceSturdy(levelreader, blockpos3, Direction.NORTH), Direction.NORTH);
      boolean flag3 = this.connectsTo(blockstate3, blockstate3.isFaceSturdy(levelreader, blockpos4, Direction.EAST), Direction.EAST);
      BlockState blockstate5 = this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
      return this.updateShape(levelreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3);
   }

   @Override
   public BlockState updateShape(BlockState stateIn, LevelReader reader, ScheduledTickAccess tick, BlockPos currentPos, Direction dir, BlockPos facingPos, BlockState state, RandomSource source) {
      if (!stateIn.canSurvive(reader, currentPos)) {
         tick.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(reader));
      }
      if (dir == Direction.DOWN) {
         return super.updateShape(stateIn, reader, tick, currentPos, dir, facingPos, state, source);
      } else {
         return dir == Direction.UP ? this.topUpdate(reader, stateIn, facingPos, state) : this.sideUpdate(reader, currentPos, stateIn, facingPos, state, dir);
      }
   }

   private static boolean isConnected(BlockState pState, Property<WallSide> pHeightProperty) {
      return pState.getValue(pHeightProperty) != WallSide.NONE;
   }

   private static boolean isCovered(VoxelShape pFirstShape, VoxelShape pSecondShape) {
      return !Shapes.joinIsNotEmpty(pSecondShape, pFirstShape, BooleanOp.ONLY_FIRST);
   }

   private BlockState topUpdate(LevelReader pLevel, BlockState pState, BlockPos pPos, BlockState pSecondState) {
      boolean flag = isConnected(pState, NORTH_WALL);
      boolean flag1 = isConnected(pState, EAST_WALL);
      boolean flag2 = isConnected(pState, SOUTH_WALL);
      boolean flag3 = isConnected(pState, WEST_WALL);
      return this.updateShape(pLevel, pState, pPos, pSecondState, flag, flag1, flag2, flag3);
   }

   private BlockState sideUpdate(LevelReader pLevel, BlockPos pFirstPos, BlockState pFirstState, BlockPos pSecondPos, BlockState pSecondState, Direction pDir) {
      Direction direction = pDir.getOpposite();
      boolean flag = pDir == Direction.NORTH ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, NORTH_WALL);
      boolean flag1 = pDir == Direction.EAST ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, EAST_WALL);
      boolean flag2 = pDir == Direction.SOUTH ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, SOUTH_WALL);
      boolean flag3 = pDir == Direction.WEST ? this.connectsTo(pSecondState, pSecondState.isFaceSturdy(pLevel, pSecondPos, direction), direction) : isConnected(pFirstState, WEST_WALL);
      BlockPos blockpos = pFirstPos.above();
      BlockState blockstate = pLevel.getBlockState(blockpos);
      return this.updateShape(pLevel, pFirstState, blockpos, blockstate, flag, flag1, flag2, flag3);
   }

   private BlockState updateShape(LevelReader pLevel, BlockState pState, BlockPos pPos, BlockState pNeighbour, boolean pNorthConnection, boolean pEastConnection, boolean pSouthConnection, boolean pWestConnection) {
      VoxelShape voxelshape = pNeighbour.getCollisionShape(pLevel, pPos).getFaceShape(Direction.DOWN);
      BlockState blockstate = this.updateSides(pState, pNorthConnection, pEastConnection, pSouthConnection, pWestConnection, voxelshape);
      return blockstate.setValue(UP, this.shouldRaisePost(blockstate, pNeighbour, voxelshape));
   }

   private boolean shouldRaisePost(BlockState pState, BlockState pNeighbour, VoxelShape pShape) {
      boolean flag = pNeighbour.getBlock() instanceof WallBlock && pNeighbour.getValue(UP);
      if (flag) {
         return true;
      } else {
         WallSide wallside = pState.getValue(NORTH_WALL);
         WallSide wallside1 = pState.getValue(SOUTH_WALL);
         WallSide wallside2 = pState.getValue(EAST_WALL);
         WallSide wallside3 = pState.getValue(WEST_WALL);
         boolean flag1 = wallside1 == WallSide.NONE;
         boolean flag2 = wallside3 == WallSide.NONE;
         boolean flag3 = wallside2 == WallSide.NONE;
         boolean flag4 = wallside == WallSide.NONE;
         boolean flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
         if (flag5) {
            return true;
         } else {
            boolean flag6 = wallside == WallSide.TALL && wallside1 == WallSide.TALL || wallside2 == WallSide.TALL && wallside3 == WallSide.TALL;
            if (flag6) {
               return false;
            } else {
               return pNeighbour.is(BlockTags.WALL_POST_OVERRIDE) || isCovered(pShape, POST_TEST);
            }
         }
      }
   }

   private BlockState updateSides(BlockState pState, boolean pNorthConnection, boolean pEastConnection, boolean pSouthConnection, boolean pWestConnection, VoxelShape pWallShape) {
      return pState.setValue(NORTH_WALL, this.makeWallState(pNorthConnection, pWallShape, NORTH_TEST)).setValue(EAST_WALL, this.makeWallState(pEastConnection, pWallShape, EAST_TEST)).setValue(SOUTH_WALL, this.makeWallState(pSouthConnection, pWallShape, SOUTH_TEST)).setValue(WEST_WALL, this.makeWallState(pWestConnection, pWallShape, WEST_TEST));
   }

   private WallSide makeWallState(boolean pAllowConnection, VoxelShape pShape, VoxelShape pNeighbourShape) {
      if (pAllowConnection) {
         return isCovered(pShape, pNeighbourShape) ? WallSide.TALL : WallSide.LOW;
      } else {
         return WallSide.NONE;
      }
   }

   @Override
   public @NotNull FluidState getFluidState(BlockState pState) {
      return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
   }

   @Override
   protected boolean propagatesSkylightDown(BlockState pState) {
      return !pState.getValue(WATERLOGGED);
   }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
      pBuilder.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, WATERLOGGED);
   }

   @Override
   public @NotNull BlockState rotate(@NotNull BlockState pState, Rotation pRotation) {
      return switch (pRotation) {
         case CLOCKWISE_180 ->
                 pState.setValue(NORTH_WALL, pState.getValue(SOUTH_WALL)).setValue(EAST_WALL, pState.getValue(WEST_WALL)).setValue(SOUTH_WALL, pState.getValue(NORTH_WALL)).setValue(WEST_WALL, pState.getValue(EAST_WALL));
         case COUNTERCLOCKWISE_90 ->
                 pState.setValue(NORTH_WALL, pState.getValue(EAST_WALL)).setValue(EAST_WALL, pState.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, pState.getValue(WEST_WALL)).setValue(WEST_WALL, pState.getValue(NORTH_WALL));
         case CLOCKWISE_90 ->
                 pState.setValue(NORTH_WALL, pState.getValue(WEST_WALL)).setValue(EAST_WALL, pState.getValue(NORTH_WALL)).setValue(SOUTH_WALL, pState.getValue(EAST_WALL)).setValue(WEST_WALL, pState.getValue(SOUTH_WALL));
         default -> pState;
      };
   }

   @Override
   public @NotNull BlockState mirror(@NotNull BlockState pState, Mirror pMirror) {
      return switch (pMirror) {
         case LEFT_RIGHT ->
                 pState.setValue(NORTH_WALL, pState.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, pState.getValue(NORTH_WALL));
         case FRONT_BACK ->
                 pState.setValue(EAST_WALL, pState.getValue(WEST_WALL)).setValue(WEST_WALL, pState.getValue(EAST_WALL));
         default -> super.mirror(pState, pMirror);
      };
   }
}