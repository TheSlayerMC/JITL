package net.jitl.core.data;

import net.jitl.core.data.loot.JLootTableSubProvider;
import net.jitl.core.data.recipe.JRecipeRegistry;
import net.jitl.core.data.world_gen.ConfiguredFeaturesGenerator;
import net.jitl.core.init.JITL;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        //generator.addProvider(event.includeServer(), new JRecipeRegistry(generator.getPackOutput()));
        //generator.addProvider(event.includeServer(), new JLootTableSubProvider(generator.getPackOutput()));
        //generator.addProvider(event.includeServer(), new CarverGenerator(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new ConfiguredFeaturesGenerator(generator.getPackOutput()));
        //generator.addProvider(event.includeServer(), new PlacedBonmealFeaturesGenerator(generator.getPackOutput()));
    }
}