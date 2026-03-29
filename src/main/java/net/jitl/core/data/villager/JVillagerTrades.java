package net.jitl.core.data.villager;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

public class JVillagerTrades {

    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_NAME_TAG = resourceKey("emerald_name_tag");
    public static final ResourceKey<VillagerTrade> CRYPIAN = resourceKey("euca/crypian");

    public static final ResourceKey<VillagerTrade> BOIL_TRADER_MOLTEN_KNIFE = resourceKey("boil/boil_trader_molten_knife");
    public static final ResourceKey<VillagerTrade> BOIL_TRADER_BOILING_BLADE = resourceKey("boil/boil_trader_boiling_blade");
    public static final ResourceKey<VillagerTrade> BOIL_TRADER_FLAMING_BOW = resourceKey("boil/boil_trader_flaming_bow");

    public static final ResourceKey<VillagerTrade> CONVICT_CHARRED_BLADE = resourceKey("boil/convict_charred_blade");
    public static final ResourceKey<VillagerTrade> CONVICT_CHARRED_BOW = resourceKey("boil/convict_charred_bow");
    public static final ResourceKey<VillagerTrade> CONVICT_BLOODWIELD_SWORD = resourceKey("boil/convict_bloodwild_sword");

    public static final ResourceKey<VillagerTrade> STARLIGHT_BLACKSMITH_GOLEM_SWORD = resourceKey("cloudia/starlight_blacksmith_golem_sword");
    public static final ResourceKey<VillagerTrade> STARLIGHT_BLACKSMITH_GOLEM_BOW = resourceKey("cloudia/starlight_blacksmith_golem_bow");
    public static final ResourceKey<VillagerTrade> STARLIGHT_BLACKSMITH_STARLIGHT_BLADE = resourceKey("cloudia/starlight_blacksmith_starlight_blade");
    public static final ResourceKey<VillagerTrade> STARLIGHT_BLACKSMITH_STARLIGHT_BOW = resourceKey("cloudia/starlight_blacksmith_starlight_bow");

    public static final ResourceKey<VillagerTrade> STARLIGHT_VILLAGER_CLOUDIA_ORB = resourceKey("cloudia/starlight_villager_cloudia_orb");
    public static final ResourceKey<VillagerTrade> STARLIGHT_VILLAGER_LUNITE_CHUNK = resourceKey("cloudia/starlight_villager_lunite_chunk");
    public static final ResourceKey<VillagerTrade> STARLIGHT_VILLAGER_MYSTERIOUS_DISK = resourceKey("cloudia/starlight_villager_mysterious_disk");

    public static final ResourceKey<VillagerTrade> GREEN_TORDO_HEALERS_BLADE = resourceKey("corba/green_tordo_healers_blade");
    public static final ResourceKey<VillagerTrade> GREEN_TORDO_TREE_HUGGER = resourceKey("corba/green_tordo_tree_hugger");

    public static final ResourceKey<VillagerTrade> HOODED = resourceKey("corba/hooded");

    public static final ResourceKey<VillagerTrade> RED_TORDO_TOMATO_SEEDS = resourceKey("corba/red_tordo_tomato_seeds");
    public static final ResourceKey<VillagerTrade> RED_TORDO_CORVEGGIES = resourceKey("corba/red_tordo_corveggies");
    public static final ResourceKey<VillagerTrade> RED_TORDO_CRACKENCANE_SEEDS = resourceKey("corba/red_tordo_crackencane_seeds");
    public static final ResourceKey<VillagerTrade> RED_TORDO_CRAKEBULB_SEEDS = resourceKey("corba/red_tordo_crakebulb_seeds");
    public static final ResourceKey<VillagerTrade> RED_TORDO_SPINEBERRIES = resourceKey("corba/red_tordo_spineberries");
    public static final ResourceKey<VillagerTrade> RED_TORDO_GLOWA_SEEDS = resourceKey("corba/red_tordo_glowa_seeds");
    public static final ResourceKey<VillagerTrade> RED_TORDO_ZATPEDAL_SEEDS = resourceKey("corba/red_tordo_zatpedal_seeds");

    public static final ResourceKey<VillagerTrade> OVERGROWN_MERCHANT_VINESTRAND_BLADE = resourceKey("corba/overgrown_merchant_vinestrand_blade");
    public static final ResourceKey<VillagerTrade> OVERGROWN_MERCHANT_DARK_PINE_SWORD = resourceKey("corba/overgrown_merchant_dark_pine_sword");
    public static final ResourceKey<VillagerTrade> OVERGROWN_MERCHANT_OVERGROWN_STAFF = resourceKey("corba/overgrown_merchant_overgrown_staff");
    public static final ResourceKey<VillagerTrade> OVERGROWN_MERCHANT_OVERGROWN_HAMMER = resourceKey("corba/overgrown_merchant_overgrown_hammer");

    public static final ResourceKey<VillagerTrade> AURON_DEPTHS_SLAYER = resourceKey("depths/auron_depths_slayer");
    public static final ResourceKey<VillagerTrade> AURON_DEPTHS_BOW = resourceKey("depths/auron_depths_bow");
    public static final ResourceKey<VillagerTrade> AURON_DEPTHS_DARKSWORD = resourceKey("depths/auron_depths_darksword");
    public static final ResourceKey<VillagerTrade> AURON_DARK_ENFORCER = resourceKey("depths/auron_depths_enforcer");

    public static final ResourceKey<VillagerTrade> STARING_GUARDIAN_DARK_KEY = resourceKey("depths/staring_guardian_dark_key");

    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_ROYAL_BOW = resourceKey("euca/alloy_mender_royal_bow");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_ROYAL_KNIFE = resourceKey("euca/alloy_mender_royal_knife");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_ROYAL_HAMMER = resourceKey("euca/alloy_mender_royal_hammer");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_CELEKIUM_BATTLE_AXE = resourceKey("euca/alloy_mender_celekium_battle_axe");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_CELESTITE_BATTLE_AXE = resourceKey("euca/alloy_mender_celestite_battle_axe");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_STORUM_BATTLE_AXE = resourceKey("euca/alloy_mender_storm_battle_axe");
    public static final ResourceKey<VillagerTrade> ALLOY_MENDER_BRONZED_BATTLE_AXE = resourceKey("euca/alloy_mender_bronzed_battle_axe");

    public static final ResourceKey<VillagerTrade> TERRANIAN_ENCHANTER_TERROLICA_SWORD = resourceKey("terrania/terranian_enchanter_terrolical_sword");
    public static final ResourceKey<VillagerTrade> TERRANIAN_ENCHANTER_TERRALIGHT_BLADE = resourceKey("terrania/terranian_enchanter_terralight_blade");
    public static final ResourceKey<VillagerTrade> TERRANIAN_ENCHANTER_ANCIENT_PIECE = resourceKey("terrania/terranian_enchanter_ancient_piece");
    public static final ResourceKey<VillagerTrade> TERRANIAN_ENCHANTER_ANCIENT_FRAGMENT = resourceKey("terrania/terranian_enchanter_ancient_fragment");

    public static final ResourceKey<VillagerTrade> TERRANIAN_TRADER_HEALERS_BLADE = resourceKey("terrania/terranian_trader_healers_blade");
    public static final ResourceKey<VillagerTrade> TERRANIAN_TRADER_TREE_HUGGER = resourceKey("terrania/terranian_trader_tree_hugger");
    public static final ResourceKey<VillagerTrade> TERRANIAN_TRADER_ANCIENT_CHUNK = resourceKey("terrania/terranian_trader_ancient_chunk");
    public static final ResourceKey<VillagerTrade> TERRANIAN_TRADER_ANCIENT_SHARD = resourceKey("terrania/terranian_trader_ancient_shard");

    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_BLAZE_POWDER = resourceKey("overworld/rockite_blaze_powder");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_CRYSTALLIZED_BATTLE_AXE = resourceKey("overworld/rockite_golem_crystalized_battle_axe");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_CRYSTALLIZED_HAMMER = resourceKey("overworld/rockite_golem_crystalized_hammer");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_ROCKY_HAMMER = resourceKey("overworld/rockite_golem_rocky_hammer");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_ROCK_LAUNCHER = resourceKey("overworld/rockite_golem_rock_launcher");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_STAFF_OF_DIVINITY = resourceKey("overworld/rockite_golem_staff_of_divinity");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_CRYSTAL_STAFF = resourceKey("overworld/rockite_golem_crystal_staff");
    public static final ResourceKey<VillagerTrade> ROCKITE_GOLEM_ROCKY_BATTLE_AXE = resourceKey("overworld/rockite_golem_rocky_battle_axe");

    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBORN_SOUL = resourceKey("frozen/eskimo_frostborn_soul");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROZEN_ICE_BALL = resourceKey("frozen/eskimo_frozen_ice_ball");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTY_SWORD = resourceKey("frozen/eskimo_frosty_sword");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTY_BOW = resourceKey("frozen/eskimo_frosty_bow");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTY_PIERCER = resourceKey("frozen/eskimo_frosty_piercer");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_SWORD = resourceKey("frozen/eskimo_frostbitten_sword");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_BOW = resourceKey("frozen/eskimo_frostbitten_bow");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_PIERCER = resourceKey("frozen/eskimo_frostbitten_piercer");
    public static final ResourceKey<VillagerTrade> ESKIMO_CRYSTAL_FLAKE_HELMET = resourceKey("frozen/eskimo_crystal_flake_helmet");
    public static final ResourceKey<VillagerTrade> ESKIMO_CRYSTAL_FLAKE_CHEST = resourceKey("frozen/eskimo_crystal_flake_chest");
    public static final ResourceKey<VillagerTrade> ESKIMO_CRYSTAL_FLAKE_LEGS = resourceKey("frozen/eskimo_crystal_flake_legs");
    public static final ResourceKey<VillagerTrade> ESKIMO_CRYSTAL_FLAKE_BOOTS = resourceKey("frozen/eskimo_crystal_flake_boots");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_HELMET = resourceKey("frozen/eskimo_frostbitten_helmet");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_CHEST = resourceKey("frozen/eskimo_frostbitten_chest");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_LEGS = resourceKey("frozen/eskimo_frostbitten_legs");
    public static final ResourceKey<VillagerTrade> ESKIMO_FROSTBITTEN_BOOTS = resourceKey("frozen/eskimo_frostbitten_boots");

    public static final ResourceKey<VillagerTrade> GUNSMITH_NETHER_PLASMA = resourceKey("overworld/gunsmith_nether_plasma");
    public static final ResourceKey<VillagerTrade> GUNSMITH_OCEAN_PLASMA = resourceKey("overworld/gunsmith_ocean_plasma");
    public static final ResourceKey<VillagerTrade> GUNSMITH_FOREST_PLASMA = resourceKey("overworld/gunsmith_forest_plasma");
    public static final ResourceKey<VillagerTrade> GUNSMITH_ROCK_LAUNCHER = resourceKey("overworld/gunsmith_rock_launcher");
    public static final ResourceKey<VillagerTrade> GUNSMITH_CHAOS_CANNON = resourceKey("overworld/gunsmith_chaos_cannon");
    public static final ResourceKey<VillagerTrade> GUNSMITH_EYE_BLASTER = resourceKey("overworld/gunsmith_eye_blaster");
    public static final ResourceKey<VillagerTrade> GUNSMITH_FLAMING_HAMMER = resourceKey("overworld/gunsmith_flaming_hammer");
    public static final ResourceKey<VillagerTrade> GUNSMITH_NETHIC_HAMMER = resourceKey("overworld/gunsmith_nethic_hammer");
    public static final ResourceKey<VillagerTrade> GUNSMITH_WITHIC_HAMMER = resourceKey("overworld/gunsmith_withic_hammer");

    public static final ResourceKey<VillagerTrade> BLACKSMITH_DAWN_BREAKER = resourceKey("overworld/blacksmith_dawn_breaker");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_TEMPEST_BATTLE_AXE = resourceKey("overworld/blacksmith_tempest_battle_axe");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_DRAGONS_TOOTH = resourceKey("overworld/blacksmith_dragons_tooth");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_POISON_SWORD = resourceKey("overworld/blacksmith_poison_sword");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_CLOUD_SLICER = resourceKey("overworld/blacksmith_cloud_slicer");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_BACK_BITER = resourceKey("overworld/blacksmith_back_biter");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_SUNSET_PIERCER = resourceKey("overworld/blacksmith_sunset_piercer");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_AQUATIC_KNIFE = resourceKey("overworld/blacksmith_aquatic_knife");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_POISON_BOW = resourceKey("overworld/blacksmith_poison_bow");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_DARKNESS_BOW = resourceKey("overworld/blacksmith_darkness_bow");
    public static final ResourceKey<VillagerTrade> BLACKSMITH_FROZEN_BOW = resourceKey("overworld/blacksmith_frozen_bow");

    public static final ResourceKey<VillagerTrade> MAGE_LOOT_POUCH = resourceKey("overworld/mage_loot_pouch");
    public static final ResourceKey<VillagerTrade> MAGE_SAPPHIRE = resourceKey("overworld/mage_sapphire");
    public static final ResourceKey<VillagerTrade> MAGE_PET_ROBOT_SPAWNER = resourceKey("overworld/mage_pet_robot_spawner");
    public static final ResourceKey<VillagerTrade> MAGE_FERMENTED_SPIDER_EYE = resourceKey("overworld/mage_fermented_spider_eye");
    public static final ResourceKey<VillagerTrade> MAGE_GUNPOWDER = resourceKey("overworld/mage_gunpowder");
    public static final ResourceKey<VillagerTrade> MAGE_REDSTONE = resourceKey("overworld/mage_redstone");
    public static final ResourceKey<VillagerTrade> MAGE_PHANTOM_MEMBRANE = resourceKey("overworld/mage_phantom_member");
    public static final ResourceKey<VillagerTrade> MAGE_GHAST_TEAR = resourceKey("overworld/mage_ghast_tear");
    public static final ResourceKey<VillagerTrade> MAGE_MAGMA_CREAM = resourceKey("overworld/mage_magma_cream");
    public static final ResourceKey<VillagerTrade> MAGE_GLOWSTONE_DUST = resourceKey("overworld/mage_glowstone_dust");
    public static final ResourceKey<VillagerTrade> MAGE_BLAZE_POWDER = resourceKey("overworld/mage_blaze_powder");
    public static final ResourceKey<VillagerTrade> MAGE_EARTHEN_HAMMER = resourceKey("overworld/mage_earthen_hammer");
    public static final ResourceKey<VillagerTrade> MAGE_SPELLBINDING_HAMMER = resourceKey("overworld/mage_spellbinding_hammer");
    public static final ResourceKey<VillagerTrade> MAGE_STAFF_OF_GREENPACE = resourceKey("overworld/mage_staff_of_greenpace");
    public static final ResourceKey<VillagerTrade> MAGE_STAFF_OF_HELLSTONE = resourceKey("overworld/mage_staff_of_hellstone");
    public static final ResourceKey<VillagerTrade> MAGE_DOOMSBRINGER = resourceKey("overworld/mage_doomsbringer");
    public static final ResourceKey<VillagerTrade> MAGE_STAFF_OF_ESSENCIA = resourceKey("overworld/mage_staff_of_essencia");
    public static final ResourceKey<VillagerTrade> MAGE_WIZARDS_STAR = resourceKey("overworld/mage_wizards_star");
    public static final ResourceKey<VillagerTrade> MAGE_STAFF_OF_ENLIGHTENMENT = resourceKey("overworld/mage_staff_of_enlightenment");
    public static final ResourceKey<VillagerTrade> MAGE_STAFF_OF_CONJURING = resourceKey("overworld/mage_staff_of_conjuring");
    public static final ResourceKey<VillagerTrade> MAGE_TELEPORTATION_STAFF = resourceKey("overworld/mage_teleportation_staff");

    public static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        registerTrade(context, CRYPIAN, new TradeCost(JItems.PERIDOT_GEMSTONE, 1), new ItemStackTemplate(Items.COMPASS, 1));

        registerTrade(context, ALLOY_MENDER_ROYAL_BOW, new TradeCost(JItems.SHIMMERER_DUST, 64), new TradeCost(JItems.METAL_DISK, 1), new ItemStackTemplate(JItems.ROYAL_BOW, 1));
        registerTrade(context, ALLOY_MENDER_ROYAL_KNIFE, new TradeCost(JItems.SHIMMERER_DUST, 64), new TradeCost(JItems.GOLDER_DUST, 1), new ItemStackTemplate(JItems.ROYAL_KNIFE, 16));
        registerTrade(context, ALLOY_MENDER_ROYAL_HAMMER, new TradeCost(JItems.SHIMMERER_DUST, 64), new TradeCost(JItems.METAL_DISK, 2), new ItemStackTemplate(JItems.ROYAL_HAMMER, 1));
        registerTrade(context, ALLOY_MENDER_CELEKIUM_BATTLE_AXE, new TradeCost(JBlocks.CELESTIUM_BLOCK, 8), new TradeCost(JBlocks.MEKYUM_BLOCK, 8), new ItemStackTemplate(JItems.CELEKIUM_BATTLE_AXE, 1));
        registerTrade(context, ALLOY_MENDER_CELESTITE_BATTLE_AXE, new TradeCost(JBlocks.CELESTIUM_BLOCK, 8), new TradeCost(JBlocks.KORITE_BLOCK, 8), new ItemStackTemplate(JItems.CELESTITE_BATTLE_AXE, 1));
        registerTrade(context, ALLOY_MENDER_STORUM_BATTLE_AXE, new TradeCost(JBlocks.STORON_BLOCK, 8), new TradeCost(JBlocks.MEKYUM_BLOCK, 8), new ItemStackTemplate(JItems.STORUM_BATTLE_AXE, 1));
        registerTrade(context, ALLOY_MENDER_BRONZED_BATTLE_AXE, new TradeCost(JBlocks.STORON_BLOCK, 8), new TradeCost(JBlocks.KORITE_BLOCK, 8), new ItemStackTemplate(JItems.BRONZED_BATTLE_AXE, 1));


        registerTrade(context, BOIL_TRADER_MOLTEN_KNIFE, new TradeCost(JItems.ASH, 10), new TradeCost(JItems.BOIL_POWDER, 16), new ItemStackTemplate(JItems.MOLTEN_KNIFE, 16));
        registerTrade(context, BOIL_TRADER_BOILING_BLADE, new TradeCost(JItems.ASH, 16), new TradeCost(JItems.BOIL_POWDER, 64), new ItemStackTemplate(JItems.BOILING_BLADE, 1));
        registerTrade(context, BOIL_TRADER_FLAMING_BOW, new TradeCost(JItems.ASH, 16), new TradeCost(JItems.BOIL_POWDER, 64), new ItemStackTemplate(JItems.FLAMING_BOW, 1));

        registerTrade(context, CONVICT_CHARRED_BLADE, new TradeCost(JItems.BOILING_SKULL, 10), new TradeCost(JItems.BOIL_POWDER, 64), new ItemStackTemplate(JItems.CHARRED_BLADE, 16));
        registerTrade(context, CONVICT_CHARRED_BOW, new TradeCost(JItems.BOILING_SKULL, 10), new TradeCost(JItems.BOIL_POWDER, 64), new ItemStackTemplate(JItems.CHARRED_BOW, 1));
        registerTrade(context, CONVICT_BLOODWIELD_SWORD, new TradeCost(JItems.BOILING_SKULL, 10), new TradeCost(JItems.BOIL_POWDER, 64), new ItemStackTemplate(JItems.BLOODWIELD_SWORD, 1));

        registerTrade(context, STARLIGHT_BLACKSMITH_GOLEM_SWORD, new TradeCost(JItems.FLUFFY_FEATHER, 16), new TradeCost(JItems.GOLEM_CHUNK, 8), new ItemStackTemplate(JItems.GOLEM_SWORD, 1));
        registerTrade(context, STARLIGHT_BLACKSMITH_GOLEM_BOW, new TradeCost(JItems.FLUFFY_FEATHER, 16), new TradeCost(JItems.GOLEM_CHUNK, 8), new ItemStackTemplate(JItems.GOLEM_BOW, 1));
        registerTrade(context, STARLIGHT_BLACKSMITH_STARLIGHT_BLADE, new TradeCost(JItems.LUNITE_CHUNK, 16), new TradeCost(JItems.GOLEM_CHUNK, 8), new ItemStackTemplate(JItems.STARLIGHT_BLADE, 1));
        registerTrade(context, STARLIGHT_BLACKSMITH_STARLIGHT_BOW, new TradeCost(JItems.LUNITE_CHUNK, 16), new TradeCost(JItems.GOLEM_CHUNK, 8), new ItemStackTemplate(JItems.STARLIGHT_BOW, 1));

        registerTrade(context, STARLIGHT_VILLAGER_CLOUDIA_ORB, new TradeCost(JItems.FLUFFY_FEATHER, 32), new TradeCost(JItems.GOLEM_CHUNK, 16), new ItemStackTemplate(JItems.CLOUDIA_ORB, 1));
        registerTrade(context, STARLIGHT_VILLAGER_LUNITE_CHUNK, new TradeCost(JItems.FLUFFY_FEATHER, 64), new TradeCost(JItems.GOLEM_CHUNK, 32), new ItemStackTemplate(JItems.LUNITE_CHUNK, 8));
        registerTrade(context, STARLIGHT_VILLAGER_MYSTERIOUS_DISK, new TradeCost(JItems.CLOUDIA_ORB, 8), new TradeCost(JItems.LUNITE_CHUNK, 16), new ItemStackTemplate(JItems.MYSTERIOUS_DISK, 1));

        registerTrade(context, GREEN_TORDO_HEALERS_BLADE, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.OVER_SEEING_EYE, 16), new ItemStackTemplate(JItems.HEALERS_BLADE, 1));
        registerTrade(context, GREEN_TORDO_TREE_HUGGER, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.TREE_HUGGER, 1));

        registerTrade(context, HOODED, new TradeCost(JItems.COLLECTOR_ROCK, 64), new TradeCost(JItems.OVER_SEEING_EYE, 16), new ItemStackTemplate(JItems.ELDER_KEY, 1));

        registerTrade(context, RED_TORDO_TOMATO_SEEDS, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.TOMATO_SEEDS, 16));
        registerTrade(context, RED_TORDO_CORVEGGIES, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.CORVEGGIES, 16));
        registerTrade(context, RED_TORDO_CRACKENCANE_SEEDS, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.CRACKENCANE_SEEDS, 16));
        registerTrade(context, RED_TORDO_CRAKEBULB_SEEDS, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.CRAKEBULB_SEEDS, 16));
        registerTrade(context, RED_TORDO_SPINEBERRIES, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.SPINEBERRIES, 16));
        registerTrade(context, RED_TORDO_GLOWA_SEEDS, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.GLOWA_SEEDS, 16));
        registerTrade(context, RED_TORDO_ZATPEDAL_SEEDS, new TradeCost(JItems.NATURE_TABLET, 16), new TradeCost(JItems.COLLECTOR_ROCK, 16), new ItemStackTemplate(JItems.ZATPEDAL_SEEDS, 16));

        registerTrade(context, OVERGROWN_MERCHANT_VINESTRAND_BLADE, new TradeCost(JItems.ENCHANTED_LEAF, 64), new TradeCost(JItems.ORBADITE_INGOT, 16), new ItemStackTemplate(JItems.VINESTRAND_BLADE, 1));
        registerTrade(context, OVERGROWN_MERCHANT_DARK_PINE_SWORD, new TradeCost(JItems.ENCHANTED_LEAF, 64), new TradeCost(JItems.GORBITE_GEM, 16), new ItemStackTemplate(JItems.DARK_PINE_SWORD, 1));
        registerTrade(context, OVERGROWN_MERCHANT_OVERGROWN_STAFF, new TradeCost(JItems.ENCHANTED_LEAF, 64), new TradeCost(JItems.ORBADITE_INGOT, 16), new ItemStackTemplate(JItems.OVERGROWN_STAFF, 1));
        registerTrade(context, OVERGROWN_MERCHANT_OVERGROWN_HAMMER, new TradeCost(JItems.ENCHANTED_LEAF, 64), new TradeCost(JItems.GORBITE_GEM, 16), new ItemStackTemplate(JItems.OVERGROWN_HAMMER, 1));

        registerTrade(context, AURON_DEPTHS_SLAYER, new TradeCost(JItems.DEPTHS_FLAKE, 16), new TradeCost(JItems.DARK_CRYSTAL, 16), new ItemStackTemplate(JItems.DEPTHS_SLAYER, 1));
        registerTrade(context, AURON_DEPTHS_BOW, new TradeCost(JItems.DEPTHS_FLAKE, 16), new TradeCost(JItems.BEASTLY_STOMACH, 16), new ItemStackTemplate(JItems.DEPTHS_BOW, 1));
        registerTrade(context, AURON_DEPTHS_DARKSWORD, new TradeCost(JItems.DEPTHS_FLAKE, 16), new TradeCost(JItems.DEPTHS_SLAYER, 1), new ItemStackTemplate(JItems.DEPTHS_DARKSWORD, 1));
        registerTrade(context, AURON_DARK_ENFORCER, new TradeCost(JItems.DEPTHS_FLAKE, 16), new TradeCost(JItems.DEPTHS_BOW, 1), new ItemStackTemplate(JItems.DARK_ENFORCER, 1));

        registerTrade(context, STARING_GUARDIAN_DARK_KEY, new TradeCost(JItems.DEPTHS_FLAKE, 16), new TradeCost(JItems.DARK_CRYSTAL, 16), new ItemStackTemplate(JItems.DARK_KEY, 1));

        registerTrade(context, TERRANIAN_ENCHANTER_TERROLICA_SWORD, new TradeCost(JItems.SLUG_SLIME, 64), new TradeCost(JItems.EARTHEN_CRYSTAL, 8), new ItemStackTemplate(JItems.TERROLICA_SWORD, 1));
        registerTrade(context, TERRANIAN_ENCHANTER_TERRALIGHT_BLADE, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.EARTHEN_CRYSTAL, 8), new ItemStackTemplate(JItems.TERRALIGHT_BLADE, 1));
        registerTrade(context, TERRANIAN_ENCHANTER_ANCIENT_PIECE, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.BILE_VIAL, 8), new ItemStackTemplate(JItems.ANCIENT_PIECE, 1));
        registerTrade(context, TERRANIAN_ENCHANTER_ANCIENT_FRAGMENT, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.YELLOW_GEM, 16), new ItemStackTemplate(JItems.ANCIENT_FRAGMENT, 1));

        registerTrade(context, TERRANIAN_TRADER_HEALERS_BLADE, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.DARK_TERRANIAN_SOIL, 16), new ItemStackTemplate(JItems.HEALERS_BLADE, 1));
        registerTrade(context, TERRANIAN_TRADER_TREE_HUGGER, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.LIGHT_TERRANIAN_SOIL, 16), new ItemStackTemplate(JItems.TREE_HUGGER, 1));
        registerTrade(context, TERRANIAN_TRADER_ANCIENT_CHUNK, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.GREEN_GEM, 4), new ItemStackTemplate(JItems.ANCIENT_CHUNK, 1));
        registerTrade(context, TERRANIAN_TRADER_ANCIENT_SHARD, new TradeCost(JItems.PURPLE_POWDER, 16), new TradeCost(JItems.PURPLE_GEM, 4), new ItemStackTemplate(JItems.ANCIENT_SHARD, 1));

        registerTrade(context, ROCKITE_GOLEM_BLAZE_POWDER, new TradeCost(JItems.REINFORCED_CRYSTAL_INGOT, 1), new ItemStackTemplate(Items.BLAZE_POWDER, 8));
        registerTrade(context, ROCKITE_GOLEM_CRYSTALLIZED_BATTLE_AXE, new TradeCost(JItems.REINFORCED_STONE_INGOT, 16), new TradeCost(JItems.BACK_BITER, 1), new ItemStackTemplate(JItems.CRYSTALLIZED_BATTLE_AXE, 1));
        registerTrade(context, ROCKITE_GOLEM_CRYSTALLIZED_HAMMER, new TradeCost(JItems.REINFORCED_CRYSTAL_INGOT, 16), new TradeCost(JItems.EARTHEN_HAMMER, 1), new ItemStackTemplate(JItems.CRYSTALLIZED_HAMMER, 1));
        registerTrade(context, ROCKITE_GOLEM_ROCKY_HAMMER, new TradeCost(JItems.REINFORCED_STONE_INGOT, 16), new TradeCost(JItems.EARTHEN_HAMMER, 1), new ItemStackTemplate(JItems.ROCKY_HAMMER, 1));
        registerTrade(context, ROCKITE_GOLEM_ROCK_LAUNCHER, new TradeCost(JItems.REINFORCED_STONE_INGOT, 16), new TradeCost(JItems.STONE_CLUMP, 4), new ItemStackTemplate(JItems.ROCK_LAUNCHER, 1));
        registerTrade(context, ROCKITE_GOLEM_STAFF_OF_DIVINITY, new TradeCost(JItems.REINFORCED_CRYSTAL_INGOT, 16), new TradeCost(JItems.STAFF_OF_ENLIGHTENMENT, 1), new ItemStackTemplate(JItems.STAFF_OF_DIVINITY, 1));
        registerTrade(context, ROCKITE_GOLEM_CRYSTAL_STAFF, new TradeCost(JItems.REINFORCED_CRYSTAL_INGOT, 16), new TradeCost(JItems.STAFF_OF_DIVINITY, 1), new ItemStackTemplate(JItems.CRYSTAL_STAFF, 1));
        registerTrade(context, ROCKITE_GOLEM_ROCKY_BATTLE_AXE, new TradeCost(JItems.REINFORCED_STONE_INGOT, 16), new TradeCost(JItems.BRONZED_BATTLE_AXE, 1), new ItemStackTemplate(JItems.ROCKY_BATTLE_AXE, 1));

        registerTrade(context, ESKIMO_FROSTBORN_SOUL, new TradeCost(JItems.CRYSTAL_FLAKE, 16), new TradeCost(JItems.SOULSTONE, 16), new ItemStackTemplate(JItems.FROSTBORN_SOUL, 1));
        registerTrade(context, ESKIMO_FROZEN_ICE_BALL, new TradeCost(JItems.FROST_FLAKE, 4), new ItemStackTemplate(JItems.FROZEN_ICE_BALL, 1));
        registerTrade(context, ESKIMO_FROSTY_SWORD, new TradeCost(JItems.CRYSTAL_FLAKE, 16), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.FROSTY_SWORD, 1));
        registerTrade(context, ESKIMO_FROSTY_BOW, new TradeCost(JItems.CRYSTAL_FLAKE, 16), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.FROSTY_BOW, 1));
        registerTrade(context, ESKIMO_FROSTY_PIERCER, new TradeCost(JItems.CRYSTAL_FLAKE, 16), new TradeCost(JItems.FROST_GEM, 10), new ItemStackTemplate(JItems.FROSTY_PIERCER, 16));
        registerTrade(context, ESKIMO_FROSTBITTEN_SWORD, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.FROSTY_SWORD, 1), new ItemStackTemplate(JItems.FROSTBITTEN_SWORD, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_BOW, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.FROSTY_BOW, 1), new ItemStackTemplate(JItems.FROSTBITTEN_BOW, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_PIERCER, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.FROSTY_PIERCER, 16), new ItemStackTemplate(JItems.FROSTBITTEN_PIERCER, 16));
        registerTrade(context, ESKIMO_CRYSTAL_FLAKE_HELMET, new TradeCost(JItems.CRYSTAL_FLAKE, 4), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.CRYSTAL_FLAKE_HELMET, 1));
        registerTrade(context, ESKIMO_CRYSTAL_FLAKE_CHEST, new TradeCost(JItems.CRYSTAL_FLAKE, 4), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.CRYSTAL_FLAKE_CHEST, 1));
        registerTrade(context, ESKIMO_CRYSTAL_FLAKE_LEGS, new TradeCost(JItems.CRYSTAL_FLAKE, 4), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.CRYSTAL_FLAKE_LEGS, 1));
        registerTrade(context, ESKIMO_CRYSTAL_FLAKE_BOOTS, new TradeCost(JItems.CRYSTAL_FLAKE, 4), new TradeCost(JItems.FROST_GEM, 16), new ItemStackTemplate(JItems.CRYSTAL_FLAKE_BOOTS, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_HELMET, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.CRYSTAL_FLAKE_HELMET, 1), new ItemStackTemplate(JItems.FROSTBITTEN_HELMET, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_CHEST, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.CRYSTAL_FLAKE_CHEST, 1), new ItemStackTemplate(JItems.FROSTBITTEN_CHEST, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_LEGS, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.CRYSTAL_FLAKE_LEGS, 1), new ItemStackTemplate(JItems.FROSTBITTEN_LEGS, 1));
        registerTrade(context, ESKIMO_FROSTBITTEN_BOOTS, new TradeCost(JItems.FROST_FLAKE, 16), new TradeCost(JItems.CRYSTAL_FLAKE_BOOTS, 1), new ItemStackTemplate(JItems.FROSTBITTEN_BOOTS, 1));

        registerTrade(context, GUNSMITH_NETHER_PLASMA, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(Items.BLAZE_ROD, 16), new ItemStackTemplate(JItems.NETHER_PLASMA, 1));
        registerTrade(context, GUNSMITH_OCEAN_PLASMA, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(JItems.BLUE_GEM, 16), new ItemStackTemplate(JItems.OCEAN_PLASMA, 1));
        registerTrade(context, GUNSMITH_FOREST_PLASMA, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(JItems.GREEN_GEM, 16), new ItemStackTemplate(JItems.FOREST_PLASMA, 1));
        registerTrade(context, GUNSMITH_ROCK_LAUNCHER, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(JItems.STONE_CLUMP, 16), new ItemStackTemplate(JItems.ROCK_LAUNCHER, 1));
        registerTrade(context, GUNSMITH_CHAOS_CANNON, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(JItems.YELLOW_GEM, 16), new ItemStackTemplate(JItems.CHAOS_CANNON, 1));
        registerTrade(context, GUNSMITH_EYE_BLASTER, new TradeCost(JItems.GUN_BASE, 1), new TradeCost(Items.ENDER_EYE, 8), new ItemStackTemplate(JItems.EYE_BLASTER, 1));
        registerTrade(context, GUNSMITH_FLAMING_HAMMER, new TradeCost(JItems.ROCKY_HAMMER, 1), new TradeCost(JItems.BLOODCRUST_INGOT, 8), new ItemStackTemplate(JItems.FLAMING_HAMMER, 1));
        registerTrade(context, GUNSMITH_NETHIC_HAMMER, new TradeCost(JItems.ROCKY_HAMMER, 1), new TradeCost(Items.BLAZE_ROD, 8), new ItemStackTemplate(JItems.NETHIC_HAMMER, 1));
        registerTrade(context, GUNSMITH_WITHIC_HAMMER, new TradeCost(JItems.NETHIC_HAMMER, 1), new TradeCost(Items.WITHER_SKELETON_SKULL, 1), new ItemStackTemplate(JItems.WITHIC_HAMMER, 1));

        registerTrade(context, BLACKSMITH_DAWN_BREAKER, new TradeCost(Items.STICK, 10), new TradeCost(JItems.PURPLE_GEM, 10), new ItemStackTemplate(JItems.DAWN_BREAKER, 1));
        registerTrade(context, BLACKSMITH_TEMPEST_BATTLE_AXE, new TradeCost(Items.STICK, 10), new TradeCost(JItems.PURPLE_GEM, 10), new ItemStackTemplate(JItems.TEMPEST_BATTLE_AXE, 1));
        registerTrade(context, BLACKSMITH_DRAGONS_TOOTH, new TradeCost(Items.STICK, 10), new TradeCost(JItems.GREEN_GEM, 10), new ItemStackTemplate(JItems.DRAGONS_TOOTH, 1));
        registerTrade(context, BLACKSMITH_POISON_SWORD, new TradeCost(Items.STICK, 10), new TradeCost(JItems.GREEN_GEM, 10), new ItemStackTemplate(JItems.POISON_SWORD, 1));
        registerTrade(context, BLACKSMITH_CLOUD_SLICER, new TradeCost(Items.STICK, 10), new TradeCost(JItems.BLUE_GEM, 10), new ItemStackTemplate(JItems.CLOUD_SLICER, 1));
        registerTrade(context, BLACKSMITH_BACK_BITER, new TradeCost(Items.STICK, 10), new TradeCost(JItems.YELLOW_GEM, 10), new ItemStackTemplate(JItems.BACK_BITER, 1));
        registerTrade(context, BLACKSMITH_SUNSET_PIERCER, new TradeCost(JItems.BLUE_GEM, 10), new TradeCost(JItems.YELLOW_GEM, 10), new ItemStackTemplate(JItems.SUNSET_PIERCER, 1));
        registerTrade(context, BLACKSMITH_AQUATIC_KNIFE, new TradeCost(JItems.BLUE_GEM, 10), new TradeCost(JItems.YELLOW_GEM, 10), new ItemStackTemplate(JItems.AQUATIC_KNIFE, 1));
        registerTrade(context, BLACKSMITH_POISON_BOW, new TradeCost(JItems.PURPLE_GEM, 10), new TradeCost(JItems.GREEN_GEM, 10), new ItemStackTemplate(JItems.POISON_BOW, 1));
        registerTrade(context, BLACKSMITH_DARKNESS_BOW, new TradeCost(JItems.PURPLE_GEM, 10), new TradeCost(JItems.YELLOW_GEM, 10), new ItemStackTemplate(JItems.DARKNESS_BOW, 1));
        registerTrade(context, BLACKSMITH_FROZEN_BOW, new TradeCost(JItems.PURPLE_GEM, 10), new TradeCost(JItems.BLUE_GEM, 10), new ItemStackTemplate(JItems.FROZEN_BOW, 1));

        registerTrade(context, MAGE_LOOT_POUCH, new TradeCost(JItems.SAPPHIRE, 16), new ItemStackTemplate(JItems.LOOT_POUCH, 1));
        registerTrade(context, MAGE_SAPPHIRE, new TradeCost(JItems.LUNIUM_POWDER, 8), new ItemStackTemplate(JItems.SAPPHIRE, 1));
        registerTrade(context, MAGE_PET_ROBOT_SPAWNER, new TradeCost(JItems.CRIMSON_QUARTZ, 32), new TradeCost(JItems.MEKYUM_INGOT, 16), new ItemStackTemplate(JItems.PET_ROBOT_SPAWNER, 1));
        registerTrade(context, MAGE_FERMENTED_SPIDER_EYE, new TradeCost(JItems.SAPPHIRE, 16), new ItemStackTemplate(Items.FERMENTED_SPIDER_EYE, 2));
        registerTrade(context, MAGE_GUNPOWDER, new TradeCost(JItems.SAPPHIRE, 10), new ItemStackTemplate(Items.GUNPOWDER, 4));
        registerTrade(context, MAGE_REDSTONE, new TradeCost(JItems.SAPPHIRE, 4), new ItemStackTemplate(Items.REDSTONE, 8));
        registerTrade(context, MAGE_PHANTOM_MEMBRANE, new TradeCost(JItems.SAPPHIRE, 16), new ItemStackTemplate(Items.PHANTOM_MEMBRANE, 2));
        registerTrade(context, MAGE_GHAST_TEAR, new TradeCost(JItems.SAPPHIRE, 16), new ItemStackTemplate(Items.GHAST_TEAR, 4));
        registerTrade(context, MAGE_MAGMA_CREAM, new TradeCost(JItems.SAPPHIRE, 16), new ItemStackTemplate(Items.MAGMA_CREAM, 8));
        registerTrade(context, MAGE_GLOWSTONE_DUST, new TradeCost(JItems.SAPPHIRE, 8), new ItemStackTemplate(Items.GLOWSTONE_DUST, 4));
        registerTrade(context, MAGE_BLAZE_POWDER, new TradeCost(JItems.SAPPHIRE, 32), new ItemStackTemplate(Items.BLAZE_POWDER, 8));
        registerTrade(context, MAGE_EARTHEN_HAMMER, new TradeCost(JItems.STONE_STICK, 2), new TradeCost(JItems.GREEN_GEM, 10), new ItemStackTemplate(JItems.EARTHEN_HAMMER, 1));
        registerTrade(context, MAGE_SPELLBINDING_HAMMER, new TradeCost(JItems.EARTHEN_HAMMER, 1), new TradeCost(JItems.PURPLE_GEM, 16), new ItemStackTemplate(JItems.SPELLBINDING_HAMMER, 1));
        registerTrade(context, MAGE_STAFF_OF_GREENPACE, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.GREEN_GEM, 10), new ItemStackTemplate(JItems.STAFF_OF_GREENPACE, 1));
        registerTrade(context, MAGE_STAFF_OF_HELLSTONE, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.BLOODCRUST_INGOT, 10), new ItemStackTemplate(JItems.STAFF_OF_HELLSTONE, 1));
        registerTrade(context, MAGE_DOOMSBRINGER, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.PURPLE_GEM, 10), new ItemStackTemplate(JItems.DOOMSBRINGER, 1));
        registerTrade(context, MAGE_STAFF_OF_ESSENCIA, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.BOTTLE_OF_ESSENCIA, 8), new ItemStackTemplate(JItems.STAFF_OF_ESSENCIA, 1));
        registerTrade(context, MAGE_WIZARDS_STAR, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.YELLOW_GEM, 10), new ItemStackTemplate(JItems.WIZARDS_STAR, 1));
        registerTrade(context, MAGE_STAFF_OF_ENLIGHTENMENT, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.BLUE_GEM, 10), new ItemStackTemplate(JItems.STAFF_OF_ENLIGHTENMENT, 1));
        registerTrade(context, MAGE_STAFF_OF_CONJURING, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(JItems.STAFF_OF_GREENPACE, 1), new ItemStackTemplate(JItems.STAFF_OF_CONJURING, 1));
        registerTrade(context, MAGE_TELEPORTATION_STAFF, new TradeCost(JItems.STAFF_BASE, 1), new TradeCost(Items.ENDER_PEARL, 64), new ItemStackTemplate(JItems.TELEPORTATION_STAFF, 1));

        return context.register(WANDERING_TRADER_EMERALD_NAME_TAG, new VillagerTrade(new TradeCost(Items.EMERALD, 1), new ItemStackTemplate(Items.NAME_TAG), 5, 1, 0.05F, Optional.empty(), List.of()));
    }

    public static void registerTrade(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> key, TradeCost want1, ItemStackTemplate give) {
        context.register(key, new VillagerTrade(want1, give, 12, 5, 0.05F, Optional.empty(), List.of()));
    }

    public static void registerTrade(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> key, TradeCost want1, TradeCost want2, ItemStackTemplate give) {
        context.register(key, new VillagerTrade(want1, Optional.of(want2), give, 12, 5, 0.05F, Optional.empty(), List.of()));
    }

    public static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(JITL.MOD_ID, path));
    }
}
