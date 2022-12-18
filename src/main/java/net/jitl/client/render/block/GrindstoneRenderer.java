package net.jitl.client.render.block;

import net.jitl.client.model.block.GrindstoneModel;
import net.jitl.common.block.entity.GrindstoneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GrindstoneRenderer extends GeoBlockRenderer<GrindstoneEntity> {

    public GrindstoneRenderer(BlockEntityRendererProvider.Context context) {
        super(new GrindstoneModel());
    }

    @Override
    public RenderType getRenderType(GrindstoneEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}