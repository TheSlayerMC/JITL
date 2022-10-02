package net.jitl.common.world.gen;

import net.jitl.common.world.gen.boil.ScorchedStalagmiteFeature;
import net.jitl.common.world.gen.boil.SulphurCrystalFeature;
import net.jitl.common.world.gen.boil.SulphurDepositFeature;
import net.jitl.common.world.gen.boil.VolcaniocRockFeature;
import net.jitl.common.world.gen.euca.BoulderFeature;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JFeatures {

    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    public static final RegistryObject<Feature<BlockStateConfiguration>> BOULDER = REGISTRY.register("boulder", () -> new BoulderFeature(BlockStateConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SCORCHED_STALAGMITE = REGISTRY.register("scorched_stalagmite", () -> new ScorchedStalagmiteFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<BlockStateConfiguration>> SULPHUR_DEPOSIT = REGISTRY.register("sulphur_deposit", () -> new SulphurDepositFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SULPHUR_CRYSTAL = REGISTRY.register("sulphur_crystal", () -> new SulphurCrystalFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VOLCANIC_ROCK = REGISTRY.register("volcanic_rock", () -> new VolcaniocRockFeature(NoneFeatureConfiguration.CODEC));

}
