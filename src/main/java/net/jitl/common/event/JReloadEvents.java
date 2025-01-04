package net.jitl.common.event;

import net.jitl.client.render.world.clouds.JCloudRenderer;
import net.jitl.core.init.JITL;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = JITL.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class JReloadEvents {

    @SubscribeEvent
    public static void addReloadListener(final AddReloadListenerEvent event) {
        event.addListener(new JCloudRenderer());
    }
}
