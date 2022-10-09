package net.jitl.core.init.internal;

import net.jitl.core.helper.internal.EmptyContainer;
import net.jitl.core.init.JITL;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JAttributes {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, JITL.MODID);

    //TODO: During development, default values are set so we don't have to keep equipping curios to test essence. In public builds, they should be set to zero.
    public static final RegistryObject<Attribute> MAX_ESSENCE =
            REGISTRY.register("max_essence", () -> new RangedAttribute("jitl.max_essence", 1.0F, 0.0F, 20.0F).setSyncable(true));

    public static final RegistryObject<Attribute> ESSENCE_REGEN_SPEED =
            REGISTRY.register("essence_regen_speed", () -> new RangedAttribute("jitl.essence_regen_speed", 0.012F, 0.0F, 0.065F).setSyncable(true));

    public static final RegistryObject<Attribute> ESSENCE_BURNOUT =
            REGISTRY.register("essence_burnout_time", () -> new RangedAttribute("jitl.essence_burnout_time", 5.0F, 0.0F, 50.0F).setSyncable(false));
}