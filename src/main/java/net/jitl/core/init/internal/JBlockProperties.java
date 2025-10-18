package net.jitl.core.init.internal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.ToIntFunction;

public class JBlockProperties {

    public static BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F);

    public static BlockBehaviour.Properties ALTAR = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .lightLevel((l) -> 2)
            .noOcclusion()
            .strength(1.5F, 6.0F);

    public static BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties POINTED_DRIPSTONE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
            .strength(1.5F, 1.0F)
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.POINTED_DRIPSTONE)
            .noOcclusion()
            .randomTicks()
            .dynamicShape()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(JBlockProperties::never);

    public static BlockBehaviour.Properties LIGHT_POINTED_DRIPSTONE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
            .strength(1.5F, 1.0F)
            .forceSolidOn()
            .lightLevel((state) -> 6)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.POINTED_DRIPSTONE)
            .noOcclusion()
            .randomTicks()
            .dynamicShape()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(JBlockProperties::never);

    public static BlockBehaviour.Properties DRIPSTONE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
            .strength(1.5F, 1.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.DRIPSTONE_BLOCK)
            .pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties ANCIENT_STONE = BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties OBELISK = BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE)
            .sound(SoundType.STONE)
            .lightLevel((l) -> 2)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties TROPHY = BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GLASS = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .isRedstoneConductor(JBlockProperties::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties DUNGEON_GLASS = BlockBehaviour.Properties.of()
            .strength(-1F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isViewBlocking(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .isRedstoneConductor(JBlockProperties::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties MUSHROOM_BLOCK = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(0.5F)
            .sound(SoundType.WOOD)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GLOW_MUSHROOM_BLOCK = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(0.5F)
            .noOcclusion()
            .isViewBlocking(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .lightLevel((s) -> 6)
            .sound(SoundType.WOOD)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties MUSHROOM_SHELF = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(0.5F)
            .sound(SoundType.WOOD)
            .noCollision()
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties SPAWNER = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .mapColor(MapColor.COLOR_BLACK)
            .noOcclusion()
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties BOTTLE = BlockBehaviour.Properties.of()
            .strength(0.2F)
            .mapColor(MapColor.COLOR_BLUE)
            .isViewBlocking(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .lightLevel((level) -> 3)
            .noOcclusion()
            .sound(JSoundTypes.VASE);

    public static final BlockBehaviour.Properties LUNIUM_ORE_PROPS = BlockBehaviour.Properties.of()
            .sound(JSoundTypes.LUNIUM_ORE)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F);

    public static BlockBehaviour.Properties FIRE_STONE = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties GRINDSTONE = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties ROCKITE_SPAWNER = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties CHEST = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .sound(SoundType.STONE)
            .noOcclusion();

    public static BlockBehaviour.Properties DUNGEON_BLOCK = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties DUNGEON_LAMP = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .lightLevel((level) -> 7)
            .sound(SoundType.STONE);

    public static BlockBehaviour.Properties BREAKABLE_DUNGEON_LAMP = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .lightLevel((level) -> 7)
            .sound(SoundType.GLASS);

    public static BlockBehaviour.Properties GLOW_BLOCK = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .lightLevel((level) -> 15)
            .sound(SoundType.GLASS);

    public static BlockBehaviour.Properties LIGHT_CRYSTAL_BLOCK = BlockBehaviour.Properties.of()
            .strength(1.5F)
            .lightLevel((level) -> 15)
            .noOcclusion()
            .noCollision()
            .sound(SoundType.GLASS);

    public static BlockBehaviour.Properties GLOW_DUNGEON_BLOCK = BlockBehaviour.Properties.of()
            .strength(-1F, 3600000.0F)
            .lightLevel((level) -> 15)
            .sound(SoundType.GLASS);

    public static BlockBehaviour.Properties SLIME = BlockBehaviour.Properties.of()
            .strength(0.5F)
            .sound(SoundType.SLIME_BLOCK)
            .noOcclusion()
            .randomTicks()
            .speedFactor(0.3F)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static BlockBehaviour.Properties FARMLAND = BlockBehaviour.Properties.of()
            .randomTicks()
            .strength(0.6F)
            .sound(SoundType.GRAVEL)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static final BlockBehaviour.Properties PATH = BlockBehaviour.Properties.of()
            .strength(0.65F)
            .sound(SoundType.GRASS)
            .isViewBlocking((state, getter, pos) -> true)
            .isSuffocating((state, getter, pos) -> true);

    public static BlockBehaviour.Properties FIRE_DIRT = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.GRAVEL);

    public static BlockBehaviour.Properties SAND = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.SAND);

    public static BlockBehaviour.Properties FIRE_SAND = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.SAND);

    public static BlockBehaviour.Properties CACTUS = BlockBehaviour.Properties.of()
            .strength(1F)
            .sound(SoundType.WOOL);

    public static BlockBehaviour.Properties LEAVES = BlockBehaviour.Properties.of()
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .randomTicks()
            .ignitedByLava()
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(JBlockProperties::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties FIRE_LEAVES = BlockBehaviour.Properties.of()
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .randomTicks()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties LUMINESCENT_LEAVES = BlockBehaviour.Properties.of()
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .randomTicks()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .lightLevel((state) -> 4)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CLOUD = BlockBehaviour.Properties.of()
            .strength(0.5F)
            .sound(SoundType.WOOL)
            .noOcclusion()
            .noCollision()
            .isViewBlocking(JBlockProperties::never)
            .isSuffocating(JBlockProperties::never)
            .isValidSpawn(JBlockProperties::never)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties CROP = BlockBehaviour.Properties.of()
            .sound(SoundType.CROP)
            .instabreak()
            .noCollision()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties GROWING_BUSH = BlockBehaviour.Properties.of()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .instabreak()
            .noCollision()
            .noOcclusion()
            .randomTicks();


    public static BlockBehaviour.Properties ICE = BlockBehaviour.Properties.of()
            .strength(0.5F)
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops();

    public static BlockBehaviour.Properties REPLACABLE_PLANT = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .ignitedByLava()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties PLANT = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .ignitedByLava()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties LILY_PLANT = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noOcclusion();

    public static BlockBehaviour.Properties FLOWER = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties GLOW_FLOWER = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .lightLevel((state) -> 5)
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties CRYSTAL = BlockBehaviour.Properties.of()
            .sound(SoundType.GLASS)
            .noCollision()
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static BlockBehaviour.Properties VINE = BlockBehaviour.Properties.of()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .randomTicks();

    public static BlockBehaviour.Properties GRASS = BlockBehaviour.Properties.of()
            .randomTicks()
            .strength(0.75F)
            .sound(SoundType.GRASS);

    public static BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of()
            .strength(1F)
            .ignitedByLava()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties LADDER = BlockBehaviour.Properties.of()
            .strength(1F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties CAMPFIRE = BlockBehaviour.Properties.of()
            .strength(1F)
            .noOcclusion()
            .lightLevel((state) -> 10)
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties BUTTON = BlockBehaviour.Properties.of()
            .strength(1F)
            .noOcclusion()
            .noCollision()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties DOOR = BlockBehaviour.Properties.of()
            .strength(3F)
            .noOcclusion()
            .dynamicShape()
            .sound(SoundType.WOOD);

    public static BlockBehaviour.Properties PORTAL = BlockBehaviour.Properties.of()
            .strength(3F)
            .sound(SoundType.GLASS)
            .lightLevel((state) -> 6)
            .noOcclusion()
            .noCollision();

    public static final BlockBehaviour.Properties VOLCANIC_BLOCK = BlockBehaviour.Properties.of()
            .sound(SoundType.STONE)
            .noOcclusion()
            .lightLevel((state) -> 10)
            .strength(1.5F, 6.0F);

    public static final BlockBehaviour.Properties FURNACE = BlockBehaviour.Properties.of()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .lightLevel(litBlockEmission(13))
            .strength(1.5F, 6.0F);

    public static final BlockBehaviour.Properties REDSTONE_LAMP = BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_ORANGE)
            .sound(SoundType.GLASS)
            .isValidSpawn(JBlockProperties::always)
            .lightLevel(litBlockEmission(15))
            .strength(0.3F);

    public static final BlockBehaviour.Properties GLOW_PLANT = BlockBehaviour.Properties.of()
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .noOcclusion()
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .noCollision()
            .ignitedByLava()
            .lightLevel((state) -> 10)
            .instabreak();

    public static BlockBehaviour.Properties LIGHT_PLANT = BlockBehaviour.Properties.of()
            .instabreak()
            .sound(SoundType.GRASS)
            .noCollision()
            .noOcclusion()
            .lightLevel((state) -> 4)
            .ignitedByLava()
            .offsetType(BlockBehaviour.OffsetType.XZ);

    public static final BlockBehaviour.Properties CRYSTAL_FRUIT = BlockBehaviour.Properties.of()
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .noOcclusion()
            .lightLevel((state) -> 10);

    public static final BlockBehaviour.Properties CAVE_GLOW_PLANT = BlockBehaviour.Properties.of()
            .sound(JSoundTypes.CRYSTAL_FRUIT)
            .noOcclusion()
            .noCollision()
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
        if(name.contains("iridium") && name.contains("torch")) {
            texName = "iridium_torch";
        }
        if(name.contains("frozen")) {
            texName = "frozen_planks";
        }
        if(name.contains("frozen_brick")) {
            texName = "frozen_bricks";
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
        if(name.contains("terranian")) {
            texName = "terranian_planks";
        }
        if(name.contains("cloudia")) {
            texName = "cloudia_planks";
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
        if(name.contains("corba_cobblestone")) {
            texName = "corba_cobblestone";
        }
        if(name.contains("corba_brick")) {
            texName = "corba_bricks";
        }
        if(name.contains("corba_cracked")) {
            texName = "corba_cracked_bricks";
        }
        if(name.contains("corba_dark")) {
            texName = "corba_dark_bricks";
        }
        if(name.contains("corba_light")) {
            texName = "corba_light_bricks";
        }
        if(name.contains("corba_sentry")) {
            texName = "corba_sentry_bricks";
        }
        if(name.contains("cloudia_post")) {
            texName = "cloudia_post";
        }
        if(name.contains("cloudia_tile")) {
            texName = "cloudia_tile";
        }
        if(name.contains("cloudia_brick")) {
            texName = "cloudia_brick";
        }
        if(name.contains("cloudia_wall")) {
            texName = "cloudia_wall";
        }
        if(name.contains("senterian_post")) {
            texName = "senterian_post";
        }
        if(name.contains("senterian_brick")) {
            texName = "senterian_bricks";
        }
        if(name.contains("sizzling_post")) {
            texName = "sizzling_post";
        }
        if(name.contains("scorched_rubble_brick")) {
            texName = "scorched_rubble_bricks";
        }
        if(name.contains("boil_cobblestone")) {
            texName = "boil_cobblestone";
        }
        if(name.contains("dark_floor")) {
            texName = "dark_floor";
        }
        if(name.contains("dark_brick")) {
            texName = "dark_brick";
        }
        if(name.contains("depths_brick")) {
            texName = "depths_brick";
        }
        if(name.contains("depths_shingle")) {
            texName = "depths_shingle";
        }
        if(name.contains("depths_cobblestone")) {
            texName = "depths_cobblestone";
        }
        if(name.contains("depths_tile")) {
            texName = "depths_tile";
        }
        if(name.contains("dark_shingle")) {
            texName = "dark_shingle";
        }
        if(name.contains("depths_dark_shingle")) {
            texName = "depths_dark_shingle";
        }
        if(name.contains("terranian_panel")) {
            texName = "terranian_panels";
        }
        if(name.contains("terranian_stone")) {
            texName = "terranian_stone";
        }
        if(name.contains("terranian_dark_panel")) {
            texName = "terranian_dark_panels";
        }
        if(name.contains("terranian_dark_panel")) {
            texName = "terranian_dark_panels";
        }
        if(name.contains("volcanic_sandstone")) {
            texName = "volcanic_sandstone_bottom";
        }
        if(name.contains("permafrost")) {
            texName = "permafrost";
        }
        if(name.contains("goldite_cobblestone")) {
            texName = "goldite_cobblestone";
        }
        if(name.contains("boil_cobblestone")) {
            texName = "boil_cobblestone";
        }
        if(name.contains("boil_brick")) {
            texName = "boil_bricks";
        }
        if(name.contains("boil_square")) {
            texName = "boil_square_bricks";
        }
        if(name.contains("boil_shingle")) {
            texName = "boil_shingle";
        }
        if(name.contains("blazier_brick")) {
            texName = "blazier_bricks";
        }
        if(name.contains("blazier_metal")) {
            texName = "blazier_metal";
        }
        if(name.contains("corba_dark")) {
            texName = "corba_dark_bricks";
        }
        return texName;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static boolean never(BlockState s, BlockGetter g, BlockPos p) {
        return false;
    }

    private static Boolean never(BlockState s, BlockGetter g, BlockPos p, EntityType<?> e) {
        return false;
    }

    private static Boolean always(BlockState s, BlockGetter g, BlockPos p, EntityType<?> e) {
        return true;
    }
}
