package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.TerranianPlacedFeatures;
import net.jitl.core.init.internal.JEntities;
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

public class TerranianBiomes extends Biomes {

    public static int SKY_COLOUR = 8847544;
    public static int FOG_COLOUR = 3473505;
    public static int WATER_COLOUR = 10682526;
    public static int WATER_FOG_COLOUR = 10682526;
    public static int GRASS_COLOUR = 16711927;
    public static int FOLIAGE_COLOUR = 15597823;

    public static Biome terrania(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.MEGA_TERRANIAN_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TERRANIA_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TERRANIA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TALL_TERRAMUSHROOM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.SMALL_TERRAMUSHROOM);

//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.ARANA_KING_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.PURPLIAN_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRAGROW_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRASCATTERER_TYPE.get(), 5, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRASLUG_TYPE.get(), 5, 1, 1));

        return biome(true, 1F, 0.4F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.TERRANIA_MUSIC));
    }

    public static Biome mushroomForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.HUGE_PINK_TERRASHROOM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.HUGE_PURPLE_TERRASHROOM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TERRANIA_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TERRANIA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.ENCHANTED_SHROOMS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.ENCHANTED_SHROOMS_TALL);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.TALL_TERRAMUSHROOM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TerranianPlacedFeatures.SMALL_TERRAMUSHROOM);
//
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.PURPLIAN_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRAGROW_TYPE.get(), 5, 1, 2));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRASCATTERER_TYPE.get(), 5, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRASHROOM_TYPE.get(), 5, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TERRASLUG_TYPE.get(), 5, 1, 1));
//        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.FLUNGAS_TYPE.get(), 3, 1, 1));

        return biome(true, 1F, 0.4F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.TERRANIA_MUSIC));
    }
}