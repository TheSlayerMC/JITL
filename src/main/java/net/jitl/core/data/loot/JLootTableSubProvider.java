package net.jitl.core.data.loot;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JLootTableSubProvider extends LootTableProvider {

    public JLootTableSubProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), ImmutableList.of(new SubProviderEntry(JBlockLootTables::new, LootContextParamSets.BLOCK)
                , new SubProviderEntry(JEntityLootTables::new, LootContextParamSets.ENTITY)));
    }

    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return ImmutableList.of(new SubProviderEntry(JBlockLootTables::new, LootContextParamSets.BLOCK)
                , new SubProviderEntry(JEntityLootTables::new, LootContextParamSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationcontext) {
        //TODO map.forEach((id, table) -> LootTables.validate(validationcontext, id, table));
    }
}
