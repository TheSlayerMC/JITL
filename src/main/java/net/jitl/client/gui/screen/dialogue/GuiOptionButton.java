package net.jitl.client.gui.screen.dialogue;

import net.jitl.client.util.EnumHexColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;

public class GuiOptionButton extends Button {

	public String displayString;

	public GuiOptionButton(String displayString, Button.OnPress press, int x, int y) {
		super(x, y, 276, 20, CommonComponents.EMPTY, press, DEFAULT_NARRATION);
		this.displayString = displayString;
	}

	@Override
	public void renderWidget(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float pPartialTick) {
		if (this.visible) {
			this.isHovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
			int j = EnumHexColor.WHITE.getInt();
			if (this.packedFGColor != 0) {
				j = this.packedFGColor;
			} else if (!this.active) {
				j = EnumHexColor.LIGHT_BLUE.getInt();
			} else if (this.isHovered) {
				j = EnumHexColor.YELLOW.getInt();
			}
			pGuiGraphics.drawString(Minecraft.getInstance().font, this.displayString, this.getX() + this.width / this.getX(), this.getY() + (this.height - 8) / 2, j);
		}
	}
}
