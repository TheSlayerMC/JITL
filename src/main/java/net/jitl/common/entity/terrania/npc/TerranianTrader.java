package net.jitl.common.entity.terrania.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
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
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class TerranianTrader extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.PURPLE_POWDER.get(), 16, JItems.DARK_TERRANIAN_SOIL.get(), 16, JItems.HEALERS_BLADE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.PURPLE_POWDER.get(), 16, JItems.LIGHT_TERRANIAN_SOIL.get(), 16, JItems.TREE_HUGGER.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.PURPLE_POWDER.get(), 16, JItems.GREEN_GEM.get(), 4, JItems.ANCIENT_CHUNK.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.PURPLE_POWDER.get(), 16, JItems.PURPLE_GEM.get(), 4, JItems.ANCIENT_SHARD.get(), 1, 12, 5),

    }));

    public TerranianTrader(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.terranian_trader.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.terranian_trader.idle");

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
}