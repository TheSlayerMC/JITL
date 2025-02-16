package net.jitl.common.capability.gear;

import net.jitl.common.items.base.JArmorItem;
import net.jitl.common.items.gear.FullArmorAbility;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class PlayerArmor implements INBTSerializable<CompoundTag> {

    private ArrayList<ItemStack> armorPieces;
    private FullArmorAbility fullSet;
    private CompoundTag nbt = new CompoundTag();

    public void copyFrom(PlayerArmor source) {
        this.armorPieces = source.armorPieces;
        this.fullSet = source.fullSet;
        this.nbt = source.nbt;
    }

    public void setArmor(Iterator<ItemStack> iterator) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        ItemStack currentStack = iterator.next();
        Item item = currentStack.getItem();
        ArmorMaterial material = null;
        FullArmorAbility fullArmorAbility = null;
        if (item instanceof JArmorItem) {
            stacks.add(currentStack);
            fullArmorAbility = Objects.requireNonNull(((JArmorItem) item).getAbility()).getFullAbility(nbt);
            if (fullArmorAbility != null) {
                material = ((JArmorItem) item).getMaterial().value();
            }
        }
        while (iterator.hasNext()) {
            currentStack = iterator.next();
            item = currentStack.getItem();
            if (item instanceof JArmorItem) {
                if (((JArmorItem) item).getMaterial().value() != material) {
                    fullArmorAbility = null;
                    material = null;
                }
                stacks.add(currentStack);
            } else {
                fullArmorAbility = null;
                material = null;
            }
        }
        stacks.trimToSize();
        armorPieces = stacks;
        fullSet = fullArmorAbility;
    }

    public ArrayList<ItemStack> getArmor() {
        return armorPieces;
    }

    public FullArmorAbility getFullArmor() {
        return fullSet;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.put("armor ability", this.nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.nbt = nbt.getCompound("armor ability");
    }
}
