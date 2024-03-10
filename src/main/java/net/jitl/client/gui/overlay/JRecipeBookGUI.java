package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.core.helper.EnumSummoningRecipes;
import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class JRecipeBookGUI extends AbstractContainerScreen<EmptyContainer> {

    private PageButton nextButton;
    private PageButton previousButton;
    private final ResourceLocation RECIPE = JITL.rl("textures/gui/summoning_table_recipe.png");
    private final ResourceLocation BACKGROUND = JITL.rl("textures/gui/recipe_book.png");
    public int pageNumber = 0;

    public JRecipeBookGUI(Inventory playerInventory) {
        super(new EmptyContainer(), playerInventory, Component.translatable("jitl.recipe_book"));
        this.imageWidth = 242;
        this.imageHeight = 216;
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        this.renderBackground(graphics, pMouseX, pMouseY, pPartialTick);//Dims around the GUI for a more vanilla look
        this.updateButtonVisibility();
    }

    @Override
    protected void init() {
        super.init();
        int w = (this.width - this.imageWidth) / 2;
        int h = (this.height - this.imageHeight) / 2;
        int xPos = w + 95;
        int yPos = h + 200;
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
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);
        poseStack.blit(BACKGROUND, x, y, 0, 0, this.imageWidth, this.imageHeight);//Draws the main Background

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
        int w = 120;

        int x = -3;
        int y = -3;

        drawSprite(stack, x, y, EnumSummoningRecipes.OKOLOO);
        x += w;
        drawSprite(stack, x, y, EnumSummoningRecipes.w);

        y = y + 65;
        x -= w;
        drawSprite(stack, x, y, EnumSummoningRecipes.a);
        x += w;
        drawSprite(stack, x, y, EnumSummoningRecipes.s);

        y = y + 65;
        x -= w;
        drawSprite(stack, x, y, EnumSummoningRecipes.d);
        x += w;
        drawSprite(stack, x, y, EnumSummoningRecipes.f);
    }

    public void page2(GuiGraphics stack) {
        int height = 65;
        int x = 9;
        int h = 9;

    }

    public void drawSprite(GuiGraphics matrixStack, int x, int y, EnumSummoningRecipes recipe) {
        int k = (width - imageWidth) / 2 + 3;
        int l = (height - imageHeight) / 2 + 3;

        x = x + k + 10;
        y = y + l + 10;
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.RECIPE);
        matrixStack.blit(RECIPE, x - 5, y - 5,0, 0, 112, 62);


        matrixStack.renderItem(new ItemStack(recipe.getItems(0)), x, y);
        y = y + 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(1)), x, y);
        x = x + 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(3)), x, y);
        x = x - 18;

        y = y + 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(2)), x, y);

        x = x + 36;
        y = y - 36;
        matrixStack.renderItem(new ItemStack(recipe.getItems(4)), x, y);
        y = y + 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(5)), x, y);
        y = y + 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(6)), x, y);

        y = y - 18;
        matrixStack.renderItem(new ItemStack(recipe.getItems(7)), x + 46, y);

        RenderSystem.enableDepthTest();
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
