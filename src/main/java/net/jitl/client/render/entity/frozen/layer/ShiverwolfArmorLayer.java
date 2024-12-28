package net.jitl.client.render.entity.frozen.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Map;

import net.jitl.client.model.ShiverwolfModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.entity.Crackiness.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShiverwolfArmorLayer extends RenderLayer<WolfRenderState, ShiverwolfModel> {

    private final ShiverwolfModel adultModel;
    private final ShiverwolfModel babyModel;
    private final EquipmentLayerRenderer equipmentRenderer;
    private static final Map<Crackiness.Level, ResourceLocation> ARMOR_CRACK_LOCATIONS;

    public ShiverwolfArmorLayer(RenderLayerParent<WolfRenderState, ShiverwolfModel> renderer, EntityModelSet models, EquipmentLayerRenderer layer) {
        super(renderer);
        this.adultModel = new ShiverwolfModel(models.bakeLayer(ModelLayers.WOLF_ARMOR));
        this.babyModel = new ShiverwolfModel(models.bakeLayer(ModelLayers.WOLF_BABY_ARMOR));
        this.equipmentRenderer = layer;
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, WolfRenderState livingEntity, float limbSwing, float limbSwingAmount) {
        ItemStack itemstack = livingEntity.bodyArmorItem;
        Equippable equippable = itemstack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && !equippable.assetId().isEmpty()) {
            ShiverwolfModel wolfmodel = livingEntity.isBaby ? this.babyModel : this.adultModel;
            wolfmodel.setupAnim(livingEntity);
            this.equipmentRenderer.renderLayers(EquipmentClientInfo.LayerType.WOLF_BODY, (ResourceKey)equippable.assetId().get(), wolfmodel, itemstack, poseStack, bufferSource, packedLight);
            this.maybeRenderCracks(poseStack, bufferSource, packedLight, itemstack, wolfmodel);
        }
    }

    private void maybeRenderCracks(PoseStack poseStack, MultiBufferSource buffer, int packedLight, ItemStack armorStack, Model p_364428_) {
        Crackiness.Level crackiness$level = Crackiness.WOLF_ARMOR.byDamage(armorStack);
        if (crackiness$level != Level.NONE) {
            ResourceLocation resourcelocation = (ResourceLocation)ARMOR_CRACK_LOCATIONS.get(crackiness$level);
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.armorTranslucent(resourcelocation));
            p_364428_.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }
    }

    static {
        ARMOR_CRACK_LOCATIONS = Map.of(Level.LOW, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_low.png"), Level.MEDIUM, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_medium.png"), Level.HIGH, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_high.png"));
    }
}
