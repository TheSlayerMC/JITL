package net.jitl.common.world.gen.ruins;

import com.mojang.serialization.Codec;
import net.jitl.common.block.JChestBlock;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class GolditeRuinsFeature extends Feature<NoneFeatureConfiguration> {

	public GolditeRuinsFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		BlockPos pos = context.origin();
		WorldGenLevel reader = context.level();
		RandomSource rand = context.random();

		int spread = 8;
		int maxHeight = 6;
		int maxColums = 9;
		BlockState spawn = JBlocks.GOLDITE_GRASS.get().defaultBlockState();
		WeightedStateProvider blocks = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3).add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1).add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2));
		if (!(reader.getBlockState(pos.below()) == spawn)) {
			return false;
		} else {
			BlockPos.MutableBlockPos placePos = pos.mutable();
			int columns = rand.nextInt(maxColums) + 5;
			for (int j1 = 0; j1 < columns; j1++) {
				int xPos = pos.getX() + rand.nextInt(spread);
				int zPos = pos.getZ() + rand.nextInt(spread);
				int yPos = reader.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, xPos, zPos);
				int height = 1 + rand.nextInt(maxHeight);
				placePos.set(xPos, yPos, zPos);
				for (int i = 0; i < height; ++i) {
					reader.setBlock(placePos, blocks.getState(rand, placePos), 2);
					placePos.move(Direction.UP);
				}
				if (rand.nextInt(4) == 0) {
                    BlockPos chestPos = new BlockPos(pos.getX(), yPos - 2, pos.getZ());
                    BlockPos spawnPos = new BlockPos(pos.getX(), yPos, pos.getZ());

                    if(reader.getBlockState(spawnPos.below()) == spawn) {
						BlockState chestState = JBlocks.EUCA_CHEST.get().defaultBlockState().setValue(JChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(rand));
						reader.setBlock(chestPos, chestState, 2);
						RandomizableContainerBlockEntity.setLootTable(reader, rand, chestPos, BuiltInLootTables.SIMPLE_DUNGEON);
					}
                }
			}
			return true;
		}
	}
}
