package net.jitl.client.render.entity.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.overworld.state.BoomRenderState;
import net.jitl.common.entity.overworld.BoomBoom;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BoomBoomRenderer<T extends BoomRenderState & GeoRenderState> extends GeoEntityRenderer<BoomBoom, T> {

    public BoomBoomRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<BoomBoom> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    public void scale(T pLivingEntity, PoseStack pMatrixStack) {
        float f = pLivingEntity.swelling;
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        pMatrixStack.scale(f2, f3, f2);
    }

    @Override
    protected T createBaseRenderState(BoomBoom entity) {
        return (T)new BoomRenderState();
    }

    @Override
    public void render(T renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        scale(renderState, poseStack);
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public void extractRenderState(BoomBoom e, T state, float ticks) {
        super.extractRenderState(e, state, ticks);
        state.swelling = e.getSwelling(ticks);
        state.isPowered = e.isPowered();
    }
}
