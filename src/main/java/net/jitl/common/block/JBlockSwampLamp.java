package net.jitl.common.block;

import net.jitl.common.block.base.JBlock;
import net.jitl.common.entity.corba.SwampFly;
import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JBlockSwampLamp extends JBlock {

    protected static final VoxelShape BOTTOM = Block.box(5D, 0D, 5D, 11D, 6D, 11D);
    protected static final VoxelShape MID = Block.box(6D, 6D, 6D, 10D, 9D, 10D);
    protected static final VoxelShape TOP = Block.box(7D, 9D, 7D, 9D, 11D, 9D);

    public JBlockSwampLamp() {
        super(JBlockProperties.BOTTLE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(BOTTOM, MID, TOP);
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        float x = pos.getX() + 0.3F + (random.nextFloat() / 3);
        float y = pos.getY() + 0.1F + (random.nextFloat() / 4);
        float z = pos.getZ() + 0.3F + (random.nextFloat() / 3);
        if(random.nextInt(3) == 0) {
            level.addParticle(JParticleManager.SWAMP_FLY.get(), x, y, z, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    protected void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        SwampFly swampFly = JEntities.SWAMP_FLY_TYPE.get().create(level);
        if(swampFly != null) {
            swampFly.moveTo((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, 0.0F, 0.0F);
            level.addFreshEntity(swampFly);
        }
    }
}
