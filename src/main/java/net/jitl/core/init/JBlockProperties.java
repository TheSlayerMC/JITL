package net.jitl.core.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;

import java.util.function.ToIntFunction;

public class JBlockProperties {

    public static BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of(Material.DIRT)
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of(Material.LEAVES)
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties SAPLING = BlockBehaviour.Properties.of(Material.LEAVES)
            .instabreak()
            .sound(SoundType.GRASS)
            .noOcclusion();

    public static BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1F)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties BUTTON = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1F)
            .noOcclusion()
            .noCollission()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties DOOR = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(3F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    private static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }

    private static ToIntFunction<BlockState> hardness(int hardness) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LOCKED) ? hardness : 0;
        };
    }

    public static String getTextureFromName(String name) {
        String plankName = "";
        if(name.contains("euca_brown")) {
            plankName = "euca_brown_planks";
        }
        if(name.contains("euca_gold")) {
            plankName = "euca_gold_planks";
        }
        return plankName;
    }
}
