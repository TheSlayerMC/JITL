package net.jitl.common.world.dimension;

import net.jitl.core.init.JITL;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber
public class JBiomeRegistry {

    public static Biome EUCA_PLAINS;
    public static Biome EUCA_GOLDITE_GRAINS;

    public static Biome FROZEN_WASTES;
    public static Biome FROZEN_DYING_FOREST;
    public static Biome FROZEN_BITTERWOOD_FOREST;

    public static Biome BOILING_PLAINS;
    public static Biome BOILING_SANDS;
    public static Biome CHARRED_FIELDS;
    public static Biome SCORCHED_WASTELANDS;


    @SubscribeEvent
    public static void registerBiomes(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BIOMES, (helper) -> {
            helper.register(JITL.rl("frozen_wastes"), FROZEN_WASTES);
            helper.register(JITL.rl("dying_forest"), FROZEN_DYING_FOREST);
            helper.register(JITL.rl("bitterwood_forest"), FROZEN_BITTERWOOD_FOREST);

            helper.register(JITL.rl("euca_plains"), EUCA_PLAINS);
            helper.register(JITL.rl("euca_goldite_grains"), EUCA_GOLDITE_GRAINS);

            helper.register(JITL.rl("boiling_plains"), BOILING_PLAINS);
            helper.register(JITL.rl("boiling_sands"), BOILING_SANDS);
            helper.register(JITL.rl("charred_fields"), CHARRED_FIELDS);
            helper.register(JITL.rl("scorched_wastelands"), SCORCHED_WASTELANDS);
        });
    }
}
