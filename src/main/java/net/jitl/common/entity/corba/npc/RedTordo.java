package net.jitl.common.entity.corba.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
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

public class RedTordo extends JVillagerEntity {

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.TOMATO_SEEDS.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.CORVEGGIES.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.CRACKENCANE_SEEDS.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.CRAKEBULB_SEEDS.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.SPINEBERRIES.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.GLOWA_SEEDS.get(), 16, 12, 5),
            new CurrencyForItemsTrade(JItems.NATURE_TABLET.get(), 16, JItems.COLLECTOR_ROCK.get(), 16, JItems.ZATPEDAL_SEEDS.get(), 16, 12, 5)
    }));

    public RedTordo(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.26).build();
    }

    @Override
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    private final RawAnimation MOVING = RawAnimation.begin().thenLoop("animation.green_tordo.walk");
    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.green_tordo.idle");

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