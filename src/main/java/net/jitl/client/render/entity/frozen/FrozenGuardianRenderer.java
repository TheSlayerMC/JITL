package net.jitl.client.render.entity.frozen;

import net.jitl.common.entity.frozen.npc.FrozenGuardian;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FrozenGuardianRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

    public FrozenGuardianRenderer(EntityRendererProvider.Context renderManager, DefaultedEntityGeoModel<T> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
        //this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        FrozenGuardian e = (FrozenGuardian)entity;
        boolean isActivated = e.isActivated();
        if (isActivated) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "textures/entity/frozen/frozen_guardian_awake.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(JITL.MODID, "textures/entity/frozen/frozen_guardian_asleep.png");
        }
    }
}
