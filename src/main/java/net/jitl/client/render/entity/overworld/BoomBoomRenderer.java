package net.jitl.client.render.entity.overworld;


import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.AnimatedMonsterRenderer;
import net.jitl.common.entity.overworld.BoomBoom;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BoomBoomRenderer<T extends LivingEntity & GeoEntity> extends AnimatedMonsterRenderer<T> {

    public BoomBoomRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider, 0.5F);
    }

    public void scale(BoomBoom pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = pLivingEntity.getSwelling(pPartialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        pMatrixStack.scale(f2, f3, f2);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        BoomBoom b = (BoomBoom)animatable;
        //scale(b, stack, partialTicks);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
