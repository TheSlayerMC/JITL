package net.jitl.common.world.dimension;

import net.jitl.common.block.portal.DepthsPortalBlock;
import net.jitl.common.block.portal.DepthsPortalFrameBlock;
import net.jitl.common.world.Surface;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class DepthsTeleporter extends BaseTeleporter {

    protected final ServerLevel level;
    private final DepthsPortalBlock portal_block;
    private final Block portal_frame;
    protected final ResourceKey<PoiType> poi;
    protected final ResourceKey<Level> destination;
    private static final int SEARCH_RADIUS = 256;
    private static final int hardcodedDepthsPortalLimit = 41;

    public DepthsTeleporter(ServerLevel worldIn, DepthsPortalBlock portal, Block frame, ResourceKey<PoiType> poi, ResourceKey<Level> destination) {
        super(worldIn, portal, frame, poi, destination);
        this.level = worldIn;
        this.portal_block = portal;
        this.portal_frame = frame;
        this.poi = poi;
        this.destination = destination;
    }

    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        entity.setPortalCooldown();

        if(destWorld.dimension() == Dimensions.DEPTHS) {
            PortalInfo ruinedPortal = repairPortal(destWorld, destWorld.findNearestMapStructure(StructureTags.RUINED_PORTAL, getHeight(level, entity.portalEntrancePos.getX(), entity.portalEntrancePos.getZ()), SEARCH_RADIUS, false), entity);
            if(ruinedPortal != null) return ruinedPortal;
        }

        return findPlayerMadePortal(destWorld, entity, SEARCH_RADIUS);
    }

    private PortalInfo repairPortal(ServerLevel level, BlockPos position, Entity entity) {
        if(position == null) return null;
        int surface = Surface.getSurface(Surface.Surface_Type.HIGHEST_GROUND, Surface.Mode.FULL, 16, 80, 0, level, null, position.getX(), position.getZ());
        for(int x = position.getX() - 11; x < position.getX() + 11; x++) for(int y = surface - 7; y < surface + 7; y++) for(int z = position.getZ() - 11; z < position.getZ() + 11; z++) if(level.getBlockState(new BlockPos(x, y, z)).is(portal_block))
            return new PortalInfo(new Vec3(x, y, z), Vec3.ZERO, entity.yRotO, entity.xRotO);
        BlockPos pos = null;
        Block tile = JBlocks.DEPTHS_GRASS.get();
        loop: for(int x = position.getX() - 11; x < position.getX() + 11; x++) for(int y = surface - 7; y < surface + 7; y++) for(int z = position.getZ() - 11; z < position.getZ() + 11; z++) if(level.getBlockState(pos = new BlockPos(x, y, z)).is(tile)) {
            Block brick = JBlocks.DEPTHS_GRASS.get();
            pos = level.getBlockState(pos.offset(-1, 0, -1)).is(brick) ? pos.offset(-5, 0, -5)
                    : level.getBlockState(pos.offset(1, 0, -1)).is(brick) ? pos.offset(-1, 0, -5)
                    : level.getBlockState(pos.offset(-1, 0, 1)).is(brick) ? pos.offset(-5, 0, -1)
                    : level.getBlockState(pos.offset(1, 0, 1)).is(brick) ? pos.offset(-1, 0, -1) : null;
            break loop;
        }
        if(pos == null) return null;
        return new PortalInfo(new Vec3(pos.getX() + 2, pos.getY() + 1, pos.getZ() + 2), Vec3.ZERO, entity.yRotO, entity.xRotO);
    }

    private PortalInfo findPlayerMadePortal(ServerLevel level, Entity entity, int searchRadius) {
        BlockPos pos = entity.blockPosition();
        PoiManager poiManager = level.getPoiManager();
        poiManager.ensureLoadedAndValid(level, pos, searchRadius);
        Optional<PoiRecord> optional = poiManager.getInSquare((poiType) ->
                poiType.is(poi), pos, searchRadius, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble((poi) ->
                poi.getPos().distSqr(pos)).thenComparingInt((poi) ->
                poi.getPos().getY())).findFirst();
        BlockPos blockpos;
        if(optional.isEmpty()) {
            BlockUtil.FoundRectangle r = makePortal(pos, null).get();
            blockpos = r.minCorner.offset(2, 0, 2);
            level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            return new PortalInfo(new Vec3(r.minCorner.getX() + 2, r.minCorner.getY(), r.minCorner.getZ() + 2), Vec3.ZERO, entity.yRotO, entity.xRotO);
        }
        blockpos = optional.get().getPos();
        return new PortalInfo(new Vec3(blockpos.getX(), blockpos.getY(), blockpos.getZ()), Vec3.ZERO, entity.yRotO, entity.xRotO);
    }

    @Override
    public Optional<BlockUtil.FoundRectangle> makePortal(BlockPos pos, Direction.Axis axis) {
        pos = getHeight(level, pos.getX(), pos.getZ());
        for (int x = -2; x < 3; x++)
            for (int z = -2; z < 3; z++) {
                if (Math.abs(x) < 2 && Math.abs(z) < 2)
                    level.setBlock(pos.offset(x, 0, z), portal_block.defaultBlockState(), 3);
                else if (Math.abs(z) < 2) {
                    if (x == -2)
                        level.setBlock(pos.offset(x, 0, z), portal_frame.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST).setValue(DepthsPortalFrameBlock.HAS_EYE, true), 3);
                    else if (x == 2)
                        level.setBlock(pos.offset(x, 0, z), portal_frame.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST).setValue(DepthsPortalFrameBlock.HAS_EYE, true), 3);
                } else if (Math.abs(x) < 2) {
                    if (z == -2)
                        level.setBlock(pos.offset(x, 0, z), portal_frame.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH).setValue(DepthsPortalFrameBlock.HAS_EYE, true), 3);
                    else
                        level.setBlock(pos.offset(x, 0, z), portal_frame.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH).setValue(DepthsPortalFrameBlock.HAS_EYE, true), 3);
                }
            }

        for(int x = -5; x < 5; x++) {
            for(int z = -5; z < 5; z++) {
                level.setBlock(pos.offset(x, -1, z), JBlocks.DEPTHS_GRASS.get().defaultBlockState(), 3);
            }
        }

        for(int x = -2; x < 3; x++) {
            for(int z = -2; z < 3; z++) {
                level.setBlock(pos.offset(x, 1, z), Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(pos.offset(x, 2, z), Blocks.AIR.defaultBlockState(), 3);
            }
        }
        return Optional.of(new BlockUtil.FoundRectangle(pos, 5, 5));
    }

    protected BlockPos getHeight(ServerLevel level, int posX, int posZ) {
        int limit = 32;
        for(int y = limit; y > 0; y--) {
            BlockState block = level.getBlockState(new BlockPos(posX, y, posZ));
            if(!block.is(Blocks.AIR))
                return new BlockPos(posX, y + 1, posZ);
        }
        return new BlockPos(posX, limit, posZ);
    }

}