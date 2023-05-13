package net.jitl.common.world.gen.senterian;

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

public class SenterianTerrain extends Feature<NoneFeatureConfiguration> {

    public static SenterianPiece[] TOP, BIG_TOP, BOTTOM, PATHS, TOP_PATHS;

    public SenterianTerrain() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos pos = context.origin();

        StructureTemplateManager manager = level.getLevel().getServer().getStructureManager();
        PATHS = new SenterianPiece[] {

        };

        TOP_PATHS = new SenterianPiece[] {

        };

        TOP = new SenterianPiece[] {

        };

        BIG_TOP = new SenterianPiece[] {

        };

        BOTTOM = new SenterianPiece[] {

        };

        this.generate(level, random, pos);

        return true;
    }

    public void generate(WorldGenLevel level, RandomSource random, BlockPos pos) {
        int bottomLayer = 100;
        int topLayer = bottomLayer + 12;//sits 12 blocks taller (structures can then be 16x16x16 but top of the bottom structure is reserved for pathing)
        int rarity = 2;
        int big_rarity = 6;

        BlockPos topPos = new BlockPos(pos.getX(), topLayer, pos.getZ());
        BlockPos bottomPos = new BlockPos(pos.getX(), bottomLayer, pos.getZ());

        /*TOP_PATHS[random.nextInt(TOP_PATHS.length)].gen(level, random, topPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) == 0)
            TOP[random.nextInt(TOP.length)].gen(level, random, topPos, Rotation.getRandom(random));

        PATHS[random.nextInt(PATHS.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
        if(random.nextInt(rarity) == 0)
            BOTTOM[random.nextInt(BOTTOM.length)].gen(level, random, bottomPos, Rotation.getRandom(random));*/

        //if(random.nextInt(big_rarity) == 0)
            //BIG_TOP[random.nextInt(BIG_TOP.length)].gen(level, random, topPos, Rotation.getRandom(random));
    }

    public static void placePiece(StructureTemplate structure, WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
        structure.placeInWorld(level, pos, pos, StructureRegistry.defaultSettings.setRotation(rotation), random, 2);
    }

    private static class SenterianPiece {

        public final StructureTemplate part;

        public SenterianPiece(StructureTemplateManager manager, String location) {
            part = manager.getOrCreate(JITL.rl(location));
        }

        public void gen(WorldGenLevel level, RandomSource random, BlockPos pos, Rotation rotation) {
            placePiece(part, level, random, pos, rotation);
        }
    }
}