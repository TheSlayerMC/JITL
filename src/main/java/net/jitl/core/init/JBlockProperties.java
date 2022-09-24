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

    public static BlockBehaviour.Properties GRINDSTONE = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties DUNGEON_BLOCK = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1000000000F, -1F)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties DUNGEON_LAMP = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1000000000F, -1F)
            .lightLevel((level) -> 7)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties BREAKABLE_DUNGEON_LAMP = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .lightLevel((level) -> 7)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of(Material.DIRT)
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of(Material.LEAVES)
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties ICE = BlockBehaviour.Properties.of(Material.ICE)
            .strength(0.5F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties TALL_GRASS = BlockBehaviour.Properties.of(Material.LEAVES)
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion();

    public static BlockBehaviour.Properties GRASS = BlockBehaviour.Properties.of(Material.GRASS)
            .strength(0.75F)
            .sound(SoundType.GRASS);

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

    public static BlockBehaviour.Properties PORTAL = BlockBehaviour.Properties.of(Material.PORTAL)
            .strength(100F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .noCollission();

    private static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    public static String getTextureFromName(String name) {
        String plankName = "";
        if(name.contains("euca_brown")) {
            plankName = "euca_brown_planks";
        }
        if(name.contains("euca_gold")) {
            plankName = "euca_gold_planks";
        }
        if(name.contains("euca_gold")) {
            plankName = "euca_gold_planks";
        }
        if(name.contains("frozen")) {
            plankName = "frozen_planks";
        }
        return plankName;
    }
}
