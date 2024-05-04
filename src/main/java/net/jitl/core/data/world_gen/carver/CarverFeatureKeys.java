package net.jitl.core.data.world_gen.carver;

import net.jitl.common.world.dimension.JCarver;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class CarverFeatureKeys {

    public static final ResourceKey<ConfiguredWorldCarver<?>> FROZEN_CARVER = createKey("frozen_carver");
    public static final ResourceKey<ConfiguredWorldCarver<?>> BOIL_CARVER = createKey("boil_carver");

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        context.register(FROZEN_CARVER, JCarver.FROZEN_CARVER.get().configured(new CaveCarverConfiguration(0.2F, UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.belowTop(1)), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), holdergetter.getOrThrow(JTags.FROZEN_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
        context.register(BOIL_CARVER, JCarver.BOIL_CARVER.get().configured(new CaveCarverConfiguration(0.2F, UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.belowTop(1)), ConstantFloat.of(0.5F), VerticalAnchor.aboveBottom(10), holdergetter.getOrThrow(JTags.BOIL_CARVER_REPLACEABLES), ConstantFloat.of(1.0F), ConstantFloat.of(1.0F), ConstantFloat.of(-0.7F))));
    }

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, JITL.rl(name));
    }
}
