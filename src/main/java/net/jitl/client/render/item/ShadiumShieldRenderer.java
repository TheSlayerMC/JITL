package net.jitl.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.jitl.core.init.JITL;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Set;

public class ShadiumShieldRenderer implements SpecialModelRenderer<DataComponentMap> {

    private final ShieldModel model;

    public ShadiumShieldRenderer(ShieldModel model) {
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(ItemStack p_387204_) {
        return p_387204_.immutableComponents();
    }

    @Override
    public void render(@Nullable DataComponentMap map, ItemDisplayContext ctx, PoseStack pose, MultiBufferSource buff, int alp, int col, boolean bol) {
        pose.pushPose();
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexconsumer = buff.getBuffer(RenderType.entityCutoutNoCull(JITL.rl("textures/shield/" + "shadium" + "_shield.png")));
        this.model.handle().render(pose, vertexconsumer, alp, col);
        this.model.plate().render(pose, vertexconsumer, alp, col);
        this.model.renderToBuffer(pose, vertexconsumer, alp, col);
        pose.popPose();
    }

    @Override
    public void getExtents(Set<Vector3f> set) {
        PoseStack posestack = new PoseStack();
        posestack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(posestack, set);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final Unbaked INSTANCE = new Unbaked();
        public static final MapCodec<Unbaked> MAP_CODEC;

        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(EntityModelSet p_387269_) {
            return new ShadiumShieldRenderer(new ShieldModel(p_387269_.bakeLayer(ModelLayers.SHIELD)));
        }

        static {
            MAP_CODEC = MapCodec.unit(INSTANCE);
        }
    }
}
