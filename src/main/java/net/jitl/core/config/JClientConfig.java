package net.jitl.core.config;

import net.jitl.core.config.enums.EssencePosition;
import net.jitl.core.config.enums.HealthBarRendering;
import net.jitl.core.config.enums.IsometricAngleSnap;
import net.minecraftforge.common.ForgeConfigSpec;

public class JClientConfig {

	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue ENABLE_ISOMETRIC_CAMERA;
	public static final ForgeConfigSpec.BooleanValue LOCK_ISOMETRIC_PERSPECTIVE;
	public static final ForgeConfigSpec.EnumValue<IsometricAngleSnap> ISOMETRIC_ANGLE_SNAP;

	public static final ForgeConfigSpec.BooleanValue ENABLE_JITL_MENU_TOGGLE_BUTTON;
	public static final ForgeConfigSpec.BooleanValue ENABLE_JITL_MENU_SCREEN;
	public static final ForgeConfigSpec.EnumValue<HealthBarRendering> RENDER_ENTITY_HEALTH;
	public static final ForgeConfigSpec.EnumValue<EssencePosition> ESSENCE_POSITION;
	public static final ForgeConfigSpec.IntValue BIG_SCREENSHOT_WIDTH;
	public static final ForgeConfigSpec.IntValue BIG_SCREENSHOT_HEIGHT;

	static {
		BUILDER.push("Client configs for JITL");

		ENABLE_ISOMETRIC_CAMERA = BUILDER
				.comment("If set to 'true', the camera will be locked to the isometric view.")
				.define("Enable Isometric Camera: ", false);

		LOCK_ISOMETRIC_PERSPECTIVE = BUILDER
				.comment("If set to 'true', the perspective will be locked for the isometric camera.")
				.define("Lock Isometric Perspective: ", false);

		ISOMETRIC_ANGLE_SNAP = BUILDER
				.comment("Determines snap angle of the isometric camera view.")
				.defineEnum("Isometric Camera Angle: ", IsometricAngleSnap.NORTH_WEST);

		ENABLE_JITL_MENU_SCREEN = BUILDER
				.comment("If set to 'true', the JITL main menu theme will be enabled by default. "
						+ "This can also be toggled in the main menu itself by pressing the top-left button if enabled.")
				.define("Enable JITL Menu Screen: ", true);

		ENABLE_JITL_MENU_TOGGLE_BUTTON = BUILDER
				.comment("If set to 'true', the button that toggles the main menu theme will be visible. "
						+ "If set to 'false', the button will no longer appear on the main menu screen.")
				.define("Enable JITL Menu Screen Toggle Button: ", true);

		RENDER_ENTITY_HEALTH = BUILDER
				.comment("Determines if/when living entity's health bar will be rendered")
				.defineEnum("Render health: ", HealthBarRendering.IN_DEBUG_MODE);

		ESSENCE_POSITION = BUILDER
				.comment("Determines the position of the Essence bar in-game. ")
				.defineEnum("Essence Bar Position: ", EssencePosition.ABOVE_HUNGER_BAR);

		BIG_SCREENSHOT_WIDTH = BUILDER
				.comment("The width of any big screenshots you may take. ")
				.defineInRange("Width: ", 3840, 128, 7680);

		BIG_SCREENSHOT_HEIGHT = BUILDER
				.comment("The height of any big screenshots you may take. ")
				.defineInRange("Height: ", 2160, 72, 4320);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}

	public boolean isIsometricFOVEnabled() {
		return ENABLE_ISOMETRIC_CAMERA.get();
	}

	public void setIsometricFov(boolean enabled) {
		ENABLE_ISOMETRIC_CAMERA.set(enabled);
	}

	public boolean isIsometricPerspectiveLocked() {
		return LOCK_ISOMETRIC_PERSPECTIVE.get();
	}

	public void lockIsometricPerspective(boolean enabled) {
		LOCK_ISOMETRIC_PERSPECTIVE.set(enabled);
	}

	public boolean isJITLMenuEnabled() {
		return ENABLE_JITL_MENU_SCREEN.get();
	}

	public void setJITLMenu(boolean enabled) {
		ENABLE_JITL_MENU_SCREEN.set(enabled);
	}

	public boolean isToggleMenuButtonEnabled() {
		return ENABLE_JITL_MENU_TOGGLE_BUTTON.get();
	}

	public int getEssenceXPos() {
		return ESSENCE_POSITION.get().getX();
	}

	public int getEssenceYPos() {
		return ESSENCE_POSITION.get().getY();
	}

	public EssencePosition getEssencePosition() {
		return ESSENCE_POSITION.get();
	}

	public void setEssencePosition(EssencePosition essencePosition) {
		ESSENCE_POSITION.set(essencePosition);
	}

	public HealthBarRendering getHealthBarRendering() {
		return RENDER_ENTITY_HEALTH.get();
	}

	public void setHealthBarRendering(HealthBarRendering healthBarRendering) {
		RENDER_ENTITY_HEALTH.set(healthBarRendering);
	}

	public IsometricAngleSnap getIsometricAngleSnap() {
		return ISOMETRIC_ANGLE_SNAP.get();
	}

	public void setIsometricAngleSnap(IsometricAngleSnap isometricAngleSnap) {
		this.ISOMETRIC_ANGLE_SNAP.set(isometricAngleSnap);
	}

	public int getBigScreenshotWidth() {
		return BIG_SCREENSHOT_WIDTH.get();
	}

	public int getBigScreenshotHeight() {
		return BIG_SCREENSHOT_HEIGHT.get();
	}
}
