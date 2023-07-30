package net.jitl.common.block.entity;

import net.jitl.common.block.SummoningTableBlock;
import net.jitl.common.block.entity.container.SummoningTableContainer;
import net.jitl.core.init.internal.JBlockEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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

        if(entity.startedSummon()) {
            BlockState active_state = state.setValue(SummoningTableBlock.IS_ACTIVE, Boolean.TRUE);
            level.setBlock(pos, active_state, 2);
        } else {
            BlockState active_state = state.setValue(SummoningTableBlock.IS_ACTIVE, Boolean.FALSE);
            level.setBlock(pos, active_state, 2);
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
        this.level.playSound(null, this.getBlockPos(), JSounds.LOOT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
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

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public ItemStack getItemInSlot(int slot) {
        return inventory.get(slot);
    }

    public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
        return getItemInSlot(0).getItem() == s && getItemInSlot(1).getItem() == s1 && getItemInSlot(2).getItem() == s2 && getItemInSlot(3).getItem() == s3 && getItemInSlot(4).getItem() == s4 && getItemInSlot(5).getItem() == s5 && getItemInSlot(6).getItem() == s6;
    }

    public boolean startedSummon() {
        return getItemInSlot(0) != ItemStack.EMPTY || getItemInSlot(1) != ItemStack.EMPTY || getItemInSlot(2) != ItemStack.EMPTY || getItemInSlot(3) != ItemStack.EMPTY || getItemInSlot(4) != ItemStack.EMPTY || getItemInSlot(5) != ItemStack.EMPTY || getItemInSlot(6) != ItemStack.EMPTY;
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
        //this.inventory = item;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
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
