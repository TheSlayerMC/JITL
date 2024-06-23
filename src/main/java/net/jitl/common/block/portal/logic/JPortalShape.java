package net.jitl.common.block.portal.logic;

import net.jitl.common.block.portal.JBasePortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.ArrayList;

import static net.jitl.common.block.portal.logic.JPortalShape.PortalDirection.*;

public class JPortalShape {
    private static final ArrayList<Vec3i> northSouthPattern = new ArrayList<>();
    private static final ArrayList<Vec3i> eastWestPattern = new ArrayList<>();

    public static PortalDirection testFrameForActivation(Level world, BlockPos activationPos, Direction sideClicked, JBasePortalBlock portalBlock, Block frame) {
        switch (sideClicked) {
            case NORTH:
            case SOUTH:
                return testNorthSouthPortalFrame(world, activationPos, portalBlock, frame);
            case EAST:
            case WEST:
                return testEastWestPortalFrame(world, activationPos, portalBlock, frame);
            case UP:
            case DOWN:
            default:
                PortalDirection dir = testNorthSouthPortalFrame(world, activationPos, portalBlock, frame);

                if(dir == INVALID)
                    dir = testEastWestPortalFrame(world, activationPos, portalBlock, frame);

                return dir;
        }
    }

    public static PortalDirection testNorthSouthPortalFrame(Level world, BlockPos basePos, JBasePortalBlock portalBlock, Block frame) {
        int portalFrameCount = 0;

        for(Vec3i pos : northSouthPattern) {
            BlockState testState = world.getBlockState(basePos.offset(pos));
            Block testBlock = testState.getBlock();
            if(testBlock == frame)
                portalFrameCount++;

        }
        if(portalFrameCount >= 10) {
            for(int x = 0; x < 2; x++) {
                for(int y = 1; y < 4; y++) {
                    BlockState state = world.getBlockState(basePos.offset(x, y, 0));

                    if(state.getBlock() instanceof JBasePortalBlock) {
                        if(state.getBlock() == portalBlock)
                            return EXISTING;
                    } else if(!state.canBeReplaced()) {
                        return INVALID;
                    }
                }
            }

            return NORTH_SOUTH;
        }

        return INVALID;
    }

    public static PortalDirection testEastWestPortalFrame(Level world, BlockPos basePos, JBasePortalBlock portalBlock, Block frame) {
        int portalFrameCount = 0;

        for(Vec3i pos : eastWestPattern) {
            BlockState testState = world.getBlockState(basePos.offset(pos));
            Block testBlock = testState.getBlock();
            if(testBlock == frame)
                portalFrameCount++;
        }

        if(portalFrameCount >= 10) {
            for(int z = 0; z < 2; z++) {
                for(int y = 1; y < 4; y++) {
                    BlockState state = world.getBlockState(basePos.offset(0, y, z));

                    if(state.getBlock() instanceof JBasePortalBlock) {
                        if(state.getBlock() == portalBlock)
                            return EXISTING;
                    } else if(!state.canBeReplaced()) {
                        return INVALID;
                    }
                }

                return EAST_WEST;
            }
        }

        return INVALID;
    }


    public static void lightPortalFrame(ServerLevel level, BlockPos basePos, PortalDirection direction, JBasePortalBlock portalBlock) {
        switch (direction) {
            case NORTH_SOUTH:
                for(int x = 0; x < 2; x++) {
                    for(int y = 1; y < 4; y++) {
                        BlockPos pos = basePos.offset(x, y, 0);
                        level.setBlock(pos, portalBlock.defaultBlockState(), Block.UPDATE_CLIENTS);
                    }
                }
                break;
            case EAST_WEST:
                for(int z = 0; z < 2; z++) {
                    for(int y = 1; y < 4; y++) {
                        BlockPos pos = basePos.offset(0, y, z);
                        level.setBlock(pos, portalBlock.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z), Block.UPDATE_CLIENTS);
                    }
                }
                break;
            default:
                break;
        }
    }

    public enum PortalDirection {
        INVALID,
        EXISTING,
        NORTH_SOUTH,
        EAST_WEST
    }

    static {
        northSouthPattern.add(new Vec3i(-1, 0, 0));
        northSouthPattern.add(new Vec3i(0, 0, 0));
        northSouthPattern.add(new Vec3i(1, 0, 0));
        northSouthPattern.add(new Vec3i(2, 0, 0));
        northSouthPattern.add(new Vec3i(2, 1, 0));
        northSouthPattern.add(new Vec3i(2, 2, 0));
        northSouthPattern.add(new Vec3i(2, 3, 0));
        northSouthPattern.add(new Vec3i(2, 4, 0));
        northSouthPattern.add(new Vec3i(2, 4, 0));
        northSouthPattern.add(new Vec3i(1, 4, 0));
        northSouthPattern.add(new Vec3i(0, 4, 0));
        northSouthPattern.add(new Vec3i(-1, 4, 0));
        northSouthPattern.add(new Vec3i(-1, 4, 0));
        northSouthPattern.add(new Vec3i(-1, 3, 0));
        northSouthPattern.add(new Vec3i(-1, 2, 0));
        northSouthPattern.add(new Vec3i(-1, 1, 0));

        eastWestPattern.add(new Vec3i(0, 0, -1));
        eastWestPattern.add(new Vec3i(0, 0, 0));
        eastWestPattern.add(new Vec3i(0, 0, 1));
        eastWestPattern.add(new Vec3i(0, 0, 2));
        eastWestPattern.add(new Vec3i(0, 1, 2));
        eastWestPattern.add(new Vec3i(0, 2, 2));
        eastWestPattern.add(new Vec3i(0, 3, 2));
        eastWestPattern.add(new Vec3i(0, 4, 2));
        eastWestPattern.add(new Vec3i(0, 4, 1));
        eastWestPattern.add(new Vec3i(0, 4, 0));
        eastWestPattern.add(new Vec3i(0, 4, -1));
        eastWestPattern.add(new Vec3i(0, 4, -1));
        eastWestPattern.add(new Vec3i(0, 4, -1));
        eastWestPattern.add(new Vec3i(0, 3, -1));
        eastWestPattern.add(new Vec3i(0, 2, -1));
        eastWestPattern.add(new Vec3i(0, 1, -1));
    }
}
