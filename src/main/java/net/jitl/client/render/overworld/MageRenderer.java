package net.jitl.client.render.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.model.MageModel;
import net.jitl.common.entity.overworld.npc.Mage;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MageRenderer extends GeoEntityRenderer<Mage> {

    public MageRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MageModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(Mage instance) {
        return JITL.rl("textures/entity/overworld/mage.png");
    }

    @Override
    public RenderType getRenderType(Mage animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer
            , @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(1.2F, 1.2F, 1.2F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
