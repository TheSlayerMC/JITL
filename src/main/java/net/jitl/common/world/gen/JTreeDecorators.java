package net.jitl.common.world.gen;

import net.jitl.common.world.gen.terrania.TerranianLeaveVineDecorator;
import net.jitl.common.world.gen.terrania.TerranianTrunkVineDecorator;
import net.jitl.common.world.gen.tree_grower.decorators.FrozenTreeDecorator;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, JITL.MODID);

    public static final RegistryObject<TreeDecoratorType<FrozenTreeDecorator>> FROZEN_DECORATOR = REGISTRY.register("frozen_tree_decorator", () -> new TreeDecoratorType<>(FrozenTreeDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<TerranianLeaveVineDecorator>> TERRANIAN_DECORATOR = REGISTRY.register("terranian_tree_decorator", () -> new TreeDecoratorType<>(TerranianLeaveVineDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<TerranianTrunkVineDecorator>> TERRANIAN_TRUNK_DECORATOR = REGISTRY.register("terranian_trunk_tree_decorator", () -> new TreeDecoratorType<>(TerranianTrunkVineDecorator.CODEC));

}
