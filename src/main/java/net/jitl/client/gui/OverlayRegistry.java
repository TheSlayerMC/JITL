package net.jitl.client.gui;

import net.jitl.client.gui.overlay.EssenceBar;
import net.jitl.client.gui.overlay.PortalOverlayRenderer;
import net.jitl.core.init.JITL;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@EventBusSubscriber(modid = JITL.MOD_ID, value = Dist.CLIENT)//, bus = EventBusSubscriber.Bus.MOD)
public class OverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAboveAll(JITL.rl("essence_bar"), new EssenceBar());
        event.registerAboveAll(JITL.rl("portal_overlay"), new PortalOverlayRenderer());
    }
}
