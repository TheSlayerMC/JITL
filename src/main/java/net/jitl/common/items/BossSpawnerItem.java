package net.jitl.common.items;

import net.jitl.client.ChatUtils;
import net.jitl.common.block.OkolooPedestalBlock;
import net.jitl.common.entity.boss.*;
import net.jitl.common.items.base.JItem;
import net.jitl.common.world.dimension.Dimensions;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
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

public class BossSpawnerItem extends JItem {

    public BossSpawnerItem() {
        super(JItems.itemProps().stacksTo(1));
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
            } else {
                if(!level.isClientSide()) {
                    ServerLevel serverLevel = (ServerLevel)level;

                    //NETHER
                    bindSpawner(player, serverLevel, pos, JItems.WITHERING_BEAST_ORB.get(), "jitl.boss_spawn.withering_beast", Dimensions.NETHER, "The Nether", new WitheringBeast(JEntities.WITHERING_BEAST_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.CALCIA_ORB.get(), "jitl.boss_spawn.calcia", Dimensions.NETHER, "The Nether", new Calcia(JEntities.CALCIA_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.SOUL_WATCHER_ORB.get(), "jitl.boss_spawn.soul_watcher", Dimensions.NETHER, "The Nether", new SoulWatcher(JEntities.SOUL_WATCHER_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.BLAZIER_ORB.get(), "jitl.boss_spawn.blazier", Dimensions.NETHER, "The Nether", new Blazier(JEntities.BLAZIER_TYPE.get(), level));

                    //EUCA
                    bindSpawner(player, serverLevel, pos, JItems.EUDOR_CROWN.get(), "jitl.boss_spawn.eudor", Dimensions.EUCA, "Euca", new Eudor(JEntities.EUDOR_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.CORALLATOR_ORB.get(), "jitl.boss_spawn.corallator", Dimensions.EUCA, "Euca", new Corallator(JEntities.CORALLATOR_TYPE.get(), level));

                    //DEPTHS
                    bindSpawner(player, serverLevel, pos, JItems.THUNDER_BIRD_ORB.get(), "jitl.boss_spawn.thunder_bird", Dimensions.DEPTHS, "The Depths", new ThunderBird(JEntities.THUNDER_BIRD_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.SCALE_ORB.get(), "jitl.boss_spawn.scale", Dimensions.DEPTHS, "The Depths", new Scale(JEntities.SCALE_TYPE.get(), level));

                    //CORBA
                    bindSpawner(player, serverLevel, pos, JItems.LOGGER_ORB.get(), "jitl.boss_spawn.logger", Dimensions.CORBA, "Corba", new Logger(JEntities.LOGGER_TYPE.get(), level));
                    bindSpawner(player, serverLevel, pos, JItems.SENTRY_KING_ORB.get(), "jitl.boss_spawn.sentry_king", Dimensions.CORBA, "Corba", new SentryKing(JEntities.SENTRY_KING_TYPE.get(), level));

                    //TERRANIA
                    bindSpawner(player, serverLevel, pos, JItems.ENCHANTED_TERRASTAR.get(), "jitl.boss_spawn.terranian_protector", Dimensions.TERRANIA, "Terrania", new TerranianProtector(JEntities.TERRANIAN_PROTECTOR_TYPE.get(), level));

                    //CLOUDIA
                    bindSpawner(player, serverLevel, pos, JItems.MYSTERIOUS_DISK.get(), "jitl.boss_spawn.sky_stalker", Dimensions.CLOUDIA, "Cloudia", new SkyStalker(JEntities.SKY_STALKER_TYPE.get(), level));
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    public void bindSpawner(Player player, ServerLevel level, BlockPos pos, Item spawner, String langKey, ResourceKey<Level> dimension, String dimensionName, LivingEntity boss) {
        if(player.getMainHandItem().getItem() == spawner && boss != null) {
            if(level.getLevel() == level.getServer().getLevel(dimension)) {
                boss.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                level.addFreshEntity(boss);
                if(!player.isCreative())
                    player.getMainHandItem().shrink(1);
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.GOLD, langKey);
            } else {
                ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.RED, "jitl.boss_spawn.fail", boss.getName(), dimensionName);
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable TooltipContext con, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        if(this == JItems.BROKEN_OKOLOO_CLUB.get()) {
            pTooltip.add(Component.translatable("jitl.tooltip.okoloo"));
        }

        pTooltip.add(Component.translatable("jitl.tooltip.spawn"));
    }
}
