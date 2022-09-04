package net.jitl.client.knowledge;

import net.jitl.client.gui.overlay.helper.JDisplayInfo;
import net.jitl.client.gui.overlay.helper.JFrameType;
import net.minecraft.network.chat.Component;

public class KnowledgeXPDisplay extends JDisplayInfo {

    public KnowledgeXPDisplay(String name, boolean isLevel) {
        super(Component.translatable("jitl.knowledge." + name.toLowerCase()), isLevel ? JFrameType.LEVEL : JFrameType.XP);
    }
}