package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.model.block.GrindstoneModel;
import net.jitl.client.model.block.RockiteSpawnerModel;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RockiteSpawnerRenderer extends GeoBlockRenderer<RockiteSpawnerEntity> {

    public RockiteSpawnerRenderer(BlockEntityRendererProvider.Context r) {
        super(r, new RockiteSpawnerModel());
    }

    @Override
    public RenderType getRenderType(RockiteSpawnerEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        float size = 2.0F;
        stack.scale(size, size, size);
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}