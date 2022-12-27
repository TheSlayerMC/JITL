package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.model.block.RockiteSpawnerModel;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RockiteSpawnerRenderer extends GeoBlockRenderer<RockiteSpawnerEntity> {

    public RockiteSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        super(new RockiteSpawnerModel());
    }

    @Override
    public RenderType getRenderType(RockiteSpawnerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, RockiteSpawnerEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float size = 2.0F;
        poseStack.scale(size, size, size);
        poseStack.translate(-0.5F, 0, -0.5F);
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}