package net.jitl.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.gui.overlay.EssenceBar;
import net.jitl.core.init.JITL;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class OverlayRegistry {

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {

        event.registerAboveAll("essence_bar", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            EssenceBar.render(mStack, screenHeight, screenWidth);
        });
    }
}
