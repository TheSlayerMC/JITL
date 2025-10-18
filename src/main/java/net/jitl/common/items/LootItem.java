package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class LootItem extends JItem {

    public LootTier tier;

    public LootItem(Properties props, LootTier tier, Rarity r) {
        super(props.rarity(r).stacksTo(4));
        this.tier = tier;
    }

    @Override
    public @NotNull InteractionResult use(Level world, @NotNull Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        RandomSource rand = world.random;
        ResourceKey<LootTable> loot = JLootTables.LOOT_BASIC;
        switch(this.tier) {
            case GOLD -> loot = JLootTables.LOOT_GOLD;
            case DIAMOND -> loot = JLootTables.LOOT_DIAMOND;
            case FROSTY_GIFT -> loot = JLootTables.LOOT_FROSTY;
        }
        if(!world.isClientSide()) {
            LootTable table = Objects.requireNonNull(world.getServer()).reloadableRegistries().getLootTable(loot);
            List<ItemStack> itemList = table.getRandomItems(new LootParams.Builder((ServerLevel)world).withParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.ORIGIN, player.position()).create(LootContextParamSets.GIFT));
            ItemStack spawn = itemList.get(rand.nextInt(itemList.size()));

            ItemEntity item = new ItemEntity(world, player.position().x, player.position().y, player.position().z, spawn);
            world.addFreshEntity(item);
            player.awardStat(Stats.ITEM_USED.get(this));
            if(!player.isCreative())
                player.getItemInHand(hand).shrink(1);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public boolean isFoil(ItemStack f) {
        return this.tier == LootTier.DIAMOND;
    }

    public enum LootTier {
        NORMAL,
        GOLD,
        DIAMOND,
        FROSTY_GIFT
    }
}
