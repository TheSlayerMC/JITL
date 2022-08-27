package net.jitl.client.essence;

public class ClientEssence {

    private static int essence;

    public static void setClientEssence(int value) {
        ClientEssence.essence = value;
    }

    public static int getEssence() {
        return essence;
    }

}
