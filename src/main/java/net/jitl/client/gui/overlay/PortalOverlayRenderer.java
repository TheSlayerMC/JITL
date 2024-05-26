package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.common.capability.player.Portal;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PortalOverlayRenderer implements LayeredDraw.Layer {

    @Override
    public void render(GuiGraphics pGuiGraphics, float pAlpha) {
        Minecraft minecraft = Minecraft.getInstance();
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        Player player = minecraft.player;
        if (player != null) {
            Portal playerPortalOverlay = player.getData(JDataAttachments.PORTAL_OVERLAY);
            float timeInPortal = playerPortalOverlay.getOldPortalOverlayTime() * 1.45F + (playerPortalOverlay.getPortalOverlayTime() - playerPortalOverlay.getOldPortalOverlayTime()) * pAlpha;
            if(timeInPortal > 0.0F) {
                if (timeInPortal < 1.0F) {
                    timeInPortal *= timeInPortal;
                    timeInPortal *= timeInPortal;
                    timeInPortal = timeInPortal * 0.8F + 0.2F;
                }
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, timeInPortal);
                TextureAtlasSprite textureatlassprite = Minecraft.getInstance()
                        .getBlockRenderer()
                        .getBlockModelShaper()
                        .getParticleIcon(playerPortalOverlay.getPortalBlockToRender().defaultBlockState());
                pGuiGraphics.blit(0, 0, -90, pGuiGraphics.guiWidth(), pGuiGraphics.guiHeight(), textureatlassprite);
                RenderSystem.disableBlend();
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
