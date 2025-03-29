package net.jitl.core.init.compat;

import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModCompat {

    public static void init(FMLCommonSetupEvent event) {
        if(ModList.get().isLoaded("jeresources")) {
            //todo JERCompat.init();
        }
    }
}
