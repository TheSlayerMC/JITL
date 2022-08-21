package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public enum EnumJTier {

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
    BLOODCRUST_HOE(JToolTier.BLOODCRUST, 0, 0)
    ;

    private final Tier tier;
    private final int damage;
    private final float speedModifier;

    EnumJTier(Tier tier, int damage, float speed) {
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

    }

    public static class JArmorTier {

        public static final JArmorMaterial SAPPHIRE = new JArmorMaterial("sapphire", 20, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final JArmorMaterial LUNIUM = new JArmorMaterial("lunium", 23, new int[] {2, 5, 6, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final JArmorMaterial SHADIUM = new JArmorMaterial("shadium", 33, new int[] {3, 6, 8, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));
        public static final JArmorMaterial BLOODCRUST = new JArmorMaterial("bloodcrust", 33, new int[] {3, 5, 7, 3}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> Ingredient.of(JItems.BLOODCRUST_INGOT.get()));

    }
}
