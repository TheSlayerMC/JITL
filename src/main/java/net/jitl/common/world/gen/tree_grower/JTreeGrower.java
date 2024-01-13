package net.jitl.common.world.gen.tree_grower;

import net.jitl.core.data.world_gen.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class JTreeGrower {

    public static final TreeGrower EUCA_GOLD_TREE_GROWER = new TreeGrower("euca_gold_tree", Optional.empty(), Optional.of(JConfiguredFeatures.EUCA_GOLD_TREE), java.util.Optional.empty());
    public static final TreeGrower EUCA_GREEN_TREE_GROWER = new TreeGrower("euca_green_tree", Optional.empty(), Optional.of(JConfiguredFeatures.EUCA_GREEN_TREE), java.util.Optional.empty());

    public static final TreeGrower DEPTHS_TREE_GROWER = new TreeGrower("depths_tree", Optional.empty(), Optional.of(JConfiguredFeatures.DEPTHS_TREE), java.util.Optional.empty());

    public static final TreeGrower BOGWOOD_TREE_GROWER = new TreeGrower("bogwood_tree", Optional.empty(), Optional.of(JConfiguredFeatures.CORBA_SWAMP_TREE), java.util.Optional.empty());
    public static final TreeGrower CORBA_TREE_GROWER = new TreeGrower("corba_tree", Optional.of(JConfiguredFeatures.CORBA_TREE_LARGE), Optional.of(JConfiguredFeatures.CORBA_TREE_MEDIUM), java.util.Optional.empty());

    public static final TreeGrower TERRRANIAN_TREE_GROWER = new TreeGrower("terranian_tree", Optional.empty(), Optional.of(JConfiguredFeatures.MEGA_TERRANIAN_TREE), java.util.Optional.empty());

}
