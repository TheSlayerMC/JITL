package net.jitl.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class BossBarRenderer {
    protected static LivingEntity boss;
    protected static ResourceLocation texture;
    protected static long startTime;

    public BossBarRenderer(LivingEntity entity, ResourceLocation tex) {
        boss = entity;
        texture = tex;
        startTime = Util.getNanos();
    }

    public static void render(ForgeGui gui, PoseStack stack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft minecraft = Minecraft.getInstance();
        int x = screenWidth + 91;
        int y = screenHeight;

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

        GuiComponent.blit(stack, x - timeWidth / 2, y, timeWidth, 9, 0, 10, 182, 9, 182, 19);
        GuiComponent.blit(stack, x - timeWidth / 2, y, (int) (timeWidth * healthWidth), 9, 0, 0, (int) (182 * healthWidth), 9, 182, 19);

        if (time > 1) {
            //minecraft.gui.drawCenteredString(stack, minecraft.font, boss.getName(), x, y - 9, 255, (int) (255 * nonRed), (int) (255 * nonRed), (int) Math.min(255, (time - 1) * 255));
        }
    }
}