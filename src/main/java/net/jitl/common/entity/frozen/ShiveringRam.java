package net.jitl.common.entity.frozen;

import net.jitl.common.entity.base.JAnimalEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.common.entity.goal.ShiveringRamEatBlockGoal;
import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class ShiveringRam extends JAnimalEntity implements Shearable {

    private static final EntityDataAccessor<Boolean> IS_SHEARED = SynchedEntityData.defineId(ShiveringRam.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_EATING = SynchedEntityData.defineId(ShiveringRam.class, EntityDataSerializers.BOOLEAN);
    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.shivering_ram.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.shivering_ram.idle");
    private final RawAnimation EAT = RawAnimation.begin().thenPlay("animation.shivering_ram.eat");

    public ShiveringRam(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, (stack) -> stack.is(JItems.FROST_FLAKE), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new ShiveringRamEatBlockGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.TEMPT_RANGE, MobStats.STANDARD_TEMPT_RANGE)
                .add(Attributes.MAX_HEALTH, MobStats.SHIVERING_RAM_HEALTH).build();
    }

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else if(isEating()) {
                return state.setAndContinue(EAT);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(JItems.FROST_FLAKE);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SHEARED, false);
        builder.define(IS_EATING, false);
    }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("Sheared", this.isSheared());
        output.putBoolean("Eating", this.isEating());
    }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setSheared(input.getBooleanOr("Sheared", false));
        this.setSheared(input.getBooleanOr("Eating", false));
    }

    public boolean isEating() {
        return this.entityData.get(IS_EATING);
    }

    public void setEating(boolean eating) {
        this.entityData.set(IS_EATING, eating);
    }

    public boolean isSheared() {
        return this.entityData.get(IS_SHEARED);
    }

    public void setSheared(boolean sheared) {
        this.entityData.set(IS_SHEARED, sheared);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        player.getItemInHand(hand);
        return super.mobInteract(player, hand);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos p, BlockState s) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }


    @Override
    public void shear(ServerLevel level, SoundSource sounds, ItemStack stack) {
        level.playSound(null, this, SoundEvents.SHEEP_SHEAR, sounds, 1.0F, 1.0F);
        this.dropFromShearingLootTable(level, JLootTables.SHIVERING_RAM_WOOL, stack, (p_405522_, p_405241_) -> {
            for(int i = 0; i < p_405241_.getCount(); ++i) {
                ItemEntity itementity = this.spawnAtLocation(p_405522_, p_405241_.copyWithCount(1), 1.0F);
                if (itementity != null) {
                    itementity.setDeltaMovement(itementity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                }
            }
        });
        this.setSheared(true);
    }

    public void ate() {
        super.ate();
        this.setSheared(false);
        if(this.isBaby()) {
            this.ageUp(60);
        }
    }

    @Nullable
    public ShiveringRam getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        return JEntities.SHIVERING_RAM_TYPE.get().create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

}
