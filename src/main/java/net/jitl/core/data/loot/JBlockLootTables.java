package net.jitl.core.data.loot;

import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.function.Function;

public class JBlockLootTables extends BlockLootSubProvider {

    public JBlockLootTables() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return JBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void generate() {
        this.add(JBlocks.IRIDIUM_ORE, (block) -> createOreDrop(block, JItems.IRIDIUM_NUGGET.get()));
        this.add(JBlocks.DEEPSLATE_IRIDIUM_ORE, (block) -> createOreDrop(block, JItems.IRIDIUM_NUGGET.get()));
        this.dropSelf(JBlocks.IRIDIUM_BLOCK);
        this.add(JBlocks.SAPPHIRE_ORE, (block) -> createOreDrop(block, JItems.SAPPHIRE.get()));
        this.add(JBlocks.DEEPSLATE_SAPPHIRE_ORE, (block) -> createOreDrop(block, JItems.SAPPHIRE.get()));
        this.dropSelf(JBlocks.SAPPHIRE_BLOCK);
        this.add(JBlocks.SHADIUM_ORE, (block) -> createOreDrop(block, JItems.RAW_SHADIUM.get()));
        this.add(JBlocks.DEEPSLATE_SHADIUM_ORE, (block) -> createOreDrop(block, JItems.RAW_SHADIUM.get()));
        this.dropSelf(JBlocks.SHADIUM_BLOCK);
        this.add(JBlocks.LUNIUM_ORE, (block) -> createOreDrop(block, JItems.LUNIUM_POWDER.get()));
        this.add(JBlocks.DEEPSLATE_LUNIUM_ORE, (block) -> createOreDrop(block, JItems.LUNIUM_POWDER.get()));
        this.dropSelf(JBlocks.LUNIUM_BLOCK);

        this.dropSelf(JBlocks.VERDITE_ORE);
        this.dropSelf(JBlocks.VERDITE_BLOCK);
        this.dropSelf(JBlocks.DEEPSLATE_VERDITE_ORE);

        this.add(JBlocks.BLOODCRUST_ORE, (block) -> createOreDrop(block, JItems.RAW_BLOODCRUST.get()));
        this.dropSelf(JBlocks.BLOODCRUST_BLOCK);

        this.add(JBlocks.ENDERILLIUM_ORE, (block) -> createOreDrop(block, JItems.ENDERILLIUM_SHARD.get()));
        this.dropSelf(JBlocks.ENDERILLIUM_BLOCK);

        this.add(JBlocks.ASHUAL_ORE, (block) -> createOreDrop(block, JItems.ASH.get()));
        this.dropSelf(JBlocks.ASHUAL_BLOCK);
        this.dropSelf(JBlocks.FIRESTONE_ORE);
        this.dropSelf(JBlocks.FIRESTONE_BLOCK);

        this.add(JBlocks.BLAZIUM_ORE, (block) -> createOreDrop(block, JItems.RAW_BLAZIUM.get()));
        this.dropSelf(JBlocks.BLAZIUM_BLOCK);

        this.add(JBlocks.CELESTIUM_ORE, (block) -> createOreDrop(block, JItems.CELESTIUM_GEMSTONE.get()));
        this.dropSelf(JBlocks.CELESTIUM_BLOCK);
        this.add(JBlocks.MEKYUM_ORE, (block) -> createOreDrop(block, JItems.MEKYUM_GEMSTONE.get()));
        this.dropSelf(JBlocks.MEKYUM_BLOCK);
        this.add(JBlocks.KORITE_ORE, (block) -> createOreDrop(block, JItems.KORITE_GEMSTONE.get()));
        this.dropSelf(JBlocks.KORITE_BLOCK);
        this.add(JBlocks.STORON_ORE, (block) -> createOreDrop(block, JItems.STORON_GEMSTONE.get()));
        this.dropSelf(JBlocks.STORON_BLOCK);

        this.add(JBlocks.RIMESTONE_ORE, (block) -> createOreDrop(block, JItems.RIMESTONE.get()));
        this.dropSelf(JBlocks.RIMESTONE_BLOCK);

        this.add(JBlocks.PERIDOT_ORE, (block) -> createOreDrop(block, JItems.PERIDOT_GEMSTONE.get()));
        this.dropSelf(JBlocks.PERIDOT_BLOCK);

        this.dropSelf(JBlocks.DES_ORE);
        this.dropSelf(JBlocks.DES_BLOCK);
        this.dropSelf(JBlocks.FLAIRIUM_ORE);
        this.dropSelf(JBlocks.FLAIRIUM_BLOCK);

        this.add(JBlocks.ORBADITE_ORE, (block) -> createOreDrop(block, JItems.RAW_ORBADITE.get()));
        this.dropSelf(JBlocks.ORBADITE_BLOCK);
        this.add(JBlocks.GORBITE_ORE, (block) -> createOreDrop(block, JItems.GORBITE_GEM.get()));
        this.dropSelf(JBlocks.GORBITE_BLOCK);

        this.add(JBlocks.RARE_GEM_BLOCK, (block) -> createGemBlockDrops(block, true));
        this.add(JBlocks.COMMON_GEM_BLOCK, (block) -> createGemBlockDrops(block, false));

        this.dropSelf(JBlocks.YELLOW_GEM_BLOCK);
        this.dropSelf(JBlocks.PURPLE_GEM_BLOCK);
        this.dropSelf(JBlocks.GREEN_GEM_BLOCK);
        this.dropSelf(JBlocks.BLUE_GEM_BLOCK);

        this.add(JBlocks.WARPED_QUARTZ_ORE, (block) -> createRandomAmount(block, JItems.WARPED_QUARTZ.get(), 1, 3));
        this.dropSelf(JBlocks.WARPED_QUARTZ_BLOCK);
        this.dropSelf(JBlocks.CHISELED_WARPED_QUARTZ_BLOCK);
        this.add(JBlocks.WARPED_QUARTZ_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.WARPED_QUARTZ_BRICKS);
        this.dropSelf(JBlocks.WARPED_QUARTZ_STAIRS);
        this.dropSelf(JBlocks.WARPED_QUARTZ_PILLAR);
        this.dropSelf(JBlocks.SMOOTH_WARPED_QUARTZ);
        this.add(JBlocks.SMOOTH_WARPED_QUARTZ_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.SMOOTH_WARPED_QUARTZ_STAIRS);

        this.add(JBlocks.CRIMSON_QUARTZ_ORE, (block) -> createRandomAmount(block, JItems.CRIMSON_QUARTZ.get(), 1, 3));
        this.dropSelf(JBlocks.CRIMSON_QUARTZ_BLOCK);
        this.dropSelf(JBlocks.CHISELED_CRIMSON_QUARTZ_BLOCK);
        this.add(JBlocks.CRIMSON_QUARTZ_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.CRIMSON_QUARTZ_BRICKS);
        this.dropSelf(JBlocks.CRIMSON_QUARTZ_STAIRS);
        this.dropSelf(JBlocks.CRIMSON_QUARTZ_PILLAR);
        this.dropSelf(JBlocks.SMOOTH_CRIMSON_QUARTZ);
        this.add(JBlocks.SMOOTH_CRIMSON_QUARTZ_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.SMOOTH_CRIMSON_QUARTZ_STAIRS);

        this.add(JBlocks.BLEEDSTONE_BLOCK, (block) -> createOreDrop(block, JItems.BLEEDSTONE.get()));
        this.add(JBlocks.SMITHSTONE_BLOCK, (block) -> createOreDrop(block, JItems.SMITHSTONE.get()));
        this.dropSelf(JBlocks.SOULSTONE_BLOCK);

        this.dropSelf(JBlocks.TALL_GREEN_GLOWSHROOM);
        this.dropSelf(JBlocks.TALL_BLUE_GLOWSHROOM);
        this.dropSelf(JBlocks.TALL_RED_GLOWSHROOM);
        this.dropSelf(JBlocks.GREEN_GLOWSHROOM);
        this.dropSelf(JBlocks.BLUE_GLOWSHROOM);
        this.dropSelf(JBlocks.RED_GLOWSHROOM);

        this.dropSelf(JBlocks.DUNGEON_BRICKS);
        this.dropSelf(JBlocks.DUNGEON_BRICK_STAIRS);
        this.dropSelf(JBlocks.DUNGEON_BRICK_FENCE);
        this.dropSelf(JBlocks.CARVED_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.CRACKED_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.CARVED_DUNGEON_BRICK_STAIRS);
        this.dropSelf(JBlocks.CARVED_DUNGEON_BRICK_FENCE);
        this.dropSelf(JBlocks.CHISELED_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.CHISELED_DUNGEON_BRICK_STAIRS);
        this.dropSelf(JBlocks.CHISELED_DUNGEON_BRICK_FENCE);
        this.dropSelf(JBlocks.DUNGEON_FLOOR);
        this.dropSelf(JBlocks.GILDED_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.GILDED_DUNGEON_BRICK_STAIRS);
        this.dropSelf(JBlocks.GILDED_DUNGEON_BRICK_FENCE);
        this.dropSelf(JBlocks.DUNGEON_LAMP);
        this.dropSelf(JBlocks.DUNGEON_LAMP_STAIRS);
        this.dropSelf(JBlocks.DUNGEON_LAMP_FENCE);
        this.dropSelf(JBlocks.NETHER_DUNGEON_BRICK_STAIRS);
        this.dropSelf(JBlocks.NETHER_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.NETHER_DUNGEON_BRICK_FENCE);
        this.add(JBlocks.MINI_GHAST_SPAWNER, noDrop());
        this.dropSelf(JBlocks.BOILING_BARS);

        this.dropSelf(JBlocks.EUCA_PORTAL_FRAME);
        this.add(JBlocks.EUCA_PORTAL, noDrop());
        this.dropSelf(JBlocks.GOLDITE_DIRT);
        this.add(JBlocks.GOLDITE_STONE, (block) -> this.createSingleItemTableWithSilkTouch(block, JBlocks.GOLDITE_COBBLESTONE.get()));
        this.dropSelf(JBlocks.GOLDITE_COBBLESTONE);
        this.dropSelf(JBlocks.EUCA_BRICK);
        this.dropSelf(JBlocks.EUCA_DUNGEON_BRICKS);
        this.dropSelf(JBlocks.ROYAL_BRICKS);
        this.dropSelf(JBlocks.ROYAL_STAIRS);
        this.dropSelf(JBlocks.ROYAL_FENCE);
        this.dropSelf(JBlocks.ROYAL_STONE);
        this.dropSelf(JBlocks.ROYAL_STONE_STAIRS);
        this.dropSelf(JBlocks.ROYAL_STONE_FENCE);
        this.dropSelf(JBlocks.ROYAL_PAVER);
        this.dropSelf(JBlocks.ROYAL_PAVER_STAIRS);
        this.dropSelf(JBlocks.ROYAL_PAVER_FENCE);
        this.dropSelf(JBlocks.ROYAL_SHINGLE);
        this.dropSelf(JBlocks.ROYAL_SHINGLE_STAIRS);
        this.dropSelf(JBlocks.ROYAL_SHINGLE_FENCE);
        this.dropSelf(JBlocks.POLISHED_ROYAL_STONE);
        this.dropSelf(JBlocks.POLISHED_ROYAL_STAIRS);
        this.dropSelf(JBlocks.POLISHED_ROYAL_FENCE);
        this.dropSelf(JBlocks.ROYAL_PLILLAR);
        this.dropSelf(JBlocks.EUCA_DUNGEON_STAIRS);
        this.dropSelf(JBlocks.EUCA_DUNGEON_TILE);
        this.dropSelf(JBlocks.EUCA_RUNIC_BRICKS);
        this.dropSelf(JBlocks.EUCA_RUNIC_LAMP);
        this.dropSelf(JBlocks.EUCA_SQUARE_RUNIC_BRICKS);
        this.dropSelf(JBlocks.EUCA_SQUARE_BRICKS);
        this.dropSelf(JBlocks.EUCA_TILE);
        this.dropSelf(JBlocks.EUCA_TALL_GRASS);
        this.dropSelf(JBlocks.EUCA_TALL_FLOWERS);
        this.dropSelf(JBlocks.EUCA_SILVER_FLOWER);
        this.dropSelf(JBlocks.EUCA_BLUE_FLOWER);
        this.dropSelf(JBlocks.GOLDITE_FLOWER);
        this.dropSelf(JBlocks.GOLDITE_STALKS);
        this.dropSelf(JBlocks.GOLDITE_BULB);
        this.add(JBlocks.GOLD_BOT_SPAWNER, noDrop());
        this.dropSelf(JBlocks.GOLDITE_FURNACE);
        this.dropSelf(JBlocks.EUCA_PUMPKIN);
        this.dropSelf(JBlocks.GLIMMER_ROOT);

        this.add(JBlocks.EUCA_GOLD_GRASS, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.GOLDITE_DIRT.get()));
        this.add(JBlocks.GOLDITE_GRASS, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.GOLDITE_DIRT.get()));
        this.dropSelf(JBlocks.EUCA_GOLD_LOG);
        this.add(JBlocks.EUCA_GOLD_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.EUCA_GOLD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.EUCA_GOLD_SAPLING);
        this.dropSelf(JBlocks.EUCA_GOLD_PLANKS);
        this.add(JBlocks.EUCA_GOLD_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.EUCA_GOLD_TRAP_DOOR);
        this.dropSelf(JBlocks.EUCA_GOLD_STAIRS);
        this.add(JBlocks.EUCA_GOLD_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.EUCA_GOLD_BUTTON);
        this.dropSelf(JBlocks.EUCA_GOLD_PRESSURE_PLATE);
        this.dropSelf(JBlocks.EUCA_GOLD_FENCE_GATE);
        this.dropSelf(JBlocks.EUCA_GOLD_FENCE);
        this.add(JBlocks.GOLDITE_PATH, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.GOLDITE_DIRT.get()));

        this.dropSelf(JBlocks.EUCA_BROWN_LOG);
        this.add(JBlocks.EUCA_GREEN_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.EUCA_GREEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.EUCA_GREEN_SAPLING);
        this.dropSelf(JBlocks.EUCA_BROWN_PLANKS);
        this.add(JBlocks.EUCA_BROWN_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.EUCA_BROWN_TRAP_DOOR);
        this.dropSelf(JBlocks.EUCA_BROWN_STAIRS);
        this.add(JBlocks.EUCA_BROWN_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.EUCA_BROWN_BUTTON);
        this.dropSelf(JBlocks.EUCA_BROWN_PRESSURE_PLATE);
        this.dropSelf(JBlocks.EUCA_BROWN_FENCE_GATE);
        this.dropSelf(JBlocks.EUCA_BROWN_FENCE);

        this.dropSelf(JBlocks.FROZEN_PORTAL_FRAME);
        this.add(JBlocks.FROZEN_PORTAL, noDrop());
        this.add(JBlocks.GRASSY_PERMAFROST, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.CRUMBLED_PERMAFROST.get()));
        this.dropSelf(JBlocks.FUMICE);
        this.dropSelf(JBlocks.PERMAFROST);
        this.dropSelf(JBlocks.CRUMBLED_PERMAFROST);
        this.dropSelf(JBlocks.FROZEN_LOG);
        this.add(JBlocks.FROZEN_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.FROSTWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.FROSTWOOD_SAPLING);
        this.dropSelf(JBlocks.FROZEN_PLANKS);
        this.add(JBlocks.FROZEN_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.FROZEN_TRAP_DOOR);
        this.dropSelf(JBlocks.FROZEN_STAIRS);
        this.add(JBlocks.FROZEN_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.FROZEN_BUTTON);
        this.dropSelf(JBlocks.FROZEN_PRESSURE_PLATE);
        this.dropSelf(JBlocks.FROZEN_FENCE_GATE);
        this.dropSelf(JBlocks.FROZEN_FENCE);
        this.dropSelf(JBlocks.FROST_CRYSTAL_LARGE);
        this.dropSelf(JBlocks.FROST_CRYSTAL_MEDIUM);
        this.dropSelf(JBlocks.FROST_CRYSTAL_SMALL);
        this.dropSelf(JBlocks.FROST_CRYSTAL_TINY);
        this.dropSelf(JBlocks.FROSTBERRY_THORN);
        this.dropSelf(JBlocks.ICE_BUSH);
        this.dropSelf(JBlocks.ICE_BUD);
        this.dropSelf(JBlocks.BITTERWOOD_SAPLING);
        this.dropSelf(JBlocks.BITTERWOOD_CAMPFIRE);
        this.dropSelf(JBlocks.PERMAFROST_FURNACE);
        this.dropSelf(JBlocks.PACKED_SNOW_BRICKS);
        this.dropSelf(JBlocks.PACKED_ICE_BRICKS);
        this.dropSelf(JBlocks.PACKED_SNOW_FENCE);
        this.dropSelf(JBlocks.PACKED_ICE_FENCE);
        this.dropSelf(JBlocks.FROZEN_BRICKS);
        this.dropSelf(JBlocks.PACKED_SNOW_BRICKS_STAIRS);
        this.dropSelf(JBlocks.PACKED_ICE_BRICKS_STAIRS);
        this.add(JBlocks.PERMAFROST_ROAD, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.CRUMBLED_PERMAFROST.get()));
        this.dropSelf(JBlocks.FROZEN_BLOOM);
        this.dropSelf(JBlocks.FROZEN_FLOWER);
        this.dropSelf(JBlocks.FROSTY_ICE);
        this.dropSelf(JBlocks.FROST_GEM_BLOCK);

        this.dropSelf(JBlocks.BOIL_PORTAL_FRAME);
        this.add(JBlocks.BOIL_PORTAL, noDrop());
        this.dropSelf(JBlocks.SULPHUR_CRYSTAL);
        this.dropSelf(JBlocks.SULPHUR_ROCK);
        this.dropSelf(JBlocks.RUBBLE);
        this.dropSelf(JBlocks.ASH_BLOCK);
        this.dropSelf(JBlocks.SCORCHED_RUBBLE);
        this.dropSelf(JBlocks.VOLCANIC_SAND);
        this.dropSelf(JBlocks.VOLCANIC_SOIL);
        this.dropSelf(JBlocks.HOT_GROUND);
        this.dropSelf(JBlocks.CHARRED_GRASS);
        this.dropSelf(JBlocks.VOLCANIC_SANDSTONE);
        this.dropSelf(JBlocks.SCORCHED_STALAGMITE_TINY);
        this.dropSelf(JBlocks.SCORCHED_STALAGMITE_SMALL);
        this.dropSelf(JBlocks.SCORCHED_STALAGMITE_MED);
        this.dropSelf(JBlocks.SCORCHED_STALAGMITE_LARGE);
        this.dropSelf(JBlocks.CHARRED_BRUSH);
        this.dropSelf(JBlocks.CHARRED_SHORT_GRASS);
        this.dropSelf(JBlocks.CHARRED_WEEDS);
        this.dropSelf(JBlocks.CRUMBLING_PINE);
        this.dropSelf(JBlocks.CRISP_GRASS);
        this.dropSelf(JBlocks.FLAME_POD);
        this.dropSelf(JBlocks.INFERNO_BUSH);
        this.dropSelf(JBlocks.LAVA_BLOOM);
        this.dropSelf(JBlocks.SCORCHED_CACTUS);

        this.dropSelf(JBlocks.BURNED_BARK);
        this.add(JBlocks.CHARRED_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.BURNED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.BURNED_SAPLING);
        this.dropSelf(JBlocks.CHARRED_SAPLING);
        this.dropSelf(JBlocks.BURNED_PLANKS);
        this.add(JBlocks.BURNED_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.BURNED_TRAP_DOOR);
        this.dropSelf(JBlocks.BURNED_STAIRS);
        this.add(JBlocks.BURNED_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.BURNED_BUTTON);
        this.dropSelf(JBlocks.BURNED_PRESSURE_PLATE);
        this.dropSelf(JBlocks.BURNED_FENCE_GATE);
        this.dropSelf(JBlocks.BURNED_FENCE);
        this.dropSelf(JBlocks.VOLCANIC_ROCK);
        this.add(JBlocks.BOIL_LOCK, noDrop());
        this.dropSelf(JBlocks.TALL_MOLTEN_PLANT);
        this.dropSelf(JBlocks.TALL_CRUMBLING_PINE);
        this.dropSelf(JBlocks.TALL_SIZZLESHROOM);
        this.dropSelf(JBlocks.TALL_CHARRED_GRASS);
        this.dropSelf(JBlocks.SIZZLESHROOM);

        this.dropSelf(JBlocks.DEPTHS_PORTAL_FRAME);
        this.add(JBlocks.DEPTHS_PORTAL, noDrop());
        this.add(JBlocks.DEPTHS_GRASS, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.DEPTHS_DIRT.get()));
        this.dropSelf(JBlocks.DEPTHS_LOG);
        this.add(JBlocks.DEPTHS_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.DEPTHS_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.DEPTHS_SAPLING);
        this.dropSelf(JBlocks.DEPTHS_PLANKS);
        this.add(JBlocks.DEPTHS_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.DEPTHS_TRAP_DOOR);
        this.dropSelf(JBlocks.DEPTHS_STAIRS);
        this.add(JBlocks.DEPTHS_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.DEPTHS_BUTTON);
        this.dropSelf(JBlocks.DEPTHS_PRESSURE_PLATE);
        this.dropSelf(JBlocks.DEPTHS_FENCE_GATE);
        this.dropSelf(JBlocks.DEPTHS_FENCE);
        this.dropSelf(JBlocks.DEPTHS_DIRT);
        this.dropSelf(JBlocks.DEPTHS_STONE);
        this.dropSelf(JBlocks.DEPTHS_LAMP);
        this.dropSelf(JBlocks.DEPTHS_LIGHT);
        this.dropSelf(JBlocks.DEPTHS_CRYSTAL);
        this.dropSelf(JBlocks.DARK_BRICK);
        this.dropSelf(JBlocks.DARK_FLOOR);
        this.dropSelf(JBlocks.DARK_SHINGLE);
        this.dropSelf(JBlocks.DEPTHS_BRICK);
        this.dropSelf(JBlocks.DEPTHS_SHINGLE);
        this.dropSelf(JBlocks.DEPTHS_DARK_SHINGLE);
        this.dropSelf(JBlocks.DEPTHS_COBBLESTONE);
        this.dropSelf(JBlocks.DEPTHS_TILE);
        this.dropSelf(JBlocks.DEPTHS_GLASS);
        this.dropSelf(JBlocks.DEPTHS_PILLAR);
        this.add(JBlocks.DARKLY_LOCK, noDrop());
        this.add(JBlocks.DEPTHS_LOCK, noDrop());
        this.dropSelf(JBlocks.DEPTHS_BOOK_SHELF);
        this.dropSelf(JBlocks.DEPTHS_BLUE_FLOWER);
        this.dropSelf(JBlocks.DEPTHS_FLOWER);

        this.dropSelf(JBlocks.CORBA_PORTAL_FRAME);
        this.add(JBlocks.CORBA_PORTAL, noDrop());
        this.add(JBlocks.CORBA_GRASS, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.CORBA_DIRT.get()));
        this.dropSelf(JBlocks.BOGWOOD_LOG);
        this.add(JBlocks.BOGWOOD_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.BOGWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.BOGWOOD_SAPLING);
        this.dropSelf(JBlocks.CORBA_LOG);
        this.add(JBlocks.CORBA_LEAVES, (block) -> this.createLeavesDrops(block, JBlocks.CORBA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(JBlocks.CORBA_SAPLING);
        this.dropSelf(JBlocks.CORBA_PLANKS);
        this.add(JBlocks.CORBA_DOOR, this::createDoorTable);
        this.dropSelf(JBlocks.CORBA_TRAP_DOOR);
        this.dropSelf(JBlocks.CORBA_STAIRS);
        this.add(JBlocks.CORBA_SLAB, this::createSlabItemTable);
        this.dropSelf(JBlocks.CORBA_BUTTON);
        this.dropSelf(JBlocks.CORBA_PRESSURE_PLATE);
        this.dropSelf(JBlocks.CORBA_FENCE_GATE);
        this.dropSelf(JBlocks.CORBA_FENCE);

        this.dropSelf(JBlocks.CORBA_DIRT);
        this.add(JBlocks.CORBA_STONE, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.CORBA_COBBLESTONE.get()));
        this.add(JBlocks.CORBA_PATH, (block) -> createSingleItemTableWithSilkTouch(block, JBlocks.CORBA_DIRT.get()));
        this.dropSelf(JBlocks.CORBA_POST);
        this.dropSelf(JBlocks.CORBA_PLILLAR);
        this.dropSelf(JBlocks.TAINTED_MUD);
        this.dropSelf(JBlocks.DRIED_MUD);
        this.dropSelf(JBlocks.CORBA_LAMP);
        this.dropSelf(JBlocks.CORBA_BRICKS);
        this.dropSelf(JBlocks.CORBA_CRACKED_BRICKS);
        this.dropSelf(JBlocks.CORBA_COBBLESTONE);
        this.dropSelf(JBlocks.CORBA_DARK_BRICKS);
        this.dropSelf(JBlocks.CORBA_LIGHT_BRICKS);
        this.dropSelf(JBlocks.ELDER_BLOCK);
        this.dropSelf(JBlocks.CORBA_SENTRY_BRICKS);
        this.dropSelf(JBlocks.CORBA_LADDER);
        this.dropSelf(JBlocks.CORBA_BLUE_FLOWER);
        this.dropSelf(JBlocks.CORBA_RED_FLOWER);
        this.dropSelf(JBlocks.CORBA_SPECKLED_FLOWER);
        this.dropSelf(JBlocks.CORBA_PURPLE_FLOWER);
        this.dropSelf(JBlocks.CORBA_LIGHT_PURPLE_FLOWER);
        this.dropSelf(JBlocks.CORBA_DARK_PURPLE_FLOWER);
        this.dropSelf(JBlocks.CORBA_FLOWER);

        this.dropSelf(JBlocks.GRINDSTONE);
        this.dropSelf(JBlocks.JOURNEY_CHEST);
        this.dropSelf(JBlocks.NETHER_CHEST);
        this.dropSelf(JBlocks.FROZEN_CHEST);
        this.dropSelf(JBlocks.EUCA_CHEST);
        this.dropSelf(JBlocks.BOIL_CHEST);
        this.dropSelf(JBlocks.DEPTHS_CHEST);
        this.dropSelf(JBlocks.CORBA_CHEST);

        this.add(JBlocks.ROCKITE_SPAWNER, noDrop());
        this.dropSelf(JBlocks.FROZEN_PEDESTAL);
        this.dropSelf(JBlocks.ROYAL_PEDESTAL);
        this.dropSelf(JBlocks.STONE_PLILLAR);
        this.dropSelf(JBlocks.SMALL_STONE_BRICKS);

        this.addCrop(JBlocks.FLORO_PEDAL_CROP.get(), JItems.FLORO_PEDAL.get(), JItems.FLORO_SEEDS.get(), 7);
    }

    protected void addCrop(Block cropBlock, Item crop, Item seeds, int maxAge) {
        this.add(cropBlock, this.createCropDrops(cropBlock, crop, seeds, LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, maxAge))));
    }

    protected LootTable.Builder createGemBlockDrops(Block block, boolean rare) {
        float prob = rare ? 0.525F : 0.125F;
        int min = rare ? 2 : 1;
        int max = rare ? 4 : 2;
        return this.applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(JItems.YELLOW_GEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).when(LootItemRandomChanceCondition.randomChance(prob))
                .add(LootItem.lootTableItem(JItems.PURPLE_GEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).when(LootItemRandomChanceCondition.randomChance(prob))
                .add(LootItem.lootTableItem(JItems.GREEN_GEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).when(LootItemRandomChanceCondition.randomChance(prob))
                .add(LootItem.lootTableItem(JItems.BLUE_GEM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))).when(LootItemRandomChanceCondition.randomChance(prob))));
    }


    protected LootTable.Builder createRandomAmount(Block pBlock, Item drop, int min, int max) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected void add(RegistryObject<? extends Block> block, Function<Block, LootTable.Builder> factory) {
        this.add(block.get(), factory.apply(block.get()));
    }

    public void dropSelf(RegistryObject<? extends Block> block) {
        this.dropSelf(block.get());
    }

    public void dropOther(RegistryObject<? extends Block> block, RegistryObject<? extends Item> item) {
        this.dropOther(block.get(), item.get());
    }

    protected void add(RegistryObject<? extends Block> block, LootTable.Builder builder) {
        add(block.get(), builder);
    }
}
