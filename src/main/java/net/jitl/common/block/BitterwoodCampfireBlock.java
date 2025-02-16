package net.jitl.common.block;

import net.jitl.common.block.entity.BitterwoodCampfireBlockEntity;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Optional;

public class BitterwoodCampfireBlock extends CampfireBlock {

    public BitterwoodCampfireBlock() {
        super(true, 1, JBlockProperties.CAMPFIRE);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if(blockentity instanceof BitterwoodCampfireBlockEntity campfireblockentity) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if (!pLevel.isClientSide) {
                Optional<RecipeHolder<CampfireCookingRecipe>> optional = campfireblockentity.getCookableRecipe(itemstack);
                if (optional.isPresent()) {
                    if (campfireblockentity.placeFood(pPlayer, pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().value().cookingTime())) {
                        pPlayer.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                        return InteractionResult.SUCCESS_SERVER;
                    }
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if(blockentity instanceof BitterwoodCampfireBlockEntity)
                Containers.dropContents(pLevel, pPos, ((BitterwoodCampfireBlockEntity)blockentity).getItems());

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public static void dowse(@Nullable Entity pEntity, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if(pLevel.isClientSide()) {
            for(int i = 0; i < 20; ++i) {
                makeParticles((Level)pLevel, pPos, pState.getValue(SIGNAL_FIRE), true);
            }
        }
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if(blockentity instanceof BitterwoodCampfireBlockEntity)
            ((BitterwoodCampfireBlockEntity)blockentity).dowse();

        pLevel.gameEvent(pEntity, GameEvent.BLOCK_CHANGE, pPos);
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if(!pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            boolean flag = pState.getValue(LIT);
            if(flag) {
                if(!pLevel.isClientSide())
                    pLevel.playSound(null, pPos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);

                dowse(null, pLevel, pPos, pState);
            }
            pLevel.setBlock(pPos, pState.setValue(WATERLOGGED, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)), 3);
            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BitterwoodCampfireBlockEntity(pPos, pState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel instanceof ServerLevel serverlevel) {
            return pState.getValue(LIT) ? createTickerHelper(pBlockEntityType, JBlockEntities.BITTERWOOD_CAMPFIRE.get(), (p, s, e, r) -> BitterwoodCampfireBlockEntity.cookTick(serverlevel, s, e, r)) : createTickerHelper(pBlockEntityType, JBlockEntities.BITTERWOOD_CAMPFIRE.get(), BitterwoodCampfireBlockEntity::cooldownTick);
        }else {
            return pState.getValue(LIT) ? createTickerHelper(pBlockEntityType, JBlockEntities.BITTERWOOD_CAMPFIRE.get(), BitterwoodCampfireBlockEntity::particleTick) : null;
        }
    }
}
