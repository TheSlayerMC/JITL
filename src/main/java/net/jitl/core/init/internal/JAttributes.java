package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JAttributes {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(Registries.ATTRIBUTE, JITL.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> MAX_ESSENCE =
            REGISTRY.register("max_essence", () -> new RangedAttribute("jitl.max_essence", 1.0F, 0.0F, 20.0F).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> ESSENCE_REGEN_SPEED =
            REGISTRY.register("essence_regen_speed", () -> new RangedAttribute("jitl.essence_regen_speed", 0.012F, 0.0F, 0.065F).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> ESSENCE_BURNOUT =
            REGISTRY.register("essence_burnout_time", () -> new RangedAttribute("jitl.essence_burnout_time", 5.0F, 0.0F, 50.0F).setSyncable(false));
}