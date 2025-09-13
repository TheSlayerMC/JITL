package net.jitl.common.world.gen.cloudia;

import net.jitl.core.init.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class CloudiaTerrain extends Feature<NoneFeatureConfiguration> {

    public static CloudiaPiece[] TOP, BOTTOM, PATHS, TOP_PATHS, VILLAGE;
    public static BigRoom[] BIG_TOP;

    public static final StructurePlaceSettings defaultSettings = new StructurePlaceSettings().setIgnoreEntities(false).setFinalizeEntities(true).setLiquidSettings(LiquidSettings.APPLY_WATERLOGGING);

    public CloudiaTerrain() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos pos = context.origin();

        StructureTemplateManager manager = level.getLevel().getServer().getStructureManager();
        PATHS = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/crossroads/cross"),
                new CloudiaPiece(manager, "cloudia/crossroads/straight"),
                new CloudiaPiece(manager, "cloudia/crossroads/tee"),
                new CloudiaPiece(manager, "cloudia/crossroads/elbow"),
                new CloudiaPiece(manager, "cloudia/crossroads/curve"),
                new CloudiaPiece(manager, "cloudia/crossroads/round"),
                new CloudiaPiece(manager, "cloudia/crossroads/round_large")
        };

        TOP_PATHS = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/crossroads/top_cross"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_straight"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_tee"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_elbow"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_curve"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_round"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_round_large")
        };

        TOP = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/top/tall_house2"),
                new CloudiaPiece(manager, "cloudia/top/tall_house4"),
        };

        BIG_TOP = new BigRoom[] {
                new BigRoom(manager, "cloudia/top/big_base")
        };

        BOTTOM = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/bottom/tall_house1"),
                new CloudiaPiece(manager, "cloudia/bottom/island_1"),
                new CloudiaPiece(manager, "cloudia/bottom/tall_house3"),
                new CloudiaPiece(manager, "cloudia/bottom/melon_farm"),
        };

        VILLAGE = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/village/starlight_village"),
                new CloudiaPiece(manager, "cloudia/village/starlight_village_2"),
        };

        this.generate(level, random, pos);
        return true;
    }

    public void generate(WorldGenLevel level, RandomSource random, BlockPos pos) {
        int bottomLayer = 100;
        int topLayer = bottomLayer + 12;//sits 12 blocks taller (structures can then be 16x16x16 but top of the bottom structure is reserved for pathing)
        int rarity = 2;
        int village_rarity = 30;
        int chunkX = pos.getX() / 16, chunkZ = pos.getZ() / 16;

        BlockPos topPos = pos.offset(0, topLayer, 0);
        BlockPos bottomPos = pos.offset(0, bottomLayer, 0);

        TOP_PATHS[random.nextInt(TOP_PATHS.length)].gen(level, random, topPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) == 0)
            TOP[random.nextInt(TOP.length)].gen(level, random, topPos, Rotation.getRandom(random));

        PATHS[random.nextInt(PATHS.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) == 0)
            BOTTOM[random.nextInt(BOTTOM.length)].gen(level, random, bottomPos, Rotation.getRandom(random));

        if(random.nextInt(village_rarity) == 0)
            VILLAGE[random.nextInt(VILLAGE.length)].gen(level, random, topPos, Rotation.getRandom(random));
    }

    public static void placePiece(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
        boolean b = rotation == Rotation.CLOCKWISE_180;
        pos = pos.offset(b || rotation == Rotation.CLOCKWISE_90 ? 15 : 0, 0, b || rotation == Rotation.COUNTERCLOCKWISE_90 ? 15 : 0);
        structure.placeInWorld(level, pos, pos, defaultSettings.setRotation(rotation), random, 2);
    }

    private static class CloudiaPiece {

        public final StructureTemplate part;

        public CloudiaPiece(StructureTemplateManager manager, String location) {
            part = manager.getOrCreate(JITL.rl(location));
        }

        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
            placePiece(part, level, random, pos, rotation);
        }
    }

    static class BigRoom {
        public final StructureTemplate room;
        public BigRoom(StructureTemplateManager manager, String room) {
            this.room = manager.getOrCreate(ResourceLocation.fromNamespaceAndPath(JITL.MOD_ID, room));
        }
        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, int xPart, int zPart) {
            if(xPart == 0) {
                if(zPart == 0) placePiece(room, level, random, pos, Rotation.NONE);
                else placePiece(room, level, random, pos, Rotation.COUNTERCLOCKWISE_90);
            } else {
                if(zPart == 0) placePiece(room, level, random, pos, Rotation.CLOCKWISE_90);
                else placePiece(room, level, random, pos, Rotation.CLOCKWISE_180);
            }
        }
    }
}