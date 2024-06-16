package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

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

    private final Tier tier;
    private final int damage;
    private final float speedModifier;

    JToolTiers(Tier tier, int damage, float speed) {
        this.tier = tier;
        this.damage = damage;
        this.speedModifier = speed;
    }

    JToolTiers(Tier tier, int type) {
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

    public Tier getTier() {
        return tier;
    }

    public float getSpeedModifier() {
        return speedModifier;
    }

    public int getDamage() {
        return damage;
    }

    public static class JToolTier {

        public static final Tier SAPPHIRE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 900, 8F, 1F, 15, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final Tier LUNIUM = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1056, 8F, 2F, 15, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final Tier SHADIUM = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 2F, 15, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final Tier BLOODCRUST = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 2F, 15, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));

        //SORT BELOW
        public static final Tier CELESTIUM = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 11F, 5F, 15, () -> Ingredient.of(JItems.CELESTIUM_INGOT.get()));
        public static final Tier KORITE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 10F, 5F, 15, () -> Ingredient.of(JItems.KORITE_INGOT.get()));
        public static final Tier STORON = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 11F, 5F, 15, () -> Ingredient.of(JItems.STORON_INGOT.get()));
        public static final Tier MEKYUM = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1342, 10F, 5F, 15, () -> Ingredient.of(JItems.MEKYUM_INGOT.get()));

        public static final Tier FLAIRIUM = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 12F, 7F, 15, () -> Ingredient.of(JItems.MEKYUM_INGOT.get()));
        public static final Tier DES = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 12F, 7F, 15, () -> Ingredient.of(JItems.DES_INGOT.get()));

        public static final Tier GORBITE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 15F, 8F, 15, () -> Ingredient.of(JItems.GORBITE_GEM.get()));
        public static final Tier ORBADITE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 15F, 8F, 15, () -> Ingredient.of(JItems.ORBADITE_INGOT.get()));

        public static final Tier SOULSTONE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 8F, 5F, 15, () -> Ingredient.of(JItems.SOULSTONE.get()));

        public static final Tier CHAMPIONS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> null);
        public static final Tier THE_WRAITH = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> null);
        public static final Tier POISON_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> null);
        public static final Tier CLOUD_SLICER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> null);
        public static final Tier DRAGONS_TOOTH = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> null);
        public static final Tier DEMONIC_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> null);
        public static final Tier PEDAL_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> null);
        public static final Tier RE_CRYSTAL_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, () -> null);
        public static final Tier RE_STONE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, () -> null);
        public static final Tier CRYSTAL_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> null);
        public static final Tier SNOW_SHOVELER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 8F, 3F, 25, () -> null);
        public static final Tier FROSTBITTEN_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 8F, 3F, 25, () -> null);
        public static final Tier FROSTY_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1681, 8F, 3F, 25, () -> null);
        public static final Tier WITHIC_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier CALCIA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier WITHERING_BEAST_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier BOILING_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> null);
        public static final Tier SIZZLER_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> null);
        public static final Tier BLOODWIELD_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> null);
        public static final Tier CHARRED_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier MOLTEN_KNIFE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> null);
        public static final Tier CORE_MENDER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier ROYAL_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> null);
        public static final Tier ROYAL_STABBER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> null);
        public static final Tier KINGS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier DEPTHS_DARKSWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5, 25, () -> null);
        public static final Tier DEPTHS_SLAYER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> null);
        public static final Tier ROC_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> null);
        public static final Tier SWORD_THUNDERBIRD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> null);
        public static final Tier THUNDERBLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> null);
        public static final Tier BUBBLE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> null);
        public static final Tier VINESTRAND_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> null);
        public static final Tier DARK_PINE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> null);
        public static final Tier NATURES_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> null);
        public static final Tier TREE_HUGGER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> null);
        public static final Tier HEALERS_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> null);
        public static final Tier LOGGERS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> null);
        public static final Tier SENTRY_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> null);
        public static final Tier TERRALIGHT_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> null);
        public static final Tier TERRANA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> null);
        public static final Tier TERROLICA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> null);
        public static final Tier VOLITE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> null);
        public static final Tier TERRONIC_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7F, 25, () -> null);
        public static final Tier GOLEM_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 9F, 25, () -> null);
        public static final Tier STARLIGHT_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> null);
        public static final Tier FLUFFY_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 15F, 25, () -> null);
        public static final Tier DARK_KEEPER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> null);

        public static final Tier DEVELOPER_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 1000F, 25, () -> null);
        public static final Tier THROWING_KNIFE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, -1, 3F, 2F, 25, () -> null);
    }

    public static class JArmorTier {

        public static final Holder<ArmorMaterial> SAPPHIRE = register("sapphire", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 2);
                    armor.put(ArmorItem.Type.LEGGINGS, 5);
                    armor.put(ArmorItem.Type.CHESTPLATE, 6);
                    armor.put(ArmorItem.Type.HELMET, 2);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SAPPHIRE.get()));

        public static final Holder<ArmorMaterial> LUNIUM = register("lunium", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 2);
                    armor.put(ArmorItem.Type.LEGGINGS, 5);
                    armor.put(ArmorItem.Type.CHESTPLATE, 6);
                    armor.put(ArmorItem.Type.HELMET, 2);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 30, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));

        public static final Holder<ArmorMaterial> SHADIUM = register("shadium", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 3);
                    armor.put(ArmorItem.Type.LEGGINGS, 6);
                    armor.put(ArmorItem.Type.CHESTPLATE, 8);
                    armor.put(ArmorItem.Type.HELMET, 3);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));

        public static final Holder<ArmorMaterial> BLOODCRUST = register("bloodcrust", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 3);
                    armor.put(ArmorItem.Type.LEGGINGS, 5);
                    armor.put(ArmorItem.Type.CHESTPLATE, 7);
                    armor.put(ArmorItem.Type.HELMET, 3);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));

        public static final Holder<ArmorMaterial> CELESTIUM = register("celestium", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 5);
                    armor.put(ArmorItem.Type.LEGGINGS, 7);
                    armor.put(ArmorItem.Type.CHESTPLATE, 8);
                    armor.put(ArmorItem.Type.HELMET, 5);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.CELESTIUM_INGOT.get()));

        public static final Holder<ArmorMaterial> FLAIRIUM = register("flairium", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 5);
                    armor.put(ArmorItem.Type.LEGGINGS, 7);
                    armor.put(ArmorItem.Type.CHESTPLATE, 8);
                    armor.put(ArmorItem.Type.HELMET, 5);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.FLAIRIUM_INGOT.get()));

        public static final Holder<ArmorMaterial> GORBITE = register("gorbite", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.GORBITE_GEM.get()));

        public static final Holder<ArmorMaterial> ORBADITE = register("orbadite", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.ORBADITE_INGOT.get()));

        public static final Holder<ArmorMaterial> TWILIGHT = register("twilight", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        public static final Holder<ArmorMaterial> FLAME = register("flame", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        public static final Holder<ArmorMaterial> HOLLOW = register("hollow", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        public static final Holder<ArmorMaterial> CRYSTAL_FLAKE = register("crystal_flake", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        public static final Holder<ArmorMaterial> FROSTBITTEN = register("frostbitten", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        public static final Holder<ArmorMaterial> HELLMETAL = register("hellmetal", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, null);

        private static Holder<ArmorMaterial> register(
                String pName,
                EnumMap<ArmorItem.Type, Integer> pDefense,
                int pEnchantmentValue,
                Holder<SoundEvent> pEquipSound,
                float pToughness,
                float pKnockbackResistance,
                Supplier<Ingredient> pRepairIngredient
        ) {
            List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(JITL.rl(pName)));
            return register(pName, pDefense, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngredient, list);
        }

        private static Holder<ArmorMaterial> register(
                String pName,
                EnumMap<ArmorItem.Type, Integer> pDefense,
                int pEnchantmentValue,
                Holder<SoundEvent> pEquipSound,
                float pToughness,
                float pKnockbackResistance,
                Supplier<Ingredient> pRepairIngridient,
                List<ArmorMaterial.Layer> pLayers
        ) {
            EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

            for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
                enummap.put(armoritem$type, pDefense.get(armoritem$type));
            }

            return Registry.registerForHolder(
                    BuiltInRegistries.ARMOR_MATERIAL,
                    ResourceLocation.withDefaultNamespace(pName),
                    new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
            );
        }
    }
}
