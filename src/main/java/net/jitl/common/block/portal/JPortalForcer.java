package net.jitl.common.block.portal;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Comparator;
import java.util.Optional;

public class JPortalForcer {

    protected final ServerLevel level;
    protected final Block portal_block;
    protected final Block portal_frame;
    protected final ResourceKey<PoiType> poi;
    
    public JPortalForcer(ServerLevel pLevel, Block portal, Block frame, ResourceKey<PoiType> poi) {
        this.level = pLevel;
        this.portal_block = portal;
        this.portal_frame = frame;
        this.poi = poi;
    }

    public Optional<BlockPos> findClosestPortalPosition(BlockPos pos, boolean isDim, WorldBorder p_352374_) {
        PoiManager poimanager = this.level.getPoiManager();
        poimanager.ensureLoadedAndValid(this.level, pos, 64);
        return poimanager.getInSquare(p_230634_ -> p_230634_.is(this.poi), pos, 64, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(p_352374_::isWithinBounds)
                .filter(p_352047_ -> this.level.getBlockState(p_352047_).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(p_352046_ -> p_352046_.distSqr(pos)).thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pPos, Direction.Axis pAxis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, pAxis);
        double d0 = -1.0;
        BlockPos blockpos = null;
        double d1 = -1.0;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int i = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for (BlockPos.MutableBlockPos mutPos : BlockPos.spiralAround(pPos, 16, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, mutPos.getX(), mutPos.getZ()));
            if (worldborder.isWithinBounds(mutPos) && worldborder.isWithinBounds(mutPos.move(direction, 1))) {
                mutPos.move(direction.getOpposite(), 1);

                for (int l = k; l >= this.level.getMinBuildHeight(); l--) {
                    mutPos.setY(l);
                    if (this.canPortalReplaceBlock(mutPos)) {
                        int i1 = l;

                        while (l > this.level.getMinBuildHeight() && this.canPortalReplaceBlock(mutPos.move(Direction.DOWN))) {
                            l--;
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                mutPos.setY(l);
                                if (this.canHostFrame(mutPos, blockpos$mutableblockpos, direction, 0)) {
                                    double d2 = pPos.distSqr(mutPos);
                                    if (this.canHostFrame(mutPos, blockpos$mutableblockpos, direction, -1)
                                            && this.canHostFrame(mutPos, blockpos$mutableblockpos, direction, 1)
                                            && (d0 == -1.0 || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = mutPos.immutable();
                                    }

                                    if (d0 == -1.0 && (d1 == -1.0 || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = mutPos.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0 && d1 != -1.0) {
            blockpos = blockpos1;
            d0 = d1;
        }

        if (d0 == -1.0) {
            int k1 = Math.max(this.level.getMinBuildHeight() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {
                return Optional.empty();
            }

            blockpos = new BlockPos(pPos.getX() - direction.getStepX(), Mth.clamp(pPos.getY(), k1, i2), pPos.getZ() - direction.getStepZ())
                    .immutable();
            blockpos = worldborder.clampToBounds(blockpos);
            Direction direction1 = direction.getClockWise();

            for (int i3 = -1; i3 < 2; i3++) {
                for (int j3 = 0; j3 < 2; j3++) {
                    for (int k3 = -1; k3 < 3; k3++) {
                        BlockState blockstate1 = k3 < 0 ? this.portal_frame.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        blockpos$mutableblockpos.setWithOffset(
                                blockpos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ()
                        );
                        this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
                    }
                }
            }
        }

        for (int l1 = -1; l1 < 3; l1++) {
            for (int j2 = -1; j2 < 4; j2++) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    this.level.setBlock(blockpos$mutableblockpos, this.portal_frame.defaultBlockState(), 3);
                }
            }
        }

        BlockState blockstate = this.portal_block.defaultBlockState().setValue(NetherPortalBlock.AXIS, pAxis);

        for (int k2 = 0; k2 < 2; k2++) {
            for (int l2 = 0; l2 < 3; l2++) {
                blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                this.level.setBlock(blockpos$mutableblockpos, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pPos) {
        BlockState blockstate = this.level.getBlockState(pPos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos pOriginalPos, BlockPos.MutableBlockPos pOffsetPos, Direction pDirection, int pOffsetScale) {
        Direction direction = pDirection.getClockWise();

        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 4; j++) {
                pOffsetPos.setWithOffset(
                        pOriginalPos, pDirection.getStepX() * i + direction.getStepX() * pOffsetScale, j, pDirection.getStepZ() * i + direction.getStepZ() * pOffsetScale
                );
                if (j < 0 && !this.level.getBlockState(pOffsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.canPortalReplaceBlock(pOffsetPos)) {
                    return false;
                }
            }
        }
        return true;
    }
}