package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.core.helper.internal.ArgbColor;
import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PlayerStats extends AbstractContainerScreen<EmptyContainer> {

    private PageButton nextButton;
    private PageButton previousButton;
    private final ResourceLocation KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private final ResourceLocation BACKGROUND = JITL.rl("textures/gui/stats.png");
    public int pageNumber = 0;
    public Player player;

    public PlayerStats(Player player) {
        super(new EmptyContainer(), player.getInventory(), Component.translatable("jitl.stats"));
        this.imageWidth = 242;
        this.imageHeight = 197;
        this.player = player;
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
       // this.renderBackground(graphics, pMouseX, pMouseY, pPartialTick);//Dims around the GUI for a more vanilla look
        this.updateButtonVisibility();
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
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        poseStack.pose().pushPose();
        //RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);
        poseStack.blitSprite(RenderType::guiTextured, BACKGROUND, x, y, this.imageWidth, this.imageHeight);//Draws the main Background

        switch(pageNumber) {
            case 0 -> page1(poseStack);
            case 1 -> page2(poseStack);
            default -> {
            }
        }
        poseStack.pose().popPose();
        RenderSystem.enableDepthTest();
    }

    public void page1(GuiGraphics stack) {
        int height = 43;
        int x = 9;
        int h = 9;

        drawKnowledgeSprite(stack, x, h, EnumKnowledge.OVERWORLD, "Overworld");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.NETHER, "The Nether");

        h += height;

        drawKnowledgeSprite(stack, x, h, EnumKnowledge.END, "End");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.BOIL, "Boiling Point");

        h += height;
        drawKnowledgeSprite(stack, x, h, EnumKnowledge.FROZEN, "Frozen Lands");
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.EUCA, "Euca");

        h += height;
        drawKnowledgeSprite(stack, 126, h, EnumKnowledge.DEPTHS, "The Depths");
        drawKnowledgeSprite(stack, x, h,  EnumKnowledge.CORBA, "Corba");
    }

    public void page2(GuiGraphics stack) {
        int height = 43;
        int x = 9;
        int h = 9;

        drawKnowledgeSprite(stack, x, h,  EnumKnowledge.TERRANIA, "Terrania");
        drawKnowledgeSprite(stack, 126, h,  EnumKnowledge.CLOUDIA, "Cloudia");

        h += height;

        drawKnowledgeSprite(stack, x, h,  EnumKnowledge.SENTERIAN, "Senterain");
        drawSprite(stack, 126, h, 0, 74, "Sentacoins:");

    }

    public void drawSprite(GuiGraphics matrixStack, int x, int y, int spriteX, int spriteY, String s) {
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
       // RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);

        //matrixStack.blitSprite(RenderType::guiTextured, BACKGROUND, k + x - 4, l + y - 4, 0, 216, 115, 40);//Draws the rectangle bg for the sprites

        //RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);

        //matrixStack.blit(RenderType::guiTextured, KNOWLEDGE_SPRITE, k + x, l + y, spriteX, spriteY, 32, 32); //Draws the knowledge sprite
        matrixStack.drawString(font, s, k + x + 35, l + y + 5, 4210752, false); //Draws the sprite name

        if(s.contains("Sentacoins"))
            matrixStack.drawString(font, "" + ClientPlayerStats.getSentacoins(), k + x + 35, l + y + 15, ArgbColor.from(ChatFormatting.WHITE));

        RenderSystem.enableDepthTest();
    }

    public void drawKnowledgeSprite(GuiGraphics matrixStack, int x, int y, EnumKnowledge type, String s) {
        drawSprite(matrixStack, x, y, type.getSpriteX(), type.getSpriteY(), s);
        int progressBarSize = 65;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        //TODO RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);
        if(player != null) {
            net.jitl.common.capability.stats.PlayerStats knowledge = player.getData(JDataAttachments.PLAYER_STATS);
            boolean completed = knowledge.isCompleted(type);
                float percents = knowledge.getXP(type) / knowledge.getLevelCapacity(knowledge.getLevel(type));
                int width = (int) (percents * progressBarSize);

                int progressBarX = k + x + 35, progressBarY = l + y + 19;
                //matrixStack.blit(KNOWLEDGE_SPRITE, progressBarX, progressBarY, 0, 5, progressBarSize, 5);
                //matrixStack.blit(KNOWLEDGE_SPRITE, progressBarX, progressBarY, 0, 0, width, 5);

                if(completed) {
                //    matrixStack.blit(KNOWLEDGE_SPRITE, k + x, l + y + 3, 130, 43, 32, 29);
                }

                int lvX = progressBarX + 29, lvY = progressBarY - 1;

                int getLevelCount = knowledge.getLevel(type);
                String level = "" + getLevelCount;

                matrixStack.drawString(font, "" + (getLevelCount), lvX - this.font.width(level) / 2 + 4, lvY, ArgbColor.from(ChatFormatting.WHITE), true);
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.nextButton.active = pageNumber < 1;
        this.previousButton.active = pageNumber > 0;
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics matrixStack, int x, int y) {

    }
}
