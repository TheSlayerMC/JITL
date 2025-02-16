package net.jitl.common.block.entity;

import net.jitl.common.block.BitterwoodCampfireBlock;
import net.jitl.core.init.internal.JBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.Optional;

public class BitterwoodCampfireBlockEntity extends BlockEntity implements Clearable {
   private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
   private final int[] cookingProgress = new int[4];
   private final int[] cookingTime = new int[4];
   private final RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);

   public BitterwoodCampfireBlockEntity(BlockPos pPos, BlockState pBlockState) {
      super(JBlockEntities.BITTERWOOD_CAMPFIRE.get(), pPos, pBlockState);
   }

   public static void cookTick(Level pLevel, BlockPos pPos, BlockState pState, BitterwoodCampfireBlockEntity pBlockEntity) {
      boolean flag = false;

      for (int i = 0; i < pBlockEntity.items.size(); i++) {
         ItemStack itemstack = pBlockEntity.items.get(i);
         if (!itemstack.isEmpty()) {
            flag = true;
            pBlockEntity.cookingProgress[i]++;
            if (pBlockEntity.cookingProgress[i] >= pBlockEntity.cookingTime[i]) {
               SingleRecipeInput singlerecipeinput = new SingleRecipeInput(itemstack);
               ItemStack itemstack1 = pBlockEntity.quickCheck
                       .getRecipeFor(singlerecipeinput, pLevel)
                       .map(p_335297_ -> p_335297_.value().assemble(singlerecipeinput, pLevel.registryAccess()))
                       .orElse(itemstack);
               if (itemstack1.isItemEnabled(pLevel.enabledFeatures())) {
                  Containers.dropItemStack(pLevel, (double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ(), itemstack1);
                  pBlockEntity.items.set(i, ItemStack.EMPTY);
                  pLevel.sendBlockUpdated(pPos, pState, pState, 3);
                  pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pState));
               }
            }
         }
      }

      if (flag) {
         setChanged(pLevel, pPos, pState);
      }
   }

   public static void cooldownTick(Level pLevel, BlockPos pPos, BlockState pState, BitterwoodCampfireBlockEntity pBlockEntity) {
      boolean flag = false;

      for(int i = 0; i < pBlockEntity.items.size(); ++i) {
         if (pBlockEntity.cookingProgress[i] > 0) {
            flag = true;
            pBlockEntity.cookingProgress[i] = Mth.clamp(pBlockEntity.cookingProgress[i] - 2, 0, pBlockEntity.cookingTime[i]);
         }
      }

      if (flag) {
         setChanged(pLevel, pPos, pState);
      }

   }

   public static void particleTick(Level pLevel, BlockPos pPos, BlockState pState, BitterwoodCampfireBlockEntity pBlockEntity) {
      RandomSource randomsource = pLevel.random;
      if (randomsource.nextFloat() < 0.11F) {
         for (int i = 0; i < randomsource.nextInt(2) + 2; i++) {
            BitterwoodCampfireBlock.makeParticles(pLevel, pPos, pState.getValue(CampfireBlock.SIGNAL_FIRE), false);
         }
      }

      int l = pState.getValue(BitterwoodCampfireBlock.FACING).get2DDataValue();

      for (int j = 0; j < pBlockEntity.items.size(); j++) {
         if (!pBlockEntity.items.get(j).isEmpty() && randomsource.nextFloat() < 0.2F) {
            Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
            float f = 0.3125F;
            double d0 = (double)pPos.getX()
                    + 0.5
                    - (double)((float)direction.getStepX() * 0.3125F)
                    + (double)((float)direction.getClockWise().getStepX() * 0.3125F);
            double d1 = (double)pPos.getY() + 0.5;
            double d2 = (double)pPos.getZ()
                    + 0.5
                    - (double)((float)direction.getStepZ() * 0.3125F)
                    + (double)((float)direction.getClockWise().getStepZ() * 0.3125F);

            for (int k = 0; k < 4; k++) {
               pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 5.0E-4, 0.0);
            }
         }
      }
   }

   public NonNullList<ItemStack> getItems() {
      return this.items;
   }

   @Override
   public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
      super.loadAdditional(pTag, pRegistries);
      this.items.clear();
      ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
      if (pTag.contains("CookingTimes", 11)) {
         int[] aint = pTag.getIntArray("CookingTimes");
         System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
      }

      if (pTag.contains("CookingTotalTimes", 11)) {
         int[] aint1 = pTag.getIntArray("CookingTotalTimes");
         System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
      }

   }
   @Override
   protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
      super.saveAdditional(pTag, pRegistries);
      ContainerHelper.saveAllItems(pTag, this.items, true, pRegistries);
      pTag.putIntArray("CookingTimes", this.cookingProgress);
      pTag.putIntArray("CookingTotalTimes", this.cookingTime);
   }

   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   @Override
   public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
      CompoundTag compoundtag = new CompoundTag();
      ContainerHelper.saveAllItems(compoundtag, this.items, true, pRegistries);
      return compoundtag;
   }

   public Optional<RecipeHolder<CampfireCookingRecipe>> getCookableRecipe(ItemStack pStack) {
      return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.quickCheck.getRecipeFor(new SingleRecipeInput(pStack), this.level);
   }

   public boolean placeFood(@Nullable Entity pEntity, ItemStack pStack, int pCookTime) {
      for(int i = 0; i < this.items.size(); ++i) {
         ItemStack itemstack = this.items.get(i);
         if (itemstack.isEmpty()) {
            this.cookingTime[i] = pCookTime;
            this.cookingProgress[i] = 0;
            this.items.set(i, pStack.split(1));
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(pEntity, this.getBlockState()));
            this.markUpdated();
            return true;
         }
      }

      return false;
   }

   private void markUpdated() {
      this.setChanged();
      this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
   }

   @Override
   public void clearContent() {
      this.items.clear();
   }

   public void dowse() {
      if (this.level != null) {
         this.markUpdated();
      }
   }

   @Override
   protected void applyImplicitComponents(BlockEntity.DataComponentInput pComponentInput) {
      super.applyImplicitComponents(pComponentInput);
      pComponentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
   }

   @Override
   protected void collectImplicitComponents(DataComponentMap.Builder pComponents) {
      super.collectImplicitComponents(pComponents);
      pComponents.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
   }

   @Override
   public void removeComponentsFromTag(CompoundTag pTag) {
      pTag.remove("Items");
   }
}