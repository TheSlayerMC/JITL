package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ItemRegistrys {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> toolName = new ArrayList<>();

    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> toolLangName = new ArrayList<>();

    public static RegistryObject<Item> registerNormalItem(String name, String translatedName, CreativeModeTab tab) {
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(tab)), ItemType.ITEM);
    }

    public static RegistryObject<Item> registerNormalItem(String name, String translatedName) {
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(JTabs.MATERIALS)), ItemType.ITEM);
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

    public static Item.Properties rangedProps() {
        return new Item.Properties().tab(JTabs.RANGED_WEAPONS);
    }

    public static Item.Properties toolProps() {
        return new Item.Properties().tab(JTabs.TOOLS);
    }

    public static Item.Properties miscProps() {
        return new Item.Properties().tab(JTabs.MISC);
    }

    public static Item.Properties eggProps() {
        return new Item.Properties().tab(JTabs.MISC);
    }

    enum ItemType {
        ITEM,
        TOOL;
    }
}
