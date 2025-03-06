package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public enum JToolTiers {

    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 4, -2.4F),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 2, -2.8F),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 7, -3F),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 2, -3F),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 0),

    LUNIUM_SWORD(JToolTier.LUNIUM, 5, -2.4F),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 1, -2.8F),
    LUNIUM_AXE(JToolTier.LUNIUM, 7, -3F),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 1, -3F),
    LUNIUM_HOE(JToolTier.LUNIUM, 0),

    SHADIUM_SWORD(JToolTier.SHADIUM, 5, -2.4F),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 1, -2.8F),
    SHADIUM_AXE(JToolTier.SHADIUM, 7, -3F),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 1, -3F),
    SHADIUM_HOE(JToolTier.SHADIUM, 0),

    BLOODCRUST_SWORD(JToolTier.BLOODCRUST, 5, -2.4F),
    BLOODCRUST_PICKAXE(JToolTier.BLOODCRUST, 2, -2.8F),
    BLOODCRUST_AXE(JToolTier.BLOODCRUST, 8, -3F),
    BLOODCRUST_SHOVEL(JToolTier.BLOODCRUST, 1, -3F),
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0),

    SOULSTONE_SWORD(JToolTier.SOULSTONE, 2, -2.4F),
    SOULSTONE_PICKAXE(JToolTier.SOULSTONE, 0, -2.8F),
    SOULSTONE_AXE(JToolTier.SOULSTONE, 6, -3F),
    SOULSTONE_SHOVEL(JToolTier.SOULSTONE, 0, -3F),
    SOULSTONE_HOE(JToolTier.SOULSTONE, 0),

    CELESTIUM_SWORD(JToolTier.CELESTIUM, 4, -2.4F),
    CELESTIUM_PICKAXE(JToolTier.CELESTIUM, 0, -2.8F),
    CELESTIUM_AXE(JToolTier.CELESTIUM, 6, -3F),
    CELESTIUM_SHOVEL(JToolTier.CELESTIUM, 0, -3F),
    CELESTIUM_HOE(JToolTier.CELESTIUM, 0),

    KORITE_SWORD(JToolTier.KORITE, 4, -2.4F),
    KORITE_PICKAXE(JToolTier.KORITE, 0, -2.8F),
    KORITE_AXE(JToolTier.KORITE, 6, -3F),
    KORITE_SHOVEL(JToolTier.KORITE, 0, -3F),
    KORITE_HOE(JToolTier.KORITE, 0),

    STORON_SWORD(JToolTier.STORON, 4, -2.4F),
    STORON_PICKAXE(JToolTier.STORON, 0, -2.8F),
    STORON_AXE(JToolTier.STORON, 6, -3F),
    STORON_SHOVEL(JToolTier.STORON, 0, -3F),
    STORON_HOE(JToolTier.STORON, 0),

    MEKYUM_SWORD(JToolTier.MEKYUM, 4, -2.4F),
    MEKYUM_PICKAXE(JToolTier.MEKYUM, 0, -2.8F),
    MEKYUM_AXE(JToolTier.MEKYUM, 6, -3F),
    MEKYUM_SHOVEL(JToolTier.MEKYUM, 0, -3F),
    MEKYUM_HOE(JToolTier.MEKYUM, 0),

    FLAIRIUM_SWORD(JToolTier.FLAIRIUM, 3, -2.4F),
    FLAIRIUM_PICKAXE(JToolTier.FLAIRIUM, 0, -2.8F),
    FLAIRIUM_AXE(JToolTier.FLAIRIUM, 5, -3F),
    FLAIRIUM_SHOVEL(JToolTier.FLAIRIUM, 0, -3F),
    FLAIRIUM_HOE(JToolTier.FLAIRIUM, 0),

    DES_SWORD(JToolTier.DES, 3, -2.4F),
    DES_PICKAXE(JToolTier.DES, 0, -2.8F),
    DES_AXE(JToolTier.DES, 5, -3F),
    DES_SHOVEL(JToolTier.DES, 0, -3F),
    DES_HOE(JToolTier.DES, 0, 0F),

    GORBITE_SWORD(JToolTier.GORBITE, 3, -2.4F),
    GORBITE_PICKAXE(JToolTier.GORBITE, 0, -2.8F),
    GORBITE_AXE(JToolTier.GORBITE, 4, -3F),
    GORBITE_SHOVEL(JToolTier.GORBITE, 0, -3F),
    GORBITE_HOE(JToolTier.GORBITE, 0, 0F),

    ORBADITE_SWORD(JToolTier.ORBADITE, 3, -2.4F),
    ORBADITE_PICKAXE(JToolTier.ORBADITE, 0, -2.8F),
    ORBADITE_AXE(JToolTier.ORBADITE, 4, -3F),
    ORBADITE_SHOVEL(JToolTier.ORBADITE, 0, -3F),
    ORBADITE_HOE(JToolTier.ORBADITE, 0, 0F),


    CHAMPIONS_SWORD(JToolTier.CHAMPIONS_SWORD, 0, -2.4F),
    THE_WRAITH(JToolTier.THE_WRAITH, 0, -2.4F),

    //overworld
    POISON_SWORD(JToolTier.POISON_SWORD, 2, -2.4F),
    CLOUD_SLICER(JToolTier.CLOUD_SLICER, 2, -2.4F),
    DRAGONS_TOOTH(JToolTier.DRAGONS_TOOTH, 5, -2.4F),
    DEMONIC_SWORD(JToolTier.DEMONIC_SWORD, 4, -2.4F),
    PEDAL_SWORD(JToolTier.PEDAL_SWORD, 4, -2.4F),
    RE_CRYSTAL_SWORD(JToolTier.RE_CRYSTAL_SWORD, 5, -2.4F),
    RE_STONE_SWORD(JToolTier.RE_STONE_SWORD, 4, -2.4F),
    CRYSTAL_BLADE(JToolTier.CRYSTAL_BLADE, 5, -2.4F),

    //frozen
    SNOW_SHOVELER(JToolTier.SNOW_SHOVELER, 6, -2.4F),
    FROSTBITTEN_SWORD(JToolTier.FROSTBITTEN_SWORD, 9, -2.4F),
    FROSTY_SWORD(JToolTier.FROSTY_SWORD, 7, -2.4F),

    //nether
    WITHIC_BLADE(JToolTier.WITHIC_BLADE, 7, -2.4F),
    CALCIA_SWORD(JToolTier.CALCIA_SWORD, 9, -2.4F),
    WITHERING_BEAST_SWORD(JToolTier.WITHERING_BEAST_SWORD, 9, -2.4F),

    //boil
    BOILING_BLADE(JToolTier.BOILING_BLADE, 7, -2.4F),
    SIZZLER_SWORD(JToolTier.SIZZLER_SWORD, 6, -2.4F),
    BLOODWIELD_SWORD(JToolTier.BLOODWIELD_SWORD, 6, -2.4F),
    CHARRED_BLADE(JToolTier.CHARRED_BLADE, 7, -2.4F),
    MOLTEN_KNIFE(JToolTier.MOLTEN_KNIFE, 3, -2.4F),

    //euca
    CORE_MENDER(JToolTier.CORE_MENDER, 7, -2.4F),
    ROYAL_BLADE(JToolTier.ROYAL_BLADE, 7, -2.4F),
    ROYAL_STABBER(JToolTier.ROYAL_STABBER, 7, -2.4F),
    KINGS_SWORD(JToolTier.KINGS_SWORD, 5, -2.4F),

    //depths
    DEPTHS_DARKSWORD(JToolTier.DEPTHS_DARKSWORD, 9, -2.4F),
    DEPTHS_SLAYER(JToolTier.DEPTHS_SLAYER, 9, -2.4F),
    ROC_SWORD(JToolTier.ROC_SWORD, 13, -2.4F),
    SWORD_THUNDERBIRD(JToolTier.SWORD_THUNDERBIRD, 0, -2.4F),
    THUNDERBLADE(JToolTier.THUNDERBLADE, 0, -2.4F),
    BUBBLE_SWORD(JToolTier.BUBBLE_SWORD, 0, -2.4F),

    //corba
    VINESTRAND_BLADE(JToolTier.VINESTRAND_BLADE, 0, -2.4F),
    DARK_PINE_SWORD(JToolTier.DARK_PINE_SWORD, 0, -2.4F),
    NATURES_BLADE(JToolTier.NATURES_BLADE, 7, -2.4F),
    TREE_HUGGER(JToolTier.TREE_HUGGER, 10, -2.4F),
    HEALERS_BLADE(JToolTier.HEALERS_BLADE, 0, -2.4F),
    LOGGERS_SWORD(JToolTier.LOGGERS_SWORD, 5, -2.4F),
    SENTRY_SWORD(JToolTier.SENTRY_SWORD, 5, -2.4F),

    //terrania
    TERRALIGHT_BLADE(JToolTier.TERRALIGHT_BLADE, 0, -2.4F),
    TERRANA_SWORD(JToolTier.TERRANA_SWORD, 0, -2.4F),
    TERROLICA_SWORD(JToolTier.TERROLICA_SWORD, 7, -2.4F),
    VOLITE_SWORD(JToolTier.VOLITE_SWORD, 11, -2.4F),
    TERRONIC_BLADE(JToolTier.TERRONIC_BLADE, 0, -2.4F),

    //cloudia
    GOLEM_SWORD(JToolTier.GOLEM_SWORD, 3, -2.4F),
    STARLIGHT_BLADE(JToolTier.STARLIGHT_BLADE, 0, -2.4F),
    FLUFFY_BLADE(JToolTier.FLUFFY_BLADE, 0, -2.4F),
    DARK_KEEPER(JToolTier.DARK_KEEPER, 0, -2.4F),

    DEVELOPER_SWORD(JToolTier.DEVELOPER_SWORD, 1000, 1000),

    EARTHERN_HAMMER(JToolTier.EARTHERN_HAMMER, 0, -2.4F),


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

        public static final Tier CHAMPIONS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier THE_WRAITH = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier POISON_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier CLOUD_SLICER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1483, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier DRAGONS_TOOTH = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier DEMONIC_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier PEDAL_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier RE_CRYSTAL_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier RE_STONE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2015, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier CRYSTAL_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1056, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier SNOW_SHOVELER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1883, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier FROSTBITTEN_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2056, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier FROSTY_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1681, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier WITHIC_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier CALCIA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier WITHERING_BEAST_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier BOILING_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier SIZZLER_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier BLOODWIELD_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier CHARRED_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier MOLTEN_KNIFE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier CORE_MENDER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier ROYAL_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> Ingredient.EMPTY);
        public static final Tier ROYAL_STABBER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 3F, 25, () -> Ingredient.EMPTY);
        public static final Tier KINGS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier DEPTHS_DARKSWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5, 25, () -> Ingredient.EMPTY);
        public static final Tier DEPTHS_SLAYER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 5F, 25, () -> Ingredient.EMPTY);
        public static final Tier ROC_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> Ingredient.EMPTY);
        public static final Tier SWORD_THUNDERBIRD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> Ingredient.EMPTY);
        public static final Tier THUNDERBLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> Ingredient.EMPTY);
        public static final Tier BUBBLE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> Ingredient.EMPTY);
        public static final Tier VINESTRAND_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> Ingredient.EMPTY);
        public static final Tier DARK_PINE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> Ingredient.EMPTY);
        public static final Tier NATURES_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> Ingredient.EMPTY);
        public static final Tier TREE_HUGGER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> Ingredient.EMPTY);
        public static final Tier HEALERS_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 8F, 25, () -> Ingredient.EMPTY);
        public static final Tier LOGGERS_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> Ingredient.EMPTY);
        public static final Tier SENTRY_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> Ingredient.EMPTY);
        public static final Tier TERRALIGHT_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> Ingredient.EMPTY);
        public static final Tier TERRANA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> Ingredient.EMPTY);
        public static final Tier TERROLICA_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> Ingredient.EMPTY);
        public static final Tier VOLITE_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7.5F, 25, () -> Ingredient.EMPTY);
        public static final Tier TERRONIC_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 7F, 25, () -> Ingredient.EMPTY);
        public static final Tier GOLEM_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 9F, 25, () -> Ingredient.EMPTY);
        public static final Tier STARLIGHT_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 12F, 25, () -> Ingredient.EMPTY);
        public static final Tier FLUFFY_BLADE = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 15F, 25, () -> Ingredient.EMPTY);
        public static final Tier DARK_KEEPER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 10F, 25, () -> Ingredient.EMPTY);
        public static final Tier EARTHERN_HAMMER = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 642, 8F, 6F, 25, () -> Ingredient.EMPTY);

        public static final Tier DEVELOPER_SWORD = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3000, 8F, 1000F, 25, () -> Ingredient.EMPTY);
        public static final Tier THROWING_KNIFE = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, -1, 3F, 2F, 25, () -> Ingredient.EMPTY);
    }

    public static class JArmorTier {

        public static final DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(Registries.ARMOR_MATERIAL, JITL.MODID);

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
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

        public static final Holder<ArmorMaterial> FLAME = register("flame", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

        public static final Holder<ArmorMaterial> HOLLOW = register("hollow", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

        public static final Holder<ArmorMaterial> CRYSTAL_FLAKE = register("crystal_flake", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

        public static final Holder<ArmorMaterial> FROSTBITTEN = register("frostbitten", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 20, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

        public static final Holder<ArmorMaterial> HELLMETAL = register("hellmetal", Util.make(new EnumMap<>(ArmorItem.Type.class),
                armor -> {
                    armor.put(ArmorItem.Type.BOOTS, 8);
                    armor.put(ArmorItem.Type.LEGGINGS, 9);
                    armor.put(ArmorItem.Type.CHESTPLATE, 10);
                    armor.put(ArmorItem.Type.HELMET, 8);
                    armor.put(ArmorItem.Type.BODY, 5);
                }), 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.EMPTY);

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
            return REGISTRY.register(pName, () -> new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance));
        }
    }
}
