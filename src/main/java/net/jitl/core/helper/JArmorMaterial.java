package net.jitl.core.helper;

import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record JArmorMaterial(String name, int dur, int[] prot, int enchantability, SoundEvent equpSound, float toughness, float knowbackRes, Supplier<Ingredient> repairItem) implements ArmorMaterial {

    private static final int[] DUR_PER_SLOT = new int[] {13, 15, 16, 11};

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return DUR_PER_SLOT[pSlot.getIndex()] * this.dur();
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.prot[pSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equpSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairItem.get();
    }

    @Override
    public @NotNull String getName() {
        return JITL.PREFIX + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knowbackRes;
    }
}