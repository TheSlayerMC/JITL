package net.jitl.common.block.portal;

import net.jitl.common.block.portal.logic.JPortal;
import net.jitl.common.block.portal.logic.PortalCoordinatesContainer;
import net.jitl.common.capability.player.Portal;
import net.jitl.common.entity.EntityUtil;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class JBasePortalBlock extends Block implements JPortal {

    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    private final ResourceKey<Level> dimensionID;
    private final Supplier<Block> frame;

    public JBasePortalBlock(BlockBehaviour.Properties properties, ResourceKey<Level> dimID, Supplier<Block> frame) {
        super(properties);
        this.dimensionID = dimID;
        this.frame = frame;
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));
    }

    @Override
    public JBasePortalBlock getPortalBlock() {
        return this;
    }

    @Override
    public Block getPortalFrame() {
        return this.frame.get();
    }

    public ResourceKey<Level> getDimension() {
        return this.dimensionID;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if(state.getValue(BlockStateProperties.HORIZONTAL_AXIS) == Direction.Axis.Z)
            return Z_AABB;

        return X_AABB;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacent, Direction side) {
        if (adjacent.getBlock() == this) {
            Direction.Axis axis;

            switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
                case X -> {
                    axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                    if (axis == Direction.Axis.X)
                        return true;
                }
                case Z -> {
                    axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                    if (axis == Direction.Axis.Z)
                        return true;
                }
                default -> {
                }
            }
        }

        return false;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
                case Z -> state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);
                case X -> state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_AXIS);
    }

    @Override
    public void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (world.isEmptyBlock(pos.above()) || world.isEmptyBlock(pos.below())) {
            world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

            return;
        }

        switch (world.getBlockState(pos).getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
            case Z:
                if (world.isEmptyBlock(pos.east()) || world.isEmptyBlock(pos.west()))
                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                break;
            case X:
                if (world.isEmptyBlock(pos.north()) || world.isEmptyBlock(pos.south()))
                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                break;
        }
    }

    private boolean isCompatibleNeighbour(Level world, BlockPos pos) {
        BlockState block = world.getBlockState(pos);

        return block.getBlock() == this || !block.isAir();
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, Orientation fromPos, boolean isMoving) {
        Direction.Axis facing = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);

        switch (facing) {
            case Z:
                if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.east()) || !isCompatibleNeighbour(world, pos.west()))
                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                break;
            case X:
                if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.north()) || !isCompatibleNeighbour(world, pos.south()))
                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                break;
        }
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {

        SimpleParticleType particle = null;
        SimpleParticleType particle2 = null;

        if(this == JBlocks.EUCA_PORTAL.get()) {
            particle = JParticleManager.GOLDITE_FLOWER.get();
        }

        if(this == JBlocks.FROZEN_PORTAL.get()) {
            particle = JParticleManager.SNOWFLAKE.get();
        }

        if(this == JBlocks.BOIL_PORTAL.get()) {
            particle = JParticleManager.FLAME_POLLEN.get();
            particle2 = JParticleManager.SULPHUR.get();
        }

        if(this == JBlocks.TERRANIAN_PORTAL.get()) {
            particle = JParticleManager.TERRANIA_PORTAL.get();
        }

        if(this == JBlocks.CLOUDIA_PORTAL.get()) {
            particle = JParticleManager.CLOUDIA_PORTAL.get();
        }

        for(int i = 0; i < 3; ++i) {
            double d0 = (double)pos.getX() + random.nextDouble();
            double d1 = (double)pos.getY() + random.nextDouble();
            double d2 = (double)pos.getZ() + random.nextDouble();
            double d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = random.nextFloat() * 2.0F * (float)j;
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = random.nextFloat() * 2.0F * (float)j;
            }

            if(particle != null)
                level.addParticle(particle, d0, d1, d2, d3, d4, d5);

            if(particle2 != null)
                level.addParticle(particle2, d0, d1, d2, d3, d4, d5);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, EntityUtil.getDirectionFacing(context.getPlayer(), true).getAxis());
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
        if(entity.canUsePortal(false)) {
            if(entity instanceof Player player) {
                Portal portal = player.getData(JDataAttachments.PORTAL_OVERLAY);
                portal.setInPortal(this, true);
                int cooldownTime = portal.getPortalTimer();
                assert player.portalProcess != null;
                //if(cooldownTime >= player.portalProcess.getPortalTime()) {
                entity.setAsInsidePortal(this, pos);
                //    portal.setPortalTimer(0);
                //}
            } else {
                entity.setAsInsidePortal(this, pos);
            }
        }
    }

    @Nullable
    @Override
    public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        if (!(entity instanceof ServerPlayer))
            return null;

        final ResourceKey<Level> currentDimension = level.dimension();
        final ResourceKey<Level> portalTargetDimension = getDimension();
        final MinecraftServer server = level.getServer();
        final Optional<PortalCoordinatesContainer> existingLink = Optional.ofNullable(entity instanceof ServerPlayer pl ? pl.getData(JDataAttachments.PORTAL_OVERLAY).getPortalReturnLocation(currentDimension) : null);
        ServerLevel targetLevel = existingLink
                .map(link -> server.getLevel(currentDimension != portalTargetDimension ? portalTargetDimension : link.fromDim()))
                .orElseGet(() -> server.getLevel(currentDimension == portalTargetDimension ? Level.OVERWORLD : portalTargetDimension));

        if (targetLevel == null) {
            if (currentDimension == Level.OVERWORLD)
                return null;

            targetLevel = server.overworld();
        }

        return JPortal.getTransitionForLevel(targetLevel, entity, Optional.of(pos), JPortal.makeSafeCoords(level, targetLevel, entity.position()), this, existingLink);
    }

    public boolean makePortal(LevelAccessor worldIn, BlockPos pos) {
//        if(portal != null) {
//            portal.createPortalBlocks();
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
}
