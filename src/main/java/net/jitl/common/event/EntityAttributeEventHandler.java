package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeEventHandler {

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, JAttributes.MAX_ESSENCE.get());
        event.add(EntityType.PLAYER, JAttributes.ESSENCE_REGEN_SPEED.get());
        event.add(EntityType.PLAYER, JAttributes.ESSENCE_BURNOUT.get());
    }
}
