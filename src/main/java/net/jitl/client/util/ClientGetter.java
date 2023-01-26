package net.jitl.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientGetter {

    public static Player player() {
        return safeCheck(Minecraft.getInstance().player);
    }

    public static Level level() {
        return safeCheck(Minecraft.getInstance().level);
    }

    public static <R, T extends R>R safeCheck(T object) {
        return object;
    }
}
