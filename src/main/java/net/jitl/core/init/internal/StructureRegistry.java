package net.jitl.core.init.internal;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.structure.HighestCeilingType;
import net.jitl.common.world.gen.structure.HighestGroundType;
import net.jitl.common.world.gen.structure.LowestCeilingType;
import net.jitl.common.world.gen.structure.LowestGroundType;
import net.jitl.core.init.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureRegistry {
	public static final StructurePlaceSettings defaultSettings = new StructurePlaceSettings().setIgnoreEntities(false).setFinalizeEntities(true).setKeepLiquids(true);
    public static final DeferredRegister<StructureType<?>> REGISTRY = DeferredRegister.create(Registries.STRUCTURE_TYPE, JITL.MODID);

    public static final RegistryObject<StructureType<?>> HIGHEST_GROUND = REGISTRY.register("highest_ground", () -> codecConv(HighestGroundType.CODEC));
	public static final RegistryObject<StructureType<?>> LOWEST_GROUND = REGISTRY.register("lowest_ground", () -> codecConv(LowestGroundType.CODEC));
	public static final RegistryObject<StructureType<?>> HIGHEST_CEILING = REGISTRY.register("highest_ceiling", () -> codecConv(HighestCeilingType.CODEC));
	public static final RegistryObject<StructureType<?>> LOWEST_CEILING = REGISTRY.register("lowest_ceiling", () -> codecConv(LowestCeilingType.CODEC));

    private static <S extends Structure> StructureType<S> codecConv(Codec<S> codec) {
        return () -> codec;
    }
    public static void placeStructure(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos) {
    	structure.placeInWorld(level, pos, pos, defaultSettings, random, 2);
	}
    public static void placeStructure(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
    	structure.placeInWorld(level, pos, pos, defaultSettings.copy().setRotation(rotation), random, 2);
	}
}