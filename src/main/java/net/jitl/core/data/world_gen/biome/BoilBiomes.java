package net.jitl.core.data.world_gen.biome;

import net.jitl.common.world.dimension.JCarver;
import net.jitl.core.data.world_gen.carver.CarverFeatureKeys;
import net.jitl.core.data.world_gen.placed_features.BoilPlacedFeatures;
import net.jitl.core.data.world_gen.placed_features.EucaPlacedFeatures;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BoilBiomes extends Biomes {

    public static int SKY_COLOUR = 4262144;
    public static int FOG_COLOUR = 4262144;
    public static int WATER_COLOUR = 4262144;
    public static int WATER_FOG_COLOUR = 4262144;
    public static int GRASS_COLOUR = 4262144;
    public static int FOLIAGE_COLOUR = 4262144;

    public static Biome boil(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
       // biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.BOIL_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.FIRE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_PLAINS_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.ASHUAL_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.BLAZIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_UNDERGROWTH);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.MAGMA_BLAZE_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.FRIGHTENER_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.BURNING_LIGHT_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FLAME_LOTUS_TYPE.get(), 15, 1, 3));

        return biome(false, 2F, 0, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.BOIL_MUSIC));
    }

    public static Biome charredFields(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        //biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.BOIL_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.FIRE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.CHARRED_FIELDS_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.ASHUAL_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.BLAZIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_UNDERGROWTH);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.VOLCANIC_ROCK);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.LARGE_CHARRED_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.MEDIUM_BURNED_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.SMALL_BURNED_TREE);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.MAGMA_BLAZE_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.FRIGHTENER_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.BURNING_LIGHT_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FLAME_LOTUS_TYPE.get(), 15, 2, 3));

        return biome(false, 2F, 0, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.FLAME_POLLEN.get(), 0.001428F, Musics.createGameMusic(JSounds.BOIL_MUSIC));
    }

    public static Biome scorchedWastelands(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        //biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.BOIL_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.FIRE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.ASHUAL_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.BLAZIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_UNDERGROWTH);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.VOLCANIC_ROCK);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_STALAGMITE);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.MAGMA_BLAZE_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.FRIGHTENER_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.BURNING_LIGHT_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FLAME_LOTUS_TYPE.get(), 15, 2, 3));

        return biome(false, 2F, 0, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.BOIL_MUSIC));
    }

    public static Biome boilingSands(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        //biomeSetting.addCarver(GenerationStep.Carving.AIR, CarverFeatureKeys.BOIL_CARVER);

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.FIRE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_SANDS_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.ASHUAL_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BoilPlacedFeatures.BLAZIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.BOIL_UNDERGROWTH);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.SCORCHED_CACTUS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, BoilPlacedFeatures.SULPHUR_DEPOSIT);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.SULPHUR_CRYSTAL);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BoilPlacedFeatures.DYING_BURNED_TREE);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.MAGMA_BLAZE_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.FRIGHTENER_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.BURNING_LIGHT_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FLAME_LOTUS_TYPE.get(), 15, 2, 3));

        return biome(false, 2F, 0, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.SULPHUR.get(), 0.001428F, Musics.createGameMusic(JSounds.BOIL_MUSIC));
    }
}