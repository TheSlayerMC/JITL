package net.jitl.client.music;

import net.jitl.core.helper.EnumStructureMusic;
import net.jitl.core.init.JITL;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class StructureMusicHandler {
    public static EnumStructureMusic currentMusic;

    @SubscribeEvent
    public static void structureMusicTick(TickEvent.ClientTickEvent event) {
        if (currentMusic != null) {
            JMusicTicker.addTrack(currentMusic.getMusicForStructure());
        }
    }
}
