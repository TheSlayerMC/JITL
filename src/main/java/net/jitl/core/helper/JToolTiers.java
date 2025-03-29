package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public enum JToolTiers {


    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 4, 0F),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 2, 0F),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 7, 0F),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 2, 0F),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 0),

    LUNIUM_SWORD(JToolTier.LUNIUM, 5, 0F),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 1, 0F),
    LUNIUM_AXE(JToolTier.LUNIUM, 7, 0F),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 1, 0F),
    LUNIUM_HOE(JToolTier.LUNIUM, 0),

    SHADIUM_SWORD(JToolTier.SHADIUM, 5, 0F),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 1, 0F),
    SHADIUM_AXE(JToolTier.SHADIUM, 7, 0F),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 1, 0F),
    SHADIUM_HOE(JToolTier.SHADIUM, 0),

    BLOODCRUST_SWORD(JToolTier.BLOODCRUST, 5, 0F),
    BLOODCRUST_PICKAXE(JToolTier.BLOODCRUST, 2, 0F),
    BLOODCRUST_AXE(JToolTier.BLOODCRUST, 8, 0F),
    BLOODCRUST_SHOVEL(JToolTier.BLOODCRUST, 1, 0F),
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0),

    SOULSTONE_SWORD(JToolTier.SOULSTONE, 2, 0F),
    SOULSTONE_PICKAXE(JToolTier.SOULSTONE, 0, 0F),
    SOULSTONE_AXE(JToolTier.SOULSTONE, 6, 0F),
    SOULSTONE_SHOVEL(JToolTier.SOULSTONE, 0, 0F),
    SOULSTONE_HOE(JToolTier.SOULSTONE, 0),

    CELESTIUM_SWORD(JToolTier.CELESTIUM, 4, 0F),
    CELESTIUM_PICKAXE(JToolTier.CELESTIUM, 0, 0F),
    CELESTIUM_AXE(JToolTier.CELESTIUM, 6, 0F),
    CELESTIUM_SHOVEL(JToolTier.CELESTIUM, 0, 0F),
    CELESTIUM_HOE(JToolTier.CELESTIUM, 0),

    KORITE_SWORD(JToolTier.KORITE, 4, 0F),
    KORITE_PICKAXE(JToolTier.KORITE, 0, 0F),
    KORITE_AXE(JToolTier.KORITE, 6, 0F),
    KORITE_SHOVEL(JToolTier.KORITE, 0, 0F),
    KORITE_HOE(JToolTier.KORITE, 0),

    STORON_SWORD(JToolTier.STORON, 4, 0F),
    STORON_PICKAXE(JToolTier.STORON, 0, 0F),
    STORON_AXE(JToolTier.STORON, 6, 0F),
    STORON_SHOVEL(JToolTier.STORON, 0, 0F),
    STORON_HOE(JToolTier.STORON, 0),

    MEKYUM_SWORD(JToolTier.MEKYUM, 4, 0F),
    MEKYUM_PICKAXE(JToolTier.MEKYUM, 0, 0F),
    MEKYUM_AXE(JToolTier.MEKYUM, 6, 0F),
    MEKYUM_SHOVEL(JToolTier.MEKYUM, 0, 0F),
    MEKYUM_HOE(JToolTier.MEKYUM, 0),

    FLAIRIUM_SWORD(JToolTier.FLAIRIUM, 3, 0F),
    FLAIRIUM_PICKAXE(JToolTier.FLAIRIUM, 0, 0F),
    FLAIRIUM_AXE(JToolTier.FLAIRIUM, 5, 0F),
    FLAIRIUM_SHOVEL(JToolTier.FLAIRIUM, 0, 0F),
    FLAIRIUM_HOE(JToolTier.FLAIRIUM, 0),

    DES_SWORD(JToolTier.DES, 3, 0F),
    DES_PICKAXE(JToolTier.DES, 0, 0F),
    DES_AXE(JToolTier.DES, 5, 0F),
    DES_SHOVEL(JToolTier.DES, 0, 0F),
    DES_HOE(JToolTier.DES, 0, 0F),

    GORBITE_SWORD(JToolTier.GORBITE, 3, 0F),
    GORBITE_PICKAXE(JToolTier.GORBITE, 0, 0F),
    GORBITE_AXE(JToolTier.GORBITE, 4, 0F),
    GORBITE_SHOVEL(JToolTier.GORBITE, 0, 0F),
    GORBITE_HOE(JToolTier.GORBITE, 0, 0F),

    ORBADITE_SWORD(JToolTier.ORBADITE, 3, 0F),
    ORBADITE_PICKAXE(JToolTier.ORBADITE, 0, 0F),
    ORBADITE_AXE(JToolTier.ORBADITE, 4, 0F),
    ORBADITE_SHOVEL(JToolTier.ORBADITE, 0, 0F),
    ORBADITE_HOE(JToolTier.ORBADITE, 0, 0F),


    CHAMPIONS_SWORD(JToolTier.CHAMPIONS_SWORD, 0, 0),
    THE_WRAITH(JToolTier.THE_WRAITH, 0, 0),

    //overworld
    POISON_SWORD(JToolTier.POISON_SWORD, 2, 0),
    CLOUD_SLICER(JToolTier.CLOUD_SLICER, 2, 0),
    DRAGONS_TOOTH(JToolTier.DRAGONS_TOOTH, 5, 0),
    DEMONIC_SWORD(JToolTier.DEMONIC_SWORD, 4, 0),
    PEDAL_SWORD(JToolTier.PEDAL_SWORD, 4, 0),
    RE_CRYSTAL_SWORD(JToolTier.RE_CRYSTAL_SWORD, 5, 0),
    RE_STONE_SWORD(JToolTier.RE_STONE_SWORD, 4, 0),
    CRYSTAL_BLADE(JToolTier.CRYSTAL_BLADE, 5, 0),

    //frozen
    SNOW_SHOVELER(JToolTier.SNOW_SHOVELER, 6, 0),
    FROSTBITTEN_SWORD(JToolTier.FROSTBITTEN_SWORD, 6, 0),
    FROSTY_SWORD(JToolTier.FROSTY_SWORD, 7, 0),

    //nether
    WITHIC_BLADE(JToolTier.WITHIC_BLADE, 7, 0),
    CALCIA_SWORD(JToolTier.CALCIA_SWORD, 9, 0),
    WITHERING_BEAST_SWORD(JToolTier.WITHERING_BEAST_SWORD, 9, 0),

    //boil
    BOILING_BLADE(JToolTier.BOILING_BLADE, 7, 0),
    SIZZLER_SWORD(JToolTier.SIZZLER_SWORD, 6, 0),
    BLOODWIELD_SWORD(JToolTier.BLOODWIELD_SWORD, 6, 0),
    CHARRED_BLADE(JToolTier.CHARRED_BLADE, 7, 0),
    MOLTEN_KNIFE(JToolTier.MOLTEN_KNIFE, 3, 0),

    //euca
    CORE_MENDER(JToolTier.CORE_MENDER, 7, 0),
    ROYAL_BLADE(JToolTier.ROYAL_BLADE, 7, 0),
    ROYAL_STABBER(JToolTier.ROYAL_STABBER, 7, 0),
    KINGS_SWORD(JToolTier.KINGS_SWORD, 5, 0),

    //depths
    DEPTHS_DARKSWORD(JToolTier.DEPTHS_DARKSWORD, 9, 0),
    DEPTHS_SLAYER(JToolTier.DEPTHS_SLAYER, 9, 0),
    ROC_SWORD(JToolTier.ROC_SWORD, 13, 0),
    SWORD_THUNDERBIRD(JToolTier.SWORD_THUNDERBIRD, 0, 0),
    THUNDERBLADE(JToolTier.THUNDERBLADE, 0, 0),
    BUBBLE_SWORD(JToolTier.BUBBLE_SWORD, 0, 0),

    //corba
    VINESTRAND_BLADE(JToolTier.VINESTRAND_BLADE, 0, 0),
    DARK_PINE_SWORD(JToolTier.DARK_PINE_SWORD, 0, 0),
    NATURES_BLADE(JToolTier.NATURES_BLADE, 7, 0),
    TREE_HUGGER(JToolTier.TREE_HUGGER, 10, 0),
    HEALERS_BLADE(JToolTier.HEALERS_BLADE, 0, 0),
    LOGGERS_SWORD(JToolTier.LOGGERS_SWORD, 5, 0),
    SENTRY_SWORD(JToolTier.SENTRY_SWORD, 5, 0),

    //terrania
    TERRALIGHT_BLADE(JToolTier.TERRALIGHT_BLADE, 0, 0),
    TERRANA_SWORD(JToolTier.TERRANA_SWORD, 0, 0),
    TERROLICA_SWORD(JToolTier.TERROLICA_SWORD, 7, 0),
    VOLITE_SWORD(JToolTier.VOLITE_SWORD, 11, 0),
    TERRONIC_BLADE(JToolTier.TERRONIC_BLADE, 0, 0),

    //cloudia
    GOLEM_SWORD(JToolTier.GOLEM_SWORD, 3, 0),
    STARLIGHT_BLADE(JToolTier.STARLIGHT_BLADE, 0, 0),
    FLUFFY_BLADE(JToolTier.FLUFFY_BLADE, 0, 0),
    DARK_KEEPER(JToolTier.DARK_KEEPER, 0, 0),

    DEVELOPER_SWORD(JToolTier.DEVELOPER_SWORD, 1000, 10000),

    THROWING_KNIFE(JToolTier.THROWING_KNIFE, 0, 0)
    ;

    private final ToolMaterial tier;
    private final int damage;
    private final float speedModifier;

    JToolTiers(ToolMaterial tier, int damage, float speed) {
        this.tier = tier;
        this.damage = damage;
        this.speedModifier = speed;
    }

    JToolTiers(ToolMaterial tier, int type) {
        int d = 0;//hoe
        this.tier = tier;
        if(type == 1){ //sword
            d = 2;
        }
        if(type == 2){//pickaxe
            d = 0;
        }
        if(type == 3){//axe
            d = 3;
        }
        if(type == 4){//shovel
            d = -1;
        }
        damage = d;
        this.speedModifier = 0;
    }

    public ToolMaterial getTier() {
        return tier;
    }

    public float getSpeedModifier() {
        return speedModifier;
    }

    public int getDamage() {
        return damage;
    }

    public static class JToolTier {

        public static final ToolMaterial SAPPHIRE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 900, 8F, 1F, 15, JTags.SAPPHIRE);
        public static final ToolMaterial LUNIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1056, 8F, 2F, 15, JTags.LUNIUM);
        public static final ToolMaterial SHADIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 2F, 15, JTags.SHADIUM);
        public static final ToolMaterial BLOODCRUST = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 2F, 15, JTags.BLOODCRUST);

        //SORT BELOW
        public static final ToolMaterial CELESTIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 11F, 5F, 15, JTags.CELESTIUM);
        public static final ToolMaterial KORITE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 10F, 5F, 15, JTags.KORITE);
        public static final ToolMaterial STORON = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 11F, 5F, 15, JTags.STORON);
        public static final ToolMaterial MEKYUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 10F, 5F, 15, JTags.MEKYUM);

        public static final ToolMaterial FLAIRIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 12F, 7F, 15, JTags.FLAIRIUM);
        public static final ToolMaterial DES = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 12F, 7F, 15, JTags.DES);

        public static final ToolMaterial GORBITE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 15F, 8F, 15, JTags.GORBITE);
        public static final ToolMaterial ORBADITE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 15F, 8F, 15, JTags.ORBADITE);

        public static final ToolMaterial SOULSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 8F, 5F, 15, JTags.SOULSTONE);

        public static final ToolMaterial CHAMPIONS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial THE_WRAITH = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial POISON_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial CLOUD_SLICER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial DRAGONS_TOOTH = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial DEMONIC_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial PEDAL_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial RE_CRYSTAL_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial RE_STONE_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial CRYSTAL_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial SNOW_SHOVELER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial FROSTBITTEN_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial FROSTY_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1681, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial WITHIC_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial CALCIA_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial WITHERING_BEAST_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial BOILING_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial SIZZLER_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial BLOODWIELD_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial CHARRED_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial MOLTEN_KNIFE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial CORE_MENDER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial ROYAL_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, JTags.EMPTY);
        public static final ToolMaterial ROYAL_STABBER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, JTags.EMPTY);
        public static final ToolMaterial KINGS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial DEPTHS_DARKSWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5, 25, JTags.EMPTY);
        public static final ToolMaterial DEPTHS_SLAYER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, JTags.EMPTY);
        public static final ToolMaterial ROC_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, JTags.EMPTY);
        public static final ToolMaterial SWORD_THUNDERBIRD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, JTags.EMPTY);
        public static final ToolMaterial THUNDERBLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, JTags.EMPTY);
        public static final ToolMaterial BUBBLE_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, JTags.EMPTY);
        public static final ToolMaterial VINESTRAND_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, JTags.EMPTY);
        public static final ToolMaterial DARK_PINE_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, JTags.EMPTY);
        public static final ToolMaterial NATURES_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, JTags.EMPTY);
        public static final ToolMaterial TREE_HUGGER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, JTags.EMPTY);
        public static final ToolMaterial HEALERS_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, JTags.EMPTY);
        public static final ToolMaterial LOGGERS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, JTags.EMPTY);
        public static final ToolMaterial SENTRY_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, JTags.EMPTY);
        public static final ToolMaterial TERRALIGHT_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, JTags.EMPTY);
        public static final ToolMaterial TERRANA_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, JTags.EMPTY);
        public static final ToolMaterial TERROLICA_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, JTags.EMPTY);
        public static final ToolMaterial VOLITE_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, JTags.EMPTY);
        public static final ToolMaterial TERRONIC_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7F, 25, JTags.EMPTY);
        public static final ToolMaterial GOLEM_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 9F, 25, JTags.EMPTY);
        public static final ToolMaterial STARLIGHT_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, JTags.EMPTY);
        public static final ToolMaterial FLUFFY_BLADE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 15F, 25, JTags.EMPTY);
        public static final ToolMaterial DARK_KEEPER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, JTags.EMPTY);

        public static final ToolMaterial DEVELOPER_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 1000F, 25, JTags.EMPTY);
        public static final ToolMaterial THROWING_KNIFE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, -1, 3F, 2F, 25, JTags.EMPTY);
    }

    public static class JArmorTier {

        public static final ArmorMaterial SAPPHIRE = register("sapphire", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 2);
                    armor.put(ArmorType.LEGGINGS, 5);
                    armor.put(ArmorType.CHESTPLATE, 6);
                    armor.put(ArmorType.HELMET, 2);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.SAPPHIRE);

        public static final ArmorMaterial LUNIUM = register("lunium", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 2);
                    armor.put(ArmorType.LEGGINGS, 5);
                    armor.put(ArmorType.CHESTPLATE, 6);
                    armor.put(ArmorType.HELMET, 2);
                    armor.put(ArmorType.BODY, 5);
                }), 30, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.LUNIUM);

        public static final ArmorMaterial SHADIUM = register("shadium", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 3);
                    armor.put(ArmorType.LEGGINGS, 6);
                    armor.put(ArmorType.CHESTPLATE, 8);
                    armor.put(ArmorType.HELMET, 3);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.SHADIUM);

        public static final ArmorMaterial BLOODCRUST = register("bloodcrust", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 3);
                    armor.put(ArmorType.LEGGINGS, 5);
                    armor.put(ArmorType.CHESTPLATE, 7);
                    armor.put(ArmorType.HELMET, 3);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.BLOODCRUST);

        public static final ArmorMaterial CELESTIUM = register("celestium", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 5);
                    armor.put(ArmorType.LEGGINGS, 7);
                    armor.put(ArmorType.CHESTPLATE, 8);
                    armor.put(ArmorType.HELMET, 5);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.CELESTIUM);

        public static final ArmorMaterial FLAIRIUM = register("flairium", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 5);
                    armor.put(ArmorType.LEGGINGS, 7);
                    armor.put(ArmorType.CHESTPLATE, 8);
                    armor.put(ArmorType.HELMET, 5);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.FLAIRIUM);

        public static final ArmorMaterial GORBITE = register("gorbite", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.GORBITE);

        public static final ArmorMaterial ORBADITE = register("orbadite", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.ORBADITE);

        public static final ArmorMaterial TWILIGHT = register("twilight", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        public static final ArmorMaterial FLAME = register("flame", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        public static final ArmorMaterial HOLLOW = register("hollow", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        public static final ArmorMaterial CRYSTAL_FLAKE = register("crystal_flake", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        public static final ArmorMaterial FROSTBITTEN = register("frostbitten", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        public static final ArmorMaterial HELLMETAL = register("hellmetal", 1000, Util.make(new EnumMap<>(ArmorType.class),
                armor -> {
                    armor.put(ArmorType.BOOTS, 8);
                    armor.put(ArmorType.LEGGINGS, 9);
                    armor.put(ArmorType.CHESTPLATE, 10);
                    armor.put(ArmorType.HELMET, 8);
                    armor.put(ArmorType.BODY, 5);
                }), 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, JTags.EMPTY);

        private static ArmorMaterial register(
                String pName,
                int dur,
                EnumMap<ArmorType, Integer> pDefense,
                int pEnchantmentValue,
                Holder<SoundEvent> pEquipSound,
                float pToughness,
                float pKnockbackResistance,
                TagKey<Item> pRepairIngredient
        ) {
            return register(dur, pDefense, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngredient, JITL.rl(pName));
        }

        private static ArmorMaterial register(
                int dur,
                EnumMap<ArmorType, Integer> pDefense,
                int pEnchantmentValue,
                Holder<SoundEvent> pEquipSound,
                float pToughness,
                float pKnockbackResistance,
                TagKey<Item> pRepairIngridient,
                ResourceLocation pLayers
        ) {
            EnumMap<ArmorType, Integer> enummap = new EnumMap<>(ArmorType.class);

            for (ArmorType armoritem$type : ArmorType.values()) {
                enummap.put(armoritem$type, pDefense.get(armoritem$type));
            }
            return new ArmorMaterial(dur, enummap, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngridient, createId(pLayers));
        }
    }

    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));

    private static ResourceKey<EquipmentAsset> createId(ResourceLocation loc) {
        return ResourceKey.create(ROOT_ID, loc);
    }
}
