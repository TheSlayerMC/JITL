package net.jitl.core.helper;

import net.jitl.core.init.internal.JItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public enum EnumJTier {

    SAPPHIRE_SWORD(JTier.SAPPHIRE, 3, 3),
    SAPPHIRE_PICKAXE(JTier.SAPPHIRE, 3, 3),
    SAPPHIRE_AXE(JTier.SAPPHIRE, 3, 3),
    SAPPHIRE_SHOVEL(JTier.SAPPHIRE, 3, 3),
    SAPPHIRE_HOE(JTier.SAPPHIRE, 3, 3),

    LUNIUM_SWORD(JTier.LUNIUM, 3, 3),
    LUNIUM_PICKAXE(JTier.LUNIUM, 3, 3),
    LUNIUM_AXE(JTier.LUNIUM, 3, 3),
    LUNIUM_SHOVEL(JTier.LUNIUM, 3, 3),
    LUNIUM_HOE(JTier.LUNIUM, 3, 3),

    SHADIUM_SWORD(JTier.SHADIUM, 3, 3),
    SHADIUM_PICKAXE(JTier.SHADIUM, 3, 3),
    SHADIUM_AXE(JTier.SHADIUM, 3, 3),
    SHADIUM_SHOVEL(JTier.SHADIUM, 3, 3),
    SHADIUM_HOE(JTier.SHADIUM, 3, 3)

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

    public static class JTier {

        public static final Tier SAPPHIRE = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.SAPPHIRE.get()));
        public static final Tier LUNIUM = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.LUNIUM_POWDER.get()));
        public static final Tier SHADIUM = new ForgeTier(2, 800, 3F, 3, 30, null, () -> Ingredient.of(JItems.SHADIUM_INGOT.get()));

    }
}
