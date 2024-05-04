package net.jitl.common.entity.euca.npc;

import net.jitl.client.ChatUtils;
import net.jitl.common.block.entity.PedestalTile;
import net.jitl.common.entity.base.MobStats;
import net.jitl.core.init.internal.JBlocks;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
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


    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.royal_king.idle");

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> state.setAndContinue(IDLE)));
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MobStats.NPC_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, MobStats.STANDARD_KNOCKBACK_RESISTANCE)
                .add(Attributes.FOLLOW_RANGE, MobStats.STANDARD_FOLLOW_RANGE)
                .add(Attributes.MOVEMENT_SPEED, MobStats.STANDARD_MOVEMENT_SPEED).build();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(isActivated()) {
            if (!level().isClientSide) {
                if(hasCrown())
                    this.level().addFreshEntity(new ItemEntity(level(), this.position().x + 0.5F, this.position().y + 1.4F, this.position().z + 0.5F, new ItemStack(JItems.EUDOR_CROWN.get(), 1)));
                setHasCrown(false);
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_IS_ACTIVATED, false);
        pBuilder.define(DATA_HAS_CROWN, true);
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
        int totalRoyalDisks = 0;
        int totalMetalDisks = 0;
        int totalTablets = 0;

        int neededRoyalDisks = 4;
        int neededMetalDisks = 2;
        int neededTables = 8;

        final Level world = this.level();
        final BlockPos entityPos = BlockPos.containing(this.position());
        for(int x = -check_radius; x <= check_radius; x++) {
            for(int z = -check_radius; z <= check_radius; z++) {
                for(int y = -check_radius; y <= check_radius; y++) {
                    final BlockPos pos = entityPos.offset(x, y, z);
                    final Block block = world.getBlockState(pos).getBlock();
                    if(block == JBlocks.ROYAL_PEDESTAL.get()) {
                        PedestalTile tile = (PedestalTile) world.getBlockEntity(pos);
                        if(tile != null) {
                            if(tile.getItem(0).getItem().equals(JItems.ROYAL_DISK.get())) {
                                totalRoyalDisks++;
                                neededRoyalDisks--;
                            }

                            if(tile.getItem(0).getItem().equals(JItems.METAL_DISK.get())) {
                                totalMetalDisks++;
                                neededMetalDisks--;
                            }

                            if(tile.getItem(0).getItem().equals(JItems.EUCA_TABLET.get())) {
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
        if(totalRoyalDisks >= 4 && totalTablets >= 8 && totalMetalDisks >= 2) {
            this.playSound(JSounds.COIN_PICKUP.get(), 1.5F, 1.0F);
            setActivated(true);
        }
            if(hasCrown())
                ChatUtils.addDialogStyleChat(playerEntity, "I need " + neededRoyalDisks + " more Royal Disks, " + neededMetalDisks + " Metal Disks and " + neededTables + " Euca Tablets");
            if (!hasCrown()) {
                ChatUtils.addDialogStyleChat(playerEntity, "jitl.king.hero");
        }
        return super.mobInteract(playerEntity, hand);
    }
}
