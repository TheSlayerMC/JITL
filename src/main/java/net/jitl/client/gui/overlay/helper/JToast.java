package net.jitl.client.gui.overlay.helper;

import net.jitl.core.init.JITL;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public interface JToast extends Toast {

    ResourceLocation TEXTURE = JITL.rl("textures/gui/toasts.png");
    Object NO_TOKEN = new Object();

    @Override
    default @NotNull Object getToken() {
        return NO_TOKEN;
    }

    @Override
    default int width() {
        return 160;
    }

    @Override
    default int height() {
        return 32;
    }

    @Override
    void render(@NotNull GuiGraphics poseStack, @NotNull Font var2, long timeSinceLastVisible);
}