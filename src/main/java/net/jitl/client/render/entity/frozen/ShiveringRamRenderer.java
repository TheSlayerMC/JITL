package net.jitl.client.render.entity.frozen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.entity.frozen.state.ShiveringRamState;
import net.jitl.common.entity.frozen.ShiveringRam;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ShiveringRamRenderer<T extends ShiveringRamState & GeoRenderState> extends GeoEntityRenderer<ShiveringRam, T> {

    public ShiveringRamRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<ShiveringRam> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(T renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        float size = 1.1F;
        if(renderState.isBaby) {
            size = 0.5F;
        }
        poseStack.scale(size, size, size);
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    protected T createBaseRenderState(ShiveringRam entity) {
        return (T)new ShiveringRamState();
    }

    @Override
    public void extractRenderState(ShiveringRam entity, T entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isSheared = entity.isSheared();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        String name = entity.isSheared ? "shivering_ram" : "shivering_ram_wool";
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/" + name + ".png");
    }
}
