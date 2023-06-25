package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;

public class JLootTables {

    public static ResourceLocation TOWER_GUARDIAN_CRYSTAL = addLootTable("entities/boss/temple_guardian");
    public static ResourceLocation ROCKITE_SMASHER_CRYSTAL = addLootTable("entities/boss/rockite_smasher");

    public static ResourceLocation addLootTable(String name) {
        return JITL.rl(name);
    }
}