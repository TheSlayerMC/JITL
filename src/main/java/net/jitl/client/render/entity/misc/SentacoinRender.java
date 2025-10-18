package net.jitl.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.client.render.item.state.SentacoinRenderState;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SentacoinRender<T extends Sentacoin> extends EntityRenderer<T, SentacoinRenderState> {

    private final ItemModelResolver renderEntity;
    private final Sentacoin.Type type;

    public SentacoinRender(EntityRendererProvider.Context context, Sentacoin.Type type) {
        super(context);
        this.type = type;
        this.shadowRadius = 0.07F;
        this.shadowStrength = 0.75F;
        this.renderEntity = context.getItemModelResolver();
    }

    @Override
    public void submit(SentacoinRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        renderItem(renderState, new double[]{0.5D, 1.25D, 0.5D}, poseStack, nodeCollector);
    }

    private void renderItem(SentacoinRenderState state, double[] translation, PoseStack matrixStack, SubmitNodeCollector collector) {
        ItemStackRenderState item = state.item;
        if (!item.isEmpty()) {
            matrixStack.pushPose();
            float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
            matrixStack.translate(translation[0], translation[1], translation[2]);
            matrixStack.mulPose(Axis.YP.rotation(timeD));
            matrixStack.scale(state.scale, state.scale, state.scale);
            item.submit(matrixStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            matrixStack.popPose();
        }
    }

    @Override
    public @NotNull SentacoinRenderState createRenderState() {
        return new SentacoinRenderState();
    }

    @Override
    public void extractRenderState(@NotNull T entity, @NotNull SentacoinRenderState item, float f) {
        super.extractRenderState(entity, item, f);
        ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
        ItemStack stack = new ItemStack(JItems.SENTACOIN.get());
        item.scale = 0.55F;
        switch(type) {
            case COIN -> stack = new ItemStack(JItems.SENTACOIN.get());
            case BAG -> {
                stack = new ItemStack(JItems.SENTACOIN_BAG.get());
                item.scale = 1F;
            }
        }
        this.renderEntity.updateForTopItem(itemstackrenderstate, stack, ItemDisplayContext.GROUND, entity.level(), null, item.seed);
    }
}
