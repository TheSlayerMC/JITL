package net.jitl.common.entity.cloudia.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.client.ChatUtils;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JPathfinderMob;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
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
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;

public class StarlightBlacksmith extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.FLUFFY_FEATHER.get(), 16, JItems.GOLEM_CHUNK.get(), 8, JItems.GOLEM_SWORD.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.FLUFFY_FEATHER.get(), 16, JItems.GOLEM_CHUNK.get(), 8, JItems.GOLEM_BOW.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.LUNITE_CHUNK.get(), 16, JItems.GOLEM_CHUNK.get(), 8, JItems.STARLIGHT_BLADE.get(), 1, 12, 5),
            new CurrencyForItemsTrade(JItems.LUNITE_CHUNK.get(), 16, JItems.GOLEM_CHUNK.get(), 8, JItems.STARLIGHT_BOW.get(), 1, 12, 5)
    }));

    public StarlightBlacksmith(EntityType<? extends JPathfinderMob> type, Level worldIn) {
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
            case 0 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.starlight_blacksmith1");
            case 1 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.starlight_blacksmith2");
            case 2 -> ChatUtils.addDialogStyleChat(player, "jitl.trader.starlight_blacksmith3");
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

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.starlight_blacksmith.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.starlight_blacksmith.idle");

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
