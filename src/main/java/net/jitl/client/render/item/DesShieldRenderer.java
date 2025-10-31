package net.jitl.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.jitl.core.init.JITL;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Set;

public class DesShieldRenderer implements SpecialModelRenderer<DataComponentMap> {

    private final ShieldModel model;
    private final MaterialSet materials;

    public DesShieldRenderer(MaterialSet materials, ShieldModel model) {
        this.model = model;
        this.materials = materials;
    }


    @Nullable
    public DataComponentMap extractArgument(ItemStack i) {
        return i.immutableComponents();
    }

    @Override
    public void submit(@Nullable DataComponentMap typedDataComponents, @NotNull ItemDisplayContext itemDisplayContext, PoseStack pose, SubmitNodeCollector submitNodeCollector, int i, int i1, boolean b, int i2) {
        pose.pushPose();
        pose.scale(1.0F, -1.0F, -1.0F);
        Material material = new Material(Sheets.SHIELD_SHEET, JITL.rl("entity/shield/des_shield"));
        submitNodeCollector.submitModelPart(this.model.handle(), pose, this.model.renderType(material.atlasLocation()), i, i1, this.materials.get(material), false, false,-1, null, i2 );
        submitNodeCollector.submitModelPart(this.model.plate(), pose, material.renderType(RenderType::entityCutout), i, i1, this.materials.get(material), false, b, -1, null, i2);
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
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);;

        @Override
        public @NotNull MapCodec<DesShieldRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext c) {
            return new DesShieldRenderer(c.materials(), new ShieldModel(c.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}
