package net.jitl.client.gui.overlay;

import net.jitl.common.capability.player.Portal;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.gui.GuiLayer;
import org.jetbrains.annotations.NotNull;

public class PortalOverlayRenderer implements GuiLayer {

    @Override
    public void render(@NotNull GuiGraphicsExtractor pGuiGraphicsExtractor, @NotNull DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        Player player = minecraft.player;
        if (player != null) {
            Portal playerPortalOverlay = player.getData(JDataAttachments.PORTAL_OVERLAY);
            float timeInPortal = playerPortalOverlay.getOldPortalOverlayTime() * 1.45F + (playerPortalOverlay.getPortalOverlayTime() - playerPortalOverlay.getOldPortalOverlayTime()) * deltaTracker.getGameTimeDeltaTicks();
            if(timeInPortal > 0.0F) {
                if (timeInPortal < 1.0F) {
                    timeInPortal *= timeInPortal;
                    timeInPortal *= timeInPortal;
                    timeInPortal = timeInPortal * 0.8F + 0.2F;
                }

                //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);todo
//                pGuiGraphicsExtractor.blitSprite(RenderPipelines.GUI_TEXTURED, minecraft.blockRe().getBlockModelShaper().getParticleIcon(playerPortalOverlay.getPortalBlockToRender().defaultBlockState(), EmptyBlockAndTintGetter.INSTANCE, BlockPos.ZERO), 0, 0, pGuiGraphicsExtractor.guiWidth(), pGuiGraphicsExtractor.guiHeight());
//                pGuiGraphicsExtractor.drawCenteredString(minecraft.font, Component.translatable("multiplayer.downloadingTerrain"), screenWidth / 2, screenHeight / 2 - 50, -1);

            }
        }
    }
}
