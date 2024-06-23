package net.jitl.common.block.portal.logic;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.jitl.common.block.portal.DepthsPortalFrameBlock;
import net.jitl.common.block.portal.JBasePortalBlock;
import net.jitl.common.world.WorldUtil;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface DepthsPortal extends Portal {

    IdentityHashMap<ResourceKey<Level>, List<BlockPos>> TRANSIENT_PORTAL_CACHE = new IdentityHashMap<>();

    Block getPortalBlock();
    Block getPortalFrame();

    default DimensionTransition.PostDimensionTransition playTransitSound(Entity entity) {
        return DimensionTransition.PLAY_PORTAL_SOUND;
    }

    static BlockPos makeSafeCoords(Level fromLevel, Level toLevel, Vec3 pos) {
        final WorldBorder border = toLevel.getWorldBorder();
        final double relativeScale = DimensionType.getTeleportationScale(fromLevel.dimensionType(), toLevel.dimensionType());

        return border.clampToBounds(pos.x * relativeScale, pos.y, pos.z * relativeScale);
    }

    static DimensionTransition getTransitionForLevel(ServerLevel destination, Entity entity, Block jPortalBlock) {
        return getTransitionForLevel(destination, entity, Optional.empty(), entity.blockPosition(), (DepthsPortal)jPortalBlock, Optional.ofNullable(entity instanceof ServerPlayer pl ? pl.getData(JDataAttachments.PORTAL_OVERLAY).getPortalReturnLocation(entity.level().dimension()) : null));
    }

    static DimensionTransition getTransitionForLevel(ServerLevel destination, Entity entity, Optional<BlockPos> fromPortal, BlockPos safeCoords, DepthsPortal portal, Optional<PortalCoordinatesContainer> existingLink) {
        final ServerLevel fromLevel = (ServerLevel)entity.level();
        final BlockPos portalPos = getOrCreatePortalLocation(destination, fromLevel, entity, safeCoords, portal, existingLink);

        fromPortal.ifPresent(portalBlock -> {
            if(entity instanceof ServerPlayer pl)
                updatePlayerLink(pl, portalBlock, fromLevel.dimension(), destination.dimension());
        });

        return new DimensionTransition(destination, Vec3.atCenterOf(portalPos), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), portal.playTransitSound(entity).then(DimensionTransition.PLACE_PORTAL_TICKET));
    }

    static BlockPos getOrCreatePortalLocation(ServerLevel destination, ServerLevel fromLevel, Entity entity, BlockPos safeCoords, DepthsPortal portal, Optional<PortalCoordinatesContainer> existingLink) {
        BlockPos portalPos = entity instanceof ServerPlayer pl ? existingLink.filter(link -> link.fromDim() == destination.dimension()).map(link -> portal.retrieveExistingLinkExit(pl, pl.serverLevel(), destination, link)).orElse(null) : null;

        if(fromLevel.getBlockState(entity.blockPosition()).getBlock() instanceof JBasePortalBlock)
            updateLocalCache(fromLevel, entity.blockPosition());

        if(portalPos == null)
            portalPos = portal.findExistingPortal(destination, entity, safeCoords);

        if(portalPos == null) {
            portalPos = portal.findSuitablePortalLocation(destination, entity, safeCoords);
            portalPos = portal.createPortalFrameAndSpace(destination, entity, portalPos);
        }

        updateLocalCache(destination, portalPos);

        return portalPos;
    }

    static void updatePlayerLink(ServerPlayer pl, BlockPos fromPortalBlockPos, ResourceKey<Level> fromDim, ResourceKey<Level> destinationDim) {
        final net.jitl.common.capability.player.Portal plData = pl.getData(JDataAttachments.PORTAL_OVERLAY);
        final PortalCoordinatesContainer portalLoc = plData.getPortalReturnLocation(destinationDim);

        if(portalLoc != null) {
            PortalCoordinatesContainer returnPortalLoc = plData.getPortalReturnLocation(fromDim);

            if(returnPortalLoc != null && returnPortalLoc.fromDim() == destinationDim)
                return;
        }

        if(portalLoc == null || fromDim == portalLoc.fromDim() || pl.distanceToSqr(Vec3.atLowerCornerOf(portalLoc.portalPos())) > JCommonConfig.PORTAL_SEARCH_RADIUS.get() * JCommonConfig.PORTAL_SEARCH_RADIUS.get())
            plData.setPortalReturnLocation(destinationDim, new PortalCoordinatesContainer(fromDim, fromPortalBlockPos));
    }

    @Nullable
    static BlockPos getLocalCachedPos(Level level, BlockPos pos, Block portalBlock) {
        final List<BlockPos> localPortalCache = TRANSIENT_PORTAL_CACHE.get(level.dimension());

        if(localPortalCache == null || localPortalCache.isEmpty())
            return null;

        final int range = JCommonConfig.PORTAL_SEARCH_RADIUS.getAsInt();
        BlockPos closest = null;
        double closestDistSqr = Double.MAX_VALUE;

        for (Iterator<BlockPos> positions = localPortalCache.iterator(); positions.hasNext();) {
            BlockPos nextPos = positions.next();
            double distSqr = nextPos.distSqr(pos);

            if(distSqr <= range * range && distSqr < closestDistSqr) {
                if(!level.getBlockState(nextPos).is(portalBlock)) {
                    positions.remove();
                    continue;
                }

                closest = nextPos;
                closestDistSqr = distSqr;
            }
        }

        return closest;
    }

    static BlockPos updateLocalCache(Level level, BlockPos pos) {
        TRANSIENT_PORTAL_CACHE.computeIfAbsent(level.dimension(), key -> new ObjectArrayList<>()).add(pos.immutable());

        return pos;
    }

    @Nullable
    default BlockPos retrieveExistingLinkExit(ServerPlayer player, ServerLevel currentWorld, ServerLevel destWorld, PortalCoordinatesContainer existingLink) {
        final net.jitl.common.capability.player.Portal plData = player.getData(JDataAttachments.PORTAL_OVERLAY);
        BlockPos locPos = existingLink.portalPos();
        BlockState state = destWorld.getBlockState(locPos);

        if(state.is(this.getPortalBlock()))
            return locPos;

        if(!(state.getBlock() instanceof JBasePortalBlock))
            plData.removePortalReturnLocation(currentWorld.dimension());

        return null;
    }

    default BlockPos findExistingPortal(Level targetLevel, Entity entity, BlockPos originPos) {
        final BlockPos cachedPos = getLocalCachedPos(targetLevel, originPos, this.getPortalBlock());

        if(cachedPos != null)
            return cachedPos;

        final Block portalBlock = this.getPortalBlock();
        final int searchRadius = JCommonConfig.PORTAL_SEARCH_RADIUS.get();
        final int worldHeight = 32;
        final int worldFloor = targetLevel.getMinBuildHeight();
        final int posX = Mth.floor(originPos.getX());
        final int posY = originPos.getY() >= worldHeight ? 65 : Mth.floor(originPos.getY());
        final int posZ = Mth.floor(originPos.getZ());
        final BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(posX, posY, posZ);

        try (BulkSectionAccess sectionAccess = new BulkSectionAccess(targetLevel)) {
            if(sectionAccess.getBlockState(checkPos).is(portalBlock)) {
                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                return updateLocalCache(targetLevel, checkPos);
            }

            for (int radius = 1; radius <= searchRadius; radius++) {
                for (int y = posY - radius; y <= posY + radius; y += radius * 2) {
                    if(y < worldFloor || y >= worldHeight)
                        continue;

                    int xNeg = -1;

                    for (int x = 0; x <= radius; x++) {
                        int x2 = posX + x * xNeg;

                        if(xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;
                        int zNeg = -1;

                        for (int z = 0; z <= radius; z++) {
                            int z2 = posZ + z * zNeg;

                            if(zNeg == 1 && z != 0)
                                z--;

                            zNeg *= -1;

                            if(sectionAccess.getBlockState(checkPos.set(x2, y, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }
                }

                int yNeg = -1;

                for (int y = 0; y <= radius - 1; y++) {
                    int y2 = posY + y * yNeg;

                    if(y2 < worldFloor || y2 >= worldHeight)
                        continue;

                    if(yNeg == 1 && y != 0)
                        y--;

                    yNeg *= -1;
                    int zNeg = -1;

                    for (int z = 0; z <= radius; z++) {
                        int z2 = posZ + z * zNeg;

                        if(zNeg == 1 && z != 0)
                            z--;

                        zNeg *= -1;

                        for (int x = -radius; x <= radius; x += radius * 2) {
                            int x2 = posX + x;

                            if(sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }

                    int xNeg = 1;

                    for (int x = 1; x <= radius - 1; x++) {
                        int x2 = posX + x * xNeg;

                        if(xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;

                        for (int z = -radius; z <= radius; z += radius * 2) {
                            int z2 = posZ + z;

                            if(sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).is(portalBlock)) {
                                while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                                return updateLocalCache(targetLevel, checkPos.above());
                            }
                        }
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY - searchRadius, z);

                    while (!sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock) && checkPos.getY() >= worldFloor);

                    if(sectionAccess.getBlockState(checkPos).is(portalBlock)) {
                        while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).is(portalBlock));

                        return checkPos.above(2).immutable();
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY + searchRadius, z);

                    while (!sectionAccess.getBlockState(checkPos.move(Direction.UP)).is(portalBlock) && checkPos.getY() < worldHeight);

                    if(sectionAccess.getBlockState(checkPos).is(portalBlock))
                        return checkPos.above().immutable();
                }
            }
        }

        return null;
    }

    default BlockPos findSuitablePortalLocation(Level level, Entity entity, BlockPos originPos) {
        final int searchRadius = JCommonConfig.PORTAL_SEARCH_RADIUS.get();
        final int worldHeight = 32;
        final int worldFloor = level.getMinBuildHeight();
        final BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        final int posX = Mth.floor(originPos.getX());
        final int posY = originPos.getY() >= worldHeight ? 64 : Mth.floor(originPos.getY());
        final int posZ = Mth.floor(originPos.getZ());
        BlockPos fallbackPos = null;
        boolean isCleanSpawn = true;

        try (BulkSectionAccess sectionAccess = new BulkSectionAccess(level)) {
            for (int x = posX - 2; x <= posX + 2 && isCleanSpawn; x++) {
                for (int z = posZ - 2; z <= posZ + 2 && isCleanSpawn; z++) {
                    for (int y = posY + 1; y <= posY + 6 && isCleanSpawn; y++) {
                        if(y >= worldHeight || !sectionAccess.getBlockState(checkPos.set(x, y, z)).isAir())
                            isCleanSpawn = false;
                    }
                }
            }

            if(isCleanSpawn) {
                if(!sectionAccess.getBlockState(checkPos.set(posX, posY, posZ)).isAir())
                    return checkPos.set(posX, posY + 2, posZ).immutable();

                fallbackPos = checkPos.set(posX, posY + 2, posZ).immutable();
            }

            for (int radius = 1; radius <= searchRadius; radius++) {
                for (int y = posY - radius; y <= posY + radius; y += radius * 2) {
                    if(y < worldFloor || y >= worldHeight - 6)
                        continue;

                    int xNeg = -1;

                    for (int x = 0; x <= radius; x++) {
                        int x2 = posX + x * xNeg;

                        if(xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;
                        int zNeg = -1;

                        for (int z = 0; z <= radius; z++) {
                            int z2 = posZ + z * zNeg;

                            if(zNeg == 1 && z != 0)
                                z--;

                            zNeg *= -1;

                            if(!sectionAccess.getBlockState(checkPos.set(x2, y, z2)).isAir()) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y + 1; y3 <= y + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    return checkPos.set(x2, y + 2, z2).immutable();
                            }
                            else if(fallbackPos == null) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y + 1; y3 <= y + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    fallbackPos = checkPos.set(x2, y + 2, z2).immutable();
                            }
                        }
                    }
                }

                int yNeg = -1;

                for (int y = 0; y <= radius - 1; y++) {
                    int y2 = posY + y * yNeg;

                    if(y2 < 0 || y2 >= worldHeight - 6)
                        continue;

                    if(yNeg == 1 && y != 0)
                        y--;

                    yNeg *= -1;
                    int zNeg = -1;

                    for (int z = 0; z <= radius; z++) {
                        int z2 = posZ + z * zNeg;

                        if(zNeg == 1 && z != 0)
                            z--;

                        zNeg *= -1;

                        for (int x = -radius; x <= radius; x += radius * 2) {
                            int x2 = posX + x;

                            if(!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir()) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y2 + 1; y3 <= y2 + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    return checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                            else if(fallbackPos == null) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y2 + 1; y3 <= y2 + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    fallbackPos = checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                        }
                    }

                    int xNeg = 1;

                    for (int x = 1; x <= radius - 1; x++) {
                        int x2 = posX + x * xNeg;

                        if(xNeg == 1 && x != 0)
                            x--;

                        xNeg *= -1;

                        for (int z = -radius; z <= radius; z += radius * 2) {
                            int z2 = posZ + z;

                            if(!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir()) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y2 + 1; y3 <= y2 + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    return checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                            else if(fallbackPos == null) {
                                isCleanSpawn = true;

                                for (int x3 = x2 - 2; x3 <= x2 + 2 && isCleanSpawn; x3++) {
                                    for (int z3 = z2 - 2; z3 <= z2 + 2 && isCleanSpawn; z3++) {
                                        for (int y3 = y2 + 1; y3 <= y2 + 6 && isCleanSpawn; y3++) {
                                            if(!sectionAccess.getBlockState(checkPos.set(x3, y3, z3)).isAir())
                                                isCleanSpawn = false;
                                        }
                                    }
                                }

                                if(isCleanSpawn)
                                    fallbackPos = checkPos.set(x2, y2 + 2, z2).immutable();
                            }
                        }
                    }
                }
            }

            for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, posY - searchRadius, z);

                    while (sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).isAir() && checkPos.getY() >= 0);

                    int y = checkPos.getY();
                    isCleanSpawn = true;

                    for (int x2 = x - 2; x2 <= x + 2 && isCleanSpawn; x2++) {
                        for (int z2 = z - 2; z2 <= z + 2 && isCleanSpawn; z2++) {
                            for (int y2 = y + 1; y2 <= y + 6 && isCleanSpawn; y2++) {
                                if(!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir() || y2 >= worldHeight - 6)
                                    isCleanSpawn = false;
                            }
                        }
                    }

                    if(isCleanSpawn && y >= 0)
                        return checkPos.set(x, y + 2, z).immutable();
                }
            }

            for(int x = posX - searchRadius; x <= posX + searchRadius; x++) {
                for(int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
                    checkPos.set(x, worldHeight - 7, z);

                    while(sectionAccess.getBlockState(checkPos.move(Direction.DOWN)).isAir() && checkPos.getY() >= posY + searchRadius)
                        ;

                    int y = checkPos.getY();
                    isCleanSpawn = true;

                    for (int x2 = x - 2; x2 <= x + 2 && isCleanSpawn; x2++) {
                        for (int z2 = z - 2; z2 <= z + 2 && isCleanSpawn; z2++) {
                            for (int y2 = y + 1; y2 <= y + 6 && isCleanSpawn; y2++) {
                                if(!sectionAccess.getBlockState(checkPos.set(x2, y2, z2)).isAir())
                                    isCleanSpawn = false;
                            }
                        }
                    }

                    if(isCleanSpawn)
                        return checkPos.set(x, y + 2, z).immutable();
                }
            }
        }

        if(fallbackPos != null)
            return fallbackPos;

        return BlockPos.containing(originPos.getX(), Math.clamp(originPos.getY() + 2, worldFloor + 1, worldHeight - 10), originPos.getZ());
    }

    default BlockPos createPortalFrameAndSpace(Level level, Entity entity, BlockPos pos) {
        if(WorldUtil.isWorld(level, Dimensions.OVERWORLD))
            return pos;

        final BlockPos returnPos = pos;
        final BlockState border = this.getPortalFrame().defaultBlockState().setValue(DepthsPortalFrameBlock.HAS_EYE, true);
        final BlockState portal = this.getPortalBlock().defaultBlockState();

        for (int x = -2; x < 3; x++)
            for (int z = -2; z < 3; z++) {
                if (Math.abs(x) < 2 && Math.abs(z) < 2)
                    level.setBlock(pos.offset(x, 0, z), portal, 3);
                else if (Math.abs(z) < 2) {
                    if (x == -2)
                        level.setBlock(pos.offset(x, 0, z), border.setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 3);
                    else if (x == 2)
                        level.setBlock(pos.offset(x, 0, z), border.setValue(HorizontalDirectionalBlock.FACING, Direction.WEST), 3);
                } else if (Math.abs(x) < 2) {
                    if (z == -2)
                        level.setBlock(pos.offset(x, 0, z), border.setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 3);
                    else
                        level.setBlock(pos.offset(x, 0, z), border.setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 3);
                }
            }

        for(int x = -4; x < 5; x++) {
            for(int z = -4; z < 5; z++) {
                level.setBlock(pos.offset(x, -1, z), JBlocks.DEPTHS_GRASS.get().defaultBlockState(), 3);
            }
        }

        for(int x = -2; x < 3; x++) {
            for(int z = -2; z < 3; z++) {
                level.setBlock(pos.offset(x, 1, z), Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(pos.offset(x, 2, z), Blocks.AIR.defaultBlockState(), 3);
            }
        }
        return returnPos;
    }
}