package net.jitl.core.data.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class LootHelper {
    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayer player, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayer player, @Nullable RandomSource random, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player.getLevel(), random, builder -> {
            //builder.withPlayer(player);
            builder.withLuck(player.getLuck());
            builderModificator.accept(builder);
        });
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerLevel world, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, world, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerLevel world, @Nullable RandomSource random, Consumer<LootContext.Builder> builderModificator) {
        LootContext.Builder builder = new LootContext.Builder(world);
        builderModificator.accept(builder);
        LootTables table = world.getLevel().getServer().getLootTables();
        return genFromLootTable(lootTable, world, builderModificator);
    }
}