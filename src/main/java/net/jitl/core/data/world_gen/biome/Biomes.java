package net.jitl.core.data.world_gen.biome;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;

import javax.annotation.Nullable;

public class Biomes {

    protected static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    public static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int skyColour, int fogColour, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride,
                              MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music backgroundMusic) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .skyColor(skyColour)
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .fogColor(fogColour)
                .skyColor(calculateSkyColor(temperature))
                .backgroundMusic(backgroundMusic);
        if(grassColorOverride != null)
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);

        if(foliageColorOverride != null)
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int skyColour, int fogColour, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride,
                              MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .skyColor(skyColour)
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .fogColor(fogColour)
                .skyColor(calculateSkyColor(temperature));
        if(grassColorOverride != null)
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);

        if(foliageColorOverride != null)
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int skyColour, int fogColour, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride,
                              MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, SimpleParticleType particle, float prob, @Nullable Music backgroundMusic) {

        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .skyColor(skyColour)
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .fogColor(fogColour)
                .skyColor(calculateSkyColor(temperature))
                .ambientParticle(new AmbientParticleSettings(particle, prob))
                .backgroundMusic(backgroundMusic);
        if(grassColorOverride != null)
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);

        if(foliageColorOverride != null)
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }
}
