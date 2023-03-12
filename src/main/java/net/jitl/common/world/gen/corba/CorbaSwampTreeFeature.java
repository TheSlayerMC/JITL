package net.jitl.common.world.gen.corba;

import com.mojang.serialization.Codec;
import net.jitl.core.helper.RandHelper;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class CorbaSwampTreeFeature extends Feature<NoneFeatureConfiguration> {

    public CorbaSwampTreeFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    private boolean isSpawnBlock(BlockState b) {
        return b == JBlocks.DRIED_MUD.get().defaultBlockState() || b == JBlocks.TAINTED_MUD.get().defaultBlockState();
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource random = context.random();
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();

        int treeHeight = 5 + random.nextInt(2) + random.nextInt(2);

        BlockPos soilPos = blockPos.below();

        BlockPos.MutableBlockPos basePos = new BlockPos.MutableBlockPos(blockPos.getX(), blockPos.getY() - 2, blockPos.getZ());

        if(isSpawnBlock(world.getBlockState(soilPos))) {

            int stumpHeight = random.nextInt(5) + 3;

            for (int i = 0; i < stumpHeight; i++) {
                placeStumps(world, basePos);
            }

            for (int i = 0; i < treeHeight; i++) {
                placeLog(world, basePos);
            }

            BlockPos.MutableBlockPos leafPos = basePos.move(Direction.DOWN, 2);

            for (int j5 = 0; j5 <= 10; ++j5) {
                for (int l5 = 0; l5 <= 10; ++l5) {
                    createCrown(world, leafPos.relative(getRandomDirection(random), random.nextInt(2)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.relative(getRandomDirection(random), random.nextInt(5)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.relative(getRandomDirection(random), random.nextInt(3)), random.nextInt(3) + 1);
                    createCrown(world, leafPos.relative(getRandomDirection(random), random.nextInt(5)), random.nextInt(3) + 1);
                }
            }

            for (int i = 0; i < random.nextInt(5) + 2; i++) {
                placeStumps(world, basePos);
            }

            int stemChance = random.nextInt(2);
            switch (stemChance) {
                case 0:
                    for (int j5 = 0; j5 <= 1; ++j5) {
                        for (int l5 = 0; l5 <= 1; ++l5) {
                            placeStems(world, leafPos, Direction.EAST);
                        }
                    }
                case 1:
                    for (int j5 = 0; j5 <= 1; ++j5) {
                        for (int l5 = 0; l5 <= 1; ++l5) {
                            placeStems(world, leafPos, Direction.WEST);
                        }
                    }
                case 2:
            }

            for (int i = 0; i < random.nextInt(3); i++) {
                placeMushroom(world, blockPos.north(2).above(random.nextInt(stumpHeight - 1) + 1), Direction.NORTH);
                placeMushroom(world, blockPos.north(1).above(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), Direction.NORTH);

                if (stemChance != 1) {
                    placeMushroom(world, blockPos.west(2).above(random.nextInt(stumpHeight - 1) + 1), Direction.WEST);
                    placeMushroom(world, blockPos.west(1).above(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), Direction.WEST);
                }

                if (stemChance != 0) {
                    placeMushroom(world, blockPos.east(2).above(random.nextInt(stumpHeight - 1) + 1), Direction.EAST);
                    placeMushroom(world, blockPos.east(1).above(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), Direction.EAST);
                }

                placeMushroom(world, blockPos.south(2).above(random.nextInt(stumpHeight - 1) + 1), Direction.SOUTH);
                placeMushroom(world, blockPos.south(1).above(stumpHeight + 1 + (random.nextInt(treeHeight - 4))), Direction.SOUTH);
            }
        }
        return true;
    }

    private Direction getRandomDirection(RandomSource random) {
        return RandHelper.chooseEqual(random, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP);
    }

    private void placeLog(WorldGenLevel world, BlockPos.MutableBlockPos pos) {
        setBlock(world, pos.move(Direction.UP), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
    }

    private void placeStumps(WorldGenLevel world, BlockPos.MutableBlockPos logPos) {
        setBlock(world, logPos.move(Direction.UP).east(), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
        setBlock(world, logPos.west(), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
        setBlock(world, logPos.north(), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
        setBlock(world, logPos.south(), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
    }

    private void placeStems(WorldGenLevel world, BlockPos.MutableBlockPos logPos, Direction direction) {
        setBlock(world, logPos.move(direction, 1).move(Direction.UP, 1).below(5), JBlocks.BOGWOOD_LOG.get().defaultBlockState());
    }

    private void createCrown(WorldGenLevel world, BlockPos pos, int size) {
        pos = pos.above();
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {
                    int distance;
                    if (x >= y & x >= z) distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);
                    else if (y >= x & y >= z) distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);
                    else distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);
                    if (distance <= size) {
                        placeLeaves(world, pos.offset(-x, +y, +z));
                        placeLeaves(world, pos.offset(-x, +y, -z));
                        placeLeaves(world, pos.offset(+x, +y, +z));
                        placeLeaves(world, pos.offset(+x, +y, -z));
                    }
                }
            }
        }
    }

    private void placeLeaves(WorldGenLevel world, BlockPos pos) {
        setBlock(world, pos, JBlocks.BOGWOOD_LEAVES.get().defaultBlockState());
    }

    private void placeMushroom(WorldGenLevel world, BlockPos pos, Direction facing) {
        //setBlock(world, pos, JourneyBlocks.SWAMP_SHELF.getDefaultState().withProperty(JBlockFungalShelf.FACING, facing));
    }
}
