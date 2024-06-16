package net.jitl.common.block.portal;

import com.mojang.logging.LogUtils;
import net.jitl.common.capability.player.Portal;
import net.jitl.common.world.dimension.BaseTeleporter;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class JBasePortalBlock extends Block implements net.minecraft.world.level.block.Portal {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    private final ResourceKey<Level> dimensionID;
    private final Supplier<Block> frame;

    public JBasePortalBlock(ResourceKey<Level> dimID, Supplier<Block> frame) {
        super(JBlockProperties.PORTAL);
        this.dimensionID = dimID;
        this.frame = frame;
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {

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
            double d0 = (double)pPos.getX() + pRandom.nextDouble();
            double d1 = (double)pPos.getY() + pRandom.nextDouble();
            double d2 = (double)pPos.getZ() + pRandom.nextDouble();
            double d3 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            int j = pRandom.nextInt(2) * 2 - 1;
            if (!pLevel.getBlockState(pPos.west()).is(this) && !pLevel.getBlockState(pPos.east()).is(this)) {
                d0 = (double)pPos.getX() + 0.5D + 0.25D * (double)j;
                d3 = pRandom.nextFloat() * 2.0F * (float)j;
            } else {
                d2 = (double)pPos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = pRandom.nextFloat() * 2.0F * (float)j;
            }

            if(particle != null)
                pLevel.addParticle(particle, d0, d1, d2, d3, d4, d5);

            if(particle2 != null)
                pLevel.addParticle(particle2, d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        Direction.Axis direction$axis = pFacing.getAxis();
        Direction.Axis direction$axis1 = pState.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && !pFacingState.is(this) && !new PortalShape(pLevel, pCurrentPos, direction$axis1).isComplete()
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> {
                return switch (state.getValue(AXIS)) {
                    case Z -> state.setValue(AXIS, Direction.Axis.X);
                    case X -> state.setValue(AXIS, Direction.Axis.Z);
                    default -> state;
                };
            }
            default -> {
                return state;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }


    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
        if(entity.canUsePortal(false)) {
            if(entity instanceof Player player) {
                Portal portal = player.getData(JDataAttachments.PORTAL_OVERLAY);
                portal.setInPortal(this, true);
                int cooldownTime = portal.getPortalTimer();
                assert player.portalProcess != null;
                if(cooldownTime >= player.portalProcess.getPortalTime()) {
                    entity.setAsInsidePortal(this, pos);
                    portal.setPortalTimer(0);
                }
            } else {
                entity.setAsInsidePortal(this, pos);
            }
        }
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player player
                ? Math.max(
                1,
                level.getGameRules()
                        .getInt(
                                player.getAbilities().invulnerable
                                        ? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
                                        : GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY
                        )
        ) : 0;
    }

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> destination = entity.level().dimension() == dimensionID ? Level.OVERWORLD : dimensionID;
        ServerLevel destinationWorld = level.getServer().getLevel(destination);
        
        if (destinationWorld == null) {
            return null;
        } else {
            boolean flag = destinationWorld.dimension() == dimensionID;
            WorldBorder worldborder = level.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(level.dimensionType(), destinationWorld.dimensionType());
            BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
            return this.getExitPortal(destinationWorld, entity, pos, blockpos, flag, worldborder);
        }
    }

    @Nullable
    private DimensionTransition getExitPortal(ServerLevel level, Entity entity, BlockPos pos1, BlockPos pos2, boolean isDim, WorldBorder border) {
        JPortalForcer forcer = new JPortalForcer(level, this, this.frame.get(), getPoi());
        Optional<BlockPos> optional = forcer.findClosestPortalPosition(pos2, isDim, border);
        BlockUtil.FoundRectangle rec;
        DimensionTransition.PostDimensionTransition dimTrans;
        if(optional.isPresent()) {
            BlockPos blockpos = optional.get();
            BlockState blockstate = level.getBlockState(blockpos);
            rec = BlockUtil.getLargestRectangleAround(
                    blockpos,
                    blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                    21,
                    Direction.Axis.Y,
                    21,
                    p_351970_ -> level.getBlockState(p_351970_) == blockstate
            );
            dimTrans = DimensionTransition.PLAY_PORTAL_SOUND.then(p_351967_ -> p_351967_.placePortalTicket(blockpos));
        } else {
            Direction.Axis axis = entity.level().getBlockState(pos1).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> optional1 = forcer.createPortal(pos2, axis);
            if (optional1.isEmpty()) {
                LogUtils.getLogger().error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            rec = optional1.get();
            dimTrans = DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET);
        }
        return getDimensionTransitionFromExit(entity, pos1, rec, level, dimTrans);
    }

    public ResourceKey<PoiType> getPoi() {
        ResourceKey<PoiType> poi = Dimensions.FROZEN_PORTAL.getKey();

        if(this == JBlocks.EUCA_PORTAL.get())
            poi = Dimensions.EUCA_PORTAL.getKey();

        if(this == JBlocks.FROZEN_PORTAL.get())
            poi = Dimensions.FROZEN_PORTAL.getKey();

        if(this == JBlocks.BOIL_PORTAL.get())
            poi = Dimensions.BOIL_PORTAL.getKey();

        if(this == JBlocks.DEPTHS_PORTAL.get())
            poi = Dimensions.DEPTHS_PORTAL.getKey();

        if(this == JBlocks.CORBA_PORTAL.get())
            poi = Dimensions.CORBA_PORTAL.getKey();

        if(this == JBlocks.TERRANIAN_PORTAL.get())
            poi = Dimensions.TERRANIAN_PORTAL.getKey();

        if(this == JBlocks.CLOUDIA_PORTAL.get())
            poi = Dimensions.CLOUDIA_PORTAL.getKey();
        return poi;
    }

    private static DimensionTransition getDimensionTransitionFromExit(Entity entity, BlockPos pos, BlockUtil.FoundRectangle rect, ServerLevel level, DimensionTransition.PostDimensionTransition dimTrans) {
        BlockState blockstate = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 vec3;
        if(blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle r = BlockUtil.getLargestRectangleAround(
                    pos, axis, 21, Direction.Axis.Y, 21, 
                    p -> entity.level().getBlockState(p) == blockstate
            );
            vec3 = entity.getRelativePortalPosition(axis, r);
        } else {
            axis = Direction.Axis.X;
            vec3 = new Vec3(0.5, 0.0, 0.0);
        }
        return createDimensionTransition(level, rect, axis, vec3, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), dimTrans);
    }

    private static DimensionTransition createDimensionTransition(ServerLevel level, BlockUtil.FoundRectangle rect, Direction.Axis axis, Vec3 vec, Entity entity, Vec3 movement, float yRot, float xRot, DimensionTransition.PostDimensionTransition dimTrans) {
        BlockPos blockpos = rect.minCorner;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d0 = rect.axis1Size;
        double d1 = rect.axis2Size;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == direction$axis ? 0 : 90;
        Vec3 vec3 = axis == direction$axis ? movement : new Vec3(movement.z, movement.y, -movement.x);
        double d2 = (double)entitydimensions.width() / 2.0 + (d0 - (double)entitydimensions.width()) * vec.x();
        double d3 = (d1 - (double)entitydimensions.height()) * vec.y();
        double d4 = 0.5 + vec.z();
        boolean flag = direction$axis == Direction.Axis.X;
        Vec3 vec31 = new Vec3((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec32 = PortalShape.findCollisionFreePosition(vec31, level, entity, entitydimensions);
        return new DimensionTransition(level, vec32, vec3, yRot + (float)i, xRot, dimTrans);
    }

    @Override
    public @NotNull Transition getLocalTransition() {
        return net.minecraft.world.level.block.Portal.Transition.CONFUSION;
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
