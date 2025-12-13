package net.jitl.client.render.entity.frozen.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.model.ShiverwolfModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.entity.Crackiness.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

import java.util.Map;

public class ShiverwolfArmorLayer extends RenderLayer<WolfRenderState, ShiverwolfModel> {

    private final ShiverwolfModel adultModel;
    private final ShiverwolfModel babyModel;
    private final EquipmentLayerRenderer equipmentRenderer;
    private static final Map<Crackiness.Level, Identifier> ARMOR_CRACK_LOCATIONS;

    public ShiverwolfArmorLayer(RenderLayerParent<WolfRenderState, ShiverwolfModel> renderer, EntityModelSet models, EquipmentLayerRenderer layer) {
        super(renderer);
        this.adultModel = new ShiverwolfModel(models.bakeLayer(ModelLayers.WOLF_ARMOR));
        this.babyModel = new ShiverwolfModel(models.bakeLayer(ModelLayers.WOLF_BABY_ARMOR));
        this.equipmentRenderer = layer;
    }

    @Override
    public void submit(PoseStack p_436050_, SubmitNodeCollector p_434212_, int p_433618_, WolfRenderState p_435660_, float p_435015_, float p_434923_) {
        ItemStack itemstack = p_435660_.bodyArmorItem;
        Equippable equippable = (Equippable)itemstack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && !equippable.assetId().isEmpty()) {
            ShiverwolfModel wolfmodel = p_435660_.isBaby ? this.babyModel : this.adultModel;
            this.equipmentRenderer.renderLayers(EquipmentClientInfo.LayerType.WOLF_BODY, (ResourceKey)equippable.assetId().get(), wolfmodel, p_435660_, itemstack, p_436050_, p_434212_, p_433618_, p_435660_.outlineColor);
            this.maybeRenderCracks(p_436050_, p_434212_, p_433618_, itemstack, wolfmodel, p_435660_);
        }
    }

    private void maybeRenderCracks(PoseStack p_331222_, SubmitNodeCollector p_434578_, int p_330931_, ItemStack p_331187_, Model<WolfRenderState> p_364428_, WolfRenderState p_433708_) {
        Crackiness.Level crackiness$level = Crackiness.WOLF_ARMOR.byDamage(p_331187_);
        if (crackiness$level != Level.NONE) {
            Identifier Identifier = (Identifier)ARMOR_CRACK_LOCATIONS.get(crackiness$level);
            p_434578_.submitModel(p_364428_, p_433708_, p_331222_, RenderTypes.armorTranslucent(Identifier), p_330931_, OverlayTexture.NO_OVERLAY, p_433708_.outlineColor, (ModelFeatureRenderer.CrumblingOverlay)null);
        }

    }

    static {
        ARMOR_CRACK_LOCATIONS = Map.of(Level.LOW, Identifier.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_low.png"), Level.MEDIUM, Identifier.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_medium.png"), Level.HIGH, Identifier.withDefaultNamespace("textures/entity/wolf/wolf_armor_crackiness_high.png"));
    }
}
