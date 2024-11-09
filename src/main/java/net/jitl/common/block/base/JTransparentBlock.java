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
    public VoxelShape getVisualShape(BlockState p_309057_, BlockGetter p_308936_, BlockPos p_308956_, CollisionContext p_309006_) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState p_320652_) {
        return true;
    }
}
