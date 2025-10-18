package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.model.block.RockiteSpawnerModel;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class RockiteSpawnerRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<RockiteSpawnerEntity, R> {

    public RockiteSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        super(new RockiteSpawnerModel());
    }

    @Override
    public @Nullable RenderType getRenderType(R renderState, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public void preRender(R renderState, PoseStack poseStack, BakedGeoModel model, SubmitNodeCollector renderTasks, CameraRenderState cameraState, int packedLight, int packedOverlay, int renderColor) {
        float size = 2.0F;
        poseStack.scale(size, size, size);
        poseStack.translate(-0.5F, 0, -0.5F);
        super.preRender(renderState, poseStack, model, renderTasks, cameraState, packedLight, packedOverlay, renderColor);
    }
}