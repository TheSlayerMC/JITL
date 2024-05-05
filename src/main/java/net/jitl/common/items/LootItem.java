package net.jitl.common.items;

import net.jitl.common.items.base.JItem;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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

    public LootItem(LootTier tier, Rarity r) {
        super(JItems.itemProps().rarity(r).stacksTo(4));
        this.tier = tier;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        RandomSource rand = world.random;
        String lootName = "";
        switch(this.tier) {
            case NORMAL -> lootName = "basic";
            case GOLD -> lootName = "gold";
            case DIAMOND -> lootName = "diamond";
            case FROSTY_GIFT -> lootName = "frosty_gift";
        }
        if(!world.isClientSide) {
            LootTable table = Objects.requireNonNull(world.getServer()).reloadableRegistries().getLootTable(JLootTables.addLootTable("loot/loot_" + lootName));
            List<ItemStack> itemList = table.getRandomItems(new LootParams.Builder((ServerLevel)world).withParameter(LootContextParams.THIS_ENTITY, player).withParameter(LootContextParams.ORIGIN, player.position()).create(LootContextParamSets.GIFT));
            ItemStack spawn = itemList.get(rand.nextInt(itemList.size()));

            ItemEntity item = new ItemEntity(world, player.position().x, player.position().y, player.position().z, spawn);
            world.addFreshEntity(item);
            player.awardStat(Stats.ITEM_USED.get(this));
            if(!player.isCreative())
                player.getItemInHand(hand).shrink(1);
            return InteractionResultHolder.consume(itemstack);
        }
        return InteractionResultHolder.fail(itemstack);
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
