package net.jitl.common.block.portal.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record PortalCoordinatesContainer(ResourceKey<Level> fromDim, BlockPos portalPos) { }
