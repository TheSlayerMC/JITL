package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.EnumMap;

public enum JToolTiers {


    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 4, -2.8F),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 2, -2.4F),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 7, -3.1F),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 2, -3F),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 0),

    LUNIUM_SWORD(JToolTier.LUNIUM, 5, -2.8F),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 1, -2.4F),
    LUNIUM_AXE(JToolTier.LUNIUM, 7, -3.1F),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 1, -3F),
    LUNIUM_HOE(JToolTier.LUNIUM, 0),

    SHADIUM_SWORD(JToolTier.SHADIUM, 5, -2.8F),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 1, -2.4F),
    SHADIUM_AXE(JToolTier.SHADIUM, 7, -3.1F),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 1, -3F),
    SHADIUM_HOE(JToolTier.SHADIUM, 0),

    BLOODCRUST_SWORD(JToolTier.BLOODCRUST, 5, -2.8F),
    BLOODCRUST_PICKAXE(JToolTier.BLOODCRUST, 2, -2.4F),
    BLOODCRUST_AXE(JToolTier.BLOODCRUST, 8, -3.1F),
    BLOODCRUST_SHOVEL(JToolTier.BLOODCRUST, 1, -3F),
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0),

    SOULSTONE_SWORD(JToolTier.SOULSTONE, 2, -2.8F),
    SOULSTONE_PICKAXE(JToolTier.SOULSTONE, 0, -2.4F),
    SOULSTONE_AXE(JToolTier.SOULSTONE, 6, -3.1F),
    SOULSTONE_SHOVEL(JToolTier.SOULSTONE, 0, -3F),
    SOULSTONE_HOE(JToolTier.SOULSTONE, 0),

    CELESTIUM_SWORD(JToolTier.CELESTIUM, 4, -2.8F),
    CELESTIUM_PICKAXE(JToolTier.CELESTIUM, 0, -2.4F),
    CELESTIUM_AXE(JToolTier.CELESTIUM, 6, -3.1F),
    CELESTIUM_SHOVEL(JToolTier.CELESTIUM, 0, -3F),
    CELESTIUM_HOE(JToolTier.CELESTIUM, 0),

    KORITE_SWORD(JToolTier.KORITE, 4, -2.8F),
    KORITE_PICKAXE(JToolTier.KORITE, 0, -2.4F),
    KORITE_AXE(JToolTier.KORITE, 6, -3.1F),
    KORITE_SHOVEL(JToolTier.KORITE, 0, -3F),
    KORITE_HOE(JToolTier.KORITE, 0),

    STORON_SWORD(JToolTier.STORON, 4, -2.8F),
    STORON_PICKAXE(JToolTier.STORON, 0, -2.4F),
    STORON_AXE(JToolTier.STORON, 6, -3.1F),
    STORON_SHOVEL(JToolTier.STORON, 0, -3F),
    STORON_HOE(JToolTier.STORON, 0),

    MEKYUM_SWORD(JToolTier.MEKYUM, 4, -2.8F),
    MEKYUM_PICKAXE(JToolTier.MEKYUM, 0, -2.4F),
    MEKYUM_AXE(JToolTier.MEKYUM, 6, -3.1F),
    MEKYUM_SHOVEL(JToolTier.MEKYUM, 0, -3F),
    MEKYUM_HOE(JToolTier.MEKYUM, 0),

    FLAIRIUM_SWORD(JToolTier.FLAIRIUM, 3, -2.8F),
    FLAIRIUM_PICKAXE(JToolTier.FLAIRIUM, 0, -2.4F),
    FLAIRIUM_AXE(JToolTier.FLAIRIUM, 5, -3.1F),
    FLAIRIUM_SHOVEL(JToolTier.FLAIRIUM, 0, -3F),
    FLAIRIUM_HOE(JToolTier.FLAIRIUM, 0),

    DES_SWORD(JToolTier.DES, 3, -2.8F),
    DES_PICKAXE(JToolTier.DES, 0, -2.4F),
    DES_AXE(JToolTier.DES, 5, -3.1F),
    DES_SHOVEL(JToolTier.DES, 0, -3F),
    DES_HOE(JToolTier.DES, 0, 0F),

    GORBITE_SWORD(JToolTier.GORBITE, 3, -2.8F),
    GORBITE_PICKAXE(JToolTier.GORBITE, 0, -2.4F),
    GORBITE_AXE(JToolTier.GORBITE, 4, -3.1F),
    GORBITE_SHOVEL(JToolTier.GORBITE, 0, -3F),
    GORBITE_HOE(JToolTier.GORBITE, 0, 0F),

    ORBADITE_SWORD(JToolTier.ORBADITE, 3, -2.8F),
    ORBADITE_PICKAXE(JToolTier.ORBADITE, 0, -2.4F),
    ORBADITE_AXE(JToolTier.ORBADITE, 4, -3.1F),
    ORBADITE_SHOVEL(JToolTier.ORBADITE, 0, -3F),
    ORBADITE_HOE(JToolTier.ORBADITE, 0, 0F),


    CHAMPIONS_SWORD(JToolTier.CHAMPIONS_SWORD, 0, -2.8F),
    THE_WRAITH(JToolTier.THE_WRAITH, 0, -2.8F),

    //overworld
    POISON_SWORD(JToolTier.POISON_SWORD, 2, -2.8F),
    CLOUD_SLICER(JToolTier.CLOUD_SLICER, 2, -2.8F),
    DRAGONS_TOOTH(JToolTier.DRAGONS_TOOTH, 5, -2.8F),
    DEMONIC_SWORD(JToolTier.DEMONIC_SWORD, 4, -2.8F),
    PEDAL_SWORD(JToolTier.PEDAL_SWORD, 4, -2.8F),
    RE_CRYSTAL_SWORD(JToolTier.RE_CRYSTAL_SWORD, 5, -2.8F),
    RE_STONE_SWORD(JToolTier.RE_STONE_SWORD, 4, -2.8F),
    CRYSTAL_BLADE(JToolTier.CRYSTAL_BLADE, 5, -2.8F),

    LIFE_HOE(JToolTier.LIFE_HOE, 0),
    EARTH_HOE(JToolTier.EARTH_HOE, 0),


    //frozen
    SNOW_SHOVELER(JToolTier.SNOW_SHOVELER, 6, -2.8F),
    FROSTBITTEN_SWORD(JToolTier.FROSTBITTEN_SWORD, 6, -2.8F),
    FROSTY_SWORD(JToolTier.FROSTY_SWORD, 7, -2.8F),

    //nether
    WITHIC_BLADE(JToolTier.WITHIC_BLADE, 7, -2.8F),
    CALCIA_SWORD(JToolTier.CALCIA_SWORD, 9, -2.8F),
    WITHERING_BEAST_SWORD(JToolTier.WITHERING_BEAST_SWORD, 9, -2.8F),

    //boil
    BOILING_BLADE(JToolTier.BOILING_BLADE, 7, -2.8F),
    SIZZLER_SWORD(JToolTier.SIZZLER_SWORD, 6, -2.8F),
    BLOODWIELD_SWORD(JToolTier.BLOODWIELD_SWORD, 6, -2.8F),
    CHARRED_BLADE(JToolTier.CHARRED_BLADE, 7, -2.8F),
    MOLTEN_KNIFE(JToolTier.MOLTEN_KNIFE, 3, -2.8F),

    //euca
    CORE_MENDER(JToolTier.CORE_MENDER, 7, -2.8F),
    ROYAL_BLADE(JToolTier.ROYAL_BLADE, 7, -2.8F),
    ROYAL_STABBER(JToolTier.ROYAL_STABBER, 7, -2.8F),
    KINGS_SWORD(JToolTier.KINGS_SWORD, 5, -2.8F),

    //depths
    DEPTHS_DARKSWORD(JToolTier.DEPTHS_DARKSWORD, 9, -2.8F),
    DEPTHS_SLAYER(JToolTier.DEPTHS_SLAYER, 9, -2.8F),
    ROC_SWORD(JToolTier.ROC_SWORD, 13, -2.8F),
    SWORD_THUNDERBIRD(JToolTier.SWORD_THUNDERBIRD, 0, -2.8F),
    THUNDERBLADE(JToolTier.THUNDERBLADE, 0, -2.8F),
    BUBBLE_SWORD(JToolTier.BUBBLE_SWORD, 0, -2.8F),

    //corba
    VINESTRAND_BLADE(JToolTier.VINESTRAND_BLADE, 0, -2.8F),
    DARK_PINE_SWORD(JToolTier.DARK_PINE_SWORD, 0, -2.8F),
    NATURES_BLADE(JToolTier.NATURES_BLADE, 7, -2.8F),
    TREE_HUGGER(JToolTier.TREE_HUGGER, 10, -2.8F),
    HEALERS_BLADE(JToolTier.HEALERS_BLADE, 0, -2.8F),
    LOGGERS_SWORD(JToolTier.LOGGERS_SWORD, 5, -2.8F),
    SENTRY_SWORD(JToolTier.SENTRY_SWORD, 5, -2.8F),

    //terrania
    TERRALIGHT_BLADE(JToolTier.TERRALIGHT_BLADE, 0, -2.8F),
    TERRANA_SWORD(JToolTier.TERRANA_SWORD, 0, -2.8F),
    TERROLICA_SWORD(JToolTier.TERROLICA_SWORD, 7, -2.8F),
    VOLITE_SWORD(JToolTier.VOLITE_SWORD, 11, -2.8F),
    TERRONIC_BLADE(JToolTier.TERRONIC_BLADE, 0, -2.8F),

    //cloudia
    GOLEM_SWORD(JToolTier.GOLEM_SWORD, 3, -2.8F),
    STARLIGHT_BLADE(JToolTier.STARLIGHT_BLADE, 0, -2.8F),
    FLUFFY_BLADE(JToolTier.FLUFFY_BLADE, 0, -2.8F),
    DARK_KEEPER(JToolTier.DARK_KEEPER, 0, -2.8F),

    DEVELOPER_SWORD(JToolTier.DEVELOPER_SWORD, 1000, 10000),

    HAMMER(JToolTier.HAMMER, 0, -2.8F),
    BATTLE_AXE(JToolTier.BATTLE_AXE, 5, -3F),

    THROWING_KNIFE(JToolTier.THROWING_KNIFE, 0, -2.8F)
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

        public static final ToolMaterial LIFE_HOE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 126, 8F, 5F, 15, JTags.EMPTY);
        public static final ToolMaterial EARTH_HOE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 8F, 5F, 15, JTags.EMPTY);

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

        public static final ToolMaterial BATTLE_AXE = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,768, 12F, 7, 15, JTags.EMPTY);
        public static final ToolMaterial HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,642, 8F, 6, 25, JTags.EMPTY);
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
                Identifier pLayers
        ) {
            EnumMap<ArmorType, Integer> enummap = new EnumMap<>(ArmorType.class);

            for (ArmorType armoritem$type : ArmorType.values()) {
                enummap.put(armoritem$type, pDefense.get(armoritem$type));
            }
            return new ArmorMaterial(dur, enummap, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngridient, createId(pLayers));
        }
    }

    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    private static ResourceKey<EquipmentAsset> createId(Identifier loc) {
        return ResourceKey.create(ROOT_ID, loc);
    }
}
