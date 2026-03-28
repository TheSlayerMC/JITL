package net.jitl.client.gui.screen;

import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.client.util.GuiHelper;
import net.jitl.common.entity.jmerchant.SentacoinMerchantMenu;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.network.PacketBuyItem;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class SentacoinMerchantScreen extends AbstractContainerScreen<SentacoinMerchantMenu> {

    private static final Identifier MERCHANT_LOCATION = JITL.rl("textures/gui/merchant.png");
    private static final Component TRADES_LABEL = Component.translatable("merchant.trades");

    public SentacoinMerchantScreen(SentacoinMerchantMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 220;
        this.imageHeight = 220;
    }

    protected void init() {
        super.init();
        addButton(0, 86, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_BARS.get()), 16, 64);
        addButton(0, 107, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_BRICKS.get()), 16, 64);
        addButton(0, 128, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_BRICK_STAIRS.get()), 16, 64);
        addButton(0, 149, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_CARVED_ROCK.get()), 16, 64);
        addButton(0, 170, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_FLOOR.get()), 16, 64);
        addButton(0, 191, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_ROCK.get()), 16, 64);
        addButton(0, 212, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_GLASS.get()), 16, 64);
        addButton(0, 233, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_POST.get()), 16, 64);
        addButton(0, 254, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_GUARDIAN_LAMP.get()), 16, 64);
        addButton(104, 86, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_LIGHT_LAMP.get()), 16, 64);
        addButton(104, 107, new ItemStack(JBlocks.BREAKABLE_SENTERIAN_MELLOW_LAMP.get()), 16, 64);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY) {
        Component component = Component.literal("Sentacoins: " + ClientPlayerStats.getSentacoins());
        int j = this.font.width(component);
        int k = 49 + this.imageWidth / 2 - j / 2;
        GuiHelper.drawString(pGuiGraphicsExtractor, component, k, 6, -12566464, false);
        int l = this.font.width(TRADES_LABEL);
        GuiHelper.drawString(pGuiGraphicsExtractor, TRADES_LABEL, 5 - l / 2 + 53, 6, -12566464, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, float pPartialTick) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphicsExtractor.pose().pushMatrix();
        GuiHelper.drawTexture(pGuiGraphicsExtractor, MERCHANT_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        pGuiGraphicsExtractor.pose().pushMatrix();
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, float pPartialTick) {
        super.extractRenderState(pGuiGraphicsExtractor, pMouseX, pMouseY, pPartialTick);
    }

    public void addButton(int x, int y, ItemStack item, int amount, int cost) {
        Button b = addRenderableWidget(new TradeOfferButton(item.getItem().getDescriptionId(), this.width / 2 - 100 + x, this.height / 2 - 175 + y, item, amount, cost));
        b.visible = true;
        b.active = true;
    }

    public void buy(ItemStack item, int amount, int cost) {
        JNetworkRegistry.sendToServer(new PacketBuyItem(item.getItem() + "", amount, cost));
    }

    public class TradeOfferButton extends Button {

        public TradeOfferButton(String name, int pX, int pY, ItemStack i, int amount, int cost) {
            super(pX, pY, 96, 20, Component.translatable(name), (press) -> buy(i, amount, cost), DEFAULT_NARRATION);
            setTooltip(Tooltip.create(Component.literal(" x" + amount + " for x"  + cost + " Sentacoins")));
        }

        @Override
        protected void extractContents(GuiGraphicsExtractor guiGraphicsExtractor, int i, int i1, float v) {

        }
    }
}
