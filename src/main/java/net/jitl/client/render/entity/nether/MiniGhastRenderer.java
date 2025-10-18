package net.jitl.client.render.entity.nether;

import net.jitl.common.entity.nether.MiniGhast;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class MiniGhastRenderer<T extends GhastRenderState & GeoRenderState> extends GeoEntityRenderer<MiniGhast, T> {

    public MiniGhastRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<MiniGhast> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public T createRenderState(MiniGhast animatable, Void relatedObject) {
        return (T)new GhastRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        String name = entity.isCharging ? "mini_ghast_shooting" : "mini_ghast";
        return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/nether/" + name + ".png");
    }
}
