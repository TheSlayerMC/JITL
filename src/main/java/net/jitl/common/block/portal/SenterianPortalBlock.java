package net.jitl.common.block.portal;

import net.jitl.common.capability.player.Portal;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.dimension.SenterianTeleporter;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SenterianPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);

    public SenterianPortalBlock() {
        super(JBlockProperties.PORTAL);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(@NotNull BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double)pPos.getX() + pRandom.nextDouble();
        double d1 = (double)pPos.getY() + 0.8D;
        double d2 = (double)pPos.getZ() + pRandom.nextDouble();
        pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if(!entity.level().isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                if(entity instanceof Player player) {
                    Portal portal = player.getData(JDataAttachments.PORTAL_OVERLAY);
                    portal.setInPortal(this, true);
                    int cooldownTime = portal.getPortalTimer();
                    if(cooldownTime >= player.getPortalWaitTime()) {
                        teleport(entity);
                        portal.setPortalTimer(0);
                    }
                } else {
                    teleport(entity);
                }
            }
        }
    }

    public void teleport(Entity entity) {
        Level entityWorld = entity.level();
        MinecraftServer minecraftserver = entityWorld.getServer();
        if(minecraftserver != null) {
            ResourceKey<Level> destination = entity.level().dimension() == Dimensions.SENTERIAN ? Level.OVERWORLD : Dimensions.SENTERIAN;
            ServerLevel destinationWorld = minecraftserver.getLevel(destination);
            ResourceKey<PoiType> poi = Dimensions.SENTERIAN_PORTAL.getKey();

            if(destinationWorld != null && !entity.isPassenger()) {
                entity.setPortalCooldown();
                entity.changeDimension(destinationWorld, new SenterianTeleporter(destinationWorld, this, JBlocks.SENTERIAN_PORTAL_FRAME.get(), poi, destination));
            }
        }
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState pState, @NotNull Fluid pFluid) {
        return false;
    }
}