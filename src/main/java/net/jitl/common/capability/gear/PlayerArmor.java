package net.jitl.common.capability.gear;

import net.jitl.common.items.base.JArmorItem;
import net.jitl.common.items.gear.FullArmorAbility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class PlayerArmor implements ValueIOSerializable {

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
                material = ((JArmorItem) item).getMaterial();
            }
        }
        while (iterator.hasNext()) {
            currentStack = iterator.next();
            item = currentStack.getItem();
            if (item instanceof JArmorItem) {
                if (((JArmorItem) item).getMaterial() != material) {
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
    public void serialize(ValueOutput valueOutput) {
    }

    @Override
    public void deserialize(@NotNull ValueInput valueInput) {
    }
}
