package net.jitl.common.world.gen.senterian;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SenterianTerrain extends Feature<NoneFeatureConfiguration> {

    public static final StructurePlaceSettings defaultSettings = new StructurePlaceSettings().setIgnoreEntities(false).setFinalizeEntities(true).setLiquidSettings(LiquidSettings.APPLY_WATERLOGGING);
    public static NormalNoise senterianNoise;
    public static long seed;
    public static Room[] rooms, rareRooms;
    public static VerticalRoom[] verticalRooms;
    public static BigRoom[] bigRooms;

    public SenterianTerrain() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        return false;
    }

    @Override
    public boolean place(@NotNull NoneFeatureConfiguration c, WorldGenLevel level, @NotNull ChunkGenerator g, @NotNull RandomSource random, @NotNull BlockPos pos) {
        StructureTemplateManager manager = level.getLevel().getServer().getStructureManager();

        rooms = new Room[]{
                new Room(manager, "senterian/room/room_1"),
                new Room(manager, "senterian/room/maze_1"),
                new Room(manager, "senterian/hallways/cross_1"),
                new Room(manager, "senterian/hallways/cross_2"),
                new Room(manager, "senterian/hallways/hallway_straight"),
                new Room(manager, "senterian/hallways/corner_1"),
        };

        rareRooms = new Room[] {
                new Room(manager, "senterian/room/loot_1"),
                new Room(manager, "senterian/room/loot_2")
        };

        verticalRooms = new VerticalRoom[] {
                new VerticalRoom(manager, "senterian/tall/staircase_hallway"),
                new VerticalRoom(manager, "senterian/tall/staircase_1"),
                new VerticalRoom(manager, "senterian/tall/tall_loot_1")
        };

        bigRooms = new BigRoom[] {
                new BigRoom(manager, "senterian/big/altar_1")
        };
        long newSeed = level.getSeed();
        if(seed != newSeed) {
            senterianNoise = NormalNoise.create(new XoroshiroRandomSource(newSeed), 1, 1.5);
            seed = newSeed;
        }
        for(int x = 0; x < 16; x++) for(int z = 0; z < 16; z++) {
            setBlock(level, pos.offset(x, 32, z), JBlocks.SENTERIAN_ROCK.get().defaultBlockState());
        }
        generate(level, random, pos.offset(0, 24, 0), true);
        generate(level, random, pos.offset(0, 16, 0), false);
        generate(level, random, pos.offset(0, 8, 0), false);
        generate(level, random, pos.offset(0, 0, 0), false);
        return true;
    }
    public void generate(WorldGenLevel level, RandomSource random, BlockPos pos, boolean topLayer) {
        int chunkX = pos.getX() / 16, chunkZ = pos.getZ() / 16;
        genRegular(level, random, pos);

        if(!topLayer) {
            if(random.nextInt(10) == 0)
                genRare(level, random, pos);

            if(random.nextInt(10) == 0)
                genVertical(level, random, pos);

            if(wantsBigRoom(chunkX, pos.getY(), chunkZ))
                bigRooms[random.nextInt(bigRooms.length)].gen(level, random, pos, chunkX % 2, chunkZ % 2);
        }
    }

    private void genVertical(WorldGenLevel level, RandomSource random, BlockPos pos) {
        verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.getRandom(random));
    }

    private void genRegular(WorldGenLevel level, RandomSource random, BlockPos pos) {
        rooms[random.nextInt(rooms.length)].gen(level, random, pos, Rotation.getRandom(random));
    }

    private void genRare(WorldGenLevel level, RandomSource random, BlockPos pos) {
        rareRooms[random.nextInt(rareRooms.length)].gen(level, random, pos, Rotation.getRandom(random));
    }

    public static boolean wantsBigRoom(int chunkX, int y, int chunkZ) {
        int xPart = chunkX % 2, zPart = chunkZ % 2;
        Random rand = new Random();
        return rand.nextInt(15) == 0 && getDoorAmount(chunkX, y, chunkZ) == 0
                || getDoorAmount(chunkX + 1 + ((chunkX > -1 ? -2 : 2) * xPart), y, chunkZ) == 0
                || getDoorAmount(chunkX, y, chunkZ + 1 + ((chunkZ > -1 ? -2 : 2) * zPart)) == 0
                || getDoorAmount(chunkX + 1 + ((chunkX > -1 ? -2 : 2) * xPart), y, chunkZ + 1 + ((chunkZ > -1 ? -2 : 2) * zPart)) == 0;
    }

    public static byte getDoorAmount(int chunkX, int y, int chunkZ) {
        byte doorValue = getDoorValue(chunkX, y, chunkZ);
        byte hasXDoor = (byte) (doorValue % 2), hasZDoor = (byte) ((doorValue / 2) % 2), hasXEntrance = (byte) (getDoorValue(chunkX - 1, y, chunkZ) % 2), hasZEntrance = (byte) ((getDoorValue(chunkX, y, chunkZ - 1) / 2) % 2);
        return (byte) (hasXDoor + hasZDoor + hasXEntrance + hasZEntrance);
    }

    public static byte getDoorValue(int chunkX, int y, int chunkZ) {
        byte value = 0;
        for(byte steps = 0; value == 0 && steps < 16; steps++) value = (byte) (Math.abs(senterianNoise.getValue(chunkX - (111 * steps), y + (112 * steps), chunkZ - (113 * steps))) * 15.7);
        return value;
    }

    public static void placeRoom(StructureTemplate room, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
        boolean b = rotation == Rotation.CLOCKWISE_180;
        pos = pos.offset(b || rotation == Rotation.CLOCKWISE_90 ? 15 : 0, 0, b || rotation == Rotation.COUNTERCLOCKWISE_90 ? 15 : 0);
        room.placeInWorld(level, pos, pos, defaultSettings.setRotation(rotation), random, 2);
    }
    public static void setBlock(WorldGenLevel level, BlockPos pos, BlockState block) {level.setBlock(pos, block, 3);}

    public static class Room {
        public final StructureTemplate room;
        public Room(StructureTemplateManager manager, String location) {
            room = manager.getOrCreate(ResourceLocation.fromNamespaceAndPath(JITL.MODID, location));
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
            placeRoom(room, level, random, pos, rotation);
        }
    }

    public static class VerticalRoom {
        public final StructureTemplate room;
        public VerticalRoom(StructureTemplateManager manager, String location) {
            room = manager.getOrCreate(ResourceLocation.fromNamespaceAndPath(JITL.MODID, location));
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {

            placeRoom(room, level, random, pos, rotation);
        }
    }

    public static class BigRoom {
        public final StructureTemplate room;
        public BigRoom(StructureTemplateManager manager, String room) {
            this.room = manager.getOrCreate(ResourceLocation.fromNamespaceAndPath(JITL.MODID, room));
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, int xPart, int zPart) {
            if(xPart == 0) {
                if(zPart == 0) placeRoom(room, level, random, pos, Rotation.NONE);
                else placeRoom(room, level, random, pos, Rotation.COUNTERCLOCKWISE_90);
            } else {
                if(zPart == 0) placeRoom(room, level, random, pos, Rotation.CLOCKWISE_90);
                else placeRoom(room, level, random, pos, Rotation.CLOCKWISE_180);
            }
        }
    }
}