package net.jitl.core.data.world_gen.biome;

import net.jitl.core.data.world_gen.placed_features.CorbaPlacedFeatures;
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

public class CorbaBiomes extends Biomes {

    public static Biome bogweedFields(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.ORBADITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.GORBITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_PLANTS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_LILY_PAD);
        biomeSetting.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CorbaPlacedFeatures.CORBA_RUINS);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.LEAF_BLOWER_TYPE.get(), 3, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SURFACE_SEER_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.WOOD_CREATURE_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SMELLY_TYPE.get(), 2, 1, 1));

        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), 20, 1, 2));

        return biome(false, 0.8F, 0.4F, 23609, 1524224, 800768, 800768, 800769, 800768,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.CORBA_MUSIC));
    }

    public static Biome corbaPlains(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.ORBADITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.GORBITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_VEG);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TREE_MEDIUM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TREE_LARGE);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.LEAF_BLOWER_TYPE.get(), 3, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SURFACE_SEER_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.WOOD_CREATURE_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SMELLY_TYPE.get(), 2, 1, 1));

        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), 20, 1, 2));

        return biome(false, 0.8F, 0.4F, 763904, 1524224, 3496974, 3496974, 800768, 800768,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.CORBA_MUSIC));
    }

    public static Biome taintedSwamp(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.ORBADITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.GORBITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_PLANTS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_SWAMP_TREE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.BOGSHROOMS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_LILY_PAD);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.NATURE_MAGE_TYPE.get(), 3, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.LEAF_BLOWER_TYPE.get(), 3, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SURFACE_SEER_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.WOOD_CREATURE_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.STINKY_TYPE.get(), 2, 1, 3));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SMELLY_TYPE.get(), 4, 1, 1));

        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), 20, 1, 2));
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.SWAMP_FLY_TYPE.get(), 40, 1, 3));

        return biome(false, 0.8F, 0.4F, 23609, 1524224, 800769, 800769, 3496974, 3496974,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.CORBA_MUSIC));
    }

    public static Biome taintedForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder biomeSetting = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();

        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.ORBADITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CorbaPlacedFeatures.GORBITE_ORE);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TALL_GRASS);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TREE_SMALL);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TREE_MEDIUM);
        biomeSetting.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CorbaPlacedFeatures.CORBA_TREE_LARGE);

        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.TREE_GOLEM_TYPE.get(), 5, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.LEAF_BLOWER_TYPE.get(), 3, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SURFACE_SEER_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.WOOD_CREATURE_TYPE.get(), 4, 1, 1));
        mobSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(JEntities.SMELLY_TYPE.get(), 2, 1, 1));

        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(JEntities.CORBANIAN_MOLLUSK_TYPE.get(), 20, 1, 2));

        return biome(false, 0.8F, 0.4F, 1927192, 1524224, 3496974, 3496974, 800769, 800769,
                mobSettings, biomeSetting, Musics.createGameMusic(JSounds.CORBA_MUSIC));
    }
}