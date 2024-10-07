package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.CloudiaPlacedFeatures;
import net.jitl.core.data.world_gen.placed_features.SenterianPlacedFeatures;
import net.jitl.core.data.world_gen.placed_features.TerranianPlacedFeatures;
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

public class CloudiaBiomes extends Biomes {

    public static int SKY_COLOUR = 61183;
    public static int FOG_COLOUR = 61183;
    public static int WATER_COLOUR = 61183;
    public static int WATER_FOG_COLOUR = 61183;
    public static int GRASS_COLOUR = 61183;
    public static int FOLIAGE_COLOUR = 61183;

    public static Biome cloudia(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CloudiaPlacedFeatures.CLOUDIA_TERRAIN);
        biomeSetting.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, CloudiaPlacedFeatures.CLOUDIA_CLOUD_BLUE);
        biomeSetting.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, CloudiaPlacedFeatures.CLOUDIA_CLOUD_LIGHT_BLUE);
        biomeSetting.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, CloudiaPlacedFeatures.CLOUDIA_CLOUD_PINK);
        biomeSetting.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, CloudiaPlacedFeatures.CLOUDIA_ISLAND);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.CLOUD_GHOST_TYPE.get(), 1, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SKY_EEL_TYPE.get(), 1, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.STARLIGHT_GOLEM_TYPE.get(), 1, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.STARLIGHT_TRANSPORTER_TYPE.get(), 1, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.STARLIGHT_WALKER_TYPE.get(), 1, 1, 3));

        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.AERO_LOTUS_TYPE.get(), 15, 1, 1));

        return biome(false, 1F, 0F, SKY_COLOUR, FOG_COLOUR, WATER_COLOUR, WATER_FOG_COLOUR, GRASS_COLOUR, FOLIAGE_COLOUR,
                mobSettings, biomeSetting, JParticleManager.CLOUDIA_PORTAL.get(), 0.001428F, Musics.createGameMusic(JSounds.CLOUDIA_MUSIC));
    }
}