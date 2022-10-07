package net.jitl.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JCommonConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	static {
		BUILDER.push("Configs for JITL");

		//ADD GENERATION CONFIGS

		BUILDER.pop();
		SPEC = BUILDER.build();
	}

}