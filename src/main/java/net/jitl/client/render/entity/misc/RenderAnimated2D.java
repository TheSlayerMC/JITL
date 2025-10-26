package net.jitl.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.JModelLayers;
import net.jitl.client.model.JItemModel;
import net.jitl.client.render.projectile.state.TwoDRenderState;
import net.jitl.core.helper.RandHelper;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class RenderAnimated2D<T extends Entity> extends EntityRenderer<T, TwoDRenderState> {

    public final String[] textures;
    public final int animationSpeed;
    private final JItemModel model;

    public RenderAnimated2D(EntityRendererProvider.Context context, int animationSpeed, String... textures) {
        super(context);
        this.textures = textures;
        this.animationSpeed = animationSpeed;
        this.model = new JItemModel<>(context.bakeLayer(JModelLayers.ITEM_MODEL));
    }

    @Override
    public void submit(TwoDRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(cameraRenderState.orientation);
        poseStack.translate(0.0F, -0.3F, 0.0F);
        float scale = 0.5F;
        poseStack.scale(scale, scale, scale);
        nodeCollector.submitModel(this.model, renderState, poseStack, this.model.renderType(renderState.texture), renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
        poseStack.popPose();
    }

    @Override
    public @NotNull TwoDRenderState createRenderState() {
        return new TwoDRenderState();
    }

    @Override
    protected int getBlockLightLevel(@NotNull T entity, @NotNull BlockPos pos) {
        return 15;
    }

    @Override
    public void extractRenderState(@NotNull T entity, @NotNull TwoDRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.texture = getTextureLocation(entity);
        state.entity = entity;
    }

    public @NotNull ResourceLocation getTextureLocation(T t) {
        RandomSource r = RandomSource.create();
        if(t.tickCount % animationSpeed == 0) {
            String name = RandHelper.chooseEqual(r, textures);
            return JITL.rl("textures/entity/" + name + ".png");
        }
        if(t.tickCount % (animationSpeed / 2) == 0) {
            String name = RandHelper.chooseEqual(r, textures);
            return JITL.rl("textures/entity/" + name + ".png");
        } else {
            return JITL.rl("textures/entity/" + textures[0] + ".png");
        }
    }
}
