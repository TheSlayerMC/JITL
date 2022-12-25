package net.jitl.common.world.gen.tree_grower;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

public class JourneyTree extends Feature<TreeConfig> {
	public JourneyTree() {
		super(TreeConfig.CODEC);
	}

	public boolean hasSpace(WorldGenLevel level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		return hasSpace(state);
	}
	
	public boolean hasSpace(BlockState state) {
		return state.isAir() || state.is(BlockTags.LEAVES) || state.is(BlockTags.FLOWERS);
	}

	public boolean canBeHere(WorldGenLevel level, BlockPos pos) {
		if(hasSpace(level, pos)) {
			BlockState state = level.getBlockState(pos.below());
			return defaultGrowOn(state) && level.getBlockState(pos).isAir() &&
					level.getBlockState(pos) != Blocks.LAVA.defaultBlockState() && state != Blocks.LAVA.defaultBlockState();
		}
		return false;
	}

	private static boolean isVine(LevelSimulatedReader l, BlockPos p) {
		return l.isStateAtPosition(p, (s) -> s.is(Blocks.VINE));
	}

	private static void setBlockKnownShape(LevelWriter l, BlockPos p, BlockState s) {
		l.setBlock(p, s, 19);
	}

	protected boolean defaultGrowOn(BlockState state) {
		return state.is(BlockTags.DIRT);
	}

	protected void setBlock(WorldGenLevel level, BlockPos pos, BlockState state, boolean replace) {
		BlockState block = level.getBlockState(pos);
		if(hasSpace(block) || (replace && !block.is(Blocks.BEDROCK))) setBlock(level, pos, state);
	}

	private boolean doPlace(WorldGenLevel level, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> root, BiConsumer<BlockPos, BlockState> trunk, BiConsumer<BlockPos, BlockState> foiliage, TreeConfig config) {
		if(canBeHere(level, pos)) {
			int i = config.trunkPlacer.getTreeHeight(random);
			int j = config.foliagePlacer.foliageHeight(random, i, config);
			int k = i - j;
			int l = config.foliagePlacer.foliageRadius(random, k);
			BlockPos blockpos = config.rootPlacer.map((p_225286_) -> {
				return p_225286_.getTrunkOrigin(pos, random);
			}).orElse(pos);
			int i1 = Math.min(pos.getY(), blockpos.getY());
			int j1 = Math.max(pos.getY(), blockpos.getY()) + i + 1;
			if (i1 >= level.getMinBuildHeight() + 1 && j1 <= level.getMaxBuildHeight()) {
				OptionalInt optionalint = config.minimumSize.minClippedHeight();
				int k1 = this.getMaxFreeTreeHeight(level, i, blockpos, config);
				if (k1 >= i || optionalint.isPresent() && k1 >= optionalint.getAsInt()) {
					if (config.rootPlacer.isPresent() && !config.rootPlacer.get().placeRoots(level, root, random, pos, blockpos, config)) {
						return false;
					} else {
						List<FoliagePlacer.FoliageAttachment> list = config.trunkPlacer.placeTrunk(level, trunk, random, k1, blockpos, config);
						list.forEach((p_225279_) -> {
							config.foliagePlacer.createFoliage(level, foiliage, random, config, k1, p_225279_, j, l);
						});
						return true;
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	private int getMaxFreeTreeHeight(LevelSimulatedReader p_67216_, int p_67217_, BlockPos p_67218_, TreeConfiguration p_67219_) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int i = 0; i <= p_67217_ + 1; ++i) {
			int j = p_67219_.minimumSize.getSizeAtHeight(p_67217_, i);

			for(int k = -j; k <= j; ++k) {
				for(int l = -j; l <= j; ++l) {
					blockpos$mutableblockpos.setWithOffset(p_67218_, k, i, l);
					if (!p_67219_.trunkPlacer.isFree(p_67216_, blockpos$mutableblockpos) || !p_67219_.ignoreVines && isVine(p_67216_, blockpos$mutableblockpos)) {
						return i - 2;
					}
				}
			}
		}

		return p_67217_;
	}

	protected void setBlock(@NotNull LevelWriter l, @NotNull BlockPos p, @NotNull BlockState s) {
		setBlockKnownShape(l, p, s);
	}

	public final boolean place(FeaturePlaceContext<TreeConfig> config) {
		WorldGenLevel worldgenlevel = config.level();
		RandomSource randomsource = config.random();
		BlockPos blockpos = config.origin();
		TreeConfig treeconfiguration = config.config();
		Set<BlockPos> set = Sets.newHashSet();
		Set<BlockPos> set1 = Sets.newHashSet();
		Set<BlockPos> set2 = Sets.newHashSet();
		Set<BlockPos> set3 = Sets.newHashSet();
		BiConsumer<BlockPos, BlockState> biconsumer = (p, s) -> {
			set.add(p.immutable());
			worldgenlevel.setBlock(p, s, 19);
		};
		BiConsumer<BlockPos, BlockState> biconsumer1 = (p, s) -> {
			set1.add(p.immutable());
			worldgenlevel.setBlock(p, s, 19);
		};
		BiConsumer<BlockPos, BlockState> biconsumer2 = (p, s) -> {
			set2.add(p.immutable());
			worldgenlevel.setBlock(p, s, 19);
		};
		BiConsumer<BlockPos, BlockState> biconsumer3 = (p, s) -> {
			set3.add(p.immutable());
			worldgenlevel.setBlock(p, s, 19);
		};
		boolean flag = this.doPlace(worldgenlevel, randomsource, blockpos, biconsumer, biconsumer1, biconsumer2, treeconfiguration);
		if (flag && (!set1.isEmpty() || !set2.isEmpty())) {
			if (!treeconfiguration.decorators.isEmpty()) {
				TreeDecorator.Context treedecorator$context = new TreeDecorator.Context(worldgenlevel, biconsumer3, randomsource, set1, set2, set);
				treeconfiguration.decorators.forEach((l) -> {
					l.place(treedecorator$context);
				});
			}

			return BoundingBox.encapsulatingPositions(Iterables.concat(set, set1, set2, set3)).map((s) -> {
				DiscreteVoxelShape discretevoxelshape = updateLeaves(worldgenlevel, s, set1, set3, set);
				StructureTemplate.updateShapeAtEdge(worldgenlevel, 3, discretevoxelshape, s.minX(), s.minY(), s.minZ());
				return true;
			}).orElse(false);
		} else {
			return false;
		}
	}

	private static DiscreteVoxelShape updateLeaves(LevelAccessor level, BoundingBox bb, Set<BlockPos> s1, Set<BlockPos> s2, Set<BlockPos> s3) {
		List<Set<BlockPos>> list = Lists.newArrayList();
		DiscreteVoxelShape discretevoxelshape = new BitSetDiscreteVoxelShape(bb.getXSpan(), bb.getYSpan(), bb.getZSpan());

		for(int j = 0; j < 6; ++j) {
			list.add(Sets.newHashSet());
		}

		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(BlockPos blockpos : Lists.newArrayList(Sets.union(s2, s3))) {
			if (bb.isInside(blockpos)) {
				discretevoxelshape.fill(blockpos.getX() - bb.minX(), blockpos.getY() - bb.minY(), blockpos.getZ() - bb.minZ());
			}
		}

		for(BlockPos blockpos1 : Lists.newArrayList(s1)) {
			if (bb.isInside(blockpos1)) {
				discretevoxelshape.fill(blockpos1.getX() - bb.minX(), blockpos1.getY() - bb.minY(), blockpos1.getZ() - bb.minZ());
			}

			for(Direction direction : Direction.values()) {
				blockpos$mutableblockpos.setWithOffset(blockpos1, direction);
				if (!s1.contains(blockpos$mutableblockpos)) {
					BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
					if (blockstate.hasProperty(BlockStateProperties.DISTANCE)) {
						list.get(0).add(blockpos$mutableblockpos.immutable());
						setBlockKnownShape(level, blockpos$mutableblockpos, blockstate.setValue(BlockStateProperties.DISTANCE, 1));
						if (bb.isInside(blockpos$mutableblockpos)) {
							discretevoxelshape.fill(blockpos$mutableblockpos.getX() - bb.minX(), blockpos$mutableblockpos.getY() - bb.minY(), blockpos$mutableblockpos.getZ() - bb.minZ());
						}
					}
				}
			}
		}

		for(int l = 1; l < 6; ++l) {
			Set<BlockPos> set = list.get(l - 1);
			Set<BlockPos> set1 = list.get(l);

			for(BlockPos blockpos2 : set) {
				if (bb.isInside(blockpos2)) {
					discretevoxelshape.fill(blockpos2.getX() - bb.minX(), blockpos2.getY() - bb.minY(), blockpos2.getZ() - bb.minZ());
				}

				for(Direction direction1 : Direction.values()) {
					blockpos$mutableblockpos.setWithOffset(blockpos2, direction1);
					if (!set.contains(blockpos$mutableblockpos) && !set1.contains(blockpos$mutableblockpos)) {
						BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
						if (blockstate1.hasProperty(BlockStateProperties.DISTANCE)) {
							int k = blockstate1.getValue(BlockStateProperties.DISTANCE);
							if (k > l + 1) {
								BlockState blockstate2 = blockstate1.setValue(BlockStateProperties.DISTANCE, l + 1);
								setBlockKnownShape(level, blockpos$mutableblockpos, blockstate2);
								if (bb.isInside(blockpos$mutableblockpos)) {
									discretevoxelshape.fill(blockpos$mutableblockpos.getX() - bb.minX(), blockpos$mutableblockpos.getY() - bb.minY(), blockpos$mutableblockpos.getZ() - bb.minZ());
								}

								set1.add(blockpos$mutableblockpos.immutable());
							}
						}
					}
				}
			}
		}

		return discretevoxelshape;
	}
}