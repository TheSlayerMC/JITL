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
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class Mage extends JVillagerEntity {

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, JItems.LOOT_POUCH.get(), 1, 99, 5),
            new CurrencyForItemsTrade(JItems.LUNIUM_POWDER.get(), 8, JItems.SAPPHIRE.get(), 1, 99, 5),
            new CurrencyForItemsTrade(JItems.CRIMSON_QUARTZ.get(), 32, JItems.MEKYUM_INGOT.get(), 16, JItems.PET_ROBOT_SPAWNER.get(), 1, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.FERMENTED_SPIDER_EYE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 10, Items.GUNPOWDER, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 4, Items.REDSTONE, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.PHANTOM_MEMBRANE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.GHAST_TEAR, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.MAGMA_CREAM, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 8, Items.GLOWSTONE_DUST, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 32, Items.BLAZE_POWDER, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.STONE_STICK, 2, JItems.GREEN_GEM.get(), 10, JItems.EARTHEN_HAMMER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.EARTHEN_HAMMER, 1, JItems.PURPLE_GEM.get(), 16, JItems.SPELLBINDING_HAMMER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.GREEN_GEM.get(), 10, JItems.STAFF_OF_GREENPACE, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.BLOODCRUST_INGOT.get(), 10, JItems.STAFF_OF_HELLSTONE, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.PURPLE_GEM.get(), 10, JItems.DOOMSBRINGER, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.BOTTLE_OF_ESSENCIA.get(), 8, JItems.STAFF_OF_ESSENCIA, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.YELLOW_GEM.get(), 10, JItems.WIZARDS_STAR, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, JItems.BLUE_GEM.get(), 10, JItems.STAFF_OF_ENLIGHTENMENT, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.GREEN_GEM, 1, JItems.STAFF_OF_GREENPACE.get(), 10, JItems.STAFF_OF_CONJURING, 1, 99, 5),
            new CurrencyForItemsTrade(JItems.STAFF_BASE, 1, Items.ENDER_PEARL, 64, JItems.TELEPORTATION_STAFF, 1, 99, 5)
    }));

    public Mage(EntityType<? extends JVillagerEntity> type, Level worldIn) {
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
            case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.mage1");
            case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.mage2");
            case 2 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.mage3");
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.mage.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.mage.idle");

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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
