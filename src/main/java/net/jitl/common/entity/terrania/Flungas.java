package net.jitl.common.entity.terrania;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.entity.base.JPathfinderMob;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimatableManager;

public class Flungas extends JPathfinderMob {

    public Flungas(EntityType<? extends Flungas> entityType, Level world) {
        super(entityType, world);
        setKnowledge(EnumKnowledge.TERRANIA, 5F);
    }

    @Override
    protected void registerGoals() { }

    @Override
    protected SoundEvent getAmbientSound() {
        return JSounds.TERRA_SLUG.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return JSounds.TERRA_SLUG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JSounds.TERRA_SLUG_DEATH.get();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public void push(Entity entity) { }

    @Override
    protected void doPush(Entity entityIn) { }

    @Override
    public boolean isPushable() {
        return false;
    }


    @Override
    protected void controller(AnimatableManager.ControllerRegistrar controllers) { }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.FLUNGUS_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.FLUNGUS_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.FLUNGUS_MOVEMENT_SPEED).build();
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack heldItem = player.getMainHandItem();
        if(heldItem.getItem() == Items.GLASS_BOTTLE) {
            this.level().playSound(player, player.getOnPos(), JSounds.BOTTLE_PLUG.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            if(!player.isCreative()) heldItem.shrink(1);
            this.level().addFreshEntity(new ItemEntity(level(), player.position().x, player.position().y, player.position().z, new ItemStack(JItems.BILE_VIAL.get(), 1)));
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }
}
