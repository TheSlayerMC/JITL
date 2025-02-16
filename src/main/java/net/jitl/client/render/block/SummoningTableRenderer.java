package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.block.entity.SummoningTableTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SummoningTableRenderer implements BlockEntityRenderer<SummoningTableTile> {

    private final ItemRenderer renderEntity;

    public SummoningTableRenderer(BlockEntityRendererProvider.Context context) {
        this.renderEntity = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(SummoningTableTile e, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        double y = 1D;
        renderItem(e.getItem(0), new double[]{0.85D, y, 0.85D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);
        renderItem(e.getItem(1), new double[]{0.85D, y, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);
        renderItem(e.getItem(2), new double[]{0.85D, y, 0.15D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);

        renderItem(e.getItem(3), new double[]{0.5D, y, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 1.0F);

        renderItem(e.getItem(4), new double[]{0.15D, y, 0.85D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);
        renderItem(e.getItem(5), new double[]{0.15D, y, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);
        renderItem(e.getItem(6), new double[]{0.15D, y, 0.15D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 0.7F);
    }

    private void renderItem(ItemStack stack, double[] translation, PoseStack matrixStack, MultiBufferSource buffer, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.pushPose();
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(Axis.YP.rotation(timeD));
        matrixStack.scale(scale, scale, scale);
        BakedModel model = renderEntity.getModel(stack, null, null, 0);
        this.renderEntity.render(stack, ItemDisplayContext.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);
        matrixStack.popPose();
    }
}
