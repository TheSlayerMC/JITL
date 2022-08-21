package net.jitl.core.init.internal;

import net.jitl.common.items.base.*;
import net.jitl.core.helper.EnumJTier;
import net.jitl.core.init.JITL;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> toolName = new ArrayList<>();

    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> toolLangName = new ArrayList<>();

    public static final RegistryObject<Item> SAPPHIRE = registerNormalItem("sapphire", "Sapphire", JTabs.MATERIALS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerToolItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(EnumJTier.SAPPHIRE_SHOVEL));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerToolItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(EnumJTier.SAPPHIRE_PICKAXE));
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerToolItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(EnumJTier.SAPPHIRE_AXE));
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerToolItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(EnumJTier.SAPPHIRE_HOE));
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerToolItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(EnumJTier.SAPPHIRE_SWORD));

    public static final RegistryObject<Item> LUNIUM_POWDER = registerNormalItem("lunium_powder", "Lunium Powder", JTabs.MATERIALS);
    public static final RegistryObject<Item> LUNIUM_SHOVEL = registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(EnumJTier.LUNIUM_SHOVEL));
    public static final RegistryObject<Item> LUNIUM_PICKAXE = registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(EnumJTier.LUNIUM_PICKAXE));
    public static final RegistryObject<Item> LUNIUM_AXE = registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(EnumJTier.LUNIUM_AXE));
    public static final RegistryObject<Item> LUNIUM_HOE = registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(EnumJTier.LUNIUM_HOE));
    public static final RegistryObject<Item> LUNIUM_SWORD = registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(EnumJTier.LUNIUM_SWORD));

    public static final RegistryObject<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot", JTabs.MATERIALS);
    public static final RegistryObject<Item> SHADIUM_SHOVEL = registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(EnumJTier.SHADIUM_SHOVEL));
    public static final RegistryObject<Item> SHADIUM_PICKAXE = registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(EnumJTier.SHADIUM_PICKAXE));
    public static final RegistryObject<Item> SHADIUM_AXE = registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(EnumJTier.SHADIUM_AXE));
    public static final RegistryObject<Item> SHADIUM_HOE = registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(EnumJTier.SHADIUM_HOE));
    public static final RegistryObject<Item> SHADIUM_SWORD = registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(EnumJTier.SHADIUM_SWORD));

    public static RegistryObject<Item> registerNormalItem(String name, String translatedName, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(tab)), ItemType.ITEM);
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