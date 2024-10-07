package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.SenterianPlacedFeatures;
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

public class SenterianBiomes extends Biomes {

    public static int SKY_COLOUR = 61183;
    public static int FOG_COLOUR = 61183;
    public static int WATER_COLOUR = 61183;
    public static int WATER_FOG_COLOUR = 61183;
    public static int GRASS_COLOUR = 61183;
    public static int FOLIAGE_COLOUR = 61183;

    public static Biome senterian(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, SenterianPlacedFeatures.SENTERIAN_TERRAIN);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SENTRY_LORD_TYPE.get(), 2, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SENTRY_WALKER_TYPE.get(), 2, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SENTRY_STALKER_TYPE.get(), 2, 1, 1));

        return biome(false, 1F, 0.0F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.SENTERIAN_PORTAL));
    }
}