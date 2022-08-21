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
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class EssenceBar implements IGuiOverlay {

    private static final ResourceLocation OVER_EXP_TEXTURE = new ResourceLocation(JITL.MODID, "gui/essence_over_exp.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        System.out.println("RENDERING");

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        if(player != null && !player.isCreative() && !player.isSpectator()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                float currentEssence = essence.getEssence();
                RenderSystem.setShaderTexture(0, OVER_EXP_TEXTURE);

                int yPos = 29;
                int xPos = 91;

                if (!minecraft.options.hideGui && !player.isSpectator()) {
                    if(instanceOfEssenceItem(player.getMainHandItem().getItem())) {
                        int y = screenHeight - yPos;
                        int x = screenWidth / 2 - xPos;
                        for (int i = 0; i < currentEssence; i++) {
                            RenderUtils.blit(poseStack, x, y, 0, 0, 81, 5, 81, 11);
                            xPos += 10;
                        }
                        RenderUtils.blit(poseStack, x, y, 0, 0, 81, 11, 81, 11);

                    }
                }
            });
        }
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static boolean instanceOfEssenceItem(Item isEssence) {
        return isEssence instanceof IEssenceItem;
    }
}
