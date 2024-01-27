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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import javax.annotation.Nullable;
import java.util.Random;

public class SenterianTerrain extends Feature<NoneFeatureConfiguration> {

    public static final StructurePlaceSettings defaultSettings = new StructurePlaceSettings().setIgnoreEntities(false).setFinalizeEntities(true).setKeepLiquids(true);
    public static NormalNoise dungeonNoise;
    public static long seed;
    public static StructureTemplate doorway, entranceRoom;
    public static StructureTemplate[] doors;
    public static Room[] adaptiveRooms, corners, crossroads, deadends, hallways;
    public static VerticalRoom[] verticalRooms;
    public static BigRoom[] bigRooms;

    public SenterianTerrain() {super(NoneFeatureConfiguration.CODEC);}

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return place(null, context.level(), null, context.random(), context.origin());
    }

    @Override
    public boolean place(NoneFeatureConfiguration c, WorldGenLevel level, ChunkGenerator g, RandomSource random, BlockPos pos) {
        if(doorway == null) {
            StructureTemplateManager manager = level.getLevel().getServer().getStructureManager();

            entranceRoom = manager.getOrCreate(new ResourceLocation(JITL.MODID, "senterian/room/portal_room"));
            doorway = manager.getOrCreate(new ResourceLocation(JITL.MODID, "senterian/doors/door"));

            BlockState rock = JBlocks.SENTERIAN_ROCK.get().defaultBlockState();
            BlockState brick = JBlocks.SENTERIAN_BRICKS.get().defaultBlockState();

            doors = new StructureTemplate[] {
                    manager.getOrCreate(new ResourceLocation(JITL.MODID, "senterian/doors/door")),
            };

            adaptiveRooms = new Room[] {
                    new Room(manager, "senterian/room/room_1", brick),
                    new Room(manager, "senterian/room/maze_1", brick)
            };

            crossroads = new Room[] {
                    new Room(manager, "senterian/hallways/cross_1", brick),
                    new Room(manager, "senterian/hallways/cross_2", brick)
            };
            deadends = new Room[] {
                    new Room(manager, "senterian/ends/deadend_1", brick)
            };
            hallways = new Room[] {
                    new Room(manager, "senterian/hallways/hallway_straight", brick)
            };
            corners = new Room[] {
                    new Room(manager, "senterian/hallways/corner_1", brick)
            };
            verticalRooms = new VerticalRoom[] {
                    new VerticalRoom(manager, "senterian/tall/staircase_hallway", brick, doors[0]),
                    new VerticalRoom(manager, "senterian/tall/staircase_1", brick, doors[0]),
                    new VerticalRoom(manager, "senterian/tall/tall_loot_1", brick, null)
            };
            bigRooms = new BigRoom[] {
                    new BigRoom(manager, "senterian/big/altar_1"),
                    new BigRoom(manager, "senterian/big/altar_1"),
                    new BigRoom(manager, "senterian/big/altar_1")

            };
        }
        long newSeed = level.getSeed();
        if(seed != newSeed) {
            dungeonNoise = NormalNoise.create(new XoroshiroRandomSource(newSeed), 1, 1.5);
            seed = newSeed;
        }
        for(int x = 0; x < 16; x++) for(int z = 0; z < 16; z++) {
            setBlock(level, pos.offset(x, 32, z), JBlocks.SENTERIAN_ROCK.get().defaultBlockState());
        }
        if(generate(level, random, pos, (byte)0)) {
            if(generate(level, random, pos.offset(0, 8, 0), (byte)1)) {
                if(generate(level, random, pos.offset(0, 16, 0), (byte)2)) generate(level, random, pos.offset(0, 24, 0), (byte)3);
            }
            else generate(level, random, pos.offset(0, 24, 0), (byte)3);
        } else if(generate(level, random, pos.offset(0, 16, 0), (byte)2)) generate(level, random, pos.offset(0, 24, 0), (byte)3);
        return true;
    }
    public boolean generate(WorldGenLevel level, RandomSource random, BlockPos pos, byte layer) {
        int chunkX = pos.getX() / 16, chunkZ = pos.getZ() / 16, y = layer * 256;
        if(layer < 3) {
            if(wantsBigRoom(chunkX, y, chunkZ)) {
                byte doorValue = (byte) (getDoorValue((chunkX - (chunkX < 0 ? 1 : 0)) / 2, y, (chunkZ - (chunkZ < 0 ? 1 : 0)) / 2) % 4);
                bigRooms[doorValue == 1 ? 1 : doorValue == 2 ? 2 : 0].gen(level, random, pos, chunkX % 2, chunkZ % 2);
            } else {
                byte roomShape = getRoomShape(chunkX, y, chunkZ), doorAmount = getDoorAmount(chunkX, y, chunkZ);
                if(random.nextInt(16) == 0 && !wantsBigRoom(chunkX, y + 256, chunkZ)) genVertical(level, random, pos, roomShape, getRoomShape(chunkX, y + 256, chunkZ), doorAmount);
                else {
                    genRegular(level, random, pos, roomShape, doorAmount);
                    return true;
                }
            }
            return false;
        } else {
            byte doorAmount = getDoorAmount(chunkX, y, chunkZ);
            if(doorAmount != 0 && random.nextInt(92) == 0) {
                //TODO MOVE TO TELEPORTER genEntrance(level, random, pos, getRoomShape(chunkX, y, chunkZ));
            }
            else genRegular(level, random, pos, getRoomShape(chunkX, y, chunkZ), doorAmount);
        }
        return true;
    }

    private void genEntrance(WorldGenLevel level, RandomSource random, BlockPos pos, byte roomShape) {
        placeStructure(entranceRoom, level, random, pos.offset(1, 0, 1), Rotation.NONE);
        genWalls(level, random, pos, JBlocks.SENTERIAN_BRICKS.get().defaultBlockState(), roomShape, null);
    }

    private void genVertical(WorldGenLevel level, RandomSource random, BlockPos pos, byte roomShape, byte upperRoomShape, byte doorAmount) {
        if(doorAmount == 2) {
            if(getsNorthEntrance(roomShape)) {
                if(hasEastDoor(roomShape)) verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.NONE, roomShape, upperRoomShape);
                else if(getsWestEntrance(roomShape)) verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.COUNTERCLOCKWISE_90, roomShape, upperRoomShape);
                else verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.NONE, roomShape, upperRoomShape);
            } else if(hasEastDoor(roomShape)) {
                if(hasSouthDoor(roomShape)) verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.CLOCKWISE_90, roomShape, upperRoomShape);
                else verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.CLOCKWISE_90, roomShape, upperRoomShape);
            } else verticalRooms[random.nextInt(verticalRooms.length)].gen(level, random, pos, Rotation.CLOCKWISE_180, roomShape, upperRoomShape);
        } //else verticalRooms[1 + random.nextInt(3)].gen(level, random, pos, Rotation.getRandom(random), roomShape, upperRoomShape);
    }
    private void genRegular(WorldGenLevel level, RandomSource random, BlockPos pos, byte roomShape, byte doorAmount) {
        if(random.nextInt(6) == 0) adaptiveRooms[random.nextInt(adaptiveRooms.length)].gen(level, random, pos, Rotation.getRandom(random), roomShape);
        else if(doorAmount == 1) deadends[random.nextInt(deadends.length)].gen(level, random, pos, getsNorthEntrance(roomShape) ? Rotation.NONE : hasEastDoor(roomShape) ? Rotation.CLOCKWISE_90 : hasSouthDoor(roomShape) ? Rotation.CLOCKWISE_180 : Rotation.COUNTERCLOCKWISE_90, roomShape);
        else if(doorAmount == 2) {
            if(getsNorthEntrance(roomShape)) {
                if(hasEastDoor(roomShape)) corners[random.nextInt(corners.length)].gen(level, random, pos, Rotation.NONE, roomShape);
                else if(getsWestEntrance(roomShape)) corners[random.nextInt(corners.length)].gen(level, random, pos, Rotation.COUNTERCLOCKWISE_90, roomShape);
                else hallways[random.nextInt(hallways.length)].gen(level, random, pos, Rotation.NONE, roomShape);
            } else if(hasEastDoor(roomShape)) {
                if(hasSouthDoor(roomShape)) corners[random.nextInt(corners.length)].gen(level, random, pos, Rotation.CLOCKWISE_90, roomShape);
                else hallways[random.nextInt(hallways.length)].gen(level, random, pos, Rotation.CLOCKWISE_90, roomShape);
            } else corners[random.nextInt(corners.length)].gen(level, random, pos, Rotation.CLOCKWISE_180, roomShape);
        } //else if(doorAmount == 3) junctions[random.nextInt(junctions.length)].gen(level, random, pos, !hasSouthDoor(roomShape) ? Rotation.NONE : !getsWestEntrance(roomShape) ? Rotation.CLOCKWISE_90 : !getsNorthEntrance(roomShape) ? Rotation.CLOCKWISE_180 : Rotation.COUNTERCLOCKWISE_90, roomShape);
        else crossroads[random.nextInt(crossroads.length)].gen(level, random, pos, Rotation.NONE, roomShape);
    }
    public static boolean wantsBigRoom(int chunkX, int y, int chunkZ) {
        int xPart = chunkX % 2, zPart = chunkZ % 2;
        Random rand = new Random();
        return rand.nextInt(2) == 0 && getDoorAmount(chunkX, y, chunkZ) == 0
                || getDoorAmount(chunkX + 1 + ((chunkX > -1 ? -2 : 2) * xPart), y, chunkZ) == 0
                || getDoorAmount(chunkX, y, chunkZ + 1 + ((chunkZ > -1 ? -2 : 2) * zPart)) == 0
                || getDoorAmount(chunkX + 1 + ((chunkX > -1 ? -2 : 2) * xPart), y, chunkZ + 1 + ((chunkZ > -1 ? -2 : 2) * zPart)) == 0;
    }
    public static byte getRoomShape(int chunkX, int y, int chunkZ) {
        byte doorValue = getDoorValue(chunkX, y, chunkZ);
        byte hasXDoor = (byte) (doorValue % 2), hasZDoor = (byte) ((doorValue / 2) % 2), hasXEntrance = (byte) (getDoorValue(chunkX - 1, y, chunkZ) % 2), hasZEntrance = (byte) ((getDoorValue(chunkX, y, chunkZ - 1) / 2) % 2);
        return (byte) (hasXDoor + (hasZDoor * 2) + (hasXEntrance * 4) + (hasZEntrance * 8));
    }
    public static byte getDoorAmount(int chunkX, int y, int chunkZ) {
        byte doorValue = getDoorValue(chunkX, y, chunkZ);
        byte hasXDoor = (byte) (doorValue % 2), hasZDoor = (byte) ((doorValue / 2) % 2), hasXEntrance = (byte) (getDoorValue(chunkX - 1, y, chunkZ) % 2), hasZEntrance = (byte) ((getDoorValue(chunkX, y, chunkZ - 1) / 2) % 2);
        return (byte) (hasXDoor + hasZDoor + hasXEntrance + hasZEntrance);
    }
    public static boolean getsWestEntrance(byte roomShape) {return (roomShape / 4) % 2 == 1;}
    public static boolean getsNorthEntrance(byte roomShape) {return (roomShape / 8) % 2 == 1;}
    public static boolean hasEastDoor(byte doorValue) {return doorValue % 2 == 1;}
    public static boolean hasEastDoor(int chunkX, int y, int chunkZ) {
        return getDoorValue(chunkX, y, chunkZ) % 2 == 1;
    }
    public static boolean hasSouthDoor(byte doorValue) {return (doorValue / 2) % 2 == 1;}
    public static boolean hasSouthDoor(int chunkX, int y, int chunkZ) {
        return (getDoorValue(chunkX, y, chunkZ) / 2) % 2 == 1;
    }
    public static byte getDoorValue(int chunkX, int y, int chunkZ) {
        byte value = 0;
        for(byte steps = 0; value == 0 && steps < 16; steps++) value = (byte) (Math.abs(dungeonNoise.getValue(chunkX - (111 * steps), y + (112 * steps), chunkZ - (113 * steps))) * 15.7);
        return value;
    }
    public static void placeRoom(StructureTemplate room, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation, byte getsWalls) {
        boolean b = rotation == Rotation.CLOCKWISE_180;
        pos = pos.offset(b || rotation == Rotation.CLOCKWISE_90 ? 15 - getsWalls : getsWalls, 0, b || rotation == Rotation.COUNTERCLOCKWISE_90 ? 15 - getsWalls : getsWalls);
        room.placeInWorld(level, pos, pos, defaultSettings.setRotation(rotation), random, 2);
    }
    public static void setBlock(WorldGenLevel level, BlockPos pos, BlockState block) {level.setBlock(pos, block, 3);}
    public static void genDoors(WorldGenLevel level, RandomSource random, BlockPos pos, byte roomShape, @Nullable StructureTemplate door) {
        boolean b = door == null;
        if(hasEastDoor(roomShape)) placeStructure(b ? (random.nextInt(25) == 0 ? doors[random.nextInt(doors.length)] : doorway) : door, level, random, pos.offset(15, 1, 6), Rotation.CLOCKWISE_90);
        if(hasSouthDoor(roomShape)) placeStructure(b ? (random.nextInt(25) == 0 ? doors[random.nextInt(doors.length)] : doorway) : door, level, random, pos.offset(9, 1, 15), Rotation.CLOCKWISE_180);
        if(getsWestEntrance(roomShape)) placeStructure(b ? (random.nextInt(25) == 0 ? doors[random.nextInt(doors.length)] : doorway) : door, level, random, pos.offset(0, 1, 9), Rotation.COUNTERCLOCKWISE_90);
        if(getsNorthEntrance(roomShape)) placeStructure(b ? (random.nextInt(25) == 0 ? doors[random.nextInt(doors.length)] : doorway) : door, level, random, pos.offset(6, 1, 0), Rotation.NONE);
    }
    public static void genWalls(WorldGenLevel level, RandomSource random, BlockPos pos, BlockState wallBlock, byte roomShape, @Nullable StructureTemplate door) {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 16; x++) {
                setBlock(level, pos.offset(x, y, 0), wallBlock);
                setBlock(level, pos.offset(x, y, 15), wallBlock);
            }
            for(int z = 1; z < 15; z++) {
                setBlock(level, pos.offset(0, y, z), wallBlock);
                setBlock(level, pos.offset(15, y, z), wallBlock);
            }
        }
        genDoors(level, random, pos, roomShape, door);
    }

    public static void placeStructure(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
        structure.placeInWorld(level, pos, pos, defaultSettings.copy().setRotation(rotation), random, 2);
    }

    class Room {
        public final StructureTemplate room;
        public final BlockState wallBlock;
        public Room(StructureTemplateManager manager, String location, @Nullable BlockState wallBlock) {
            room = manager.getOrCreate(new ResourceLocation(JITL.MODID, location));
            this.wallBlock = wallBlock;
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation, byte roomShape) {
            boolean b = wallBlock != null;
            if(b) genWalls(level, random, pos, wallBlock, roomShape, null);
            placeRoom(room, level, random, pos, rotation, b ? (byte)1 : 0);
        }
    }
    class VerticalRoom {
        public final StructureTemplate room, door;
        public final BlockState wallBlock;
        public VerticalRoom(StructureTemplateManager manager, String location, @Nullable BlockState wallBlock, @Nullable StructureTemplate upperRoomDoor) {
            room = manager.getOrCreate(new ResourceLocation(JITL.MODID, location));
            this.wallBlock = wallBlock;
            this.door = upperRoomDoor;
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation, byte roomShape, byte upperRoomShape) {
            boolean b = wallBlock != null;
            if(b) {
                genWalls(level, random, pos, wallBlock, roomShape, null);
                if(door != null)
                    genWalls(level, random, pos.offset(0, 8, 0), wallBlock, upperRoomShape, door);
            }
            placeRoom(room, level, random, pos, rotation, b ? (byte)1 : 0);
        }
    }
    class BigRoom {
        public final StructureTemplate room;
        public BigRoom(StructureTemplateManager manager, String room) {
            this.room = manager.getOrCreate(new ResourceLocation(JITL.MODID, room));
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, int xPart, int zPart) {
            if(xPart == 0) {
                if(zPart == 0) placeRoom(room, level, random, pos, Rotation.NONE, (byte)0);
                else placeRoom(room, level, random, pos, Rotation.COUNTERCLOCKWISE_90, (byte)0);
            } else {
                if(zPart == 0) placeRoom(room, level, random, pos, Rotation.CLOCKWISE_90, (byte)0);
                else placeRoom(room, level, random, pos, Rotation.CLOCKWISE_180, (byte)0);
            }
        }
    }
}