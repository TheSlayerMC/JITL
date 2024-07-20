package net.jitl.client.ability;

import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;

public class ClientCuriosCooldown {

    public static void setCooldown(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).setCooldown(value);
    }

    public static int getCooldown() {
        return Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).getCooldown();
    }
}
