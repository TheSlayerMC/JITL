package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

    public static final RegistryObject<Item> SAPPHIRE = registerNormalItem("sapphire", "Sapphire", JTabs.MATERIALS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerToolItem("sapphire_shovel", "Sapphire Shovel");
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerToolItem("sapphire_sword", "Sapphire Sword");

    public static final RegistryObject<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot", JTabs.MATERIALS);


    public static RegistryObject<Item> registerNormalItem(String name, String translatedName, CreativeModeTab tab) {
        itemName.add(name);
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(tab)));
    }

    public static RegistryObject<Item> registerToolItem(String name, String translatedName) {
        toolName.add(name);
        return register(name, translatedName, () -> new Item(new Item.Properties().tab(JTabs.TOOLS)));
    }

    public static RegistryObject<Item> register(String name, String translatedName) {
        return register(name, translatedName, () -> new Item(new Item.Properties()));
    }

    public static RegistryObject<Item> register(String name, String translatedName, Supplier<Item> item) {
        langName.add(translatedName);
        return ITEMS.register(name, item);
    }
}