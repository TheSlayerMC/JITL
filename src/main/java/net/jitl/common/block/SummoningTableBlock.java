package net.jitl.common.block;

import com.mojang.serialization.MapCodec;
import net.jitl.common.block.entity.SummoningTableTile;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    public static final BooleanProperty SUMMON = BooleanProperty.create("summon");

    public static BlockPattern STRUCTURE_PATTERN;

    public SummoningTableBlock(BlockBehaviour.Properties p) {
        super(p);
        getOrCreateStructurePattern();
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_ACTIVE, Boolean.FALSE));
        this.registerDefaultState(this.stateDefinition.any().setValue(SUMMON, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(IS_ACTIVE, Boolean.FALSE).setValue(SUMMON, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_ACTIVE);
        builder.add(SUMMON);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return JBlockEntities.SUMMONING_TABLE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> entity) {
        return level.isClientSide() ? null : createTickerHelper(entity, JBlockEntities.SUMMONING_TABLE.get(), SummoningTableTile::serverTick);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if(blockentity instanceof SummoningTableTile) {
                if(isUsable(level, pos) || player.isCreative()) {
                    player.openMenu((SummoningTableTile) blockentity);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pos, RandomSource pRandom) {
        for(int i = 0; i < 10; i++) {
            if (pRandom.nextInt(5) == 0) {
                double d0 = (double) pos.getX() + pRandom.nextFloat();
                double d1 = (double) pos.getY() + 1.0D;
                double d2 = (double) pos.getZ() + pRandom.nextFloat();
                if(isUsable(pLevel, pos)) {
                    pLevel.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    if (pState.getValue(IS_ACTIVE)) {
                        pLevel.addParticle(ParticleTypes.ENCHANT, d0, d1 + 0.2D, d2, 0.0D, 0.0D, 0.0D);
                        pLevel.addParticle(ParticleTypes.CRIT, d0, d1 + 0.2D, d2, 0.0D, 0.0D, 0.0D);
                    }
                    if(getIsSummoning(pState)) {
                        pLevel.addParticle(ParticleTypes.CLOUD,
                                pos.getX() - Mth.nextDouble(pRandom, -0.45D, 0.75D),
                                pos.getY() + Mth.nextDouble(pRandom, 0.5D, 2.0D),
                                pos.getZ() - Mth.nextDouble(pRandom, -0.45D, 0.75D),
                                pRandom.nextGaussian() * 0.05D, 0.15D, pRandom.nextGaussian() * 0.05D);
                    }
                }
            }
        }
    }

    public boolean getIsSummoning(BlockState state) {
        return state.getValue(SUMMON);
    }

    public boolean isUsable(Level level, BlockPos pos) {
        return STRUCTURE_PATTERN.find(level, pos.below(1).north(2).west(2)) != null || !JCommonConfig.NEED_SUMMONING_STRUCTURE.get();
    }

//    @Override
//    public void onRemove(BlockState state, Level level, @NotNull BlockPos pos, BlockState blockState, boolean b) {
//        if (!state.is(blockState.getBlock())) {
//            BlockEntity blockentity = level.getBlockEntity(pos);
//            if (blockentity instanceof SummoningTableTile) {
//                if (level instanceof ServerLevel) Containers.dropContents(level, pos, (SummoningTableTile)blockentity);
//                level.updateNeighbourForOutputSignal(pos, this);
//            }
//            super.onRemove(state, level, pos, blockState, b);
//        }
//    }

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
