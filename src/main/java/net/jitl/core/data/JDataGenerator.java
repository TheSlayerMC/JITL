package net.jitl.core.data;

import net.jitl.core.data.loot.JLootTableSubProvider;
import net.jitl.core.data.recipe.JRecipeRegistry;
import net.jitl.core.data.world_gen.ConfiguredFeaturesGenerator;
import net.jitl.core.data.world_gen.carver.CarverGenerator;
import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.MOD)
public class JDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new JRecipeRegistry(generator.getPackOutput(), lookupProvider));
        generator.addProvider(event.includeServer(), new JLootTableSubProvider(generator.getPackOutput(), lookupProvider));
        //generator.addProvider(event.includeServer(), new CarverGenerator(generator.getPackOutput(), lookupProvider));
        generator.addProvider(event.includeServer(), new ConfiguredFeaturesGenerator(generator.getPackOutput(), event.getLookupProvider()));
        //generator.addProvider(event.includeServer(), new PlacedBonmealFeaturesGenerator(generator.getPackOutput()));
        //generator.addProvider(event.includeServer(), new JEnchantmentGenerator(generator.getPackOutput(), lookupProvider));

    }
}