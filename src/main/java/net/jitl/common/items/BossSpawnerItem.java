package net.jitl.common.items;

import net.jitl.common.block.OkolooPedestalBlock;
import net.jitl.common.entity.boss.*;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class BossSpawnerItem extends Item {

    private LivingEntity entity = null;

    public BossSpawnerItem() {
        super(JItems.itemProps());
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState block = level.getBlockState(pos);
        Player player = context.getPlayer();
        if(player != null) {
            if(this == JItems.BROKEN_OKOLOO_CLUB.get()) {
                if(block == JBlocks.OKOLOO_PEDESTAL.get().defaultBlockState()) {
                    if(!block.getValue(OkolooPedestalBlock.HAS_CLUB)) {
                        BlockState filled_state = block.setValue(OkolooPedestalBlock.HAS_CLUB, Boolean.TRUE);
                        level.setBlock(pos, filled_state, 2);
                        if(!player.isCreative())
                            player.getMainHandItem().shrink(1);
                    }
                }
            }

            if(this == JItems.WITHERING_BEAST_ORB.get()) {
                entity = new WitheringBeast(JEntities.WITHERING_BEAST_TYPE.get(), level);
            }

            if(this == JItems.CALCIA_ORB.get()) {
                entity = new Calcia(JEntities.CALCIA_TYPE.get(), level);
            }

            if(this == JItems.SOUL_WATCHER_ORB.get()) {
                entity = new SoulWatcher(JEntities.SOUL_WATCHER_TYPE.get(), level);
            }

            if(this == JItems.EUDOR_CROWN.get()) {
                entity = new Eudor(JEntities.EUDOR_TYPE.get(), level);
            }

            if(this == JItems.CORALLATOR_ORB.get()) {
                entity = new Corallator(JEntities.CORALLATOR_TYPE.get(), level);
            }

            if(this == JItems.BLAZIER_ORB.get()) {
                entity = new Blazier(JEntities.BLAZIER_TYPE.get(), level);
            }

            if(this == JItems.THUNDER_BIRD_ORB.get()) {
                entity = new ThunderBird(JEntities.THUNDER_BIRD_TYPE.get(), level);
            }

            if(this == JItems.LOGGER_ORB.get()) {
                entity = new Logger(JEntities.LOGGER_TYPE.get(), level);
            }

            if(this == JItems.SENTRY_KING_ORB.get()) {
                entity = new SentryKing(JEntities.SENTRY_KING_TYPE.get(), level);
            }

            if(this == JItems.MYSTERIOUS_DISK.get()) {
                entity = new SkyStalker(JEntities.SKY_STALKER_TYPE.get(), level);
            }

            if(this == JItems.ENCHANTED_TERRASTAR.get()) {
                entity = new TerranianProtector(JEntities.TERRANIAN_PROTECTOR_TYPE.get(), level);
            }

            if(entity != null) {
                entity.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                level.addFreshEntity(entity);
                if(!player.isCreative())
                    player.getMainHandItem().shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level level, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        if(this == JItems.BROKEN_OKOLOO_CLUB.get()) {
            pTooltip.add(Component.translatable("jitl.tooltip.okoloo"));
        }

        pTooltip.add(Component.translatable("jitl.tooltip.spawn"));
    }
}
