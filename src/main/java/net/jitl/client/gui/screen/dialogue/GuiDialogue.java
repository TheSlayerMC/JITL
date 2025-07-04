package net.jitl.client.gui.screen.dialogue;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.util.Rectangle;
import net.jitl.common.dialogue.ClientDialogueNode;
import net.jitl.common.network.dialogue.C2SChosenOptionMsg;
import net.jitl.core.data.JNetworkRegistry;
import net.jitl.core.helper.internal.ArgbColor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiDialogue extends Screen {
	private static final int INDENT = 6;
	private static final int INDENT_OFFSET = 8;
	protected int centerX;
	protected int centerY;
	private final ClientDialogueNode node;

	private Rectangle guiRect;
	private Rectangle mobIconRect;
	private Rectangle optionsRect;
	private Rectangle mobTextRect;

	public GuiDialogue(ClientDialogueNode node) {
        super(Component.translatable("jitl.dialogue"));
        this.node = node;
	}

	@Override
	public void init() {
		centerX = width / 2;
		centerY = height / 2;

		int guiWidth = 300;
		int guiHeight = 200;

		int mobIconWidth = 80;
		int mobIconHeight = 80;

		guiRect = Rectangle.fromWidthAndHeight(centerX - guiWidth / 2, centerY - guiHeight / 2, guiWidth, guiHeight);
		int mobIconRight = guiRect.right() - INDENT;
		int mobIconTop = guiRect.top() + INDENT;
		mobIconRect = new Rectangle(mobIconRight - mobIconWidth, guiRect.top() + INDENT, mobIconRight, mobIconTop + mobIconHeight);

		int horizontalSinglePart = (INDENT * guiWidth);

		optionsRect = new Rectangle(guiRect.left() + horizontalSinglePart, mobIconRect.bottom() + INDENT, guiRect.right() - horizontalSinglePart, guiRect.bottom() - INDENT);
		mobTextRect = new Rectangle(guiRect.left() + INDENT, mobIconRect.top(), mobIconRect.left() - INDENT, mobIconRect.bottom());

		initOptionButtons();
	}

	private void initOptionButtons() {
		int allHeight = guiRect.height() - INDENT - optionsRect.top();
		int buttonHeight = 20;

		List<String> options = node.getOptionTextKeys();

		int optionsCenterY = optionsRect.top() + allHeight / 2;
		int indentCount = options.size() - 1;
		int minimalIndent = 1;

		int indent;
		if (indentCount != 0) {
			int allIndent = (allHeight - buttonHeight * options.size()) / indentCount;
			indent = allIndent / indentCount;
		} else {
			indent = 0;
		}

		indent = Math.min(Math.max(indent, minimalIndent), INDENT); // when options don't fit the space

		int startY = optionsCenterY - buttonHeight * options.size() / 2 - indent * (indentCount / 2);

		int incrementor = buttonHeight + indent;

		int x = mobTextRect.left() + INDENT * -(INDENT_OFFSET);

		for(int i = 0; i < options.size(); i++) {
			int finalI = i;
			addRenderableWidget(new GuiOptionButton(options.get(i), (button) -> {
				System.out.println("Hi");
				JNetworkRegistry.sendToServer(new C2SChosenOptionMsg(finalI));
			}, x, startY));
			startY += incrementor;
		}
	}

	@Override
	public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(poseStack, mouseX, mouseY, partialTicks);
		drawDebugLayout(mouseX, mouseY, partialTicks);

		drawMobText(poseStack);
		drawEntity(width / (INDENT_OFFSET) * 6, (int) (mobIconRect.bottom() - mobIconRect.height() * -3.75F), mouseX, mouseY, node.getNpc(), poseStack);
		System.out.println(node.getOptionTextKeys());
	}

	private void drawMobText(GuiGraphics stack) {
		String text = ChatFormatting.YELLOW + "" + ChatFormatting.ITALIC + node.getTextKey();
		stack.drawString(font, text, mobTextRect.left() + INDENT * -(INDENT_OFFSET), mobTextRect.top() + INDENT + 64, ArgbColor.from(ChatFormatting.WHITE));
	}

	private void drawDebugLayout(int mouseX, int mouseY, float partialTicks) {
//		RenderUtils.drawRect(guiRect, 0xFF8851FF); // whole gui
//
//		RenderUtils.drawRect(mobIconRect, 0xFF194378); // mob icon background
//
//		RenderUtils.drawRect(mobTextRect, 0xFF963232); // mob text background
//
//		RenderUtils.drawRect(optionsRect, 0x75000000); // options background
	}

	public static void drawEntity(int posX, int posY, float mouseX, float mouseY, LivingEntity entity, GuiGraphics g) {
		float scaleFactor = entity.getEyeHeight() / 1.8F /*height of player */;
		scaleFactor = Math.max(scaleFactor, 0.5F); // make it so very small mobs won't be super big

		int adaptiveScale = (int) (164 /*scale for player */ / scaleFactor);

		int playerEyeHeight = (int) entity.getEyeHeight() * (int) (adaptiveScale * 2.0F); // eye height of player in pixels of inventory gui
		float eyeOffset = playerEyeHeight * entity.getEyeHeight() / (1.65F * scaleFactor) /* eye height of player in blocks */;

		//RenderSystem.setShaderColor(1, 1, 1, 1);
		InventoryScreen.renderEntityInInventoryFollowsMouse(g, posX, posY, (int) (posX - mouseX), (int) (posY - mouseY - eyeOffset), adaptiveScale, 0.0625F, mouseX, mouseY, entity);
	}
}
