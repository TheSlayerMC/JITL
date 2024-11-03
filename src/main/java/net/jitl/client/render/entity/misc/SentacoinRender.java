package net.jitl.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.entity.misc.Sentacoin;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SentacoinRender<T extends Sentacoin> extends EntityRenderer<T, ItemEntityRenderState> {

    private final ItemRenderer itemRenderer;
    private final Sentacoin.Type type;

    public SentacoinRender(EntityRendererProvider.Context context, Sentacoin.Type type) {
        super(context);
        this.type = type;
        this.shadowRadius = 0.07F;
        this.shadowStrength = 0.75F;
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(@NotNull ItemEntityRenderState entity, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, matrixStack, buffer, packedLight);
        float angle = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        float scale = 0.55F;
        ItemStack stack = new ItemStack(JItems.SENTACOIN.get());
        switch(type) {
            case COIN -> stack = new ItemStack(JItems.SENTACOIN.get());
            case BAG -> {
                stack = new ItemStack(JItems.SENTACOIN_BAG.get());
                scale = 1F;
            }
        }
        matrixStack.pushPose();
        matrixStack.scale(scale, scale, scale);
        matrixStack.mulPose(Axis.YP.rotation(angle));
        assert entity.itemModel != null;
        this.itemRenderer.render(stack, ItemDisplayContext.GROUND, false, matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, entity.itemModel);
        matrixStack.popPose();
        super.render(entity, matrixStack, buffer, packedLight);
    }

    @Override
    public ItemEntityRenderState createRenderState() {
        return new ItemEntityRenderState();
    }

    public void extractRenderState(@NotNull T entity, @NotNull ItemEntityRenderState item, float f) {
        super.extractRenderState(entity, item, f);
        ItemStack itemstack = ((ItemSupplier)item).getItem();
        item.itemModel = !itemstack.isEmpty() ? this.itemRenderer.getModel(itemstack, entity.level(), null, entity.getId()) : null;
        item.item = itemstack.copy();
    }
}
