package net.jitl.common.world.gen.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class Underground extends PlacementFilter {
	private static final Underground INSTANCE = new Underground();
	public static Codec<Underground> CODEC = Codec.unit(() -> INSTANCE);
	private Underground() {}
	public static Underground underground() {return INSTANCE;}
	protected boolean shouldPlace(PlacementContext context, RandomSource source, BlockPos pos) {
		return isUnderground(context.getLevel(), pos);
	}
	public static boolean isUnderground(LevelAccessor level, BlockPos pos) {
		return !level.canSeeSkyFromBelowWater(pos.above());
	}
	public PlacementModifierType<?> type() {
		return PlacementModifierRegistry.UNDERGROUND;
	}
}