package net.jitl.common.block.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JTransparentBlock extends HalfTransparentBlock {

    public static final MapCodec<JTransparentBlock> CODEC = simpleCodec(JTransparentBlock::new);

    public JTransparentBlock(BlockBehaviour.Properties p_309186_) {
        super(p_309186_);
    }

    @Override
    protected MapCodec<? extends JTransparentBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull VoxelShape getVisualShape(@NotNull BlockState d, @NotNull BlockGetter dh, @NotNull BlockPos p, @NotNull CollisionContext c) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(@NotNull BlockState d, @NotNull BlockGetter g, @NotNull BlockPos p) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }
}
