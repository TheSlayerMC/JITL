package net.jitl.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class JCommonConfig {

	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;

	public static final ModConfigSpec.BooleanValue NEED_SUMMONING_STRUCTURE;
	public static final ModConfigSpec.BooleanValue ENABLE_LOOT_POUCH_DROP;
	public static final ModConfigSpec.ConfigValue<Integer> COMMON_LOOT_CHANCE;
	public static final ModConfigSpec.ConfigValue<Integer> GOLD_LOOT_CHANCE;
	public static final ModConfigSpec.ConfigValue<Integer> DIAMOND_LOOT_CHANCE;
	public static final ModConfigSpec.IntValue PORTAL_SEARCH_RADIUS;

	static {
		BUILDER.push("Common configs for JITL");

		NEED_SUMMONING_STRUCTURE = BUILDER.comment("Need structure to use Summoning Table").define("Summoning structure: ", true);

		ENABLE_LOOT_POUCH_DROP = BUILDER.comment("Can Mobs drop Loot Pouches").define("Loot Pouches: ", true);
		COMMON_LOOT_CHANCE = BUILDER.comment("Common Loot Chance").define("Common Loot Chance 1 out of: ", 100);
		GOLD_LOOT_CHANCE = BUILDER.comment("Gold Loot Chance").define("Gold Loot Chance 1 out of: ", 150);
		DIAMOND_LOOT_CHANCE = BUILDER.comment("Diamond Loot Chance").define("Diamond Loot Chance 1 out of: ", 200);
		PORTAL_SEARCH_RADIUS = BUILDER.comment("how far to look for portal locations.").defineInRange("Portal Search Radius", 24, 1, 128);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}