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

    public static KeyMapping keyIsometricView = new KeyMapping("Toggle Isometric Camera", GLFW.GLFW_KEY_EQUAL, I18n.get("jitl.key"));
    public static KeyMapping keyLockPerspective = new KeyMapping("Lock Isometric Perspective", GLFW.GLFW_KEY_DELETE, I18n.get("jitl.key"));
    public static KeyMapping keyMoveCameraUp = new KeyMapping("Move Isometric Camera Up", GLFW.GLFW_KEY_UP, I18n.get("jitl.key"));
    public static KeyMapping keyMoveCameraDown = new KeyMapping("Move Isometric Camera Down", GLFW.GLFW_KEY_DOWN, I18n.get("jitl.key"));
    public static KeyMapping keyMoveCameraLeft = new KeyMapping("Move Isometric Camera Left", GLFW.GLFW_KEY_LEFT, I18n.get("jitl.key"));
    public static KeyMapping keyMoveCameraRight = new KeyMapping("Move Isometric Camera Right", GLFW.GLFW_KEY_RIGHT, I18n.get("jitl.key"));
    public static KeyMapping keyRotateCameraClockwise = new KeyMapping("Rotate Camera Clockwise", GLFW.GLFW_KEY_RIGHT_BRACKET, I18n.get("jitl.key"));
    public static KeyMapping keyRotateCameraCounterClockwise = new KeyMapping("Rotate Camera Counter-Clockwise", GLFW.GLFW_KEY_LEFT_BRACKET, I18n.get("jitl.key"));
    public static KeyMapping keyResetRotation = new KeyMapping("Reset Camera Rotation", GLFW.GLFW_KEY_END, I18n.get("jitl.key"));
    public static KeyMapping keyResetCameraPosition = new KeyMapping("Reset Camera Position", GLFW.GLFW_KEY_PAGE_DOWN, I18n.get("jitl.key"));
    public static KeyMapping keyResetAll = new KeyMapping("Reset All Isometric Camera Settings", GLFW.GLFW_KEY_BACKSLASH, I18n.get("jitl.key"));
    public static KeyMapping keyCycleSnapAngle = new KeyMapping("Cycle Through Isometric Angles", GLFW.GLFW_KEY_MINUS, I18n.get("jitl.key"));
    public static KeyMapping keyBigScreenshot = new KeyMapping("Take Big Screenshot", GLFW.GLFW_KEY_F9, I18n.get("jitl.key"));

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(keyStats);
        //event.register(keyArmor);
        event.register(keyAmulet);

//        event.register(keyIsometricView);
//        event.register(keyLockPerspective);
//        event.register(keyMoveCameraUp);
//        event.register(keyMoveCameraDown);
//        event.register(keyMoveCameraLeft);
//        event.register(keyMoveCameraRight);
//        event.register(keyRotateCameraClockwise);
//        event.register(keyRotateCameraCounterClockwise);
//        event.register(keyResetRotation);
//        event.register(keyResetAll);
//        event.register(keyCycleSnapAngle);
//        event.register(keyBigScreenshot);
    }


}
