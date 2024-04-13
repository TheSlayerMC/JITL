package net.jitl.client.render.entity.frozen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.model.FrozenTrollModel;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrozenTrollHeldItemLayer extends RenderLayer<FrozenTrollEntity, FrozenTrollModel<FrozenTrollEntity>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public FrozenTrollHeldItemLayer(RenderLayerParent<FrozenTrollEntity, FrozenTrollModel<FrozenTrollEntity>> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer);
        this.itemInHandRenderer = pItemInHandRenderer;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, FrozenTrollEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
//        boolean flag = pLivingEntity.getMainArm() == HumanoidArm.RIGHT;
//        pPoseStack.pushPose();
//        float f = 1.0F;
//        float f1 = -1.0F;
//        float f2 = Mth.abs(pLivingEntity.getXRot()) / 60.0F;
//        if (pLivingEntity.getXRot() < 0.0F) {
//            pPoseStack.translate(0.0F, 1.0F - f2 * 0.5F, -1.0F + f2 * 0.5F);
//        } else {
//            pPoseStack.translate(0.0F, 1.0F + f2 * 0.8F, -1.0F + f2 * 0.2F);
//        }
//
//        ItemStack itemstack = flag ? pLivingEntity.getMainHandItem() : pLivingEntity.getOffhandItem();
//        this.itemInHandRenderer.renderItem(pLivingEntity, itemstack, ItemDisplayContext.GROUND, false, pPoseStack, pBuffer, pPackedLight);
//        pPoseStack.popPose();
    }
}