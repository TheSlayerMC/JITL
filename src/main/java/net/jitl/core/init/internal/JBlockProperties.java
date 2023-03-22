package net.jitl.core.init.internal;

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

    public static BlockBehaviour.Properties GLASS = BlockBehaviour.Properties.of(Material.GLASS)
            .strength(1F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GLOW_LAMP = BlockBehaviour.Properties.of(Material.GLASS)
            .strength(1.0F)
            .sound(SoundType.GLASS)
            .lightLevel((level) -> 15)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties MUSHROOM_BLOCK = BlockBehaviour.Properties.of(Material.PLANT)
            .strength(0.5F)
            .sound(SoundType.WOOD)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties SPAWNER = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .noOcclusion()
            .sound(SoundType.STONE);

    public static final BlockBehaviour.Properties LUNIUM_ORE_PROPS = BlockBehaviour.Properties.of(Material.STONE)
            .sound(JSoundTypes.LUNIUM_ORE)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F);

    public static BlockBehaviour.Properties FIRE_STONE = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GRINDSTONE = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties ROCKITE_SPAWNER = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties CHEST = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties DUNGEON_BLOCK = BlockBehaviour.Properties.of(Material.STONE)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties DUNGEON_LAMP = BlockBehaviour.Properties.of(Material.STONE)
            .strength(-1F, 3600000.0F)
            .lightLevel((level) -> 7)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties BREAKABLE_DUNGEON_LAMP = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .lightLevel((level) -> 7)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of(Material.DIRT)
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties FARMLAND = BlockBehaviour.Properties.of(Material.DIRT)
            .randomTicks()
            .strength(0.6F)
            .sound(SoundType.GRAVEL)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static final BlockBehaviour.Properties PATH = BlockBehaviour.Properties.of(Material.DIRT)
            .strength(0.65F)
            .sound(SoundType.GRASS)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static BlockBehaviour.Properties FIRE_DIRT = BlockBehaviour.Properties.of(Material.DIRT)
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties SAND = BlockBehaviour.Properties.of(Material.SAND)
            .strength(1F)
            .sound(SoundType.SAND);

    public static BlockBehaviour.Properties FIRE_SAND = BlockBehaviour.Properties.of(Material.SAND)
            .strength(1F)
            .sound(SoundType.SAND);

    public static BlockBehaviour.Properties CACTUS = BlockBehaviour.Properties.of(Material.CACTUS)
            .strength(1F)
            .sound(SoundType.WOOL);

    public static BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of(Material.LEAVES)
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CLOUD = BlockBehaviour.Properties.of(Material.WOOL)
            .strength(0.5F)
            .sound(SoundType.WOOL)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CROP = BlockBehaviour.Properties.of(Material.PLANT)
            .sound(SoundType.CROP)
            .instabreak()
            .noCollission()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties GROWING_BUSH = BlockBehaviour.Properties.of(Material.PLANT)
            .sound(SoundType.SWEET_BERRY_BUSH)
            .instabreak()
            .noCollission()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties LUMINESCENT_LEAVES = BlockBehaviour.Properties.of(Material.LEAVES)
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .lightLevel((state) -> 4)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties ICE = BlockBehaviour.Properties.of(Material.ICE)
            .strength(0.5F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties REPLACABLE_PLANT = BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT)
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties PLANT = BlockBehaviour.Properties.of(Material.PLANT)
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties LILY_PLANT = BlockBehaviour.Properties.of(Material.PLANT)
            .instabreak()
            .sound(SoundType.GRASS)
            .noOcclusion();

    public static BlockBehaviour.Properties FLOWER = BlockBehaviour.Properties.of(Material.PLANT)
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties GLOW_FLOWER = BlockBehaviour.Properties.of(Material.PLANT)
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .lightLevel((state) -> 5)
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties CRYSTAL = BlockBehaviour.Properties.of(Material.PLANT)
            .sound(SoundType.GLASS)
            .noCollission()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties VINE = BlockBehaviour.Properties.of(Material.LEAVES)
            .sound(SoundType.GRASS)
            .noCollission()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties GRASS = BlockBehaviour.Properties.of(Material.GRASS)
            .randomTicks()
            .strength(0.75F)
            .sound(SoundType.GRASS);

    public static BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1F)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties LADDER = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties CAMPFIRE = BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1F)
            .noOcclusion()
            .lightLevel((state) -> 10)
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
            .lightLevel((state) -> 4)
            .noOcclusion()
            .noCollission();

    public static final BlockBehaviour.Properties VOLCANIC_BLOCK = BlockBehaviour.Properties.of(Material.STONE)
            .sound(SoundType.STONE)
            .noOcclusion()
            .lightLevel((state) -> 10)
            .strength(1.5F, 6.0F);

    public static final BlockBehaviour.Properties FURNACE = BlockBehaviour.Properties.of(Material.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .lightLevel(litBlockEmission(13))
            .strength(1.5F, 6.0F);

    public static final BlockBehaviour.Properties GLOW_PLANT = BlockBehaviour.Properties.of(Material.GLASS)
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .noCollission()
            .lightLevel((state) -> 10)
            .instabreak();

    public static final BlockBehaviour.Properties CAVE_GLOW_PLANT = BlockBehaviour.Properties.of(Material.PLANT)
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .noOcclusion()
            .noCollission()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .lightLevel((state) -> 5)
            .instabreak();

    public static String getTextureFromName(String name) {
        String texName = "";
        if(name.contains("euca_brown")) {
            texName = "euca_brown_planks";
        }
        if(name.contains("euca_gold")) {
            texName = "euca_gold_planks";
        }
        if(name.contains("frozen")) {
            texName = "frozen_planks";
        }
        if(name.contains("depths")) {
            texName = "depths_planks";
        }
        if(name.contains("corba")) {
            texName = "corba_planks";
        }
        if(name.contains("corba_post")) {
            texName = "corba_post";
        }
        if(name.contains("dungeon_lamp")) {
            texName = "dungeon_lamp";
        }
        if(name.contains("dungeon_brick")) {
            texName = "dungeon_bricks";
        }
        if(name.contains("nether_dungeon_brick")) {
            texName = "nether_dungeon_bricks";
        }
        if(name.contains("gilded_dungeon_brick")) {
            texName = "gilded_dungeon_bricks";
        }
        if(name.contains("chiseled_dungeon")) {
            texName = "chiseled_dungeon_bricks";
        }
        if(name.contains("carved_dungeon")) {
            texName = "carved_dungeon_bricks";
        }
        if(name.contains("warped_quartz")) {
            texName = "warped_quartz_block";
        }
        if(name.contains("smooth_warped_quartz")) {
            texName = "smooth_warped_quartz_block";
        }
        if(name.contains("crimson_quartz")) {
            texName = "crimson_quartz_block";
        }
        if(name.contains("smooth_crimson_quartz")) {
            texName = "smooth_crimson_quartz_block";
        }
        if(name.contains("burned")) {
            texName = "burned_bark_planks";
        }
        if(name.contains("euca_dungeon_stairs")) {
            texName = "euca_dungeon_brick";
        }
        if(name.contains("packed_snow")) {
            texName = "packed_snow_bricks";
        }
        if(name.contains("packed_ice")) {
            texName = "packed_ice_bricks";
        }
        if(name.contains("royal")) {
            texName = "royal_bricks";
        }
        if(name.contains("royal_stone")) {
            texName = "royal_stone";
        }
        if(name.contains("polished_royal")) {
            texName = "polished_royal_stone";
        }
        if(name.contains("royal_paver")) {
            texName = "royal_paver";
        }
        if(name.contains("royal_shingle")) {
            texName = "royal_shingle";
        }
        if(name.contains("corba_cobblestone_wall")) {
            texName = "corba_cobblestone";
        }
        if(name.contains("corba_brick")) {
            texName = "corba_bricks";
        }
        if(name.contains("terranian_post")) {
            texName = "terranian_post";
        }
        if(name.contains("cloudia_post")) {
            texName = "cloudia_post";
        }
        if(name.contains("cloudia_tile_stairs")) {
            texName = "cloudia_tile";
        }
        if(name.contains("cloudia_brick_stairs")) {
            texName = "cloudia_brick";
        }
        if(name.contains("cloudia_wall")) {
            texName = "cloudia_wall";
        }

        return texName;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
