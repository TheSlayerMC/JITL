package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public enum JToolTiers {

    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 2, 0),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 1, 0),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 2, 0),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 1, 0),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 0, 0),

    LUNIUM_SWORD(JToolTier.LUNIUM, 2, 0),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 1, 0),
    LUNIUM_AXE(JToolTier.LUNIUM, 2, 0),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 1, 0),
    LUNIUM_HOE(JToolTier.LUNIUM, 0, 0),

    SHADIUM_SWORD(JToolTier.SHADIUM, 3, 0),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 2, 0),
    SHADIUM_AXE(JToolTier.SHADIUM, 3, 0),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 1, 0),
    SHADIUM_HOE(JToolTier.SHADIUM, 0, 0),

    BLOODCRUST_SWORD(JToolTier.BLOODCRUST, 4, 0),
    BLOODCRUST_PICKAXE(JToolTier.BLOODCRUST, 2, 0),
    BLOODCRUST_AXE(JToolTier.BLOODCRUST, 4, 0),
    BLOODCRUST_SHOVEL(JToolTier.BLOODCRUST, 1, 0),
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0, 0),

    CELESTIUM_SWORD(JToolTier.CELESTIUM, 4, 0),
    CELESTIUM_PICKAXE(JToolTier.CELESTIUM, 2, 0),
    CELESTIUM_AXE(JToolTier.CELESTIUM, 4, 0),
    CELESTIUM_SHOVEL(JToolTier.CELESTIUM, 1, 0),
    CELESTIUM_HOE(JToolTier.CELESTIUM, 0, 0),

    KORITE_SWORD(JToolTier.KORITE, 4, 0),
    KORITE_PICKAXE(JToolTier.KORITE, 2, 0),
    KORITE_AXE(JToolTier.KORITE, 4, 0),
    KORITE_SHOVEL(JToolTier.KORITE, 1, 0),
    KORITE_HOE(JToolTier.KORITE, 0, 0),

    STORON_SWORD(JToolTier.STORON, 4, 0),
    STORON_PICKAXE(JToolTier.STORON, 2, 0),
    STORON_AXE(JToolTier.STORON, 4, 0),
    STORON_SHOVEL(JToolTier.STORON, 1, 0),
    STORON_HOE(JToolTier.STORON, 0, 0),

    MEKYUM_SWORD(JToolTier.MEKYUM, 4, 0),
    MEKYUM_PICKAXE(JToolTier.MEKYUM, 2, 0),
    MEKYUM_AXE(JToolTier.MEKYUM, 4, 0),
    MEKYUM_SHOVEL(JToolTier.MEKYUM, 1, 0),
    MEKYUM_HOE(JToolTier.MEKYUM, 0, 0),

    CHAMPIONS_SWORD(JToolTier.CHAMPIONS_SWORD, 4, 0),
    THE_WRAITH(JToolTier.THE_WRAITH, 4, 0),

    //overworld
    POISON_SWORD(JToolTier.POISON_SWORD, 4, 0),
    CLOUD_SLICER(JToolTier.CLOUD_SLICER, 4, 0),
    DRAGONS_TOOTH(JToolTier.DRAGONS_TOOTH, 4, 0),
    DEMONIC_SWORD(JToolTier.DEMONIC_SWORD, 4, 0),
    PEDAL_SWORD(JToolTier.PEDAL_SWORD, 4, 0),
    RE_CRYSTAL_SWORD(JToolTier.RE_CRYSTAL_SWORD, 4, 0),
    RE_STONE_SWORD(JToolTier.RE_STONE_SWORD, 4, 0),
    CRYSTAL_BLADE(JToolTier.CRYSTAL_BLADE, 4, 0),

    //frozen
    SNOW_SHOVELER(JToolTier.SNOW_SHOVELER, 4, 0),
    FROSTBITTEN_SWORD(JToolTier.FROSTBITTEN_SWORD, 4, 0),
    FROSTY_SWORD(JToolTier.FROSTY_SWORD, 4, 0),

    //nether
    WITHIC_BLADE(JToolTier.WITHIC_BLADE, 4, 0),
    CALCIA_SWORD(JToolTier.CALCIA_SWORD, 4, 0),
    NETHER_BEAST_SWORD(JToolTier.NETHER_BEAST_SWORD, 4, 0),
    WITHERING_BEAST_SWORD(JToolTier.WITHERING_BEAST_SWORD, 4, 0),

    //boil
    BOILING_BLADE(JToolTier.BOILING_BLADE, 4, 0),
    SIZZLER_SWORD(JToolTier.SIZZLER_SWORD, 4, 0),
    BLOODWIELD_SWORD(JToolTier.BLOODWIELD_SWORD, 4, 0),
    CHARRED_BLADE(JToolTier.CHARRED_BLADE, 4, 0),
    MOLTEN_KNIFE(JToolTier.MOLTEN_KNIFE, 4, 0),

    //euca
    CORE_MENDER(JToolTier.CORE_MENDER, 4, 0),
    ROYAL_BLADE(JToolTier.ROYAL_BLADE, 4, 0),
    ROYAL_STABBER(JToolTier.ROYAL_STABBER, 4, 0),
    KINGS_SWORD(JToolTier.KINGS_SWORD, 4, 0),

    //depths
    DEPTHS_DARKSWORD(JToolTier.DEPTHS_DARKSWORD, 4, 0),
    DEPTHS_SLAYER(JToolTier.DEPTHS_SLAYER, 4, 0),
    ROC_SWORD(JToolTier.ROC_SWORD, 4, 0),
    SWORD_THUNDERBIRD(JToolTier.SWORD_THUNDERBIRD, 4, 0),
    THUNDERBLADE(JToolTier.THUNDERBLADE, 4, 0),
    BUBBLE_SWORD(JToolTier.BUBBLE_SWORD, 4, 0),

    //corba
    VINESTRAND_BLADE(JToolTier.VINESTRAND_BLADE, 4, 0),
    DARK_PINE_SWORD(JToolTier.DARK_PINE_SWORD, 4, 0),
    NATURES_BLADE(JToolTier.NATURES_BLADE, 4, 0),
    TREE_HUGGER(JToolTier.TREE_HUGGER, 4, 0),
    HEALERS_BLADE(JToolTier.HEALERS_BLADE, 4, 0),
    LOGGERS_SWORD(JToolTier.LOGGERS_SWORD, 4, 0),
    SENTRY_SWORD(JToolTier.SENTRY_SWORD, 4, 0),

    //terrania
    TERRALIGHT_BLADE(JToolTier.TERRALIGHT_BLADE, 4, 0),
    TERRANA_SWORD(JToolTier.TERRANA_SWORD, 4, 0),
    TERROLICA_SWORD(JToolTier.TERROLICA_SWORD, 4, 0),
    VOLITE_SWORD(JToolTier.VOLITE_SWORD, 4, 0),
    TERRONIC_BLADE(JToolTier.TERRONIC_BLADE, 4, 0),

    //cloudia
    GOLEM_SWORD(JToolTier.GOLEM_SWORD, 4, 0),
    STARLIGHT_BLADE(JToolTier.STARLIGHT_BLADE, 4, 0),
    FLUFFY_BLADE(JToolTier.FLUFFY_BLADE, 4, 0),
    DARK_KEEPER(JToolTier.DARK_KEEPER, 4, 0),

    DEVELOPER_SWORD(JToolTier.DEVELOPER_SWORD, 1000, 0)
    ;

    private final Tier tier;
    private final int damage;
    private final float speedModifier;

    JToolTiers(Tier tier, int damage, float speed) {
        this.tier = tier;
        this.damage = damage;
        this.speedModifier = speed;
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

        public static final Tier SAPPHIRE = new ForgeTier(2, 855, 6F, 1F, 15, null, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final Tier LUNIUM = new ForgeTier(2, 875, 6F, 2F, 15, null, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final Tier SHADIUM = new ForgeTier(3, 1561, 8F, 3F, 15, null, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final Tier BLOODCRUST = new ForgeTier(3, 1883, 8F, 3F, 15, null, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));

        public static final Tier CELESTIUM = new ForgeTier(3, 1883, 8F, 3F, 15, null, () -> Ingredient.of(JItems.CELESTIUM_INGOT.get()));
        public static final Tier KORITE = new ForgeTier(3, 1883, 8F, 3F, 15, null, () -> Ingredient.of(JItems.KORITE_INGOT.get()));
        public static final Tier STORON = new ForgeTier(3, 1883, 8F, 3F, 15, null, () -> Ingredient.of(JItems.STORON_INGOT.get()));
        public static final Tier MEKYUM = new ForgeTier(3, 1883, 8F, 3F, 15, null, () -> Ingredient.of(JItems.MEKYUM_INGOT.get()));

        public static final Tier CHAMPIONS_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier THE_WRAITH = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier POISON_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CLOUD_SLICER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DRAGONS_TOOTH = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DEMONIC_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier PEDAL_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier RE_CRYSTAL_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier RE_STONE_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CRYSTAL_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SNOW_SHOVELER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier FROSTBITTEN_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier FROSTY_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier WITHIC_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CALCIA_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier NETHER_BEAST_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier WITHERING_BEAST_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier BOILING_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SIZZLER_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier BLOODWIELD_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CHARRED_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier MOLTEN_KNIFE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier CORE_MENDER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier ROYAL_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier ROYAL_STABBER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier KINGS_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DEPTHS_DARKSWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DEPTHS_SLAYER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier ROC_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SWORD_THUNDERBIRD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier THUNDERBLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier BUBBLE_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier VINESTRAND_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DARK_PINE_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier NATURES_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier TREE_HUGGER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier HEALERS_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier LOGGERS_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier SENTRY_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier TERRALIGHT_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier TERRANA_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier TERROLICA_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier VOLITE_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier TERRONIC_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier GOLEM_SWORD = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier STARLIGHT_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier FLUFFY_BLADE = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);
        public static final Tier DARK_KEEPER = new ForgeTier(3, 3000, 8F, 3F, 25, null, () -> null);

        public static final Tier DEVELOPER_SWORD = new ForgeTier(3, 3000, 8F, 1000F, 25, null, () -> null);
    }

    public static class JArmorTier {

        public static final JArmorMaterial SAPPHIRE = new JArmorMaterial("sapphire", 20, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final JArmorMaterial LUNIUM = new JArmorMaterial("lunium", 23, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final JArmorMaterial SHADIUM = new JArmorMaterial("shadium", 33, new int[] {3, 6, 8, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final JArmorMaterial BLOODCRUST = new JArmorMaterial("bloodcrust", 33, new int[] {3, 5, 7, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));
    }
}
