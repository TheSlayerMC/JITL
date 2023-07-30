package net.jitl.common.block.entity;

import net.jitl.common.block.entity.container.JFurnaceMenu;
import net.jitl.core.init.internal.JBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class JFurnaceTile extends AbstractFurnaceBlockEntity {

    public JFurnaceTile(BlockPos worldPosition_, BlockState blockState_) {
        super(JBlockEntities.JFURNACE.get(), worldPosition_, blockState_, RecipeType.SMELTING);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.furnace");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerID, @NotNull Inventory inventory) {
        return new JFurnaceMenu(containerID, inventory, this, this.dataAccess);
    }
}
