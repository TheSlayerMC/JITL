package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class JTags {

    public static final TagKey<Item> FROZEN_TROLL_LOVED_ITEMS = tagItem("frozen_troll_loved_items");
    public static final TagKey<Block> EUCA_STONE_ORE_REPLACEABLES = tagBlock("euca_stone_ore_replaceables");
    public static final TagKey<Block> BOIL_STONE_ORE_REPLACEABLES = tagBlock("boil_stone_ore_replaceables");
    public static final TagKey<Block> FROZEN_STONE_ORE_REPLACEABLES = tagBlock("frozen_stone_ore_replaceables");
    public static final TagKey<Block> CORBA_STONE_ORE_REPLACEABLES = tagBlock("corba_stone_ore_replaceables");
    public static final TagKey<Block> FROZEN_CARVER_REPLACEABLES = tagBlock("frozen_carver_replaceables");
    public static final TagKey<Block> BOIL_CARVER_REPLACEABLES = tagBlock("boil_carver_replaceables");
    public static final TagKey<Block> DEPTHS_STONE_ORE_REPLACEABLES = tagBlock("depths_stone_ore_replaceables");
    public static final TagKey<Block> DEPTHS_LAMP_REPLACEABLES = tagBlock("depths_lamp_replaceables");
    public static final TagKey<Block> CLOUDIA_CLOUD_REPLACEABLES = tagBlock("cloudia_cloud_replaceables");
    public static final TagKey<Block> NETHER_ORE_REPLACEABLES = tagBlock("nether_ore_replaceables");
    public static final TagKey<Block> OVERWORLD_ORE_REPLACEABLES = tagBlock("overworld_ore_replaceables");
    public static final TagKey<Block> DEEPSLATE_ORE_REPLACEABLES = tagBlock("deepslate_ore_replaceables");
    public static final TagKey<Block> BASALT_ORE_REPLACEABLES = tagBlock("basalt_replaceables");
    public static final TagKey<Block> END_STONE = tagBlock("end_stone");
    public static final TagKey<Block> JLOGS = tagBlock("jlogs");
    public static final TagKey<Block> JPLANKS = tagBlock("planks");
    public static final TagKey<Block> EUCA_GRASS = tagBlock("euca_grass");
    public static final TagKey<Block> CORBA_MUD = tagBlock("corba_mud");
    public static final TagKey<Item> ESSENCE_ARROW = tagItem("essence_arrows");
    public static final TagKey<Item> PIERCER_ITEM = tagItem("piercers");

    public static final TagKey<Item> SAPPHIRE = tagItem("sapphire");
    public static final TagKey<Item> LUNIUM = tagItem("lunium_ingot");
    public static final TagKey<Item> SHADIUM = tagItem("shadium_ingot");
    public static final TagKey<Item> BLOODCRUST = tagItem("bloodcrust_ingot");
    public static final TagKey<Item> CELESTIUM = tagItem("celestium_ingot");
    public static final TagKey<Item> KORITE = tagItem("korite_ingot");
    public static final TagKey<Item> STORON = tagItem("storon_ingot");
    public static final TagKey<Item> MEKYUM = tagItem("mekyum_ingot");
    public static final TagKey<Item> FLAIRIUM = tagItem("flairium_ingot");
    public static final TagKey<Item> DES = tagItem("des_ingot");
    public static final TagKey<Item> GORBITE = tagItem("gorbite_gem");
    public static final TagKey<Item> ORBADITE = tagItem("orbadite_gem");
    public static final TagKey<Item> SOULSTONE = tagItem("soulstone");
    public static final TagKey<Item> EMPTY = tagItem("empty");


    private static TagKey<Item> tagItem(String name) {
        return ItemTags.create(JITL.rl(name));
    }

    private static TagKey<Enchantment> tagEnchantment(String name) {
        return TagKey.create(Registries.ENCHANTMENT, JITL.rl(name));
    }

    private static TagKey<Block> tagBlock(String name) {
        return BlockTags.create(JITL.rl(name));
    }

    private static TagKey<Biome> tagBiome(String name) {
        return TagKey.create(Registries.BIOME, JITL.rl("has_structure/" + name));
    }
}
