package net.jitl.client.render.vehicle;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.jitl.client.JModelLayers;
import net.jitl.client.model.JBoatModel;
import net.jitl.common.entity.base.JBoat;
import net.jitl.core.init.JITL;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

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
    protected void renderTypeAdditions(BoatRenderState b, PoseStack p, MultiBufferSource buffer, int i) {
        if (!b.isUnderWater) {
            this.waterPatchModel.renderToBuffer(p, buffer.getBuffer(this.waterPatchModel.renderType(this.texture)), i, OverlayTexture.NO_OVERLAY);
        }
    }
}
