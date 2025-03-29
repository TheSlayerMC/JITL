package net.jitl.core.init.compat;


import net.jitl.common.block.base.JFarmlandBlock;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.storage.loot.LootTable;

public class JERCompat {

//    public static void init() {
//        IJERAPI api = JERAPI.getInstance();
//todo
//        IWorldGenRegistry worldGen = api.getWorldGenRegistry();
//        if(worldGen != null) {
//            worldGen.register(new ItemStack(JBlocks.SAPPHIRE_ORE.get()), new ItemStack(JBlocks.DEEPSLATE_SAPPHIRE_ORE.get()), new DistributionSquare(5, 7, -80, 48), new LootDrop(new ItemStack(JItems.SAPPHIRE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.IRIDIUM_ORE.get()), new ItemStack(JBlocks.DEEPSLATE_IRIDIUM_ORE.get()), new DistributionSquare(5, 7, -80, 48), new LootDrop(new ItemStack(JItems.IRIDIUM_NUGGET.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.SHADIUM_ORE.get()), new ItemStack(JBlocks.DEEPSLATE_SHADIUM_ORE.get()), new DistributionSquare(5, 7, -80, 48), new LootDrop(new ItemStack(JItems.RAW_SHADIUM.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.LUNIUM_ORE.get()), new ItemStack(JBlocks.DEEPSLATE_LUNIUM_ORE.get()), new DistributionSquare(5, 7, -80, 48), new LootDrop(new ItemStack(JItems.LUNIUM_POWDER.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.BLOODCRUST_ORE.get()), new DistributionSquare(4, 7, -80, 256), true, new LootDrop(new ItemStack(JItems.RAW_BLOODCRUST.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.BLEEDSTONE_BLOCK.get()), new DistributionSquare(2, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.BLEEDSTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.SMITHSTONE_BLOCK.get()), new DistributionSquare(2, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.SMITHSTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.FIRESTONE_ORE.get()), new DistributionSquare(10, 7, -64, 256), true, new LootDrop(new ItemStack(JItems.FIRESTONE_CLUMP.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.CRIMSON_QUARTZ_ORE.get()), new DistributionSquare(10, 7, -64, 256), true, new LootDrop(new ItemStack(JItems.CRIMSON_QUARTZ.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.WARPED_QUARTZ_ORE.get()), new DistributionSquare(10, 7, -64, 256), true, new LootDrop(new ItemStack(JItems.WARPED_QUARTZ.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.ENDERILLIUM_ORE.get()), new DistributionSquare(7, 7, -64, 128), true, new LootDrop(new ItemStack(JItems.ENDERILLIUM_SHARD.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.ASHUAL_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.ASH.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.BLAZIUM_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.RAW_BLAZIUM.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.CELESTIUM_ORE.get()), new DistributionSquare(7, 7, -64, 128), true, new LootDrop(new ItemStack(JItems.CELESTIUM_GEMSTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.MEKYUM_ORE.get()), new DistributionSquare(7, 7, -64, 128), true, new LootDrop(new ItemStack(JItems.MEKYUM_GEMSTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.STORON_ORE.get()), new DistributionSquare(7, 7, -64, 128), true, new LootDrop(new ItemStack(JItems.STORON_GEMSTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.KORITE_ORE.get()), new DistributionSquare(7, 7, -64, 128), true, new LootDrop(new ItemStack(JItems.RAW_ORBADITE.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.RIMESTONE_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.RIMESTONE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.PERIDOT_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.PERIDOT_GEMSTONE.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.DES_ORE.get()), new DistributionSquare(7, 7, 0, 256), new LootDrop(new ItemStack(JBlocks.DES_ORE.get()), 1));
//            worldGen.register(new ItemStack(JBlocks.FLAIRIUM_ORE.get()), new DistributionSquare(7, 7, 0, 256), new LootDrop(new ItemStack(JBlocks.FLAIRIUM_ORE.get()), 1));
//
//            worldGen.register(new ItemStack(JBlocks.ORBADITE_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.RAW_ORBADITE.get()), 1, 5, Conditional.affectedByFortune));
//            worldGen.register(new ItemStack(JBlocks.GORBITE_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.GORBITE_GEM.get()), 1, 5, Conditional.affectedByFortune));
//
//            worldGen.register(new ItemStack(JBlocks.LUNITE_ORE.get()), new DistributionSquare(12, 7, 0, 256), true, new LootDrop(new ItemStack(JItems.LUNITE_CHUNK.get()), 1, 5, Conditional.affectedByFortune));
//        }
//
//        //VillagerRegistry.getInstance().addVillagerEntry(new AlloyMenderEntry());
//
//        IPlantRegistry plants = api.getPlantRegistry();
//
//        if(plants != null) {
//            addPlant(plants, JItems.FLORO_SEEDS.get(), JItems.FLORO_PEDAL.get(), JBlocks.FLORO_PEDAL_CROP.get());
//            addPlant(plants, JItems.TOMATO_SEEDS.get(), JItems.TOMATO.get(), JBlocks.TOMATO_CROP.get());
//            addBerryBush(plants, JItems.REDCURRANT_BERRY.get(), JBlocks.REDCURRANT_BUSH.get(), JBlocks.GRASSY_PERMAFROST.get());
//            addBerryBush(plants, JItems.BRADBERRY.get(), JBlocks.BRADBERRY_BUSH.get(), Blocks.GRASS_BLOCK);
//
//            addPlant(plants, JItems.ZATPEDAL_SEEDS.get(), JItems.ZATPEDAL.get(), JBlocks.ZATPEDAL_CROP.get(), JBlocks.GOLDITE_FARMLAND.get());
//            addPlant(plants, JItems.SPINEBERRY_SEEDS.get(), JItems.SPINEBERRIES.get(), JBlocks.SPINEBERRY_CROP.get(), JBlocks.GOLDITE_FARMLAND.get());
//
//            addPlant(plants, JItems.CRAKEBULB_SEEDS.get(), JItems.CRAKEBULB.get(), JBlocks.CRAKEBULB_CROP.get(), JBlocks.DEPTHS_FARMLAND.get());
//            addPlant(plants, JItems.CRACKENCANE_SEEDS.get(), JItems.CRACKENCANE.get(), JBlocks.CRACKENCANE_CROP.get(), JBlocks.DEPTHS_FARMLAND.get());
//
//            addPlant(plants, JItems.CORVEGGIES.get(), JItems.CORVEGGIES.get(), JBlocks.CORVEGGIES_CROP.get(), JBlocks.CORBA_FARMLAND.get());
//            addPlant(plants, JItems.GLOWA_SEEDS.get(), JItems.GLOWA.get(), JBlocks.GLOWA_CROP.get(), JBlocks.CORBA_FARMLAND.get());
//        }
//
//        IDungeonRegistry chest = api.getDungeonRegistry();
//        if(chest != null) {
//            chest.registerChest("Alloy Mender's House", getChestLootTable("alloy_mender_house"));
//            chest.registerChest("Ancient Loot Chests", getChestLootTable("ancient_loot"));
//            chest.registerChest("Senterian Bar Dungeon Room Chests", getChestLootTable("bars_senterian_dungeon"));
//            chest.registerChest("Boiling Point Dungeon Chests", getChestLootTable("boil_dungeon"));
//            chest.registerChest("Cloudia Chests", getChestLootTable("cloudia"));
//            chest.registerChest("Corba Village Chests", getChestLootTable("corba_village"));
//            chest.registerChest("Depths Dungeon Chests", getChestLootTable("depths_dungeon"));
//            chest.registerChest("Depths Village Chests", getChestLootTable("depths_village"));
//            chest.registerChest("Euca Palace Chests", getChestLootTable("euca_palace"));
//            chest.registerChest("Goldite Sphere Dungeon Chests", getChestLootTable("goldite_dungeon"));
//            chest.registerChest("Guardian Tower Chests", getChestLootTable("guardian_tower"));
//            chest.registerChest("Locked Guardian Tower Chests", getChestLootTable("guardian_tower_locked"));
//            chest.registerChest("Hellbot Structure Chests", getChestLootTable("hellbot_spawner"));
//            chest.registerChest("Hellwing Structure Chests", getChestLootTable("hellwing_tower"));
//            chest.registerChest("Rockite Dungeon Locked Chests", getChestLootTable("locked_rockite_dungeon"));
//            chest.registerChest("Nether Tower Locked Chests", getChestLootTable("nether_tower"));
//            chest.registerChest("Rockite Dungeon Chests", getChestLootTable("rockite_dungeon"));
//            chest.registerChest("Senterian Dungeon Chests", getChestLootTable("senterian_dungeon"));
//            chest.registerChest("Frozen Spike Dungeon Chests", getChestLootTable("spike_dungeon"));
//            chest.registerChest("Terranian Mega Tree Chests", getChestLootTable("terranian_mega_tree"));
//
//            chest.registerChest("Blazier Boss Crystal", getBossLootTable("blazier"));
//            chest.registerChest("Calcia Boss Crystal", getBossLootTable("calcia"));
//            chest.registerChest("Corallator Boss Crystal", getBossLootTable("corallator"));
//            chest.registerChest("Eudor Boss Crystal", getBossLootTable("eudor"));
//            chest.registerChest("Frost Golem Boss Crystal", getBossLootTable("frost_golem"));
//            chest.registerChest("Logger Boss Crystal", getBossLootTable("logger"));
//            chest.registerChest("Okoloo Boss Crystal", getBossLootTable("okoloo"));
//            chest.registerChest("Rockite Smasher Boss Crystal", getBossLootTable("rockite_smasher"));
//            chest.registerChest("Scale Boss Crystal", getBossLootTable("scale"));
//            chest.registerChest("Sentry King Boss Crystal", getBossLootTable("sentry_king"));
//            chest.registerChest("Sky Stalker Boss Crystal", getBossLootTable("sky_stalker"));
//            chest.registerChest("Soul Watcher Boss Crystal", getBossLootTable("soul_watcher"));
//            chest.registerChest("Temple Guardian Boss Crystal", getBossLootTable("temple_guardian"));
//            chest.registerChest("Terranian Protector Boss Crystal", getBossLootTable("terranian_protector"));
//            chest.registerChest("Thunder Bird Boss Crystal", getBossLootTable("thunder_bird"));
//            chest.registerChest("Withering Beast Boss Crystal", getBossLootTable("withering_beast"));
//
//            chest.registerChest("Basic Loot Pouch", getLootTable("loot_basic"));
//            chest.registerChest("Gold Loot Pouch", getLootTable("loot_gold"));
//            chest.registerChest("Diamond Loot Pouch", getLootTable("loot_diamond"));
//            chest.registerChest("Frosty Loot Pouch", getLootTable("loot_frosty_gift"));
//            chest.registerChest("Corba Spirit Crystal", getLootTable("spirit_crystal"));
//        }
//    }
//
//    public static void addPlant(IPlantRegistry reg, Item seeds, Item flower, BushBlock plant) {
//        reg.register(new ItemStack(seeds), plant, new PlantDrop(new ItemStack(flower), 1, 1), new PlantDrop(new ItemStack(seeds), 1, 4));
//    }
//
//    public static void addPlant(IPlantRegistry reg, Item seeds, Item flower, BushBlock plant, Block base) {
//        reg.registerWithSoil(new ItemStack(seeds), plant, base.defaultBlockState(), new PlantDrop(new ItemStack(flower), 1, 1), new PlantDrop(new ItemStack(seeds), 1, 4));
//    }
//
//    public static void addBerryBush(IPlantRegistry reg, Item seeds, BushBlock plant, Block base) {
//        reg.registerWithSoil(new ItemStack(seeds), plant, base.defaultBlockState(), new PlantDrop(new ItemStack(seeds), 2, 3));
//    }

    public static ResourceKey<LootTable> getMobLootTable(String name){
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl("entities/" + name));
    }

    public static ResourceKey<LootTable> getLootTable(String name){
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl("loot/" + name));
    }

    public static ResourceKey<LootTable> getChestLootTable(String name){
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl("chests/" + name));
    }

    public static ResourceKey<LootTable> getBossLootTable(String name){
        return ResourceKey.create(Registries.LOOT_TABLE, JITL.rl("boss_crystal/" + name));
    }

}