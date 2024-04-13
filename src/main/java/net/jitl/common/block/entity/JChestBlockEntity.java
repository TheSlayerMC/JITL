package net.jitl.common.block.entity;

import net.jitl.common.block.JChestBlock;
import net.jitl.core.init.internal.JBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JChestBlockEntity extends ChestBlockEntity {
    
    private final ChestLidController chestLidController = new ChestLidController();

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

        @Override
        protected void onOpen(@NotNull Level level2, @NotNull BlockPos blockPos2, @NotNull BlockState blockState2) {
            JChestBlockEntity.playSound(level2, blockPos2, blockState2, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(@NotNull Level level1, @NotNull BlockPos blockPos1, @NotNull BlockState blockState1) {
            JChestBlockEntity.playSound(level1, blockPos1, blockState1, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(@NotNull Level level3, @NotNull BlockPos blockPos3, @NotNull BlockState blockState3, int i, int int1) {
            JChestBlockEntity.this.signalOpenCount(level3, blockPos3, blockState3, i, int1);
        }

        @Override
        protected boolean isOwnContainer(Player player1) {
            if(!(player1.containerMenu instanceof ChestMenu)) {
                return false;
            } else {
                Container container = ((ChestMenu)player1.containerMenu).getContainer();
                return container == JChestBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(JChestBlockEntity.this);
            }
        }
    };

    protected JChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public JChestBlockEntity(BlockPos worldPosition, BlockState blockState) {
        this(JBlockEntities.JCHEST.get(), worldPosition, blockState);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.setItems(NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY));
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.getItems());
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if(!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.getItems());
        }
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, JChestBlockEntity blockEntity) {
        blockEntity.chestLidController.tickLid();
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if(id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(@NotNull Player player) {
        if(!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if(!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    @NotNull
    @Override
    protected AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return ChestMenu.threeRows(id, player, this);
    }

    @Override
    public void recheckOpen() {
        if(!this.remove)
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
    }

    private static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        ChestType chesttype = state.getValue(ChestBlock.TYPE);
        if (chesttype != ChestType.LEFT) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.5D;
            double d2 = (double)pos.getZ() + 0.5D;
            if (chesttype == ChestType.RIGHT) {
                Direction direction = ChestBlock.getConnectedDirection(state);
                d0 += (double)direction.getStepX() * 0.5D;
                d2 += (double)direction.getStepZ() * 0.5D;
            }
            level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }
}