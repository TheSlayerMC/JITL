package net.jitl.client.gui;

import net.jitl.core.init.JITL;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.language.I18n;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
@EventBusSubscriber(modid = JITL.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindEvents {

    public static KeyMapping keyStats = new KeyMapping("Open Journey Stats", GLFW.GLFW_KEY_J, I18n.get("jitl.key"));
    public static KeyMapping keyArmor = new KeyMapping("Use Armor Ability", GLFW.GLFW_KEY_C, I18n.get("jitl.key"));
    public static KeyMapping keyAmulet = new KeyMapping("Use Amulet Ability", GLFW.GLFW_KEY_V, I18n.get("jitl.key"));

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(keyStats);
        event.register(keyArmor);
        event.register(keyAmulet);
    }
}