package net.jitl.client.render.entity.nether;

import net.jitl.common.entity.nether.MiniGhast;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MiniGhastRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

    public MiniGhastRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        MiniGhast e = (MiniGhast)entity;
        String name = e.isCharging() ? "mini_ghast_shooting" : "mini_ghast";
        return new ResourceLocation(JITL.MODID, "textures/entity/nether/" + name + ".png");
    }
}
