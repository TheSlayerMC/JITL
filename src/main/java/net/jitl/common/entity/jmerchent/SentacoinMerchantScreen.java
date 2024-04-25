package net.jitl.common.entity.jmerchent;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.common.world.menu.SentacoinMerchantMenu;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.Objects;


public class SentacoinMerchantScreen extends AbstractContainerScreen<SentacoinMerchantMenu> {

    private static final ResourceLocation SCROLLER_SPRITE = new ResourceLocation("container/villager/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = new ResourceLocation("container/villager/scroller_disabled");
    private static final ResourceLocation TRADE_ARROW_SPRITE = new ResourceLocation("container/villager/trade_arrow");
    private static final ResourceLocation VILLAGER_LOCATION = new ResourceLocation("textures/gui/container/villager.png");
    private static final Component TRADES_LABEL = Component.translatable("merchant.trades");
    private int shopItem;
    private final TradeOfferButton[] tradeOfferButtons = new TradeOfferButton[7];
    int scrollOff;
    private boolean isDragging;

    public SentacoinMerchantScreen(SentacoinMerchantMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 276;
        this.inventoryLabelX = 107;
    }

    private void postButtonClick() {
        this.menu.setSelectionHint(this.shopItem);
        this.menu.tryMoveItems(this.shopItem);
        assert this.minecraft != null;
        Objects.requireNonNull(this.minecraft.getConnection()).send(new ServerboundSelectTradePacket(this.shopItem));
    }

    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int k = j + 16 + 2;

        for(int l = 0; l < 7; ++l) {
            this.tradeOfferButtons[l] = this.addRenderableWidget(new TradeOfferButton(i + 5, k, l, (p_99174_) -> {
                if (p_99174_ instanceof TradeOfferButton) {
                    this.shopItem = ((TradeOfferButton)p_99174_).getIndex() + this.scrollOff;
                    this.postButtonClick();
                }

            }));
            k += 20;
        }

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
        pGuiGraphics.blit(VILLAGER_LOCATION, i, j, 0, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 512, 256);
    }

    private void renderScroller(GuiGraphics pGuiGraphics, int pPosX, int pPosY, SentacoinMerchantOffers pSentacoinMerchantOffers) {
        int i = pSentacoinMerchantOffers.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int i1 = Math.min(113, this.scrollOff * k);
            if (this.scrollOff == i - 1) {
                i1 = 113;
            }
            pGuiGraphics.blitSprite(SCROLLER_SPRITE, pPosX + 94, pPosY + 18 + i1, 0, 6, 27);
        } else {
            pGuiGraphics.blitSprite(SCROLLER_DISABLED_SPRITE, pPosX + 94, pPosY + 18, 0, 6, 27);
        }

    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        SentacoinMerchantOffers SentacoinMerchantOffers = this.menu.getOffers();
        if (!SentacoinMerchantOffers.isEmpty()) {
            int i = (this.width - this.imageWidth) / 2;
            int j = (this.height - this.imageHeight) / 2;
            int k = j + 16 + 1;
            int l = i + 5 + 5;
            this.renderScroller(pGuiGraphics, i, j, SentacoinMerchantOffers);
            int i1 = 0;
            Iterator var11 = SentacoinMerchantOffers.iterator();

            while(true) {
                SentacoinMerchantOffer SentacoinMerchantOffer;
                while(var11.hasNext()) {
                    SentacoinMerchantOffer = (SentacoinMerchantOffer)var11.next();
                    if (this.canScroll(SentacoinMerchantOffers.size()) && (i1 < this.scrollOff || i1 >= 7 + this.scrollOff)) {
                        ++i1;
                    } else {
                        ItemStack itemstack = new ItemStack(JItems.SENTACOIN.asItem());
                        ItemStack itemstack3 = SentacoinMerchantOffer.getResult();
                        pGuiGraphics.pose().pushPose();
                        pGuiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
                        int j1 = k + 2;
                        this.renderAndDecorateCostA(pGuiGraphics, itemstack, SentacoinMerchantOffer.getSentacoinCost(), l, j1);
                        this.renderButtonArrows(pGuiGraphics, SentacoinMerchantOffer, i, j1);
                        pGuiGraphics.renderFakeItem(itemstack3, i + 5 + 68, j1);
                        pGuiGraphics.renderItemDecorations(this.font, itemstack3, i + 5 + 68, j1);
                        pGuiGraphics.pose().popPose();
                        k += 20;
                        ++i1;
                    }
                }

                TradeOfferButton[] var19 = this.tradeOfferButtons;
                int var20 = var19.length;

                for(int var21 = 0; var21 < var20; ++var21) {
                    TradeOfferButton merchantscreen$tradeofferbutton = var19[var21];
                    if (merchantscreen$tradeofferbutton.isHoveredOrFocused()) {
                        merchantscreen$tradeofferbutton.renderToolTip(pGuiGraphics, pMouseX, pMouseY);
                    }

                    merchantscreen$tradeofferbutton.visible = merchantscreen$tradeofferbutton.index < this.menu.getOffers().size();
                }

                RenderSystem.enableDepthTest();
                break;
            }
        }

        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private void renderButtonArrows(GuiGraphics pGuiGraphics, SentacoinMerchantOffer pSentacoinMerchantOffers, int pPosX, int pPosY) {
        RenderSystem.enableBlend();
        pGuiGraphics.blitSprite(TRADE_ARROW_SPRITE, pPosX + 5 + 35 + 20, pPosY + 3, 0, 10, 9);
    }

    private void renderAndDecorateCostA(GuiGraphics pGuiGraphics, ItemStack pRealCost, int amount, int pX, int pY) {
        pGuiGraphics.renderFakeItem(pRealCost, pX, pY);
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0.0F, 0.0F, 200.0F);
        pGuiGraphics.renderItemDecorations(this.font, pRealCost, pX, pY);
        String count = String.valueOf(amount);
        this.font.drawInBatch(count, (float)(pX) + 19.0F - 2.0F - (float)this.font.width(count), (float)pY + 6.0F + 3.0F, 16777215, true, pGuiGraphics.pose().last().pose(), pGuiGraphics.bufferSource(), DisplayMode.NORMAL, 0, 15728880, false);
        pGuiGraphics.pose().popPose();
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().popPose();
    }

    private boolean canScroll(int pNumOffers) {
        return pNumOffers > 7;
    }

    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        int i = this.menu.getOffers().size();
        if (this.canScroll(i)) {
            int j = i - 7;
            this.scrollOff = Mth.clamp((int)((double)this.scrollOff - pScrollY), 0, j);
        }

        return true;
    }

    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        int i = this.menu.getOffers().size();
        if (this.isDragging) {
            int j = this.topPos + 18;
            int k = j + 139;
            int l = i - 7;
            float f = ((float)pMouseY - (float)j - 13.5F) / ((float)(k - j) - 27.0F);
            f = f * (float)l + 0.5F;
            this.scrollOff = Mth.clamp((int)f, 0, l);
            return true;
        } else {
            return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        }
    }

    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        this.isDragging = false;
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        if (this.canScroll(this.menu.getOffers().size()) && pMouseX > (double)(i + 94) && pMouseX < (double)(i + 94 + 6) && pMouseY > (double)(j + 18) && pMouseY <= (double)(j + 18 + 139 + 1)) {
            this.isDragging = true;
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @OnlyIn(Dist.CLIENT)
    class TradeOfferButton extends Button {
        final int index;

        public TradeOfferButton(int pX, int pY, int pIndex, Button.OnPress pOnPress) {
            super(pX, pY, 88, 20, CommonComponents.EMPTY, pOnPress, DEFAULT_NARRATION);
            this.index = pIndex;
            this.visible = false;
        }

        public int getIndex() {
            return this.index;
        }

        public void renderToolTip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
            if (this.isHovered && SentacoinMerchantScreen.this.menu.getOffers().size() > this.index + SentacoinMerchantScreen.this.scrollOff) {
                ItemStack itemstack1;
                if (pMouseX < this.getX() + 20) {
                    itemstack1 = new ItemStack(JItems.SENTACOIN.get());
                    pGuiGraphics.renderTooltip(SentacoinMerchantScreen.this.font, itemstack1, pMouseX, pMouseY);
                } else if (pMouseX > this.getX() + 65) {
                    itemstack1 = SentacoinMerchantScreen.this.menu.getOffers().get(this.index + SentacoinMerchantScreen.this.scrollOff).getResult();
                    pGuiGraphics.renderTooltip(SentacoinMerchantScreen.this.font, itemstack1, pMouseX, pMouseY);
                }
            }
        }
    }
}
