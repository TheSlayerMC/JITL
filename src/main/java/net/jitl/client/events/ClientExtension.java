package net.jitl.client.events;

import net.jitl.common.items.base.JShieldItem;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientExtension {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(JShieldItem.RenderProps.INSTANCE,
                JItems.SAPPHIRE_SHIELD.get(),
                JItems.LUNIUM_SHIELD.get(),
                JItems.SHADIUM_SHIELD.get(),
                JItems.BLOODCRUST_SHIELD.get(),
                JItems.CELESTIUM_SHIELD.get(),
                JItems.MEKYUM_SHIELD.get(),
                JItems.GORBITE_SHIELD.get(),
                JItems.STORON_SHIELD.get(),
                JItems.KORITE_SHIELD.get(),
                JItems.FLAIRIUM_SHIELD.get(),
                JItems.DES_SHIELD.get(),
                JItems.ORBADITE_SHIELD.get(),
                JItems.SOULSTONE_SHIELD.get()
        );
    }
}
