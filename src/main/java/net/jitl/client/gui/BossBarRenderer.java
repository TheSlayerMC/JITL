package net.jitl.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;

public class BossBarRenderer {
    protected LivingEntity boss;
    protected ResourceLocation texture;
    protected long startTime;

    public BossBarRenderer(LivingEntity entity, ResourceLocation tex) {
        this.boss = entity;
        this.texture = tex;
        this.startTime = Util.getNanos();
    }

    public void render(CustomizeGuiOverlayEvent.BossEventProgress event) {
        Minecraft minecraft = Minecraft.getInstance();
        GuiGraphics graphics = event.getGuiGraphics();
        int x = event.getX() + 91;
        int y = event.getY();

        float health = boss.getHealth() / boss.getMaxHealth();
        double time = (Util.getNanos() - startTime) / 1000000000D;

        int timeWidth = (int) (182 * Math.min(time, 1));
        double healthWidth = boss.getHealth() / boss.getMaxHealth();
        float nonRed;

        if (health < 1F / 3F) {
            nonRed = 1 - 3 * health; //find health missing relative to a third of max
            nonRed = nonRed * ((float) Math.sin(time * 2) / 2 + 0.5F); //sin function for color
            nonRed = 1 - nonRed; //decrease all other colors by this amount

        } else {
            nonRed = 1.0F;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0F, nonRed, nonRed, 1.0F);

        graphics.blit(texture, x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        graphics.blit(texture, x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth), 9, 182, 19);

        if(time > 1) {
            drawCenteredString(graphics, minecraft.font, boss.getName(), x, y - 9, 255, (int) (255 * nonRed), (int) (255 * nonRed), (int) Math.min(255, (time - 1) * 255));
        }
    }

    public static void drawCenteredString(GuiGraphics matrixStack, Font fontRenderer, Component fontIn, float x, float y, int red, int green, int blue, int alpha) {
        FormattedCharSequence ireorderingprocessor = fontIn.getVisualOrderText();
        int color = Math.max(4, alpha) << 24 | red << 16 | green << 8 | blue;
        matrixStack.drawString(fontRenderer, ireorderingprocessor, x - fontRenderer.width(ireorderingprocessor) / 2F, y, color, true);
    }
}