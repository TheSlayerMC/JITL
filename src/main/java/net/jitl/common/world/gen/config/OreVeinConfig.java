package net.jitl.common.world.gen.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class OreVeinConfig implements FeatureConfiguration {
	public static final Codec<OreVeinConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("cutoffChance").forGetter((config) -> {
	        return config.cutoffChance;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("straightness").forGetter((config) -> {
	        return config.straightness;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("branchingChance").forGetter((config) -> {
	        return config.branchingChance;
        }), Codec.list(TargetBlockState.CODEC).fieldOf("states").forGetter((config) -> {
			return config.targetStates;
		})).apply(instance, OreVeinConfig::new);
	});
	public final List<TargetBlockState> targetStates;
	public final float cutoffChance, straightness, branchingChance;
	public OreVeinConfig(float cutoffChance, float straightness, float branchingChance, List<TargetBlockState> states) {
	    this.cutoffChance = cutoffChance;
	    this.straightness = straightness;
	    this.branchingChance = branchingChance;
		this.targetStates = states;
	}
	public OreVeinConfig(float cutoffChance, float straightness, float branchingChance, RuleTest target, BlockState state, float chance, float discardChance) {
		this(cutoffChance, straightness, branchingChance, ImmutableList.of(new TargetBlockState(target, state, chance, discardChance)));
	}
	public static TargetBlockState target(RuleTest target, BlockState state, float chance, float discardChance) {
	      return new TargetBlockState(target, state, chance, discardChance);
	}
	public static class TargetBlockState {
	      public static final Codec<TargetBlockState> CODEC = RecordCodecBuilder.create((instance) -> {
	         return instance.group(RuleTest.CODEC.fieldOf("target").forGetter((config) -> {
	            return config.target;
	         }), BlockState.CODEC.fieldOf("state").forGetter((config) -> {
	            return config.state;
	         }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter((config) -> {
	             return config.chance;
	         }), Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((config) -> {
	             return config.discardChanceOnAirExposure;
	         })).apply(instance, TargetBlockState::new);
	      });
	      public final RuleTest target;
	      public final BlockState state;
	      public final float chance, discardChanceOnAirExposure;

	      TargetBlockState(RuleTest target, BlockState state, float chance, float discardChance) {
	         this.target = target;
	         this.state = state;
	         this.chance = chance;
	         this.discardChanceOnAirExposure = discardChance;
	      }
	}
}