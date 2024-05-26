package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

public class AirrootCropBlock extends JCropBlock {

    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    public AirrootCropBlock() {
        super(DimensionCrops.CLOUDIA);
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected int getBonemealAgeIncrease(@NotNull Level pLevel) {
        return super.getBonemealAgeIncrease(pLevel) / MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return JItems.AIRROOT_SEEDS.get();
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        int i = this.getAge(pState) + this.getBonemealAgeIncrease(pLevel);
        int j = this.getMaxAge();
        if(i > j) {
            i = j;
        }

        int age = this.getAge(pState);
        if(age == 3) {
            pLevel.setBlock(pPos, JBlocks.AIRROOT_MELON.get().defaultBlockState(), 2);
        } else {
            pLevel.setBlock(pPos, this.getStateForAge(i), 2);
        }
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            int i = this.getAge(pState);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(this, pLevel, pPos);
                if (CommonHooks.canCropGrow(pLevel, pPos, pState, pRandom.nextInt((int)(25.0F / f) + 1) == 0)) {
                    if(i == 3) {
                        pLevel.setBlock(pPos, JBlocks.AIRROOT_MELON.get().defaultBlockState(), 2);
                    } else {
                        pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2);
                    }
                    CommonHooks.fireCropGrowPost(pLevel, pPos, pState);
                }
            }
        }

    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
