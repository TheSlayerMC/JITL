package net.jitl.core.data.world_gen;

import net.jitl.common.world.dimension.Dimensions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.timeline.Timeline;

import java.util.Optional;

public class JDimensionTypes {

    public static void bootstrap(BootstrapContext<DimensionType> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<Timeline> timelines = context.lookup(Registries.TIMELINE);
        HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);

        context.register(Dimensions.EUCA_TYPE, new DimensionType(true, true, false, false, 1D, 0, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.CLOUD_HEIGHT, 0F).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.BOIL_TYPE, new DimensionType(true, true, false, false, 1D, 0, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_NETHER), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.CLOUD_HEIGHT, 150F).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.WATER_EVAPORATES, true).set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true).set(EnvironmentAttributes.FAST_LAVA, true).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.CORBA_TYPE, new DimensionType(true, true, false, false, 1D, -64, 384, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.2F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.CLOUD_HEIGHT, 192F).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.DEPTHS_TYPE, new DimensionType(true, false, true, false, 1D, 0, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.1F, new DimensionType.MonsterSettings(ConstantInt.of(3), 15), DimensionType.Skybox.END, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CLOUD_HEIGHT, 0F).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.CLOUDIA_TYPE, new DimensionType(true, true, false, false, 1D, -64, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CLOUD_HEIGHT, 63F).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.FROZEN_LANDS_TYPE, new DimensionType(true, true, false, false, 1D, 0, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CLOUD_HEIGHT, 150F).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.TERRANIA_TYPE, new DimensionType(true, true, false, false, 1D, 0, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CLOUD_HEIGHT, 200F).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
        context.register(Dimensions.SENTERIAN_TYPE, new DimensionType(true, false, true, false, 1D, -64, 256, 256, blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD), 0.0F, new DimensionType.MonsterSettings(ConstantInt.of(3), 0), DimensionType.Skybox.OVERWORLD, CardinalLighting.Type.DEFAULT, EnvironmentAttributeMap.builder().set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES).set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, true).set(EnvironmentAttributes.CLOUD_HEIGHT, 0F).set(EnvironmentAttributes.CAN_START_RAID, false).build(), timelines.getOrThrow(TimelineTags.UNIVERSAL), Optional.empty()));
    }
}
