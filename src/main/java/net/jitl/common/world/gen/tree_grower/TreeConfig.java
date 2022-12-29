package net.jitl.common.world.gen.tree_grower;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class TreeConfig extends TreeConfiguration {
	public static final Codec<TreeConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_161248_) -> {
			return p_161248_.trunkProvider;
		}), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((p_161246_) -> {
			return p_161246_.trunkPlacer;
		}), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((p_161244_) -> {
			return p_161244_.foliageProvider;
		}), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((p_191357_) -> {
			return p_191357_.foliagePlacer;
		}), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter((p_225478_) -> {
			return p_225478_.rootPlacer;
		}), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((p_225476_) -> {
			return p_225476_.dirtProvider;
		}), FeatureSize.CODEC.fieldOf("minimum_size").forGetter((p_225474_) -> {
			return p_225474_.minimumSize;
		}), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_225472_) -> {
			return p_225472_.decorators;
		}), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((p_161232_) -> {
			return p_161232_.ignoreVines;
		}), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((p_225470_) -> {
			return p_225470_.forceDirt;
		})).apply(instance, TreeConfig::new);
     });

	public final BlockStateProvider trunkProvider;
	public final BlockStateProvider dirtProvider;
	public final TrunkPlacer trunkPlacer;
	public final BlockStateProvider foliageProvider;
	public final FoliagePlacer foliagePlacer;
	public final Optional<RootPlacer> rootPlacer;
	public final FeatureSize minimumSize;
	public final List<TreeDecorator> decorators;
	public final boolean ignoreVines;
	public final boolean forceDirt;
	public TreeConfig(BlockStateProvider trunk, TrunkPlacer t, BlockStateProvider foiliage, FoliagePlacer fplacer, Optional<RootPlacer> rp, BlockStateProvider dirt, FeatureSize size, List<TreeDecorator> dec, boolean vines, boolean forceDirt) {
		super(trunk, t, foiliage, fplacer, rp, dirt, size, dec, vines, forceDirt);
		this.trunkProvider = trunk;
		this.trunkPlacer = t;
		this.foliageProvider = foiliage;
		this.foliagePlacer = fplacer;
		this.rootPlacer = rp;
		this.dirtProvider = dirt;
		this.minimumSize = size;
		this.decorators = dec;
		this.ignoreVines = vines;
		this.forceDirt = forceDirt;
	}

	public static class JTreeConfigurationBuilder {
		public final BlockStateProvider trunkProvider;
		private final TrunkPlacer trunkPlacer;
		public final BlockStateProvider foliageProvider;
		private final FoliagePlacer foliagePlacer;
		private final Optional<RootPlacer> rootPlacer;
		private BlockStateProvider dirtProvider;
		private final FeatureSize minimumSize;
		private List<TreeDecorator> decorators = ImmutableList.of();
		private boolean ignoreVines;
		private boolean forceDirt;

		public JTreeConfigurationBuilder(BlockStateProvider pTrunkProvider, TrunkPlacer pTrunkPlacer, BlockStateProvider pFoliageProvider, FoliagePlacer pFoliagePlacer, Optional<RootPlacer> pRootPlacer, FeatureSize pMinimumSize) {
			this.trunkProvider = pTrunkProvider;
			this.trunkPlacer = pTrunkPlacer;
			this.foliageProvider = pFoliageProvider;
			this.dirtProvider = BlockStateProvider.simple(Blocks.DIRT);
			this.foliagePlacer = pFoliagePlacer;
			this.rootPlacer = pRootPlacer;
			this.minimumSize = pMinimumSize;
		}

		public JTreeConfigurationBuilder(BlockStateProvider pTrunkProvider, TrunkPlacer pTrunkPlacer, BlockStateProvider pFoliageProvider, FoliagePlacer pFoliagePlacer, FeatureSize pMinimumSize) {
			this(pTrunkProvider, pTrunkPlacer, pFoliageProvider, pFoliagePlacer, Optional.empty(), pMinimumSize);
		}

		public TreeConfig.JTreeConfigurationBuilder dirt(BlockStateProvider pDirtProvider) {
			this.dirtProvider = pDirtProvider;
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

		public TreeConfig.JTreeConfigurationBuilder forceDirt() {
			this.forceDirt = true;
			return this;
		}

		public TreeConfig build() {
			return new TreeConfig(this.trunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt);
		}
	}
}