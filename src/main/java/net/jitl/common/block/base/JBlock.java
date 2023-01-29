package net.jitl.common.block.base;

import net.jitl.core.init.internal.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class JBlock extends Block {

    private final Properties props;

    public JBlock(Properties properties) {
        super(properties);
        this.props = properties;
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader level, BlockPos pos, Direction direction) {
        return props == JBlockProperties.FIRE_STONE || props == JBlockProperties.FIRE_SAND || props == JBlockProperties.FIRE_DIRT;
    }
}
