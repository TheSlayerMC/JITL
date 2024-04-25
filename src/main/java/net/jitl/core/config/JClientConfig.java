package net.jitl.core.config;

import net.jitl.core.config.enums.EssencePosition;
import net.neoforged.neoforge.common.ModConfigSpec;

public class JClientConfig {

	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;

	public static final ModConfigSpec.BooleanValue UPDATE_MESSAGE;

	public static final ModConfigSpec.EnumValue<EssencePosition> ESSENCE_POSITION;

	static {
		BUILDER.push("Client configs for JITL");

		UPDATE_MESSAGE = BUILDER
				.comment("If set to 'true', you will get a message on every login with an update check.")
				.define("Send Player update Messages: ", true);

		ESSENCE_POSITION = BUILDER
				.comment("Determines the position of the Essence bar in-game. ")
				.defineEnum("Essence Bar Position: ", EssencePosition.ABOVE_HUNGER_BAR);

		BUILDER.pop();
		SPEC = BUILDER.build();
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
}
