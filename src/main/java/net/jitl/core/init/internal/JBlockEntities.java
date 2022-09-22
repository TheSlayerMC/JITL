package net.jitl.core.init.internal;

import net.jitl.common.block.entity.GrindstoneEntity;
import net.jitl.core.init.JITL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JITL.MODID);

    public static final RegistryObject<BlockEntityType<GrindstoneEntity>> GRINDSTONE = REGISTRY.register("grindstone",
            () -> BlockEntityType.Builder.of(GrindstoneEntity::new, JBlocks.GRINDSTONE.get()).build(null));

}
