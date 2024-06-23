package net.jitl.common.entity;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EntityUtil {

    public static Direction getDirectionFacing(Entity entity, boolean lateralOnly) {
        if (!lateralOnly) {
            if (entity.getXRot() < -50)
                return Direction.DOWN;

            if (entity.getXRot() > 50)
                return Direction.UP;
        }

        int vec = Mth.floor(entity.getYRot() * 4 / 360 + 0.5) & 0x3;

        return switch(++vec % 4) {
            case 0 -> Direction.EAST;
            case 1 -> Direction.SOUTH;
            case 2 -> Direction.WEST;
            default -> Direction.NORTH;
        };
    }

}