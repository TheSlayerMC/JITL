package net.jitl.core.init;

import net.jitl.client.ClientEventHandler;
import net.jitl.client.render.RenderEntitys;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.dimension.JCarver;
import net.jitl.common.world.gen.*;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.data.*;
import net.jitl.core.data.block_generation.*;
import net.jitl.core.init.internal.JContainers;
import net.jitl.core.init.internal.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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

        JBlocks.BLOCKS.register(modEventBus);
        JItems.ITEMS.register(modEventBus);
        JEntities.REGISTRY.register(modEventBus);
        JEntities.EGG_REGISTRY.register(modEventBus);
        JConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        JPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        JFoliagePlacers.REGISTRY.register(modEventBus);
        JFeatures.REGISTRY.register(modEventBus);
        JContainers.REGISTRY.register(modEventBus);
        JBlockEntities.REGISTRY.register(modEventBus);
        Dimensions.REGISTRY.register(modEventBus);
        JCarver.REGISTRY.register(modEventBus);
        JEnchantments.REGISTRY.register(modEventBus);
        JTreeDecorators.REGISTRY.register(modEventBus);
        JParticleManager.REGISTRY.register(modEventBus);

        ClientEventHandler.regToBus(forgeEventBus);

        if(DEV_MODE) {
            new JItemGenerator().generate();
            new JBlockGenerator().generate();
            new JBlockChestGenerator().generate();
            new JBlockRotatableGenerator().generate();
            new JBlockFurnaceGenerator().generate();
            new JBlockCrossGenerator().generate();
            new JBlockAttachedCrossGenerator().generate();
            new JBlockPillarGenerator().generate();
            new JBlockDoorGenerator().generate();
            new JBlockTrapDoorGenerator().generate();
            new JBlockStairsGenerator().generate();
            new JBlockSlabGenerator().generate();
            new JBlockPressurePlateGenerator().generate();
            new JBlockButtonGenerator().generate();
            new JBlockGateGenerator().generate();
            new JBlockFenceGenerator().generate();
            new JBlockGrassGenerator().generate();
            new LangRegistry().generate();
        }

        modEventBus.addListener(this::commonInit);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueue);

        JNetworkRegistry.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, JClientConfig.SPEC, "jitl-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JCommonConfig.SPEC, "jitl-common.toml");

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
