package net.jitl.client.essence;

import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.client.Minecraft;

public class ClientEssence {

    public static void setClientEssence(float value) {
        Minecraft.getInstance().player.getData(JDataAttachments.ESSENCE).setEssence(value);
    }

    public static void setClientBurnout(float value) {
        Minecraft.getInstance().player.getData(JDataAttachments.ESSENCE).setBurnout(value);
    }

    public static float getCurrentClientEssence() {
        return Minecraft.getInstance().player.getData(JDataAttachments.ESSENCE).getCurrentEssence();
    }

    public static float getMaxClientEssence() {
        return Minecraft.getInstance().player.getData(JDataAttachments.ESSENCE).getMaxEssence(Minecraft.getInstance().player);
    }

    public static float getClientEssenceBurnout() {
        return Minecraft.getInstance().player.getData(JDataAttachments.ESSENCE).getBurnout();
    }
}
