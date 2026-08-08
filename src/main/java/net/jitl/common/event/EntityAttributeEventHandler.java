package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(modid = JITL.MOD_ID)
public class EntityAttributeEventHandler {

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityTypes.PLAYER, JAttributes.MAX_ESSENCE);
        event.add(EntityTypes.PLAYER, JAttributes.ESSENCE_REGEN_SPEED);
        event.add(EntityTypes.PLAYER, JAttributes.ESSENCE_BURNOUT);
    }
}
