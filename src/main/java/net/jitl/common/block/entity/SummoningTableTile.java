package net.jitl.common.block.entity;

import net.jitl.common.block.entity.container.SummoningTableContainer;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SummoningTableTile extends RandomizableContainerBlockEntity implements MenuProvider, Nameable {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(7, ItemStack.EMPTY);

    public SummoningTableTile(BlockPos pos, BlockState state) {
        super(JBlockEntities.SUMMONING_TABLE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SummoningTableTile entity) {
        ItemStack slot1 = entity.inventory.get(0);
        ItemStack slot2 = entity.inventory.get(1);
        ItemStack slot3 = entity.inventory.get(2);
        ItemStack slot4 = entity.inventory.get(3);
        ItemStack slot5 = entity.inventory.get(4);
        ItemStack slot6 = entity.inventory.get(5);
        ItemStack slot7 = entity.inventory.get(6);

        if(slot1.getItem() != null && slot2 != null && slot3 != null && slot4 != null && slot5 != null && slot6 != null && slot7 != null) {
            if(entity.areItemsInSlots(
                    JItems.SPAWNER_BAR.get(),
                    JItems.ASH.get(),
                    JItems.SPAWNER_BAR.get(),
                    JItems.HELL_SHARDS.get(),
                    JItems.SPAWNER_BAR.get(),
                    JItems.ASH.get(),
                    JItems.SPAWNER_BAR.get())){
                entity.summonItem(new ItemStack(JItems.BLOOD_KNIFE.get()));
            }
        }
    }

    public void summonItem(ItemStack orb) {
        this.inventory.get(0).shrink(1);
        this.inventory.get(1).shrink(1);
        this.inventory.get(2).shrink(1);
        this.inventory.get(3).shrink(1);
        this.inventory.get(4).shrink(1);
        this.inventory.get(5).shrink(1);
        this.inventory.get(6).shrink(1);

        this.inventory.set(3, orb);

        assert this.level != null;
        this.level.playSound(null, this.getBlockPos(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        addParticles();
    }

    public void addParticles() {
        RandomSource r = RandomSource.create();
        assert this.level != null;
        if (!this.level.isClientSide) {
            for (int i = 0; i < 20; i++)
                this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        this.getBlockPos().getX() - Mth.nextDouble(r, -0.45D, 0.75D),
                        this.getBlockPos().getY() + Mth.nextDouble(r, 0.5D, 2.0D),
                        this.getBlockPos().getZ() - Mth.nextDouble(r, -0.45D, 0.75D),
                        r.nextGaussian() * 0.05D, 0.15D, r.nextGaussian() * 0.05D);
        }
    }

    public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
        return this.inventory.get(0).getItem() == s && this.inventory.get(1).getItem() == s1 && this.inventory.get(2).getItem() == s2 && this.inventory.get(3).getItem() == s3 && this.inventory.get(4).getItem() == s4 && this.inventory.get(5).getItem() == s5 && this.inventory.get(6).getItem() == s6;
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        ContainerHelper.saveAllItems(compound, this.inventory);
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> item) {
        this.inventory = item;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("jitl.tile.summon_table");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerID, @NotNull Inventory inventory) {
        return new SummoningTableContainer(containerID, inventory, this);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {
        return new SummoningTableContainer(containerID, inventory, this);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public @NotNull ItemStack removeItem(int pIndex, int pCount) {
        return ContainerHelper.removeItem(this.inventory, pIndex, pCount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.inventory, pIndex);
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        this.inventory.set(pIndex, pStack);
        if(pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }
}
