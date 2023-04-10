package net.jitl.common.world.gen.cloudia;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.StructureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class CloudiaTerrain extends Feature<NoneFeatureConfiguration> {

    public static CloudiaPiece[] TOP, BOTTOM, PATHS, TOP_PATHS;

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
                new CloudiaPiece(manager, "cloudia/crossroads/elbow")
        };

        TOP_PATHS = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/crossroads/top_cross"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_straight"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_tee"),
                new CloudiaPiece(manager, "cloudia/crossroads/top_elbow")
        };

        TOP = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/top/tall_house2"),
                new CloudiaPiece(manager, "cloudia/top/tall_house4")
        };

        BOTTOM = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/bottom/tall_house1"),
                new CloudiaPiece(manager, "cloudia/bottom/island_1"),
                new CloudiaPiece(manager, "cloudia/bottom/tall_house3"),
                new CloudiaPiece(manager, "cloudia/bottom/melon_farm"),
        };

        this.generate(level, random, pos);

        return true;
    }

    public void generate(WorldGenLevel level, RandomSource random, BlockPos pos) {
        int bottomLayer = 100;
        int topLayer = bottomLayer + 12;//sits 12 blocks taller (structures can then be 16x16x16 but top of the bottom structure is reserved for pathing)
        int rarity = 2;

        BlockPos topPos = new BlockPos(pos.getX(), topLayer, pos.getZ());
        BlockPos bottomPos = new BlockPos(pos.getX(), bottomLayer, pos.getZ());

        TOP_PATHS[random.nextInt(TOP_PATHS.length)].gen(level, random, topPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) != 0)
            TOP[random.nextInt(TOP.length)].gen(level, random, topPos, Rotation.getRandom(random));

        PATHS[random.nextInt(PATHS.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) != 0)
            BOTTOM[random.nextInt(BOTTOM.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
    }

    public static void placePiece(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
        structure.placeInWorld(level, pos, pos, StructureRegistry.defaultSettings.setRotation(rotation), random, 2);
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
}