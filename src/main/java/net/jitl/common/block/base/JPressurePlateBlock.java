package net.jitl.common.block.base;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class JPressurePlateBlock extends JBasePressurePlateBlock {
    public static final MapCodec<JPressurePlateBlock> CODEC = RecordCodecBuilder.mapCodec((p_432675_) -> p_432675_.group(BlockSetType.CODEC.fieldOf("block_set_type").forGetter((p_304917_) -> p_304917_.type), propertiesCodec()).apply(p_432675_, JPressurePlateBlock::new));
    public static final BooleanProperty POWERED;

    public MapCodec<JPressurePlateBlock> codec() {
        return CODEC;
    }

    public JPressurePlateBlock(BlockSetType type, BlockBehaviour.Properties properties) {
        super(properties, type);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(POWERED, false));
    }

    protected int getSignalForState(BlockState state) {
        return (Boolean)state.getValue(POWERED) ? 15 : 0;
    }

    protected BlockState setSignalForState(BlockState state, int strength) {
        return (BlockState)state.setValue(POWERED, strength > 0);
    }

    protected int getSignalStrength(Level level, BlockPos pos) {
        Class var10000;
        switch (this.type.pressurePlateSensitivity()) {
            case EVERYTHING -> var10000 = Entity.class;
            case MOBS -> var10000 = LivingEntity.class;
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        Class<? extends Entity> oclass = var10000;
        return getEntityCount(level, TOUCH_AABB.move(pos), oclass) > 0 ? 15 : 0;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(new Property[]{POWERED});
    }

    static {
        POWERED = BlockStateProperties.POWERED;
    }
}
