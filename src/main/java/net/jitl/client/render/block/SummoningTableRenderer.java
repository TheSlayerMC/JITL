package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.block.state.SummoningTableRenderState;
import net.jitl.common.block.entity.SummoningTableTile;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SummoningTableRenderer implements BlockEntityRenderer<SummoningTableTile, SummoningTableRenderState> {

    private final ItemModelResolver itemModelResolver;

    public SummoningTableRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public @NotNull SummoningTableRenderState createRenderState() {
        return new SummoningTableRenderState();
    }

    @Override
    public void extractRenderState(SummoningTableTile tile, SummoningTableRenderState state, float ticks, Vec3 v, @Nullable ModelFeatureRenderer.CrumblingOverlay o) {
        BlockEntityRenderer.super.extractRenderState(tile, state, ticks, v, o);

        int i = (int)tile.getBlockPos().asLong();
        state.items = new ArrayList();

        for(int j = 0; j < tile.getItems().size(); ++j) {
            ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(itemstackrenderstate, tile.getItemInSlot(j), ItemDisplayContext.FIXED, tile.getLevel(), null, i + j);
            state.items.add(itemstackrenderstate);
        }
    }

    @Override
    public void submit(SummoningTableRenderState summoningTableRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        double y = 1D;
        renderItem(summoningTableRenderState, 0, new double[]{0.85D, y, 0.85D}, poseStack, submitNodeCollector, 0.7F);
        renderItem(summoningTableRenderState, 1, new double[]{0.85D, y, 0.5D}, poseStack, submitNodeCollector, 0.7F);
        renderItem(summoningTableRenderState, 2, new double[]{0.85D, y, 0.15D}, poseStack, submitNodeCollector, 0.7F);

        renderItem(summoningTableRenderState, 3, new double[]{0.5D, y, 0.5D}, poseStack, submitNodeCollector, 1.0F);

        renderItem(summoningTableRenderState, 4, new double[]{0.15D, y, 0.85D}, poseStack, submitNodeCollector, 0.7F);
        renderItem(summoningTableRenderState, 5, new double[]{0.15D, y, 0.5D}, poseStack, submitNodeCollector, 0.7F);
        renderItem(summoningTableRenderState, 6, new double[]{0.15D, y, 0.15D}, poseStack, submitNodeCollector, 0.7F);
    }

    private void renderItem(SummoningTableRenderState state, int itemIndex, double[] translation, PoseStack matrixStack, SubmitNodeCollector collector, float scale) {
        ItemStackRenderState item = state.items.get(itemIndex);
        if(!item.isEmpty()) {
            matrixStack.pushPose();
            float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
            matrixStack.translate(translation[0], translation[1], translation[2]);
            matrixStack.mulPose(Axis.YP.rotation(timeD));
            matrixStack.scale(scale, scale, scale);
            item.submit(matrixStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            matrixStack.popPose();
        }
    }
}