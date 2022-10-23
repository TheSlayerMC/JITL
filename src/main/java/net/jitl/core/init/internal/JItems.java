package net.jitl.core.init.internal;

import net.jitl.common.entity.base.JBoat;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.common.entity.projectile.FloroMudEntity;
import net.jitl.common.entity.projectile.PiercerEntity;
import net.jitl.common.items.*;
import net.jitl.common.items.base.*;
import net.jitl.common.items.gear.IAbility;
import net.jitl.common.items.gear.LuniumAbility;
import net.jitl.common.items.gear.bloodcrust.BloodcrustArmorAbility;
import net.jitl.common.items.gear.bloodcrust.BloodcrustSwordAbility;
import net.jitl.common.items.gear.celestium.CelestiumArmorAbility;
import net.jitl.common.items.gear.korite.KoriteSwordAbility;
import net.jitl.common.items.gear.mekyum.MekyumSwordAbility;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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

    private static final IAbility BASIC = new IAbility() {};
    private static final IAbility LUNIUM_ABILITY = new LuniumAbility();
    private static final IAbility MEKYUM_SWORD_ABILITY = new MekyumSwordAbility();
    private static final IAbility KORITE_SWORD_ABILITY = new KoriteSwordAbility();
    private static final IAbility CELESTIUM_ARMOR_ABILITY = new CelestiumArmorAbility();
    private static final IAbility BLOODCRUST_SWORD_ABILITY = new BloodcrustSwordAbility();
    private static final IAbility BLOODCRUST_ARMOR_ABILITY = new BloodcrustArmorAbility();

    public static final ArrayList<String> itemName = new ArrayList<>();
    public static final ArrayList<String> toolName = new ArrayList<>();
    public static final ArrayList<String> recordName = new ArrayList<>();
    public static final ArrayList<String> recordDescName = new ArrayList<>();
    public static final ArrayList<String> spawnName = new ArrayList<>();
    public static final ArrayList<String> spawnLangName = new ArrayList<>();

    public static final ArrayList<String> langName = new ArrayList<>();
    public static final ArrayList<String> toolLangName = new ArrayList<>();
    public static final ArrayList<String> recordLangName = new ArrayList<>();
    public static final ArrayList<String> recordDescLangName = new ArrayList<>();

    private static final int OVERWORLD_COLOR = 0x32f53f;
    private static final int NETHER_COLOR = 0x881a2b;
    private static final int END_COLOR = 0x000000/*0x931aa3*/;
    private static final int FROZEN_COLOR = 0x3ea4ff;
    private static final int BOILING_COLOR = 0xeb8026;
    private static final int EUCA_COLOR = 0xffff0b;
    private static final int DEPTHS_COLOR = 0x0705a7;
    private static final int CORBA_COLOR = 0x106903;
    private static final int TERRANIA_COLOR = 0x91046d;
    private static final int CLOUDIA_COLOR = 0xfa45cd;
    private static final int SENTERIAN_COLOR = 0x2e2d2c;
    //mob type colors
    private static final int PASSIVE_COLOR = 0x00ff00;
    private static final int NEUTRAL_COLOR = 0x555555;
    private static final int HOSTILE_COLOR = 0xff0000;
    private static final int TRADER_COLOR = 0x7d007d;
    private static final int BOSS_COLOR = 0xffff7d;

    public static final RegistryObject<Item> TEST_BUG = registerNormalItem("test_bug", "Test Bug", TestBugItem::new);

    public static final RegistryObject<Item> IRIDIUM_NUGGET = registerFuelItem("iridium_nugget", "Iridium Nugget", 1600);
    public static final RegistryObject<Item> ENDERILLIUM_SHARD = registerNormalItem("enderillium_shard", "Enderillium Shard");
    public static final RegistryObject<Item> WARPED_QUARTZ = registerNormalItem("warped_quartz", "Warped Quartz");
    public static final RegistryObject<Item> CRIMSON_QUARTZ = registerNormalItem("crimson_quartz", "Crimson Quartz");
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

    public static final RegistryObject<Item> BOIL_KEY = registerNormalItem("boil_key", "Boiling Lock Key", ChestInteractionItem::new);

    public static final RegistryObject<Item> PADLOCK = registerNormalItem("padlock", "Padlock", ChestInteractionItem::new);
    public static final RegistryObject<Item> CHEST_KEY = registerNormalItem("chest_key", "Master Chest Key", ChestInteractionItem::new);
    public static final RegistryObject<Item> JOURNEY_KEY = registerNormalItem("journey_key", "Journey Chest Key", ChestInteractionItem::new);
    public static final RegistryObject<Item> NETHER_KEY = registerNormalItem("nether_key", "Nether Chest Key", ChestInteractionItem::new);
    public static final RegistryObject<Item> FROZEN_KEY = registerNormalItem("frozen_key", "frozen Chest Key", ChestInteractionItem::new);
    public static final RegistryObject<Item> EUCA_KEY = registerNormalItem("euca_key", "Euca Chest Key", ChestInteractionItem::new);
    public static final RegistryObject<Item> BOILING_KEY = registerNormalItem("boiling_key", "Boiling Chest Key", ChestInteractionItem::new);

    public static final RegistryObject<Item> SAPPHIRE = registerNormalItem("sapphire", "Sapphire", JTabs.MATERIALS);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerToolItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(JToolTiers.SAPPHIRE_SHOVEL));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerToolItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(JToolTiers.SAPPHIRE_PICKAXE));
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerToolItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(JToolTiers.SAPPHIRE_AXE));
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerToolItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(JToolTiers.SAPPHIRE_HOE));
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerToolItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(JToolTiers.SAPPHIRE_SWORD, BASIC));
    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerNormalItem("sapphire_helmet", "Sapphire Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SAPPHIRE_CHEST = registerNormalItem("sapphire_chestplate", "Sapphire Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SAPPHIRE_LEGS = registerNormalItem("sapphire_leggings", "Sapphire Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerNormalItem("sapphire_boots", "Sapphire Boots", () -> new JArmorItem(JToolTiers.JArmorTier.SAPPHIRE, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> LUNIUM_POWDER = registerNormalItem("lunium_powder", "Lunium Powder");
    public static final RegistryObject<Item> LUNIUM_INGOT = registerNormalItem("lunium_ingot", "Lunium Ingot");
    public static final RegistryObject<Item> LUNIUM_SHOVEL = registerToolItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(JToolTiers.LUNIUM_SHOVEL));
    public static final RegistryObject<Item> LUNIUM_PICKAXE = registerToolItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(JToolTiers.LUNIUM_PICKAXE));
    public static final RegistryObject<Item> LUNIUM_AXE = registerToolItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(JToolTiers.LUNIUM_AXE));
    public static final RegistryObject<Item> LUNIUM_HOE = registerToolItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(JToolTiers.LUNIUM_HOE));
    public static final RegistryObject<Item> LUNIUM_SWORD = registerToolItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(JToolTiers.LUNIUM_SWORD, LUNIUM_ABILITY));
    public static final RegistryObject<Item> LUNIUM_HELMET = registerNormalItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, EquipmentSlot.HEAD, LUNIUM_ABILITY));
    public static final RegistryObject<Item> LUNIUM_CHEST = registerNormalItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, EquipmentSlot.CHEST, LUNIUM_ABILITY));
    public static final RegistryObject<Item> LUNIUM_LEGS = registerNormalItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, EquipmentSlot.LEGS, LUNIUM_ABILITY));
    public static final RegistryObject<Item> LUNIUM_BOOTS = registerNormalItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.LUNIUM, EquipmentSlot.FEET, LUNIUM_ABILITY));

    public static final RegistryObject<Item> RAW_SHADIUM = registerNormalItem("raw_shadium", "Raw Shadium");
    public static final RegistryObject<Item> SHADIUM_INGOT = registerNormalItem("shadium_ingot", "Shadium Ingot");
    public static final RegistryObject<Item> SHADIUM_SHOVEL = registerToolItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(JToolTiers.SHADIUM_SHOVEL));
    public static final RegistryObject<Item> SHADIUM_PICKAXE = registerToolItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(JToolTiers.SHADIUM_PICKAXE));
    public static final RegistryObject<Item> SHADIUM_AXE = registerToolItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(JToolTiers.SHADIUM_AXE));
    public static final RegistryObject<Item> SHADIUM_HOE = registerToolItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(JToolTiers.SHADIUM_HOE));
    public static final RegistryObject<Item> SHADIUM_SWORD = registerToolItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(JToolTiers.SHADIUM_SWORD, BASIC));
    public static final RegistryObject<Item> SHADIUM_HELMET = registerNormalItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, EquipmentSlot.HEAD, null));
    public static final RegistryObject<Item> SHADIUM_CHEST = registerNormalItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, EquipmentSlot.CHEST, null));
    public static final RegistryObject<Item> SHADIUM_LEGS = registerNormalItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, EquipmentSlot.LEGS, null));
    public static final RegistryObject<Item> SHADIUM_BOOTS = registerNormalItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(JToolTiers.JArmorTier.SHADIUM, EquipmentSlot.FEET, null));

    public static final RegistryObject<Item> RAW_BLOODCRUST = registerNormalItem("raw_bloodcrust", "Raw Bloodcrust");
    public static final RegistryObject<Item> BLOODCRUST_INGOT = registerNormalItem("bloodcrust_ingot", "Bloodcrust Ingot");
    public static final RegistryObject<Item> BLOODCRUST_SHOVEL = registerToolItem("bloodcrust_shovel", "Bloodcrust Shovel", () -> new JShovelItem(JToolTiers.BLOODCRUST_SHOVEL));
    public static final RegistryObject<Item> BLOODCRUST_PICKAXE = registerToolItem("bloodcrust_pickaxe", "Bloodcrust Pickaxe", () -> new JPickaxeItem(JToolTiers.BLOODCRUST_PICKAXE));
    public static final RegistryObject<Item> BLOODCRUST_AXE = registerToolItem("bloodcrust_axe", "Bloodcrust Axe", () -> new JAxeItem(JToolTiers.BLOODCRUST_AXE));
    public static final RegistryObject<Item> BLOODCRUST_HOE = registerToolItem("bloodcrust_hoe", "Bloodcrust Hoe", () -> new JHoeItem(JToolTiers.BLOODCRUST_HOE));
    public static final RegistryObject<Item> BLOODCRUST_SWORD = registerToolItem("bloodcrust_sword", "Bloodcrust Sword", () -> new JSwordItem(JToolTiers.BLOODCRUST_SWORD, BLOODCRUST_SWORD_ABILITY));
    public static final RegistryObject<Item> BLOODCRUST_HELMET = registerNormalItem("bloodcrust_helmet", "Bloodcrust Helmet", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, EquipmentSlot.HEAD, BLOODCRUST_ARMOR_ABILITY));
    public static final RegistryObject<Item> BLOODCRUST_CHEST = registerNormalItem("bloodcrust_chestplate", "Bloodcrust Chestplate", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, EquipmentSlot.CHEST, BLOODCRUST_ARMOR_ABILITY));
    public static final RegistryObject<Item> BLOODCRUST_LEGS = registerNormalItem("bloodcrust_leggings", "Bloodcrust Leggings", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, EquipmentSlot.LEGS, BLOODCRUST_ARMOR_ABILITY));
    public static final RegistryObject<Item> BLOODCRUST_BOOTS = registerNormalItem("bloodcrust_boots", "Bloodcrust Boots", () -> new JArmorItem(JToolTiers.JArmorTier.BLOODCRUST, EquipmentSlot.FEET, BLOODCRUST_ARMOR_ABILITY));

    public static final RegistryObject<Item> CELESTIUM_INGOT = registerNormalItem("celestium_ingot", "Celestium Ingot");
    public static final RegistryObject<Item> CELESTIUM_GEMSTONE = registerNormalItem("celestium_gemstone", "Celestium Gemstone");
    public static final RegistryObject<Item> CELESTIUM_SHOVEL = registerToolItem("celestium_shovel", "Celestium Shovel", () -> new JShovelItem(JToolTiers.CELESTIUM_SHOVEL));
    public static final RegistryObject<Item> CELESTIUM_PICKAXE = registerToolItem("celestium_pickaxe", "Celestium Pickaxe", () -> new JPickaxeItem(JToolTiers.CELESTIUM_PICKAXE));
    public static final RegistryObject<Item> CELESTIUM_AXE = registerToolItem("celestium_axe", "Celestium Axe", () -> new JAxeItem(JToolTiers.CELESTIUM_AXE));
    public static final RegistryObject<Item> CELESTIUM_HOE = registerToolItem("celestium_hoe", "Celestium Hoe", () -> new JHoeItem(JToolTiers.CELESTIUM_HOE));
    public static final RegistryObject<Item> CELESTIUM_SWORD = registerToolItem("celestium_sword", "Celestium Sword", () -> new JSwordItem(JToolTiers.CELESTIUM_SWORD, BASIC));

    public static final RegistryObject<Item> MEKYUM_INGOT = registerNormalItem("mekyum_ingot", "Mekyum Ingot");
    public static final RegistryObject<Item> MEKYUM_GEMSTONE = registerNormalItem("mekyum_gemstone", "Mekyum Gemstone");
    public static final RegistryObject<Item> MEKYUM_SHOVEL = registerToolItem("mekyum_shovel", "Mekyum Shovel", () -> new JShovelItem(JToolTiers.MEKYUM_SHOVEL));
    public static final RegistryObject<Item> MEKYUM_PICKAXE = registerToolItem("mekyum_pickaxe", "Mekyum Pickaxe", () -> new JPickaxeItem(JToolTiers.MEKYUM_PICKAXE));
    public static final RegistryObject<Item> MEKYUM_AXE = registerToolItem("mekyum_axe", "Mekyum Axe", () -> new JAxeItem(JToolTiers.MEKYUM_AXE));
    public static final RegistryObject<Item> MEKYUM_HOE = registerToolItem("mekyum_hoe", "Mekyum Hoe", () -> new JHoeItem(JToolTiers.MEKYUM_HOE));
    public static final RegistryObject<Item> MEKYUM_SWORD = registerToolItem("mekyum_sword", "Mekyum Sword", () -> new JSwordItem(JToolTiers.MEKYUM_SWORD, MEKYUM_SWORD_ABILITY));

    public static final RegistryObject<Item> STORON_INGOT = registerNormalItem("storon_ingot", "Storon Ingot");
    public static final RegistryObject<Item> STORON_GEMSTONE = registerNormalItem("storon_gemstone", "Storon Gemstone");
    public static final RegistryObject<Item> STORON_SHOVEL = registerToolItem("storon_shovel", "Storon Shovel", () -> new JShovelItem(JToolTiers.STORON_SHOVEL));
    public static final RegistryObject<Item> STORON_PICKAXE = registerToolItem("storon_pickaxe", "Storon Pickaxe", () -> new JPickaxeItem(JToolTiers.STORON_PICKAXE));
    public static final RegistryObject<Item> STORON_AXE = registerToolItem("storon_axe", "Storon Axe", () -> new JAxeItem(JToolTiers.STORON_AXE));
    public static final RegistryObject<Item> STORON_HOE = registerToolItem("storon_hoe", "Storon Hoe", () -> new JHoeItem(JToolTiers.STORON_HOE));
    public static final RegistryObject<Item> STORON_SWORD = registerToolItem("storon_sword", "Storon Sword", () -> new JSwordItem(JToolTiers.STORON_SWORD, BASIC));

    public static final RegistryObject<Item> KORITE_INGOT = registerNormalItem("korite_ingot", "Korite Ingot");
    public static final RegistryObject<Item> KORITE_GEMSTONE = registerNormalItem("korite_gemstone", "Korite Gemstone");
    public static final RegistryObject<Item> KORITE_SHOVEL = registerToolItem("korite_shovel", "Korite Shovel", () -> new JShovelItem(JToolTiers.KORITE_SHOVEL));
    public static final RegistryObject<Item> KORITE_PICKAXE = registerToolItem("korite_pickaxe", "Korite Pickaxe", () -> new JPickaxeItem(JToolTiers.KORITE_PICKAXE));
    public static final RegistryObject<Item> KORITE_AXE = registerToolItem("korite_axe", "korite Axe", () -> new JAxeItem(JToolTiers.KORITE_AXE));
    public static final RegistryObject<Item> KORITE_HOE = registerToolItem("korite_hoe", "Korite Hoe", () -> new JHoeItem(JToolTiers.KORITE_HOE));
    public static final RegistryObject<Item> KORITE_SWORD = registerToolItem("korite_sword", "korite Sword", () -> new JSwordItem(JToolTiers.KORITE_SWORD, KORITE_SWORD_ABILITY));

    public static final RegistryObject<Item> STAFF_OF_CONJURING = registerToolItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(1, ConjuringProjectileEntity::new));
    public static final RegistryObject<Item> STAFF_OF_ESSENCIA = registerToolItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(4, EssenciaProjectileEntity::new));

    public static final RegistryObject<Item> GOLDEN_EUCA_BOAT = registerNormalItem("golden_euca_boat", "Gold Euca Boat", () -> new JBoatItem(JBoat.Type.GOLD_EUCA));
    public static final RegistryObject<Item> BROWN_EUCA_BOAT = registerNormalItem("brown_euca_boat", "Brown Euca Boat", () -> new JBoatItem(JBoat.Type.BROWN_EUCA));
    public static final RegistryObject<Item> FROZEN_BOAT = registerNormalItem("frozen_boat", "Frostwood Boat", () -> new JBoatItem(JBoat.Type.FROZEN));
    public static final RegistryObject<Item> DEPTHS_BOAT = registerNormalItem("depths_boat", "Depths Boat", () -> new JBoatItem(JBoat.Type.DEPTHS));

    public static final RegistryObject<Item> EUCA_PIERCER = registerToolItem("euca_piercer", "Euca Piercer", () ->
            new PiercerItem(rangedProps().durability(50), (worldIn, owner, stack) -> new PiercerEntity(owner, worldIn, stack, 3, 10.0F)));

    public static final RegistryObject<Item> PIERCER = registerToolItem("piercer", "Piercer", () ->
            new PiercerItem(rangedProps().durability(128), (worldIn, owner, stack) -> new PiercerEntity(owner, worldIn, stack, 3, 3.0F)));

    public static final RegistryObject<Item> MUD_BALL = registerNormalItem("mud_ball", "Mud Ball", () -> new ThrowableItem(rangedProps(),
            (world, thrower) -> new FloroMudEntity(JEntities.FLORO_MUD_TYPE.get(), world, thrower, 0.0F)).setSound(() -> SoundEvents.SNOWBALL_THROW));
    
    public static final RegistryObject<Item> POISON_SWORD = registerToolItem("poison_sword", "Poison Sword", () -> new JSwordItem(JToolTiers.POISON_SWORD, BASIC));
    public static final RegistryObject<Item> CLOUD_SLICER = registerToolItem("cloud_slicer", "Cloud Slicer", () -> new JSwordItem(JToolTiers.CLOUD_SLICER, BASIC));
    public static final RegistryObject<Item> DRAGONS_TOOTH = registerToolItem("dragons_tooth", "Dragons Tooth", () -> new JSwordItem(JToolTiers.DRAGONS_TOOTH, BASIC));
    public static final RegistryObject<Item> NETHER_BEAST_SWORD = registerToolItem("nether_beast_sword", "Netherbeast Sword", () -> new JSwordItem(JToolTiers.NETHER_BEAST_SWORD, BASIC));
    public static final RegistryObject<Item> WITHERING_BEAST_SWORD = registerToolItem("withering_beast_sword", "Witheringbeast Sword", () -> new JSwordItem(JToolTiers.WITHERING_BEAST_SWORD, BASIC));
    public static final RegistryObject<Item> CALCIA_SWORD = registerToolItem("calcia_sword", "Calcia Sword", () -> new JSwordItem(JToolTiers.CALCIA_SWORD, BASIC));
    public static final RegistryObject<Item> CHAMPIONS_SWORD = registerToolItem("champions_sword", "Champions Sword", () -> new JSwordItem(JToolTiers.CHAMPIONS_SWORD, BASIC));
    public static final RegistryObject<Item> THE_WRAITH = registerToolItem("the_wraith", "The Wraith", () -> new JSwordItem(JToolTiers.THE_WRAITH, BASIC));
    public static final RegistryObject<Item> BUBBLE_SWORD = registerToolItem("bubble_sword", "Bubble Sword", () -> new JSwordItem(JToolTiers.BUBBLE_SWORD, BASIC));
    public static final RegistryObject<Item> BOILING_BLADE = registerToolItem("boiling_blade", "Boiling Blade", () -> new JSwordItem(JToolTiers.BOILING_BLADE, BASIC));
    public static final RegistryObject<Item> LOGGERS_SWORD = registerToolItem("loggers_sword", "Loggers Sword", () -> new JSwordItem(JToolTiers.LOGGERS_SWORD, BASIC));
    public static final RegistryObject<Item> NATURES_BLADE = registerToolItem("natures_blade", "Natures Blade", () -> new JSwordItem(JToolTiers.NATURES_BLADE, BASIC));
    public static final RegistryObject<Item> DEPTHS_DARKSWORD = registerToolItem("depths_dark_sword", "Depths Darksword", () -> new JSwordItem(JToolTiers.DEPTHS_DARKSWORD, BASIC));
    public static final RegistryObject<Item> DEPTHS_SLAYER = registerToolItem("depths_slayer", "Depths Slayer", () -> new JSwordItem(JToolTiers.DEPTHS_SLAYER, BASIC));
    public static final RegistryObject<Item> SNOW_SHOVELER = registerToolItem("snow_shoveler", "Snow Shoveler", () -> new JSwordItem(JToolTiers.SNOW_SHOVELER, BASIC));
    public static final RegistryObject<Item> FROSTY_SWORD = registerToolItem("frosty_sword", "Frosty Sword", () -> new JSwordItem(JToolTiers.FROSTY_SWORD, BASIC));
    public static final RegistryObject<Item> FROSTBITTEN_SWORD = registerToolItem("frostbitten_sword", "Frostbitten Sword", () -> new JSwordItem(JToolTiers.FROSTBITTEN_SWORD, BASIC));
    public static final RegistryObject<Item> TREE_HUGGER = registerToolItem("tree_hugger", "Tree Hugger", () -> new JSwordItem(JToolTiers.TREE_HUGGER, BASIC));
    public static final RegistryObject<Item> CORE_MENDER = registerToolItem("core_mender", "Core Mender", () -> new JSwordItem(JToolTiers.CORE_MENDER, BASIC));
    public static final RegistryObject<Item> ROYAL_BLADE = registerToolItem("royal_blade", "Royal Blade", () -> new JSwordItem(JToolTiers.ROYAL_BLADE, BASIC));
    public static final RegistryObject<Item> ROYAL_STABBER = registerToolItem("royal_stabber", "Royal Stabber", () -> new JSwordItem(JToolTiers.ROYAL_STABBER, BASIC));
    public static final RegistryObject<Item> ROC_SWORD = registerToolItem("roc_sword", "Roc Sword", () -> new JSwordItem(JToolTiers.ROC_SWORD, BASIC));
    public static final RegistryObject<Item> SWORD_THUNDERBIRD = registerToolItem("sword_of_the_thunderbird", "Sword of the Thunderbird", () -> new JSwordItem(JToolTiers.SWORD_THUNDERBIRD, BASIC));
    public static final RegistryObject<Item> BLOODWIELD_SWORD = registerToolItem("blood_wield_sword", "Blood Wielder", () -> new JSwordItem(JToolTiers.BLOODWIELD_SWORD, BASIC));
    public static final RegistryObject<Item> CHARRED_BLADE = registerToolItem("charred_blade", "Charred Blade", () -> new JSwordItem(JToolTiers.CHARRED_BLADE, BASIC));
    public static final RegistryObject<Item> SIZZLER_SWORD = registerToolItem("sizzler_sword", "Sizzler Sword", () -> new JSwordItem(JToolTiers.SIZZLER_SWORD, BASIC));
    public static final RegistryObject<Item> FLUFFY_BLADE = registerToolItem("fluffy_blade", "Fluffy Blade", () -> new JSwordItem(JToolTiers.FLUFFY_BLADE, BASIC));
    public static final RegistryObject<Item> GOLEM_SWORD = registerToolItem("golem_sword", "Golem Sword", () -> new JSwordItem(JToolTiers.GOLEM_SWORD, BASIC));
    public static final RegistryObject<Item> THUNDERBLADE = registerToolItem("thunder_blade", "Thunder Blade", () -> new JSwordItem(JToolTiers.THUNDERBLADE, BASIC));
    public static final RegistryObject<Item> SENTRY_SWORD = registerToolItem("sentry_sword", "Sentry Sword", () -> new JSwordItem(JToolTiers.SENTRY_SWORD, BASIC));
    public static final RegistryObject<Item> CRYSTAL_BLADE = registerToolItem("crystal_blade", "Crystal Blade", () -> new JSwordItem(JToolTiers.CRYSTAL_BLADE, BASIC));
    public static final RegistryObject<Item> STARLIGHT_BLADE = registerToolItem("starlight_blade", "Starlight Blade", () -> new JSwordItem(JToolTiers.STARLIGHT_BLADE, BASIC));
    public static final RegistryObject<Item> PEDAL_SWORD = registerToolItem("pedal_sword", "Pedal Sword", () -> new JSwordItem(JToolTiers.PEDAL_SWORD, BASIC));
    public static final RegistryObject<Item> WITHIC_BLADE = registerToolItem("withic_blade", "Withic Blade", () -> new JSwordItem(JToolTiers.WITHIC_BLADE, BASIC));
    public static final RegistryObject<Item> REINFORCED_CRYSTAL_SWORD = registerToolItem("reinforced_crystal_sword", "Reinforced Crystal Sword", () -> new JSwordItem(JToolTiers.RE_CRYSTAL_SWORD, BASIC));
    public static final RegistryObject<Item> REINFORCED_STONE_SWORD = registerToolItem("reinforced_stone_sword", "Reinforced Stone Sword", () -> new JSwordItem(JToolTiers.RE_STONE_SWORD, BASIC));
    public static final RegistryObject<Item> TERRALIGHT_BLADE = registerToolItem("terralight_blade", "Terralight Blade", () -> new JSwordItem(JToolTiers.TERRALIGHT_BLADE, BASIC));
    public static final RegistryObject<Item> TERRANA_SWORD = registerToolItem("terrana_sword", "Terrana Sword", () -> new JSwordItem(JToolTiers.TERRANA_SWORD, BASIC));
    public static final RegistryObject<Item> TERROLICA_SWORD = registerToolItem("terrolica_sword", "Terrolica Sword", () -> new JSwordItem(JToolTiers.TERROLICA_SWORD, BASIC));
    public static final RegistryObject<Item> VOLITE_SWORD = registerToolItem("volite_sword", "Volite Sword", () -> new JSwordItem(JToolTiers.VOLITE_SWORD, BASIC));
    public static final RegistryObject<Item> KINGS_SWORD = registerToolItem("kings_sword", "Kings Sword", () -> new JSwordItem(JToolTiers.KINGS_SWORD, BASIC));
    public static final RegistryObject<Item> DEMONIC_SWORD = registerToolItem("demonic_sword", "Wither Sword", () -> new JSwordItem(JToolTiers.DEMONIC_SWORD, BASIC));
    public static final RegistryObject<Item> VINESTRAND_BLADE = registerToolItem("vinestrand_blade", "Vinestrand Blade", () -> new JSwordItem(JToolTiers.VINESTRAND_BLADE, BASIC));
    public static final RegistryObject<Item> DARK_PINE_SWORD = registerToolItem("dark_pine_sword", "Dark Pine Sword", () -> new JSwordItem(JToolTiers.DARK_PINE_SWORD, BASIC));
    public static final RegistryObject<Item> HEALERS_BLADE = registerToolItem("healers_blade", "Healers Blade", () -> new JSwordItem(JToolTiers.HEALERS_BLADE, BASIC));
    public static final RegistryObject<Item> TERRONIC_BLADE = registerToolItem("terronic_blade", "Terronic Blade", () -> new JSwordItem(JToolTiers.TERRONIC_BLADE, BASIC));
    public static final RegistryObject<Item> DARK_KEEPERS_PLADE = registerToolItem("blade_of_dark_keeper", "Dark Keepers Blade", () -> new JSwordItem(JToolTiers.DARK_KEEPER, BASIC));
    public static final RegistryObject<Item> DEVELOPER_SWORD = registerToolItem("developer_sword", "Creative Sword", () -> new JSwordItem(JToolTiers.DEVELOPER_SWORD, BASIC));
    
    
    public static final RegistryObject<Item> MAGE_EGG = register("mage_spawn_egg", "Mage Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.MAGE_TYPE,
            OVERWORLD_COLOR, TRADER_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> FLORO_EGG = register("floro_spawn_egg", "Floro Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.FLORO_TYPE,
            OVERWORLD_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> TOWER_GUARDIAN_EGG = register("tower_guardian_spawn_egg", "Tower Guarduan Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.TOWER_GUARDIAN_TYPE,
            OVERWORLD_COLOR, BOSS_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> ROCKITE_SMASHER_EGG = register("rockite_smasher_spawn_egg", "Rockite Smasher Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ROCKITE_SMASHER_TYPE,
            OVERWORLD_COLOR, BOSS_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> WITHERSPINE_SMASHER_EGG = register("witherspine_spawn_egg", "Witherspine Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.WITHERSPINE_TYPE,
            NETHER_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> BROWN_HONGO_EGG = register("brown_hongo_spawn_egg", "Brown Hongo Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.BROWN_HONGO_TYPE,
            OVERWORLD_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> ILLAGER_MECH_EGG = register("illager_mech_spawn_egg", "Illager Mech Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ILLAGER_MECH_TYPE,
            OVERWORLD_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> EUCA_CHARGER_EGG = register("euca_charger_spawn_egg", "Euca Charger Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.EUCA_CHARGER_TYPE,
            EUCA_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> DYNASTER_EGG = register("dynaster_spawn_egg", "Dynaster Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.DYNASTER_TYPE,
            EUCA_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> GOLDBOT_EGG = register("goldbot_spawn_egg", "Goldbot Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.GOLDBOT_TYPE,
            EUCA_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> SHIMMERER_EGG = register("shimmerer_spawn_egg", "Shimmerer Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.SHIMMERER_TYPE,
            EUCA_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> GOLDER_EGG = register("golder_spawn_egg", "Golder Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.GOLDER_TYPE,
            EUCA_COLOR, HOSTILE_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> ESKIMO_EGG = register("eskimo_spawn_egg", "Eskimo Spawn Egg", () -> new ForgeSpawnEggItem(JEntities.ESKIMO_TYPE,
            FROZEN_COLOR, TRADER_COLOR, eggProps()), ItemType.SPAWN_EGG);

    public static final RegistryObject<Item> UNDERWATER_WORLD_RECORD = registerRecord("underwater_world_record", "Blue Water", JSounds.UNDERWATER_WORLD, 2640);
    public static final RegistryObject<Item> GOLD_PLAINS_RECORD = registerRecord("gold_plains_record", "Gold Plains", JSounds.GOLD_PLAINS_MUSIC, 1120);
    public static final RegistryObject<Item> EUCA_RECORD_1 = registerRecord("euca_record_1", "Euca", JSounds.EUCA_DISC_1, 1200);
    public static final RegistryObject<Item> EUCA_RECORD_2 = registerRecord("euca_record_2", "Euca", JSounds.EUCA_DISC_2, 620);
    public static final RegistryObject<Item> EUCA_RECORD_3 = registerRecord("euca_record_3", "Euca", JSounds.EUCA_DISC_3, 3500);
    public static final RegistryObject<Item> FROZEN_RECORD_1 = registerRecord("frozen_record_1", "Frozen", JSounds.FROZEN_DISC_1, 3480);
    public static final RegistryObject<Item> BOIL_RECORD_1 = registerRecord("boil_record_1", "Boiling Point", JSounds.BOIL_DISC_1, 3140);


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

    public static RegistryObject<Item> registerRecord(String name, String descTranslated, Supplier<SoundEvent> sound, int tickLengths) {
        recordDescName.add("item.jitl." + name + ".desc");
        recordDescLangName.add(descTranslated);
        return register(name, "Journey Record", () -> new RecordItem(4, sound, miscProps().stacksTo(1), tickLengths), ItemType.RECORD);
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
        if(type == ItemType.RECORD) {
            recordName.add(name);
            recordLangName.add(translatedName);
        }
        if(type == ItemType.SPAWN_EGG) {
            spawnName.add(name);
            spawnLangName.add(translatedName);
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

    public enum ItemType {
        ITEM,
        RECORD,
        SPAWN_EGG,
        TOOL;
    }
}