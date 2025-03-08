package net.jitl.common.entity.euca.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.ChatUtils;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JPathfinderMob;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JBlocks;
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
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class AlloyMender extends JVillagerEntity {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.SHIMMERER_DUST.get(), 64, JItems.METAL_DISK.get(), 1, JItems.ROYAL_BOW.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.SHIMMERER_DUST.get(), 16, JItems.GOLDER_DUST.get(), 16, JItems.ROYAL_KNIFE.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.SHIMMERER_DUST.get(), 64, JItems.METAL_DISK.get(), 2, JItems.ROYAL_HAMMER.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JBlocks.CELESTIUM_BLOCK.get(), 8, JBlocks.MEKYUM_BLOCK.get(), 8, JItems.CELEKIUM_BATTLE_AXE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JBlocks.CELESTIUM_BLOCK.get(), 8, JBlocks.KORITE_BLOCK.get(), 8, JItems.CELESTITE_BATTLE_AXE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JBlocks.STORON_BLOCK.get(), 8, JBlocks.MEKYUM_BLOCK.get(), 8, JItems.STORUM_BATTLE_AXE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JBlocks.STORON_BLOCK.get(), 8, JBlocks.KORITE_BLOCK.get(), 8, JItems.BRONZED_BATTLE_AXE.get(), 1, 12, 5)
    }));

    public AlloyMender(EntityType<? extends JVillagerEntity> type, Level worldIn) {
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.alloy_mender.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.alloy_mender.idle");

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
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand playerHand) {
        switch (random.nextInt(4)) {
            case 0 ->
                    ChatUtils.addDialogStyleChat(player, "jitl.trader.alloy_mender1");
            case 1 ->
                    ChatUtils.addDialogStyleChat(player, "jitl.trader.alloy_mender2");
            case 2 ->
                    ChatUtils.addDialogStyleChat(player, "jitl.trader.alloy_mender3");
            case 3 ->
                    ChatUtils.addDialogStyleChat(player, "jitl.trader.alloy_mender4");
        }
        return super.mobInteract(player, playerHand);
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
