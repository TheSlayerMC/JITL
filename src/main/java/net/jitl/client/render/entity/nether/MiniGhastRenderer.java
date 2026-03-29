package net.jitl.client.render.entity.nether;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.jitl.common.entity.nether.MiniGhast;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull Identifier getTextureLocation(@NotNull T entity) {
        String name = entity.isCharging ? "mini_ghast_shooting" : "mini_ghast";
        return Identifier.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/nether/" + name + ".png");
    }
}
