package net.jitl.common.world.dimension;

import net.jitl.core.helper.MathHelper;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class SenterianToOverworldTeleporter implements ITeleporter {

    protected ServerLevel level;
    protected final ResourceKey<Level> destination;
    protected final BlockPos entrancePos;

    public Optional<BlockUtil.FoundRectangle> getExistingPortal(BlockPos pos) {
        PoiManager poiManager = this.level.getPoiManager();
        poiManager.ensureLoadedAndValid(this.level, pos, 64);
        Optional<PoiRecord> optional = poiManager.getInSquare((poiType) -> {
            assert Dimensions.SENTERIAN_PORTAL.getKey() != null;
            return poiType.is(Dimensions.SENTERIAN_PORTAL.getKey());
        }, pos, 64, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble((poi) ->
                poi.getPos().distSqr(pos)).thenComparingInt((poi) ->
                poi.getPos().getY())).filter((poi) ->
                this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)).findFirst();
        return optional.map((poi) -> {
            BlockPos blockpos = poi.getPos();
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.level.getBlockState(blockpos);
            return BlockUtil.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posIn) ->
                    this.level.getBlockState(posIn) == blockstate);
        });
    }

    public SenterianToOverworldTeleporter(ServerLevel level, ResourceKey<Level> destination, BlockPos entrance) {
        this.level = level;
        this.destination = destination;
        this.entrancePos = entrance;
    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        double posX, posZ, posY;

        BlockPos correctPosition = entrancePos;
        posX = correctPosition.getX();
        posY = correctPosition.getY();
        posZ = correctPosition.getZ();


        short searchRange = 200;
        double var10 = -1.0D;
        int var12 = 0;
        int var13 = 0;
        int var14 = 0;
        int entityPosX_floored = MathHelper.floor(posX);
        int entityPosY = MathHelper.floor(posZ);
        double var24;

        for (int searchX = entityPosX_floored - searchRange; searchX <= entityPosX_floored + searchRange; ++searchX) {
            double var18 = searchX + 0.5D - posX;

            for (int searchZ = entityPosY - searchRange; searchZ <= entityPosY + searchRange; ++searchZ) {
                double var21 = searchZ + 0.5D - posZ;

                for (int searchY = 128 - 1; searchY >= 0; --searchY) {
                    if (this.isBlockPortal(this.level, searchX, searchY, searchZ)) {
                        while (this.isBlockPortal(this.level, searchX, searchY - 1, searchZ)) {
                            --searchY;
                        }

                        var24 = searchY + 0.5D - posY;
                        double var26 = var18 * var18 + var24 * var24 + var21 * var21;

                        if (var10 < 0.0D || var26 < var10) {
                            var10 = var26;
                            var12 = searchX;
                            var13 = searchY;
                            var14 = searchZ;
                        }
                    }
                }
            }
        }
        if (var10 >= 0.0D) {
            double var28 = var12 + 0.5D;
            double var22 = var13 + 0.5D;
            var24 = var14 + 0.5D;
            if (this.isBlockPortal(this.level, var12 - 1, var13, var14)) var28 -= 0.5D;
            if (this.isBlockPortal(this.level, var12 + 1, var13, var14)) var28 += 0.5D;
            if (this.isBlockPortal(this.level, var12, var13, var14 - 1)) var24 -= 0.5D;
            if (this.isBlockPortal(this.level, var12, var13, var14 + 1)) var24 += 0.5D;
            double finalVar2 = var28;
            double finalVar21 = var24;
            return this.getOrMakeOverworldPortal(entity, destWorld, new BlockPos((int) var28, (int) var22, (int) var24)).map((result) -> {
                BlockState blockstate = entity.level().getBlockState(entity.portalEntrancePos);
                Direction.Axis axis = Direction.Axis.X;
                BlockUtil.FoundRectangle rectangle = BlockUtil.getLargestRectangleAround(entity.portalEntrancePos, axis, 21, Direction.Axis.Y, 21, (pos) -> entity.level().getBlockState(pos) == blockstate);
                Vec3 vector3d = entity.getRelativePortalPosition(axis, rectangle);
                entity.teleportTo((int) finalVar2, (int) var22, (int) finalVar21);
                return PortalShape.createPortalInfo(destWorld, result, axis, vector3d, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
            }).orElse(null);
        }
        else
            return null;
    }

    public boolean isBlockPortal(Level var1, int var2, int var3, int var4) {
        return var1.getBlockState(new BlockPos(var2, var3, var4)) == JBlocks.SENTERIAN_PORTAL.get().defaultBlockState();
    }

    protected Optional<BlockUtil.FoundRectangle> getOrMakeOverworldPortal(Entity entity, ServerLevel level, BlockPos pos) {
        Optional<BlockUtil.FoundRectangle> existingPortal = this.getExistingPortal(pos);
        if (existingPortal.isPresent()) {
            return existingPortal;
        } else {
            return this.makeOverworldPortal(pos);
        }
    }

    public Optional<BlockUtil.FoundRectangle> makeOverworldPortal(BlockPos pos) {
        /*int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        Block portal_block = JBlocks.SENTERIAN_PORTAL.get();
        Block portal_frame = JBlocks.SENTERIAN_PORTAL_FRAME.get();
        this.level.setBlock(new BlockPos(x, y, z), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x, y, z + 1), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x, y, z + 2), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 3), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 3), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 3), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z + 1), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 4, y, z + 2), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z - 1), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z - 1), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z - 1), portal_frame.defaultBlockState().setValue(CorbaPortalFrameBlock.HAS_EYE, true), 0);

        this.level.setBlock(new BlockPos(x + 1, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 1), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 1, y, z + 2), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 2, y, z + 2), portal_block.defaultBlockState(), 0);
        this.level.setBlock(new BlockPos(x + 3, y, z + 2), portal_block.defaultBlockState(), 0);*/

        return Optional.of(new BlockUtil.FoundRectangle(pos.immutable(), 1, 1));

    }
}