package net.jitl.common.entity.corba.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class GreenTordo extends JVillagerEntity {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.OVER_SEEING_EYE.get(), 16, JItems.HEALERS_BLADE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.TREE_HUGGER.get(), 1, 12, 5)
    }));

    public GreenTordo(EntityType<? extends JVillagerEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.NPC_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.green_tordo.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.green_tordo.idle");

    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 5, state -> {
            if(state.isMoving()) {
                return state.setAndContinue(MOVING);
            } else {
                return state.setAndContinue(IDLE);
            }
        }));
    }

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}