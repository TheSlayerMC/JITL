package net.jitl.client.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.JModelLayers;
import net.jitl.client.model.JItemModel;
import net.jitl.client.render.projectile.state.TwoDRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class RenderProjectile<T extends Entity> extends EntityRenderer<T, TwoDRenderState> {

    private final Identifier texture;
    private final JItemModel model;

    public RenderProjectile(EntityRendererProvider.Context context, Identifier texture) {
        super(context);
        this.texture = texture;
        this.model = new JItemModel<>(context.bakeLayer(JModelLayers.ITEM_MODEL));
    }

    @Override
    public void submit(TwoDRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(cameraRenderState.orientation);
        poseStack.translate(0.0F, -0.3F, 0.0F);
        float scale = 0.35F;
        poseStack.scale(scale, scale, scale);
        nodeCollector.submitModel(this.model, renderState, poseStack, this.model.renderType(renderState.texture), renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(@NotNull T entity, @NotNull TwoDRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.texture = this.texture;
    }

    @Override
    public @NotNull TwoDRenderState createRenderState() {
        return new TwoDRenderState();
    }

    @Override
    protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
        return 15;
    }
}
