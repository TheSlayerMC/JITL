package net.jitl.core.init.internal;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class JFood {

    public static final FoodProperties CRYSTAL_APPLE = (new FoodProperties.Builder()).nutrition(8).saturationModifier(2.2F)
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

    public static class JConsumables {

        public static final Consumable DEFAULT_FOOD = defaultFood().build();
        public static final Consumable DEFAULT_DRINK = defaultDrink().build();
        
        public static final Consumable CRYSTAL_APPLE = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 800, 2), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 4000, 0), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4000, 0), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 4000, 2), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEAL, 10, 1), 1.0F))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1400, 4), 1.0F)).build();

        public static final Consumable HONGLOW_SHROOM = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 1), 0.5F)).build();

        public static final Consumable FLORO_PEDAL = quickFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 3), 0.5F)).build();
        
        public static final Consumable FRIED_GHAST_TENTACLE = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 1), 1.0F)).build();

        public static final Consumable FLAMING_GHAST_TENTACLE = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 1), 1.0F)).build();


        public static final Consumable SNAKE_FLESH = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5, 1), 1.0F)).build();

        public static final Consumable SLIMY_FLESH = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.CONFUSION, 5, 1), 0.5F)).build();

        public static final Consumable BREATHING_FUNGUS = quickFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 5, 1), 0.5F)).build();

        public static final Consumable CANDY_CANE = quickFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 1.0F)).build();

        public static final Consumable QUICK_EAT = quickFood().build();

        public static Consumable.Builder defaultFood() {
            return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
        }

        public static Consumable.Builder quickFood() {
            return Consumable.builder().consumeSeconds(1F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
        }

        public static Consumable.Builder defaultDrink() {
            return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
        }
    }
}