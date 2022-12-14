package net.jitl.client.gui.overlay.helper;

import net.minecraft.network.chat.Component;

public enum JFrameType {

    LEVEL("level", 26),
    XP("experience", 52);

    private final String name;
    private final int texture;
    private final Component displayName;

    JFrameType(String name, int texture) {
        this.name = name;
        this.texture = texture;
        this.displayName = Component.translatable("jitl.knowledge." + name);
    }

    public String getName() {
        return this.name;
    }

    public int getTexture() {
        return this.texture;
    }

    public Component getDisplayName() {
        return this.displayName;
    }
}