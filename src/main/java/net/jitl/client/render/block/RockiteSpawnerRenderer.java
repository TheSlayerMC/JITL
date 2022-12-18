package net.jitl.client.render.block;

import net.jitl.client.model.block.RockiteSpawnerModel;
import net.jitl.common.block.entity.RockiteSpawnerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RockiteSpawnerRenderer extends GeoBlockRenderer<RockiteSpawnerEntity> {

    public RockiteSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        super(new RockiteSpawnerModel());
    }

    @Override
    public RenderType getRenderType(RockiteSpawnerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        float size = 2.0F;
        //stack.scale(size, size, size);TODO
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}