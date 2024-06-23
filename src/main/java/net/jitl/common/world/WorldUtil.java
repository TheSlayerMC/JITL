package net.jitl.common.world;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class WorldUtil {

    @SafeVarargs
    public static boolean isWorld(ServerLevelAccessor world, ResourceKey<Level>... keys) {
        for(ResourceKey<Level> key : keys) {
            if(world.getLevel().dimension() == key)
                return true;
        }
        return false;
    }

    @SafeVarargs
    public static boolean isWorld(Level world, ResourceKey<Level>... keys) {
        for(ResourceKey<Level> key : keys) {
            if(world.dimension() == key)
                return true;
        }
        return false;
    }

}