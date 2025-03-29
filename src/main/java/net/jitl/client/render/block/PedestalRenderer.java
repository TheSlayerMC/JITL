package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.block.entity.PedestalTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class PedestalRenderer implements BlockEntityRenderer<PedestalTile> {

    private final ItemRenderer renderEntity;

    public PedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.renderEntity = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(PedestalTile e, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn, Vec3 v) {
        ItemStack i = e.getItem(0);
        renderItem(e, i, new double[]{0.5D, 1.25D, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 1.0F);
    }

    private void renderItem(PedestalTile block, ItemStack stack, double[] translation, PoseStack matrixStack, MultiBufferSource buffer, int combinedOverlay, int lightLevel, float scale) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        matrixStack.pushPose();
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(Axis.YP.rotation(timeD));
        matrixStack.scale(scale, scale, scale);
        //BakedModel model = renderEntity.getModel(stack, null, null, 0);
        //this.renderEntity.render(stack, ItemDisplayContext.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);

        int j = LevelRenderer.getLightColor(LevelRenderer.BrightnessGetter.DEFAULT, Objects.requireNonNull(block.getLevel()), block.getBlockState(), block.getBlockPos());
        renderer.renderStatic(stack, ItemDisplayContext.GROUND, j, OverlayTexture.NO_OVERLAY, matrixStack, buffer, block.getLevel(), 0);
        matrixStack.popPose();
    }
}
