package net.jitl.core.helper;

public class DrawHelper {

    public static int getRed(int argb) {
        return argb >> 16 & 0xFF;
    }

    public static int getGreen(int argb) {
        return argb >> 8 & 0xFF;
    }

    public static int getBlue(int argb) {
        return argb & 0xFF;
    }

    public static int getAlpha(int argb) {
        return argb >> 24 & 0xFF;
    }
}