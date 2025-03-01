package net.jitl.client.ability;

import net.jitl.core.init.network.PacketUpdateClientPlayerMovement;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.OptionalDouble;

public class ClientPlayerMovement {

    public static void adjustPlayerMovement(OptionalDouble x, OptionalDouble y, OptionalDouble z, PacketUpdateClientPlayerMovement.Operation operation) {
        Player player = Minecraft.getInstance().player;
        assert player != null;
        Vec3 velocity = player.getDeltaMovement();
        switch (operation) {
            case SET -> player.setDeltaMovement(x.orElseGet(velocity::x), y.orElseGet(velocity::y), z.orElseGet(velocity::z));
            case ADD -> player.setDeltaMovement(velocity.add(x.orElse(0), y.orElse(0), z.orElse(0)));
            case MULTIPLY -> player.setDeltaMovement(velocity.multiply(x.orElse(1), y.orElse(1), z.orElse(1)));
            case MAX -> player.setDeltaMovement(Math.min(x.orElseGet(velocity::x), velocity.x), Math.min(y.orElseGet(velocity::y), velocity.y), Math.min(z.orElseGet(velocity::z), velocity.z));
            case MIN -> player.setDeltaMovement(Math.max(x.orElseGet(velocity::x), velocity.x), Math.max(y.orElseGet(velocity::y), velocity.y), Math.max(z.orElseGet(velocity::z), velocity.z));
        }
    }
}
