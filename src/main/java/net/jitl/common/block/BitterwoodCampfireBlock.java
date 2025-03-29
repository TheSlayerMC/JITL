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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
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
    protected InteractionResult useItemOn(ItemStack p_316347_, BlockState p_51274_, Level p_51275_, BlockPos p_51276_, Player p_51277_, InteractionHand p_51278_, BlockHitResult p_51279_) {
        if (p_51275_.getBlockEntity(p_51276_) instanceof CampfireBlockEntity campfireblockentity) {
            ItemStack itemstack = p_51277_.getItemInHand(p_51278_);
            if (p_51275_.recipeAccess().propertySet(RecipePropertySet.CAMPFIRE_INPUT).test(itemstack)) {
                if (p_51275_ instanceof ServerLevel serverlevel && campfireblockentity.placeFood(serverlevel, p_51277_, itemstack)) {
                    p_51277_.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return InteractionResult.SUCCESS_SERVER;
                }

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

//    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
//        if(!pState.is(pNewState.getBlock())) {
//            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
//            if(blockentity instanceof BitterwoodCampfireBlockEntity)
//                Containers.dropContents(pLevel, pPos, ((BitterwoodCampfireBlockEntity)blockentity).getItems());
//
//            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
//        }
//    }

    public static void dowse(@Nullable Entity entity, LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) {
            for (int i = 0; i < 20; i++) {
                makeParticles((Level)level, pos, state.getValue(SIGNAL_FIRE), true);
            }
        }
        level.gameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            boolean flag = pState.getValue(LIT);
            if (flag) {
                if (!pLevel.isClientSide())
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_152755_, BlockState p_152756_, BlockEntityType<T> p_152757_) {
        if (p_152755_ instanceof ServerLevel serverlevel) {
            if (p_152756_.getValue(LIT)) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> cachedcheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
                return createTickerHelper(
                        p_152757_,
                        JBlockEntities.BITTERWOOD_CAMPFIRE.get(),
                        (p_379259_, p_379260_, p_379261_, p_379262_) -> BitterwoodCampfireBlockEntity.cookTick(serverlevel, p_379260_, p_379261_, p_379262_, cachedcheck)
                );
            } else {
                return createTickerHelper(p_152757_, JBlockEntities.BITTERWOOD_CAMPFIRE.get(), BitterwoodCampfireBlockEntity::cooldownTick);
            }
        } else {
            return p_152756_.getValue(LIT) ? createTickerHelper(p_152757_, JBlockEntities.BITTERWOOD_CAMPFIRE.get(), BitterwoodCampfireBlockEntity::particleTick) : null;
        }
    }
}
