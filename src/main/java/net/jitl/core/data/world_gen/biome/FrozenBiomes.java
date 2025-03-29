package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.BoilPlacedFeatures;
import net.jitl.core.data.world_gen.placed_features.EucaPlacedFeatures;
import net.jitl.core.data.world_gen.placed_features.FrozenPlacedFeatures;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FrozenBiomes extends Biomes {

    public static int SKY_COLOUR = 8297684;
    public static int FOG_COLOUR = 7112378;
    public static int WATER_COLOUR = 3145727;
    public static int WATER_FOG_COLOUR = 25855;
    public static int GRASS_COLOUR = 3145727;
    public static int FOLIAGE_COLOUR = 25855;

    public static Biome bitterwoodForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
       // biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.FROZEN_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.RIMESTONE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.PERIDOT_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.LARGE_FROZEN_BITTERWOOD_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.MEDIUM_FROZEN_BITTERWOOD_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.SMALL_FROZEN_BITTERWOOD_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_FLOWERS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, FrozenPlacedFeatures.GLACIAL_ROCK);

//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_SHRIEKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.PERMAFRAUST_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.CRYSTAL_CLUSTER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHATTERER_TYPE.get(), 5, 1, 2));
//
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FROZEN_TROLL_TYPE.get(), 20, 1, 3));
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.SHIVERWOLF_TYPE.get(), 20, 1, 3));
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CAPYBARA_TYPE.get(), 20, 1, 3));

        return biome(true, -0.5F, 0.4F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.SNOWFLAKE.get(), 0.03428F, Musics.createGameMusic(JSounds.FROZEN_MUSIC));
    }

    public static Biome dyingForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        // biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.FROZEN_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.RIMESTONE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.PERIDOT_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.LARGE_FROZEN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.MEDIUM_FROZEN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.SMALL_FROZEN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_FLOWERS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, FrozenPlacedFeatures.GLACIAL_ROCK);

//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_SHRIEKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.PERMAFRAUST_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.CRYSTAL_CLUSTER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHATTERER_TYPE.get(), 5, 1, 2));
//
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FROZEN_TROLL_TYPE.get(), 20, 1, 3));
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.SHIVERWOLF_TYPE.get(), 20, 1, 3));

        return biome(true, -0.5F, 0.4F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.SNOWFLAKE.get(), 0.001428F, Musics.createGameMusic(JSounds.FROZEN_MUSIC));
    }

    public static Biome frozenWastes(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        // biomeSetting.addCarver(GenerationStep.Carving.AIR, worldCarvers.getOrThrow(CarverFeatureKeys.FROZEN_CARVER));

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.RIMESTONE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FrozenPlacedFeatures.PERIDOT_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FrozenPlacedFeatures.FROZEN_FLOWERS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, FrozenPlacedFeatures.ICE_SPIKE);

//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_BUSHWALKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIVERING_SHRIEKER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.PERMAFRAUST_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.CRYSTAL_CLUSTER_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHATTERER_TYPE.get(), 5, 1, 2));
//
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.FROZEN_TROLL_TYPE.get(), 20, 1, 3));
//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CAPYBARA_TYPE.get(), 20, 1, 3));

        return biome(true, -0.8F, 0.85F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.SNOWFLAKE.get(), 0.06428F, Musics.createGameMusic(JSounds.FROZEN_MUSIC));
    }
}