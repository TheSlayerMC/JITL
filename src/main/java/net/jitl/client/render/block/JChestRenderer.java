package net.jitl.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.jitl.client.JModelLayers;
import net.jitl.client.render.block.state.JChestRenderState;
import net.jitl.common.block.JChestBlock;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.client.model.ChestModel;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T, JChestRenderState> {

    private final ChestModel singleModel;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;
    private final ItemModelResolver renderEntity;
    private final MaterialSet materials;

    public static final MaterialMapper CHEST_MAPPER = new MaterialMapper(Sheets.CHEST_SHEET, "entity/chest");

    public static final Material BOILING_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("boiling_chest"));
    public static final Material BOILING_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("boiling_chest_left"));
    public static final Material BOILING_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("boiling_chest_right"));

    public static final Material CLOUDIA_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("cloudia_chest"));
    public static final Material CLOUDIA_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("cloudia_chest_left"));
    public static final Material CLOUDIA_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("cloudia_chest_right"));

    public static final Material CORBA_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("corba_chest"));
    public static final Material CORBA_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("corba_chest_left"));
    public static final Material CORBA_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("corba_chest_right"));

    public static final Material DEPTHS_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("depths_chest"));
    public static final Material DEPTHS_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("depths_chest_left"));
    public static final Material DEPTHS_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("depths_chest_right"));

    public static final Material EUCA_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("euca_chest"));
    public static final Material EUCA_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("euca_chest_left"));
    public static final Material EUCA_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("euca_chest_right"));

    public static final Material FROZEN_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("frozen_chest"));
    public static final Material FROZEN_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("frozen_chest_left"));
    public static final Material FROZEN_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("frozen_chest_right"));

    public static final Material JOURNEY_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("journey_chest"));
    public static final Material JOURNEY_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("journey_chest_left"));
    public static final Material JOURNEY_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("journey_chest_right"));

    public static final Material NETHER_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("nether_chest"));
    public static final Material NETHER_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("nether_chest_left"));
    public static final Material NETHER_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("nether_chest_right"));

    public static final Material SENTERIAN_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("senterian_chest"));
    public static final Material SENTERIAN_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("senterian_chest_left"));
    public static final Material SENTERIAN_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("senterian_chest_right"));

    public static final Material TERRANIAN_CHEST_LOCATION = CHEST_MAPPER.apply(JITL.rl("terranian_chest"));
    public static final Material TERRANIAN_CHEST_LOCATION_LEFT = CHEST_MAPPER.apply(JITL.rl("terranian_chest_left"));
    public static final Material TERRANIAN_CHEST_LOCATION_RIGHT = CHEST_MAPPER.apply(JITL.rl("terranian_chest_right"));

    public JChestRenderer(BlockEntityRendererProvider.Context context) {
        this.materials = context.materials();
        this.singleModel = new ChestModel(context.bakeLayer(JModelLayers.JCHEST));
        this.doubleLeftModel = new ChestModel(context.bakeLayer(JModelLayers.JDOUBLE_CHEST_LEFT));
        this.doubleRightModel = new ChestModel(context.bakeLayer(JModelLayers.JDOUBLE_CHEST_RIGHT));
        this.renderEntity = context.itemModelResolver();
    }

    public static Material chooseMaterial(JChestRenderState.JChestMaterialType materialType, ChestType chestType) {
        return switch(materialType) {
            case BOILING -> chooseMaterial(chestType, BOILING_CHEST_LOCATION, BOILING_CHEST_LOCATION_LEFT, BOILING_CHEST_LOCATION_RIGHT);
            case CLOUDIA -> chooseMaterial(chestType, CLOUDIA_CHEST_LOCATION, CLOUDIA_CHEST_LOCATION_LEFT, CLOUDIA_CHEST_LOCATION_RIGHT);
            case CORBA -> chooseMaterial(chestType, CORBA_CHEST_LOCATION, CORBA_CHEST_LOCATION_LEFT, CORBA_CHEST_LOCATION_RIGHT);
            case DEPTHS -> chooseMaterial(chestType, DEPTHS_CHEST_LOCATION, DEPTHS_CHEST_LOCATION_LEFT, DEPTHS_CHEST_LOCATION_RIGHT);
            case EUCA -> chooseMaterial(chestType, EUCA_CHEST_LOCATION, EUCA_CHEST_LOCATION_LEFT, EUCA_CHEST_LOCATION_RIGHT);
            case NETHER -> chooseMaterial(chestType, NETHER_CHEST_LOCATION, NETHER_CHEST_LOCATION_LEFT, NETHER_CHEST_LOCATION_RIGHT);
            case FROZEN -> chooseMaterial(chestType, FROZEN_CHEST_LOCATION, FROZEN_CHEST_LOCATION_LEFT, FROZEN_CHEST_LOCATION_RIGHT);
            case SENTERIAN -> chooseMaterial(chestType, SENTERIAN_CHEST_LOCATION, SENTERIAN_CHEST_LOCATION_LEFT, SENTERIAN_CHEST_LOCATION_RIGHT);
            case TERRANIAN -> chooseMaterial(chestType, TERRANIAN_CHEST_LOCATION, TERRANIAN_CHEST_LOCATION_LEFT, TERRANIAN_CHEST_LOCATION_RIGHT);
            case JOURNEY -> chooseMaterial(chestType, JOURNEY_CHEST_LOCATION, JOURNEY_CHEST_LOCATION_LEFT, JOURNEY_CHEST_LOCATION_RIGHT);
        };
    }

    private JChestRenderState.JChestMaterialType getChestMaterial(BlockEntity e) {
        Block chest = e.getBlockState().getBlock();
        JChestRenderState.JChestMaterialType type = JChestRenderState.JChestMaterialType.JOURNEY;
        if(chest == JBlocks.JOURNEY_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.JOURNEY;
        }
        if(chest == JBlocks.EUCA_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.EUCA;
        }
        if(chest == JBlocks.FROZEN_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.FROZEN;
        }
        if(chest == JBlocks.BOIL_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.BOILING;
        }
        if(chest == JBlocks.NETHER_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.NETHER;
        }
        if(chest == JBlocks.DEPTHS_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.DEPTHS;
        }
        if(chest == JBlocks.CORBA_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.CORBA;
        }
        if(chest == JBlocks.TERRANIAN_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.TERRANIAN;
        }
        if(chest == JBlocks.CLOUDIA_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.CLOUDIA;
        }
        if(chest == JBlocks.SENTERIAN_CHEST.get()) {
            type = JChestRenderState.JChestMaterialType.CLOUDIA;
        }
        return type;
    }

    private static Material chooseMaterial(ChestType chestType, Material doubleMaterial, Material leftMaterial, Material rightMaterial) {
        return switch(chestType) {
            case LEFT -> leftMaterial;
            case RIGHT -> rightMaterial;
            default -> doubleMaterial;
        };
    }

    @Override
    public @NotNull JChestRenderState createRenderState() {
        return new JChestRenderState();
    }

    @Override
    public void extractRenderState(T tile, JChestRenderState render, float tick, Vec3 vec, @Nullable ModelFeatureRenderer.CrumblingOverlay overlay) {
        DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
        label30: {
            BlockEntityRenderer.super.extractRenderState(tile, render, tick, vec, overlay);
            boolean flag = tile.getLevel() != null;
            BlockState blockstate = tile.getBlockState();
            render.type = blockstate.hasProperty(JChestBlock.TYPE) ? blockstate.getValue(JChestBlock.TYPE) : ChestType.SINGLE;
            render.angle = blockstate.getValue(JChestBlock.FACING).toYRot();
            render.material = this.getChestMaterial(tile);
            render.facingYAngle = tile.getBlockState().getValue(JChestBlock.FACING).toYRot();
            render.isLocked = tile.getBlockState().getValue(JChestBlock.IS_LOCKED);
            ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
            this.renderEntity.updateForTopItem(itemstackrenderstate, new ItemStack(JItems.PADLOCK.get()), ItemDisplayContext.GROUND, tile.getLevel(), null, (int)tile.getBlockPos().asLong());
            render.item = itemstackrenderstate;
            if (flag) {
                Block var10 = blockstate.getBlock();
                if (var10 instanceof JChestBlock) {
                    JChestBlock chestblock = (JChestBlock)var10;
                    neighborcombineresult = chestblock.combine(blockstate, tile.getLevel(), tile.getBlockPos(), true);
                    break label30;
                }
            }
            neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
        }

        render.open = neighborcombineresult.apply(ChestBlock.opennessCombiner(tile)).get(tick);
        if(render.type != ChestType.SINGLE) {
            render.lightCoords = ((Int2IntFunction)neighborcombineresult.apply(new BrightnessCombiner())).applyAsInt(render.lightCoords);
        }
    }

    @Override
    public void submit(JChestRenderState render, PoseStack stack, SubmitNodeCollector node, CameraRenderState renderState) {
        stack.pushPose();
        stack.translate(0.5F, 0.5F, 0.5F);
        stack.mulPose(Axis.YP.rotationDegrees(-render.angle));
        stack.translate(-0.5F, -0.5F, -0.5F);
        float f = render.open;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        Material material = chooseMaterial(render.material, render.type);
        RenderType rendertype = material.renderType(RenderType::entityCutout);
        TextureAtlasSprite textureatlassprite = this.materials.get(material);
        if (render.type != ChestType.SINGLE) {
            if (render.type == ChestType.LEFT) {
                node.submitModel(this.doubleLeftModel, f, stack, rendertype, render.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, render.breakProgress);
            } else {
                node.submitModel(this.doubleRightModel, f, stack, rendertype, render.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, render.breakProgress);
            }
        } else {
            node.submitModel(this.singleModel, f, stack, rendertype, render.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, render.breakProgress);
        }

        stack.popPose();

        if(render.isLocked) {
            if(render.type != ChestType.SINGLE) {
                if(render.type == ChestType.LEFT) {
                    renderItem(render, new double[]{0.0D, 0.2D, 0.945D}, stack, node);
                } else {
                    renderItem(render, new double[]{1, 0.2D, 0.945D}, stack, node);
                }
            } else {
                renderItem(render, new double[]{0.5D, 0.2D, 0.945D}, stack, node);
            }
        }
    }

    @Override
    public net.minecraft.world.phys.@NotNull AABB getRenderBoundingBox(T blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return net.minecraft.world.phys.AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }

    private void renderItem(JChestRenderState state, double[] translation, PoseStack matrixStack, SubmitNodeCollector collector) {
        ItemStackRenderState item = state.item;
        if (!item.isEmpty()) {
            matrixStack.pushPose();
            float f = state.facingYAngle;
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            matrixStack.mulPose(Axis.YP.rotationDegrees(-f));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            matrixStack.translate(translation[0], translation[1], translation[2]);
            float scale = 1.0F;
            matrixStack.scale(scale, scale, scale + 0.15F);
            item.submit(matrixStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            matrixStack.popPose();
        }
    }
}