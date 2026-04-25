package net.jitl.core.data.villager;

import net.jitl.core.init.JITL;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class JVillagerTradeTagsProvider extends VillagerTradesTagsProvider {

    public static final TagKey<VillagerTrade> CRYPIAN = create("euca/crypian");

    public static final TagKey<VillagerTrade> BOIL_TRADER = create("boil/boil_trader");
    public static final TagKey<VillagerTrade> CONVICT = create("boil/convict");
    public static final TagKey<VillagerTrade> STARLIGHT_BLACKSMITH = create("cloudia/starlight_blacksmith");
    public static final TagKey<VillagerTrade> STARLIGHT_VILLAGER = create("cloudia/starlight_villager");
    public static final TagKey<VillagerTrade> GREEN_TORDO = create("corba/green_tordo");
    public static final TagKey<VillagerTrade> HOODED = create("corba/hooded");
    public static final TagKey<VillagerTrade> RED_TORDO = create("corba/red_tordo");
    public static final TagKey<VillagerTrade> OVERGROWN_MERCHANT = create("corba/overgrown_merchant");
    public static final TagKey<VillagerTrade> AURON = create("depths/auron_depths");
    public static final TagKey<VillagerTrade> STARING = create("depths/staring_guardian");
    public static final TagKey<VillagerTrade> ALLOY_MENDER = create("euca/alloy_mender");
    public static final TagKey<VillagerTrade> TERRANIAN_ENCHANTER = create("terrania/terranian_enchanter");
    public static final TagKey<VillagerTrade> TERRANIAN_TRADER = create("terrania/terranian_trader");
    public static final TagKey<VillagerTrade> ROCKITE_GOLEM = create("overworld/rockite");
    public static final TagKey<VillagerTrade> ESKIMO = create("frozen/eskimo");
    public static final TagKey<VillagerTrade> GUNSMITH = create("overworld/gunsmith");
    public static final TagKey<VillagerTrade> BLACKSMITH = create("overworld/blacksmith");
    public static final TagKey<VillagerTrade> MAGE = create("overworld/mage");

    public JVillagerTradeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(CRYPIAN).add(JVillagerTrades.CRYPIAN);
        this.tag(BOIL_TRADER).add(JVillagerTrades.BOIL_TRADER_BOILING_BLADE).add(JVillagerTrades.BOIL_TRADER_FLAMING_BOW).add(JVillagerTrades.BOIL_TRADER_MOLTEN_KNIFE);
        this.tag(CONVICT).add(JVillagerTrades.CONVICT_CHARRED_BLADE).add(JVillagerTrades.CONVICT_CHARRED_BOW).add(JVillagerTrades.CONVICT_BLOODWIELD_SWORD);
        this.tag(STARLIGHT_BLACKSMITH).add(JVillagerTrades.STARLIGHT_BLACKSMITH_GOLEM_SWORD).add(JVillagerTrades.STARLIGHT_BLACKSMITH_GOLEM_BOW).add(JVillagerTrades.STARLIGHT_BLACKSMITH_STARLIGHT_BLADE).add(JVillagerTrades.STARLIGHT_BLACKSMITH_STARLIGHT_BOW);
        this.tag(STARLIGHT_VILLAGER).add(JVillagerTrades.STARLIGHT_VILLAGER_CLOUDIA_ORB).add(JVillagerTrades.STARLIGHT_VILLAGER_LUNITE_CHUNK).add(JVillagerTrades.STARLIGHT_VILLAGER_MYSTERIOUS_DISK);
        this.tag(GREEN_TORDO).add(JVillagerTrades.GREEN_TORDO_HEALERS_BLADE).add(JVillagerTrades.GREEN_TORDO_TREE_HUGGER);
        this.tag(HOODED).add(JVillagerTrades.HOODED);
        this.tag(RED_TORDO).add(JVillagerTrades.RED_TORDO_TOMATO_SEEDS).add(JVillagerTrades.RED_TORDO_CORVEGGIES).add(JVillagerTrades.RED_TORDO_CRACKENCANE_SEEDS).add(JVillagerTrades.RED_TORDO_CRAKEBULB_SEEDS).add(JVillagerTrades.RED_TORDO_SPINEBERRIES).add(JVillagerTrades.RED_TORDO_GLOWA_SEEDS).add(JVillagerTrades.RED_TORDO_ZATPEDAL_SEEDS);
        this.tag(OVERGROWN_MERCHANT).add(JVillagerTrades.OVERGROWN_MERCHANT_VINESTRAND_BLADE).add(JVillagerTrades.OVERGROWN_MERCHANT_DARK_PINE_SWORD).add(JVillagerTrades.OVERGROWN_MERCHANT_OVERGROWN_STAFF).add(JVillagerTrades.OVERGROWN_MERCHANT_OVERGROWN_HAMMER);
        this.tag(AURON).add(JVillagerTrades.AURON_DEPTHS_SLAYER).add(JVillagerTrades.AURON_DEPTHS_BOW).add(JVillagerTrades.AURON_DEPTHS_DARKSWORD).add(JVillagerTrades.AURON_DARK_ENFORCER);
        this.tag(STARING).add(JVillagerTrades.STARING_GUARDIAN_DARK_KEY);
        this.tag(ALLOY_MENDER).add(JVillagerTrades.ALLOY_MENDER_ROYAL_BOW).add(JVillagerTrades.ALLOY_MENDER_ROYAL_KNIFE).add(JVillagerTrades.ALLOY_MENDER_ROYAL_HAMMER).add(JVillagerTrades.ALLOY_MENDER_CELEKIUM_BATTLE_AXE).add(JVillagerTrades.ALLOY_MENDER_CELESTITE_BATTLE_AXE).add(JVillagerTrades.ALLOY_MENDER_STORUM_BATTLE_AXE).add(JVillagerTrades.ALLOY_MENDER_BRONZED_BATTLE_AXE);
        this.tag(TERRANIAN_ENCHANTER).add(JVillagerTrades.TERRANIAN_ENCHANTER_TERROLICA_SWORD).add(JVillagerTrades.TERRANIAN_ENCHANTER_TERRALIGHT_BLADE).add(JVillagerTrades.TERRANIAN_ENCHANTER_ANCIENT_PIECE).add(JVillagerTrades.TERRANIAN_ENCHANTER_ANCIENT_FRAGMENT);
        this.tag(TERRANIAN_TRADER).add(JVillagerTrades.TERRANIAN_TRADER_HEALERS_BLADE).add(JVillagerTrades.TERRANIAN_TRADER_TREE_HUGGER).add(JVillagerTrades.TERRANIAN_TRADER_ANCIENT_CHUNK).add(JVillagerTrades.TERRANIAN_TRADER_ANCIENT_SHARD);
        this.tag(ROCKITE_GOLEM).add(JVillagerTrades.ROCKITE_GOLEM_BLAZE_POWDER).add(JVillagerTrades.ROCKITE_GOLEM_CRYSTALLIZED_BATTLE_AXE).add(JVillagerTrades.ROCKITE_GOLEM_CRYSTALLIZED_HAMMER).add(JVillagerTrades.ROCKITE_GOLEM_ROCKY_HAMMER).add(JVillagerTrades.ROCKITE_GOLEM_ROCK_LAUNCHER).add(JVillagerTrades.ROCKITE_GOLEM_STAFF_OF_DIVINITY).add(JVillagerTrades.ROCKITE_GOLEM_CRYSTAL_STAFF).add(JVillagerTrades.ROCKITE_GOLEM_ROCKY_BATTLE_AXE);
        this.tag(ESKIMO).add(JVillagerTrades.ESKIMO_FROSTBORN_SOUL).add(JVillagerTrades.ESKIMO_FROZEN_ICE_BALL).add(JVillagerTrades.ESKIMO_FROSTY_SWORD).add(JVillagerTrades.ESKIMO_FROSTY_BOW).add(JVillagerTrades.ESKIMO_FROSTY_PIERCER).add(JVillagerTrades.ESKIMO_FROSTBITTEN_SWORD).add(JVillagerTrades.ESKIMO_FROSTBITTEN_BOW).add(JVillagerTrades.ESKIMO_FROSTBITTEN_PIERCER).add(JVillagerTrades.ESKIMO_CRYSTAL_FLAKE_HELMET).add(JVillagerTrades.ESKIMO_CRYSTAL_FLAKE_CHEST).add(JVillagerTrades.ESKIMO_CRYSTAL_FLAKE_LEGS).add(JVillagerTrades.ESKIMO_CRYSTAL_FLAKE_BOOTS).add(JVillagerTrades.ESKIMO_FROSTBITTEN_HELMET).add(JVillagerTrades.ESKIMO_FROSTBITTEN_CHEST).add(JVillagerTrades.ESKIMO_FROSTBITTEN_LEGS).add(JVillagerTrades.ESKIMO_FROSTBITTEN_BOOTS);
        this.tag(GUNSMITH).add(JVillagerTrades.GUNSMITH_NETHER_PLASMA).add(JVillagerTrades.GUNSMITH_OCEAN_PLASMA).add(JVillagerTrades.GUNSMITH_FOREST_PLASMA).add(JVillagerTrades.GUNSMITH_ROCK_LAUNCHER).add(JVillagerTrades.GUNSMITH_CHAOS_CANNON).add(JVillagerTrades.GUNSMITH_EYE_BLASTER).add(JVillagerTrades.GUNSMITH_FLAMING_HAMMER).add(JVillagerTrades.GUNSMITH_NETHIC_HAMMER).add(JVillagerTrades.GUNSMITH_WITHIC_HAMMER);
        this.tag(BLACKSMITH).add(JVillagerTrades.BLACKSMITH_DAWN_BREAKER).add(JVillagerTrades.BLACKSMITH_TEMPEST_BATTLE_AXE).add(JVillagerTrades.BLACKSMITH_DRAGONS_TOOTH).add(JVillagerTrades.BLACKSMITH_POISON_SWORD).add(JVillagerTrades.BLACKSMITH_CLOUD_SLICER).add(JVillagerTrades.BLACKSMITH_BACK_BITER).add(JVillagerTrades.BLACKSMITH_SUNSET_PIERCER).add(JVillagerTrades.BLACKSMITH_AQUATIC_KNIFE).add(JVillagerTrades.BLACKSMITH_POISON_BOW).add(JVillagerTrades.BLACKSMITH_DARKNESS_BOW).add(JVillagerTrades.BLACKSMITH_FROZEN_BOW);
        this.tag(MAGE).add(JVillagerTrades.MAGE_LOOT_POUCH).add(JVillagerTrades.MAGE_SAPPHIRE).add(JVillagerTrades.MAGE_PET_ROBOT_SPAWNER).add(JVillagerTrades.MAGE_FERMENTED_SPIDER_EYE).add(JVillagerTrades.MAGE_GUNPOWDER).add(JVillagerTrades.MAGE_REDSTONE).add(JVillagerTrades.MAGE_PHANTOM_MEMBRANE).add(JVillagerTrades.MAGE_GHAST_TEAR).add(JVillagerTrades.MAGE_MAGMA_CREAM).add(JVillagerTrades.MAGE_GLOWSTONE_DUST).add(JVillagerTrades.MAGE_BLAZE_POWDER).add(JVillagerTrades.MAGE_EARTHEN_HAMMER).add(JVillagerTrades.MAGE_SPELLBINDING_HAMMER).add(JVillagerTrades.MAGE_STAFF_OF_GREENPACE).add(JVillagerTrades.MAGE_STAFF_OF_HELLSTONE).add(JVillagerTrades.MAGE_DOOMSBRINGER).add(JVillagerTrades.MAGE_STAFF_OF_ESSENCIA).add(JVillagerTrades.MAGE_WIZARDS_STAR).add(JVillagerTrades.MAGE_STAFF_OF_ENLIGHTENMENT).add(JVillagerTrades.MAGE_STAFF_OF_CONJURING).add(JVillagerTrades.MAGE_TELEPORTATION_STAFF);
    }

    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(JITL.MOD_ID, name));
    }
}