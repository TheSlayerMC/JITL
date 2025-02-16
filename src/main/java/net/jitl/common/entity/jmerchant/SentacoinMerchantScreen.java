package net.jitl.common.entity.jmerchant;

import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class SentacoinMerchantScreen extends AbstractContainerScreen<SentacoinMerchantMenu> {

    private static final ResourceLocation MERCHANT_LOCATION = JITL.rl("textures/gui/merchant.png");
    private static final Component TRADES_LABEL = Component.translatable("merchant.trades");
    private int shopItem;
    private final TradeOfferButton[] tradeOfferButtons = new TradeOfferButton[7];
    int scrollOff;
    private boolean isDragging;
    private final Player player;

    public SentacoinMerchantScreen(SentacoinMerchantMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 220;
        this.imageHeight = 220;
        this.player = pPlayerInventory.player;
    }

    protected void init() {
        super.init();

        addButton(0, 86, new ItemStack(JItems.AIRMELON.get()), 16, 32);
        addButton(0, 107, new ItemStack(JItems.BROWN_EUCA_BOAT.get()), 64, 64);
        addButton(0, 128, new ItemStack(JItems.DES_AXE.get()), 2, 128);
        addButton(0, 149, new ItemStack(JItems.CORVEGGIES.get()), 32, 256);
        addButton(0, 170, new ItemStack(JItems.DEPTHS_DARKSWORD.get()), 1, 512);
    }

    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Component component = Component.translatable(String.valueOf(ClientPlayerStats.getSentacoins()));
        int j = this.font.width(component);
        int k = 49 + this.imageWidth / 2 - j / 2;
        pGuiGraphics.drawString(this.font, component, k, 6, 4210752, false);
        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
        int l = this.font.width(TRADES_LABEL);
        pGuiGraphics.drawString(this.font, TRADES_LABEL, 5 - l / 2 + 48, 6, 4210752, false);
    }

    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(MERCHANT_LOCATION, i, j, 0, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 512, 256);
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

    public void addButton(int x, int y, ItemStack item, int amount, int cost) {
        Button b = addRenderableWidget(new TradeOfferButton(this.width / 2 - 100 + x, this.height / 2 - 175 + y, item, amount, cost));
        b.visible = true;
        b.active = true;
    }

    public void buy(ItemStack item, int amount, int cost) {

    }

    class TradeOfferButton extends Button {
        final ItemStack item;

        public TradeOfferButton(int pX, int pY, ItemStack i, int amount, int cost) {
            super(pX, pY, 96, 20, CommonComponents.EMPTY, (press) -> buy(i, amount, cost), DEFAULT_NARRATION);
            this.visible = false;
            this.item = i;
        }

        public void renderToolTip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
            if (this.isHovered) {
                ItemStack itemstack1;
                if (pMouseX < this.getX() + 20) {
                    itemstack1 = item;
                    pGuiGraphics.renderTooltip(SentacoinMerchantScreen.this.font, itemstack1, pMouseX, pMouseY);
                }
            }
        }
    }
}
