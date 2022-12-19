package net.jitl.common.world.gen.ruins;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class RuinsFeatureConfig implements FeatureConfiguration {

	public static final Codec<RuinsFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
			instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((ruinsFeatureConfig -> ruinsFeatureConfig.spawnBlock)),
                    BlockStateProvider.CODEC.fieldOf("chest_block").forGetter((ruinsFeatureConfig -> ruinsFeatureConfig.chest)),
                    WeightedStateProvider.CODEC.fieldOf("ruined_blocks_provider").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.ruinedBlocksProvider),
                    Codec.INT.fieldOf("max_spreading").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.maxSpreading),
                    Codec.INT.fieldOf("max_height").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.maxHeight),
                    Codec.INT.fieldOf("max_columns").forGetter((ruinsFeatureConfig) -> ruinsFeatureConfig.maxColumns)
            ).apply(instance, RuinsFeatureConfig::new));

    public final RuleTest spawnBlock;
    public final BlockStateProvider chest;
    public final WeightedStateProvider ruinedBlocksProvider;
    public int maxSpreading;
    public int maxHeight;
    public int maxColumns;

    public RuinsFeatureConfig(RuleTest spawnBlock, BlockStateProvider chest, WeightedStateProvider ruinedBlocksProvider, int maxSpreading, int maxHeight, int maxColumns) {
        this.spawnBlock = spawnBlock;
        this.chest = chest;
        this.ruinedBlocksProvider = ruinedBlocksProvider;
        this.maxSpreading = maxSpreading;
        this.maxHeight = maxHeight;
        this.maxColumns = maxColumns;
    }
}
