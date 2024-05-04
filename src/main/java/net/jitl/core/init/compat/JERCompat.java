package net.jitl.core.init.compat;

import net.jitl.core.init.JITL;
import net.minecraft.resources.ResourceLocation;

public class JERCompat {

    public static void init() {
//        IJERAPI api = JERAPI.getInstance();
//
//        IMobRegistry mobRegistry = api.getMobRegistry();
//        if(mobRegistry != null) {
//            //mobRegistry.register(Floro.class, getMobLootTable("floro"));
//        }
    }

    public static ResourceLocation getMobLootTable(String name){
        return JITL.rl("entities/" + name);
    }

    public static ResourceLocation getChestLootTable(String name){
        return JITL.rl("chests/" + name);
    }
}