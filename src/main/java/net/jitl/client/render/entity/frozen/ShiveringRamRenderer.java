package net.jitl.client.render.entity.frozen;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.jitl.client.render.entity.frozen.state.ShiveringRamState;
import net.jitl.common.entity.frozen.ShiveringRam;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ShiveringRamRenderer<T extends ShiveringRamState & GeoRenderState> extends GeoEntityRenderer<ShiveringRam, T> {

    public ShiveringRamRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<ShiveringRam> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void preRenderPass(RenderPassInfo<T> renderPassInfo, SubmitNodeCollector renderTasks) {
        float size = 1.1F;

        if(renderPassInfo.renderState().isBaby) {
            size = 0.5F;
        }
        renderPassInfo.poseStack().scale(size, size, size);
        super.preRenderPass(renderPassInfo, renderTasks);
    }

    @Override
    public T createRenderState(ShiveringRam animatable, Void relatedObject) {
        return (T)new ShiveringRamState();
    }

    @Override
    public void extractRenderState(ShiveringRam entity, T entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isSheared = entity.isSheared();
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull T entity) {
        String name = entity.isSheared ? "shivering_ram" : "shivering_ram_wool";
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/" + name + ".png");
    }
}
