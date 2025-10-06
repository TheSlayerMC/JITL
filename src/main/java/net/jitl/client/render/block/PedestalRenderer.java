package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.block.state.BlockEntityItemRenderState;
import net.jitl.common.block.entity.PedestalTile;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class PedestalRenderer implements BlockEntityRenderer<PedestalTile, BlockEntityItemRenderState> {

    private final ItemModelResolver renderEntity;

    public PedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.renderEntity = context.itemModelResolver();
    }

    @Override
    public BlockEntityItemRenderState createRenderState() {
        return new BlockEntityItemRenderState();
    }

    @Override
    public void submit(BlockEntityItemRenderState blockEntityItemRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        renderItem(blockEntityItemRenderState, new double[]{0.5D, 1.25D, 0.5D}, poseStack, submitNodeCollector, 1.0F);
    }

    private void renderItem(BlockEntityItemRenderState state, double[] translation, PoseStack matrixStack, SubmitNodeCollector collector, float scale) {
        ItemStackRenderState item = state.item;
        if (!item.isEmpty()) {
            matrixStack.pushPose();
            float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
            matrixStack.translate(translation[0], translation[1], translation[2]);
            matrixStack.mulPose(Axis.YP.rotation(timeD));
            matrixStack.scale(scale, scale, scale);
            item.submit(matrixStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            matrixStack.popPose();
        }
    }

    @Override
    public void extractRenderState(PedestalTile tile, BlockEntityItemRenderState s, float ticks, Vec3 v, @Nullable ModelFeatureRenderer.CrumblingOverlay o) {
        BlockEntityRenderer.super.extractRenderState(tile, s, ticks, v, o);
        ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
        this.renderEntity.updateForTopItem(itemstackrenderstate, tile.getItem(0), ItemDisplayContext.GROUND, tile.getLevel(), null, (int)tile.getBlockPos().asLong());
        s.item = itemstackrenderstate;
    }
}
