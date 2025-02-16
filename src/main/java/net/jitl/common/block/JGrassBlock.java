package net.jitl.common.block;

import net.jitl.common.items.base.MultitoolItem;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;

public class JGrassBlock extends Block {//implements BonemealableBlock {

    public JGrassBlock() {
        super(JBlockProperties.GRASS);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        Item itemstack = pPlayer.getItemInHand(pHand).getItem();
        Block dirt = getFarmlandFromGrass();
        if(itemstack instanceof HoeItem || itemstack instanceof MultitoolItem) {
            if(dirt != null) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(!pLevel.isClientSide) {
                    pLevel.setBlock(pPos, dirt.defaultBlockState(), 2);
                    if(!pPlayer.isCreative())
                        pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, EquipmentSlot.MAINHAND);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public Block getFarmlandFromGrass() {
        Block farmland = null;
        if(this == JBlocks.GOLDITE_GRASS.get() || this == JBlocks.EUCA_GOLD_GRASS.get())
            farmland = JBlocks.GOLDITE_FARMLAND.get();

        if(this == JBlocks.GRASSY_PERMAFROST.get())
            farmland = JBlocks.PERMAFROST_FARMLAND.get();

        if(this == JBlocks.DEPTHS_GRASS.get())
            farmland = JBlocks.DEPTHS_FARMLAND.get();

        if(this == JBlocks.CORBA_GRASS.get())
            farmland = JBlocks.CORBA_FARMLAND.get();

        if(this == JBlocks.TERRANIAN_GRASS.get())
            farmland = JBlocks.TERRANIAN_GRASS.get();

        if(this == JBlocks.CLOUDIA_GRASS.get())
            farmland = JBlocks.CLOUDIA_GRASS.get();

        return farmland;
    }

    public Block getDirtFromGrass() {
        Block dirt = null;
        if(this == JBlocks.GOLDITE_GRASS.get() || this == JBlocks.EUCA_GOLD_GRASS.get())
            dirt = JBlocks.GOLDITE_DIRT.get();

        if(this == JBlocks.GRASSY_PERMAFROST.get())
            dirt = JBlocks.CRUMBLED_PERMAFROST.get();

        if(this == JBlocks.CHARRED_GRASS.get())
            dirt = JBlocks.RUBBLE.get();

        if(this == JBlocks.DEPTHS_GRASS.get())
            dirt = JBlocks.DEPTHS_DIRT.get();

        if(this == JBlocks.CORBA_GRASS.get())
            dirt = JBlocks.CORBA_DIRT.get();

        if(this == JBlocks.TERRANIAN_GRASS.get())
            dirt = JBlocks.TERRANIAN_DIRT.get();

        if(this == JBlocks.CLOUDIA_GRASS.get())
            dirt = JBlocks.CLOUDIA_DIRT.get();

        return dirt;
    }

   /* @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = this.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = pLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);


        label46:
        for(int i = 0; i < 128; i++) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; j++) {
                blockpos1 = blockpos1.offset(pRandom.nextInt(3) - 1, (pRandom.nextInt(3) - 1) * pRandom.nextInt(3) / 2, pRandom.nextInt(3) - 1);
                if(!pLevel.getBlockState(blockpos1.below()).is(this) || pLevel.getBlockState(blockpos1).isCollisionShapeFullBlock(pLevel, blockpos1))
                    continue label46;
            }
            BlockState blockstate1 = pLevel.getBlockState(blockpos1);
            if(blockstate1.is(blockstate.getBlock()) && pRandom.nextInt(10) == 0)
                ((BonemealableBlock) blockstate.getBlock()).performBonemeal(pLevel, pRandom, blockpos1, blockstate1);

            if(blockstate1.isAir()) {
                Holder<PlacedFeature> holder;
                if(pRandom.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = pLevel.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
                    if(list.isEmpty())
                        continue;
                    holder = ((RandomPatchConfiguration) list.get(0).config()).feature();
                } else {
                    if(optional.isEmpty()) {
                        continue;
                    }
                    holder = optional.get();
                }
                holder.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, blockpos1);
            }
        }

    }*/

    private static boolean canBeGrass(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        int i = LightEngine.getLightBlockInto(pState, blockstate, Direction.UP, blockstate.getLightBlock());
        return i < 15;
    }

    private static boolean canPropagate(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return canBeGrass(pState, pLevel, pPos) && !pLevel.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canBeGrass(pState, pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1)) return;
            pLevel.setBlockAndUpdate(pPos, this.getDirtFromGrass().defaultBlockState());
        } else {
            if (!pLevel.isAreaLoaded(pPos, 3)) return;
            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                    if (pLevel.getBlockState(blockpos).is(this.getDirtFromGrass()) && canPropagate(blockstate, pLevel, blockpos)) {
                        pLevel.setBlock(blockpos, blockstate, 4);
                    }
                }
            }
        }
    }
}