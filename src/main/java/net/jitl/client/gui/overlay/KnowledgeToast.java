package net.jitl.client.gui.overlay;

import net.jitl.client.gui.overlay.helper.JDisplayInfo;
import net.jitl.client.gui.overlay.helper.JFrameType;
import net.jitl.client.gui.overlay.helper.JToast;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.helper.internal.ArgbColor;
import net.jitl.core.init.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KnowledgeToast implements JToast {

    private final EnumKnowledge knowledge;
    private boolean playedSound;
    private final boolean isLevel;
    private final Identifier KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private JToast.Visibility wantedVisibility = JToast.Visibility.HIDE;

    public KnowledgeToast(EnumKnowledge knowledge, boolean isLevel) {
        this.knowledge = knowledge;
        this.isLevel = isLevel;
    }

    @Override
    public @NotNull Visibility getWantedVisibility() {
        return wantedVisibility;
    }

    @Override
    public void update(ToastManager toastManager, long l) {

    }

    @Override
    public void render(GuiGraphics poseStack, Font font, long timeSinceLastVisible) {
        //RenderSystem.setShaderTexture(0, Minecraft.getInstance().getTextureManager().getTexture(TEXTURE).getTextureView());
        JDisplayInfo displayinfo = isLevel ? this.knowledge.getLevelDisplay() : this.knowledge.getXPDisplay();
        poseStack.blitSprite(RenderPipelines.GUI_TEXTURED, TEXTURE, 0, 0, this.width(), this.height());
        if(displayinfo != null) {
            List<FormattedCharSequence> list = font.split(displayinfo.getDescription(), 125);
            int i = displayinfo.getFrame() == JFrameType.LEVEL ? ArgbColor.from(ChatFormatting.DARK_PURPLE) : ARGB.colorFromFloat(1, 0, 0, 0);
            if(list.size() == 1) {
                poseStack.drawString(font, displayinfo.getFrame().getDisplayName(), 30, 18, i, false);//Level or XP
                poseStack.drawString(font, list.get(0), 30, 7, ArgbColor.from(ChatFormatting.BLACK), false);// Knowledge name
            } else {
                if(timeSinceLastVisible < 1500L) {
                    int k = Mth.floor(Mth.clamp((float)(1500L - timeSinceLastVisible) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                    poseStack.drawString(font, displayinfo.getFrame().getDisplayName(), 30, 11, i | k, false);
                } else {
                    int i1 = Mth.floor(Mth.clamp((float)(timeSinceLastVisible - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                    int l = this.height() / 2 - list.size() * 9 / 2;

                    for(FormattedCharSequence formattedcharsequence : list) {
                        poseStack.drawString(font, formattedcharsequence, 30, l, 16777215 | i1, false);
                        l += 9;
                    }
                }
            }
            if (!this.playedSound && timeSinceLastVisible > 0L) {
                this.playedSound = true;
                if (displayinfo.getFrame() == JFrameType.LEVEL) {
                    //toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST_SPECIAL.get(), 1.0F, 1.0F));
                }
                if (displayinfo.getFrame() == JFrameType.XP) {
                    //toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST.get(), 1.0F, 1.0F));
                }
            }
            float scale = 0.8F;
            float translate = 2.5F;
            poseStack.pose().pushMatrix();
            poseStack.pose().scale(scale, scale);
            poseStack.pose().translate(translate, translate);
            //RenderSystem.setShaderTexture(0, Minecraft.getInstance().getTextureManager().getTexture(KNOWLEDGE_SPRITE).getTextureView());
            poseStack.blitSprite(RenderPipelines.GUI_TEXTURED, KNOWLEDGE_SPRITE, knowledge.getSpriteX(), knowledge.getSpriteY(), 32, 32);

            poseStack.pose().popMatrix();
        }
    }
}