package net.jitl.client.gui;

import net.jitl.core.init.JITL;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class BossOverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(CustomizeGuiOverlayEvent.BossEventProgress event) {
//        IJourneyBoss boss = JBossInfo.map.get(event.getBossEvent()));
//        if(boss != null) {
//            event.setCanceled(true);
//            boss.getBossBar().render(event);
//        }TODO
    }
}
