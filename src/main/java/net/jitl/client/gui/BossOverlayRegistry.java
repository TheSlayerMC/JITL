package net.jitl.client.gui;

import net.jitl.common.entity.IJourneyBoss;
import net.jitl.common.entity.base.JBossInfo;
import net.jitl.core.init.JITL;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class BossOverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(CustomizeGuiOverlayEvent.BossEventProgress event) {
        IJourneyBoss boss = JBossInfo.map.get(event.getBossEvent().getId());
        if(boss != null) {
            event.setCanceled(true);
            boss.getBossBar().render(event);
        }
    }
}
