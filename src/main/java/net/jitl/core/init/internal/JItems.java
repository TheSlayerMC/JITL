package net.jitl.core.init.internal;

import net.jitl.common.items.base.*;
import net.jitl.core.helper.EnumJTier;
import net.jitl.core.helper.JArmorMaterial;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> toolName = new ArrayList<>();

    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> toolLangName = new ArrayList<>();

    public static final RegistryObject<Item> IRIDIUM_NUGGET = registerFuelItem("iridium_nugget", "Iridium Nugget", 1600);

    public static final RegistryObject<Item> SAPPHIRE = registerNormalItem("sapphire", "Sapphire", JTabs.MATERIALS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerToolItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(EnumJTier.SAPPHIRE_SHOVEL));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerToolItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(EnumJTier.SAPPHIRE_PICKAXE));
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerToolItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(EnumJTier.SAPPHIRE_AXE));
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerToolItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(EnumJTier.SAPPHIRE_HOE));
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerToolItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(EnumJTier.SAPPHIRE_SWORD));
    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerNormalItem("sapphire_helmet", "Sapphire Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SAPPHIRE_CHEST = registerNormalItem("sapphire_chestplate", "Sapphire Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SAPPHIRE_LEGS = registerNormalItem("sapphire_leggings", "Sapphire Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerNormalItem("sapphire_boots", "Sapphire Boots", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> LUNIUM_POWDER = registerNormalItem("lunium_powder", "Lunium Powder", JTabs.MATERIALS);
    public static final RegistryObject<Item> LUNIUM_INGOT = registerNormalItem("lunium_ingot", "Lunium Ingot", JTabs.MATERIALS);
    public static final RegistryObject<Item> LUNIUM_SHOVEL = registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(EnumJTier.LUNIUM_SHOVEL));
    public static final RegistryObject<Item> LUNIUM_PICKAXE = registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(EnumJTier.LUNIUM_PICKAXE));
    public static final RegistryObject<Item> LUNIUM_AXE = registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(EnumJTier.LUNIUM_AXE));
    public static final RegistryObject<Item> LUNIUM_HOE = registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(EnumJTier.LUNIUM_HOE));
    public static final RegistryObject<Item> LUNIUM_SWORD = registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(EnumJTier.LUNIUM_SWORD));
    public static final RegistryObject<Item> LUNIUM_HELMET = registerNormalItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> LUNIUM_CHEST = registerNormalItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> LUNIUM_LEGS = registerNormalItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> LUNIUM_BOOTS = registerNormalItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot", JTabs.MATERIALS);
    public static final RegistryObject<Item> SHADIUM_SHOVEL = registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(EnumJTier.SHADIUM_SHOVEL));
    public static final RegistryObject<Item> SHADIUM_PICKAXE = registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(EnumJTier.SHADIUM_PICKAXE));
    public static final RegistryObject<Item> SHADIUM_AXE = registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(EnumJTier.SHADIUM_AXE));
    public static final RegistryObject<Item> SHADIUM_HOE = registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(EnumJTier.SHADIUM_HOE));
    public static final RegistryObject<Item> SHADIUM_SWORD = registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(EnumJTier.SHADIUM_SWORD));
    public static final RegistryObject<Item> SHADIUM_HELMET = registerNormalItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SHADIUM_CHEST = registerNormalItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SHADIUM_LEGS = registerNormalItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SHADIUM_BOOTS = registerNormalItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.FEET, null));

    public static RegistryObject<Item> registerNormalItem(String name, String translatedName, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(tab)), ItemType.ITEM);
    }

    public static RegistryObject<Item> registerFuelItem(String name, String translatedName, int burnTime /* 200 ticks per item */) {
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(JTabs.MATERIALS)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        }, ItemType.ITEM);
    }

    public static RegistryObject<Item> registerNormalItem(String name, String translatedName, Supplier<Item> item) {
        return register(name, translatedName, item, ItemType.ITEM);
    }

    public static RegistryObject<Item> registerToolItem(String name, String translatedName, Supplier<Item> tool) {

        return register(name, translatedName, tool, ItemType.TOOL);
    }

    public static RegistryObject<Item> register(String name, String translatedName) {
        return register(name, translatedName, () -> new Item(new Item.Properties()), ItemType.ITEM);
    }

    public static RegistryObject<Item> register(String name, String translatedName, Supplier<Item> item, ItemType type) {
        if(type == ItemType.TOOL) {
            toolName.add(name);
            toolLangName.add(translatedName);
        }
        if(type == ItemType.ITEM) {
            itemName.add(name);
            langName.add(translatedName);
        }
        return register(name, item);
    }

    public static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static Item.Properties weaponProps() {
        return new Item.Properties().tab(JTabs.WEAPONS);
    }

    public static Item.Properties toolProps() {
        return new Item.Properties().tab(JTabs.TOOLS);
    }

    enum ItemType {
        ITEM,
        TOOL;
    }
}