package net.jitl.core.init;

import net.jitl.client.ClientEventHandler;
import net.jitl.client.render.ModelPropertyRegistry;
import net.jitl.client.render.RenderEntitys;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.dimension.JCarver;
import net.jitl.common.world.gen.JFeatures;
import net.jitl.common.world.gen.JFoliagePlacers;
import net.jitl.common.world.gen.JTreeDecorators;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.data.*;
import net.jitl.core.data.block_generation.*;
import net.jitl.core.init.internal.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(JITL.MODID)
public class JITL {
    public static final String MODID = "jitl", PREFIX = MODID + ":";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final boolean DEV_MODE = true;

    public JITL() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(JTabs::registerTabs);
        JItems.ITEMS.register(modEventBus);
        JBlocks.BLOCKS.register(modEventBus);
        JItems.BLOCK_ITEMS.register(modEventBus);
        JEntities.REGISTRY.register(modEventBus);
        JAttributes.REGISTRY.register(modEventBus);
        JFoliagePlacers.REGISTRY.register(modEventBus);
        JFeatures.REGISTRY.register(modEventBus);
        JContainers.REGISTRY.register(modEventBus);
        JBlockEntities.REGISTRY.register(modEventBus);
        Dimensions.REGISTRY.register(modEventBus);
        JCarver.REGISTRY.register(modEventBus);
        StructureRegistry.REGISTRY.register(modEventBus);
        JEnchantments.REGISTRY.register(modEventBus);
        JParticleManager.REGISTRY.register(modEventBus);
        JTreeDecorators.REGISTRY.register(modEventBus);
        JSounds.REGISTRY.register(modEventBus);
        JTabs.REGISTRY.register(modEventBus);

        if(DEV_MODE) {
            new BlockBreakingGenerator().generate();
            new JItemGenerator().generate();
            new JBowItemGenerator().generate();
            new JShieldItemGenerator().generate();
            new JBlockGenerator().generate();
            new JTerrainBlockGenerator().generate();
            new JBlockChestGenerator().generate();
            new JBlockRotatableGenerator().generate();
            new JBlockTrophyGenerator().generate();
            new JBlockFurnaceGenerator().generate();
            new JBlockCrossGenerator().generate();
            new JBlockLilyGenerator().generate();
            new JBlockDoublePlantGenerator().generate();
            new JBlockBushGenerator().generate();
            new JBlockAttachedCrossGenerator().generate();
            new JBlockPillarGenerator().generate();
            new JBlockBottomTopGenerator().generate();
            new JBlockWallGenerator().generate();
            new JBlockDoorGenerator().generate();
            new JBlockTrapDoorGenerator().generate();
            new JBlockPaneGenerator().generate();
            new JBlockSlimeGenerator().generate();
            new JBlockStairsGenerator().generate();
            new JBlockSlabGenerator().generate();
            new JBlockPressurePlateGenerator().generate();
            new JBlockVineGenerator().generate();
            new JBlockButtonGenerator().generate();
            new JBlockGateGenerator().generate();
            new JBlockFenceGenerator().generate();
            new JBlockGrassGenerator().generate();
            new JBlockOverlayGrassGenerator().generate();
            new JBlockTintedCrossGenerator().generate();
            new JBlockTintedLeavesGenerator().generate();
            new JBlockPortalGenerator().generate();
            new JBlockCampfireGenerator().generate();
            new JBlockPathGenerator().generate();
            new JBlockLadderGenerator().generate();
            new JRandomizedBlockGenerator().generate();
            new JBlockFarmGenerator().generate();
            new JBlockEndPortalGenerator().generate();
            new JBlockEndFrameGenerator().generate();
            new JBlockMushroomGenerator().generate();
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
        ModelPropertyRegistry.init();
        JContainers.register();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        ClientEventHandler.regToBus(forgeEventBus);
    }

    private void enqueue(InterModEnqueueEvent event) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("heart_container").icon(rl("gui/curios/heart_container")).priority(1).size(2).build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("catalyst").icon(rl("gui/curios/catalyst")).priority(1).size(2).build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(2).build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
    }

    public static ResourceLocation rl(String r) {
        return new ResourceLocation(MODID, r);
    }

    public static ResourceLocation tl(String r) {
        return new ResourceLocation(MODID, "textures/" + r);
    }

    public static ResourceLocation getRegistryName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }

    public static ResourceLocation getRegistryName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public static ResourceLocation getRegistryName(EntityType<?> entity) {
        return ForgeRegistries.ENTITY_TYPES.getKey(entity);
    }
}
