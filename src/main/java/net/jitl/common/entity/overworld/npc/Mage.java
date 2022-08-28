package net.jitl.common.entity.overworld.npc;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.jitl.common.entity.base.CurrencyForItemsTrade;
import net.jitl.common.entity.base.JVillagerEntity;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Mage extends JVillagerEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            //new CurrencyForItemsTrade(JItems.POWDER_OF_ESSENCIA.get(), 32, JItems.LUNIUM_POWDER.get(), 32, 99, 5),
            //new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, JItems.LOOT_POUCH_BASIC.get(), 1, 99, 5),
            new CurrencyForItemsTrade(JItems.LUNIUM_POWDER.get(), 8, JItems.SAPPHIRE.get(), 1, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.FERMENTED_SPIDER_EYE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 10, Items.GUNPOWDER, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 4, Items.REDSTONE, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.PHANTOM_MEMBRANE, 2, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.GHAST_TEAR, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 16, Items.MAGMA_CREAM, 8, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 8, Items.GLOWSTONE_DUST, 4, 99, 5),
            new CurrencyForItemsTrade(JItems.SAPPHIRE.get(), 32, Items.BLAZE_POWDER, 8, 99, 5)
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
    protected Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades() {
        return TRADES;
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mage.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mage.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
