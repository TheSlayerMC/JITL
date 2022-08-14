package net.jitl.core.init;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class JBlockProperties {

    public static BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();


}
