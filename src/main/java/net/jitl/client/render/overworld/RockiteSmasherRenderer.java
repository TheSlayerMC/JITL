package net.jitl.client.render.overworld;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.model.RockiteSmasherModel;
import net.jitl.client.render.model.TowerGuardianModel;
import net.jitl.common.entity.boss.RockiteSmasher;
import net.jitl.common.entity.boss.TowerGuardian;
import net.jitl.common.entity.overworld.npc.Mage;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RockiteSmasherRenderer extends GeoEntityRenderer<RockiteSmasher> {

    public RockiteSmasherRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RockiteSmasherModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(RockiteSmasher instance) {
        return getGeoModelProvider().getTextureResource(instance);
    }

    @Override
    public RenderType getRenderType(RockiteSmasher animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer
            , @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(1.5F, 1.5F, 1.5F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}