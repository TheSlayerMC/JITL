package net.jitl.client.gui.overlay.helper;

import net.minecraft.network.chat.MutableComponent;

public class JDisplayInfo {

    private final MutableComponent description;
    private final JFrameType frame;

    public JDisplayInfo(MutableComponent description, JFrameType frame) {
        this.description = description;
        this.frame = frame;
    }

    public MutableComponent getDescription() {
        return this.description;
    }

    public JFrameType getFrame() {
        return this.frame;
    }

}