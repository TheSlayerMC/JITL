package net.jitl.core.init.internal;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class JFood {

    public static final FoodProperties CRYSTAL_APPLE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(2.2F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 800, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.SPEED, 600, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.STRENGTH, 4000, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.INSTANT_HEALTH, 10, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1400, 4), 1.0F)
            //.effect(() -> new MobEffectInstance(JMobEffects.FROSTBURN.get(), 600, 1), 1.0F)
            .alwaysEdible().build();

    public static final FoodProperties REDCURRANT = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.25F).alwaysEdible().build();
    public static final FoodProperties BRADBERRY = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.25F).alwaysEdible().build();
    public static final FoodProperties HONGOSROOM = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5F).build();
    public static final FoodProperties HONGLOWSROOM = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.5F).build();

    public static final FoodProperties FLORO_PEDAL = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties TOMATO = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();

    public static final FoodProperties AIRMELON = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();

    public static final FoodProperties FRIED_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties FLAMING_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.6F).build();
    public static final FoodProperties FRIED_FLAMING_GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.6F).build();
    public static final FoodProperties GHAST_TENTACLE = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.6F).build();

    public static final FoodProperties SNAKE_FLESH = (new FoodProperties.Builder()).nutrition(5).saturationModifier(1.2F).build();
    public static final FoodProperties SLIMY_FLESH = (new FoodProperties.Builder()).nutrition(5).saturationModifier(1.2F).build();
    public static final FoodProperties BREATHING_FUNGUS = (new FoodProperties.Builder()).nutrition(5).saturationModifier(1.2F).build();

    public static final FoodProperties FRIED_EGG = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.6F).build();

    public static final FoodProperties MINT_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.6F).build();
    public static final FoodProperties FRUITY_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.6F).build();
    public static final FoodProperties CHERRY_CANDY_CANE = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.6F).build();

    public static final FoodProperties PEPPERMINT = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties JELLYBEANS = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();
    public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties VANILLA_WAFER = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1F).build();

    public static final FoodProperties EUCA_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties DEPTHS_MEAT = (new FoodProperties.Builder()).nutrition(7).saturationModifier(0.7F).build();


}