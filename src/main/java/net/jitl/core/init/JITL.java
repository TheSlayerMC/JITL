package net.jitl.core.init;

import net.jitl.client.render.RenderEntitys;
import net.jitl.common.world.gen.JConfiguredFeatures;
import net.jitl.common.world.gen.JFoliagePlacers;
import net.jitl.common.world.gen.JPlacedFeatures;
import net.jitl.core.data.*;
import net.jitl.core.helper.JContainers;
import net.jitl.core.init.internal.*;
import net.minecraft.resources.ResourceLocation;
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

        ItemRegistrys.ITEMS.register(modEventBus);
        BlockRegistrys.BLOCKS.register(modEventBus);
        JEntities.REGISTRY.register(modEventBus);
        JEntities.EGG_REGISTRY.register(modEventBus);
        JConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        JPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        JFoliagePlacers.REGISTRY.register(modEventBus);
        JContainers.REGISTRY.register(modEventBus);

        //Used to recognise the classes
        new JBlocks();
        new JItems();

        if(DEV_MODE) {
            new JItemGenerator().generate();
            new JBlockGenerator().generate();
            new JBlockCrossGenerator().generate();
            new JBlockLogGenerator().generate();
            new JBlockDoorGenerator().generate();
            new JBlockTrapDoorGenerator().generate();
            new JBlockStairsGenerator().generate();
            new JBlockSlabGenerator().generate();
            new JBlockPressurePlateGenerator().generate();
            new JBlockButtonGenerator().generate();
            new JBlockGateGenerator().generate();
            new JBlockFenceGenerator().generate();
            new LangRegistry().generate();
        }

        modEventBus.addListener(this::commonInit);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueue);

        JNetworkRegistry.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonInit(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        RenderEntitys.registerAnimationRenderers();
    }

    private void enqueue(InterModEnqueueEvent event) { }

    public static ResourceLocation rl(String r) {
        return new ResourceLocation(MODID, r);
    }

}
