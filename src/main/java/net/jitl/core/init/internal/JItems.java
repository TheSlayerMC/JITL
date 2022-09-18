package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.common.items.BottleEssenciaItem;
import net.jitl.common.items.StaffItem;
import net.jitl.common.items.base.*;
import net.jitl.core.helper.EnumJTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class JItems {



    public static final RegistryObject<Item> IRIDIUM_NUGGET = ItemRegistrys.registerFuelItem("iridium_nugget", "Iridium Nugget", 1600);
    public static final RegistryObject<Item> ENDERILLIUM_SHARD = ItemRegistrys.registerNormalItem("enderillium_shard", "Enderillium Shard");
    public static final RegistryObject<Item> WARPED_QUARTZ = ItemRegistrys.registerNormalItem("warped_quartz", "Warped Quartz");

    public static final RegistryObject<Item> BOTTLE_OF_ESSENCIA = ItemRegistrys.registerNormalItem("bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(false));
    public static final RegistryObject<Item> STRONG_BOTTLE_OF_ESSENCIA = ItemRegistrys.registerNormalItem("strong_bottle_of_essencia", "Bottle 'o' Essencia", () -> new BottleEssenciaItem(true));

    public static final RegistryObject<Item> SAPPHIRE = ItemRegistrys.registerNormalItem("sapphire", "Sapphire", JTabs.MATERIALS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ItemRegistrys.registerToolItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(EnumJTier.SAPPHIRE_SHOVEL));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ItemRegistrys.registerToolItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(EnumJTier.SAPPHIRE_PICKAXE));
    public static final RegistryObject<Item> SAPPHIRE_AXE = ItemRegistrys.registerToolItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(EnumJTier.SAPPHIRE_AXE));
    public static final RegistryObject<Item> SAPPHIRE_HOE = ItemRegistrys.registerToolItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(EnumJTier.SAPPHIRE_HOE));
    public static final RegistryObject<Item> SAPPHIRE_SWORD = ItemRegistrys.registerToolItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(EnumJTier.SAPPHIRE_SWORD));
    public static final RegistryObject<Item> SAPPHIRE_HELMET = ItemRegistrys.registerNormalItem("sapphire_helmet", "Sapphire Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SAPPHIRE_CHEST = ItemRegistrys.registerNormalItem("sapphire_chestplate", "Sapphire Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SAPPHIRE_LEGS = ItemRegistrys.registerNormalItem("sapphire_leggings", "Sapphire Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = ItemRegistrys.registerNormalItem("sapphire_boots", "Sapphire Boots", () -> new JArmorItem(EnumJTier.JArmorTier.SAPPHIRE, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> LUNIUM_POWDER = ItemRegistrys.registerNormalItem("lunium_powder", "Lunium Powder");
    public static final RegistryObject<Item> LUNIUM_INGOT = ItemRegistrys.registerNormalItem("lunium_ingot", "Lunium Ingot");
    public static final RegistryObject<Item> LUNIUM_SHOVEL = ItemRegistrys.registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(EnumJTier.LUNIUM_SHOVEL));
    public static final RegistryObject<Item> LUNIUM_PICKAXE = ItemRegistrys.registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(EnumJTier.LUNIUM_PICKAXE));
    public static final RegistryObject<Item> LUNIUM_AXE = ItemRegistrys.registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(EnumJTier.LUNIUM_AXE));
    public static final RegistryObject<Item> LUNIUM_HOE = ItemRegistrys.registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(EnumJTier.LUNIUM_HOE));
    public static final RegistryObject<Item> LUNIUM_SWORD = ItemRegistrys.registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(EnumJTier.LUNIUM_SWORD));
    public static final RegistryObject<Item> LUNIUM_HELMET = ItemRegistrys.registerNormalItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> LUNIUM_CHEST = ItemRegistrys.registerNormalItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> LUNIUM_LEGS = ItemRegistrys.registerNormalItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> LUNIUM_BOOTS = ItemRegistrys.registerNormalItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.LUNIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> RAW_SHADIUM = ItemRegistrys.registerNormalItem("raw_shadium", "Raw Shadium");
    public static final RegistryObject<Item> SHADIUM_INGOT = ItemRegistrys.registerNormalItem("shadium_ingot", "Shadium Ingot");
    public static final RegistryObject<Item> SHADIUM_SHOVEL = ItemRegistrys.registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(EnumJTier.SHADIUM_SHOVEL));
    public static final RegistryObject<Item> SHADIUM_PICKAXE = ItemRegistrys.registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(EnumJTier.SHADIUM_PICKAXE));
    public static final RegistryObject<Item> SHADIUM_AXE = ItemRegistrys.registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(EnumJTier.SHADIUM_AXE));
    public static final RegistryObject<Item> SHADIUM_HOE = ItemRegistrys.registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(EnumJTier.SHADIUM_HOE));
    public static final RegistryObject<Item> SHADIUM_SWORD = ItemRegistrys.registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(EnumJTier.SHADIUM_SWORD));
    public static final RegistryObject<Item> SHADIUM_HELMET = ItemRegistrys.registerNormalItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SHADIUM_CHEST = ItemRegistrys.registerNormalItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SHADIUM_LEGS = ItemRegistrys.registerNormalItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SHADIUM_BOOTS = ItemRegistrys.registerNormalItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(EnumJTier.JArmorTier.SHADIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> RAW_BLOODCRUST = ItemRegistrys.registerNormalItem("raw_bloodcrust", "Raw Bloodcrust");
    public static final RegistryObject<Item> BLOODCRUST_INGOT = ItemRegistrys.registerNormalItem("bloodcrust_ingot", "Bloodcrust Ingot");
    public static final RegistryObject<Item> BLOODCRUST_SHOVEL = ItemRegistrys.registerToolItem("bloodcrust_shovel", "Bloodcrust Shovel", () -> new JShovelItem(EnumJTier.BLOODCRUST_SHOVEL));
    public static final RegistryObject<Item> BLOODCRUST_PICKAXE = ItemRegistrys.registerToolItem("bloodcrust_pickaxe", "Bloodcrust Pickaxe", () -> new JPickaxeItem(EnumJTier.BLOODCRUST_PICKAXE));
    public static final RegistryObject<Item> BLOODCRUST_AXE = ItemRegistrys.registerToolItem("bloodcrust_axe", "Bloodcrust Axe", () -> new JAxeItem(EnumJTier.BLOODCRUST_AXE));
    public static final RegistryObject<Item> BLOODCRUST_HOE = ItemRegistrys.registerToolItem("bloodcrust_hoe", "Bloodcrust Hoe", () -> new JHoeItem(EnumJTier.BLOODCRUST_HOE));
    public static final RegistryObject<Item> BLOODCRUST_SWORD = ItemRegistrys.registerToolItem("bloodcrust_sword", "Bloodcrust Sword", () -> new JSwordItem(EnumJTier.BLOODCRUST_SWORD));
    public static final RegistryObject<Item> BLOODCRUST_HELMET = ItemRegistrys.registerNormalItem("bloodcrust_helmet", "Bloodcrust Helmet", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> BLOODCRUST_CHEST = ItemRegistrys.registerNormalItem("bloodcrust_chestplate", "Bloodcrust Chestplate", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> BLOODCRUST_LEGS = ItemRegistrys.registerNormalItem("bloodcrust_leggings", "Bloodcrust Leggings", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> BLOODCRUST_BOOTS = ItemRegistrys.registerNormalItem("bloodcrust_boots", "Bloodcrust Boots", () -> new JArmorItem(EnumJTier.JArmorTier.BLOODCRUST, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> STAFF_OF_CONJURING = ItemRegistrys.registerToolItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(1, ConjuringProjectileEntity::new));
    public static final RegistryObject<Item> STAFF_OF_ESSENCIA = ItemRegistrys.registerToolItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(4, EssenciaProjectileEntity::new));

    public static final RegistryObject<Item> GOLDEN_EUCA_BOAT = ItemRegistrys.registerNormalItem("golden_euca_boat", "Gold Euca Boat", () -> new JBoatItem(JBoat.Type.GOLD_EUCA));
    public static final RegistryObject<Item> BROWN_EUCA_BOAT = ItemRegistrys.registerNormalItem("brown_euca_boat", "Brown Euca Boat", () -> new JBoatItem(JBoat.Type.BROWN_EUCA));

    public static final RegistryObject<Item> MAGE_EGG = ItemRegistrys.registerNormalItem("mage_spawn_egg", "Mage Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.MAGE_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> FLORO_EGG = ItemRegistrys.registerNormalItem("floro_spawn_egg", "Floro Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.FLORO_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> TOWER_GUARDIAN_EGG = ItemRegistrys.registerNormalItem("tower_guardian_spawn_egg", "Tower Guarduan Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.TOWER_GUARDIAN_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> ROCKITE_SMASHER_EGG = ItemRegistrys.registerNormalItem("rockite_smasher_spawn_egg", "Rockite Smasher Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ROCKITE_SMASHER_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> WITHERSPINE_SMASHER_EGG = ItemRegistrys.registerNormalItem("witherspine_spawn_egg", "Witherspine Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.WITHERSPINE_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> BROWN_HONGO_EGG = ItemRegistrys.registerNormalItem("brown_hongo_spawn_egg", "Brown Hongo Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.BROWN_HONGO_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));

    public static final RegistryObject<Item> ILLAGER_MECH_EGG = ItemRegistrys.registerNormalItem("illager_mech_spawn_egg", "Illager Mech Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ILLAGER_MECH_TYPE,
            0x948e8d, 0x3b3653, ItemRegistrys.eggProps()));



}