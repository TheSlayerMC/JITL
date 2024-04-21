package net.jitl.client.gui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.Map;

public class BossBarRenderer {

    private static final Map<ResourceLocation, ResourceLocation> BAR_ID_CACHE = new Object2ObjectOpenHashMap<>();

    public static void init() {
        NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, CustomizeGuiOverlayEvent.BossEventProgress.class, BossBarRenderer::render);
    }

    public static void render(final CustomizeGuiOverlayEvent.BossEventProgress ev) {
        Minecraft mc = Minecraft.getInstance();
        Window window = mc.getWindow();
        GuiGraphics graphics = ev.getGuiGraphics();
        int x = window.getGuiScaledWidth() / 2 - 100;
        int y = ev.getY();
        LerpingBossEvent bossStatusInfo = ev.getBossEvent();
        assert mc.level != null;
        LivingEntity entity = (LivingEntity)mc.level.getEntities().get(bossStatusInfo.getId());

        if(entity == null || ev.isCanceled() || Minecraft.getInstance().level == null || bossStatusInfo.getColor() != BossEvent.BossBarColor.PINK || bossStatusInfo.getOverlay() != BossEvent.BossBarOverlay.NOTCHED_20)
            return;

        ResourceLocation texture = BAR_ID_CACHE.computeIfAbsent(
                JITL.getRegistryName(entity.getType()), key -> new ResourceLocation(key.getNamespace(), "textures/gui/bossbars/" + key.getPath() + ".png"));

        double healthWidth = entity.getHealth() / entity.getMaxHealth();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(texture, x, y, 182, 9, 0, 10, 182, 9, 182, 19);
        graphics.blit(texture, x, y, (int)(182 * healthWidth), 9, 0, 0, (int)(182 * healthWidth), 9, 182, 19);

        ev.setIncrement(ev.getIncrement() + 5);
        ev.setCanceled(true);
    }
}