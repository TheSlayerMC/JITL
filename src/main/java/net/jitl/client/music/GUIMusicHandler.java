package net.jitl.client.music;

import net.jitl.core.config.JClientConfig;
import net.jitl.core.helper.JMusic;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GUIMusicHandler {

    @SubscribeEvent()
    public static void guiMusicTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null && JClientConfig.ENABLE_JITL_MENU_SCREEN.get()) {
            JMusicTicker.addTrack(new JMusic(JSounds.MENU_MUSIC.get(), 10, 0, 0));
        }
    }
}
