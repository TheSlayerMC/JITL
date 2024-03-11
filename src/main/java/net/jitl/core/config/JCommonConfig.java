package net.jitl.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JCommonConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.IntValue IRIDIUM_ORE_MAX_SIZE;
	public static final ForgeConfigSpec.IntValue IRIDIUM_SPAWN_COUNT;
	public static final ForgeConfigSpec.IntValue IRIDIUM_ORE_MIN_Y;
	public static final ForgeConfigSpec.IntValue IRIDIUM_ORE_MAX_Y;

	public static final ForgeConfigSpec.BooleanValue NEED_SUMMONING_STRUCTRE;

	static {
		BUILDER.push("Common configs for JITL");

		IRIDIUM_ORE_MAX_SIZE = BUILDER.comment("Max Iridium ore to spawn in one vein").defineInRange("Value: ", 7, 1, 100);

		IRIDIUM_SPAWN_COUNT = BUILDER.comment("Max Iridium ore to spawn in one chunk").defineInRange("Value: ", 7, 1, 100);

		IRIDIUM_ORE_MIN_Y = BUILDER.comment("Min Y Iridium ore can spawn").defineInRange("Value: ", -80, -128, 384);

		IRIDIUM_ORE_MAX_Y = BUILDER.comment("Max Y Iridium ore can spawn").defineInRange("Value: ", 48, -128, 384);

		NEED_SUMMONING_STRUCTRE = BUILDER.comment("Need structure to use Summoning Table").define("Value: ", true);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}