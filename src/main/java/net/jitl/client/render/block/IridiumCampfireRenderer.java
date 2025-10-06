package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jitl.common.block.IridiumCampfireBlock;
import net.jitl.common.block.entity.IridiumCampfireBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.CampfireRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class IridiumCampfireRenderer implements BlockEntityRenderer<IridiumCampfireBlockEntity, CampfireRenderState> {

    private final ItemModelResolver itemModelResolver;

    public IridiumCampfireRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    public CampfireRenderState createRenderState() {
        return new CampfireRenderState();
    }

    @Override
    public void extractRenderState(IridiumCampfireBlockEntity entity, CampfireRenderState state, float f, Vec3 vec, @Nullable ModelFeatureRenderer.CrumblingOverlay model) {
        BlockEntityRenderer.super.extractRenderState(entity, state, f, vec, model);
        state.facing = entity.getBlockState().getValue(IridiumCampfireBlock.FACING);
        int i = (int)entity.getBlockPos().asLong();
        state.items = new ArrayList();

        for(int j = 0; j < entity.getItems().size(); ++j) {
            ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(itemstackrenderstate, (ItemStack)entity.getItems().get(j), ItemDisplayContext.FIXED, entity.getLevel(), null, i + j);
            state.items.add(itemstackrenderstate);
        }

    }

    @Override
    public void submit(CampfireRenderState r, PoseStack stack, SubmitNodeCollector collector, CameraRenderState state) {
        Direction direction = r.facing;
        List<ItemStackRenderState> list = r.items;

        for(int i = 0; i < list.size(); ++i) {
            ItemStackRenderState itemstackrenderstate = list.get(i);
            if (!itemstackrenderstate.isEmpty()) {
                stack.pushPose();
                stack.translate(0.5F, 0.44921875F, 0.5F);
                Direction direction1 = Direction.from2DDataValue((i + direction.get2DDataValue()) % 4);
                float f = -direction1.toYRot();
                stack.mulPose(Axis.YP.rotationDegrees(f));
                stack.mulPose(Axis.XP.rotationDegrees(90.0F));
                stack.translate(-0.3125F, -0.3125F, 0.0F);
                stack.scale(0.375F, 0.375F, 0.375F);
                itemstackrenderstate.submit(stack, collector, r.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                stack.popPose();
            }
        }
    }
}