package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.DepthsPlacedFeatures;
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

public class DepthsBiomes extends Biomes {

    public static int SKY_COLOUR = 7907327;
    public static int FOG_COLOUR = 12638463;
    public static int WATER_COLOUR = 4159204;
    public static int WATER_FOG_COLOUR = 329011;
    public static int GRASS_COLOUR = 4159204;
    public static int FOLIAGE_COLOUR = 4159204;

    public static Biome depths(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DepthsPlacedFeatures.FLAIRIUM_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DepthsPlacedFeatures.DES_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DepthsPlacedFeatures.DEPTHS_LAMP_FLOOR);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DepthsPlacedFeatures.DEPTHS_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DepthsPlacedFeatures.DEPTHS_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DepthsPlacedFeatures.DEPTHS_CRYSTAL);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DepthsPlacedFeatures.FLOOR_DEPTHS_CRYSTAL);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, DepthsPlacedFeatures.DEPTHS_GEODE);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, DepthsPlacedFeatures.DEPTHS_LAMP_ROOF);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DARKENER_TYPE.get(), 5, 1, 2));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DEPTHS_HUNTER_TYPE.get(), 2, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SPIKED_BEAST_TYPE.get(), 5, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DEPTHS_BEAST_TYPE.get(), 5, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.ROC_TYPE.get(), 5, 1, 2));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.DARKNESS_CRAWLER_TYPE.get(), 5, 1, 2));

        return biome(false, 1F, 0, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.DEPTHS_MUSIC));
    }
}