package net.jitl.core.data.loot;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class JLootTableSubProvider extends LootTableProvider {

    public JLootTableSubProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, Set.of(), ImmutableList.of(
                new SubProviderEntry(JBlockLootTables::new, LootContextParamSets.BLOCK)
                , new SubProviderEntry(JEntityLootTables::new, LootContextParamSets.ENTITY)), pRegistries);
    }

    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return ImmutableList.of(
                new SubProviderEntry(JBlockLootTables::new, LootContextParamSets.BLOCK)
                , new SubProviderEntry(JEntityLootTables::new, LootContextParamSets.ENTITY));
    }
}
