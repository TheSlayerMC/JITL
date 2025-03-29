package net.jitl.common.block.portal;

import net.jitl.common.block.portal.logic.CorbaPortal;
import net.jitl.common.block.portal.logic.PortalCoordinatesContainer;
import net.jitl.common.capability.player.Portal;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JDataAttachments;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CorbaPortalBlock extends Block implements CorbaPortal {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);

    public CorbaPortalBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public Block getPortalBlock() {
        return this;
    }

    @Override
    public Block getPortalFrame() {
        return JBlocks.CORBA_PORTAL_FRAME.get();
    }

    @Override
    public void animateTick(@NotNull BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double)pPos.getX() + pRandom.nextDouble();
        double d1 = (double)pPos.getY() + 0.8D;
        double d2 = (double)pPos.getZ() + pRandom.nextDouble();
        pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return ItemStack.EMPTY;
    }


    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity, InsideBlockEffectApplier effect) {
        if(entity.canUsePortal(false)) {
            if(entity instanceof Player player) {
                Portal portal = player.getData(JDataAttachments.PORTAL_OVERLAY);
                portal.setInPortal(this, true);
                int cooldownTime = portal.getPortalTimer();
                assert player.portalProcess != null;
                //if(cooldownTime >= player.portalProcess.getPortalTime()) {
                entity.setAsInsidePortal(this, pos);
                //    portal.setPortalTimer(0);
                //}
            } else {
                entity.setAsInsidePortal(this, pos);
            }
        }
    }

    @Override
    public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        if (!(entity instanceof ServerPlayer))
            return null;

        final ResourceKey<Level> currentDimension = level.dimension();
        final ResourceKey<Level> portalTargetDimension = Dimensions.CORBA;
        final MinecraftServer server = level.getServer();
        final Optional<PortalCoordinatesContainer> existingLink = Optional.ofNullable(entity instanceof ServerPlayer pl ? pl.getData(JDataAttachments.PORTAL_OVERLAY).getPortalReturnLocation(currentDimension) : null);
        ServerLevel targetLevel = existingLink
                .map(link -> server.getLevel(currentDimension != portalTargetDimension ? portalTargetDimension : link.fromDim()))
                .orElseGet(() -> server.getLevel(currentDimension == portalTargetDimension ? Level.OVERWORLD : portalTargetDimension));

        if (targetLevel == null) {
            if (currentDimension == Level.OVERWORLD)
                return null;

            targetLevel = server.overworld();
        }

        return CorbaPortal.getTransitionForLevel(targetLevel, entity, Optional.of(pos), CorbaPortal.makeSafeCoords(level, targetLevel, entity.position()), this, existingLink);
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState pState, @NotNull Fluid pFluid) {
        return false;
    }
}