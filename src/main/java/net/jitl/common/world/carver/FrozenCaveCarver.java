package net.jitl.common.world.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;
import java.util.function.Function;

public class FrozenCaveCarver extends CaveWorldCarver {

    public FrozenCaveCarver(Codec<CaveCarverConfiguration> codec_) {
        super(codec_);
        this.liquids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
    }

    @Override
    protected int getCaveBound() {
        return 15;
    }

    @Override
    protected float getThickness(RandomSource random) {
        return (random.nextFloat() * 3.0F + random.nextFloat()) * 3.0F;
    }

    @Override
    protected double getYScale() {
        return 2.0D;
    }

    @Override
    protected boolean carveBlock(CarvingContext carv, CaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> func,
                                 CarvingMask mask, BlockPos.MutableBlockPos pos, BlockPos.MutableBlockPos pos2, Aquifer a, MutableBoolean mut) {
        if (this.canReplaceBlock(config, chunk.getBlockState(pos))) {
            BlockState blockstate;
            if (pos.getY() <= carv.getMinGenY() + 31) {
                blockstate = LAVA.createLegacyBlock();
            } else {
                blockstate = CAVE_AIR;
            }
            chunk.setBlockState(pos, blockstate, false);
            return true;
        } else {
            return false;
        }
    }
}