package net.jitl.common.entity.frozen.npc;

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
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Eskimo extends JVillagerEntity {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 16, JItems.SOULSTONE.get(), 16, JItems.FROSTBORN_SOUL.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 4, JItems.FROZEN_ICE_BALL.get(), 1, 12, 5),

            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 16, JItems.FROST_GEM.get(), 16, JItems.FROSTY_SWORD.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 16, JItems.FROST_GEM.get(), 16, JItems.FROSTY_BOW.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 16, JItems.FROST_GEM.get(), 10, JItems.FROSTY_PIERCER.get(), 16, 12, 5),

            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 16, JItems.FROSTY_SWORD.get(), 1, JItems.FROSTBITTEN_SWORD.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 16, JItems.FROSTY_BOW.get(), 1, JItems.FROSTBITTEN_BOW.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 16, JItems.FROSTY_PIERCER.get(), 16, JItems.FROSTBITTEN_PIERCER.get(), 16, 12, 5),

            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 4, JItems.FROST_GEM.get(), 16, JItems.CRYSTAL_FLAKE_HELMET.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 4, JItems.FROST_GEM.get(), 16, JItems.CRYSTAL_FLAKE_CHEST.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 4, JItems.FROST_GEM.get(), 16, JItems.CRYSTAL_FLAKE_LEGS.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.CRYSTAL_FLAKE.get(), 4, JItems.FROST_GEM.get(), 16, JItems.CRYSTAL_FLAKE_BOOTS.get(), 1, 12, 5),

            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 15, JItems.CRYSTAL_FLAKE_HELMET.get(), 1, JItems.FROSTBITTEN_HELMET.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 15, JItems.CRYSTAL_FLAKE_CHEST.get(), 1, JItems.FROSTBITTEN_CHEST.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 15, JItems.CRYSTAL_FLAKE_LEGS.get(), 1, JItems.FROSTBITTEN_LEGS.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FROST_FLAKE.get(), 15, JItems.CRYSTAL_FLAKE_BOOTS.get(), 1, JItems.FROSTBITTEN_BOOTS.get(), 1, 12, 5)
    }));

    public Eskimo(EntityType<? extends JVillagerEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand playerHand) {
        switch (random.nextInt(3)) {
            case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.eskimo1");
            case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.eskimo2");
            case 2 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.eskimo3");
        }
        return super.mobInteract(player, playerHand);
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.eskimo.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.eskimo.idle");

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

    @Override
    protected void updateTrades() {

    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
