package net.jitl.common.entity.terrania;

import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Flungas extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Flungas(EntityType<? extends Flungas> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

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
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void push(Entity entity) {

    }

    @Override
    protected void doPush(Entity entityIn) {

    }

    @Override
    public boolean isPushable() {
        return false;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1000D).build();
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
