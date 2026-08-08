package net.jitl.core.data.world_gen.biome;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import javax.annotation.Nullable;

public class Biomes {

    protected static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    public static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int skyColour, int fogColour, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride, MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music backgroundMusic) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .waterColor(waterColor)
        ;
        if(grassColorOverride != null)
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);

        if(foliageColorOverride != null)
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);

        assert backgroundMusic != null;
        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, waterFogColor)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, fogColour)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, skyColour)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(backgroundMusic))
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }


    public static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int skyColour, int fogColour, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride,
                              MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, SimpleParticleType particle, float prob, @Nullable Music backgroundMusic) {

        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder().waterColor(waterColor);

        if(grassColorOverride != null)
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);

        if(foliageColorOverride != null)
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);

        assert backgroundMusic != null;
        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, waterFogColor)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, fogColour)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, skyColour)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(backgroundMusic))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(particle, prob))
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }
}
