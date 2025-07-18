package net.jitl.common.block;

import net.jitl.client.util.ChatUtils;
import net.jitl.common.entity.boss.Okoloo;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class OkolooPedestalBlock extends Block {

    public static final BooleanProperty HAS_CLUB = BooleanProperty.create("has_club");

    public OkolooPedestalBlock(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_CLUB, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HAS_CLUB, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_CLUB);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack pStack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if(state.getValue(HAS_CLUB)) {
            if(!worldIn.isClientSide()) {
                Okoloo okoloo = new Okoloo(JEntities.OKOLOO_TYPE.get(), worldIn);
                okoloo.setPos(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
                worldIn.addFreshEntity(okoloo);
                BlockState empty_state = state.setValue(OkolooPedestalBlock.HAS_CLUB, Boolean.FALSE);
                worldIn.setBlock(pos, empty_state, 2);
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.GOLD, "jitl.boss_spawn.okoloo");
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }
}
