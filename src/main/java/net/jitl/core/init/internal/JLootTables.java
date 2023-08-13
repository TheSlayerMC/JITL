package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;

public class JLootTables {

    public static ResourceLocation TOWER_GUARDIAN_CRYSTAL = addBoosCrystalLootTable("temple_guardian");
    public static ResourceLocation ROCKITE_SMASHER_CRYSTAL = addBoosCrystalLootTable("rockite_smasher");
    public static ResourceLocation OKOLOO_CRYSTAL = addBoosCrystalLootTable("okoloo");

    public static ResourceLocation addLootTable(String name) {
        return JITL.rl(name);
    }

    public static ResourceLocation addBoosCrystalLootTable(String name) {
        return JITL.rl("boss_crystal/" + name);
    }

}