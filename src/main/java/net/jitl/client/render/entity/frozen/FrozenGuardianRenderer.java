package net.jitl.client.render.entity.frozen;

import net.jitl.client.model.AnimatedMonsterModel;
import net.jitl.client.render.entity.state.ActiveRenderState;
import net.jitl.common.entity.frozen.npc.FrozenGuardian;
import net.jitl.common.entity.nether.MiniGhast;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class FrozenGuardianRenderer<R extends ActiveRenderState & GeoRenderState> extends GeoEntityRenderer<FrozenGuardian, R> {

    public FrozenGuardianRenderer(EntityRendererProvider.Context renderManager, AnimatedMonsterModel<FrozenGuardian> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.5F;
        //this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    protected R createBaseRenderState(FrozenGuardian entity) {
        return (R)new ActiveRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull R entity) {
        boolean isActivated = entity.isActive;
        if (isActivated) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/frozen_guardian_awake.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/frozen_guardian_asleep.png");
        }
    }

    @Override
    public void extractRenderState(FrozenGuardian entity, R entityRenderState, float partialTick) {
        super.extractRenderState(entity, entityRenderState, partialTick);
        entityRenderState.isActive = entity.isActivated();
    }
}
