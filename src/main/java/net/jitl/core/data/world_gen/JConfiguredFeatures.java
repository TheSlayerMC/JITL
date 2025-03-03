package net.jitl.core.data.world_gen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.jitl.common.world.gen.JFeatures;
import net.jitl.common.world.gen.ruins.RuinsFeatureConfig;
import net.jitl.common.world.gen.terrania.TerranianLeaveVineDecorator;
import net.jitl.common.world.gen.terrania.TerranianTrunkVineDecorator;
import net.jitl.common.world.gen.tree_grower.TreeConfig;
import net.jitl.common.world.gen.tree_grower.decorators.CrystalFruitTreeDecorator;
import net.jitl.common.world.gen.tree_grower.decorators.FrozenTreeDecorator;
import net.jitl.common.world.gen.tree_grower.decorators.IceShroomTreeDecorator;
import net.jitl.common.world.gen.tree_grower.decorators.IcyBrushTreeDecorator;
import net.jitl.common.world.gen.tree_grower.foliageplacers.SphericalFoliagePlacer;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;

public class JConfiguredFeatures {

    public static final RuleTest GRASS = new BlockStateMatchTest(Blocks.GRASS_BLOCK.defaultBlockState());
    public static final RuleTest SAND = new BlockStateMatchTest(Blocks.SAND.defaultBlockState());
    public static final RuleTest CORBA_MUD = new TagMatchTest(JTags.CORBA_MUD);
    public static final RuleTest EUCA_GRASS = new TagMatchTest(JTags.EUCA_GRASS);
    public static final RuleTest DEPTHS_LAMP_REPLACEABLES = new TagMatchTest(JTags.DEPTHS_LAMP_REPLACEABLES);
    public static final RuleTest CLOUDIA_CLOUD_REPLACEABLES = new TagMatchTest(JTags.CLOUDIA_CLOUD_REPLACEABLES);
    public static final RuleTest NETHER_ORE_REPLACEABLES = new TagMatchTest(JTags.NETHER_ORE_REPLACEABLES);
    public static final RuleTest OVERWORLD_REPLACEABLES = new TagMatchTest(JTags.OVERWORLD_ORE_REPLACEABLES);
    public static final RuleTest OVERWORLD_DEEPSLATE_REPLACEABLES = new TagMatchTest(JTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest BASALT_REPLACEABLES = new TagMatchTest(JTags.BASALT_ORE_REPLACEABLES);
    public static final RuleTest END_REPLACEABLES = new TagMatchTest(JTags.END_STONE);
    public static final RuleTest EUCA_REPLACEABLES = new TagMatchTest(JTags.EUCA_STONE_ORE_REPLACEABLES);
    public static final RuleTest FROZEN_REPLACEABLES = new TagMatchTest(JTags.FROZEN_STONE_ORE_REPLACEABLES);
    public static final RuleTest CORBA_REPLACEABLES = new TagMatchTest(JTags.CORBA_STONE_ORE_REPLACEABLES);
    public static final RuleTest DEPTHS_REPLACEABLES = new TagMatchTest(JTags.DEPTHS_STONE_ORE_REPLACEABLES);
    public static final RuleTest BOIL_REPLACEABLES = new TagMatchTest(JTags.BOIL_STONE_ORE_REPLACEABLES);

    //OVERWORLD
    public static final ResourceKey<ConfiguredFeature<?, ?>> DESERT_OVERWORLD_RUINS = registerKey("desert_overworld_ruins"),
            DEFAULT_OVERWORLD_RUINS = registerKey("default_overworld_ruins"),
            GLOWSHROOMS = registerKey("glowshrooms"),
            IRIDIUM_ORE = registerKey("iridium_ore"),
            SAPPHIRE_ORE = registerKey("sapphire_ore"),
            SHADIUM_ORE = registerKey("shadium_ore"),
           // VERDITE_ORE = registerKey("verdite_ore"),
            LUNIUM_ORE = registerKey("lunium_ore");

    //NETHER
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMITHSTONE = registerKey("smithstone"),
            BLEEDSTONE = registerKey("bleedstone"),
            WARPED_QUARTZ_ORE = registerKey("warped_quartz_ore"),
            CRIMSON_QUARTZ_ORE = registerKey("crimson_quartz_ore"),
            BLOODCRUST_ORE = registerKey("bloodcrust_ore"),
            FIRESTONE_ORE = registerKey("firestone_ore"),
            BLOOD_ROCK = registerKey("blood_rock");

    //END
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDERILLIUM_ORE = registerKey("enderillium_ore");

    //EUCA
    public static final ResourceKey<ConfiguredFeature<?, ?>> EUCA_GOLD_TREE = registerKey("euca_gold_tree"),
            EUCA_GREEN_TREE = registerKey("euca_green_tree"),
            EUCA_BOULDER = registerKey("euca_boulder"),
            EUCA_GOLDITE_RUINS = registerKey("euca_goldite_ruins"),
            EUCA_RUINS = registerKey("euca_ruins"),
            MEKYUM_ORE = registerKey("mekyum_ore"),
            CELESTIUM_ORE = registerKey("celestium_ore"),
            STORON_ORE = registerKey("storon_ore"),
            KORITE_ORE = registerKey("korite_ore"),
            GOLDITE_VEG = registerKey("goldite_veg"),
            GOLD_VEG = registerKey("gold_veg"),
            SINGLE_EUCA_GRASS = registerKey("gold_grass"),
            SINGLE_GOLDITE_STALKS = registerKey("goldite_stalks"),
            EUCA_WATER = registerKey("euca_water"),
            GOLD_BOT_SPAWNER = registerKey("gold_bot_spawner"),
            EUCA_PUMPKIN = registerKey("euca_pumpkin");

    //DEPTHS
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEPTHS_LAMP_ROOF = registerKey("depths_lamp_roof"),
            DEPTHS_LAMP_FLOOR = registerKey("depths_lamp_floor"),
            FLAIRIUM_ORE = registerKey("flairium_ore"),
            DES_ORE = registerKey("des_ore"),
            DEPTHS_VEG = registerKey("depths_veg"),
            DEPTHS_TREE = registerKey("depths_tree"),
            DEPTHS_CRYSTAL = registerKey("depths_crystal"),
            FLOOR_DEPTHS_CRYSTAL = registerKey("floor_depths_crystal"),
            DEPTHS_WATER = registerKey("depths_water"),
            DEPTHS_GEODE = registerKey("depths_geode"),
            CRYSTAL_DRIPSTONE = registerKey("crystal_dripstone");

    //BOIL
    public static final ResourceKey<ConfiguredFeature<?, ?>> VOLCANIC_ROCK = registerKey("volcanic_rock"),
            BOIL_STALAGMITE = registerKey("scorched_stalagmite"),
            SULPHUR_DEPOSIT = registerKey("sulphur_deposit"),
            SULPHUR_CRYSTAL = registerKey("sulphur_crystal"),
            LARGE_CHARRED_TREE = registerKey("large_charred_tree"),
            DYING_BURNED_TREE = registerKey("dying_burned_tree"),
            MEDIUM_BURNED_TREE = registerKey("medium_burned_tree"),
            SMALL_BURNED_TREE = registerKey("small_burned_tree"),
            SCORCHED_CACTUS = registerKey("scorched_cactus"),
            FIRE = registerKey("boil_fire"),
            BOIL_PLAINS_VEG = registerKey("boil_veg"),
            BOIL_SANDS_VEG = registerKey("boil_sands_veg"),
            BOIL_UNDERGROWTH = registerKey("boil_undergrowth"),
            CHARRED_FIELDS_VEG = registerKey("charred_veg"),
            ASHUAL_ORE = registerKey("ashual_ore"),
            BLAZIUM_ORE = registerKey("blazium_ore");

    //FROZEN
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_FROZEN_TREE = registerKey("small_frozen_tree"),
            MEDIUM_FROZEN_TREE = registerKey("medium_frozen_tree"),
            LARGE_FROZEN_TREE = registerKey("large_frozen_tree"),
            RIMESTONE_ORE = registerKey("rimestone_ore"),
            PERIDOT_ORE = registerKey("peridot_ore"),
            FROZEN_VEG = registerKey("frozen_veg"),
            FROZEN_FLOWERS = registerKey("frozen_flowers"),
            LARGE_FROZEN_BITTERWOOD_TREE = registerKey("large_frozen_bitterwood_tree"),
            MEDIUM_FROZEN_BITTERWOOD_TREE = registerKey("medium_frozen_bitterwood_tree"),
            SMALL_FROZEN_BITTERWOOD_TREE = registerKey("small_frozen_bitterwood_tree"),
            ICE_SPIKE = registerKey("frozen_ice_spike"),
            GLACIAL_ROCK = registerKey("glacial_rock");

    //CORBA
    public static final ResourceKey<ConfiguredFeature<?, ?>>
            ORBADITE_ORE = registerKey("orbadite_ore"),
            GORBITE_ORE = registerKey("gorbite_ore"),
            CORBA_VEG = registerKey("corba_veg"),
            CORBA_TALL_GRASS = registerKey("corba_tall_grass"),
            CORBA_TALL_PLANTS = registerKey("corba_tall_plants"),
            CORBA_RUINS = registerKey("corba_ruins"),
            CORBA_LILY_PAD = registerKey("corba_lily_pad"),
            BOGSHROOMS = registerKey("bogshrooms"),
            CORBA_TREE_SMALL = registerKey("corba_tree_small"),
            CORBA_TREE_MEDIUM = registerKey("corba_tree_medium"),
            CORBA_TREE_LARGE = registerKey("corba_tree_large"),
            CORBA_SWAMP_TREE = registerKey("corba_swamp_tree");

    //TERRANIA
    public static final ResourceKey<ConfiguredFeature<?, ?>>
            TERRANIA_VEG = registerKey("terrania_veg"),
            TERRANIA_TALL_GRASS = registerKey("terrania_tall_grass"),
            ENCHANTED_SHROOMS = registerKey("enchanted_shrooms"),
            ENCHANTED_SHROOMS_TALL = registerKey("enchanted_shrooms_tall"),
            TALL_TERRAMUSHROOM = registerKey("tall_terramushroom"),
            SMALL_TERRAMUSHROOM = registerKey("small_terramushroom"),
            HUGE_PINK_TERRASHROOM = registerKey("huge_pink_terrashroom"),
            HUGE_PURPLE_TERRASHROOM = registerKey("huge_purple_terrashroom"),
            MEGA_TERRANIAN_TREE = registerKey("mega_terranian_tree");

    //CLOUDIA
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLOUDIA_TERRAIN = registerKey("cloudia_terrain"),
            CLOUDIA_CLOUD_BLUE = registerKey("blue_cloudia_clouds"),
            CLOUDIA_CLOUD_LIGHT_BLUE = registerKey("light_blue_cloudia_clouds"),
            CLOUDIA_CLOUD_PINK = registerKey("pink_cloudia_clouds"),
            CLOUDIA_ISLAND = registerKey("cloudia_island");

    //SENTERIAN
    public static final ResourceKey<ConfiguredFeature<?, ?>> SENTERIAN_TERRAIN = registerKey("senterian_terrain");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        //OVERWORLD
        register(context, DESERT_OVERWORLD_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(SAND, BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SANDSTONE.defaultBlockState(), 3).add(Blocks.CHISELED_SANDSTONE.defaultBlockState(), 1).add(Blocks.CUT_SANDSTONE.defaultBlockState(), 2)), 5, 5, 8, BuiltInLootTables.DESERT_PYRAMID.location()));
        register(context, DEFAULT_OVERWORLD_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(GRASS, BlockStateProvider.simple(JBlocks.JOURNEY_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.STONE_BRICKS.defaultBlockState(), 6).add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 5).add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 4).add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3).add(Blocks.COBBLESTONE.defaultBlockState(), 4).add(Blocks.CHISELED_STONE_BRICKS.defaultBlockState(), 2).add(Blocks.INFESTED_COBBLESTONE.defaultBlockState(), 1).add(Blocks.INFESTED_STONE_BRICKS.defaultBlockState(), 1).add(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState(), 1).add(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState(), 1)),5, 5, 8, BuiltInLootTables.ABANDONED_MINESHAFT.location()));
        register(context, IRIDIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(OVERWORLD_REPLACEABLES, JBlocks.IRIDIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OVERWORLD_DEEPSLATE_REPLACEABLES, JBlocks.DEEPSLATE_IRIDIUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, SAPPHIRE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(OVERWORLD_REPLACEABLES, JBlocks.SAPPHIRE_ORE.get().defaultBlockState()), OreConfiguration.target(OVERWORLD_DEEPSLATE_REPLACEABLES, JBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, SHADIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(OVERWORLD_REPLACEABLES, JBlocks.SHADIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OVERWORLD_DEEPSLATE_REPLACEABLES, JBlocks.DEEPSLATE_SHADIUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, LUNIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(OVERWORLD_REPLACEABLES, JBlocks.LUNIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OVERWORLD_DEEPSLATE_REPLACEABLES, JBlocks.DEEPSLATE_LUNIUM_ORE.get().defaultBlockState()))).get(), 7));
//        register(context, VERDITE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(OVERWORLD_REPLACEABLES, JBlocks.VERDITE_ORE.get().defaultBlockState()), OreConfiguration.target(OVERWORLD_DEEPSLATE_REPLACEABLES, JBlocks.DEEPSLATE_VERDITE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, GLOWSHROOMS, Feature.FLOWER, new RandomPatchConfiguration(64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.GREEN_GLOWSHROOM.get().defaultBlockState(), JBlocks.TALL_GREEN_GLOWSHROOM.get().defaultBlockState(), JBlocks.BLUE_GLOWSHROOM.get().defaultBlockState(), JBlocks.TALL_BLUE_GLOWSHROOM.get().defaultBlockState(), JBlocks.RED_GLOWSHROOM.get().defaultBlockState(), JBlocks.TALL_RED_GLOWSHROOM.get().defaultBlockState()))))));

        //NETHER
        register(context, SMITHSTONE, JFeatures.SMITHSTONE.get(), new NoneFeatureConfiguration());
        register(context, BLEEDSTONE, JFeatures.BLEEDSTONE.get(), new NoneFeatureConfiguration());
        register(context, WARPED_QUARTZ_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(NETHER_ORE_REPLACEABLES, JBlocks.WARPED_QUARTZ_ORE.get().defaultBlockState()))).get(), 12));
        register(context, CRIMSON_QUARTZ_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(NETHER_ORE_REPLACEABLES, JBlocks.CRIMSON_QUARTZ_ORE.get().defaultBlockState()))).get(), 12));
        register(context, FIRESTONE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(BASALT_REPLACEABLES, JBlocks.FIRESTONE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, BLOODCRUST_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(NETHER_ORE_REPLACEABLES, JBlocks.BLOODCRUST_ORE.get().defaultBlockState()))).get(), 7));
        register(context, BLOOD_ROCK, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(NETHER_ORE_REPLACEABLES, JBlocks.BLOOD_ROCK.get().defaultBlockState()))).get(), 10));

        //END
        register(context, ENDERILLIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(END_REPLACEABLES, JBlocks.ENDERILLIUM_ORE.get().defaultBlockState()))).get(), 7));

        //EUCA
        register(context, EUCA_GOLD_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.EUCA_GOLD_LOG.get()), new ForkingTrunkPlacer(4, 1, 6), BlockStateProvider.simple(JBlocks.EUCA_GOLD_LEAVES.get()), new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get())).build());
        register(context, EUCA_GREEN_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.EUCA_BROWN_LOG.get()), new ForkingTrunkPlacer(4, 1, 6), BlockStateProvider.simple(JBlocks.EUCA_GREEN_LEAVES.get()), new SphericalFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 1), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(JBlocks.GOLDITE_DIRT.get())).build());
        register(context, EUCA_BOULDER, JFeatures.BOULDER.get(), new BlockStateConfiguration(JBlocks.GOLDITE_COBBLESTONE.get().defaultBlockState()));
        register(context, EUCA_WATER, JFeatures.EUCA_WATER_GEN.get(), new SpringConfiguration(Fluids.WATER.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, JBlocks.GOLDITE_STONE.get(), JBlocks.GOLDITE_DIRT.get())));
        register(context, GOLD_BOT_SPAWNER, JFeatures.GOLD_BOT_SPAWNER.get(), new NoneFeatureConfiguration());
        register(context, EUCA_GOLDITE_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(EUCA_GRASS, BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3).add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1).add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)), 8, 6, 9, BuiltInLootTables.VILLAGE_WEAPONSMITH.location()));
        register(context, EUCA_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(EUCA_GRASS, BlockStateProvider.simple(JBlocks.EUCA_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.EUCA_SQUARE_BRICKS.get().defaultBlockState(), 3).add(JBlocks.EUCA_SQUARE_RUNIC_BRICKS.get().defaultBlockState(), 1).add(JBlocks.EUCA_RUNIC_BRICKS.get().defaultBlockState(), 2)), 7, 6, 9, BuiltInLootTables.VILLAGE_WEAPONSMITH.location()));
        register(context, MEKYUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(EUCA_REPLACEABLES, JBlocks.MEKYUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, KORITE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(EUCA_REPLACEABLES, JBlocks.KORITE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, STORON_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(EUCA_REPLACEABLES, JBlocks.STORON_ORE.get().defaultBlockState()))).get(), 7));
        register(context, CELESTIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(EUCA_REPLACEABLES, JBlocks.CELESTIUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, GOLDITE_VEG , Feature.FLOWER, new RandomPatchConfiguration(40, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.GOLDITE_TALL_GRASS.get().defaultBlockState(), JBlocks.GOLDITE_STALKS.get().defaultBlockState(), JBlocks.GOLDITE_FLOWER.get().defaultBlockState(), JBlocks.GOLDITE_BULB.get().defaultBlockState()))))));
        register(context, GOLD_VEG , Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.EUCA_SILVER_FLOWER.get().defaultBlockState(), JBlocks.EUCA_TALL_FLOWERS.get().defaultBlockState(), JBlocks.EUCA_TALL_GRASS.get().defaultBlockState()))))));
        register(context, SINGLE_GOLDITE_STALKS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(JBlocks.GOLDITE_STALKS.get().defaultBlockState())));
        register(context, SINGLE_EUCA_GRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(JBlocks.EUCA_TALL_GRASS.get().defaultBlockState())));
        register(context, EUCA_PUMPKIN, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(JBlocks.EUCA_PUMPKIN.get())), List.of(JBlocks.EUCA_GOLD_GRASS.get())));

        //DEPTHS
        register(context, DEPTHS_LAMP_ROOF, JFeatures.ROOF_DEPTHS_LAMP.get(), new NoneFeatureConfiguration());
        register(context, DEPTHS_LAMP_FLOOR, JFeatures.FLOOR_DEPTHS_LAMP.get(), new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(DEPTHS_LAMP_REPLACEABLES, JBlocks.DEPTHS_LIGHT.get().defaultBlockState()))).get(), 32));
        register(context, DEPTHS_CRYSTAL, JFeatures.DEPTHS_CRYSTAL.get(), new NoneFeatureConfiguration());
        register(context, DEPTHS_GEODE, Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR), BlockStateProvider.simple(JBlocks.DEPTHS_CRYSTAL_BLOCK.get()), BlockStateProvider.simple(JBlocks.BUDDING_DEPTHS_CRYSTAL.get()), BlockStateProvider.simple(JBlocks.DEPTHS_LIGHT.get()), BlockStateProvider.simple(JBlocks.DEPTHS_CRYSTAL_BLOCK.get()), List.of(JBlocks.DEPTHS_CRYSTAL.get().defaultBlockState()), BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS), new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                new GeodeCrackSettings(0.95, 2.0, 2), 0.35, 0.083, true,
                UniformInt.of(4, 6),
                UniformInt.of(3, 4),
                UniformInt.of(1, 2),
                -16, 16, 0.05, 1));
        register(context, DEPTHS_TREE, JFeatures.JTREE.get(), createStraightBlobTree(JBlocks.DEPTHS_LOG.get(), JBlocks.DEPTHS_LEAVES.get(), 7, 3, 0, 2).build());
        register(context, FLAIRIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(DEPTHS_REPLACEABLES, JBlocks.FLAIRIUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, DES_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(DEPTHS_REPLACEABLES, JBlocks.DES_ORE.get().defaultBlockState()))).get(), 7));
        register(context, DEPTHS_VEG , Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.DEPTHS_BLUE_FLOWER.get().defaultBlockState(), JBlocks.DEPTHS_FLOWER.get().defaultBlockState()))))));
        register(context, FLOOR_DEPTHS_CRYSTAL , Feature.FLOWER, new RandomPatchConfiguration(60, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.FLOOR_DEPTHS_CRYSTAL_YELLOW.get().defaultBlockState(), JBlocks.FLOOR_DEPTHS_CRYSTAL_PINK.get().defaultBlockState(), JBlocks.FLOOR_DEPTHS_CRYSTAL_GREEN.get().defaultBlockState(), JBlocks.FLOOR_DEPTHS_CRYSTAL_BLUE.get().defaultBlockState()))))));
        register(context, DEPTHS_WATER, JFeatures.DEPTHS_WATER_GEN.get(), new SpringConfiguration(Fluids.WATER.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, JBlocks.DEPTHS_GRASS.get(), JBlocks.DEPTHS_DIRT.get(), JBlocks.DEPTHS_DIRT.get())));
        register(context, CRYSTAL_DRIPSTONE, JFeatures.CRYSTAL_DRIPSTONE.get(), new DripstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F),  ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8));


        //BOIL
        register(context, VOLCANIC_ROCK, JFeatures.VOLCANIC_ROCK.get(), new NoneFeatureConfiguration());
        register(context, BOIL_STALAGMITE, JFeatures.SCORCHED_STALAGMITE.get(), new NoneFeatureConfiguration());
        register(context, SULPHUR_DEPOSIT, JFeatures.SULPHUR_DEPOSIT.get(), new BlockStateConfiguration(JBlocks.SULPHUR_ROCK.get().defaultBlockState()));
        register(context, SULPHUR_CRYSTAL, JFeatures.SULPHUR_CRYSTAL.get(), new NoneFeatureConfiguration());
        register(context, LARGE_CHARRED_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(5, 5, 5), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());
        register(context, DYING_BURNED_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(2, 1, 1), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).forceDirt().dirt(BlockStateProvider.simple(JBlocks.VOLCANIC_SAND.get())).build());
        register(context, MEDIUM_BURNED_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(4, 4, 4), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());
        register(context, SMALL_BURNED_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.BURNED_BARK.get()), new ForkingTrunkPlacer(3, 3, 3), BlockStateProvider.simple(JBlocks.CHARRED_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CHARRED_GRASS.get())).build());
        register(context, BLAZIUM_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(BOIL_REPLACEABLES, JBlocks.BLAZIUM_ORE.get().defaultBlockState()))).get(), 7));
        register(context, ASHUAL_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(BOIL_REPLACEABLES, JBlocks.ASHUAL_ORE.get().defaultBlockState()))).get(), 7));
        register(context, SCORCHED_CACTUS, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 5), BlockStateProvider.simple(JBlocks.SCORCHED_CACTUS.get())), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(JBlocks.SCORCHED_CACTUS.get().defaultBlockState(), BlockPos.ZERO))))));
        register(context, BOIL_PLAINS_VEG, Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.INFERNO_BUSH.get().defaultBlockState(), JBlocks.FLAME_POD.get().defaultBlockState(), JBlocks.CRISP_GRASS.get().defaultBlockState(), JBlocks.LAVA_BLOOM.get().defaultBlockState()))))));
        register(context, CHARRED_FIELDS_VEG, Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.CHARRED_BRUSH.get().defaultBlockState(), JBlocks.CHARRED_SHORT_GRASS.get().defaultBlockState(), JBlocks.CHARRED_WEEDS.get().defaultBlockState(), JBlocks.TALL_CHARRED_GRASS.get().defaultBlockState()))))));
        register(context, BOIL_SANDS_VEG , Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.TALL_MOLTEN_PLANT.get().defaultBlockState(), JBlocks.TALL_CRUMBLING_PINE.get().defaultBlockState(), JBlocks.LAVA_BLOOM.get().defaultBlockState(), JBlocks.CRUMBLING_PINE.get().defaultBlockState(), JBlocks.INFERNO_BUSH.get().defaultBlockState()))))));
        register(context, FIRE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(50, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(Blocks.FIRE.defaultBlockState()))))));
        register(context, BOIL_UNDERGROWTH, Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.SIZZLESHROOM.get().defaultBlockState(), JBlocks.TALL_SIZZLESHROOM.get().defaultBlockState()))))));

        //FROZEN
        register(context, SMALL_FROZEN_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new ForkingTrunkPlacer(2, 1, 3), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).build());
        register(context, MEDIUM_FROZEN_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new FancyTrunkPlacer(10, 5, 5), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).decorators(ImmutableList.of(IcyBrushTreeDecorator.INSTANCE, new IceShroomTreeDecorator(0.2F), new CrystalFruitTreeDecorator(4))).build());
        register(context, LARGE_FROZEN_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new FancyTrunkPlacer(15, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).decorators(ImmutableList.of(IcyBrushTreeDecorator.INSTANCE, new IceShroomTreeDecorator(0.2F), new CrystalFruitTreeDecorator(4))).build());
        register(context, LARGE_FROZEN_BITTERWOOD_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(15, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)),new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F))).build());
        register(context, MEDIUM_FROZEN_BITTERWOOD_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new GiantTrunkPlacer(10, 7, 7), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CRUMBLED_PERMAFROST.get())).decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F))).build());
        register(context, SMALL_FROZEN_BITTERWOOD_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.FROZEN_LOG.get().defaultBlockState()), new StraightTrunkPlacer(4, 2, 3), BlockStateProvider.simple(JBlocks.FROZEN_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(2)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.GRASSY_PERMAFROST.get())).decorators(ImmutableList.of(new FrozenTreeDecorator(0.01F))).build());
        register(context, ICE_SPIKE, JFeatures.FROZEN_ICE_SPIKE.get(), new NoneFeatureConfiguration());
        register(context, RIMESTONE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(FROZEN_REPLACEABLES, JBlocks.RIMESTONE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, PERIDOT_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(FROZEN_REPLACEABLES, JBlocks.PERIDOT_ORE.get().defaultBlockState()))).get(), 7));
        register(context, FROZEN_VEG , Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.ICE_BUSH.get().defaultBlockState(), JBlocks.FROSTBERRY_THORN.get().defaultBlockState()))))));
        register(context, FROZEN_FLOWERS, Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.FROZEN_BLOOM.get().defaultBlockState(), JBlocks.CICLEBLOOM.get().defaultBlockState(), JBlocks.ICE_BUD.get().defaultBlockState()))))));
        register(context, GLACIAL_ROCK, JFeatures.BOULDER.get(), new BlockStateConfiguration(JBlocks.GLACIAL_ROCK.get().defaultBlockState()));

        //CORBA
        register(context, ORBADITE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(CORBA_REPLACEABLES, JBlocks.ORBADITE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, GORBITE_ORE, Feature.ORE, new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(CORBA_REPLACEABLES, JBlocks.GORBITE_ORE.get().defaultBlockState()))).get(), 7));
        register(context, CORBA_VEG , Feature.FLOWER, new RandomPatchConfiguration(60, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.CORBA_BLUE_FLOWER.get().defaultBlockState(), JBlocks.CORBA_RED_FLOWER.get().defaultBlockState(), JBlocks.CORBA_SPECKLED_FLOWER.get().defaultBlockState(), JBlocks.CORBA_PURPLE_FLOWER.get().defaultBlockState(), JBlocks.CORBA_LIGHT_PURPLE_FLOWER.get().defaultBlockState(), JBlocks.CORBA_DARK_PURPLE_FLOWER.get().defaultBlockState(), JBlocks.CORBA_FLOWER.get().defaultBlockState()))))));
        register(context, CORBA_TALL_GRASS , Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.CORBA_TALL_GRASS.get().defaultBlockState()))))));
        register(context, CORBA_TALL_PLANTS, Feature.FLOWER, new RandomPatchConfiguration(60, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.BOGWEED.get().defaultBlockState()))))));
        register(context, BOGSHROOMS, Feature.FLOWER, new RandomPatchConfiguration(40, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.SMALL_BOGSHROOM.get().defaultBlockState(), JBlocks.TALL_BOGSHROOM.get().defaultBlockState()))))));
        register(context, CORBA_RUINS, JFeatures.RUINS.get(), new RuinsFeatureConfig(CORBA_MUD, BlockStateProvider.simple(JBlocks.CORBA_CHEST.get()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(JBlocks.CORBA_BRICKS.get().defaultBlockState(), 6).add(JBlocks.CORBA_CRACKED_BRICKS.get().defaultBlockState(), 3).add(JBlocks.CORBA_DARK_BRICKS.get().defaultBlockState(), 4).add(JBlocks.CORBA_LIGHT_BRICKS.get().defaultBlockState(), 3)),5, 5, 8, BuiltInLootTables.ABANDONED_MINESHAFT.location()));
        register(context, CORBA_LILY_PAD, JFeatures.SWAMP_LILY.get(), new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(JBlocks.SWAMP_LILY.get())))));
        register(context, CORBA_TREE_SMALL, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.CORBA_LOG.get().defaultBlockState()), new StraightTrunkPlacer(6, 4, 1), BlockStateProvider.simple(JBlocks.CORBA_LEAVES.get().defaultBlockState()), new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CORBA_DIRT.get())).build());
        register(context, CORBA_TREE_MEDIUM, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.CORBA_LOG.get().defaultBlockState()), new ForkingTrunkPlacer(5, 2, 1), BlockStateProvider.simple(JBlocks.CORBA_LEAVES.get().defaultBlockState()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CORBA_DIRT.get())).build());
        register(context, CORBA_TREE_LARGE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.CORBA_LOG.get().defaultBlockState()), new GiantTrunkPlacer(13, 2, 5), BlockStateProvider.simple(JBlocks.CORBA_LEAVES.get().defaultBlockState()), new MegaPineFoliagePlacer(UniformInt.of(4, 6), UniformInt.of(0, 2), UniformInt.of(13, 17)), new TwoLayersFeatureSize(3, 1, 3)).ignoreVines().forceDirt().dirt(BlockStateProvider.simple(JBlocks.CORBA_DIRT.get())).build());
        register(context, CORBA_SWAMP_TREE, JFeatures.CORBA_SWAMP_TREE.get(), new NoneFeatureConfiguration());

        //TERRANIA
        register(context, TERRANIA_VEG , Feature.FLOWER, new RandomPatchConfiguration(40, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.TERRANIAN_FLOWER.get().defaultBlockState(), JBlocks.TERRANIAN_TALL_GRASS.get().defaultBlockState()))))));
        register(context, TERRANIA_TALL_GRASS , Feature.FLOWER, new RandomPatchConfiguration(40, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.TERRANIAN_TALL_GRASS.get().defaultBlockState()))))));
        register(context, ENCHANTED_SHROOMS, Feature.FLOWER, new RandomPatchConfiguration(40, 5, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.ENCHANTED_SHROOMS_SMALL.get().defaultBlockState()))))));
        register(context, ENCHANTED_SHROOMS_TALL, Feature.FLOWER, new RandomPatchConfiguration(40, 3, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.ENCHANTED_SHROOMS_TALL.get().defaultBlockState()))))));
        register(context, TALL_TERRAMUSHROOM, Feature.FLOWER, new RandomPatchConfiguration(35, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.TALL_TERRAMUSHROOM.get().defaultBlockState()))))));
        register(context, SMALL_TERRAMUSHROOM, Feature.FLOWER, new RandomPatchConfiguration(35, 3, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(JBlocks.TERRAMUSHROOM.get().defaultBlockState()))))));
        register(context, HUGE_PINK_TERRASHROOM, Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(JBlocks.TERRAMUSHROOM_BLOCK_PINK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.TRUE).setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), BlockStateProvider.simple(JBlocks.TERRASHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.FALSE).setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), 3));
        register(context, HUGE_PURPLE_TERRASHROOM, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(JBlocks.TERRAMUSHROOM_BLOCK_PURPLE.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), BlockStateProvider.simple(JBlocks.TERRASHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.FALSE).setValue(HugeMushroomBlock.DOWN, Boolean.FALSE)), 2));
        register(context, MEGA_TERRANIAN_TREE, JFeatures.JTREE.get(), new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(JBlocks.TERRANIAN_LOG.get().defaultBlockState()), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(JBlocks.TERRANIAN_LEAVES.get()), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).decorators(ImmutableList.of(TerranianTrunkVineDecorator.INSTANCE, new TerranianLeaveVineDecorator(0.25F))).forceDirt().dirt(BlockStateProvider.simple(JBlocks.TERRANIAN_DIRT.get())).build());

        //CLOUDIA
        register(context, CLOUDIA_TERRAIN, JFeatures.CLOUDIA_TERRAIN.get(), new NoneFeatureConfiguration());
        register(context, CLOUDIA_CLOUD_BLUE, JFeatures.CLOUDIA_CLOUDS.get(), new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(CLOUDIA_CLOUD_REPLACEABLES, JBlocks.BLUE_CLOUDIA_CLOUD.get().defaultBlockState()))).get(), 20));
        register(context, CLOUDIA_CLOUD_LIGHT_BLUE, JFeatures.CLOUDIA_CLOUDS.get(), new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(CLOUDIA_CLOUD_REPLACEABLES, JBlocks.LIGHT_BLUE_CLOUDIA_CLOUD.get().defaultBlockState()))).get(), 20));
        register(context, CLOUDIA_CLOUD_PINK, JFeatures.CLOUDIA_CLOUDS.get(), new OreConfiguration(Suppliers.memoize(() -> List.of(OreConfiguration.target(CLOUDIA_CLOUD_REPLACEABLES, JBlocks.PINK_CLOUDIA_CLOUD.get().defaultBlockState()))).get(), 20));
        register(context, CLOUDIA_ISLAND, JFeatures.CLOUDIA_ISLAND.get(), new NoneFeatureConfiguration());

        //SENTERIAN
        register(context, SENTERIAN_TERRAIN, JFeatures.SENTERIAN_TERRAIN.get(), new NoneFeatureConfiguration());

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(JITL.MODID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static TreeConfig.JTreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int baseHeight, int heightRandA, int heightRandB, int width) {
        return new TreeConfig.JTreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(width), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }
}