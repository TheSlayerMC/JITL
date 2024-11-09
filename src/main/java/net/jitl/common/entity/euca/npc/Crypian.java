package net.jitl.common.entity.euca.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.ChatUtils;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JPathfinderMob;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Crypian extends JVillagerEntity {

    private static final EntityDataAccessor<Boolean> DATA_CAN_TRADE = SynchedEntityData.defineId(Crypian.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ALLOY_HOUSE = SynchedEntityData.defineId(Crypian.class, EntityDataSerializers.BOOLEAN);

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE.get(), 1, Items.COMPASS, 1, 12, 5)
    }));

    public Crypian(EntityType<? extends JPathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        int chance = this.random.nextInt(2);
        pBuilder.define(DATA_CAN_TRADE, chance == 0);
        pBuilder.define(DATA_ALLOY_HOUSE, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("canTrade", this.entityData.get(DATA_CAN_TRADE));
        compound.putBoolean("alloyHouse", this.entityData.get(DATA_ALLOY_HOUSE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setCanTrade(compound.getBoolean("canTrade"));
        setAlloyHouse(compound.getBoolean("alloyHouse"));
    }

    public void setAlloyHouse(boolean canTrade) {
        this.entityData.set(DATA_ALLOY_HOUSE, canTrade);
    }

    public void setCanTrade(boolean canTrade) {
        this.entityData.set(DATA_CAN_TRADE, canTrade);
    }

    public boolean isAlloyHouse(){
        return this.entityData.get(DATA_ALLOY_HOUSE);
    }

    @Override
    public boolean canTrade(){
        return this.entityData.get(DATA_CAN_TRADE);
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.NPC_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand playerHand) {
        if(isAlive() && this.getTradingPlayer() == null && canTrade() && !isAlloyHouse()) {
            switch (random.nextInt(3)) {
                case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.crypian1");
                case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.crypian2");
                case 2 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.crypian3");
            }
            trade(player);
        } else {
            if(isAlloyHouse()) {
                switch (random.nextInt(3)) {
                    case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.npc.crypian_alloy1");
                    case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.npc.crypian_alloy2");
                    case 2 -> ChatUtils.addDialogStyleChat(player, "jitl.npc.crypian_alloy3");
                }
            } else {
                switch (random.nextInt(2)) {
                    case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.npc.crypian1");
                    case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.npc.crypian2");
                }
            }
        }
        return InteractionResult.SUCCESS_SERVER;
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.crypian.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.crypian.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

//    @Override
//    public void die(DamageSource d) {
//        super.die(d);
//        if(!level().isClientSide) {
//            for(int i = 0; i < 2 + random.nextInt(4); i++) {
//                this.level().addFreshEntity(new Sentacoin(JEntities.SENTACOIN_TYPE.get(), this));
//            }
//        }
//    }
}
