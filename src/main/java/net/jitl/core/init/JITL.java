package net.jitl.core.init;

import net.jitl.common.world.gen.JConfiguredFeatures;
import net.jitl.common.world.gen.JPlacedFeatures;
import net.jitl.core.data.JBlockGenerator;
import net.jitl.core.data.JItemGenerator;
import net.jitl.core.data.LangRegistry;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(JITL.MODID)
public class JITL {
    public static final String MODID = "jitl", PREFIX = MODID + ":";
    public static final String NAME = "Journey Into the Light";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final boolean DEV_MODE = true;

    public JITL() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        JItems.ITEMS.register(modEventBus);
        JBlocks.BLOCKS.register(modEventBus);
        JConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        JPlacedFeatures.PLACED_FEATURES.register(modEventBus);

        if(DEV_MODE) {
            new JItemGenerator().generate();
            new JBlockGenerator().generate();
            new LangRegistry().generate();
        }

        modEventBus.addListener(this::preInit);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueue);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void preInit(final FMLCommonSetupEvent event) { }

    private void clientSetup(final FMLClientSetupEvent event) { }

    private void enqueue(InterModEnqueueEvent event) { }
}
