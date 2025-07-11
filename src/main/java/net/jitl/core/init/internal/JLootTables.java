package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class JLootTables {

    public static ResourceKey<LootTable> TOWER_GUARDIAN_CRYSTAL = addBossCrystalLootTable("temple_guardian");
    public static ResourceKey<LootTable> ROCKITE_SMASHER_CRYSTAL = addBossCrystalLootTable("rockite_smasher");
    public static ResourceKey<LootTable> FROST_GOLEM_CRYSTAL = addBossCrystalLootTable("frost_golem");
    public static ResourceKey<LootTable> OKOLOO_CRYSTAL = addBossCrystalLootTable("okoloo");
    public static ResourceKey<LootTable> BLAZIER_CRYSTAL = addBossCrystalLootTable("blazier");
    public static ResourceKey<LootTable> CALCIA_CRYSTAL = addBossCrystalLootTable("calcia");
    public static ResourceKey<LootTable> SOUL_WATCHER_CRYSTAL = addBossCrystalLootTable("soul_watcher");
    public static ResourceKey<LootTable> WITHERING_BEAST_CRYSTAL = addBossCrystalLootTable("withering_beast");
    public static ResourceKey<LootTable> EUDOR_CRYSTAL = addBossCrystalLootTable("eudor");
    public static ResourceKey<LootTable> CORALLATOR_CRYSTAL = addBossCrystalLootTable("corallator");
    public static ResourceKey<LootTable> THUNDER_BIRD_CRYSTAL = addBossCrystalLootTable("thunder_bird");
    public static ResourceKey<LootTable> SCALE_CRYSTAL = addBossCrystalLootTable("scale");
    public static ResourceKey<LootTable> LOGGER_CRYSTAL = addBossCrystalLootTable("logger");
    public static ResourceKey<LootTable> TERRANIAN_PROTECTOR_CRYSTAL = addBossCrystalLootTable("terranian_protector");
    public static ResourceKey<LootTable> SENTRY_KING_CRYSTAL = addBossCrystalLootTable("sentry_king");
    public static ResourceKey<LootTable> SKY_STALKER_CRYSTAL = addBossCrystalLootTable("sky_stalker");

    public static ResourceKey<LootTable> SPIRIT_CRYSTAL = addLootTable("loot/spirit_crystal");
    public static ResourceKey<LootTable> LOOT_BASIC = addLootTable("loot/loot_basic");
    public static ResourceKey<LootTable> LOOT_GOLD = addLootTable("loot/loot_gold");
    public static ResourceKey<LootTable> LOOT_DIAMOND = addLootTable("loot/loot_diamond");
    public static ResourceKey<LootTable> LOOT_FROSTY = addLootTable("loot/loot_frosty_gift");

    public static ResourceKey<LootTable> addLootTable(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl(name));
    }

    public static ResourceKey<LootTable> addVanillaLootTable(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace(name));
    }

    public static ResourceKey<LootTable> addRuinTable(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse(name));
    }

    public static ResourceKey<LootTable> addBossCrystalLootTable(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl("boss_crystal/" + name));
    }

}