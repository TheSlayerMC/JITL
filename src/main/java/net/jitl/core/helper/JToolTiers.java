package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public enum JToolTiers {


    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 1),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 2),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 3),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 4),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 0),

    LUNIUM_SWORD(JToolTier.LUNIUM, 1),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 2),
    LUNIUM_AXE(JToolTier.LUNIUM, 3),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 4),
    LUNIUM_HOE(JToolTier.LUNIUM, 0),

    SHADIUM_SWORD(JToolTier.SHADIUM, 1),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 2),
    SHADIUM_AXE(JToolTier.SHADIUM, 3),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 4),
    SHADIUM_HOE(JToolTier.SHADIUM, 0),

    BLOODCRUST_SWORD(JToolTier.BLOODCRUST, 1),
    BLOODCRUST_PICKAXE(JToolTier.BLOODCRUST, 2),
    BLOODCRUST_AXE(JToolTier.BLOODCRUST, 3),
    BLOODCRUST_SHOVEL(JToolTier.BLOODCRUST, 4),
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0),

    CELESTIUM_SWORD(JToolTier.CELESTIUM, 1),
    CELESTIUM_PICKAXE(JToolTier.CELESTIUM, 2),
    CELESTIUM_AXE(JToolTier.CELESTIUM, 3),
    CELESTIUM_SHOVEL(JToolTier.CELESTIUM, 4),
    CELESTIUM_HOE(JToolTier.CELESTIUM, 0),

    KORITE_SWORD(JToolTier.KORITE, 1),
    KORITE_PICKAXE(JToolTier.KORITE, 2),
    KORITE_AXE(JToolTier.KORITE, 3),
    KORITE_SHOVEL(JToolTier.KORITE, 4),
    KORITE_HOE(JToolTier.KORITE, 0),

    STORON_SWORD(JToolTier.STORON, 1),
    STORON_PICKAXE(JToolTier.STORON, 2),
    STORON_AXE(JToolTier.STORON, 3),
    STORON_SHOVEL(JToolTier.STORON, 4),
    STORON_HOE(JToolTier.STORON, 0),

    MEKYUM_SWORD(JToolTier.MEKYUM, 1),
    MEKYUM_PICKAXE(JToolTier.MEKYUM, 2),
    MEKYUM_AXE(JToolTier.MEKYUM, 3),
    MEKYUM_SHOVEL(JToolTier.MEKYUM, 4),
    MEKYUM_HOE(JToolTier.MEKYUM, 0),

    FLAIRIUM_SWORD(JToolTier.FLAIRIUM, 1),
    FLAIRIUM_PICKAXE(JToolTier.FLAIRIUM, 2),
    FLAIRIUM_AXE(JToolTier.FLAIRIUM, 3),
    FLAIRIUM_SHOVEL(JToolTier.FLAIRIUM, 4),
    FLAIRIUM_HOE(JToolTier.FLAIRIUM, 0),

    DES_SWORD(JToolTier.DES, 1),
    DES_PICKAXE(JToolTier.DES, 2),
    DES_AXE(JToolTier.DES, 3),
    DES_SHOVEL(JToolTier.DES, 4),
    DES_HOE(JToolTier.DES, 0),

    GORBITE_SWORD(JToolTier.GORBITE, 1),
    GORBITE_PICKAXE(JToolTier.GORBITE, 2),
    GORBITE_AXE(JToolTier.GORBITE, 3),
    GORBITE_SHOVEL(JToolTier.GORBITE, 4),
    GORBITE_HOE(JToolTier.GORBITE, 0),

    ORBADITE_SWORD(JToolTier.ORBADITE, 1),
    ORBADITE_PICKAXE(JToolTier.ORBADITE, 2),
    ORBADITE_AXE(JToolTier.ORBADITE, 3),
    ORBADITE_SHOVEL(JToolTier.ORBADITE, 4),
    ORBADITE_HOE(JToolTier.ORBADITE, 0),

    SOULSTONE_SWORD(JToolTier.SOULSTONE, 1),
    SOULSTONE_PICKAXE(JToolTier.SOULSTONE, 2),
    SOULSTONE_AXE(JToolTier.SOULSTONE, 3),
    SOULSTONE_SHOVEL(JToolTier.SOULSTONE, 4),
    SOULSTONE_HOE(JToolTier.SOULSTONE, 0),


    CHAMPIONS_SWORD(JToolTier.CHAMPIONS_SWORD, 0, 0),
    THE_WRAITH(JToolTier.THE_WRAITH, 0, 0),

    //overworld
    POISON_SWORD(JToolTier.POISON_SWORD, 0, 0),
    CLOUD_SLICER(JToolTier.CLOUD_SLICER, 0, 0),
    DRAGONS_TOOTH(JToolTier.DRAGONS_TOOTH, 0, 0),
    DEMONIC_SWORD(JToolTier.DEMONIC_SWORD, 0, 0),
    PEDAL_SWORD(JToolTier.PEDAL_SWORD, 0, 0),
    RE_CRYSTAL_SWORD(JToolTier.RE_CRYSTAL_SWORD, 0, 0),
    RE_STONE_SWORD(JToolTier.RE_STONE_SWORD, 0, 0),
    CRYSTAL_BLADE(JToolTier.CRYSTAL_BLADE, 0, 0),

    //frozen
    SNOW_SHOVELER(JToolTier.SNOW_SHOVELER, 0, 0),
    FROSTBITTEN_SWORD(JToolTier.FROSTBITTEN_SWORD, 0, 0),
    FROSTY_SWORD(JToolTier.FROSTY_SWORD, 0, 0),

    //nether
    WITHIC_BLADE(JToolTier.WITHIC_BLADE, 0, 0),
    CALCIA_SWORD(JToolTier.CALCIA_SWORD, 0, 0),
    NETHER_BEAST_SWORD(JToolTier.NETHER_BEAST_SWORD, 0, 0),
    WITHERING_BEAST_SWORD(JToolTier.WITHERING_BEAST_SWORD, 0, 0),

    //boil
    BOILING_BLADE(JToolTier.BOILING_BLADE, 0, 0),
    SIZZLER_SWORD(JToolTier.SIZZLER_SWORD, 0, 0),
    BLOODWIELD_SWORD(JToolTier.BLOODWIELD_SWORD, 0, 0),
    CHARRED_BLADE(JToolTier.CHARRED_BLADE, 0, 0),
    MOLTEN_KNIFE(JToolTier.MOLTEN_KNIFE, 0, 0),

    //euca
    CORE_MENDER(JToolTier.CORE_MENDER, 0, 0),
    ROYAL_BLADE(JToolTier.ROYAL_BLADE, 0, 0),
    ROYAL_STABBER(JToolTier.ROYAL_STABBER, 0, 0),
    KINGS_SWORD(JToolTier.KINGS_SWORD, 0, 0),

    //depths
    DEPTHS_DARKSWORD(JToolTier.DEPTHS_DARKSWORD, 0, 0),
    DEPTHS_SLAYER(JToolTier.DEPTHS_SLAYER, 0, 0),
    ROC_SWORD(JToolTier.ROC_SWORD, 0, 0),
    SWORD_THUNDERBIRD(JToolTier.SWORD_THUNDERBIRD, 0, 0),
    THUNDERBLADE(JToolTier.THUNDERBLADE, 0, 0),
    BUBBLE_SWORD(JToolTier.BUBBLE_SWORD, 0, 0),

    //corba
    VINESTRAND_BLADE(JToolTier.VINESTRAND_BLADE, 0, 0),
    DARK_PINE_SWORD(JToolTier.DARK_PINE_SWORD, 0, 0),
    NATURES_BLADE(JToolTier.NATURES_BLADE, 0, 0),
    TREE_HUGGER(JToolTier.TREE_HUGGER, 0, 0),
    HEALERS_BLADE(JToolTier.HEALERS_BLADE, 0, 0),
    LOGGERS_SWORD(JToolTier.LOGGERS_SWORD, 0, 0),
    SENTRY_SWORD(JToolTier.SENTRY_SWORD, 0, 0),

    //terrania
    TERRALIGHT_BLADE(JToolTier.TERRALIGHT_BLADE, 0, 0),
    TERRANA_SWORD(JToolTier.TERRANA_SWORD, 0, 0),
    TERROLICA_SWORD(JToolTier.TERROLICA_SWORD, 0, 0),
    VOLITE_SWORD(JToolTier.VOLITE_SWORD, 0, 0),
    TERRONIC_BLADE(JToolTier.TERRONIC_BLADE, 0, 0),

    //cloudia
    GOLEM_SWORD(JToolTier.GOLEM_SWORD, 0, 0),
    STARLIGHT_BLADE(JToolTier.STARLIGHT_BLADE, 0, 0),
    FLUFFY_BLADE(JToolTier.FLUFFY_BLADE, 0, 0),
    DARK_KEEPER(JToolTier.DARK_KEEPER, 0, 0),

    DEVELOPER_SWORD(JToolTier.DEVELOPER_SWORD, 1000, 0),

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
        int d = 1;//hoe
        this.tier = tier;
        if(type == 1){ //sword
            d = 5;
        }
        if(type == 2){//pickaxe
            d = 2;
        }
        if(type == 3){//axe
            d = 7;
        }
        if(type == 4){//shovel
            d = 3;
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

        public static final Tier SAPPHIRE = new SimpleTier(2, 900, 8F, 1F, 15, null, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final Tier LUNIUM = new SimpleTier(2, 1056, 8F, 2F, 15, null, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final Tier SHADIUM = new SimpleTier(3, 1056, 8F, 2F, 15, null, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final Tier BLOODCRUST = new SimpleTier(3, 1056, 8F, 2F, 15, null, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));

        //SORT BELOW
        public static final Tier CELESTIUM = new SimpleTier(3, 1342, 11F, 5F, 15, null, () -> Ingredient.of(JItems.CELESTIUM_INGOT.get()));
        public static final Tier KORITE = new SimpleTier(3, 1342, 10F, 5F, 15, null, () -> Ingredient.of(JItems.KORITE_INGOT.get()));
        public static final Tier STORON = new SimpleTier(3, 1342, 11F, 5F, 15, null, () -> Ingredient.of(JItems.STORON_INGOT.get()));
        public static final Tier MEKYUM = new SimpleTier(3, 1342, 10F, 5F, 15, null, () -> Ingredient.of(JItems.MEKYUM_INGOT.get()));

        public static final Tier FLAIRIUM = new SimpleTier(4, 1883, 12F, 7F, 15, null, () -> Ingredient.of(JItems.MEKYUM_INGOT.get()));
        public static final Tier DES = new SimpleTier(4, 1883, 12F, 7F, 15, null, () -> Ingredient.of(JItems.DES_INGOT.get()));

        public static final Tier GORBITE = new SimpleTier(5, 2056, 15F, 8F, 15, null, () -> Ingredient.of(JItems.GORBITE_GEM.get()));
        public static final Tier ORBADITE = new SimpleTier(5, 2056, 15F, 8F, 15, null, () -> Ingredient.of(JItems.ORBADITE_INGOT.get()));

        public static final Tier SOULSTONE = new SimpleTier(3, 1883, 8F, 5F, 15, null, () -> Ingredient.of(JItems.SOULSTONE.get()));

        public static final Tier CHAMPIONS_SWORD = new SimpleTier(3, 1883, 8F, 5F, 25, null, () -> null);
        public static final Tier THE_WRAITH = new SimpleTier(3, 1883, 8F, 5F, 25, null, () -> null);
        public static final Tier POISON_SWORD = new SimpleTier(3, 1883, 8F, 5F, 25, null, () -> null);
        public static final Tier CLOUD_SLICER = new SimpleTier(3, 1883, 8F, 5F, 25, null, () -> null);
        public static final Tier DRAGONS_TOOTH = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DEMONIC_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier PEDAL_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier RE_CRYSTAL_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier RE_STONE_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CRYSTAL_BLADE = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SNOW_SHOVELER = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier FROSTBITTEN_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier FROSTY_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier WITHIC_BLADE = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier CALCIA_SWORD = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier NETHER_BEAST_SWORD = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier WITHERING_BEAST_SWORD = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier BOILING_BLADE = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SIZZLER_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier BLOODWIELD_SWORD = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CHARRED_BLADE = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier MOLTEN_KNIFE = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CORE_MENDER = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier ROYAL_BLADE = new SimpleTier(3, 3000, 8F, 8F, 25, null, () -> null);
        public static final Tier ROYAL_STABBER = new SimpleTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier KINGS_SWORD = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier DEPTHS_DARKSWORD = new SimpleTier(3, 3000, 8F, 5, 25, null, () -> null);
        public static final Tier DEPTHS_SLAYER = new SimpleTier(3, 3000, 8F, 5F, 25, null, () -> null);
        public static final Tier ROC_SWORD = new SimpleTier(3, 3000, 8F, 10F, 25, null, () -> null);
        public static final Tier SWORD_THUNDERBIRD = new SimpleTier(3, 3000, 8F, 10F, 25, null, () -> null);
        public static final Tier THUNDERBLADE = new SimpleTier(3, 3000, 8F, 10F, 25, null, () -> null);
        public static final Tier BUBBLE_SWORD = new SimpleTier(3, 3000, 8F, 10F, 25, null, () -> null);
        public static final Tier VINESTRAND_BLADE = new SimpleTier(3, 3000, 8F, 8F, 25, null, () -> null);
        public static final Tier DARK_PINE_SWORD = new SimpleTier(3, 3000, 8F, 7.5F, 25, null, () -> null);
        public static final Tier NATURES_BLADE = new SimpleTier(3, 3000, 8F, 7.5F, 25, null, () -> null);
        public static final Tier TREE_HUGGER = new SimpleTier(3, 3000, 8F, 8F, 25, null, () -> null);
        public static final Tier HEALERS_BLADE = new SimpleTier(3, 3000, 8F, 8F, 25, null, () -> null);
        public static final Tier LOGGERS_SWORD = new SimpleTier(3, 3000, 8F, 12F, 25, null, () -> null);
        public static final Tier SENTRY_SWORD = new SimpleTier(3, 3000, 8F, 12F, 25, null, () -> null);
        public static final Tier TERRALIGHT_BLADE = new SimpleTier(3, 3000, 8F, 12F, 25, null, () -> null);
        public static final Tier TERRANA_SWORD = new SimpleTier(3, 3000, 8F, 12F, 25, null, () -> null);
        public static final Tier TERROLICA_SWORD = new SimpleTier(3, 3000, 8F, 7.5F, 25, null, () -> null);
        public static final Tier VOLITE_SWORD = new SimpleTier(3, 3000, 8F, 7.5F, 25, null, () -> null);
        public static final Tier TERRONIC_BLADE = new SimpleTier(3, 3000, 8F, 7F, 25, null, () -> null);
        public static final Tier GOLEM_SWORD = new SimpleTier(3, 3000, 8F, 9F, 25, null, () -> null);
        public static final Tier STARLIGHT_BLADE = new SimpleTier(3, 3000, 8F, 12F, 25, null, () -> null);
        public static final Tier FLUFFY_BLADE = new SimpleTier(3, 3000, 8F, 15F, 25, null, () -> null);
        public static final Tier DARK_KEEPER = new SimpleTier(3, 3000, 8F, 10F, 25, null, () -> null);

        public static final Tier DEVELOPER_SWORD = new SimpleTier(3, 3000, 8F, 1000F, 25, null, () -> null);
        public static final Tier THROWING_KNIFE = new SimpleTier(2, -1, 3F, 2F, 25, null, () -> null);
    }

    public static class JArmorTier {

        public static final JArmorMaterial SAPPHIRE = new JArmorMaterial("sapphire", 20, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final JArmorMaterial LUNIUM = new JArmorMaterial("lunium", 23, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final JArmorMaterial SHADIUM = new JArmorMaterial("shadium", 33, new int[] {3, 6, 8, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final JArmorMaterial BLOODCRUST = new JArmorMaterial("bloodcrust", 33, new int[] {3, 5, 7, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));
        public static final JArmorMaterial CELESTIUM = new JArmorMaterial("celestium", 33, new int[] {5, 7, 8, 5}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.CELESTIUM_INGOT.get()));

        public static final JArmorMaterial FLAIRIUM = new JArmorMaterial("flairium", 33, new int[] {5, 7, 8, 5}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.FLAIRIUM_INGOT.get()));

        public static final JArmorMaterial GORBITE = new JArmorMaterial("gorbite", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.GORBITE_GEM.get()));
        public static final JArmorMaterial ORBADITE = new JArmorMaterial("orbadite", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.ORBADITE_INGOT.get()));

        public static final JArmorMaterial TWILIGHT = new JArmorMaterial("twilight", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);
        public static final JArmorMaterial FLAME = new JArmorMaterial("flame", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);
        public static final JArmorMaterial HOLLOW = new JArmorMaterial("hollow", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);
        public static final JArmorMaterial CRYSTAL_FLAKE = new JArmorMaterial("crystal_flake", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);
        public static final JArmorMaterial FROSTBITTEN = new JArmorMaterial("frostbitten", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);
        public static final JArmorMaterial HELLMETAL = new JArmorMaterial("hellmetal", 33, new int[] {8, 9, 10, 8}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, null);

    }
}
