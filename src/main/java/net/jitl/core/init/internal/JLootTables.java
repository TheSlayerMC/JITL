package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;

public class JLootTables {

    public static ResourceLocation TOWER_GUARDIAN_CRYSTAL = addBossCrystalLootTable("temple_guardian");
    public static ResourceLocation ROCKITE_SMASHER_CRYSTAL = addBossCrystalLootTable("rockite_smasher");
    public static ResourceLocation OKOLOO_CRYSTAL = addBossCrystalLootTable("okoloo");
    public static ResourceLocation BLAZIER_CRYSTAL = addBossCrystalLootTable("blazier");
    public static ResourceLocation CALCIA_CRYSTAL = addBossCrystalLootTable("calcia");
    public static ResourceLocation SOUL_WATCHER_CRYSTAL = addBossCrystalLootTable("soul_watcher");
    public static ResourceLocation WITHERING_BEAST_CRYSTAL = addBossCrystalLootTable("withering_beast");
    public static ResourceLocation EUDOR_CRYSTAL = addBossCrystalLootTable("eudor");
    public static ResourceLocation CORALLATOR_CRYSTAL = addBossCrystalLootTable("corallator");
    public static ResourceLocation THUNDER_BIRD_CRYSTAL = addBossCrystalLootTable("thunder_bird");

    public static ResourceLocation FROZEN_TROLL_TRADES = addBossCrystalLootTable("temple_guardian");

    public static ResourceLocation addLootTable(String name) {
        return JITL.rl(name);
    }

    public static ResourceLocation addBossCrystalLootTable(String name) {
        return JITL.rl("boss_crystal/" + name);
    }

}