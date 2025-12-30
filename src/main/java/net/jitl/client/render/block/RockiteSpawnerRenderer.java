package net.jitl.client.render.block;

import net.jitl.client.model.block.RockiteSpawnerModel;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.RenderPassInfo;

public class RockiteSpawnerRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<RockiteSpawnerEntity, R> {

    public RockiteSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        super(new RockiteSpawnerModel());
    }

    @Override
    public @Nullable RenderType getRenderType(R renderState, Identifier texture) {
        return RenderTypes.entityTranslucent(texture);
    }

    @Override
    public void preRenderPass(RenderPassInfo<R> renderPassInfo, SubmitNodeCollector renderTasks) {
        float size = 2.0F;
        renderPassInfo.poseStack().scale(size, size, size);
        renderPassInfo.poseStack().translate(-0.5F, 0, -0.5F);
        super.preRenderPass(renderPassInfo, renderTasks);
    }
}