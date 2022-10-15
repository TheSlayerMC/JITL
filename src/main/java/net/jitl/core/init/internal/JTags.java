package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class JTags {

    public static final TagKey<Item> FROZEN_TROLL_LOVED_ITEMS = tagItem("frozen_troll_loved_items");
    public static final TagKey<Block> EUCA_STONE_ORE_REPLACEABLES = tagBlock("euca_stone_ore_replaceables");
    public static final TagKey<Block> BOIL_STONE_ORE_REPLACEABLES = tagBlock("boil_stone_ore_replaceables");
    public static final TagKey<Block> FROZEN_STONE_ORE_REPLACEABLES = tagBlock("frozen_stone_ore_replaceables");
    public static final TagKey<Block> JLOGS = tagBlock("jlogs");
    public static final TagKey<Block> JPLANKS = tagBlock("planks");
    public static final TagKey<Block> EUCA_GRASS = tagBlock("euca_grass");
    public static final TagKey<Biome> MAGE_HOUSE = tagBiome("magehouse");
    public static final TagKey<Biome> EUCA_GOLD = tagBiome("euca_gold");
    public static final TagKey<Biome> EUCA_BIOMES = tagBiome("euca_biomes");

    private static TagKey<Item> tagItem(String name) {
        return ItemTags.create(JITL.rl(name));
    }

    private static TagKey<Block> tagBlock(String name) {
        return BlockTags.create(JITL.rl(name));
    }

    private static TagKey<Biome> tagBiome(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, JITL.rl("has_structure/" + name));
    }
}
