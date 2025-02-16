package net.jitl.common.world.gen;

import net.jitl.common.world.gen.terrania.TerranianLeaveVineDecorator;
import net.jitl.common.world.gen.terrania.TerranianTrunkVineDecorator;
import net.jitl.common.world.gen.tree_grower.decorators.*;
import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, JITL.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<FrozenTreeDecorator>> FROZEN_DECORATOR = REGISTRY.register("frozen_tree_decorator", () -> new TreeDecoratorType<>(FrozenTreeDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<IceShroomTreeDecorator>> ICE_SHELF_DECORATOR = REGISTRY.register("ice_tree_decorator", () -> new TreeDecoratorType<>(IceShroomTreeDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<IcyBrushTreeDecorator>> ICY_BRUSH_TREE = REGISTRY.register("icy_brush_tree_decorator", () -> new TreeDecoratorType<>(IcyBrushTreeDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<CrystalFruitTreeDecorator>> CRYSTAL_FRUIT_DECORATOR = REGISTRY.register("crystal_fruit_tree_decorator", () -> new TreeDecoratorType<>(CrystalFruitTreeDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<CorbaSwampTreeDecorator>> CORBA_SWAMP_DECORATOR = REGISTRY.register("corba_swamp_tree_decorator", () -> new TreeDecoratorType<>(CorbaSwampTreeDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TerranianLeaveVineDecorator>> TERRANIAN_DECORATOR = REGISTRY.register("terranian_tree_decorator", () -> new TreeDecoratorType<>(TerranianLeaveVineDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TerranianTrunkVineDecorator>> TERRANIAN_TRUNK_DECORATOR = REGISTRY.register("terranian_trunk_tree_decorator", () -> new TreeDecoratorType<>(TerranianTrunkVineDecorator.CODEC));

}
