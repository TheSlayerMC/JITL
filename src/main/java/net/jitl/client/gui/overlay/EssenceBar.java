package net.jitl.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.essence.PlayerEssenceProvider;
import net.jitl.client.utils.RenderUtils;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;

@OnlyIn(Dist.CLIENT)
public class EssenceBar {

    private static final ResourceLocation OVER_EXP_TEXTURE = new ResourceLocation(JITL.MODID, "textures/gui/essence_over_exp.png");

    public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, OVER_EXP_TEXTURE);
        if(player != null && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                float currentEssence = essence.getEssence();

                int yPos = 29;
                int xPos = 91;

                //if (!minecraft.options.hideGui && !player.isSpectator()) {
                    //if(instanceOfEssenceItem(player.getMainHandItem().getItem())) {
                        int y = screenHeight - yPos;
                        int x = screenWidth - xPos;
                        int texYPos = 0;
                        for (int i = 0; i < currentEssence; i++) {
                            minecraft.gui.blit(poseStack, x, y, 0, 0, texYPos, 5);
                            texYPos += 10;
                        }
                        minecraft.gui.blit(poseStack, x, y, 0, 5, 81, 5);
                    //}
                //}
            });
        }
    }

    private static boolean instanceOfEssenceItem(Item isEssence) {
        return isEssence instanceof IEssenceItem;
    }
}
