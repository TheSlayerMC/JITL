package net.jitl.common.world.gen.cloudia;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.StructureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;

public class CloudiaTerrain extends Feature<NoneFeatureConfiguration> {

    public static CloudiaPiece[] top, bottom, paths;

    public CloudiaTerrain() {super(NoneFeatureConfiguration.CODEC);}

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return place(NoneFeatureConfiguration.INSTANCE, context.level(), null, context.random(), context.origin());
    }

    @Override
    public boolean place(@NotNull NoneFeatureConfiguration c, WorldGenLevel level, @NotNull ChunkGenerator g, @NotNull RandomSource random, @NotNull BlockPos pos) {
        StructureTemplateManager manager = level.getLevel().getServer().getStructureManager();
        paths = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/crossroads/cross"),
                new CloudiaPiece(manager, "cloudia/crossroads/straight"),
                new CloudiaPiece(manager, "cloudia/crossroads/tee"),
                new CloudiaPiece(manager, "cloudia/crossroads/elbow")
        };

        top = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/top/tall_house2"),
                new CloudiaPiece(manager, "cloudia/top/tall_house4")
        };

        bottom = new CloudiaPiece[] {
                new CloudiaPiece(manager, "cloudia/bottom/tall_house1"),
                new CloudiaPiece(manager, "cloudia/bottom/island_1"),
                new CloudiaPiece(manager, "cloudia/bottom/tall_house3"),
                new CloudiaPiece(manager, "cloudia/bottom/melon_farm"),
        };

        boolean hasSpawned = false;

        if(!hasSpawned) {
            generate(level, random, pos);
            hasSpawned = true;
        }

        return hasSpawned;
    }

    public boolean generate(WorldGenLevel level, RandomSource random, BlockPos pos) {
        int bottomLayer = 82;
        int topLayer = bottomLayer + 12;//sits 12 blocks taller (structures can then be 16x16x16 but top of the bottom structure is reserved for pathing)
        int emptyRarity = 2;

        BlockPos topPos = new BlockPos(pos.getX(), topLayer, pos.getZ());
        BlockPos bottomPos = new BlockPos(pos.getX(), bottomLayer, pos.getZ());

        int hallwayRarity = 2;

        if (random.nextInt(hallwayRarity) == 0) {
            paths[random.nextInt(paths.length)].gen(level, random, topPos, Rotation.getRandom(random));
        }

        if (random.nextInt(emptyRarity) != 0) {
            top[random.nextInt(top.length)].gen(level, random, topPos, Rotation.getRandom(random));
        }

        if (random.nextInt(hallwayRarity) == 0) {
            paths[random.nextInt(paths.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
        }

        if (random.nextInt(emptyRarity) != 0) {
            bottom[random.nextInt(bottom.length)].gen(level, random, bottomPos, Rotation.getRandom(random));
        }

        return true;
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