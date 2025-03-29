package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.common.capability.player.Portal;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PortalOverlayRenderer implements LayeredDraw.Layer {

    @Override
    public void render(GuiGraphics pGuiGraphics, DeltaTracker deltaTracker) {
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
//                RenderSystem.disableDepthTest();todo
//                RenderSystem.depthMask(false);
//                RenderSystem.enableBlend();
//                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
//                pGuiGraphics.blitSprite(RenderType::guiTextured, minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(playerPortalOverlay.getPortalBlockToRender().defaultBlockState()), 0, 0, pGuiGraphics.guiWidth(), pGuiGraphics.guiHeight());
//                pGuiGraphics.drawCenteredString(minecraft.font, Component.translatable("multiplayer.downloadingTerrain"), screenWidth / 2, screenHeight / 2 - 50, -1);
//                RenderSystem.disableBlend();
//                RenderSystem.depthMask(true);
//                RenderSystem.enableDepthTest();
            }
        }
    }
}
