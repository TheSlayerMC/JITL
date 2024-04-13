package net.jitl.client.music;

import net.jitl.core.config.JClientConfig;
import net.jitl.core.helper.JMusic;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GUIMusicHandler {

    @SubscribeEvent()
    public static void guiMusicTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null && JClientConfig.ENABLE_JITL_MENU_SCREEN.get()) {
            JMusicTicker.addTrack(new JMusic(JSounds.MENU_MUSIC.get(), 10, 0, 0));
        }
    }
}
