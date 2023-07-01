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
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SentacoinRender extends EntityRenderer<Sentacoin> {

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
    public void render(@NotNull Sentacoin entity, float entityYaw, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
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
        this.itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer, entity.level(), entity.getId());
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Sentacoin e) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
