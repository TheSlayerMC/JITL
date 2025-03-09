package net.jitl.common.entity.overworld.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.ChatUtils;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
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
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Gunsmith extends JVillagerEntity {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, Items.BLAZE_ROD, 16, JItems.NETHER_PLASMA, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, JItems.BLUE_GEM, 16, JItems.OCEAN_PLASMA, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, JItems.GREEN_GEM, 16, JItems.FOREST_PLASMA, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, JItems.STONE_CLUMP, 16, JItems.ROCK_LAUNCHER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, JItems.YELLOW_GEM, 16, JItems.CHAOS_CANNON, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GUN_BASE, 1, Items.ENDER_EYE, 8, JItems.EYE_BLASTER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.ROCKY_HAMMER, 1, JItems.BLOODCRUST_INGOT, 8, JItems.FLAMING_HAMMER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.ROCKY_HAMMER, 1, Items.BLAZE_ROD, 8, JItems.NETHIC_HAMMER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.NETHIC_HAMMER, 1, Items.WITHER_SKELETON_SKULL, 1, JItems.WITHIC_HAMMER, 1, 99, 5)
    }));

    public Gunsmith(EntityType<? extends JVillagerEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.gunsmith.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.gunsmith.idle");

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

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {

    }

    @Override
    protected void updateTrades() {

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
