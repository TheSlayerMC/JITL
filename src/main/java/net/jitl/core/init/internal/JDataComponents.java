package net.jitl.core.init.internal;

import net.jitl.common.capability.LoreScroll;
import net.jitl.core.init.JITL;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JDataComponents {

    public static final DeferredRegister.DataComponents REGISTRY = DeferredRegister.createDataComponents(JITL.MODID);

    public static final Supplier<DataComponentType<LoreScroll>> SCROLL = registerDataComponent(
            "lore_scroll", builder -> builder.persistent(LoreScroll.LORE_CODEC).networkSynchronized(LoreScroll.LORE_STREAM_CODEC));


    public static <T> Supplier<DataComponentType<T>> registerDataComponent(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return REGISTRY.registerComponentType(id, builder);
    }
}


