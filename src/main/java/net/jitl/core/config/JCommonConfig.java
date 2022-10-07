package net.jitl.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JCommonConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.IntValue IRIDIUM_ORE_MAX_SIZE, IRIDIUM_SPAWN_COUNT, IRIDIUM_ORE_MIN_Y, IRIDIUM_ORE_MAX_Y;

	static {
		BUILDER.push("Configs for JITL");

		IRIDIUM_ORE_MAX_SIZE = BUILDER
				.comment("Max Iridium ore to spawn in one vein")
				.defineInRange("Value: ", 7, 1, 100);

		IRIDIUM_SPAWN_COUNT = BUILDER
				.comment("Max Iridium ore to spawn in one chunk")
				.defineInRange("Value: ", 7, 1, 100);

		IRIDIUM_ORE_MIN_Y = BUILDER
				.comment("Min Y Iridium ore can spawn")
				.defineInRange("Value: ", -80, -128, 384);

		IRIDIUM_ORE_MAX_Y = BUILDER
				.comment("Max Y Iridium ore can spawn")
				.defineInRange("Value: ", 48, -128, 384);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}