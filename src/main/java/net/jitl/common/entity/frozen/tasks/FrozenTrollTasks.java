package net.jitl.common.entity.frozen.tasks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.jitl.common.entity.frozen.FrozenTrollEntity;
import net.jitl.core.init.internal.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrozenTrollTasks {

    public static final Item BARTERING_ITEM = JItems.RIMESTONE.get();

    public static Brain<?> makeBrain(FrozenTrollEntity frozenTrollEntity, Brain<FrozenTrollEntity> brain) {
        initCoreActivity(brain);
        initAdmireItemActivity(brain);
        initIdleActivity(brain);
        initFightActivity(frozenTrollEntity, brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    public static boolean isLovedItem(ItemStack item) {
        return Objects.equals(item, new ItemStack(JItems.RIMESTONE.get()));
    }

    private static void initCoreActivity(Brain<FrozenTrollEntity> brain_) {
        brain_.addActivity(Activity.CORE, 0, ImmutableList.of(
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                InteractWithDoor.create(),
                FrozenTrollStopHoldingItemIfNoLongerAdmiring.create(),
                FrozenTrollStartAdmiringItemTask.create(119),
                StopBeingAngryIfTargetDead.create()));
    }

    private static void initAdmireItemActivity(Brain<FrozenTrollEntity> brain_) {
        brain_.addActivityAndRemoveMemoryWhenStopped(Activity.ADMIRE_ITEM, 10, ImmutableList.of(
                GoToWantedItem.create(FrozenTrollTasks::isNotHoldingLovedItemInOffHand, 1.0F, true, 9),
                FrozenTrollStopAdmiringIfItemTooFarAway.create(9),
                FrozenTrollStopReachingItemTask.create(200, 200)),
                MemoryModuleType.ADMIRING_ITEM);
    }

    private static void initIdleActivity(Brain<FrozenTrollEntity> brain_) {
        brain_.addActivity(Activity.IDLE, 10, ImmutableList.of(
                SetEntityLookTarget.create(FrozenTrollTasks::isPlayerHoldingLovedItem, 14.0F),
                StartAttacking.create(FrozenTrollEntity::isAlive, FrozenTrollTasks::findNearestValidAttackTarget),
                new FrozenTrollCongregateTask(),
                createIdleLookBehaviors(),
                createIdleMovementBehaviors(),
                SetLookAndInteract.create(EntityType.PLAYER, 4)));
    }

    private static void initFightActivity(FrozenTrollEntity frozenTrollEntity, Brain<FrozenTrollEntity> pBrain) {
        pBrain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, ImmutableList.<BehaviorControl<? super FrozenTrollEntity>>of(
                StopAttackingIfTargetInvalid.create((p_34981_) -> {
            return !isNearestValidAttackTarget(frozenTrollEntity, p_34981_);
        }), SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.4F), MeleeAttack.create(20)), MemoryModuleType.ATTACK_TARGET);
    }

    private static RunOne<FrozenTrollEntity> createIdleLookBehaviors() {
        return new RunOne<>(ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 1), Pair.of(SetEntityLookTarget.create(JEntities.FROZEN_TROLL_TYPE.get(), 8.0F), 1), Pair.of(SetEntityLookTarget.create(8.0F), 1), Pair.of(new DoNothing(30, 60), 1)));
    }

    private static RunOne<FrozenTrollEntity> createIdleMovementBehaviors() {
        return new RunOne<>(ImmutableList.of(
                Pair.of(RandomStroll.stroll(1.2F), 2),
                Pair.of(InteractWith.of(JEntities.FROZEN_TROLL_TYPE.get(), 8, MemoryModuleType.INTERACTION_TARGET, 1.2F, 2), 2),
                Pair.of(new DoNothing(30, 60), 1)));
    }

    private static boolean isNearestValidAttackTarget(FrozenTrollEntity frozenTrollEntity, LivingEntity livingEntity_) {
        return findNearestValidAttackTarget(frozenTrollEntity).filter((livingEntity6_) -> livingEntity6_ == livingEntity_).isPresent();
    }

    private static Optional<? extends LivingEntity> findNearestValidAttackTarget(FrozenTrollEntity frozenTrollEntity) {
        Brain<FrozenTrollEntity> brain = frozenTrollEntity.getBrain();
        Optional<LivingEntity> optional = BehaviorUtils.getLivingEntityFromUUIDMemory(frozenTrollEntity, MemoryModuleType.ANGRY_AT);
        if (optional.isPresent() && Sensor.isEntityAttackable(frozenTrollEntity, optional.get())) {
            return optional;
        } else {
            if (brain.hasMemoryValue(MemoryModuleType.UNIVERSAL_ANGER)) {
                Optional<Player> optional1 = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER);
                if (optional1.isPresent()) {
                    return optional1;
                }
            }

            Optional<Mob> optional3 = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_NEMESIS);
            if (optional3.isPresent()) {
                return optional3;
            } else {
                Optional<Player> optional2 = brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
                return optional2.isPresent() && Sensor.isEntityAttackable(frozenTrollEntity, optional2.get()) ? optional2 : Optional.empty();
            }
        }
    }

    protected static void stopHoldingOffHandItem(FrozenTrollEntity frozenTrollEntity, boolean boolean_) {
        ItemStack itemstack = frozenTrollEntity.getItemInHand(InteractionHand.OFF_HAND);
        frozenTrollEntity.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        boolean flag = itemstack.getItem() == BARTERING_ITEM;
        if (boolean_ && flag) {
            throwItems(frozenTrollEntity, getBarterResponseItems(frozenTrollEntity));
        } else if (!flag) {
            boolean flag1 = frozenTrollEntity.equipItemIfPossible(itemstack).isEmpty();
            if (!flag1) {
                putInInventory(frozenTrollEntity, itemstack);
            }
        }
    }

    private static void putInInventory(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        ItemStack itemstack = frozenTrollEntity.addToInventory(itemStack_);
        throwItemsTowardRandomPos(frozenTrollEntity, Collections.singletonList(itemstack));
    }

    private static void throwItems(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_) {
        Optional<Player> optional = frozenTrollEntity.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
        if (optional.isPresent()) {
            throwItemsTowardPlayer(frozenTrollEntity, optional.get(), list_);
        } else {
            throwItemsTowardRandomPos(frozenTrollEntity, list_);
        }

    }

    private static void throwItemsTowardRandomPos(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_) {
        throwItemsTowardPos(frozenTrollEntity, list_, getRandomNearbyPos(frozenTrollEntity));
    }

    private static void throwItemsTowardPlayer(FrozenTrollEntity frozenTrollEntity, Player playerEntity_, List<ItemStack> list_) {
        throwItemsTowardPos(frozenTrollEntity, list_, playerEntity_.position());
    }

    private static void throwItemsTowardPos(FrozenTrollEntity frozenTrollEntity, List<ItemStack> list_, Vec3 vector3d_) {
        if (!list_.isEmpty()) {
            frozenTrollEntity.swing(InteractionHand.OFF_HAND);

            for (ItemStack itemstack : list_) {
                BehaviorUtils.throwItem(frozenTrollEntity, itemstack, vector3d_.add(0.0D, 1.0D, 0.0D));
            }
        }

    }

    private static List<ItemStack> getBarterResponseItems(FrozenTrollEntity frozenTrollEntity) {
        LootTable loottable = Objects.requireNonNull(frozenTrollEntity.level().getServer()).getLootData().getLootTable(JLootTables.FROZEN_TROLL_TRADES);
        List<ItemStack> list = loottable.getRandomItems((new LootParams.Builder((ServerLevel)frozenTrollEntity.level())).withParameter(LootContextParams.THIS_ENTITY, frozenTrollEntity).create(LootContextParamSets.PIGLIN_BARTER));
        return list;
    }

    private static boolean doesntSeeAnyPlayerHoldingLovedItem(LivingEntity livingEntity_) {
        return !seesPlayerHoldingLovedItem(livingEntity_);
    }

    public static void pickUpItem(FrozenTrollEntity frozenTrollEntity, ItemEntity itemEntity_) {
        stopWalking(frozenTrollEntity);
        ItemStack itemstack;
        if (itemEntity_.getItem().getItem() == Items.GOLD_NUGGET) {
            frozenTrollEntity.take(itemEntity_, itemEntity_.getItem().getCount());
            itemstack = itemEntity_.getItem();
            itemEntity_.remove(Entity.RemovalReason.DISCARDED);
        } else {
            frozenTrollEntity.take(itemEntity_, 1);
            itemstack = removeOneItemFromItemEntity(itemEntity_);
        }
        if (isLovedItem(itemstack)) {
            frozenTrollEntity.getBrain().eraseMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
            holdInOffhand(frozenTrollEntity, itemstack);
            admireGoldItem(frozenTrollEntity);
        } else {
            boolean flag = frozenTrollEntity.equipItemIfPossible(itemstack).isEmpty();
            if (!flag) {
                putInInventory(frozenTrollEntity, itemstack);
            }
        }
    }

    private static ItemStack removeOneItemFromItemEntity(ItemEntity itemEntity_) {
        ItemStack itemstack = itemEntity_.getItem();
        ItemStack itemstack1 = itemstack.split(1);
        if (itemstack.isEmpty()) {
            itemEntity_.remove(Entity.RemovalReason.DISCARDED);
        } else {
            itemEntity_.setItem(itemstack);
        }

        return itemstack1;
    }

    public static boolean wantsToPickup(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        if (isAdmiringDisabled(frozenTrollEntity) && frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET)) {
            return false;
        } else if (itemStack_.getItem() == BARTERING_ITEM) {
            return isNotHoldingLovedItemInOffHand(frozenTrollEntity);
        } else {
            boolean flag = frozenTrollEntity.canAddToInventory(itemStack_);
            if (itemStack_.is(JTags.FROZEN_TROLL_LOVED_ITEMS)) {
                return flag;
            } else {
                return false;
            }
        }
    }

    public static InteractionResult mobInteract(FrozenTrollEntity frozenTrollEntity, Player playerEntity_, InteractionHand hand_) {
        ItemStack itemstack = playerEntity_.getItemInHand(hand_);
        if (canAdmire(frozenTrollEntity, itemstack)) {
            ItemStack itemstack1 = itemstack.split(1);
            holdInOffhand(frozenTrollEntity, itemstack1);
            admireGoldItem(frozenTrollEntity);
            stopWalking(frozenTrollEntity);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.PASS;
        }
    }

    private static void stopWalking(FrozenTrollEntity frozenTrollEntity) {
        frozenTrollEntity.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        frozenTrollEntity.getNavigation().stop();
    }

    private static void admireGoldItem(LivingEntity livingEntity_) {
        livingEntity_.getBrain().setMemoryWithExpiry(MemoryModuleType.ADMIRING_ITEM, true, 120L);
    }

    private static void holdInOffhand(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        if (isHoldingItemInOffHand(frozenTrollEntity)) {
            frozenTrollEntity.spawnAtLocation(frozenTrollEntity.getItemInHand(InteractionHand.OFF_HAND));
        }

        frozenTrollEntity.holdInOffHand(itemStack_);
    }

    public static boolean canAdmire(FrozenTrollEntity frozenTrollEntity, ItemStack itemStack_) {
        return !isAdmiringDisabled(frozenTrollEntity) && !isAdmiringItem(frozenTrollEntity) && itemStack_.getItem() == BARTERING_ITEM;
    }

    public static Optional<SoundEvent> getSoundForCurrentActivity(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().getActiveNonCoreActivity().map((activity1_) -> getSoundForActivity(frozenTrollEntity, activity1_));
    }

    private static SoundEvent getSoundForActivity(FrozenTrollEntity frozenTrollEntity, Activity activity_) {
        if (activity_ == Activity.ADMIRE_ITEM) {
            return JSounds.FROZEN_TROLL_INTRIGUED.get();
        } else if (seesPlayerHoldingLovedItem(frozenTrollEntity)) {
            return JSounds.FROZEN_TROLL_INTRIGUED.get();
        } else {
            return JSounds.FROZEN_TROLL_INTRIGUED.get();
        }
    }

    public static void updateActivity(FrozenTrollEntity frozenTrollEntity) {
        Brain<FrozenTrollEntity> brain = frozenTrollEntity.getBrain();
        Activity activity = brain.getActiveNonCoreActivity().orElse(null);
        brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.ADMIRE_ITEM, Activity.FIGHT, Activity.AVOID, Activity.CELEBRATE, Activity.RIDE, Activity.IDLE));
        Activity activity1 = brain.getActiveNonCoreActivity().orElse(null);
        if (activity != activity1) {
            getSoundForCurrentActivity(frozenTrollEntity).ifPresent(frozenTrollEntity::playSound);
        }

        frozenTrollEntity.setAggressive(brain.hasMemoryValue(MemoryModuleType.ATTACK_TARGET));
    }

    private static boolean isNotHoldingLovedItemInOffHand(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getOffhandItem().isEmpty() || !isLovedItem(frozenTrollEntity.getOffhandItem());
    }

    public static boolean isAdmiringItem(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM);
    }

    private static boolean isHoldingItemInOffHand(FrozenTrollEntity frozenTrollEntity) {
        return !frozenTrollEntity.getOffhandItem().isEmpty();
    }

    private static boolean isAdmiringDisabled(FrozenTrollEntity frozenTrollEntity) {
        return frozenTrollEntity.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_DISABLED);
    }

    private static boolean seesPlayerHoldingLovedItem(LivingEntity livingEntity_) {
        return livingEntity_.getBrain().hasMemoryValue(MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM);
    }

    public static boolean isPlayerHoldingLovedItem(LivingEntity livingEntity_) {
        return livingEntity_.getType() == EntityType.PLAYER && livingEntity_.isHolding(FrozenTrollTasks::isLovedItem);
    }

    private static Vec3 getRandomNearbyPos(FrozenTrollEntity frozenTrollEntity) {
        Vec3 vector3d = LandRandomPos.getPos(frozenTrollEntity, 4, 2);
        return vector3d == null ? frozenTrollEntity.position() : vector3d;
    }
}
