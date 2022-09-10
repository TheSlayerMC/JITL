package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.client.knowledge.ClientKnowledge;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.knowledge.PlayerKnowledgeProvider;
import net.jitl.core.helper.internal.ArgbColor;
import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.jetbrains.annotations.NotNull;

public class PlayerStats extends AbstractContainerScreen<EmptyContainer> {

    private PageButton nextButton;
    private PageButton previousButton;
    private final ResourceLocation KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private final ResourceLocation BACKGROUND = JITL.rl("textures/gui/stats.png");
    public int pageNumber = 0;

    public PlayerStats(Inventory playerInventory) {
        super(new EmptyContainer(), playerInventory, Component.translatable("jitl.stats"));
        this.imageWidth = 242;
        this.imageHeight = 197;
    }

    @Override
    protected void init() {
        super.init();
        int w = (this.width - this.imageWidth) / 2;
        int h = (this.height - this.imageHeight) / 2;
        int xPos = w + 95;
        int yPos = h + 177;
        this.nextButton = this.addRenderableWidget(new PageButton(xPos + 32, yPos, true, (button) -> {
            this.flipPage(true);
        }, true));
        this.previousButton = this.addRenderableWidget(new PageButton(xPos, yPos, false, (button) -> {
            this.flipPage(false);
        }, true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.nextButton.visible = true;
        this.previousButton.visible = true;
        this.nextButton.active = pageNumber != 1;
        this.previousButton.active = pageNumber != 0;
    }

    protected void flipPage(boolean forward) {
        if(forward) this.pageNumber++;
        else this.pageNumber--;
        this.updateButtonVisibility();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);//Dims around the GUI for a more vanilla look
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        poseStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);
        blit(poseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);//Draws the main Background

        switch(pageNumber) {
            case 0 -> page1(poseStack);
            case 1 -> page2(poseStack);
            default -> {
            }
        }
        poseStack.popPose();
        RenderSystem.enableDepthTest();
    }

    public void page1(PoseStack stack) {
        int height = 43;
        int x = 9;
        int h = 9;
        drawSprite(stack, x, h, 0, 74, "Sentacoins:");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.OVERWORLD, "Overworld");

        h += height;

        drawKnowledgeSprite(stack, x, h, EnumKnowledge.END, "End");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.FROZEN, "Frozen Lands");

        h += height;
        drawKnowledgeSprite(stack, x, h, EnumKnowledge.NETHER, "The Nether");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.BOIL, "Boiling Point");

        h += height;
        drawKnowledgeSprite(stack, x, h, EnumKnowledge.EUCA, "Euca");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.DEPTHS, "The Depths");
    }

    public void drawSprite(PoseStack matrixStack, int x, int y, int spriteX, int spriteY, String s) {
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);

        blit(matrixStack, k + x - 4, l + y - 4, 0, 216, 115, 40);//Draws the yellow rectangle bg for the sprites
        matrixStack.popPose();

        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);

        blit(matrixStack, k + x, l + y, spriteX, spriteY, 32, 32); //Draws the knowledge sprite
        font.draw(matrixStack, s, k + x + 35, l + y + 5, 4210752); //Draws the sprite name

        //if(s.contains("Sentacoins"))
        //    font.draw(matrixStack, "" + jPlayer.sentacoins.getAmount(), k + x + 35, l + y + 15, 4210752);

        matrixStack.popPose();
        RenderSystem.enableDepthTest();
    }

    public void drawKnowledgeSprite(PoseStack matrixStack, int x, int y, EnumKnowledge type, String s) {
        drawSprite(matrixStack, x, y, type.getSpriteX(), type.getSpriteY(), s);
        int progressBarSize = 65;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);
        Player player = Minecraft.getInstance().player;
        if(player != null) {
            player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {

                float percents = ClientKnowledge.getClientKnowledgeXP(type) / knowledge.getLevelCapacity(knowledge.getLevel(type));
                int width = (int) (percents * progressBarSize);

                int progressBarX = k + x + 35, progressBarY = l + y + 19;
                blit(matrixStack, progressBarX, progressBarY, 0, 5, progressBarSize, 5);
                blit(matrixStack, progressBarX, progressBarY, 0, 0, width, 5);

                int lvX = progressBarX + 29, lvY = progressBarY - 1;

                int getLevelCount = ClientKnowledge.getClientKnowledgeLevel(type);
                String level = "" + getLevelCount;

                font.drawShadow(matrixStack, "" + (getLevelCount), lvX - this.font.width(level) / 2 + 4, lvY, ArgbColor.from(ChatFormatting.WHITE));
                matrixStack.popPose();
            });
        }
        RenderSystem.enableDepthTest();
    }

    public void page2(PoseStack stack) {
        int height = 43;
        int x = 9;
        int h = 9;

        drawKnowledgeSprite(stack, x, h,  EnumKnowledge.CORBA, "Corba");
        drawKnowledgeSprite(stack, 126, h,  EnumKnowledge.TERRANIA, "Terrania");

        h += height;

        drawKnowledgeSprite(stack, x, h,  EnumKnowledge.CLOUDIA, "Cloudia");
        drawKnowledgeSprite(stack, 126, h,  EnumKnowledge.SENTERIAN, "Senterain");
        //h += height;
        //drawKnowledgeSprite(x, h, 128, 10, 0.30F, E"SDHFSDH");
        //drawKnowledgeSprite(126, h, 160, 10, 0.75F, "Frozen Lands");
        //h += height - 2;
        //drawKnowledgeSprite(x, h, 192, 10, 0.10F, "Euca");
        //drawKnowledgeSprite(126, h, 224, 10, 0.60F, "The Depths");
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.nextButton.active = pageNumber < 1;
        this.previousButton.active = pageNumber > 0;
    }

    @Override
    protected void renderLabels(@NotNull PoseStack matrixStack, int x, int y) {

    }
}
