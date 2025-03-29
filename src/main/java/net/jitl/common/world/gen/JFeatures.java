package net.jitl.common.world.gen;

import net.jitl.common.world.gen.boil.ScorchedStalagmiteFeature;
import net.jitl.common.world.gen.boil.SulphurCrystalFeature;
import net.jitl.common.world.gen.boil.SulphurDepositFeature;
import net.jitl.common.world.gen.boil.VolcaniocRockFeature;
import net.jitl.common.world.gen.cloudia.CloudFeature;
import net.jitl.common.world.gen.cloudia.CloudiaLand;
import net.jitl.common.world.gen.cloudia.CloudiaTerrain;
import net.jitl.common.world.gen.corba.CorbaSwampTreeFeature;
import net.jitl.common.world.gen.corba.SwampLilyFeature;
import net.jitl.common.world.gen.depths.*;
import net.jitl.common.world.gen.euca.BoulderFeature;
import net.jitl.common.world.gen.euca.EucaBotSpawner;
import net.jitl.common.world.gen.depths.JSpikeFeature;
import net.jitl.common.world.gen.frozen.FrozenIceSpikeFeature;
import net.jitl.common.world.gen.nether.BleedstoneFeature;
import net.jitl.common.world.gen.nether.SmithstoneFeature;
import net.jitl.common.world.gen.ruins.RuinsFeature;
import net.jitl.common.world.gen.ruins.RuinsFeatureConfig;
import net.jitl.common.world.gen.senterian.SenterianTerrain;
import net.jitl.common.world.gen.tree_grower.JourneyTree;
import net.jitl.common.world.gen.tree_grower.TreeConfig;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JFeatures {

    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(Registries.FEATURE, JITL.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfig>> JTREE = REGISTRY.register("tree", JourneyTree::new);

    //OVERWORLD
    public static final DeferredHolder<Feature<?>, Feature<RuinsFeatureConfig>> RUINS = REGISTRY.register("ruins", () -> new RuinsFeature(RuinsFeatureConfig.CODEC));

    //NETHER
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SMITHSTONE = REGISTRY.register("smithstone", () -> new SmithstoneFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BLEEDSTONE = REGISTRY.register("bleedstone", () -> new BleedstoneFeature(NoneFeatureConfiguration.CODEC));

    //EUCA
    public static final DeferredHolder<Feature<?>, Feature<SpringConfiguration>> EUCA_WATER_GEN = REGISTRY.register("euca_water_gen", () -> new SpringFeature(SpringConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GOLD_BOT_SPAWNER = REGISTRY.register("gold_bot_spawner", () -> new EucaBotSpawner(NoneFeatureConfiguration.CODEC));

    //DEPTHS
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROOF_DEPTHS_LAMP = REGISTRY.register("depths_lamp_roof", () -> new DepthsLampFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<OreConfiguration>> FLOOR_DEPTHS_LAMP = REGISTRY.register("depths_lamp_floor", () -> new DepthsLampFloorFeature(OreConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DEPTHS_CRYSTAL = REGISTRY.register("depths_crystal", () -> new DepthsCrystalFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<SpringConfiguration>> DEPTHS_WATER_GEN = REGISTRY.register("depths_water_gen", () -> new SpringFeature(SpringConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<DripstoneClusterConfiguration>> CRYSTAL_DRIPSTONE = REGISTRY.register("crystal_dripstone", () -> new CrystalDripstoneClusterFeature(JBlocks.POINTED_CRYSTALLIZED_DRIPSTONE.get(), JBlocks.CRYSTALLIZED_DRIPSTONE.get(), DripstoneClusterConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DEPTHS_PILLAR = REGISTRY.register("depths_pillar", () -> new DepthsPillarFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<DripstoneClusterConfiguration>> DEPTHS_DRIPSTONE = REGISTRY.register("depths_dripstone", () -> new CrystalDripstoneClusterFeature(JBlocks.POINTED_DEPTHS_DRIPSTONE.get(), JBlocks.DEPTHS_DRIPSTONE.get(), DripstoneClusterConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DEPTHS_SPIKE = REGISTRY.register("depths_spike", () -> new JSpikeFeature(JBlocks.DEPTHS_DRIPSTONE.get(), NoneFeatureConfiguration.CODEC));

    //BOIL
    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BOULDER = REGISTRY.register("boulder", () -> new BoulderFeature(BlockStateConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCORCHED_STALAGMITE = REGISTRY.register("scorched_stalagmite", () -> new ScorchedStalagmiteFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> SULPHUR_DEPOSIT = REGISTRY.register("sulphur_deposit", () -> new SulphurDepositFeature(BlockStateConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SULPHUR_CRYSTAL = REGISTRY.register("sulphur_crystal", () -> new SulphurCrystalFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> VOLCANIC_ROCK = REGISTRY.register("volcanic_rock", () -> new VolcaniocRockFeature(NoneFeatureConfiguration.CODEC));

    //FROZEN
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FROZEN_ICE_SPIKE = REGISTRY.register("frozen_ice_spike", () -> new FrozenIceSpikeFeature(NoneFeatureConfiguration.CODEC));

    //CORBA
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CORBA_SWAMP_TREE = REGISTRY.register("corba_swamp_tree", () -> new CorbaSwampTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<RandomPatchConfiguration>> SWAMP_LILY = REGISTRY.register("swamp_lily", () -> new SwampLilyFeature(RandomPatchConfiguration.CODEC));

    //CLOUDIA
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CLOUDIA_TERRAIN = REGISTRY.register("cloudia_terrain", CloudiaTerrain::new);
    public static final DeferredHolder<Feature<?>, Feature<OreConfiguration>> CLOUDIA_CLOUDS = REGISTRY.register("cloudia_clouds", () -> new CloudFeature(OreConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CLOUDIA_ISLAND = REGISTRY.register("cloudia_island", CloudiaLand::new);

    //SENTERIAN
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SENTERIAN_TERRAIN = REGISTRY.register("senterian_terrain", SenterianTerrain::new);

}
