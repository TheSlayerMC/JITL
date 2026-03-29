package net.jitl.common.world.gen.tree_grower;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class TreeConfig extends TreeConfiguration {

	public static final BlockPredicate CAN_PLACE_BELOW_OVERWORLD_TRUNKS = BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.CANNOT_REPLACE_BELOW_TREE_TRUNK));
	public static final RuleBasedStateProvider PLACE_BELOW_OVERWORLD_TRUNKS = RuleBasedStateProvider.ifTrueThenProvide(CAN_PLACE_BELOW_OVERWORLD_TRUNKS, Blocks.DIRT);
	public static final Codec<TreeConfig> CODEC = RecordCodecBuilder.create((i) -> i.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((c) -> c.trunkProvider), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((c) -> c.trunkPlacer), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((c) -> c.foliageProvider), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((c) -> c.foliagePlacer), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter((c) -> c.rootPlacer), FeatureSize.CODEC.fieldOf("minimum_size").forGetter((c) -> c.minimumSize), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((c) -> c.decorators), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((c) -> c.ignoreVines), BlockStateProvider.CODEC.fieldOf("below_trunk_provider").orElse(PLACE_BELOW_OVERWORLD_TRUNKS).forGetter((c) -> c.belowTrunkProvider)).apply(i, TreeConfig::new));

	public final BlockStateProvider trunkProvider;
	public final TrunkPlacer trunkPlacer;
	public final BlockStateProvider foliageProvider;
	public final FoliagePlacer foliagePlacer;
	public final Optional<RootPlacer> rootPlacer;
	public final FeatureSize minimumSize;
	public final List<TreeDecorator> decorators;
	public final boolean ignoreVines;
	public final BlockStateProvider belowTrunkProvider;

	public TreeConfig(BlockStateProvider trunk, TrunkPlacer t, BlockStateProvider foiliage, FoliagePlacer fplacer, Optional<RootPlacer> rp, FeatureSize size, List<TreeDecorator> dec, boolean vines, BlockStateProvider belowTrunkProvider) {
		super(trunk, t, foiliage, fplacer, rp, size, dec, vines, belowTrunkProvider);
		this.trunkProvider = trunk;
		this.trunkPlacer = t;
		this.foliageProvider = foiliage;
		this.foliagePlacer = fplacer;
		this.rootPlacer = rp;
		this.minimumSize = size;
		this.decorators = dec;
		this.ignoreVines = vines;
		this.belowTrunkProvider = belowTrunkProvider;
	}

	public static class JTreeConfigurationBuilder {
		public final BlockStateProvider trunkProvider;
		private final TrunkPlacer trunkPlacer;
		public final BlockStateProvider foliageProvider;
		private final FoliagePlacer foliagePlacer;
		private final Optional<RootPlacer> rootPlacer;
		private final FeatureSize minimumSize;
		private List<TreeDecorator> decorators = ImmutableList.of();
		private boolean ignoreVines;
		private BlockStateProvider belowTrunkProvider;

		public JTreeConfigurationBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, FeatureSize minimumSize, BlockStateProvider belowTrunkProvider) {
			this.decorators = ImmutableList.of();
			this.trunkProvider = trunkProvider;
			this.trunkPlacer = trunkPlacer;
			this.foliageProvider = foliageProvider;
			this.foliagePlacer = foliagePlacer;
			this.rootPlacer = rootPlacer;
			this.minimumSize = minimumSize;
			this.belowTrunkProvider = belowTrunkProvider;
		}

		public JTreeConfigurationBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, FeatureSize minimumSize) {
			this(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, Optional.empty(), minimumSize, TreeConfiguration.PLACE_BELOW_OVERWORLD_TRUNKS);
		}
		public JTreeConfigurationBuilder belowTrunkProvider(BlockStateProvider belowTrunkProvider) {
			this.belowTrunkProvider = belowTrunkProvider;
			return this;
		}

		public TreeConfig.JTreeConfigurationBuilder decorators(List<TreeDecorator> pDecorators) {
			this.decorators = pDecorators;
			return this;
		}

		public TreeConfig.JTreeConfigurationBuilder ignoreVines() {
			this.ignoreVines = true;
			return this;
		}

		public TreeConfig build() {
			return new TreeConfig(this.trunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.minimumSize, this.decorators, this.ignoreVines, this.belowTrunkProvider);
		}
	}
}