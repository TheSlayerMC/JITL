package net.jitl.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class JCommonConfig {

	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
//
//	public static final ModConfigSpec.ConfigValue<Integer> IRIDIUM_ORE_MAX_SIZE;
//	public static final ModConfigSpec.ConfigValue<Integer> IRIDIUM_SPAWN_COUNT;
//	public static final ModConfigSpec.ConfigValue<Integer> IRIDIUM_ORE_MIN_Y;
//	public static final ModConfigSpec.ConfigValue<Integer> IRIDIUM_ORE_MAX_Y;

	public static final ModConfigSpec.BooleanValue NEED_SUMMONING_STRUCTURE;
	public static final ModConfigSpec.BooleanValue ENABLE_LOOT_POUCH_DROP;
	public static final ModConfigSpec.ConfigValue<Integer> COMMON_LOOT_CHANCE;
	public static final ModConfigSpec.ConfigValue<Integer> GOLD_LOOT_CHANCE;
	public static final ModConfigSpec.ConfigValue<Integer> DIAMOND_LOOT_CHANCE;

	static {
		BUILDER.push("Common configs for JITL");

//		IRIDIUM_ORE_MAX_SIZE = BUILDER.comment("Max Iridium ore to spawn in one vein").defineInRange("Max Iridium ore to spawn in one vein: ", 7, 1, 100);
//
//		IRIDIUM_SPAWN_COUNT = BUILDER.comment("Max Iridium ore to spawn in one chunk").defineInRange("Max Iridium ore to spawn in one chunk: ", 7, 1, 100);
//
//		IRIDIUM_ORE_MIN_Y = BUILDER.comment("Min Y Iridium ore can spawn").defineInRange("Min Y Iridium ore: ", -80, -128, 384);
//
//		IRIDIUM_ORE_MAX_Y = BUILDER.comment("Max Y Iridium ore can spawn").defineInRange("Max Y Iridium ore: ", 48, -128, 384);

		NEED_SUMMONING_STRUCTURE = BUILDER.comment("Need structure to use Summoning Table").define("Summoning structure: ", true);

		ENABLE_LOOT_POUCH_DROP = BUILDER.comment("Can Mobs drop Loot Pouches").define("Loot Pouches: ", true);
		COMMON_LOOT_CHANCE = BUILDER.comment("Common Loot Chance").define("Common Loot Chance 1 out of: ", 100);
		GOLD_LOOT_CHANCE = BUILDER.comment("Gold Loot Chance").define("Gold Loot Chance 1 out of: ", 150);
		DIAMOND_LOOT_CHANCE = BUILDER.comment("Diamond Loot Chance").define("Diamond Loot Chance 1 out of: ", 200);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}