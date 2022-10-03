package net.jitl.common.world.gen;

import net.jitl.common.world.gen.treedecorator.*;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, JITL.MODID);

    public static final RegistryObject<TreeDecoratorType<CharredBrushTreeDecorator>> CHARRED_BRUSH_TREE_DECORATOR = REGISTRY.register("charred_brush_tree_decorator", () -> new TreeDecoratorType<>(CharredBrushTreeDecorator.CODEC));
}
