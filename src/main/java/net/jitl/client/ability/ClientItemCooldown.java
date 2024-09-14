package net.jitl.client.ability;

import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;

public class ClientItemCooldown {

    public static void setCooldown(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.ITEM_COOLDOWN).setCooldown(value);
    }

    public static int getCooldown() {
        return Minecraft.getInstance().player.getData(JDataAttachments.ITEM_COOLDOWN).getCooldown();
    }
}
