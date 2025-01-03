package net.jitl.common.block;

import net.jitl.core.init.internal.JBlockProperties;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class AncientCatalystBlock extends Block {

    public AncientCatalystBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public InteractionResult useItemOn(ItemStack pStack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        Random random = new Random();
        int check_radius = 4;
        int totalSockets = 0;

        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                final BlockPos pos1 = pos.offset(x, 0, z);
                final Block block = worldIn.getBlockState(pos1).getBlock();
                if(block == JBlocks.ANCIENT_SOCKET.get() && worldIn.getBlockState(pos1).getValue(AncientSocketBlock.INSERTED)) {
                    totalSockets++;
                }
            }
        }
        if(totalSockets >= 4) {
            if(player.getMainHandItem().is(JItems.ANCIENT_EYE_OF_OPENING.get())) {
                worldIn.removeBlock(pos, false);
                for (int i = 0; i < 50; i++)
                    worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(),
                            random.nextGaussian() * 0.05D, 0.15D, random.nextGaussian() * 0.05D);
                worldIn.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 0.2F);
                player.getMainHandItem().shrink(1);
            }
        }
        return InteractionResult.SUCCESS_SERVER;
    }
}
