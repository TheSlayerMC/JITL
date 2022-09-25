package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.common.items.BottleEssenciaItem;
import net.jitl.common.items.FlameCoinItem;
import net.jitl.common.items.PiercerItem;
import net.jitl.common.items.StaffItem;
import net.jitl.common.items.base.*;
import net.jitl.core.helper.EnumJTier;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
    public static final RegistryObject<Item> ENDERILLIUM_SHARD = registerNormalItem("enderillium_shard", "Enderillium Shard");
    public static final RegistryObject<Item> WARPED_QUARTZ = registerNormalItem("warped_quartz", "Warped Quartz");
    public static final RegistryObject<Item> FIRESTONE_CLUMP = registerNormalItem("firestone_clump", "Firestone Clump");

    public static final RegistryObject<Item> ASH = registerNormalItem("ash", "Ash");
    public static final RegistryObject<Item> RIMESTONE = registerNormalItem("rimestone", "Rimestone");
    public static final RegistryObject<Item> PERIDOT_GEMSTONE = registerNormalItem("peridot_gemstone", "Peridot Gemstone");

    public static final RegistryObject<Item> YELLOW_GEM = registerNormalItem("yellow_gem", "Yellow Gem");
    public static final RegistryObject<Item> PURPLE_GEM = registerNormalItem("purple_gem", "Purple Gem");
    public static final RegistryObject<Item> GREEN_GEM = registerNormalItem("green_gem", "Green Gem");
    public static final RegistryObject<Item> BLUE_GEM = registerNormalItem("blue_gem", "Blue Gem");

    public static final RegistryObject<Item> RAW_BLAZIUM = registerNormalItem("raw_blazium", "Raw Blazium");
    public static final RegistryObject<Item> BLAZIUM_INGOT = registerNormalItem("blazium_ingot", "Blazium Ingot");

    public static final RegistryObject<Item> BOTTLE_OF_ESSENCIA = registerNormalItem("bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(false));
    public static final RegistryObject<Item> STRONG_BOTTLE_OF_ESSENCIA = registerNormalItem("strong_bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(true));
    public static final RegistryObject<Item> FLAME_COIN = registerNormalItem("flame_coin", "Flame Coin", FlameCoinItem::new);

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

    public static final RegistryObject<Item> LUNIUM_POWDER = registerNormalItem("lunium_powder", "Lunium Powder");
    public static final RegistryObject<Item> LUNIUM_INGOT = registerNormalItem("lunium_ingot", "Lunium Ingot");
    public static final RegistryObject<Item> LUNIUM_SHOVEL = registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(EnumJTier.LUNIUM_SHOVEL));
    public static final RegistryObject<Item> LUNIUM_PICKAXE = registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(EnumJTier.LUNIUM_PICKAXE));
    public static final RegistryObject<Item> LUNIUM_AXE = registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(EnumJTier.LUNIUM_AXE));
    public static final RegistryObject<Item> LUNIUM_HOE = registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(EnumJTier.LUNIUM_HOE));
    public static final RegistryObject<Item> LUNIUM_SWORD = registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(EnumJTier.LUNIUM_SWORD));
    public static final RegistryObject<Item> LUNIUM_HELMET = registerNormalItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> LUNIUM_CHEST = registerNormalItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> LUNIUM_LEGS = registerNormalItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> LUNIUM_BOOTS = registerNormalItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> RAW_SHADIUM = registerNormalItem("raw_shadium", "Raw Shadium");
    public static final RegistryObject<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot");
    public static final RegistryObject<Item> SHADIUM_SHOVEL = registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(EnumJTier.SHADIUM_SHOVEL));
    public static final RegistryObject<Item> SHADIUM_PICKAXE = registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(EnumJTier.SHADIUM_PICKAXE));
    public static final RegistryObject<Item> SHADIUM_AXE = registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(EnumJTier.SHADIUM_AXE));
    public static final RegistryObject<Item> SHADIUM_HOE = registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(EnumJTier.SHADIUM_HOE));
    public static final RegistryObject<Item> SHADIUM_SWORD = registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(EnumJTier.SHADIUM_SWORD));
    public static final RegistryObject<Item> SHADIUM_HELMET = registerNormalItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SHADIUM_CHEST = registerNormalItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SHADIUM_LEGS = registerNormalItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SHADIUM_BOOTS = registerNormalItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> RAW_BLOODCRUST = registerNormalItem("raw_bloodcrust", "Raw Bloodcrust");
    public static final RegistryObject<Item> BLOODCRUST_INGOT = registerNormalItem("bloodcrust_ingot", "Bloodcrust Ingot");
    public static final RegistryObject<Item> BLOODCRUST_SHOVEL = registerToolItem("bloodcrust_shovel", "Bloodcrust Shovel", () -> new JShovelItem(EnumJTier.BLOODCRUST_SHOVEL));
    public static final RegistryObject<Item> BLOODCRUST_PICKAXE = registerToolItem("bloodcrust_pickaxe", "Bloodcrust Pickaxe", () -> new JPickaxeItem(EnumJTier.BLOODCRUST_PICKAXE));
    public static final RegistryObject<Item> BLOODCRUST_AXE = registerToolItem("bloodcrust_axe", "Bloodcrust Axe", () -> new JAxeItem(EnumJTier.BLOODCRUST_AXE));
    public static final RegistryObject<Item> BLOODCRUST_HOE = registerToolItem("bloodcrust_hoe", "Bloodcrust Hoe", () -> new JHoeItem(EnumJTier.BLOODCRUST_HOE));
    public static final RegistryObject<Item> BLOODCRUST_SWORD = registerToolItem("bloodcrust_sword", "Bloodcrust Sword", () -> new JSwordItem(EnumJTier.BLOODCRUST_SWORD));
    public static final RegistryObject<Item> BLOODCRUST_HELMET = registerNormalItem("bloodcrust_helmet", "Bloodcrust Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> BLOODCRUST_CHEST = registerNormalItem("bloodcrust_chestplate", "Bloodcrust Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> BLOODCRUST_LEGS = registerNormalItem("bloodcrust_leggings", "Bloodcrust Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> BLOODCRUST_BOOTS = registerNormalItem("bloodcrust_boots", "Bloodcrust Boots", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> CELESTIUM_INGOT = registerNormalItem("celestium_ingot", "Celestium Ingot");
    public static final RegistryObject<Item> CELESTIUM_GEMSTONE = registerNormalItem("celestium_gemstone", "Celestium Gemstone");
    public static final RegistryObject<Item> CELESTIUM_SHOVEL = registerToolItem("celestium_shovel", "Celestium Shovel", () -> new JShovelItem(EnumJTier.CELESTIUM_SHOVEL));
    public static final RegistryObject<Item> CELESTIUM_PICKAXE = registerToolItem("celestium_pickaxe", "Celestium Pickaxe", () -> new JPickaxeItem(EnumJTier.CELESTIUM_PICKAXE));
    public static final RegistryObject<Item> CELESTIUM_AXE = registerToolItem("celestium_axe", "Celestium Axe", () -> new JAxeItem(EnumJTier.CELESTIUM_AXE));
    public static final RegistryObject<Item> CELESTIUM_HOE = registerToolItem("celestium_hoe", "Celestium Hoe", () -> new JHoeItem(EnumJTier.CELESTIUM_HOE));
    public static final RegistryObject<Item> CELESTIUM_SWORD = registerToolItem("celestium_sword", "Celestium Sword", () -> new JSwordItem(EnumJTier.CELESTIUM_SWORD));

    public static final RegistryObject<Item> MEKYUM_INGOT = registerNormalItem("mekyum_ingot", "Mekyum Ingot");
    public static final RegistryObject<Item> MEKYUM_GEMSTONE = registerNormalItem("mekyum_gemstone", "Mekyum Gemstone");
    public static final RegistryObject<Item> MEKYUM_SHOVEL = registerToolItem("mekyum_shovel", "Mekyum Shovel", () -> new JShovelItem(EnumJTier.MEKYUM_SHOVEL));
    public static final RegistryObject<Item> MEKYUM_PICKAXE = registerToolItem("mekyum_pickaxe", "Mekyum Pickaxe", () -> new JPickaxeItem(EnumJTier.MEKYUM_PICKAXE));
    public static final RegistryObject<Item> MEKYUM_AXE = registerToolItem("mekyum_axe", "Mekyum Axe", () -> new JAxeItem(EnumJTier.MEKYUM_AXE));
    public static final RegistryObject<Item> MEKYUM_HOE = registerToolItem("mekyum_hoe", "Mekyum Hoe", () -> new JHoeItem(EnumJTier.MEKYUM_HOE));
    public static final RegistryObject<Item> MEKYUM_SWORD = registerToolItem("mekyum_sword", "Mekyum Sword", () -> new JSwordItem(EnumJTier.MEKYUM_SWORD));

    public static final RegistryObject<Item> STORON_INGOT = registerNormalItem("storon_ingot", "Storon Ingot");
    public static final RegistryObject<Item> STORON_GEMSTONE = registerNormalItem("storon_gemstone", "Storon Gemstone");
    public static final RegistryObject<Item> STORON_SHOVEL = registerToolItem("storon_shovel", "Storon Shovel", () -> new JShovelItem(EnumJTier.STORON_SHOVEL));
    public static final RegistryObject<Item> STORON_PICKAXE = registerToolItem("storon_pickaxe", "Storon Pickaxe", () -> new JPickaxeItem(EnumJTier.STORON_PICKAXE));
    public static final RegistryObject<Item> STORON_AXE = registerToolItem("storon_axe", "Storon Axe", () -> new JAxeItem(EnumJTier.STORON_AXE));
    public static final RegistryObject<Item> STORON_HOE = registerToolItem("storon_hoe", "Storon Hoe", () -> new JHoeItem(EnumJTier.STORON_HOE));
    public static final RegistryObject<Item> STORON_SWORD = registerToolItem("storon_sword", "Storon Sword", () -> new JSwordItem(EnumJTier.STORON_SWORD));

    public static final RegistryObject<Item> KORITE_INGOT = registerNormalItem("korite_ingot", "Korite Ingot");
    public static final RegistryObject<Item> KORITE_GEMSTONE = registerNormalItem("korite_gemstone", "Korite Gemstone");
    public static final RegistryObject<Item> KORITE_SHOVEL = registerToolItem("korite_shovel", "Korite Shovel", () -> new JShovelItem(EnumJTier.KORITE_SHOVEL));
    public static final RegistryObject<Item> KORITE_PICKAXE = registerToolItem("korite_pickaxe", "Korite Pickaxe", () -> new JPickaxeItem(EnumJTier.KORITE_PICKAXE));
    public static final RegistryObject<Item> KORITE_AXE = registerToolItem("korite_axe", "korite Axe", () -> new JAxeItem(EnumJTier.KORITE_AXE));
    public static final RegistryObject<Item> KORITE_HOE = registerToolItem("korite_hoe", "Korite Hoe", () -> new JHoeItem(EnumJTier.KORITE_HOE));
    public static final RegistryObject<Item> KORITE_SWORD = registerToolItem("korite_sword", "korite Sword", () -> new JSwordItem(EnumJTier.KORITE_SWORD));

    public static final RegistryObject<Item> STAFF_OF_CONJURING = registerToolItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(1, ConjuringProjectileEntity::new));
    public static final RegistryObject<Item> STAFF_OF_ESSENCIA = registerToolItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(4, EssenciaProjectileEntity::new));

    public static final RegistryObject<Item> GOLDEN_EUCA_BOAT = registerNormalItem("golden_euca_boat", "Gold Euca Boat", () -> new JBoatItem(JBoat.Type.GOLD_EUCA));
    public static final RegistryObject<Item> BROWN_EUCA_BOAT = registerNormalItem("brown_euca_boat", "Brown Euca Boat", () -> new JBoatItem(JBoat.Type.BROWN_EUCA));
    public static final RegistryObject<Item> FROZEN_BOAT = registerNormalItem("frozen_boat", "Frostwood Boat", () -> new JBoatItem(JBoat.Type.FROZEN));

    public static final RegistryObject<Item> EUCA_PIERCER = registerToolItem("euca_piercer", "Euca Piercer", () ->
            new PiercerItem(rangedProps().durability(50), (worldIn, owner, stack) -> new PiercerEntity(owner, worldIn, stack, 3, 10.0F)));

    public static final RegistryObject<Item> PIERCER = registerToolItem("piercer", "Piercer", () ->
            new PiercerItem(rangedProps().durability(128), (worldIn, owner, stack) -> new PiercerEntity(owner, worldIn, stack, 3, 3.0F)));

    public static final RegistryObject<Item> MAGE_EGG = registerNormalItem("mage_spawn_egg", "Mage Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.MAGE_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> FLORO_EGG = registerNormalItem("floro_spawn_egg", "Floro Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.FLORO_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> TOWER_GUARDIAN_EGG = registerNormalItem("tower_guardian_spawn_egg", "Tower Guarduan Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.TOWER_GUARDIAN_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> ROCKITE_SMASHER_EGG = registerNormalItem("rockite_smasher_spawn_egg", "Rockite Smasher Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ROCKITE_SMASHER_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> WITHERSPINE_SMASHER_EGG = registerNormalItem("witherspine_spawn_egg", "Witherspine Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.WITHERSPINE_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> BROWN_HONGO_EGG = registerNormalItem("brown_hongo_spawn_egg", "Brown Hongo Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.BROWN_HONGO_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

    public static final RegistryObject<Item> ILLAGER_MECH_EGG = registerNormalItem("illager_mech_spawn_egg", "Illager Mech Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ILLAGER_MECH_TYPE,
            0x948e8d, 0x3b3653, eggProps()));

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