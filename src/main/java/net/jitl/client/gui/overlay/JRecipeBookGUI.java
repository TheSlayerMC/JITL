package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.util.GuiHelper;
import net.jitl.core.helper.EnumSummoningRecipes;
import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JRecipeBookGUI extends AbstractContainerScreen<EmptyContainer> {

    private PageButton nextButton;
    private PageButton previousButton;
    private final ResourceLocation RECIPE = JITL.rl("textures/gui/summoning_table_recipe.png");
    private final ResourceLocation BACKGROUND = JITL.rl("textures/gui/recipe_book.png");
    public int pageNumber = 0;

    public JRecipeBookGUI(Inventory playerInventory) {
        super(new EmptyContainer(), playerInventory, Component.translatable(""));
        this.imageWidth = 242;
        this.imageHeight = 216;
    }

    @Override
    protected void init() {
        super.init();
        int w1 = (this.width - this.imageWidth) / 2;
        int h1 = (this.height - this.imageHeight) / 2;
        int xPos = w1 + 95;
        int yPos = h1 + 198;
        this.nextButton = this.addRenderableWidget(new PageButton(xPos + 28, yPos, true, (button) -> this.flipPage(true), true));
        this.previousButton = this.addRenderableWidget(new PageButton(xPos, yPos, false, (button) -> this.flipPage(false), true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        int maxPages = 5;
        this.nextButton.visible = true;
        this.previousButton.visible = true;
        this.nextButton.active = pageNumber != maxPages;
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
        assert minecraft != null;
        RenderSystem.setShaderTexture(0, minecraft.getTextureManager().getTexture(this.BACKGROUND).getTextureView());
        GuiHelper.drawTexture(poseStack, BACKGROUND, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        switch(pageNumber) {
            case 0 -> page1(poseStack, mouseX, mouseY);
            case 1 -> page2(poseStack, mouseX, mouseY);
            default -> {
            }
        }
    }

    public void page1(GuiGraphics stack, int mouseX, int mouseY) {
        int w = 120;
        int x = -3;
        int y = -3;

        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.OKOLOO);
        x += w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.WITHERING_BEAST);

        y = y + 65;
        x -= w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.CALCIA);
        x += w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.SOUL_WATCHER);

        y = y + 65;
        x -= w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.CORALLATOR);
        x += w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.BLAZIER);

    }

    public void page2(GuiGraphics stack, int mouseX, int mouseY) {
        int w = 120;
        int x = -3;
        int y = -3;

        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.THUNDERBIRD);
        x += w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.SCALE);

        y = y + 65;
        x -= w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.LOGGER);
        x += w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.SENTRY_KING);

        y = y + 65;
        x -= w;
        drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.TERRASTAR);
        x += w;
        // drawSprite(stack, mouseX, mouseY, x, y, EnumSummoningRecipes.BLAZIER);

    }

    public void drawSprite(GuiGraphics matrixStack, int mouseX, int mouseY, int x, int y, EnumSummoningRecipes recipe) {
        int k = (width - imageWidth) / 2 + 3;
        int l = (height - imageHeight) / 2 + 3;

        x = x + k + 10;
        y = y + l + 10;
        assert minecraft != null;
        RenderSystem.setShaderTexture(0, minecraft.getTextureManager().getTexture(this.RECIPE).getTextureView());
        GuiHelper.drawTexture(matrixStack, RECIPE, x - 5, y - 5, 0, 0, 112, 62, 256, 256);
        matrixStack.pose().pushMatrix();
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 0);
        y = y + 18;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 1);
        x = x + 18;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 3);
        x = x - 18;

        y = y + 18;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 2);

        x = x + 36;
        y = y - 36;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 4);
        y = y + 18;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 5);
        y = y + 18;
        renderItem(matrixStack, mouseX, mouseY, x, y, recipe, 6);

        y = y - 18;
        renderItem(matrixStack, mouseX, mouseY, x + 46, y, recipe, 7);
        matrixStack.pose().popMatrix();
    }

    public void renderItem(GuiGraphics matrixStack, int mouseX, int mouseY, int x, int y, EnumSummoningRecipes recipe, int index) {
        ItemStack item = new ItemStack(recipe.getItem(index));
        matrixStack.renderItem(item, x, y);

        if(isMouseOver(mouseX, mouseY, x, y)) {
            renderTooltip(matrixStack, mouseX, mouseY, item);
        }
    }

    protected void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY, ItemStack item) {
        Component component = item.getStyledHoverName();
        ClientTooltipComponent clienttooltipcomponent = ClientTooltipComponent.create(component.getVisualOrderText());
        pGuiGraphics.renderTooltip(this.font, List.of(clienttooltipcomponent), pX, pY, DefaultTooltipPositioner.INSTANCE, item.get(DataComponents.TOOLTIP_STYLE));
    }

    protected boolean isMouseOver(int mouseX, int mouseY, int spriteX, int spriteY) {
        if(mouseX >= spriteX && mouseX <= spriteX + 16 && mouseY >= spriteY){
            return mouseY <= spriteY + 16;
        }
        return false;
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
