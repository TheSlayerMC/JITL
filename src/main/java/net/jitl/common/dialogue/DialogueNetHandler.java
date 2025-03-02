package net.jitl.common.dialogue;

import net.jitl.client.gui.screen.dialogue.GuiDialogue;
import net.jitl.common.JManagers;
import net.jitl.common.network.dialogue.C2SChosenOptionMsg;
import net.jitl.common.network.dialogue.S2CCloseDialogueGuiMsg;
import net.jitl.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public class DialogueNetHandler {

    @OnlyIn(Dist.CLIENT)
    public void handleDialogueClosePacket(S2CCloseDialogueGuiMsg message, IPayloadContext ctx) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.screen instanceof GuiDialogue) {
            mc.setScreen(null);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleDialogueOpenPacket(S2COpenDialogueGuiMsg message, IPayloadContext ctx) {
<<<<<<< HEAD
        List<String> opt = new ArrayList<>();
        opt.add(String.valueOf(message.options()));
        int size = opt.size();
        List<String> optionKeys = new ArrayList<>();
=======
        int size = message.options().size();
        List<String> optionKeys = new ArrayList<>(size);
>>>>>>> parent of 363d0355 (dialogue work)
        for(int i = 0; i < size; i++)
            optionKeys.add(message.options().toString());
        Minecraft.getInstance().setScreen(new GuiDialogue(new ClientDialogueNode(message.npcKey(), message.textKey(), optionKeys)));
    }

    public void handlePressOptionPacket(C2SChosenOptionMsg message, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            try {
                JManagers.DIALOGUE_MANAGER.handleDialogueChosenOption((ServerPlayer)ctx.player(), message.optionIndex());
            } catch (DialogueSystemException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
