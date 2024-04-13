package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.gui.overlay.helper.JDisplayInfo;
import net.jitl.client.gui.overlay.helper.JFrameType;
import net.jitl.client.gui.overlay.helper.JToast;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.core.helper.internal.ArgbColor;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class KnowledgeToast implements JToast {

    private final EnumKnowledge knowledge;
    private boolean playedSound;
    private final boolean isLevel;
    private final ResourceLocation KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");


    public KnowledgeToast(EnumKnowledge knowledge, boolean isLevel) {
        this.knowledge = knowledge;
        this.isLevel = isLevel;
    }

    @Override
    public @NotNull JToast.Visibility render(GuiGraphics poseStack, ToastComponent toastComponent, long timeSinceLastVisible) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        JDisplayInfo displayinfo = isLevel ? this.knowledge.getLevelDisplay() : this.knowledge.getXPDisplay();
        poseStack.blit(TEXTURE, 0, 0, 0, 0, this.width(), this.height());
        if(displayinfo != null) {
            List<FormattedCharSequence> list = toastComponent.getMinecraft().font.split(displayinfo.getDescription(), 125);
            int i = displayinfo.getFrame() == JFrameType.LEVEL ? ArgbColor.from(ChatFormatting.DARK_PURPLE) : ArgbColor.from(ChatFormatting.BLACK);
            if(list.size() == 1) {
                poseStack.drawString(toastComponent.getMinecraft().font, displayinfo.getFrame().getDisplayName(), 30, 18, i, false);//Level or XP
                poseStack.drawString(toastComponent.getMinecraft().font, list.get(0), 30, 7, ArgbColor.from(ChatFormatting.BLACK), false);// Knowledge name
            } else {
                if(timeSinceLastVisible < 1500L) {
                    int k = Mth.floor(Mth.clamp((float)(1500L - timeSinceLastVisible) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                    poseStack.drawString(toastComponent.getMinecraft().font, displayinfo.getFrame().getDisplayName(), 30, 11, i | k, false);
                } else {
                    int i1 = Mth.floor(Mth.clamp((float)(timeSinceLastVisible - 1500L) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                    int l = this.height() / 2 - list.size() * 9 / 2;

                    for(FormattedCharSequence formattedcharsequence : list) {
                        poseStack.drawString(toastComponent.getMinecraft().font, formattedcharsequence, 30, l, 16777215 | i1, false);
                        l += 9;
                    }
                }
            }
            if (!this.playedSound && timeSinceLastVisible > 0L) {
                this.playedSound = true;
                if (displayinfo.getFrame() == JFrameType.LEVEL) {
                    toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST_SPECIAL.get(), 1.0F, 1.0F));
                }
                if (displayinfo.getFrame() == JFrameType.XP) {
                    toastComponent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(JSounds.TOAST.get(), 1.0F, 1.0F));
                }
            }
            float scale = 0.8F;
            double translate = 2.5D;
            poseStack.pose().pushPose();
            poseStack.pose().scale(scale, scale, scale);
            poseStack.pose().translate(translate, translate, translate);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, KNOWLEDGE_SPRITE);
            poseStack.blit(KNOWLEDGE_SPRITE, 1, 1, knowledge.getSpriteX(), knowledge.getSpriteY(), 32, 32);

            poseStack.pose().popPose();
            return timeSinceLastVisible >= 5000L ? JToast.Visibility.HIDE : JToast.Visibility.SHOW;
        } else {
            return JToast.Visibility.HIDE;
        }
    }
}