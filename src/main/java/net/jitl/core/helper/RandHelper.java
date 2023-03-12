package net.jitl.core.helper;

import net.minecraft.util.RandomSource;

public class RandHelper {
    public static final RandomSource RANDOM = RandomSource.create();

    @SafeVarargs
    public static <T> T chooseEqual(RandomSource r, T... items) {
        return items[r.nextInt(items.length)];
    }
}
