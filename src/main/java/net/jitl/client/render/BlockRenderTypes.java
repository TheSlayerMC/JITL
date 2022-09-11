package net.jitl.client.render;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRenderTypes {

    @SubscribeEvent
    public static void registerRenderTypes(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(JBlocks.EUCA_BROWN_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(JBlocks.EUCA_GOLD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(JBlocks.EUCA_BROWN_TRAP_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(JBlocks.EUCA_GOLD_TRAP_DOOR.get(), RenderType.cutout());
    }
}