package net.jitl.common.block;

import com.mojang.serialization.MapCodec;
import net.jitl.common.block.entity.SummoningTableTile;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SummoningTableBlock extends BaseEntityBlock {

    public static final MapCodec<SummoningTableBlock> CODEC = simpleCodec(SummoningTableBlock::new);

    public static final BooleanProperty IS_ACTIVE = BooleanProperty.create("is_active");

    public static BlockPattern STRUCTURE_PATTERN;

    public SummoningTableBlock(BlockBehaviour.Properties p) {
        super(p);
        getOrCreateStructurePattern();
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_ACTIVE, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(IS_ACTIVE, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return JBlockEntities.SUMMONING_TABLE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> entity) {
        return level.isClientSide ? null : createTickerHelper(entity, JBlockEntities.SUMMONING_TABLE.get(), SummoningTableTile::serverTick);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if(blockentity instanceof SummoningTableTile) {
                if(STRUCTURE_PATTERN.find(level, pos.below(1).north(2).west(2)) != null) {
                    player.openMenu((SummoningTableTile) blockentity);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, @NotNull BlockPos pos, BlockState blockState, boolean b) {
        if (!state.is(blockState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof SummoningTableTile) {
                if (level instanceof ServerLevel) Containers.dropContents(level, pos, (SummoningTableTile)blockentity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, blockState, b);
        }
    }

    public static void getOrCreateStructurePattern() {
        if(STRUCTURE_PATTERN == null) {
            STRUCTURE_PATTERN = BlockPatternBuilder.start()
                    .aisle(
                            "_kek_",
                            "l___l",
                            "l___l",
                            "cs_sc",
                            "_____"
                    ).aisle(
                            "k_k_k",
                            "_____",
                            "_____",
                            "ss_ss",
                            "_sss_"

                    ).aisle(
                            "ekkke",
                            "_____",
                            "_____",
                            "__o__",
                            "_sss_"

                    ).aisle(
                            "k_k_k",
                            "_____",
                            "_____",
                            "ss_ss",
                            "_sss_"

                    )
                    .aisle(
                            "_kek_",
                            "l___l",
                            "l___l",
                            "cs_sc",
                            "_____"
                    )
                    .where('_', BlockInWorld.hasState(BlockStatePredicate.ANY))
                    .where('k', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.BLOOD_ROCK.get())))
                    .where('e', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.BLOOD_RUNE.get())))
                    .where('l', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.BLOOD_PILLAR.get())))
                    .where('c', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.CARVED_BLOOD_ROCK.get())))
                    .where('s', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.BLOOD_BRICKS.get())))
                    .where('o', BlockInWorld.hasState(BlockStatePredicate.forBlock(JBlocks.OBELISK.get())))
                    .build();
        }
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
}
