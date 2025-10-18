package net.jitl.client.render.vehicle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.model.JBoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

public class JBoatRenderer extends AbstractBoatRenderer {

    private final Model waterPatchModel;
    private final ResourceLocation texture;
    private final EntityModel<BoatRenderState> model;

    public JBoatRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, String name) {
        super(context);
        this.shadowRadius = 0.8F;
        this.texture = layer.model().withPath((type) -> "textures/entity/boat/" + name + ".png");
        this.waterPatchModel = new Model.Simple(context.bakeLayer(ModelLayers.BOAT_WATER_PATCH), (e) -> RenderType.waterMask());
        this.model = new JBoatModel(context.bakeLayer(layer));
    }

    @Override
    protected EntityModel<BoatRenderState> model() {
        return this.model;
    }

    @Override
    protected RenderType renderType() {
        return this.model.renderType(this.texture);
    }

    @Override
    protected void submitTypeAdditions(BoatRenderState s, PoseStack p, SubmitNodeCollector node, int i) {
        if (!s.isUnderWater) {
            node.submitModel(
                    this.waterPatchModel,
                    Unit.INSTANCE,
                    p,
                    this.waterPatchModel.renderType(this.texture),
                    i,
                    OverlayTexture.NO_OVERLAY,
                    s.outlineColor,
                    null
            );
        }
    }
}
