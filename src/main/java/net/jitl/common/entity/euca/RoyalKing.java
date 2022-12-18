package net.jitl.common.entity.euca;

import net.jitl.common.block.entity.PedestalTile;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RoyalKing extends PathfinderMob implements GeoEntity {

    private static final EntityDataAccessor<Boolean> DATA_IS_ACTIVATED = SynchedEntityData.defineId(RoyalKing.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_HAS_CROWN = SynchedEntityData.defineId(RoyalKing.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public RoyalKing(EntityType<? extends RoyalKing> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() { }


    /*private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.royal_king.idle", true));
        return PlayState.CONTINUE;
    }*/

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0D).build();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            if (!level.isClientSide) {
                if(hasCrown())
                    this.level.addFreshEntity(new ItemEntity(level, this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.EUDOR_CROWN.get(), 1)));
                setHasCrown(false);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_ACTIVATED, false);
        this.entityData.define(DATA_HAS_CROWN, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("activated", this.entityData.get(DATA_IS_ACTIVATED));
        compound.putBoolean("hasCrown", this.entityData.get(DATA_HAS_CROWN));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setActivated(compound.getBoolean("activated"));
        setHasCrown(compound.getBoolean("hasCrown"));
    }

    public void setActivated(boolean activated) {
        this.entityData.set(DATA_IS_ACTIVATED, activated);
    }

    public void setHasCrown(boolean activated) {
        this.entityData.set(DATA_HAS_CROWN, activated);
    }

    public boolean isActivated() {
        return this.entityData.get(DATA_IS_ACTIVATED);
    }

    public boolean hasCrown() {
        return this.entityData.get(DATA_HAS_CROWN);
    }

    @Override
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        int check_radius = 10;
        int totalDisks = 0;
        int totalTablets = 0;

        int neededDisks = 6;
        int neededTables = 8;

        final Level world = this.level;
        final BlockPos entityPos = new BlockPos(this.position());
        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                for (int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if (block == JBlocks.ROYAL_PEDESTAL.get()) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if (tile != null) {
                            if (tile.getItem(0).getItem().equals(JItems.ROYAL_DISK.get())) {
                                totalDisks++;
                                neededDisks--;
                            }

                            if (tile.getItem(0).getItem().equals(JItems.EUCA_TABLET.get())) {
                                totalTablets++;
                                neededTables--;
                            }

                            if(isActivated()) {
                                tile.setItem(0, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            }
        }
        if(totalDisks >= 6 && totalTablets >= 8) {
            this.playSound(JSounds.FROZEN_GUARDIAN_DEATH.get(), 1.5F, 1.0F);
            setActivated(true);
        }
        if(!level.isClientSide) {
            if(hasCrown())
                playerEntity.sendSystemMessage(Component.translatable("I need " + neededDisks + " more Royal Disks and " + neededTables + " Euca Tablets"));
            if (!hasCrown()) {
                playerEntity.sendSystemMessage(Component.translatable("Good Luck... Hero"));
            }
        }
        return super.mobInteract(playerEntity, hand);
    }
}
