package net.jitl.common.world.gen;

import net.jitl.common.world.gen.boil.ScorchedStalagmiteFeature;
import net.jitl.common.world.gen.boil.SulphurCrystalFeature;
import net.jitl.common.world.gen.boil.SulphurDepositFeature;
import net.jitl.common.world.gen.boil.VolcaniocRockFeature;
import net.jitl.common.world.gen.depths.DepthsCrystalFeature;
import net.jitl.common.world.gen.depths.DepthsLampFeature;
import net.jitl.common.world.gen.depths.DepthsLampFloorFeature;
import net.jitl.common.world.gen.euca.BoulderFeature;
import net.jitl.common.world.gen.frozen.FrozenIceSpikeFeature;
import net.jitl.common.world.gen.ruins.RuinsFeature;
import net.jitl.common.world.gen.ruins.RuinsFeatureConfig;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JFeatures {

    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    //OVERWORLD
    public static final RegistryObject<Feature<RuinsFeatureConfig>> RUINS = REGISTRY.register("ruins", () -> new RuinsFeature(RuinsFeatureConfig.CODEC));

    //DEPTHS
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ROOF_DEPTHS_LAMP = REGISTRY.register("depths_lamp_roof", () -> new DepthsLampFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> FLOOR_DEPTHS_LAMP = REGISTRY.register("depths_lamp_floor", () -> new DepthsLampFloorFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DEPTHS_CRYSTAL = REGISTRY.register("depths_crystal", () -> new DepthsCrystalFeature(NoneFeatureConfiguration.CODEC));

    //BOIL
    public static final RegistryObject<Feature<BlockStateConfiguration>> BOULDER = REGISTRY.register("boulder", () -> new BoulderFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCORCHED_STALAGMITE = REGISTRY.register("scorched_stalagmite", () -> new ScorchedStalagmiteFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> SULPHUR_DEPOSIT = REGISTRY.register("sulphur_deposit", () -> new SulphurDepositFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SULPHUR_CRYSTAL = REGISTRY.register("sulphur_crystal", () -> new SulphurCrystalFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VOLCANIC_ROCK = REGISTRY.register("volcanic_rock", () -> new VolcaniocRockFeature(NoneFeatureConfiguration.CODEC));

    //EUCA
    public static final RegistryObject<Feature<SpringConfiguration>> EUCA_WATER_GEN = REGISTRY.register("euca_water_gen", () -> new SpringFeature(SpringConfiguration.CODEC));

    //FROZEN
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FROZEN_ICE_SPIKE = REGISTRY.register("frozen_ice_spike", () -> new FrozenIceSpikeFeature(NoneFeatureConfiguration.CODEC));

}
