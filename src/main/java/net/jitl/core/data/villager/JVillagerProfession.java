package net.jitl.core.data.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.jitl.core.init.JITL;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JVillagerProfession {

    public static final DeferredRegister<VillagerProfession> REGISTRY = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, JITL.MOD_ID);

    public static final Holder<VillagerProfession> CRYPIAN = register("crypian", JTradeSets.CRYPIAN);
    public static final Holder<VillagerProfession> BOIL_TRADER = register("boil_trader", JTradeSets.BOIL_TRADER);
    public static final Holder<VillagerProfession> CONVICT = register("convict", JTradeSets.CONVICT);
    public static final Holder<VillagerProfession> STARLIGHT_BLACKSMITH = register("starlight_blacksmith", JTradeSets.STARLIGHT_BLACKSMITH);
    public static final Holder<VillagerProfession> STARLIGHT_VILLAGER = register("starlight_villager", JTradeSets.STARLIGHT_VILLAGER);
    public static final Holder<VillagerProfession> GREEN_TORDO = register("green_tordo", JTradeSets.GREEN_TORDO);
    public static final Holder<VillagerProfession> HOODED = register("hooded", JTradeSets.HOODED);
    public static final Holder<VillagerProfession> RED_TORDO = register("red_tordo", JTradeSets.RED_TORDO);
    public static final Holder<VillagerProfession> OVERGROWN_MERCHANT = register("overgrown_merchant", JTradeSets.OVERGROWN_MERCHANT);
    public static final Holder<VillagerProfession> AURON = register("auron", JTradeSets.AURON);
    public static final Holder<VillagerProfession> STARING = register("staring_guardian", JTradeSets.STARING);
    public static final Holder<VillagerProfession> ALLOY_MENDER = register("alloy_mender", JTradeSets.ALLOY_MENDER);
    public static final Holder<VillagerProfession> TERRANIAN_ENCHANTER = register("terranian_enchanter", JTradeSets.TERRANIAN_ENCHANTER);
    public static final Holder<VillagerProfession> TERRANIAN_TRADER = register("terranian_trader", JTradeSets.TERRANIAN_TRADER);
    public static final Holder<VillagerProfession> ROCKITE_GOLEM = register("rockite", JTradeSets.ROCKITE_GOLEM);
    public static final Holder<VillagerProfession> ESKIMO = register("eskimo", JTradeSets.ESKIMO);
    public static final Holder<VillagerProfession> GUNSMITH = register("gunsmith", JTradeSets.GUNSMITH);
    public static final Holder<VillagerProfession> BLACKSMITH = register("blacksmithr", JTradeSets.BLACKSMITH);
    public static final Holder<VillagerProfession> MAGE = register("mage", JTradeSets.MAGE);

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }

    public static Holder<VillagerProfession> register(String name, ResourceKey<TradeSet> trades) {
        return REGISTRY.register(name, () -> new VillagerProfession(Component.translatable("entity." + name), holder -> holder == PoiTypes.HOME, holder -> holder == PoiTypes.WEAPONSMITH, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER, Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, trades))));
    }
}
