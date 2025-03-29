package net.jitl.client.render.entity.frozen;

import net.jitl.client.JModelLayers;
import net.jitl.client.model.FrozenTrollModel;
import net.jitl.client.render.entity.frozen.state.FrozenTrollState;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.jitl.core.init.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrozenTrollRenderer extends MobRenderer<FrozenTrollEntity, FrozenTrollState, FrozenTrollModel<FrozenTrollState>> {

    public FrozenTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenTrollModel<>(context.bakeLayer(JModelLayers.FROZEN_TROLL_MODEL_LAYER)), 0.5F);
    }

    @Override
    public @NotNull FrozenTrollState createRenderState() {
        return new FrozenTrollState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenTrollState entity) {
        boolean isAngry = entity.isAngry;
        if (isAngry) {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/frozen_troll_angry.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, "textures/entity/frozen/frozen_troll_lookin_cute.png");
        }
    }

    @Override
    public void extractRenderState(FrozenTrollEntity e, FrozenTrollState s, float f) {
        super.extractRenderState(e, s, f);
        s.isAngry = e.isAngry();
    }
}
