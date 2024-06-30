package net.jitl.client.ability;

import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;

public class ClientCelestiumArmor {

    public static void setJumpReady(boolean value) {
        Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).setJumpReady(value);
    }

    public static void setCooldown(int value) {
        Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).setCooldown(value);
    }

    public static boolean getJumpReady() {
        return Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).getJumpReady();
    }

    public static int getCooldown() {
        return Minecraft.getInstance().player.getData(JDataAttachments.CELESTIUM_ARMOR).getCooldown();
    }
}
