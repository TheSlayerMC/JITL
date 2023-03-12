package net.jitl.common.block.portal;

import net.jitl.common.world.dimension.DepthsTeleporter;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class DepthsPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);

    public DepthsPortalBlock() {
        super(JBlockProperties.PORTAL);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public ItemStack getCloneItemStack(@NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if(!entity.level.isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                teleport(entity);
            }
        }
    }

    public void teleport(Entity entity) {
        Level entityWorld = entity.level;
        MinecraftServer minecraftserver = entityWorld.getServer();
        if(minecraftserver != null) {
            ResourceKey<Level> destination = entity.level.dimension() == Dimensions.DEPTHS ? Level.OVERWORLD : Dimensions.DEPTHS;
            ServerLevel destinationWorld = minecraftserver.getLevel(destination);
            if(destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                entity.setPortalCooldown();
                entity.changeDimension(destinationWorld, new DepthsTeleporter(destinationWorld, this, JBlocks.DEPTHS_PORTAL_FRAME.get()));
            }
        }
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState pState, @NotNull Fluid pFluid) {
        return false;
    }
}