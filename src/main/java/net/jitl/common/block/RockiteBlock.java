package net.jitl.common.block;

import net.jitl.common.entity.boss.RockiteSmasher;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RockiteBlock extends BaseEntityBlock {

    private static final VoxelShape BASE = Block.box(-16.0D, 0.0D, -16.0D, 32.0D, 40.0D, 16.0D);

    private static final VoxelShape SHAPE = Shapes.or(BASE);

    public RockiteBlock() {
        super(JBlockProperties.ROCKITE_SPAWNER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return JBlockEntities.ROCKITE.get().create(pos, state);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        for(int i = 0; i < 10; i++) {
            if (pRandom.nextInt(8) == 0) {
                double d0 = (double) pPos.getX() + pRandom.nextFloat();
                double d1 = (double) pPos.getY() + 1.0D;
                double d2 = (double) pPos.getZ() + pRandom.nextFloat();
                pLevel.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, pState), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(player != null) {
            if(player.getMainHandItem().getItem() instanceof PickaxeItem) {
                level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1F, Explosion.BlockInteraction.NONE);
                RockiteSmasher entity = new RockiteSmasher(JEntities.ROCKITE_SMASHER_TYPE.get(), level);
                entity.setPos(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
                level.addFreshEntity(entity);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
        return true;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}