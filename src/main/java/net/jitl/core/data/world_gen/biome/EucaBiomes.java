package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.EucaPlacedFeatures;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class EucaBiomes extends Biomes {

    public static int EUCA_SKY_COLOUR = 14876538;
    public static int EUCA_FOG_COLOUR = 15597506;
    public static int EUCA_WATER_COLOUR = 14995007;
    public static int EUCA_WATER_FOG_COLOUR = 3355141;
    public static int EUCA_GRASS_COLOUR = 13159169;
    public static int EUCA_FOLIAGE_COLOUR = 5660928;

    public static Biome eucaPlains(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.EUCA_GREEN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.EUCA_GOLD_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EucaPlacedFeatures.CELESTIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EucaPlacedFeatures.KORITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.GOLDITE_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.EUCA_PUMPKIN);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EucaPlacedFeatures.EUCA_RUINS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EucaPlacedFeatures.EUCA_BOULDER);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EucaPlacedFeatures.GOLD_BOT_SPAWNER);
        biomeSetting.addFeature(GenerationStep.Decoration.LAKES, EucaPlacedFeatures.EUCA_WATER);

//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.EUCA_CHARGER_TYPE.get(), 15, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DYNASTER_TYPE.get(), 15, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.GOLDER_TYPE.get(), 15, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SHIMMERER_TYPE.get(), 5, 1, 1));

        return biome(false, 0.8F, 0, EUCA_SKY_COLOUR, EUCA_FOG_COLOUR, EUCA_WATER_COLOUR, EUCA_WATER_FOG_COLOUR, EUCA_GRASS_COLOUR, EUCA_FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.GOLDITE_FLOWER.get(), 0.001428F, Musics.createGameMusic(JSounds.EUCA_MUSIC));
    }

    public static Biome golditeGrains(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.EUCA_GREEN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EucaPlacedFeatures.STORON_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EucaPlacedFeatures.MEKYUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.GOLDITE_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EucaPlacedFeatures.SINGLE_GOLDITE_STALKS);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EucaPlacedFeatures.EUCA_GOLDITE_RUINS);
        biomeSetting.addFeature(GenerationStep.Decoration.LAKES, EucaPlacedFeatures.EUCA_WATER);

//        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.EUCA_HOPPER.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.EUCA_CHARGER_TYPE.get(), 15, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DYNASTER_TYPE.get(), 15, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.GOLDER_TYPE.get(), 15, 1, 2));

        return biome(false, 0.8F, 0, EUCA_SKY_COLOUR, EUCA_FOG_COLOUR, EUCA_WATER_COLOUR, EUCA_WATER_FOG_COLOUR, EUCA_GRASS_COLOUR, EUCA_FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.GOLDITE_FLOWER.get(), 0.001428F, Musics.createGameMusic(JSounds.EUCA_MUSIC));
    }
}