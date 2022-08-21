package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public enum EnumJTier {

    SAPPHIRE_SWORD(JToolTier.SAPPHIRE, 3, 3),
    SAPPHIRE_PICKAXE(JToolTier.SAPPHIRE, 3, 3),
    SAPPHIRE_AXE(JToolTier.SAPPHIRE, 3, 3),
    SAPPHIRE_SHOVEL(JToolTier.SAPPHIRE, 3, 3),
    SAPPHIRE_HOE(JToolTier.SAPPHIRE, 3, 3),

    LUNIUM_SWORD(JToolTier.LUNIUM, 3, 3),
    LUNIUM_PICKAXE(JToolTier.LUNIUM, 3, 3),
    LUNIUM_AXE(JToolTier.LUNIUM, 3, 3),
    LUNIUM_SHOVEL(JToolTier.LUNIUM, 3, 3),
    LUNIUM_HOE(JToolTier.LUNIUM, 3, 3),

    SHADIUM_SWORD(JToolTier.SHADIUM, 3, 3),
    SHADIUM_PICKAXE(JToolTier.SHADIUM, 3, 3),
    SHADIUM_AXE(JToolTier.SHADIUM, 3, 3),
    SHADIUM_SHOVEL(JToolTier.SHADIUM, 3, 3),
    SHADIUM_HOE(JToolTier.SHADIUM, 3, 3)

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

        public static final Tier SAPPHIRE = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final Tier LUNIUM = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final Tier SHADIUM = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));

    }

    public static class JArmorTier {

        public static final JArmorMaterial SAPPHIRE = new JArmorMaterial("sapphire", 800, new int[] {1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final JArmorMaterial LUNIUM = new JArmorMaterial("lunium", 800, new int[] {1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.LUNIUM_INGOT.get()));
        public static final JArmorMaterial SHADIUM = new JArmorMaterial("shadium", 800, new int[] {1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));

    }
}
