package net.jitl.common.entity.euca;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.core.init.internal.JItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
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
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class Crypian extends JVillagerEntity {

    private static final EntityDataAccessor<Boolean> DATA_CAN_TRADE = SynchedEntityData.defineId(Crypian.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ALLOY_HOUSE = SynchedEntityData.defineId(Crypian.class, EntityDataSerializers.BOOLEAN);

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.PERIDOT_GEMSTONE.get(), 1, Items.COMPASS, 1, 12, 5)
    }));

    public Crypian(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        int chance = this.random.nextInt(2);
        this.entityData.define(DATA_CAN_TRADE, chance == 0);
        this.entityData.define(DATA_ALLOY_HOUSE, false);
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
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand playerHand) {
        if(isAlive() && this.getTradingPlayer() == null && canTrade() && !isAlloyHouse()) {
            switch(random.nextInt(3)) {
                case 0:
                    player.sendSystemMessage(Component.translatable("Crypian: Yeah I took some of it"));
                    break;
                case 1:
                    player.sendSystemMessage(Component.translatable("Crypian: IT'S MINE.... Unless you want it?"));
                    break;
                case 2:
                    player.sendSystemMessage(Component.translatable("Crypian: I might go see where he has moved to...."));
                    break;
            }
            trade(player);
        } else {
            if(isAlloyHouse()) {
                switch(random.nextInt(3)) {
                    case 0:
                        player.sendSystemMessage(Component.translatable("Crypian: Alloy Mender used to live here, but he moved into the royal lands"));
                    break;
                    case 1:
                        player.sendSystemMessage(Component.translatable("Crypian: Everyone has raided his house"));
                        break;
                    case 2:
                        player.sendSystemMessage(Component.translatable("Crypian: You could possibly trade with some of them"));
                        break;
                }
            } else {
                switch(random.nextInt(2)) {
                    case 0:
                        player.sendSystemMessage(Component.translatable("Crypian: I missed out on the Alloy Menders gear"));
                        break;
                    case 1:
                        player.sendSystemMessage(Component.translatable("Crypian: A few others have some of the Alloy Menders things"));
                        break;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

   /* private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crypian.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crypian.idle", true));
        return PlayState.CONTINUE;
    }*/

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.crypian.idle");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.crypian.idle");
    
    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle", state -> state.setAndContinue(state.isMoving() ? MOVING : IDLE)));
    }
}
